package com.carlosbotelho.curseprojec.domain.enums;

public enum PaymentStatus {

    PENDENTE(1,"Pagamento pendente"),
    QUITADO(2,"Pagamento quitado"),
    CANCELADO(3,"Pagamento cancelado");

    private int cod;
    private String description;

    private PaymentStatus(int cod, String description){
        this.cod = cod;
        this.description = description;
    }

    public int getCod(){
        return  cod;
    }

    public String getDescription(){
        return description;
    }

    public static PaymentStatus toEnum(Integer cod){
        if(cod == null){
            return  null;
        }

        for (PaymentStatus x : PaymentStatus.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }

        throw new IllegalArgumentException(("Id inválido: " + cod));
    }

}
