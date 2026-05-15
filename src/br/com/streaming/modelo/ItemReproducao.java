package br.com.streaming.modelo;

public abstract class ItemReproducao {

    protected String nome;
    protected int duracao;

    public ItemReproducao() {

    }

    public ItemReproducao(String nome) {

        this.nome = nome;
    }

    public ItemReproducao(String nome, int duracao) {

        this.nome = nome;
        this.duracao = duracao;
    }

    public String getNome() {

        return nome;
    }

    public int getDuracao() {

        return duracao;
    }

    public abstract void reproduzir();
}