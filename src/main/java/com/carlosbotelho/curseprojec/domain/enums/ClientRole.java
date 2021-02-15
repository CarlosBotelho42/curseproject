package com.carlosbotelho.curseprojec.domain.enums;

public enum ClientRole {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Juridica");

    private int cod;
    private String description;

    private ClientRole(int cod, String description){
        this.cod = cod;
        this.description = description;
    }

    public int getCod(){
        return  cod;
    }

    public String getDescription(){
        return description;
    }

    public static ClientRole toEnum(Integer cod){
        if(cod == null){
            return  null;
        }

        for (ClientRole x : ClientRole.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }

        throw new IllegalArgumentException(("Id inválido: " + cod));
    }

}
