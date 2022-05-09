package main;

import model.Grafo;

public class Main {

    public static void main (String[] args){//Apenas para rodar o grafo e utilizar o menu
        Grafo grafo = new Grafo();

        grafo.setup();
        grafo.grafoMenu();
    }
}
