package model;

import java.util.*;

import arquivos.MontagemDoGrafo;

public class Grafo {
    private ArrayList<Vertice> listaVertice = new ArrayList<>();
    private ArrayList<Aresta> listaAresta = new ArrayList<>();
    private ArrayList<ArrayList<Vertice>> adj = new ArrayList<>();
    MontagemDoGrafo montagemDoGrafo = new MontagemDoGrafo();


    public void setup() {
        montagemDoGrafo.montarVertices(listaVertice);
        for (int i = 0; i < listaVertice.size(); i++) {
            System.out.println(listaVertice.get(i).getId() + " " + listaVertice.get(i).getNome());
        }
        montagemDoGrafo.montarArestas(listaAresta);
        for (int i = 0; i < listaAresta.size(); i++) {
            System.out.println(listaAresta.get(i).getId() + " " + listaAresta.get(i).getIdVerticeOrigem() + " " +
                    listaAresta.get(i).getIdVerticeDestino() + " " + listaAresta.get(i).getPeso());
        }
        montagemDoGrafo.adjacencia(listaVertice, listaAresta, adj);


    }

    private double acharPeso(int um, int dois) {//Func que retorna o peso da aresta entre os nos

        for(int i = 0; i<listaAresta.size();i++){
            if((listaAresta.get(i).getIdVerticeOrigem() == um && listaAresta.get(i).getIdVerticeDestino() == dois) ||
                    (listaAresta.get(i).getIdVerticeOrigem() == dois && listaAresta.get(i).getIdVerticeDestino() == um)){
                return listaAresta.get(i).getPeso();
            }
        }
        return 0;
    }

    private void imprimirAdj() {
        for (int i = 0; i < adj.size(); i++) {
            if (adj.get(i).size() == 0) {
                System.out.println(listaVertice.get(i).getNome() + " não tem vizinhos.");
            }
            else {
                for (int j = 0; j < adj.get(i).size(); j++) {
                    double p = acharPeso(listaVertice.get(i).getId(), adj.get(i).get(j).getId());
                    System.out.println("Vertice " + listaVertice.get(i).getNome() + " é vizinho de: " + adj.get(i).get(j).getNome());
                    System.out.println("Distância = " + p);
                }
            }
            System.out.println();
        }


    }

    private void buscaExtensao(int pId, int cId) {
        Vertice verticePartida = listaVertice.get(pId - 1);
        Vertice verticeChegada = listaVertice.get(cId - 1);
        Vertice verticeAtual;
        Queue<Vertice> filaExtensao = new LinkedList<>();
        Stack<Vertice> printCaminho = new Stack<>();

        //reset para a nova busca
        for (int i = 0; i < listaVertice.size(); i++) {
            Vertice vertice = listaVertice.get(i);
            vertice.setCor("Branco");
            vertice.setExtensaoDistancia(Integer.MAX_VALUE);
            vertice.setAntecessor(null);
        }


        verticePartida.setCor("Cinza");
        verticePartida.setExtensaoDistancia(0);
        filaExtensao.add(verticePartida);
        while (!filaExtensao.isEmpty()) {
            verticeAtual = filaExtensao.poll();
            if (verticeAtual == verticeChegada) {//SE o vertice de chegada foi encontrado
                System.out.println("ACHOU");
                System.out.println("Distância total: " + verticeAtual.getExtensaoDistancia());
                System.out.println("Caminho => \n");
                while (verticeChegada.getAntecessor() != null) {
                    printCaminho.push(verticeChegada); //Joga na pilha. No momento de desenpilhar, os vertices serao printados em ordem de movimentacao ate o destino
                    verticeChegada = verticeChegada.getAntecessor();
                }
                printCaminho.push(verticePartida); //Adicao do vertice de partida
                for (int i = 0; i < verticeAtual.getExtensaoDistancia() + 1; i++) {
                    Vertice print = printCaminho.pop();
                    System.out.println(print.getId() + "/" + print.getNome() + " => \n");
                }
                return;
            }

            for (Vertice v : adj.get(verticeAtual.getId() - 1)) {
                if (v.getCor().equals("Branco")) {
                    v.setCor("Cinza");
                    v.setExtensaoDistancia(verticeAtual.getExtensaoDistancia() + 1);
                    v.setAntecessor(verticeAtual);
                    filaExtensao.add(v);
                }
            }
            verticeAtual.setCor("Preto");
        }
    }

    public void buscaDijkstra(int pId, int cId) {
        Vertice verticePartida = listaVertice.get(pId - 1);
        Vertice verticeChegada = listaVertice.get(cId - 1);
        Vertice verticeAtual;
        Set<Vertice> visitados = new HashSet<>();
        Stack<Vertice> printCaminho = new Stack<>();

        Queue<Vertice> filaDijkstra = new LinkedList<>();
        double[] menorDistanciaPartida = new double[listaVertice.size()];


        //Mantendo todos os antecessores = null e criando o arraylist de
        for (int i = 0; i < listaVertice.size(); i++) {
            Vertice vertice = listaVertice.get(i);
            vertice.setAntecessor(null);
            menorDistanciaPartida[i] = Double.MAX_VALUE;
        }

        menorDistanciaPartida[verticePartida.getId()-1] = 0.0;
        filaDijkstra.add(verticePartida);

        while(!filaDijkstra.isEmpty()){

            Vertice u = filaDijkstra.poll();
            visitados.add(u);
            double distanciaAtual, distanciaNova;

            for(int i = 0; i < adj.get(u.getId()-1).size(); i++){
                verticeAtual = adj.get(u.getId()-1).get(i);

                if(!visitados.contains(verticeAtual)){
                    distanciaAtual = acharPeso(u.getId(), verticeAtual.getId());
                    distanciaNova = menorDistanciaPartida[u.getId()-1] + distanciaAtual;
                    if(distanciaNova < menorDistanciaPartida[verticeAtual.getId()-1]){
                        menorDistanciaPartida[verticeAtual.getId()-1] = distanciaNova;
                    }
                    filaDijkstra.add(verticeAtual);
                    verticeAtual.setAntecessor(u);
                }

                if(verticeAtual == verticeChegada){
                    System.out.println("ACHOU");
                    System.out.println("DISTANCIA TOTAL: "+ menorDistanciaPartida[verticeAtual.getId()-1]);
                    System.out.println("Caminho => \n");
                    do{
                        printCaminho.push(verticeAtual);//Joga na pilha. No momento de desenpilhar, os vertices serao printados em ordem de movimentacao ate o destino
                        verticeAtual = verticeAtual.getAntecessor();

                    }while (verticeAtual != null);
                    while(!printCaminho.isEmpty()) {
                        Vertice print = printCaminho.pop();
                        System.out.println(print.getId()+"/"+print.getNome() + " => \n");
                    }
                    return;
                }

            }
        }
    }

    private void menuOpcoes() {//Prints do menu
        System.out.println("Selecione: ");
        System.out.println("1 - Busca no grafo, utilizando busca em extensão.");
        System.out.println("2 - Busca no grafo, utilizando o algoritmo de Dijkstra (grafo com pesos).");
        System.out.println("3 - Impressão do grafo.");
        System.out.println("0 - Sair, pois não quero mais brincar com o grafo.");
    }

    public void grafoMenu() {//Menu de selecao
        boolean menu = true;
        int op, partida, chegada;
        Scanner in = new Scanner(System.in);

        while (menu) {
            try {
                menuOpcoes();
                op = in.nextInt();
                while (op < 0 || op > 3) {
                    System.out.println("Tá na Disney, meu consagrado? opções vão de 0 até 3.");
                    op = in.nextInt();
                }
                switch (op) {
                    case 1:
                        System.out.println("ID DO VÉRTICE DE PARTIDA:");
                        partida = in.nextInt();
                        System.out.println("ID DO VÉRTICE DE CHEGADA:");
                        chegada = in.nextInt();

                        System.out.println();
                        buscaExtensao(partida, chegada);
                        break;
                    case 2:
                        System.out.println("ID DO VÉRTICE DE PARTIDA:");
                        partida = in.nextInt();
                        System.out.println("ID DO VÉRTICE DE CHEGADA:");
                        chegada = in.nextInt();

                        System.out.println();
                        buscaDijkstra(partida, chegada);
                        break;
                    case 3:
                        System.out.println();
                        imprimirAdj();
                        break;
                    case 0:
                        System.out.println("E isso é tudo, pessoal!");
                        menu = false;
                        in.close();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(" 0\n A\n 3\n E\n NADA\n MAIS.\n");
                in.next();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Meu caro, você está inserindo números que não correspondem a nenhum nó. Por obséquio, insira números válidos.\n");
            }
        }
    }
}
