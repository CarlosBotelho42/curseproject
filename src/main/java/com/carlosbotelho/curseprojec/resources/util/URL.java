package com.carlosbotelho.curseprojec.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    //Metodo para descodificar um paramentro(acrescentar algo entro os campos vazios)
    public static String decodeParam(String s){

        try {
            return URLDecoder.decode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            return "";
        }

    }

    public static List<Integer> decodeIntList(String s){


        //Pegar string pegados(Ids) na url e converter para numeros inteiros
        return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
        //Lambida = converter vetor para lista.               para cada elemnto x dessa lista vou converter cada elemento dela para inteiro.
    }

}
