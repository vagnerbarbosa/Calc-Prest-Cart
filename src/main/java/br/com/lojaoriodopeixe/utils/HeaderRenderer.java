package br.com.lojaoriodopeixe.utils;

import javax.swing.table.*;
import java.awt.Component;
import javax.swing.JTable;

/**
 * Implementação de TableCellRenderer.
 *
 * @author Vagner Barbosa (contato@vagnerbarbosa.com)
 *
 * @since 10/11/2017
 *
 * @version 1.0
 */
public class HeaderRenderer implements TableCellRenderer {

    DefaultTableCellRenderer renderer;

    public HeaderRenderer(JTable table, int orientation) {
        renderer = (DefaultTableCellRenderer)
            table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(orientation);
    }

    @Override
    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int col) {
        return renderer.getTableCellRendererComponent(
            table, value, isSelected, hasFocus, row, col);
    }
}
