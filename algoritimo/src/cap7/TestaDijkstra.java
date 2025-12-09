package cap7;

import cap7.listaad.autoreferencia.Grafo;

public class TestaDijkstra {
    public static void main(String[] args) {
        // 1. Criar um grafo com 6 vértices (0 a 5)
        Grafo grafo = new Grafo(6);

        // 2. Adicionar arestas (v1, v2, peso)
        // Este é um exemplo clássico de grafo
        try {
            grafo.insereAresta(0, 1, 7);
            grafo.insereAresta(0, 2, 9);
            grafo.insereAresta(0, 5, 14);
            grafo.insereAresta(1, 2, 1);
            grafo.insereAresta(1, 3, 15);
            grafo.insereAresta(2, 3, 11);
            grafo.insereAresta(2, 5, 2);
            grafo.insereAresta(3, 4, 6);
            grafo.insereAresta(4, 5, 9);

            System.out.println("Grafo criado:");
            grafo.imprime();
            System.out.println("------------------------------------");

            // 3. Executar o Dijkstra
            Dijkstra dijkstra = new Dijkstra(grafo);
            int raiz = 0; // Começar pelo vértice 0
            dijkstra.obterArvoreCMC(raiz);

            // 4. Imprimir os resultados
            System.out.println("Calculando menor caminho a partir da raiz: " + raiz);
            
            // Imprime o menor caminho da raiz (0) até o vértice 4
            int destino = 4;
            System.out.println("\nCaminho para o vertice " + destino + " (custo: " + dijkstra.peso(destino) + "):");
            dijkstra.imprimeCaminho(raiz, destino);

            // Imprime o menor caminho da raiz (0) até o vértice 5
            destino = 5;
            System.out.println("\nCaminho para o vertice " + destino + " (custo: " + dijkstra.peso(destino) + "):");
            dijkstra.imprimeCaminho(raiz, destino);

        } catch (Exception e) {
            System.err.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}