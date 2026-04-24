package model;
import java.util.ArrayList;

public class UsuarioPremium extends Usuario {


    private ArrayList<Musica> downloads;

    public UsuarioPremium(String nome, String email, String plano) {
        super(nome, email);
        downloads = new ArrayList<>();
    }

    @Override
    public void reproduzirMusica(Musica m) {
        System.out.println("Reproduzindo em alta qualidade: " + m.getTitulo());
        historico.add(m);
    }

    public void baixarMusica(Musica m) {
        if (!downloads.contains(m)) {
            downloads.add(m);
            System.out.println("Música baixada com sucesso");
        } else {
            System.out.println("Música já está baixada");
        }
    }

    public void listarDownloads() {
        if (downloads.isEmpty()) {
            System.out.println("Nenhuma música baixada");
            return;
        }

        for (Musica m : downloads)
            m.exibir();
    }
}
