package br.com.lojaoriodopeixe.modelo;

/**
 * Classe de entidade ParcelaParaAVista.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 10/11/2017
 *
 * @version 1.0
 */
public class ParcelaParaAVista {
    
    private String valorParcela;
    private String numeroParcela;

    public ParcelaParaAVista() {
    }

    public String getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(String valorParcela) {
        this.valorParcela = valorParcela;
    }

    public String getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(String numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    @Override
    public String toString() {
        return "Parcela{" + "valorParcela=" + valorParcela + ", numeroParcela=" + numeroParcela + '}';
    }    
    
}
