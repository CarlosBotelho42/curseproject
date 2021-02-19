package com.carlosbotelho.curseprojec.dto;

import com.carlosbotelho.curseprojec.services.validation.ClientInsert;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClientInsert
public class ClientNewDTO implements Serializable {

    @NotEmpty(message = "Valor obrigatório!")
    @Length(min = 5, max = 50, message = "Tamanho deve ser entre 5 a 50 caracteres")
    private String name;

    @NotEmpty(message = "Valor obrigatório!")
    @Length(min = 5, max = 50, message = "Tamanho deve ser entre 5 a 80 caracteres")
    private String email;

    @NotEmpty(message = "Valor obrigatório!")
    private String cpfOrCnpj;

    private Integer clientRole;

    @NotEmpty(message = "Valor obrigatório!")
    private String logrdouro;

    @NotEmpty(message = "Valor obrigatório!")
    private String number;

    private String complement;
    private String district;

    @NotEmpty(message = "Valor obrigatório!")
    private String cep;

    @NotEmpty(message = "Valor obrigatório!")
    private String phone1;

    private String phone2;
    private String phone3;

    private Integer cityId;

    public ClientNewDTO(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpfOrCnpj() {
        return cpfOrCnpj;
    }

    public void setCpfOrCnpj(String cpfOrCnpj) {
        this.cpfOrCnpj = cpfOrCnpj;
    }

    public Integer getClientRole() {
        return clientRole;
    }

    public void setClientRole(Integer clientRole) {
        this.clientRole = clientRole;
    }

    public String getLogrdouro() {
        return logrdouro;
    }

    public void setLogrdouro(String logrdouro) {
        this.logrdouro = logrdouro;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
