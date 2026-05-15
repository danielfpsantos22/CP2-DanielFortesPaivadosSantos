package br.com.streaming.principal;
 
import java.util.ArrayList;
import java.util.Scanner;

import br.com.streaming.modelo.*;   
 
public class StreamingMusica {
 
    // --- estado global ---
    static ArrayList<Musica>  musicas  = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static Usuario usuarioLogado = null;
    static Scanner sc = new Scanner(System.in);
 
    // =========================================================
    //  PONTO DE ENTRADA
    // =========================================================
    public static void main(String[] args) {
        int op;
        do {
            menuInicial();
            op = lerInt();
            switch (op) {
                case 1: criarUsuario();  break;
                case 2: fazerLogin();    break;
                case 3: listarUsuarios();break;
                case 0: System.out.println("Ate logo!"); break;
                default: System.out.println("Opcao invalida.");
            }
        } while (op != 0);
    }
 
    static void menuInicial() {
        System.out.println("\n=== SISTEMA DE STREAMING ===");
        System.out.println("1. Criar novo usuario");
        System.out.println("2. Login");
        System.out.println("3. Listar usuarios");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
    }
 
    // =========================================================
    //  GESTAO DE USUARIOS
    // =========================================================
    static void criarUsuario() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
 
        String email;
        while (true) {
            System.out.print("Email (ex: nome@gmail.com): ");
            email = sc.nextLine();
            if (Usuario.emailValido(email)) break;
        }
 
        System.out.println("Tipo de conta: 1-Free (Gratuito) | 2-Premium (Pago)");
        int tipo = lerInt();
 
        if (tipo == 1) {
            usuarios.add(new UsuarioFree(nome, email));
            System.out.println("Conta Free criada com sucesso!");
        } else {
            System.out.println("Plano: 1-Mensal (R$ 19,90) | 2-Anual (R$ 199,00) | 3-Familiar (R$ 29,90)");
            int p = lerInt();
            String plano = (p == 2) ? "Anual (R$ 199,00)" : (p == 3) ? "Familiar (R$ 29,90)" : "Mensal (R$ 19,90)";
            usuarios.add(new UsuarioPremium(nome, email, plano));
            System.out.println("Conta Premium (" + plano + ") criada com sucesso!");
        }
    }
 
    static void fazerLogin() {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado.");
            return;
        }
        listarUsuarios();
        System.out.print("Escolha o usuario: ");
        int idx = lerInt() - 1;
        if (idx < 0 || idx >= usuarios.size()) {
            System.out.println("Opcao invalida.");
            return;
        }
        usuarioLogado = usuarios.get(idx);
        System.out.println("Login realizado: " + usuarioLogado.getNome()
                + " (" + usuarioLogado.getTipo() + ")");
        menuPrincipal();
    }
 
    static void listarUsuarios() {
        if (usuarios.isEmpty()) { System.out.println("Nenhum usuario cadastrado."); return; }
        System.out.println("\n=== USUARIOS ===");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            System.out.println((i + 1) + ". " + u.getNome() + " (" + u.getTipo() + ")");
        }
    }
 
    // =========================================================
    //  MENU PRINCIPAL (pos-login)
    // =========================================================
    static void menuPrincipal() {
        int op;
        do {
            System.out.println("\n=== MENU PRINCIPAL | " + usuarioLogado.getNome()
                    + " [" + usuarioLogado.getTipo() + "] ===");
            System.out.println("1. Cadastrar musica");
            System.out.println("2. Listar musicas");
            System.out.println("3. Buscar musica");
            System.out.println("4. Estatisticas");
            System.out.println("5. Reproduzir musica");
            System.out.println("6. Ver historico");
 
            if (usuarioLogado instanceof UsuarioPremium) {
                System.out.println("7. Criar playlist (ilimitado)");
                System.out.println("8. Gerenciar playlists");
                System.out.println("9. Playlists automaticas");
                System.out.println("10. Baixar musica");
                System.out.println("11. Ver downloads");
            } else {
                System.out.println("7. Criar playlist (max 3)");
                System.out.println("8. Gerenciar playlists");
                System.out.println("9. Playlists automaticas");
                System.out.println("10. Upgrade para Premium");
            }
 
            System.out.println("0. Logout");
            System.out.print("Escolha: ");
            op = lerInt();
 
            switch (op) {
                case 1:  cadastrar();                              break;
                case 2:  listar();                                 break;
                case 3:  buscar();                                 break;
                case 4:  estatisticas();                           break;
                case 5:  reproduzir();                             break;
                case 6:  usuarioLogado.exibirHistorico();          break;
                case 7:
                    System.out.print("Nome da playlist: ");
                    usuarioLogado.criarPlaylist(sc.nextLine());
                    break;
                case 8:  menuPlaylist();                           break;
                case 9:  menuPlaylistsAutomaticas();               break;
                case 10:
                    if (usuarioLogado instanceof UsuarioPremium up) baixar(up);
                    else upgrade();
                    break;
                case 11:
                    if (usuarioLogado instanceof UsuarioPremium up) up.listarDownloads();
                    break;
                case 0:  System.out.println("Logout realizado."); break;
                default: System.out.println("Opcao invalida.");
            }
        } while (op != 0);
        usuarioLogado = null;
    }
 
    // =========================================================
    //  MUSICAS — CRUD + BUSCA
    // =========================================================
    static void cadastrar() {
        Musica m = new Musica();
 
        do { System.out.print("Titulo: "); m.setTitulo(sc.nextLine()); }
        while (m.getTitulo() == null);
 
        do { System.out.print("Artista: "); m.setArtista(sc.nextLine()); }
        while (m.getArtista() == null);
 
        while (true) {
            System.out.print("Duracao (segundos): ");
            try {
                int d = Integer.parseInt(sc.nextLine());
                m.setDuracaoSegundos(d);
                if (m.getDuracaoSegundos() > 0) break;
            } catch (NumberFormatException ignored) {}
            System.out.println("Informe um numero valido.");
        }
 
        do {
            System.out.print("Genero (Pop, Rock, Jazz, Eletronica, Hip-Hop, Classica): ");
        } while (!m.setGenero(sc.nextLine()));
 
        musicas.add(m);
        System.out.println("Musica cadastrada com sucesso!");
    }
 
    static void listar() {
        if (musicas.isEmpty()) { System.out.println("Nenhuma musica cadastrada."); return; }
        System.out.println("\n=== MUSICAS CADASTRADAS ===");
        for (int i = 0; i < musicas.size(); i++) {
            System.out.print((i + 1) + ". ");
            musicas.get(i).exibir();
        }
        System.out.println("Total: " + musicas.size() + " musica(s)");
    }
 
    static void buscar() {
        System.out.print("Buscar (titulo, artista ou genero): ");
        String txt = sc.nextLine().trim();
        if (txt.isEmpty()) { System.out.println("Digite algo para buscar."); return; }
 
        boolean achou = false;
        for (Musica m : musicas) {
            if (m.contemTitulo(txt) || m.contemArtista(txt) || m.contemGenero(txt)) {
                m.exibir();
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhuma musica encontrada para \"" + txt + "\".");
    }
 
    // =========================================================
    //  REPRODUCAO
    // =========================================================
    static void reproduzir() {
        listar();
        if (musicas.isEmpty()) return;
        System.out.print("Escolha: ");
        int i = lerInt() - 1;
        if (i >= 0 && i < musicas.size())
            usuarioLogado.reproduzirMusica(musicas.get(i));
        else
            System.out.println("Opcao invalida.");
    }
 
    static void baixar(UsuarioPremium up) {
        listar();
        if (musicas.isEmpty()) return;
        System.out.print("Escolha: ");
        int i = lerInt() - 1;
        if (i >= 0 && i < musicas.size()) up.baixarMusica(musicas.get(i));
        else System.out.println("Opcao invalida.");
    }
 
    static void upgrade() {
        System.out.println("Plano: 1-Mensal (R$ 19,90) | 2-Anual (R$ 199,00) | 3-Familiar (R$ 29,90)");
        int p = lerInt();
        String plano = (p == 2) ? "Anual (R$ 199,00)" : (p == 3) ? "Familiar (R$ 29,90)" : "Mensal (R$ 19,90)";
        UsuarioPremium novo = new UsuarioPremium(usuarioLogado.getNome(), usuarioLogado.getEmail(), plano);
        // Migra playlists existentes
        for (Playlist pl : usuarioLogado.getPlaylists()) novo.getPlaylists().add(pl);
        usuarios.set(usuarios.indexOf(usuarioLogado), novo);
        usuarioLogado = novo;
        System.out.println("Upgrade realizado! Bem-vindo ao Premium (" + plano + ")!");
    }
 
    // =========================================================
    //  PLAYLISTS PESSOAIS
    // =========================================================
    static void menuPlaylist() {
        int op;
        do {
            System.out.println("\n=== GERENCIAR PLAYLISTS ===");
            System.out.println("1. Listar playlists");
            System.out.println("2. Adicionar musica a playlist");
            System.out.println("3. Remover musica de playlist");
            System.out.println("4. Ver detalhes de playlist");
            System.out.println("5. Reproduzir playlist");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            op = lerInt();
 
            switch (op) {
                case 1: usuarioLogado.listarPlaylists(); break;
                case 2: adicionarMusicaPlaylist();        break;
                case 3: removerMusicaPlaylist();          break;
                case 4: verPlaylist();                    break;
                case 5: reproduzirPlaylist();             break;
            }
        } while (op != 0);
    }
 
    static Playlist selecionarPlaylist() {
        usuarioLogado.listarPlaylists();
        if (usuarioLogado.getPlaylists().isEmpty()) return null;
        System.out.print("Playlist (numero): ");
        return usuarioLogado.getPlaylist(lerInt() - 1);
    }
 
    static void adicionarMusicaPlaylist() {
        Playlist pl = selecionarPlaylist();
        if (pl == null) { System.out.println("Playlist nao encontrada."); return; }
        listar();
        if (musicas.isEmpty()) return;
        System.out.print("Musica (numero): ");
        int m = lerInt() - 1;
        if (m >= 0 && m < musicas.size()) pl.adicionarMusica(musicas.get(m));
        else System.out.println("Opcao invalida.");
    }
 
    static void removerMusicaPlaylist() {
        Playlist pl = selecionarPlaylist();
        if (pl == null) { System.out.println("Playlist nao encontrada."); return; }
        pl.listarMusicas();
        if (pl.getQuantidadeMusicas() == 0) return;
        System.out.print("Musica (numero): ");
        pl.removerMusica(lerInt() - 1);
    }
 
    static void verPlaylist() {
        Playlist pl = selecionarPlaylist();
        if (pl != null) pl.listarMusicas();
        else System.out.println("Playlist nao encontrada.");
    }
 
    static void reproduzirPlaylist() {
        Playlist pl = selecionarPlaylist();
        if (pl != null) pl.reproduzir();
        else System.out.println("Playlist nao encontrada.");
    }
 
    // =========================================================
    //  PLAYLISTS AUTOMATICAS
    // =========================================================
    static void menuPlaylistsAutomaticas() {
        System.out.println("\n=== PLAYLISTS AUTOMATICAS ===");
        System.out.println("1. Top 10 Mais Tocadas");
        System.out.println("2. Recomendadas para Voce");
        System.out.println("3. Adicionadas Recentemente");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        int op = lerInt();
 
        String criterio;
        String nomePlaylist;
        switch (op) {
            case 1: criterio = "top";          nomePlaylist = "Top 10 Mais Tocadas";      break;
            case 2: criterio = "recomendadas"; nomePlaylist = "Recomendadas para Voce";   break;
            case 3: criterio = "recentes";     nomePlaylist = "Adicionadas Recentemente"; break;
            default: return;
        }
 
        if (musicas.isEmpty()) {
            System.out.println("Nenhuma musica cadastrada para gerar playlist.");
            return;
        }
 
        PlaylistAutomatica pa = new PlaylistAutomatica(nomePlaylist, criterio);
        pa.atualizar(musicas);
 
        // Adiciona ou substitui no perfil do usuario
        ArrayList<Playlist> pls = usuarioLogado.getPlaylists();
        pls.removeIf(p -> p instanceof PlaylistAutomatica
                && ((PlaylistAutomatica) p).getCriterio().equals(criterio));
        pls.add(pa);
 
        System.out.println("Playlist \"" + nomePlaylist + "\" gerada com "
                + pa.getQuantidadeMusicas() + " musica(s)!");
        pa.reproduzir();
    }
 
    // =========================================================
    //  ESTATISTICAS
    // =========================================================
    static void estatisticas() {
        System.out.println("\n=== ESTATISTICAS DO SISTEMA ===");
 
        // --- musicas ---
        int total = musicas.size();
        int soma = 0;
        for (Musica m : musicas) soma += m.getDuracaoSegundos();
 
        System.out.println("Total de musicas: " + total);
        System.out.println("Duracao total:    " + formatarTempo(soma));
        System.out.println("Duracao media:    " + formatarTempo(total > 0 ? soma / total : 0));
 
        // --- genero mais cadastrado ---
        if (!musicas.isEmpty()) {
            String[] generos = {"Pop", "Rock", "Jazz", "Eletronica", "Hip-Hop", "Classica"};
            String topGenero = "";
            int topCount = 0;
            for (String g : generos) {
                int cnt = 0;
                for (Musica m : musicas)
                    if (m.contemGenero(g)) cnt++;
                if (cnt > topCount) { topCount = cnt; topGenero = g; }
            }
            System.out.println("Genero mais cadastrado: " + topGenero + " (" + topCount + " musica(s))");
        }
 
        // --- usuarios ---
        System.out.println();
        int nFree = 0, nPremium = 0;
        int repFree = 0, repPremium = 0;
        int anuncios = 0;
 
        for (Usuario u : usuarios) {
            if (u instanceof UsuarioFree uf) {
                nFree++;
                repFree += uf.getContadorReproducoes();
                anuncios += uf.getAnunciosExibidos();
            } else if (u instanceof UsuarioPremium) {
                nPremium++;
                repPremium += u.getPlaylists().size(); // placeholder — historico nao exposto
            }
        }
        int totalRep = repFree + repPremium;
 
        System.out.println("Total de usuarios: " + usuarios.size());
        System.out.println("  Free:    " + nFree + " usuario(s)");
        System.out.println("  Premium: " + nPremium + " usuario(s)");
        System.out.println();
        System.out.println("Reproducoes Free:    " + repFree
                + (totalRep > 0 ? " (" + (repFree * 100 / totalRep) + "%)" : ""));
        System.out.println("Reproducoes Premium: " + repPremium
                + (totalRep > 0 ? " (" + (repPremium * 100 / totalRep) + "%)" : ""));
        System.out.println("Anuncios exibidos:   " + anuncios);
    }
 
    // =========================================================
    //  UTILITARIOS
    // =========================================================
    static int lerInt() {
        while (true) {
            try { return Integer.parseInt(sc.nextLine()); }
            catch (NumberFormatException e) { System.out.print("Numero invalido. Tente novamente: "); }
        }
    }
 
    static String formatarTempo(int segundos) {
        return String.format("%02d:%02d", segundos / 60, segundos % 60);
    }
}