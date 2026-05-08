package model;
 
public class PlaylistPersonalizada extends Playlist {
 
    private String criador;
 
    public PlaylistPersonalizada(String nome, String criador) {
        super(nome);
        this.criador = criador;
    }
 
    public String getCriador() { return criador; }
 
    @Override
    public void reproduzir() {
        System.out.println("\n[Personalizada] " + nome + " (por " + criador + ")");
        if (musicas.isEmpty()) { System.out.println("  Nenhuma musica."); return; }
        for (Musica m : musicas)
            System.out.println("  > " + m.getTitulo() + " - " + m.getArtista());
    }
}
 