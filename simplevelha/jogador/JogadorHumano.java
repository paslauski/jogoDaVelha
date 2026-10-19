package jogador;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JogadorHumano implements Jogador {
    private Marca marca = null;
    private Tabuleiro tabDoJogo = null;
    private BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

    public JogadorHumano(Tabuleiro tab) throws Exception {
        tabDoJogo = tab;
        while (marca == null) {
            System.out.print("Escolha sua marca (X/O): ");
            String resposta = teclado.readLine().trim().toUpperCase();
            try {
                marca = new Marca(resposta.charAt(0));
            } catch (Exception e) {
                System.out.println("Digite X ou O.");
            }
        }
    }

    public JogadorHumano(Tabuleiro tab, Marca mrc) throws Exception {
        if (tab == null || mrc == null) throw new Exception("Jogador invalido.");
        tabDoJogo = tab;
        marca = mrc;
    }

    public Marca getMarca() {
        return marca;
    }

    public void facaSuaJogada() {
        while (true) {
            try {
                System.out.println(tabDoJogo);
                System.out.println("Sua vez (" + marca + ")");
                System.out.print("Linha (1 a 3): ");
                int lin = Integer.parseInt(teclado.readLine()) - 1;
                System.out.print("Coluna (1 a 3): ");
                int col = Integer.parseInt(teclado.readLine()) - 1;
                tabDoJogo.setMarcaNaPosicao(marca, lin, col);
                return;
            } catch (Exception e) {
                System.out.println("Jogada invalida. Tente de novo.");
            }
        }
    }

    public void vcGanhou() {
        System.out.println(tabDoJogo);
        System.out.println("Voce ganhou!");
    }

    public void vcPerdeu() {
        System.out.println(tabDoJogo);
        System.out.println("Voce perdeu!");
    }

    public void deuVelha() {
        System.out.println(tabDoJogo);
        System.out.println("Deu velha! Empate.");
    }

    public boolean querMaisUmaPartida() {
        try {
            System.out.print("Quer jogar de novo? (s/n): ");
            return teclado.readLine().trim().equalsIgnoreCase("s");
        } catch (Exception e) {
            return false;
        }
    }
}
