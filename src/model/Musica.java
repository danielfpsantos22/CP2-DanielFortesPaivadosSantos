package model;
public class Musica {

    private String titulo;
    private String artista;
    private int duracaoSegundos;
    private String genero;

    public Musica() {}

    public Musica(String titulo, String artista, int duracaoSegundos, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracaoSegundos(duracaoSegundos);
        setGenero(genero);
    }

    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public int getDuracaoSegundos() { return duracaoSegundos; }
    public String getGenero() { return genero; }

    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty())
            this.titulo = titulo.trim();
        else
            System.out.println("Título inválido");
    }

    public void setArtista(String artista) {
        if (artista != null && !artista.trim().isEmpty())
            this.artista = artista.trim();
        else
            System.out.println("Artista inválido");
    }

    public void setDuracaoSegundos(int duracaoSegundos) {
        if (duracaoSegundos > 0 && duracaoSegundos < 3600)
            this.duracaoSegundos = duracaoSegundos;
        else
            System.out.println("Duração inválida");
    }

    public boolean setGenero(String genero) {
        if (genero == null) return false;

        String g = genero.trim().toLowerCase();

        switch (g) {
            case "pop": this.genero = "Pop"; return true;
            case "rock": this.genero = "Rock"; return true;
            case "jazz": this.genero = "Jazz"; return true;

            case "eletronica":
            case "eletrônica":
            case "eletronico":
            case "eletrônico":
                this.genero = "Eletrônica"; return true;

            case "rap":
            case "hip hop":
            case "hip-hop":
                this.genero = "Hip-Hop"; return true;

            case "classica":
            case "clássica":
                this.genero = "Clássica"; return true;

            default:
                System.out.println("Gênero inválido");
                return false;
        }
    }

    public void exibir() {
        System.out.println(titulo + " - " + artista + " (" + getDuracaoFormatada() + ") - " + genero);
    }

    public String getDuracaoFormatada() {
        int min = duracaoSegundos / 60;
        int seg = duracaoSegundos % 60;
        return String.format("%02d:%02d", min, seg);
    }

    public boolean contemTitulo(String busca) {
        return titulo != null && titulo.toLowerCase().contains(busca.toLowerCase());
    }

    public boolean contemArtista(String busca) {
        return artista != null && artista.toLowerCase().contains(busca.toLowerCase());
    }

    public boolean contemGenero(String busca) {
        return genero != null && genero.toLowerCase().contains(busca.toLowerCase());
    }
}
