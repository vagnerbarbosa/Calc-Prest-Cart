package br.com.lojaoriodopeixe.controle;

import br.com.lojaoriodopeixe.modelo.ParcelaParaAVista;
import br.com.lojaoriodopeixe.modelo.ParcelaPrestacaoReversa;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
            parcela.setNumeroParcela("0" + String.valueOf(x) + "ª Parcela");
            } else {
                parcela.setNumeroParcela(String.valueOf(x) + "ª Parcela");
            }
            parcela.setValorParcela(dx);
            parcelas.add(parcela);
        }
        return parcelas;
    }
    
    public List<ParcelaPrestacaoReversa> calculoReverso(Double total, Double i, Integer n, double PMT) {
        DecimalFormat decFormat = new DecimalFormat("¤ #,###,##0.00");
        List<ParcelaPrestacaoReversa> parcelas = new ArrayList<>();
        Double PV, entrada;
        Integer x;        
        for (x = 1; x <= n; x++) {
            ParcelaPrestacaoReversa parcela = new ParcelaPrestacaoReversa();
            PV = PMT * ((Math.pow((1 + (i/100)), x) - 1) / ((i/100) * Math.pow((1 + (i/100)), x)));
            entrada = total - PV;
            String dx = decFormat.format(PV);
            String dy = decFormat.format(entrada);
            String dt = decFormat.format(PMT);
            
            if (entrada > 0) {
            if (x < 10) {
            parcela.setNumeroParcela("0" + String.valueOf(x) + "ª Parcela");
            } else {
            parcela.setNumeroParcela(String.valueOf(x) + "ª Parcela");
            }
            parcela.setValorEntradaSugerida(dy);
            parcela.setValorParcela(dt);
            parcelas.add(parcela);
            }
        }
        return parcelas;
    }    

}
