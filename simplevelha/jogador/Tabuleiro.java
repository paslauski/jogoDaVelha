package jogador;

public class Tabuleiro implements Cloneable
{
    // a principio, toda igual a null
    private Marca matriz [][] = new Marca [3][3];

    private void validar (int lin, int col) throws Exception
    {
        if (lin < 0 || lin > 2 || col < 0 || col > 2)
            throw new Exception ("Posicao invalida");
    }

    public boolean haMarcaNaPosicao (int lin, int col) throws Exception
    {
        this.validar (lin, col);

        if (this.matriz [lin][col] == null)
            return false;

        return true;
    }

    public Marca getMarcaNaPosicao (int lin, int col) throws Exception
    {
        this.validar (lin, col);

        return this.matriz [lin][col];
    }

    public void setMarcaNaPosicao (Marca mrc, int lin, int col) throws Exception
    {
        this.validar (lin, col);

        if (mrc == null)
            throw new Exception ("Marca ausente");

        this.matriz [lin][col] = mrc;
    }

    @Override
    public String toString ()
    {
        String ret = "\n     1   2   3\n";

        for (int lin = 0; lin < 3; lin++)
        {
            ret += " " + (lin + 1) + "  ";

            for (int col = 0; col < 3; col++)
            {
                if (this.matriz [lin][col] == null)
                    ret += "   ";
                else
                    ret += " " + this.matriz [lin][col] + " ";

                if (col < 2)
                    ret += "|";
            }

            ret += "\n";

            if (lin < 2)
                ret += "    ---+---+---\n";
        }

        return ret;
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

        Tabuleiro tab = (Tabuleiro) obj;

        for (int lin = 0; lin < 3; lin++)
            for (int col = 0; col < 3; col++)
            {
                if (this.matriz [lin][col] == null)
                {
                    if (tab.matriz [lin][col] != null)
                        return false;
                }
                else
                    if (!this.matriz [lin][col].equals (tab.matriz [lin][col]))
                        return false;
            }

        return true;
    }

    @Override
    public int hashCode ()
    {
        int ret = 7;

        for (int lin = 0; lin < 3; lin++)
            for (int col = 0; col < 3; col++)
                if (this.matriz [lin][col] != null)
                    ret = 13 * ret + this.matriz [lin][col].hashCode ();
                else
                    ret = 13 * ret;

        if (ret < 0)
            ret = -ret;

        return ret;
    }

    public Tabuleiro ()
    {}

    // construtor de copia
    public Tabuleiro (Tabuleiro modelo) throws Exception
    {
        if (modelo == null)
            throw new Exception ("Modelo ausente");

        // Marca e imutavel, entao pode ser compartilhada
        for (int lin = 0; lin < 3; lin++)
            for (int col = 0; col < 3; col++)
                this.matriz [lin][col] = modelo.matriz [lin][col];
    }

    @Override
    public Object clone ()
    {
        Tabuleiro ret = null;

        try
        {
            ret = new Tabuleiro (this);
        }
        catch (Exception erro)
        {} // ignorando pois sei que this nao e null

        return ret;
    }
}
