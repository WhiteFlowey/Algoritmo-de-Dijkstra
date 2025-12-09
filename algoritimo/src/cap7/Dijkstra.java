package cap7;
import cap7.listaad.autoreferencia.Grafo;

public class Dijkstra {
    private int antecessor[];
    private double p[];
    private Grafo grafo;

    public Dijkstra(Grafo grafo) {
        this.grafo = grafo;
    }

    public void obterArvoreCMC(int raiz) throws Exception {
        int n = this.grafo.numVertices();
        this.p = new double[n]; // peso dos vértices
        int vs[] = new int[n+1]; // vértices
        this.antecessor = new int[n];
        for (int u = 0; u < n; u++) {
            this.antecessor[u] = -1;
            p[u] = Double.MAX_VALUE; // oo
            vs[u + 1] = u; // Heap indireto a ser construido
        }
        p[raiz] = 0;
        FPHeapMinIndireto heap = new FPHeapMinIndireto(p, vs);
        heap.constroi();
        while (!heap.vazio()) {
            int u = heap.retiraMin();
            if (!this.grafo.listaAdjVazia(u)) {
                Grafo.Aresta adj = grafo.primeiroListaAdj(u);
                while (adj != null) {
                    int v = adj.v2();
                    if (this.p[v] > (this.p[u] + adj.peso())) {
                        antecessor[v] = u;
                        heap.diminuiChave(v, this.p[u] + adj.peso());
                    }
                    adj = grafo.proxAdj(u);
                }
            }
        }
    }

    public int antecessor(int u) {
        return this.antecessor[u];
    }

    public double peso(int u) {
        return this.p[u];
    }

    public void imprimeCaminho(int origem, int v) {
        if (origem == v)
            System.out.println(origem);
        else if (this.antecessor[v] == -1)
            System.out.println("Nao existe caminho de " + origem + " ate " + v);
        else {
            imprimeCaminho(origem, this.antecessor[v]);
            System.out.println(v);
        }
    }
}