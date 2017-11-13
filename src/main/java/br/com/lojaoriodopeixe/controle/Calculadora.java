package br.com.lojaoriodopeixe.controle;

import br.com.lojaoriodopeixe.modelo.ParcelaParaAVista;
import java.util.List;

/**
 * Classe de Interface Calculadora.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 10/11/2017
 *
 * @version 1.0
 */
public interface Calculadora {
    
    /**
     *
     * @param valorVista
     * @param taxaJuros
     * @param numeroParcelas
     * @return 
     */
    public List<ParcelaParaAVista> calculoFinanceiro(Double valorVista, Double taxaJuros,Integer numeroParcelas);
    
}
