package br.com.lojaoriodopeixe.modelo;

/**
 * Classe de entidade ParcelaPrestacoes.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 10/11/2017
 *
 * @version 1.0
 */
public class ParcelaPrestacoes {
    
    private String numero;
    private String parcela;
    private String totalParcelado;
    private String totalGeral;

    public ParcelaPrestacoes() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public String getTotalParcelado() {
        return totalParcelado;
    }

    public void setTotalParcelado(String totalParcelado) {
        this.totalParcelado = totalParcelado;
    }

    public String getTotalGeral() {
        return totalGeral;
    }

    public void setTotalGeral(String totalGeral) {
        this.totalGeral = totalGeral;
    }

    @Override
    public String toString() {
        return "Parcela{" + "numero=" + numero + ", parcela=" + parcela + ", totalParcelado=" + totalParcelado + ", totalGeral=" + totalGeral + '}';
    }
      
}
