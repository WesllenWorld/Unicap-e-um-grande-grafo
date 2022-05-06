package model;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import arquivos.MontagemDoGrafo;

public class Grafo {
    private ArrayList<Vertice> listaVertice = new ArrayList<>();
    private ArrayList<Aresta> listaAresta = new ArrayList<>();
    private ArrayList<ArrayList<Vertice>> adj = new ArrayList<>();
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
        montagemDoGrafo.adjacencia(listaVertice, listaAresta, adj);

        imprimirAdj();
    }

    public void imprimir(){

    }

    public void imprimirAdj(){
        for(int i= 0; i<adj.size();i++){
            for(int j = 0; j<adj.get(i).size();j++){
                System.out.println("Vertice "+(i+1)+" é vizinho de: "+ adj.get(i).get(j).getNome());
            }
        }


    }

    public void buscaExtensao(){

    }

    public void buscaDijkstra(){

    }

    public void menuOpcoes(){
        System.out.println("Selecione: ");
        System.out.println("1 - Busca no grafo, utilizando o caminho mínimo.");
        System.out.println("2 - Busca no grafo, utilizando o algoritmo de Dijkstra (grafo com pesos).");
        System.out.println("3 - Impressão do grafo.");
        System.out.println("0 - Sair, pois não quero mais brincar com o grafo.");
    }

    public void grafoMenu(){
        boolean menu = true;
        int op;
        Scanner in = new Scanner(System.in);

        while(menu) {
            try{
                menuOpcoes();
                op = in.nextInt();
                while(op < 0 || op > 3){
                    System.out.println("Tá na Disney, meu consagrado? opções vão de 0 até 3.");
                    op = in.nextInt();
                }
                switch(op){
                    case 1:
                        System.out.println("A");
                        break;
                    case 2:
                        System.out.println("B");
                        break;
                    case 3:
                        System.out.println("C");
                        break;
                    case 0:
                        System.out.println("E isso é tudo, pessoal!");
                        menu = false;
                        in.close();
                        break;
                }
            }catch(InputMismatchException e){
                System.out.println(" 0\n A\n 3\n E\n NADA\n MAIS.\n");
                in.next();
            }

        }

    }
}
