package br.com.streaming.modelo;

public class PlaylistPersonalizada extends Playlist {

    private String criador;

    public PlaylistPersonalizada(String nome, String criador) {

        super(nome);

        this.criador = criador;
    }

    public String getCriador() {

        return criador;
    }

    @Override
    public void reproduzir() {

        System.out.println(" Playlist personalizada: " + getNome());

        for (Musica musica : getMusicas()) {

            musica.reproduzir();
        }
    }
}