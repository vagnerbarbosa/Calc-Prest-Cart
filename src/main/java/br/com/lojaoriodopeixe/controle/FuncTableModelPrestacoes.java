package br.com.lojaoriodopeixe.controle;

import br.com.lojaoriodopeixe.modelo.ParcelaPrestacoes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Implementação de Table Model.
 * 
 * @author Eric Yuzo
 */
public class FuncTableModelPrestacoes extends AbstractTableModel {
    /* Lista de Sócios que representam as linhas. */
    private List<ParcelaPrestacoes> linhas;

    /* Array de Strings com o nome das colunas. */
    private String[] colunas = new String[] {
            "Nº", "Parcela","Total Parcelado","Total Geral"};


    /* Cria um FuncionarioTableModel vazio. */
    public FuncTableModelPrestacoes() {
        linhas = new ArrayList<ParcelaPrestacoes>();
    }

    /* Cria um FuncionarioTableModel carregado com
     * a lista de sócios especificada. */
    public FuncTableModelPrestacoes(List<ParcelaPrestacoes> listaDeParcelas) {
        linhas = new ArrayList<ParcelaPrestacoes>(listaDeParcelas);
    }


    /* Retorna a quantidade de colunas. */
    @Override
    public int getColumnCount() {
        // Está retornando o tamanho do array "colunas".
        // Mas como o array é fixo, vai sempre retornar 4.
        return colunas.length;
    }

    /* Retorna a quantidade de linhas. */
    @Override
    public int getRowCount() {
        // Retorna o tamanho da lista de sócios.
        return linhas.size();
    }

    /* Retorna o nome da coluna no índice especificado.
     * Este método é usado pela JTable para saber o texto do cabeçalho. */
    @Override
    public String getColumnName(int columnIndex) {
        // Retorna o conteúdo do Array que possui o nome das colunas
        // no índice especificado.
        return colunas[columnIndex];
    };

    /* Retorna a classe dos elementos da coluna especificada.
     * Este método é usado pela JTable na hora de definir o editor da célula. */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // Retorna a classe referente a coluna especificada.
        // Aqui é feito um switch para verificar qual é a coluna
        // e retornar o tipo adequado. As colunas são as mesmas
        // que foram especificadas no array "colunas".
        switch (columnIndex) {

        case 0: // Primeira coluna é o número da parcela.
            return Long.class;
        case 1: // Segunda coluna é o valor da parcela.
            return Long.class;
        case 2: // Terceira coluna é a totalGeral.
            return Long.class;
        case 3: // Quarta coluna é a totalParcelado.
            return Long.class;            
        default:
            // Se o índice da coluna não for válido, lança um
            // IndexOutOfBoundsException (Exceção de índice fora dos limites).
            // Não foi necessário verificar se o índice da linha é inválido,
            // pois o próprio ArrayList lança a exceção caso seja inválido.
                System.out.println("O índice informado não existe! 1");                    
                    return null;
        }
    }

    /* Retorna o valor da célula especificada
     * pelos índices da linha e da coluna. */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // Pega o sócio da linha especificada.       
        ParcelaPrestacoes parc = linhas.get(rowIndex);

        // Retorna o campo referente a coluna especificada.
        // Aqui é feito um switch para verificar qual é a coluna
        // e retornar o campo adequado. As colunas são as mesmas
        // que foram especificadas no array "colunas".
        switch (columnIndex) {
        case 0: // Primeira coluna é o Nº.
            return parc.getNumero();
        case 1: // Segunda coluna é a parcela.
            return parc.getParcela();
        case 2: // Terceira coluna é a totalGeral.
            return parc.getTotalGeral();
        case 3: // Quarta coluna é a totalGeral.
            return parc.getTotalParcelado();
        default:
            // Se o índice da coluna não for válido, lança um
            // IndexOutOfBoundsException (Exceção de índice fora dos limites).
            // Não foi necessário verificar se o índice da linha é inválido,
            // pois o próprio ArrayList lança a exceção caso seja inválido.
                    System.out.println("O índice informado não existe! 2");    
                    return null;
        }

    }

    /* Retorna um valor booleano que define se a célula em questão
     * pode ser editada ou não.
     * Este método é utilizado pela JTable na hora de definir o editor da célula.
     * Neste caso, estará sempre retornando false, não permitindo que nenhuma
     * célula seja editada. */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }


    ////////////////////////////////////////////////////////////
    // Os métodos declarados até aqui foram as implementações //
    // de TableModel, que são continuamente utilizados        //
    // pela JTable para definir seu comportamento,            //
    // por isso o nome Table Model (Modelo da Tabela).        //
    //                                                        //
    // A partir de agora, os métodos criados serão            //
    // particulares desta classe. Eles serão úteis            //
    // em algumas situações.                                  //
    ////////////////////////////////////////////////////////////


    /* Retorna o sócio da linha especificada. */
    public ParcelaPrestacoes getParcela(int indiceLinha) {
            if(indiceLinha < linhas.size()){
                return linhas.get(indiceLinha);
            }
        return null;
    }

    /* Adiciona um registro. */
    public void addParcela(ParcelaPrestacoes parc) {
        // Adiciona o registro.
        linhas.add(parc);

        // Pega a quantidade de registros e subtrai um para achar
        // o último índice. É preciso subtrair um, pois os índices
        // começam pelo zero.
        int ultimoIndice = getRowCount() - 1;

        // Reporta a mudança. O JTable recebe a notificação
        // e se redesenha permitindo que visualizemos a atualização.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    /* Remove a linha especificada. */
    public void removeFuncionario(int indiceLinha) {
        // Remove o sócio da linha especificada.  
            if(indiceLinha < linhas.size()){
                linhas.remove(indiceLinha);

        // Reporta a mudança. O JTable recebe a notificação
        // e se redesenha permitindo que visualizemos a atualização.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
            }

    }

    /* Adiciona uma lista de sócios ao final dos registros. */
    public void addListaDeParcelas(List<ParcelaPrestacoes> parcelas) {
        // Pega o tamanho antigo da tabela.
        int tamanhoAntigo = getRowCount();

        // Adiciona os registros.
        linhas.addAll(parcelas);

        // Reporta a mudança. O JTable recebe a notificação
        // e se redesenha permitindo que visualizemos a atualização.
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    /* Remove todos os registros. */
    public void limpar() {
        // Remove todos os elementos da lista de sócios.
        linhas.clear();

        // Reporta a mudança. O JTable recebe a notificação
        // e se redesenha permitindo que visualizemos a atualização.
        fireTableDataChanged();
    }

    /* Verifica se este table model está vazio. */
    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    
    public Object getObject(int index) {
        return linhas.get(index);
    }  
}
