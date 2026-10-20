package jogador;

public class AnalistaSituacional
{
    private Tabuleiro tabDoJogo;

    public AnalistaSituacional (Tabuleiro tab) throws Exception
    {
        if (tab == null)
            throw new Exception ("Tabuleiro ausente");

        // sem clonar: o analista precisa enxergar as jogadas feitas depois
        this.tabDoJogo = tab;
    }

    // retorna a marca se as tres posicoes tiverem a mesma marca; senao, null
    private Marca tresIguais (int l1, int c1, int l2, int c2, int l3, int c3) throws Exception
    {
        Marca m = this.tabDoJogo.getMarcaNaPosicao (l1, c1);

        if (m == null)
            return null;

        if (m.equals (this.tabDoJogo.getMarcaNaPosicao (l2, c2)) &&
            m.equals (this.tabDoJogo.getMarcaNaPosicao (l3, c3)))
            return m;

        return null;
    }

    public Marca getMarcaDoVencedor ()
    {
        Marca ret = null;

        try
        {
            for (int i = 0; i < 3 && ret == null; i++)
            {
                ret = this.tresIguais (i, 0, i, 1, i, 2); // linha i

                if (ret == null)
                    ret = this.tresIguais (0, i, 1, i, 2, i); // coluna i
            }

            if (ret == null)
                ret = this.tresIguais (0, 0, 1, 1, 2, 2); // diagonal principal

            if (ret == null)
                ret = this.tresIguais (0, 2, 1, 1, 2, 0); // diagonal secundaria
        }
        catch (Exception erro)
        {} // ignorando pois sei que nao uso posicao invalida

        return ret;
    }

    private boolean tabuleiroCheio ()
    {
        try
        {
            for (int lin = 0; lin < 3; lin++)
                for (int col = 0; col < 3; col++)
                    if (!this.tabDoJogo.haMarcaNaPosicao (lin, col))
                        return false;
        }
        catch (Exception erro)
        {} // ignorando pois sei que nao uso posicao invalida

        return true;
    }

    public boolean fimDeJogo ()
    {
        if (this.getMarcaDoVencedor () != null)
            return true;

        if (this.tabuleiroCheio ())
            return true;

        return false;
    }

    @Override
    public String toString ()
    {
        Marca vencedor = this.getMarcaDoVencedor ();

        return "Vencedor: " + (vencedor == null ? "ninguem" : vencedor.toString ()) +
               " - Fim de jogo: " + this.fimDeJogo ();
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (this.getClass () != obj.getClass ())
            return false;

        AnalistaSituacional ana = (AnalistaSituacional) obj;

        if (!this.tabDoJogo.equals (ana.tabDoJogo))
            return false;

        return true;
    }

    @Override
    public int hashCode ()
    {
        int ret = 7;

        ret = 13 * ret + this.tabDoJogo.hashCode ();

        if (ret < 0)
            ret = -ret;

        return ret;
    }
}
