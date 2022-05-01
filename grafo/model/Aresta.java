package model;

public class Aresta {
    private double peso;
    private int id, idVerticeOrigem, idVerticeDestino;

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVerticeOrigem() {
        return idVerticeOrigem;
    }

    public void setIdVerticeOrigem(int idVerticeOrigem) {
        this.idVerticeOrigem = idVerticeOrigem;
    }

    public int getIdVerticeDestino() {
        return idVerticeDestino;
    }

    public void setIdVerticeDestino(int idVerticeDestino) {
        this.idVerticeDestino = idVerticeDestino;
    }

    public Aresta(int id, int idVerticeOrigem, int idVerticeDestino, double peso) {
        this.peso = peso;
        this.id = id;
        this.idVerticeOrigem = idVerticeOrigem;
        this.idVerticeDestino = idVerticeDestino;
    }
}
