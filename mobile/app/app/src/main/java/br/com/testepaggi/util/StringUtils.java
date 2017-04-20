package br.com.testepaggi.util;

/**
 * © Copyright 2016 Skatavel.
 * Autor : Paulo Sales - dev@paulovns.com.br
 * Empresa : Skatavel app.
 */

public class StringUtils {

    public static String setValue(String label, String value){

        return label.replace("{var}", value);
    }

}
