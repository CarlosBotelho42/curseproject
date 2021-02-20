package com.carlosbotelho.curseprojec.services.validation;

import com.carlosbotelho.curseprojec.domain.Client;
import com.carlosbotelho.curseprojec.domain.enums.ClientRole;
import com.carlosbotelho.curseprojec.dto.ClientNewDTO;
import com.carlosbotelho.curseprojec.repositories.ClientRepository;
import com.carlosbotelho.curseprojec.resources.exceptions.FieldMessage;
import com.carlosbotelho.curseprojec.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

    @Autowired
    ClientRepository repository;

    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

            //Se o tipo da pessoa for Pessoa fisica e se o CPFouCNPJ não for valido, então...
        if(objDto.getClientRole().equals(ClientRole.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOrCnpj())){
            list.add(new FieldMessage("cpfOrCnpj", "CPF inválido!"));
        }

        if(objDto.getClientRole().equals(ClientRole.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOrCnpj())){
            list.add(new FieldMessage("cpfOrCnpj", "CNPJ inválido!"));
        }

        Client aux = repository.findByEmail(objDto.getEmail());

        if(aux != null){
            list.add(new FieldMessage("email", "Email já cadastrado!"));
        }


        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
