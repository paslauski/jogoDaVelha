package jogador;

public class AnalistaSituacional {
    private Tabuleiro tabDoJogo;

    public AnalistaSituacional(Tabuleiro tab) throws Exception {
        if (tab == null) throw new Exception("Tabuleiro invalido.");
        tabDoJogo = tab;
    }

    public Marca getMarcaDoVencedor() {
        try {
            int[][] linhas = {{0,0,0,1,0,2},{1,0,1,1,1,2},{2,0,2,1,2,2},
                {0,0,1,0,2,0},{0,1,1,1,2,1},{0,2,1,2,2,2},{0,0,1,1,2,2},{0,2,1,1,2,0}};
            for (int[] l : linhas) {
                Marca a = tabDoJogo.getMarcaNaPosicao(l[0], l[1]);
                Marca b = tabDoJogo.getMarcaNaPosicao(l[2], l[3]);
                Marca c = tabDoJogo.getMarcaNaPosicao(l[4], l[5]);
                if (a != null && a.equals(b) && a.equals(c)) return a;
            }
        } catch (Exception e) {
        }
        return null;
    }

    private boolean tabuleiroCheio() {
        try {
            for (int lin = 0; lin < 3; lin++)
                for (int col = 0; col < 3; col++)
                    if (!tabDoJogo.haMarcaNaPosicao(lin, col)) return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean fimDeJogo() {
        return getMarcaDoVencedor() != null || tabuleiroCheio();
    }

    @Override
    public String toString() {
        return tabDoJogo.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AnalistaSituacional)) return false;
        return tabDoJogo.equals(((AnalistaSituacional) obj).tabDoJogo);
    }

    @Override
    public int hashCode() {
        return tabDoJogo.hashCode();
    }
}
