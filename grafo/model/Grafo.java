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

        imprimirAdj();
    }

    public void imprimir() {

    }

    public void imprimirAdj() {
        for (int i = 0; i < adj.size(); i++) {
            if (adj.get(i).size() == 0) {
                System.out.println(listaVertice.get(i).getNome() + " não tem vizinhos.");
            }
            for (int j = 0; j < adj.get(i).size(); j++) {
                System.out.println("Vertice " + listaVertice.get(i).getNome() + " é vizinho de: " + adj.get(i).get(j).getNome());
            }
        }


    }

    public void buscaExtensao(int pId, int cId) {
        Vertice verticePartida = listaVertice.get(pId - 1);
        Vertice verticeChegada = listaVertice.get(cId - 1);
        Vertice verticeAtual;
        Queue<Vertice> filaExtensao = new LinkedList<>();
        Vertice verticePrint = verticePartida;
        Stack<Vertice> printCaminho = new Stack<>();
        //reset das cores dos nós
        for (int i = 0; i < listaVertice.size(); i++) {
            Vertice vertice = listaVertice.get(i);
            vertice.setCor("Branco");
            vertice.setExtensaoDistancia(Integer.MAX_VALUE);
            vertice.setAntecessor(null);
            verticeChegada.setSucessor(null);
        }


        verticePartida.setCor("Cinza");
        verticePartida.setExtensaoDistancia(0);
        filaExtensao.add(verticePartida);
        while (!filaExtensao.isEmpty()) {
            verticeAtual = filaExtensao.poll();
            if (verticeAtual == verticeChegada) {
                System.out.println("ACHOU");
                System.out.println("Distância total: " + verticeAtual.getExtensaoDistancia());
                System.out.println("Caminho => \n");
                while (verticeChegada.getAntecessor() != null) {
                    printCaminho.push(verticeChegada);
                    verticeChegada = verticeChegada.getAntecessor();
                }
                printCaminho.push(verticePartida);
                for (int i = 0; i < verticeAtual.getExtensaoDistancia() + 1; i++) {
                    Vertice print = printCaminho.pop();
                    System.out.println(print.getId() + "/" + print.getNome() + " => ");
                }
                System.out.println();
            }
            //for(int i = 0; i<adj.get(verticeAtual.getId()).size();i++){
            //for(int i = 0; i<adj.size();i++){
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

    public void buscaDijkstra() {

    }

    public void menuOpcoes() {
        System.out.println("Selecione: ");
        System.out.println("1 - Busca no grafo, utilizando busca em extensão.");
        System.out.println("2 - Busca no grafo, utilizando o algoritmo de Dijkstra (grafo com pesos).");
        System.out.println("3 - Impressão do grafo.");
        System.out.println("0 - Sair, pois não quero mais brincar com o grafo.");
    }

    public void grafoMenu() {
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
            } catch (InputMismatchException e) {
                System.out.println(" 0\n A\n 3\n E\n NADA\n MAIS.\n");
                in.next();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Meu caro, você está inserindo números que não correspondem a nenhum nó. Por obséquio, insira números válidos.\n");
            }

        }

    }

    public Vertice acharVertice(int id) {
        for (int i = 0; i < listaVertice.size(); i++) {
            if (listaVertice.get(i).getId() == id) {
                return listaVertice.get(i);
            }
        }
        return null;
    }
}
