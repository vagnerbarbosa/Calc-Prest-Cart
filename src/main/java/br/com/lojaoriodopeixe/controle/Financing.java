package br.com.lojaoriodopeixe.controle;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe para os objetos do tipo Financing, onde serão contidos os métodos
 * para o mesmo.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 07/09/2015
 *
 * @version 0.1
 */
public class Financing {

    public Financing() {
    }
    
    private static final double CET_MAXVALUE = 10000.00;
    private static final double CET_PRECISION = 0.00001;
    private static final double DAY = 1000 * 60 * 60 * 24;

    /**
     * Obtém o valor da prestação.
     * @param value Valor a ser financiado
     * @param interest Percentual de juros a que se sujeira o valor presente
     * @param months Quantidade de parcelas do financiamento
     * @param inputValue Valor de entrada a ser abatido do valor do financiamento
     * @return Valor da parcela do financiamento
    **/
    public BigDecimal getInstalment(Double value, Double interest, Integer months, Double inputValue) {
        double realValue = value - inputValue;        
        realValue = realValue/((Math.pow((interest/100)+1, months)-1)/(Math.pow((interest/100)+1, months)*(interest/100)));        
        BigDecimal preciseValue = new BigDecimal(realValue);
        return preciseValue.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    public BigDecimal getRealCET(double interest) {
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
    public double getCET(double fc0, double fcj, int n, Date d0, Date dj0) {
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

}
