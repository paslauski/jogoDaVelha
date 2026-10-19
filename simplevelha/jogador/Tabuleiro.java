package jogador;

public class Tabuleiro implements Cloneable {
    private Marca[][] matriz = new Marca[3][3];

    public Tabuleiro() {
    }

    private void validarPosicao(int lin, int col) throws Exception {
        if (lin < 0 || lin > 2 || col < 0 || col > 2)
            throw new Exception("Posicao invalida.");
    }

    public boolean haMarcaNaPosicao(int lin, int col) throws Exception {
        validarPosicao(lin, col);
        return matriz[lin][col] != null;
    }

    public Marca getMarcaNaPosicao(int lin, int col) throws Exception {
        validarPosicao(lin, col);
        return matriz[lin][col];
    }

    public void setMarcaNaPosicao(Marca mrc, int lin, int col) throws Exception {
        validarPosicao(lin, col);
        if (mrc == null) throw new Exception("Marca invalida.");
        if (matriz[lin][col] != null) throw new Exception("Essa posicao ja esta ocupada.");
        matriz[lin][col] = mrc;
    }

    @Override
    public String toString() {
        String texto = "";
        for (int lin = 0; lin < 3; lin++) {
            for (int col = 0; col < 3; col++) {
                texto += matriz[lin][col] == null ? " " : matriz[lin][col].toString();
                if (col < 2) texto += " | ";
            }
            if (lin < 2) texto += "\n---------\n";
        }
        return texto;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Tabuleiro)) return false;
        Tabuleiro outro = (Tabuleiro) obj;
        for (int lin = 0; lin < 3; lin++)
            for (int col = 0; col < 3; col++)
                if (matriz[lin][col] == null ? outro.matriz[lin][col] != null
                    : !matriz[lin][col].equals(outro.matriz[lin][col])) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int resultado = 1;
        for (int lin = 0; lin < 3; lin++)
            for (int col = 0; col < 3; col++)
                resultado = 31 * resultado + (matriz[lin][col] == null ? 0 : matriz[lin][col].hashCode());
        return resultado;
    }

    public Tabuleiro(Tabuleiro modelo) throws Exception {
        if (modelo == null) throw new Exception("Tabuleiro invalido.");
        for (int lin = 0; lin < 3; lin++)
            for (int col = 0; col < 3; col++)
                if (modelo.matriz[lin][col] != null)
                    matriz[lin][col] = new Marca(modelo.matriz[lin][col].getSimbolo());
    }

    @Override
    public Object clone() {
        try {
            return new Tabuleiro(this);
        } catch (Exception e) {
            return null;
        }
    }
}
