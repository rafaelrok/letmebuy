package com.rafaelvieira.letmebuy.enums;

/**
 * @author rafae
 */

public enum TypeCostumer {

    PESSOAFISICA(1, "Pessoa Física"),
    PESSOAJURIDICA(2, "Pessoa Jurídica");

    private int code;
    private String description;

    // tipo enum o construtor é privado
    private TypeCostumer(int code, String description) {
        this.code = code;
        this.description = description;
    }

    // Somente getters
    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // Statico para se iniciada mesmo que não tenha instanciado
    public static TypeCostumer toEnum(Integer code) {

        if (code == null) {
            return null;
        }

        for (TypeCostumer x : TypeCostumer.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + code);
    }
}
