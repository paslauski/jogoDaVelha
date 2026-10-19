import jogador.*;

public class Velha {
    public static void main(String[] args) {
        boolean humanoComeca = (int)(Math.random() * 2) == 0;
        boolean jogarDeNovo;

        do {
            try {
                Tabuleiro tabDoJogo = new Tabuleiro();
                JogadorHumano jogadorH;
                JogadorAutomatico jogadorA;

                if (humanoComeca) {
                    jogadorH = new JogadorHumano(tabDoJogo);
                    jogadorA = new JogadorAutomatico(tabDoJogo,
                        new Marca(jogadorH.getMarca().getSimbolo() == 'X' ? 'O' : 'X'));
                } else {
                    jogadorA = new JogadorAutomatico(tabDoJogo);
                    jogadorH = new JogadorHumano(tabDoJogo,
                        new Marca(jogadorA.getMarca().getSimbolo() == 'X' ? 'O' : 'X'));
                }

                AnalistaSituacional analista = new AnalistaSituacional(tabDoJogo);
                Jogador jogador = humanoComeca ? jogadorH : jogadorA;

                while (!analista.fimDeJogo()) {
                    jogador.facaSuaJogada();
                    System.out.println(tabDoJogo);
                    jogador = jogador == jogadorH ? jogadorA : jogadorH;
                }

                Marca vencedor = analista.getMarcaDoVencedor();
                if (vencedor == null) jogadorH.deuVelha();
                else if (vencedor.equals(jogadorH.getMarca())) jogadorH.vcGanhou();
                else jogadorH.vcPerdeu();

                humanoComeca = !humanoComeca;
                jogarDeNovo = jogadorH.querMaisUmaPartida();
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
                jogarDeNovo = false;
            }
        } while (jogarDeNovo);
    }
}
