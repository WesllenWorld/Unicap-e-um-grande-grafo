package arquivos;
import model.Aresta;
import model.Vertice;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MontagemDoGrafo {
    File aArestas = new File("grafo/arquivos/arestas.txt");
    File aVertices = new File("grafo/arquivos/vertices.txt");

    public void montarVertices(ArrayList<Vertice> lV){
        String n;
        int id;
        try{
            Scanner sc = new Scanner(aVertices);
            sc.nextLine();


            while (sc.hasNext())
            {
                String[] vertice = sc.nextLine().split(",");
                id = Integer.parseInt(vertice[0]);
                n = vertice[1];
                Vertice v = new Vertice(id, n);
                lV.add(v);
            }
            sc.close();


        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void montarArestas(ArrayList<Aresta> lA){
        int id, origem, destino;
        double peso;
        try{
            Scanner sc = new Scanner(aArestas);
            sc.nextLine();

            while (sc.hasNext())
            {
                String[] aresta = sc.nextLine().split(",");
                id = Integer.parseInt(aresta[0]);
                origem = Integer.parseInt(aresta[1]);
                destino = Integer.parseInt(aresta[2]);
                peso = Double.parseDouble(aresta[3]);
                Aresta a = new Aresta(id, origem, destino, peso);
                lA.add(a);
            }
            sc.close();


        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void adjacencia(ArrayList<Vertice> lV, ArrayList<Aresta> lA, ArrayList<ArrayList<Vertice>> adj){
        for(int i = 0; i < lV.size(); i++){
            ArrayList<Vertice> adjVertice = new ArrayList<>();
            for(int j = 0; j<lA.size();j++){
                //SE -> o id está em um destino E não está na lista de adjacência atual = adiciona o vértice da origem OU
                // id está na origem de vertice E não está na lista de adjacência atual = adiciona o vértice do destino

                if(lV.get(i).getId() == lA.get(j).getIdVerticeOrigem() && !adjVertice.contains(lV.get(i).getId())){
                    adjVertice.add(lV.get(lA.get(j).getIdVerticeDestino()-1));
                }
                if(lV.get(i).getId() == lA.get(j).getIdVerticeDestino() && !adjVertice.contains(lV.get(i).getId())){
                    adjVertice.add(lV.get(lA.get(j).getIdVerticeOrigem()-1));
                }

            }
            adj.add(i, adjVertice);
        }

    }
}
