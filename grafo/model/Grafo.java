package model;

import java.util.ArrayList;
import arquivos.MontagemDoGrafo;

public class Grafo {
    private ArrayList<Vertice> listaVertice = new ArrayList<Vertice>();
    private ArrayList<Aresta> listaAresta = new ArrayList<Aresta>();
    private ArrayList<ArrayList<Vertice>> adj = new ArrayList<ArrayList<Vertice>>();
    MontagemDoGrafo montagemDoGrafo = new MontagemDoGrafo();




    public void setup(){
        montagemDoGrafo.montarVertices(listaVertice);
        for(int i = 0; i<listaVertice.size();i++){
            System.out.println(listaVertice.get(i).getId() + " "+listaVertice.get(i).getNome() );
        }
        montagemDoGrafo.montarArestas(listaAresta);
        for(int i = 0; i<listaAresta.size();i++){
            System.out.println(listaAresta.get(i).getId() + " "+listaAresta.get(i).getIdVerticeOrigem()+ " "+
                    listaAresta.get(i).getIdVerticeDestino()+ " "+listaAresta.get(i).getPeso() );
        }


    }

    public void imprimir(){

    }

    public void caminhoMinimo(){

    }
}
