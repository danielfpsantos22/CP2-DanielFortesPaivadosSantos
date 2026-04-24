package model;
public class UsuarioFree extends Usuario {

    private static final int MAX = 3;
    private int contador;

    public UsuarioFree(String nome, String email) {
        super(nome, email);
    }

    @Override
    public void reproduzirMusica(Musica m) {
        contador++;

        if (contador % 3 == 0)
            System.out.println("Anúncio: Assine Premium");

        super.reproduzirMusica(m);
    }

    @Override
    public void criarPlaylist(String nome) {
        if (playlists.size() >= MAX) {
            System.out.println("Limite de playlists atingido");
            return;
        }
        super.criarPlaylist(nome);
    }
}
