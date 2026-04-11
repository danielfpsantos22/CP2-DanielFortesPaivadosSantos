import java.util.ArrayList;

class Playlist {

    private String nome;
    private ArrayList<Musica> musicas;

    public Playlist() {
        this.musicas = new ArrayList<>();
    }

    public Playlist(String nome) {
        this();
        this.setNome(nome);
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Musica> getMusicas() {
        return musicas;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome.trim();
        } else {
            System.out.println("Nome inválido!");
        }
    }

    public void adicionarMusica(Musica musica) {
        if (musica != null) {
            musicas.add(musica);
        } else {
            System.out.println("Música inválida!");
        }
    }

    public void removerMusica(int indice) {
        if (indice >= 0 && indice < musicas.size()) {
            musicas.remove(indice);
        } else {
            System.out.println("Índice inválido!");
        }
    }

    public void listarMusicas() {
        System.out.println("\nPlaylist: " + nome);

        for (int i = 0; i < musicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            musicas.get(i).exibir();
        }
    }

    public int getDuracaoTotal() {
        int total = 0;
        for (Musica m : musicas) {
            total += m.getDuracaoSegundos();
        }
        return total;
    }

    public int getQuantidadeMusicas() {
        return musicas.size();
    }
}
