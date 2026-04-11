import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario = new Usuario();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int op = -1;

        do {
            System.out.println("\n=== SISTEMA DE STREAMING DE MÚSICA ===");
            System.out.println("1. Cadastrar música");
            System.out.println("2. Listar todas as músicas");
            System.out.println("3. Buscar música");
            System.out.println("4. Criar playlist");
            System.out.println("5. Gerenciar playlists");
            System.out.println("6. Exibir estatísticas");
            System.out.println("7. Editar música");
            System.out.println("0. Sair");

            op = lerInt();

            switch (op) {
                case 1: cadastrar(); break;
                case 2: listar(); break;
                case 3: buscar(); break;
                case 4: criarPlaylist(); break;
                case 5: menuPlaylist(); break;
                case 6: estatisticas(); break;
                case 7: editarMusica(); break;
                case 0: System.out.println("Saindo..."); break;
                default: System.out.println("Opção inválida!");
            }

        } while (op != 0);
    }

    // ================= LEITURA SEGURA =================

    static int lerInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }

    // ================= MÚSICAS =================

    static void cadastrar() {

        Musica m = new Musica();

        System.out.print("Título: ");
        m.setTitulo(sc.nextLine());

        System.out.print("Artista: ");
        m.setArtista(sc.nextLine());

        System.out.print("Duração (segundos): ");
        m.setDuracaoSegundos(lerInt());

        System.out.print("Gênero: ");
        m.setGenero(sc.nextLine());

        musicas.add(m);

        System.out.println("Música cadastrada com sucesso!");
    }

    static void listar() {

        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        for (int i = 0; i < musicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            musicas.get(i).exibir();
        }
    }

    static void buscar() {

        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        System.out.print("Buscar: ");
        String txt = sc.nextLine();

        boolean encontrou = false;

        for (Musica m : musicas) {
            if (m.contemTitulo(txt) || m.contemArtista(txt)) {
                m.exibir();
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma música encontrada.");
        }
    }

    // ================= PLAYLIST =================

    static void criarPlaylist() {
        System.out.print("Nome da playlist: ");
        usuario.criarPlaylist(sc.nextLine());
        System.out.println("Playlist criada!");
    }

    static void menuPlaylist() {

        int op;

        do {
            System.out.println("\n=== GERENCIAR PLAYLIST ===");
            System.out.println("1. Listar playlists");
            System.out.println("2. Adicionar música");
            System.out.println("3. Remover música");
            System.out.println("4. Ver músicas da playlist");
            System.out.println("0. Voltar");

            op = lerInt();

            switch (op) {

                case 1:
                    if (usuario.getPlaylists().isEmpty()) {
                        System.out.println("Nenhuma playlist criada.");
                    } else {
                        usuario.listarPlaylists();
                    }
                    break;

                case 2:
                    if (usuario.getPlaylists().isEmpty() || musicas.isEmpty()) {
                        System.out.println("Crie playlists e músicas primeiro.");
                        break;
                    }

                    usuario.listarPlaylists();
                    System.out.print("Escolha a playlist: ");
                    int p = lerInt() - 1;

                    if (p < 0 || p >= usuario.getPlaylists().size()) {
                        System.out.println("Playlist inválida!");
                        break;
                    }

                    listar();
                    System.out.print("Escolha a música: ");
                    int m = lerInt() - 1;

                    if (m < 0 || m >= musicas.size()) {
                        System.out.println("Música inválida!");
                        break;
                    }

                    usuario.getPlaylist(p).adicionarMusica(musicas.get(m));
                    System.out.println("Música adicionada!");
                    break;

                case 3:
                    if (usuario.getPlaylists().isEmpty()) {
                        System.out.println("Nenhuma playlist criada.");
                        break;
                    }

                    usuario.listarPlaylists();
                    System.out.print("Escolha a playlist: ");
                    int pRem = lerInt() - 1;

                    Playlist plRem = usuario.getPlaylist(pRem);

                    if (plRem == null || plRem.getMusicas().isEmpty()) {
                        System.out.println("Playlist inválida ou vazia.");
                        break;
                    }

                    plRem.listarMusicas();
                    System.out.print("Escolha a música para remover: ");
                    int idx = lerInt() - 1;

                    if (idx < 0 || idx >= plRem.getMusicas().size()) {
                        System.out.println("Índice inválido!");
                        break;
                    }

                    plRem.removerMusica(idx);
                    System.out.println("Música removida!");
                    break;

                case 4:
                    if (usuario.getPlaylists().isEmpty()) {
                        System.out.println("Nenhuma playlist criada.");
                        break;
                    }

                    usuario.listarPlaylists();
                    System.out.print("Escolha a playlist: ");
                    int pVer = lerInt() - 1;

                    Playlist plVer = usuario.getPlaylist(pVer);

                    if (plVer == null) {
                        System.out.println("Playlist inválida!");
                    } else {
                        plVer.listarMusicas();
                    }
                    break;
            }

        } while (op != 0);
    }

    // ================= ESTATÍSTICAS =================

    static void estatisticas() {

        System.out.println("\n=== ESTATÍSTICAS ===");

        System.out.println("Total de músicas: " + musicas.size());
        System.out.println("Total de playlists: " + usuario.getPlaylists().size());

        int totalDuracao = 0;

        for (Musica m : musicas) {
            totalDuracao += m.getDuracaoSegundos();
        }

        System.out.println("Duração total (segundos): " + totalDuracao);

        for (Playlist p : usuario.getPlaylists()) {
            System.out.println(
                "Playlist: " + p.getNome() +
                " | Músicas: " + p.getQuantidadeMusicas() +
                " | Duração: " + p.getDuracaoTotal()
            );
        }
    }

    // ================= EDITAR MÚSICA =================

    static void editarMusica() {

        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada.");
            return;
        }

        listar();
        System.out.print("Escolha a música: ");
        int i = lerInt() - 1;

        if (i < 0 || i >= musicas.size()) {
            System.out.println("Índice inválido!");
            return;
        }

        Musica m = musicas.get(i);

        System.out.print("Novo título: ");
        m.setTitulo(sc.nextLine());

        System.out.print("Novo artista: ");
        m.setArtista(sc.nextLine());

        System.out.print("Nova duração: ");
        m.setDuracaoSegundos(lerInt());

        System.out.print("Novo gênero: ");
        m.setGenero(sc.nextLine());

        System.out.println("Música atualizada!");
    }
}
