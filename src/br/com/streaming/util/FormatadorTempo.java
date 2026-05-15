package br.com.streaming.util;

public class FormatadorTempo {

    public static String formatar(int segundos) {

        int minutos = segundos / 60;

        int resto = segundos % 60;

        return minutos + ":" + String.format("%02d", resto);
    }
}