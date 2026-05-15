package br.com.streaming.servico;

import java.util.ArrayList;

import br.com.streaming.modelo.Musica;

public class GeradorRecomendacoes {

    public void recomendar(ArrayList<Musica> musicas) {

        System.out.println("\n=== RECOMENDAÇÕES ===");

        for (Musica musica : musicas) {

            if (musica.getReproducoes() >= 5) {

                System.out.println("🎵 " + musica.getTitulo()
                        + " - " + musica.getArtista());
            }
        }
    }
}