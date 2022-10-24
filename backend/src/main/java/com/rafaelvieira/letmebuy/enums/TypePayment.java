package com.rafaelvieira.letmebuy.enums;

/**
 * @author rafae
 */

public enum TypePayment {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private final int code;
    private final String description;

    private TypePayment(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription () {
        return description;
    }

    public static TypePayment toEnum(Integer code) {

        if (code == null) {
            return null;
        }

        for (TypePayment x : TypePayment.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + code);
    }
}
