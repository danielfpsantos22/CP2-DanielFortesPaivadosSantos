package model;
import java.util.ArrayList;
 
public class UsuarioPremium extends Usuario {
 
    private String plano;
    private ArrayList<Musica> musicasBaixadas;
 
    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        this.plano = plano;
        this.musicasBaixadas = new ArrayList<>();
    }
 
    public String getPlano()                        { return plano; }
    public ArrayList<Musica> getMusicasBaixadas()   { return musicasBaixadas; }
 
    @Override
    public void reproduzirMusica(Musica m) {
        if (m != null) {
            System.out.println("Reproduzindo em ALTA QUALIDADE: " + m.getTitulo() + " - " + m.getArtista());
            m.incrementarReproducoes();
            historico.add(m);
        }
    }
 
    public void baixarMusica(Musica m) {
        if (m == null) return;
        if (!musicasBaixadas.contains(m)) {
            musicasBaixadas.add(m);
            System.out.println("Download concluido: " + m.getTitulo());
        } else {
            System.out.println("\"" + m.getTitulo() + "\" ja esta baixada.");
        }
    }
 
    public void listarDownloads() {
        System.out.println("\n=== MUSICAS BAIXADAS ===");
        if (musicasBaixadas.isEmpty()) { System.out.println("Nenhuma musica baixada."); return; }
        for (int i = 0; i < musicasBaixadas.size(); i++) {
            System.out.print((i + 1) + ". ");
            musicasBaixadas.get(i).exibir();
        }
    }
 
    @Override
    public String getTipo() { return "Premium (" + plano + ")"; }
}