package br.com.streaming.modelo;
import java.util.ArrayList;
 
public class PlaylistAutomatica extends Playlist {
 
    private String criterio; // "top", "recomendadas", "recentes"
 
    public PlaylistAutomatica(String nome, String criterio) {
        super(nome);
        this.criterio = criterio;
    }
 
    public String getCriterio() { return criterio; }
 
    @Override
    public void reproduzir() {
        System.out.println("\n[Automatica] " + nome + " | Criterio: " + criterio);
        if (musicas.isEmpty()) { System.out.println("  Nenhuma musica."); return; }
        for (Musica m : musicas)
            System.out.println("  > " + m.getTitulo() + " - " + m.getArtista()
                    + " (" + m.getReproducoes() + " reprod.)");
    }
 
    public void atualizar(ArrayList<Musica> todasMusicas) {
        musicas.clear();
        if (todasMusicas.isEmpty()) return;
 
        ArrayList<Musica> copia = new ArrayList<>(todasMusicas);
 
        if (criterio.equals("top")) {
            copia.sort((a, b) -> b.getReproducoes() - a.getReproducoes());
            int limite = Math.min(10, copia.size());
            for (int i = 0; i < limite; i++) musicas.add(copia.get(i));
 
        } else if (criterio.equals("recentes")) {
            for (int i = copia.size() - 1; i >= 0 && musicas.size() < 10; i--)
                musicas.add(copia.get(i));
 
        } else { // recomendadas — embaralha
            java.util.Collections.shuffle(copia);
            int limite = Math.min(10, copia.size());
            for (int i = 0; i < limite; i++) musicas.add(copia.get(i));
        }
    }
}
    