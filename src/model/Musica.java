package model;
 
public class Musica {
 
    private String titulo;
    private String artista;
    private int duracaoSegundos;
    private String genero;
    private int reproducoes;
 
    public Musica() {}
 
    public Musica(String titulo, String artista, int duracaoSegundos, String genero) {
        setTitulo(titulo);
        setArtista(artista);
        setDuracaoSegundos(duracaoSegundos);
        setGenero(genero);
    }
 
    public String getTitulo()         { return titulo; }
    public String getArtista()        { return artista; }
    public int getDuracaoSegundos()   { return duracaoSegundos; }
    public String getGenero()         { return genero; }
    public int getReproducoes()       { return reproducoes; }
 
    public void incrementarReproducoes() { reproducoes++; }
 
    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty())
            this.titulo = titulo.trim();
        else
            System.out.println("Titulo invalido.");
    }
 
    public void setArtista(String artista) {
        if (artista != null && !artista.trim().isEmpty())
            this.artista = artista.trim();
        else
            System.out.println("Artista invalido.");
    }
 
    public void setDuracaoSegundos(int duracaoSegundos) {
        if (duracaoSegundos > 0 && duracaoSegundos < 3600)
            this.duracaoSegundos = duracaoSegundos;
        else
            System.out.println("Duracao invalida (1 a 3599 segundos).");
    }
 
    public boolean setGenero(String genero) {
        if (genero == null) return false;
        switch (genero.trim().toLowerCase()) {
            case "pop":
                this.genero = "Pop"; return true;
            case "rock":
                this.genero = "Rock"; return true;
            case "jazz":
                this.genero = "Jazz"; return true;
            case "eletronica": case "eletronico":
                this.genero = "Eletronica"; return true;
            case "rap": case "hip hop": case "hip-hop":
                this.genero = "Hip-Hop"; return true;
            case "classica":
                this.genero = "Classica"; return true;
            default:
                System.out.println("Genero invalido. Use: Pop, Rock, Jazz, Eletronica, Hip-Hop, Classica");
                return false;
        }
    }
 
    public void exibir() {
        System.out.printf("Titulo: %-25s | Artista: %-20s | Duracao: %s | Genero: %-10s | Reproducoes: %d%n",
                titulo, artista, getDuracaoFormatada(), genero, reproducoes);
    }
 
    public String getDuracaoFormatada() {
        return String.format("%02d:%02d", duracaoSegundos / 60, duracaoSegundos % 60);
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