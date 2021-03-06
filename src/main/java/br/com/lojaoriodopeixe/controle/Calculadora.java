package br.com.lojaoriodopeixe.controle;

import br.com.lojaoriodopeixe.modelo.*;
import java.math.BigDecimal;
import java.util.*;

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
    public List<ParcelaPrestacaoReversa> calculoReversoEntrada(Double total, Double i, Integer n, double p);
    public List<ParcelaPrestacaoReversa> calculoEntradaIgualParcela(Double pv, Integer n, Double i);
    public BigDecimal calculoPrestacoes(Double value, Double interest, Integer months, Double inputValue);
    public BigDecimal calculoCetReal(double interest);
    public double calculoCET(double fc0, double fcj, int n, Date d0, Date dj0);
    
}
