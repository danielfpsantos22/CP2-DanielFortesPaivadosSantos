import java.util.ArrayList;



class Usuario {



  // Lista de playlists do usuÃ¡rio

  ArrayList<Playlist> playlists = new ArrayList<>();



  // Cria uma nova playlist

  void criarPlaylist(String nome) {

    Playlist p = new Playlist(); // cria objeto

    p.nome = nome; // define nome



    playlists.add(p); // adiciona na lista

  }



  // Lista todas as playlists

  void listarPlaylists() {

    for (int i = 0; i < playlists.size(); i++) {

      System.out.println((i + 1) + ". " + playlists.get(i).nome);

    }

  }

}
