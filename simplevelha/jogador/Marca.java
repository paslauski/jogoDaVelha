package jogador;

public class Marca {
    private char simbolo;

    public Marca(char smb) throws Exception {
        if (smb != 'X' && smb != 'O')
            throw new Exception("A marca deve ser X ou O.");
        simbolo = smb;
    }

    public char getSimbolo() {
        return simbolo;
    }

    @Override
    public String toString() {
        return "" + simbolo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Marca)) return false;
        return simbolo == ((Marca) obj).simbolo;
    }

    @Override
    public int hashCode() {
        return Character.hashCode(simbolo);
    }
}
