package jogador;

import java.io.*;

public class JogadorHumano implements Jogador
{
    private Marca          marca     = null;
    private Tabuleiro      tabDoJogo = null;
    private BufferedReader teclado   = new BufferedReader
                                        (new InputStreamReader
                                        (System.in));

    public JogadorHumano (Tabuleiro tab) throws Exception
    {
        if (tab == null)
            throw new Exception ("Tabuleiro ausente");

        this.tabDoJogo = tab;

        // pedir para o jogador escolher
        // sua marca e permitir digitacao
        // intanciando marca
        while (this.marca == null)
        {
            System.out.print ("Com qual marca quer jogar (X ou O)? ");

            String resp = this.lerLinha ().trim ().toUpperCase ();

            if (resp.equals ("X") || resp.equals ("O"))
                this.marca = new Marca (resp.charAt (0));
            else
                System.out.println ("Marca invalida! Digite X ou O.");
        }
    }

    public JogadorHumano (Tabuleiro tab, Marca mrc) throws Exception
    {
        if (tab == null)
            throw new Exception ("Tabuleiro ausente");

        if (mrc == null)
            throw new Exception ("Marca ausente");

        this.tabDoJogo = tab;
        this.marca     = mrc;
    }

    // le uma linha do teclado; se a entrada acabar, interrompe o jogo
    private String lerLinha ()
    {
        try
        {
            String ret = this.teclado.readLine ();

            if (ret == null)
                throw new IllegalStateException ("Entrada encerrada");

            return ret;
        }
        catch (IOException erro)
        {
            throw new IllegalStateException ("Erro de leitura");
        }
    }

    // pergunta um numero de 1 a 3; devolve de 0 a 2
    private int lerPosicao (String pergunta)
    {
        while (true)
        {
            System.out.print (pergunta);

            try
            {
                int n = Integer.parseInt (this.lerLinha ().trim ());

                if (n >= 1 && n <= 3)
                    return n - 1;
            }
            catch (NumberFormatException erro)
            {}

            System.out.println ("Valor invalido! Digite 1, 2 ou 3.");
        }
    }

    public Marca getMarca ()
    {
        return this.marca;
    }

    public void facaSuaJogada ()
    {
        System.out.println (this.tabDoJogo);
        System.out.println ("Sua vez! Voce joga com " + this.marca);

        try
        {
            while (true)
            {
                int lin = this.lerPosicao ("Linha  (1-3): ");
                int col = this.lerPosicao ("Coluna (1-3): ");

                if (!this.tabDoJogo.haMarcaNaPosicao (lin, col))
                {
                    this.tabDoJogo.setMarcaNaPosicao (this.marca, lin, col);
                    return;
                }

                System.out.println ("Essa posicao ja esta ocupada! Escolha outra.");
            }
        }
        catch (Exception erro)
        {} // ignorando pois lin e col sao sempre validos (0 a 2)
    }

    public void vcGanhou ()
    {
        System.out.println (this.tabDoJogo);
        System.out.println ("Parabens, voce ganhou!");
    }

    public void vcPerdeu ()
    {
        System.out.println (this.tabDoJogo);
        System.out.println ("Voce perdeu! Mais sorte na proxima.");
    }

    public void deuVelha ()
    {
        System.out.println (this.tabDoJogo);
        System.out.println ("Deu velha! Empate.");
    }

    public boolean querMaisUmaPartida ()
    {
        while (true)
        {
            System.out.print ("\nQuer jogar mais uma partida (S/N)? ");

            try
            {
                String resp = this.lerLinha ().trim ().toUpperCase ();

                if (resp.equals ("S"))
                    return true;

                if (resp.equals ("N"))
                    return false;

                System.out.println ("Responda S ou N!");
            }
            catch (IllegalStateException erro)
            {
                return false; // entrada encerrada
            }
        }
    }
}
