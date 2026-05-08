package model;
 
public class UsuarioFree extends Usuario {
 
    private static final int MAX_PLAYLISTS = 3;
    private int contadorReproducoes;
    private int anunciosExibidos;
 
    public UsuarioFree(String nome, String email) {
        super(nome, email);
        this.contadorReproducoes = 0;
        this.anunciosExibidos = 0;
    }
 
    public int getContadorReproducoes() { return contadorReproducoes; }
    public int getAnunciosExibidos()    { return anunciosExibidos; }
 
    @Override
    public void reproduzirMusica(Musica m) {
        contadorReproducoes++;
        if (contadorReproducoes % 3 == 0) exibirAnuncio();
        super.reproduzirMusica(m);
    }
 
    @Override
    public void criarPlaylist(String nome) {
        if (playlists.size() >= MAX_PLAYLISTS) {
            System.out.println("Limite de " + MAX_PLAYLISTS + " playlists atingido!");
            System.out.println("Faca upgrade para Premium e tenha playlists ilimitadas.");
            return;
        }
        super.criarPlaylist(nome);
    }
 
    private void exibirAnuncio() {
        anunciosExibidos++;
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ANUNCIO: Assine Premium e ouca sem interrupcoes!");
        System.out.println("=".repeat(50) + "\n");
    }
 
    @Override
    public String getTipo() { return "Free"; }
}