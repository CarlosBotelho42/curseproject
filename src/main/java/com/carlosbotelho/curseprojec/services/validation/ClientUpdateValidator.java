package com.carlosbotelho.curseprojec.services.validation;

import com.carlosbotelho.curseprojec.domain.Client;
import com.carlosbotelho.curseprojec.domain.enums.ClientRole;
import com.carlosbotelho.curseprojec.dto.ClientDTO;
import com.carlosbotelho.curseprojec.dto.ClientNewDTO;
import com.carlosbotelho.curseprojec.repositories.ClientRepository;
import com.carlosbotelho.curseprojec.resources.exceptions.FieldMessage;
import com.carlosbotelho.curseprojec.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

    @Autowired
    private HttpServletRequest request;
    //Esse cara tem uma função que me perite ter o parametro passado na URI

    @Autowired
    private ClientRepository repository;

    @Override
    public void initialize(ClientUpdate ann) {
    }

    @Override
    public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {

        //Essa função pega o map de variaveis de URI que esta na requisiçao(que no casa são clients e o id)
        Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Client aux = repository.findByEmail(objDto.getEmail());

        //Se for encontrado algum cliente na busca, tem que testar se o Id desse cliente é diferente do Id chamado para atualizar
        if(aux != null && !aux.getId().equals(uriId)){
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
