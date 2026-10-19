package jogador;

public class JogadorAutomatico implements Jogador {
    private Marca marca = null;
    private Tabuleiro tabDoJogo = null;

    public JogadorAutomatico(Tabuleiro tab) throws Exception {
        if (tab == null) throw new Exception("Tabuleiro invalido.");
        tabDoJogo = tab;
        marca = new Marca((int)(Math.random() * 2) == 0 ? 'X' : 'O');
    }

    public JogadorAutomatico(Tabuleiro tab, Marca mrc) throws Exception {
        if (tab == null || mrc == null) throw new Exception("Jogador invalido.");
        tabDoJogo = tab;
        marca = mrc;
    }

    public Marca getMarca() {
        return marca;
    }

    public void facaSuaJogada() {
        try {
            int[][] linhas = {{0,0,0,1,0,2},{1,0,1,1,1,2},{2,0,2,1,2,2},
                {0,0,1,0,2,0},{0,1,1,1,2,1},{0,2,1,2,2,2},{0,0,1,1,2,2},{0,2,1,1,2,0}};

            // Primeiro tenta ganhar; depois bloqueia o outro.
            for (int vez = 0; vez < 2; vez++) {
                Marca alvo = vez == 0 ? marca : new Marca(marca.getSimbolo() == 'X' ? 'O' : 'X');
                for (int[] l : linhas) {
                    int iguais = 0, vazios = 0, linVazia = -1, colVazia = -1;
                    int[][] pos = {{l[0],l[1]},{l[2],l[3]},{l[4],l[5]}};
                    for (int[] p : pos) {
                        Marca atual = tabDoJogo.getMarcaNaPosicao(p[0], p[1]);
                        if (atual == null) {
                            vazios++;
                            linVazia = p[0];
                            colVazia = p[1];
                        } else if (atual.equals(alvo)) iguais++;
                    }
                    if (iguais == 2 && vazios == 1) {
                        tabDoJogo.setMarcaNaPosicao(marca, linVazia, colVazia);
                        return;
                    }
                }
            }

            // Se nao tem jogada urgente, pega a primeira casa livre.
            for (int lin = 0; lin < 3; lin++)
                for (int col = 0; col < 3; col++)
                    if (!tabDoJogo.haMarcaNaPosicao(lin, col)) {
                        tabDoJogo.setMarcaNaPosicao(marca, lin, col);
                        return;
                    }
        } catch (Exception e) {
            System.out.println("Nao foi possivel fazer a jogada do computador.");
        }
    }
}
