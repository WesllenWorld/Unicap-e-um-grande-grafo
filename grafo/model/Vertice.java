package model;

public class Vertice {
    private int id;
    private String nome;
    private String cor;
    private int extensaoDistancia;
    private Vertice antecessor;



    public Vertice(int id, String nome) {
        this.id = id;
        this.nome = nome;
        this.cor = "Branco";
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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getExtensaoDistancia() {
        return extensaoDistancia;
    }

    public void setExtensaoDistancia(int extensaoDistancia) {
        this.extensaoDistancia = extensaoDistancia;
    }

    public Vertice getAntecessor() {
        return antecessor;
    }

    public void setAntecessor(Vertice antecessor) {
        this.antecessor = antecessor;
    }
}
