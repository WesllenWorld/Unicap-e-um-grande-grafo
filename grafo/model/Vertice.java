package model;

import java.util.LinkedList;

public class Vertice {
    private int id;
    private String nome;
    private String cor;


    public Vertice(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.cor = "branco";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
