package br.com.lojaoriodopeixe.controle;

import br.com.lojaoriodopeixe.modelo.ParcelaParaAVista;
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
        for (x = 1; x <= n; x++) {
            ParcelaParaAVista parcela = new ParcelaParaAVista();
            PMT = total / x;
            PV = PMT * ((Math.pow((1 + (i/100)), x) - 1) / ((i/100) * Math.pow((1 + (i/100)), x)));
            String dx = decFormat.format(PV);
            parcela.setNumeroParcela(String.valueOf(x));
            parcela.setValorParcela(dx);
            parcelas.add(parcela);
        }
        return parcelas;
    }

}
