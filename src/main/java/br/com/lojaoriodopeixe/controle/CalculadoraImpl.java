package br.com.lojaoriodopeixe.controle;

import br.com.lojaoriodopeixe.modelo.*;
import java.text.DecimalFormat;
import java.math.BigDecimal;
import java.util.*;

/**
 * Classe de controle CalculadoraImpl.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 10/11/2017
 *
 * @version 1.0
 */
public class CalculadoraImpl implements Calculadora {
    
    private static final double CET_MAXVALUE = 10000.00;
    private static final double CET_PRECISION = 0.00001;
    private static final double DAY = 1000 * 60 * 60 * 24;    

    @Override
    public List<ParcelaParaAVista> calculoFinanceiro(Double total, Double i, Integer n) {
        DecimalFormat decFormat = new DecimalFormat("¤ #,###,##0.00");
        List<ParcelaParaAVista> parcelas = new ArrayList<>();
        Double PMT, PV;
        Integer x;        
        for (x = 1; x <= n+2; x++) {
            ParcelaParaAVista parcela = new ParcelaParaAVista();
            PMT = total / x;
            PV = PMT * ((Math.pow((1 + (i/100)), x) - 1) / ((i/100) * Math.pow((1 + (i/100)), x)));
            String dx = decFormat.format(PV);
            if (x < 10){
            parcela.setNumeroParcela("0" + String.valueOf(x) + "ª Parcela(s)");
            } else {
                parcela.setNumeroParcela(String.valueOf(x) + "ª Parcela(s)");
            }
            parcela.setValorParcela(dx);
            parcelas.add(parcela);
        }
        return parcelas;
    }
    
    @Override
    public List<ParcelaPrestacaoReversa> calculoReversoEntrada(Double total, Double i, Integer n, double PMT) {
        DecimalFormat decFormat = new DecimalFormat("¤ #,###,##0.00");
        List<ParcelaPrestacaoReversa> parcelas = new ArrayList<>();
        Double PV, entrada;
        Integer x;        
        for (x = 1; x <= n+4; x++) {
            ParcelaPrestacaoReversa parcela = new ParcelaPrestacaoReversa();
            PV = PMT * ((Math.pow((1 + (i/100)), x) - 1) / ((i/100) * Math.pow((1 + (i/100)), x)));
            entrada = total - PV;
            String dx = decFormat.format(PV);
            String dy = decFormat.format(entrada);
            String dt = decFormat.format(PMT);
            
            if (entrada > 0) {
            if (x < 10) {
            parcela.setNumeroParcela("0" + String.valueOf(x) + "ª Parcela(s)");
            } else {
            parcela.setNumeroParcela(String.valueOf(x) + "ª Parcela(s)");
            }
            parcela.setValorEntradaSugerida(dy);
            parcela.setValorParcela(dt);
            parcela.setValorTotalPago(decFormat.format( entrada + (x * PMT) ));
            parcelas.add(parcela);
            }
        }
        return parcelas;
    }

    /**
     * Obtém o valor da prestação.
     * @param value Valor a ser financiado
     * @param interest Percentual de juros a que se sujeira o valor presente
     * @param months Quantidade de parcelas do financiamento
     * @param inputValue Valor de entrada a ser abatido do valor do financiamento
     * @return Valor da parcela do financiamento
    **/
    @Override
    public BigDecimal calculoPrestacoes(Double value, Double interest, Integer months, Double inputValue) {
        double realValue = value - inputValue;        
        realValue = realValue/((Math.pow((interest/100)+1, months)-1)/(Math.pow((interest/100)+1, months)*(interest/100)));        
        BigDecimal preciseValue = new BigDecimal(realValue);
        return preciseValue.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    @Override
    public BigDecimal calculoCetReal(double interest) {
        double result = 0;
        result = (Math.pow(1 + (interest / 100), 12) - 1) * 100;
        BigDecimal preciseValue = new BigDecimal(result);
        return preciseValue.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * Calculo do custo efetivo total anual.
     *
     * @param fc0 FC0, valor a ser financiado.
     * @param fcj FCj, Percentual de juros a que se sujeira o valor presente
     * @param n N, número de parcelas mensais
     * @param d0 D0 data do contrato (liberacao de recursos)
     * @param dj0 DJ0 data da liberacao da primeira parcela
     * @return Custo Efetivo Total (CET)
     */
    @Override
    public double calculoCET(double fc0, double fcj, int n, Date d0, Date dj0) {
        Calendar c = Calendar.getInstance();

        double cet = 0.0;

        while(true) {

            double total = 0.0;

            for(int j = 0; j < n; j++) {
                Date dj = dj0;
                if(j != 0) {
                    c.setTime(dj0);
                    c.add(Calendar.MONTH, j);
                    dj = c.getTime();
                }
                double days = (dj.getTime() - d0.getTime()) / DAY;
                total += fcj / Math.pow(1.0 + cet, days/365.0);
            }

            cet += CET_PRECISION;

            if(cet >= CET_MAXVALUE) {
                return -1.0;
            }
            if(total - fc0 <= 0) {
                break;
            }
            else {
                cet *= total / fc0;
            }
        }
        return cet * 100.0;
    }      

    @Override
    public List<ParcelaPrestacaoReversa> calculoEntradaIgualParcela(Double PV, Integer n, Double i) {
        double PMT = 0, CF;
        List<ParcelaPrestacaoReversa> parcelas = new ArrayList<>();
        DecimalFormat decFormat = new DecimalFormat("¤ #,###,##0.00");
        
        for (int x = 1; x < n+4; x++) {
        if (x > 1) {    
        CF = (i/100)/(1-(1/Math.pow(1+(i/100), x-1)));        
        PMT = (PV * CF)/(1+CF);
        ParcelaPrestacaoReversa parcela = new ParcelaPrestacaoReversa();
        if ((x-1) < 10) {
        parcela.setNumeroParcela("01 + " + "0" + String.valueOf(x-1) + " Parcela(s)");
        } else {
        parcela.setNumeroParcela("01 + " + String.valueOf(x-1) + " Parcela(s)");    
        }
        parcela.setValorEntradaSugerida(decFormat.format( PMT ));
        parcela.setValorParcela(decFormat.format( PMT ));
        parcela.setValorTotalPago(decFormat.format((x * PMT) ));
        parcelas.add(parcela);        
        } 
        

        
        }
        return parcelas;        
    }

}
