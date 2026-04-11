import java.util.ArrayList;

class Usuario {

    private String nome;
    private ArrayList<Playlist> playlists;

    public Usuario() {
        this.playlists = new ArrayList<>();
    }

    public Usuario(String nome) {
        this();
        this.setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome.trim();
        } else {
            System.out.println("Nome inválido!");
        }
    }

    public void criarPlaylist(String nome) {
        Playlist p = new Playlist(nome);
        playlists.add(p);
    }

    public Playlist getPlaylist(int indice) {
        if (indice >= 0 && indice < playlists.size()) {
            return playlists.get(indice);
        }
        return null;
    }

    public void listarPlaylists() {
        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + ". " + playlists.get(i).getNome());
        }
    }
}
