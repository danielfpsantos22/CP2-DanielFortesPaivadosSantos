import java.util.ArrayList;

import java.util.Scanner;



classe pública StreamingMusica {



  // Lista principal de músicas do sistema

  static ArrayList<Musica> musicas = new ArrayList<>();



  // Usuário do sistema

  static Usuário usuário = new Usuário();



  //Scanner para entrada de dados

  Scanner estático sc = novo Scanner(System.in);



  public static void main(String[] args) {



    int op;



    // Loop principal do sistema

    fazer {

      System.out.println("\n1.Cadastrar 2.Listar 3.Buscar 4.Playlist 0.Sair");

      op = Integer.parseInt(sc.nextLine());



      switch (op) {

        caso 1: cadastrar(); interromper;

        caso 2: listar(); interromper;

        caso 3: buscar(); interromper;

        caso 4: menuPlaylist(); interromper;

      }



    } enquanto (op != 0);

  }



  // Cadastrar nova música

  estático void cadastrar() {

    Música m = new Música(); // cria objeto



    System.out.print("Título: ");

    m.titulo = sc.nextLine();



    System.out.print("Artista: ");

    m.artista = sc.nextLine();



    System.out.print("Duração (segundos): ");

    m.duracao = Integer.parseInt(sc.nextLine());



    System.out.print("Gênero: ");

    m.genero = sc.nextLine();



    músicas.add(m); //adiciona na lista

  }



  // Listar músicas cadastradas

  static void listar() {

    para (int i = 0; i < musicas.size(); i++) {

      System.out.print((i + 1) + ". ");

      musicas.get(i).exibir();

    }

  }



  // Buscar música por texto

  estático void buscar() {

    System.out.print("Buscar: ");

    String txt = sc.nextLine();



    para (Música m : músicas) {

      se (m.buscar(txt)) {

        m.exibir();

      }

    }

  }



  // Menu de playlists

  static void menuPlaylist() {



    int op;



    fazer {

      System.out.println("\n1.Criar 2.Listar 3.Adicionar música 0.Voltar");

      op = Integer.parseInt(sc.nextLine());



      switch (op) {



        caso 1:

          System.out.print("Nome da playlist: ");

          usuario.criarPlaylist(sc.nextLine());

          quebrar;



        caso 2:

          usuario.listarPlaylists();

          quebrar;



        caso 3:

          //lista de reprodução

          usuario.listarPlaylists();

          System.out.print("Escolha uma playlist: ");

          int p = Integer.parseInt(sc.nextLine()) - 1;



          // escolher música

          listar();

          System.out.print("Escolha a música: ");

          int m = Integer.parseInt(sc.nextLine()) - 1;



          //adiciona música na playlist

          usuario.playlists.get(p).adicionar(musicas.get(m));

          quebrar;

      }



    } enquanto (op != 0);

  }

}
