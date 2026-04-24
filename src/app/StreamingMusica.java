package app;


import model.Usuario;
import model.UsuarioFree;
import model.UsuarioPremium;
import model.Musica;
import model.Playlist;
import java.util.ArrayList;
import java.util.Scanner;

public class StreamingMusica {

    static ArrayList<Musica> musicas = new ArrayList<>();
    static Usuario usuario;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        iniciarUsuario();

        int op;

        do {
            System.out.println("\n1. Cadastrar música");
System.out.println("2. Listar músicas");
System.out.println("3. Buscar");

if (usuario instanceof UsuarioPremium) {
    System.out.println("4. Criar playlist (ilimitado)");
} else {
    System.out.println("4. Criar playlist");
}

System.out.println("5. Gerenciar playlists");
System.out.println("6. Estatísticas");

if (usuario instanceof UsuarioPremium) {
    System.out.println("7. Reproduzir música (Alta Qualidade)");
    System.out.println("8. Baixar música");
    System.out.println("9. Ver downloads");
} else {
    System.out.println("7. Reproduzir música");
    System.out.println("8. Upgrade para Premium");
}

System.out.println("0. Sair");


            op = lerInt();

            switch (op) {
                case 1: cadastrar(); break;
                case 2: listar(); break;
                case 3: buscar(); break;
                case 4:
                    System.out.print("Nome da playlist: ");
                    usuario.criarPlaylist(sc.nextLine());
                    break;
                case 5: menuPlaylist(); break;
                case 6: estatisticas(); break;
                case 7: reproduzir(); break;
                case 8:
                    if (usuario instanceof UsuarioPremium up) baixar(up);
                    else upgrade();
                    break;
                case 9:
                    if (usuario instanceof UsuarioPremium up) up.listarDownloads();
                    break;
            }

        } while (op != 0);
    }

    static void iniciarUsuario() {

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.println("1-Free (Gratuito) | 2-Premium (Pago)");
        int tipo = lerInt();

        if (tipo == 1) {
            usuario = new UsuarioFree(nome, email);
            System.out.println("Conta Free criada com sucesso");
        } else {
            System.out.println("Plano: 1-Mensal (R$ 19,90) 2-Anual (R$ 199,00) 3-Familiar (R$ 29,90)");
            int p = lerInt();

            String plano = (p == 1) ? "Mensal (R$ 19,90)" :
                           (p == 2) ? "Anual (R$ 199,00)" :
                                      "Familiar (R$ 29,90)";

            usuario = new UsuarioPremium(nome, email, plano);
            System.out.println("Conta Premium criada com sucesso");
        }
    }

    static int lerInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.print("Número inválido: ");
            }
        }
    }

    static void cadastrar() {
        Musica m = new Musica();

        do {
            System.out.print("Título: ");
            m.setTitulo(sc.nextLine());
        } while (m.getTitulo() == null);

        do {
            System.out.print("Artista: ");
            m.setArtista(sc.nextLine());
        } while (m.getArtista() == null);

        while (true) {
            System.out.print("Duração: ");
            try {
                int d = Integer.parseInt(sc.nextLine());
                if (d > 0 && d < 3600) {
                    m.setDuracaoSegundos(d);
                    break;
                }
            } catch (Exception ignored) {}
            System.out.println("Duração inválida");
        }

        String g;
        do {
            System.out.print("Gênero: ");
            g = sc.nextLine();
        } while (!m.setGenero(g));

        musicas.add(m);
        System.out.println("Música cadastrada com sucesso");
    }

    static void listar() {
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música cadastrada");
            return;
        }

        for (int i = 0; i < musicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            musicas.get(i).exibir();
        }
    }

    static void buscar() {
        System.out.print("Buscar: ");
        String txt = sc.nextLine();

        boolean encontrou = false;

        for (Musica m : musicas) {
            if (m.contemTitulo(txt) || m.contemArtista(txt) || m.contemGenero(txt)) {
                m.exibir();
                encontrou = true;
            }
        }

        if (!encontrou)
            System.out.println("Nenhuma música encontrada");
    }

    static void reproduzir() {
        listar();
        System.out.print("Escolha: ");
        int i = lerInt() - 1;

        if (i >= 0 && i < musicas.size())
            usuario.reproduzirMusica(musicas.get(i));
    }

    static void baixar(UsuarioPremium up) {
        listar();
        System.out.print("Escolha: ");
        int i = lerInt() - 1;

        if (i >= 0 && i < musicas.size())
            up.baixarMusica(musicas.get(i));
    }

    static void upgrade() {
        System.out.println("Plano: 1-Mensal (R$ 19,90) 2-Anual (R$ 199,00) 3-Familiar (R$ 29,90)");
        int p = lerInt();

        String plano = (p == 1) ? "Mensal (R$ 19,90)" :
                       (p == 2) ? "Anual (R$ 199,00)" :
                                  "Familiar (R$ 29,90)";

        usuario = new UsuarioPremium(usuario.getNome(), usuario.getEmail(), plano);

        System.out.println("Upgrade realizado com sucesso");
    }

    static void menuPlaylist() {

        int op;

        do {
            System.out.println("\n1. Listar playlists");
            System.out.println("2. Adicionar música");
            System.out.println("3. Remover música");
            System.out.println("4. Ver playlist");
            System.out.println("0. Voltar");

            op = lerInt();

            switch (op) {
                case 1:
                    usuario.listarPlaylists();
                    break;

                case 2:
                    usuario.listarPlaylists();
                    System.out.print("Playlist: ");
                    int p = lerInt() - 1;

                    Playlist pl = usuario.getPlaylist(p);
                    if (pl == null) break;

                    listar();
                    System.out.print("Música: ");
                    int m = lerInt() - 1;

                    if (m >= 0 && m < musicas.size())
                        pl.adicionarMusica(musicas.get(m));
                    break;

                case 3:
                    usuario.listarPlaylists();
                    System.out.print("Playlist: ");
                    int pr = lerInt() - 1;

                    Playlist plr = usuario.getPlaylist(pr);
                    if (plr == null) break;

                    plr.listarMusicas();
                    System.out.print("Índice: ");
                    plr.removerMusica(lerInt() - 1);
                    break;

                case 4:
                    usuario.listarPlaylists();
                    System.out.print("Playlist: ");
                    int pv = lerInt() - 1;

                    Playlist plv = usuario.getPlaylist(pv);
                    if (plv != null) plv.listarMusicas();
                    break;
            }

        } while (op != 0);
    }

    static void estatisticas() {
        int total = musicas.size();
        int soma = 0;

        for (Musica m : musicas)
            soma += m.getDuracaoSegundos();

        System.out.println("Total: " + total);
        System.out.println("Duração total: " + soma);
        System.out.println("Média: " + (total > 0 ? soma / total : 0));
    }
}
