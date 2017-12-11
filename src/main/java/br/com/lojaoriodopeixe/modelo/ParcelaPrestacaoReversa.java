package br.com.lojaoriodopeixe.modelo;

/**
 * Classe de entidade ParcelaPrestacaoReversa.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 10/11/2017
 *
 * @version 1.0
 */
public class ParcelaPrestacaoReversa {
    
    private String numeroParcela;
    private String valorParcela;
    private String valorEntradaSugerida;
    private String valorTotalPago;

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

    public String getValorTotalPago() {
        return valorTotalPago;
    }

    public void setValorTotalPago(String valorTotalPago) {
        this.valorTotalPago = valorTotalPago;
    }    

    @Override
    public String toString() {
        return "ParcelaPrestacaoReversa{" + "numeroParcela=" + numeroParcela + ", valorParcela=" + valorParcela + ", valorEntradaSugerida=" + valorEntradaSugerida + ", valorTotalPago=" + valorTotalPago + '}';
    }    
    
}
