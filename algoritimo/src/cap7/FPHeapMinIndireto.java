package cap7;
public class FPHeapMinIndireto {
    private double p[];
    private int n, pos[], fp[];

    public FPHeapMinIndireto(double p[], int v[]) {
        this.p = p;
        this.fp = v;
        this.n = this.fp.length - 1;
        this.pos = new int[this.n + 1];
        for (int i = 0; i <= this.n; i++)
            this.pos[i] = i;
    }

    public void refaz(int esq, int dir) {
        int j = esq * 2;
        int x = this.fp[esq];
        while (j <= dir) {
            if ((j < dir) && (this.p[this.fp[j]] > this.p[this.fp[j + 1]]))
                j++;
            if (this.p[x] <= this.p[this.fp[j]])
                break;
            this.fp[esq] = this.fp[j];
            this.pos[this.fp[j]] = esq;
            esq = j;
            j = esq * 2;
        }
        this.fp[esq] = x;
        this.pos[x] = esq;
    }

    public void constroi() {
        int esq = n / 2 + 1;
        while (esq > 1) {
            esq--;
            this.refaz(esq, this.n);
        }
    }

    public int retiraMin() throws Exception {
        int min;
        if (this.n < 1)
            throw new Exception("Erro: heap vazio");
        else {
            min = this.fp[1];
            this.fp[1] = this.fp[this.n];
            this.pos[this.fp[this.n--]] = 1;
            this.refaz(1, this.n);
        }
        return min;
    }

    public void diminuiChave(int i, double chave) throws Exception {
        i = this.pos[i];
        if (chave < this.p[this.fp[i]]) {
             this.p[this.fp[i]] = chave;
             int pai = i / 2;
             while ((pai >= 1) && (this.p[this.fp[i]] < this.p[this.fp[pai]])) {
                 int aux = this.fp[pai];
                 this.fp[pai] = this.fp[i];
                 this.pos[this.fp[i]] = pai;
                 this.fp[i] = aux;
                 this.pos[aux] = i;
                 i = pai;
                 pai = i / 2;
             }
        }
    }

    boolean vazio() {
        return this.n == 0;
    }
}