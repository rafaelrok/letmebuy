package com.rafaelvieira.letmebuy.utils;

/**
 * @author rafae
 */

public class DocumentValidity {

    private static final int[] WEIGHT_CPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    private static final int[] WEIGHT_CNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calculate(final String str, final int[] weight) {
        int sum = 0;
        for (int i = str.length() - 1, digit; i >= 0; i--) {
            digit = Integer.parseInt(str.substring(i, i + 1));
            sum += digit * weight[weight.length - str.length() + i];
        }
        sum = 11 - sum % 11;
        return sum > 9 ? 0 : sum;
    }

    public static boolean isValidCPF(final String cpf) {
        if ((cpf == null) || (cpf.length() != 11) || cpf.matches(cpf.charAt(0) + "{11}")) {
            return false;
        }
        final int digit1 = calculate(cpf.substring(0, 9), WEIGHT_CPF);
        final int digit2 = calculate(cpf.substring(0, 9) + digit1, WEIGHT_CPF);
        return cpf.equals(cpf.substring(0, 9) + Integer.toString(digit1) + Integer.toString(digit2));
    }

    public static boolean isValidCNPJ(final String cnpj) {
        if ((cnpj == null) || (cnpj.length() != 14) || cnpj.matches(cnpj.charAt(0) + "{14}")) {
            return false;
        }
        final int digit1 = calculate(cnpj.substring(0, 12), WEIGHT_CNPJ);
        final int digit2 = calculate(cnpj.substring(0, 12) + digit1, WEIGHT_CNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + Integer.toString(digit1) + Integer.toString(digit2));
    }
}
