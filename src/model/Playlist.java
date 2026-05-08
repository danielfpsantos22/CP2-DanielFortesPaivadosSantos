package model;
import java.util.ArrayList;
 
public class Playlist {
 
    protected String nome;
    protected ArrayList<Musica> musicas;
 
    public Playlist() {
        musicas = new ArrayList<>();
    }
 
    public Playlist(String nome) {
        this();
        setNome(nome);
    }
 
    public String getNome()               { return nome; }
    public ArrayList<Musica> getMusicas() { return musicas; }
 
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty())
            this.nome = nome.trim();
        else
            System.out.println("Nome invalido.");
    }
 
    public void adicionarMusica(Musica musica) {
        if (musica != null)
            musicas.add(musica);
        else
            System.out.println("Musica invalida.");
    }
 
    public void removerMusica(int indice) {
        if (indice >= 0 && indice < musicas.size())
            musicas.remove(indice);
        else
            System.out.println("Indice invalido.");
    }
 
    public void reproduzir() {
        System.out.println("\n[Playlist] " + nome);
        if (musicas.isEmpty()) { System.out.println("  Nenhuma musica."); return; }
        for (Musica m : musicas)
            System.out.println("  > " + m.getTitulo() + " - " + m.getArtista());
    }
 
    public void listarMusicas() {
        System.out.println("\nPlaylist: " + nome + " (" + musicas.size() + " musica(s))");
        if (musicas.isEmpty()) { System.out.println("  Nenhuma musica."); return; }
        for (int i = 0; i < musicas.size(); i++) {
            System.out.print("  " + (i + 1) + ". ");
            musicas.get(i).exibir();
        }
    }
 
    public int getDuracaoTotal() {
        int total = 0;
        for (Musica m : musicas) total += m.getDuracaoSegundos();
        return total;
    }
 
    public int getQuantidadeMusicas() { return musicas.size(); }
}