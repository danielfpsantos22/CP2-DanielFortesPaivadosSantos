package model;
import java.util.ArrayList;
 
public class Usuario {
 
    protected String nome;
    protected String email;
    protected ArrayList<Playlist> playlists;
    protected ArrayList<Musica> historico;
 
    public Usuario(String nome, String email) {
        this.playlists = new ArrayList<>();
        this.historico = new ArrayList<>();
        setNome(nome);
        this.email = email.toLowerCase().trim();
    }
 
    public String getNome()  { return nome; }
    public String getEmail() { return email; }
    public ArrayList<Playlist> getPlaylists() { return playlists; }
 
    public static boolean emailValido(String email) {
        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email nao pode ser vazio.");
            return false;
        }
        String e = email.toLowerCase().trim();
        if (!e.contains("@")) {
            System.out.println("Email deve conter @");
            return false;
        }
        if (e.endsWith("@gmail.com") || e.endsWith("@hotmail.com")
                || e.endsWith("@outlook.com") || e.endsWith("@yahoo.com.br")) {
            return true;
        }
        System.out.println("Dominio invalido. Use: @gmail.com, @hotmail.com, @outlook.com ou @yahoo.com.br");
        return false;
    }
 
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty())
            this.nome = nome.trim();
        else
            System.out.println("Nome invalido.");
    }
 
    public void reproduzirMusica(Musica m) {
        if (m != null) {
            System.out.println("Reproduzindo: " + m.getTitulo() + " - " + m.getArtista());
            m.incrementarReproducoes();
            historico.add(m);
        }
    }
 
    public void exibirHistorico() {
        System.out.println("\n=== HISTORICO DE REPRODUCAO ===");
        if (historico.isEmpty()) { System.out.println("Nenhuma musica reproduzida."); return; }
        for (int i = 0; i < historico.size(); i++) {
            System.out.print((i + 1) + ". ");
            historico.get(i).exibir();
        }
    }
 
    public void criarPlaylist(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome invalido.");
            return;
        }
        for (Playlist p : playlists) {
            if (p.getNome().equalsIgnoreCase(nome.trim())) {
                System.out.println("Ja existe uma playlist com esse nome.");
                return;
            }
        }
        playlists.add(new PlaylistPersonalizada(nome.trim(), this.nome));
        System.out.println("Playlist \"" + nome.trim() + "\" criada com sucesso!");
    }
 
    public Playlist getPlaylist(int i) {
        if (i >= 0 && i < playlists.size()) return playlists.get(i);
        return null;
    }
 
    public void listarPlaylists() {
        if (playlists.isEmpty()) { System.out.println("Nenhuma playlist criada."); return; }
        for (int i = 0; i < playlists.size(); i++) {
            Playlist p = playlists.get(i);
            String tipo = (p instanceof PlaylistAutomatica) ? "[Auto] " : "[Pessoal] ";
            System.out.println((i + 1) + ". " + tipo + p.getNome()
                    + " (" + p.getQuantidadeMusicas() + " musica(s))");
        }
    }
 
    public String getTipo() { return "Usuario"; }
}