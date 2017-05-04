package pack;

public class MyTable {
    public void packColumn(javax.swing.JTable table, int vColIndex, int margin) {
        javax.swing.table.DefaultTableColumnModel colModel = (javax.swing.table.DefaultTableColumnModel)table.getColumnModel();
        javax.swing.table.TableColumn col = colModel.getColumn(vColIndex);
        int width = 0;
    
        // Get width of column header
        javax.swing.table.TableCellRenderer renderer = col.getHeaderRenderer();
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        java.awt.Component comp = renderer.getTableCellRendererComponent(
            table, col.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;
    
        // Get maximum width of column data
        for (int r=0; r<table.getRowCount(); r++) {
            renderer = table.getCellRenderer(r, vColIndex);
            comp = renderer.getTableCellRendererComponent(
                table, table.getValueAt(r, vColIndex), false, false, r, vColIndex);
            width = Math.max(width, comp.getPreferredSize().width);
        }
    
        // Add margin
        width += 2*margin;
    
        // Set the width
        col.setPreferredWidth(width);
    }
    
}