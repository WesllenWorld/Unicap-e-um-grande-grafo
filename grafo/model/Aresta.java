package model;

public class Aresta {
    private double peso;
    private int id, idVerticeOrigem, idVerticeDestino;

    public Aresta(int id, int idVerticeOrigem, int idVerticeDestino, double peso) {
        this.peso = peso;
        this.id = id;
        this.idVerticeOrigem = idVerticeOrigem;
        this.idVerticeDestino = idVerticeDestino;
    }
}
