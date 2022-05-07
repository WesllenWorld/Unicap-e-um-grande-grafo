package model;

import java.util.*;

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
            if(adj.get(i).size() == 0){
                System.out.println(listaVertice.get(i).getNome() + " não tem vizinhos.");
            }
            for(int j = 0; j<adj.get(i).size();j++){
                System.out.println("Vertice "+listaVertice.get(i).getNome()+" é vizinho de: "+ adj.get(i).get(j).getNome());
            }
        }


    }

    public void buscaExtensao(int pId, int cId){
        Vertice verticePartida = listaVertice.get(pId-1);
        Vertice verticeChegada = listaVertice.get(cId-1);
        Vertice verticeAtual;
        Queue<Vertice> filaExtensao = new LinkedList<>();
        Vertice verticePrint = verticePartida;

        //reset das cores dos nós
        for (int i = 0; i < listaVertice.size(); i++) {
            Vertice vertice = listaVertice.get(i);
            vertice.setCor("Branco");
            vertice.setExtensaoDistancia(0);
            vertice.setAntecessor(null);
            verticeChegada.setSucessor(null);
        }


        verticePartida.setCor("Cinza");
        filaExtensao.add(verticePartida);
        while(!filaExtensao.isEmpty()){
            verticeAtual = filaExtensao.poll();
            //for(int i = 0; i<adj.get(verticeAtual.getId()).size();i++){
            //for(int i = 0; i<adj.size();i++){
                for(int j = 0; j<adj.get(verticeAtual.getId()-1).size();j++){
                    Vertice verticeAdjacente = adj.get(verticeAtual.getId()-1).get(j);
                   /*
                    if(verticeAdjacente == verticeChegada){
                        System.out.println("Vértice "+ verticeChegada.getNome() + " foi encontrado!");
                        System.out.println("Distância percorrida: "+verticeAdjacente.getExtensaoDistancia());
                        System.out.println("Sequência de vértices: ");
                        System.out.println(verticePartida.getNome());
                        while(verticePartida.getSucessor()!= null) {
                            System.out.printf(verticeAtual.getSucessor().getNome()+" ");
                        }
                        System.out.println(verticeChegada.getNome());
                    }
                    */
                    if(verticeAdjacente.getCor().equals("Branco")){
                        verticeAdjacente.setCor("Cinza");
                        verticeAdjacente.setExtensaoDistancia(verticeAtual.getExtensaoDistancia()+1);
                       // verticeAdjacente.setAntecessor(verticeAtual.getAntecessor());
                        verticeAtual.setSucessor(verticeAdjacente);
                        filaExtensao.add(verticeAdjacente);
                    }
                    verticeAtual.setCor("Preto");
               // }

                    if(verticeAtual==verticeChegada){
                        System.out.println("Vértice "+ verticeChegada.getNome() + " foi encontrado!");
                        System.out.println("Distância percorrida: "+verticeAtual.getExtensaoDistancia());
                        System.out.println("Sequência de vértices: ");
                        System.out.printf(verticePartida.getNome()+" ");
                        while(verticePrint.getSucessor()!= null) {
                            System.out.printf(verticePrint.getSucessor().getNome()+" ");
                            verticePrint = verticePrint.getSucessor();
                        }
                       // System.out.println(verticeChegada.getNome());
                        return;
                    }
            }
        }
    }

    public void buscaDijkstra(){

    }

    public void menuOpcoes(){
        System.out.println("Selecione: ");
        System.out.println("1 - Busca no grafo, utilizando busca em extensão.");
        System.out.println("2 - Busca no grafo, utilizando o algoritmo de Dijkstra (grafo com pesos).");
        System.out.println("3 - Impressão do grafo.");
        System.out.println("0 - Sair, pois não quero mais brincar com o grafo.");
    }

    public void grafoMenu(){
        boolean menu = true;
        int op, partida, chegada;
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
                        System.out.println("ID DO VÉRTICE DE PARTIDA:");
                        partida = in.nextInt();
                        System.out.println("ID DO VÉRTICE DE CHEGADA:");
                        chegada = in.nextInt();
                        buscaExtensao(partida, chegada);

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

    public Vertice acharVertice(int id){
        for(int i = 0;i<listaVertice.size();i++){
            if(listaVertice.get(i).getId() == id){
                return listaVertice.get(i);
            }
        }
        return null;
    }
}
