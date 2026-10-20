package jogador;

public class JogadorAutomatico implements Jogador
{
    private Marca     marca     = null;
    private Tabuleiro tabDoJogo = null;

    // as 8 linhas de 3 posicoes: 3 linhas, 3 colunas e 2 diagonais
    private static final int[][][] LINHAS =
    {
        { {0,0}, {0,1}, {0,2} }, // linha 0
        { {1,0}, {1,1}, {1,2} }, // linha 1
        { {2,0}, {2,1}, {2,2} }, // linha 2
        { {0,0}, {1,0}, {2,0} }, // coluna 0
        { {0,1}, {1,1}, {2,1} }, // coluna 1
        { {0,2}, {1,2}, {2,2} }, // coluna 2
        { {0,0}, {1,1}, {2,2} }, // diagonal principal
        { {0,2}, {1,1}, {2,0} }  // diagonal secundaria
    };

    public JogadorAutomatico (Tabuleiro tab) throws Exception
    {
        if (tab == null)
            throw new Exception ("Tabuleiro ausente");

        this.tabDoJogo = tab;

        // escolhe sua marca via sorteio de numeros aleatorios
        if ((int)(Math.random()*2) == 0)
            this.marca = new Marca ('X');
        else
            this.marca = new Marca ('O');
    }

    public JogadorAutomatico (Tabuleiro tab, Marca mrc) throws Exception
    {
        if (tab == null)
            throw new Exception ("Tabuleiro ausente");

        if (mrc == null)
            throw new Exception ("Marca ausente");

        this.tabDoJogo = tab;
        this.marca     = mrc;
    }

    public Marca getMarca ()
    {
        return this.marca;
    }

    // quantas posicoes da linha (de 3 posicoes) tem a marca m
    private int quantasMarcas (int[][] linha, Marca m) throws Exception
    {
        int ret = 0;

        for (int[] pos : linha)
            if (this.tabDoJogo.haMarcaNaPosicao (pos[0], pos[1]) &&
                this.tabDoJogo.getMarcaNaPosicao (pos[0], pos[1]).equals (m))
                ret++;

        return ret;
    }

    // quantas posicoes da linha (de 3 posicoes) estao livres
    private int quantasLivres (int[][] linha) throws Exception
    {
        int ret = 0;

        for (int[] pos : linha)
            if (!this.tabDoJogo.haMarcaNaPosicao (pos[0], pos[1]))
                ret++;

        return ret;
    }

    // joga na primeira posicao livre da linha
    private void jogarNaLivre (int[][] linha) throws Exception
    {
        for (int[] pos : linha)
            if (!this.tabDoJogo.haMarcaNaPosicao (pos[0], pos[1]))
            {
                this.tabDoJogo.setMarcaNaPosicao (this.marca, pos[0], pos[1]);
                return;
            }
    }

    // tentando ganhar (m == this.marca) ou atrapalhar a vitoria do
    // adversario (m == marca dele): ha 2 marcas de m e 1 livre numa linha
    private boolean completar (Marca m) throws Exception
    {
        for (int[][] linha : LINHAS)
            if (this.quantasMarcas (linha, m) == 2 && this.quantasLivres (linha) == 1)
            {
                this.jogarNaLivre (linha);
                return true;
            }

        return false;
    }

    // tentando jogada de futuro: ha 1 marca do jogador e mais nada
    // na linha; vamos la e plantamos outra marca
    private boolean jogadaDeFuturo () throws Exception
    {
        for (int[][] linha : LINHAS)
            if (this.quantasMarcas (linha, this.marca) == 1 && this.quantasLivres (linha) == 2)
            {
                this.jogarNaLivre (linha);
                return true;
            }

        return false;
    }

    // termina jogando num lugar qualquer que esteja livre
    private void jogarEmQualquerLugar () throws Exception
    {
        for (int lin = 0; lin < 3; lin++)
            for (int col = 0; col < 3; col++)
                if (!this.tabDoJogo.haMarcaNaPosicao (lin, col))
                {
                    this.tabDoJogo.setMarcaNaPosicao (this.marca, lin, col);
                    return;
                }
    }

    public void facaSuaJogada ()
    {
        System.out.println ("\nVez do computador (" + this.marca + ")...");

        try
        {
            Marca adversaria = new Marca (this.marca.getSimbolo () == 'X' ? 'O' : 'X');

            if (this.completar (this.marca))         // tentando ganhar
                return;

            if (this.completar (adversaria))         // tentando atrapalhar
                return;

            if (this.jogadaDeFuturo ())              // jogada de futuro
                return;

            this.jogarEmQualquerLugar ();            // qualquer lugar livre
        }
        catch (Exception erro)
        {} // ignorando pois sei que nao uso posicao invalida nem comparo null
    }
}
