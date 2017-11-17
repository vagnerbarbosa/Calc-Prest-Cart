package br.com.lojaoriodopeixe.modelo;

/**
 *
 * @author vagner
 */
public class ParcelaPrestacaoReversa {
    
    private String numeroParcela;
    private String valorParcela;
    private String valorEntradaSugerida;

    public ParcelaPrestacaoReversa() {
    }

    public String getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(String numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public String getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(String valorParcela) {
        this.valorParcela = valorParcela;
    }

    public String getValorEntradaSugerida() {
        return valorEntradaSugerida;
    }

    public void setValorEntradaSugerida(String valorEntradaSugerida) {
        this.valorEntradaSugerida = valorEntradaSugerida;
    }

    @Override
    public String toString() {
        return "ParcelaPrestacaoReversa{" + "numeroParcela=" + numeroParcela + ", valorParcela=" + valorParcela + ", valorEntradaSugerida=" + valorEntradaSugerida + '}';
    }
    
}
