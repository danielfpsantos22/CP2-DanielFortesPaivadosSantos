class Musica {

    private String titulo;
    private String artista;
    private int duracaoSegundos;
    private String genero;

    // Construtor padrão
    public Musica() {}

    // Construtor parametrizado
    public Musica(String titulo, String artista, int duracaoSegundos, String genero) {
        this.setTitulo(titulo);
        this.setArtista(artista);
        this.setDuracaoSegundos(duracaoSegundos);
        this.setGenero(genero);
    }

    // Getters
    public String getTitulo() { return titulo; }
    public String getArtista() { return artista; }
    public int getDuracaoSegundos() { return duracaoSegundos; }
    public String getGenero() { return genero; }

    // Setters com validação
    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.trim().isEmpty()) {
            this.titulo = titulo.trim();
        } else {
            System.out.println("Título inválido!");
        }
    }

    public void setArtista(String artista) {
        if (artista != null && !artista.trim().isEmpty()) {
            this.artista = artista.trim();
        } else {
            System.out.println("Artista inválido!");
        }
    }

    public void setDuracaoSegundos(int duracaoSegundos) {
        if (duracaoSegundos > 0 && duracaoSegundos < 3600) {
            this.duracaoSegundos = duracaoSegundos;
        } else {
            System.out.println("Duração inválida!");
        }
    }

    public void setGenero(String genero) {
        if (genero == null) {
            System.out.println("Gênero inválido!");
            return;
        }

        String g = genero.trim().toLowerCase();

        if (g.equals("pop") || g.equals("rock") || g.equals("jazz") ||
            g.equals("eletrônica") || g.equals("hip-hop") || g.equals("clássica")) {

            this.genero = g.substring(0,1).toUpperCase() + g.substring(1);
        } else {
            System.out.println("Gênero inválido!");
        }
    }

    // Métodos
    public void exibir() {
        System.out.println(
            titulo + " - " + artista +
            " | " + getDuracaoFormatada() +
            " | " + genero
        );
    }

    public String getDuracaoFormatada() {
        int min = duracaoSegundos / 60;
        int seg = duracaoSegundos % 60;
        return String.format("%02d:%02d", min, seg);
    }

    public boolean contemTitulo(String busca) {
        return titulo.toLowerCase().contains(busca.toLowerCase());
    }

    public boolean contemArtista(String busca) {
        return artista.toLowerCase().contains(busca.toLowerCase());
    }
}
    