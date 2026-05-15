package br.com.streaming.modelo;

import java.util.ArrayList;

import br.com.streaming.servico.Reproduzivel;

public class Playlist extends ItemReproducao implements Reproduzivel {

    protected ArrayList<Musica> musicas;

    public Playlist() {

        super("Playlist");

        musicas = new ArrayList<>();
    }

    public Playlist(String nome) {

        super(nome);

        musicas = new ArrayList<>();
    }

    public ArrayList<Musica> getMusicas() {

        return musicas;
    }

    public void adicionarMusica(Musica musica) {

        if (musica != null) {

            musicas.add(musica);
        }
    }

    public void removerMusica(int indice) {

        if (indice >= 0 && indice < musicas.size()) {

            musicas.remove(indice);
        }
    }

    public void listarMusicas() {

        System.out.println("\nPlaylist: " + nome);

        if (musicas.isEmpty()) {

            System.out.println("Nenhuma musica.");
            return;
        }

        for (int i = 0; i < musicas.size(); i++) {

            System.out.print((i + 1) + ". ");

            musicas.get(i).exibir();
        }
    }

    public int getQuantidadeMusicas() {

        return musicas.size();
    }

    @Override
    public void reproduzir() {

        System.out.println(" Reproduzindo playlist: " + nome);

        for (Musica musica : musicas) {

            musica.reproduzir();
        }
    }

    @Override
    public void pausar() {

        System.out.println(" Playlist pausada.");
    }

    @Override
    public void parar() {

        System.out.println(" Playlist parada.");
    }

    @Override
    public int getDuracaoTotal() {

        int total = 0;

        for (Musica musica : musicas) {

            total += musica.getDuracaoSegundos();
        }

        return total;
    }
}