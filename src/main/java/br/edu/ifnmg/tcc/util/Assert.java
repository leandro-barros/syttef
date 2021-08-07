package br.edu.ifnmg.tcc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Assert {
    
    public static boolean isValidEmail(String emailAddress) {
        Pattern pat;
        Matcher mat;
        pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        mat = pat.matcher(emailAddress);
        try {
            if (!mat.find()) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;

    }

    public static Boolean isCpf(String paramCpf) {
        paramCpf = paramCpf.replace(".", "").replace("-", "");
        String verifica = "";

        if ((paramCpf == null) || (paramCpf.length() != 11)) {
            return false;
        }
        int caracteresIguais = 0;
        for (int i = 0; i < paramCpf.length(); i++) {
            verifica = paramCpf.substring(i, i+1);
            if (i > 0) {
                if (verifica.equals(paramCpf.substring(i - 1, i))) {
                    caracteresIguais++;
                }
            }

        }

        if (caracteresIguais == 11 - 1) {
            return false;
        }
        /*
         * cpf = XXX.XXX.XXX-DV dv = d1 * 10 + d2
         */
        long cpf = Long.parseLong(paramCpf);
        //int x = int.Parse(cpf);
        long dv = cpf % 100;
        cpf /= 10;
        int d2 = 0;
        for (int i = 1; i <= 10; i++) {
            long x = cpf % (long) Math.pow(10, 11 - i);
            long y = x / (long) Math.pow(10, 10 - i);
            Long converteY = y;
            d2 += Integer.parseInt(converteY.toString()) * (i - 1);
        }
        d2 = (d2 % 11) % 10;
        cpf /= 10;
        int d1 = 0;
        for (int i = 1; i <= 9; i++) {
            long x = cpf % (long) Math.pow(10, 10 - i);
            long y = x / (long) Math.pow(10, 9 - i);
            Long converteY = y;
            d1 += Integer.parseInt(converteY.toString()) * i;
        }
        d1 = (d1 % 11) % 10;
        return dv == (d1 * 10 + d2);
    }

    public static boolean isCnpjValido(String cnpj) {
        if (!cnpj.substring(0, 1).equals("")) {
            try {
                cnpj = cnpj.replaceAll("\\.", "");
                cnpj = cnpj.replaceAll("/", "");
                cnpj = cnpj.replaceAll("-", "");
                cnpj = cnpj.replaceAll(" ", "");
                int soma = 0, dig;
                String cnpjCalculado = cnpj.substring(0, 12);

                if (cnpj.length() != 14) {
                    return false;
                }
                if(cnpj.equals("00000000000000")){
                    return false;
                }
                char[] chr_cnpj = cnpj.toCharArray();
                /* Primeira parte */
                for (int i = 0; i < 4; i++) {
                    if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                        soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
                    }
                }
                for (int i = 0; i < 8; i++) {
                    if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
                        soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
                    }
                }
                dig = 11 - (soma % 11);
                cnpjCalculado += (dig == 10 || dig == 11) ? "0" : Integer.toString(
                        dig);
                /* Segunda parte */
                soma = 0;
                for (int i = 0; i < 5; i++) {
                    if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
                        soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
                    }
                }
                for (int i = 0; i < 8; i++) {
                    if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
                        soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
                    }
                }
                dig = 11 - (soma % 11);
                cnpjCalculado += (dig == 10 || dig == 11) ? "0" : Integer.toString(
                        dig);
                return cnpj.equals(cnpjCalculado);
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
    
     public static boolean isTelefone(String numeroTelefone) {
        return numeroTelefone.matches("^\\([1-9]{2}\\) [9]{0,1}[6-9]{1}[0-9]{3}\\-[0-9]{4}$");
    }
    
}
