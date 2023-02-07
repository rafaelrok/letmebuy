package com.rafaelvieira.letmebuy.enums;

/**
 * @author rafae
 */

public enum TypePayment {

    PENDENTE, QUITADO, CANCELADO
//
//    private final int code;
//    private final String description;
//
//    private TypePayment(int code, String description) {
//        this.code = code;
//        this.description = description;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public String getDescription () {
//        return description;
//    }
//
//    public static TypePayment toEnum(Integer code) {
//        if (code == null) {
//            return null;
//        }
//        for (TypePayment x : TypePayment.values()) {
//            if (code.equals(x.getCode())) {
//                return x;
//            }
//        }
//        throw new IllegalArgumentException("Code TypePayment inválido: " + code);
//    }
//
//    public static TypePayment toValue(String description) {
//        if (description == null) {
//            return null;
//        }
//        for (TypePayment x : TypePayment.values()) {
//            if (description.equals(x.getDescription())) {
//                return x;
//            }
//        }
//        throw new IllegalArgumentException("Description TypePayment inválido: " + description);
//    }
}
