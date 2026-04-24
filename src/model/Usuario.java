package model;
import java.util.ArrayList;

public class Usuario {

    protected String nome;
    protected String email;
    protected ArrayList<Playlist> playlists;
    protected ArrayList<Musica> historico;

    public Usuario(String nome, String email) {
        playlists = new ArrayList<>();
        historico = new ArrayList<>();

        setNome(nome);

        if (!setEmail(email)) {
            System.out.println("Email inválido ao criar usuário.");
        }
    }

    public String getNome() { return nome; }
    public String getEmail() { return email; }

    public boolean setEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            System.out.println("Email inválido");
            return false;
        }

        String e = email.toLowerCase().trim();

        if (!e.contains("@")) {
            System.out.println("Email deve conter @");
            return false;
        }

        if (e.endsWith("@gmail.com") ||
            e.endsWith("@hotmail.com") ||
            e.endsWith("@outlook.com") ||
            e.endsWith("@yahoo.com.br")) {

            this.email = e;
            return true;
        }

        System.out.println("Domínio de email inválido");
        return false;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty())
            this.nome = nome.trim();
        else
            System.out.println("Nome inválido");
    }

    public void reproduzirMusica(Musica m) {
        if (m != null) {
            System.out.println("Reproduzindo: " + m.getTitulo());
            historico.add(m);
        }
    }

    public void criarPlaylist(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome inválido");
            return;
        }

        for (Playlist p : playlists) {
            if (p.getNome().equalsIgnoreCase(nome.trim())) {
                System.out.println("Já existe uma playlist com esse nome");
                return;
            }
        }

        playlists.add(new Playlist(nome.trim()));
        System.out.println("Playlist criada com sucesso");
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public Playlist getPlaylist(int i) {
        if (i >= 0 && i < playlists.size())
            return playlists.get(i);
        return null;
    }

    public void listarPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist criada");
            return;
        }

        for (int i = 0; i < playlists.size(); i++)
            System.out.println((i + 1) + ". " + playlists.get(i).getNome());
    }
}
