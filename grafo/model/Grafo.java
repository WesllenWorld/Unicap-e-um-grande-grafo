package model;

import java.util.ArrayList;
import arquivos.MontagemDoGrafo;
import java.util.LinkedList;
import java.util.List;

public class Grafo {
    private ArrayList<ArrayList<Vertice>> adj = new ArrayList<ArrayList<Vertice>>();
    MontagemDoGrafo montagemDoGrafo = new MontagemDoGrafo();




    public void setup(){
        montagemDoGrafo.montarVertices();
        montagemDoGrafo.montarArestas();



    }



    public void imprimir(){

    }

    public void caminhoMinimo(){

    }
}
