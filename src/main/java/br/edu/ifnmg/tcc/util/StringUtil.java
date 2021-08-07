package br.edu.ifnmg.tcc.util;

public class StringUtil {

    public static boolean isEmpty(String texto, boolean trim) {
        if (texto != null) {
            if ("".equals(texto)) {
                return true;
            } else if (trim && "".equals(texto.trim())) {
                return true;
            }
            return false;

        }
        return true;
    }

    public static boolean isEmpty(String texto) {
        return isEmpty(texto, true);
    }

    public static boolean isNotEmpty(String texto) {
        return !isEmpty(texto, true);
    }
}
