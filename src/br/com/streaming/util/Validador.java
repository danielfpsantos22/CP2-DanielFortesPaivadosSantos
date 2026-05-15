package br.com.streaming.util;

public class Validador {

    public static boolean textoValido(String texto) {

        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean duracaoValida(int duracao) {

        return duracao > 0;
    }
}