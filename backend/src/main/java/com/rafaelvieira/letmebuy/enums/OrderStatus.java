package com.rafaelvieira.letmebuy.enums;

/**
 * @author rafae
 */

public enum OrderStatus {
//    PENDENTE, FINALIZADO, CANCELADO, ENTREGUE

    PENDENTE(1, "Waiting Order"),
    FINALIZADO(2, "Finished Order"),
    CANCELADO(3, "Order Canceled"),
    ENTREGUE(4, "Order Delivered");


    private int code;
    private String description;

    //tipo enum o construtor é privado
    private OrderStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }
    //Somente getters
    public int getCode() {
        return code;
    }

    public String getDescription () {
        return description;
    }

    //Statico para se iniciada mesmo que não tenha instanciado
    public static OrderStatus toEnum(Integer code) {

        if (code == null) {
            return null;
        }

        for (OrderStatus x : OrderStatus.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + code);
    }
}
