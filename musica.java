import java.util.ArrayList;



class Playlist {



  String nome;



  // Lista de musicas da playlist

  ArrayList<Musica> musicas = new ArrayList<>();



  // Adiciona uma musica na playlist

  void adicionar(Musica m) {

    musicas.add(m);

  }



  // Remove musica pelo i­ndice

  void remover(int i) {

    if (i >= 0 && i < musicas.size()) {

      musicas.remove(i);

    }

  }



  // Lista todas as musicas da playlist

  void listar() {

    for (int i = 0; i < musicas.size(); i++) {

      System.out.print((i + 1) + ". ");

      musicas.get(i).exibir();

    }

  }

}
