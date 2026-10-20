package jogador;

public class Marca
{
    private char simbolo;

    public Marca (char smb) throws Exception
    {
        // validar smb, garantindo que ele
        // seja 'X' ou 'O' e coloca-lo em
        // simbolo
        // dar excecao em caso de valor invalido
        if (smb != 'X' && smb != 'O')
            throw new Exception ("Simbolo invalido");

        this.simbolo = smb;
    }

    public char getSimbolo ()
    {
        return this.simbolo;
    }

    @Override
    public String toString ()
    {
        return ""+simbolo;
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

        Marca mrc = (Marca) obj;

        if (this.simbolo != mrc.simbolo)
            return false;

        return true;
    }

    @Override
    public int hashCode ()
    {
        int ret = 7;

        ret = 13 * ret + Character.valueOf (this.simbolo).hashCode ();

        if (ret < 0)
            ret = -ret;

        return ret;
    }
}
