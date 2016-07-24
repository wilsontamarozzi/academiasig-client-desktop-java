/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.ui.treeTable;

import br.com.tamarozzi.model.FinanceiroCategoriaGrupo;
import br.com.tamarozzi.ui.treeTable.model.FinanceiroCategoriaTreeTableModel;
import br.com.tamarozzi.ui.treeTable.renderer.FinanceiroCategoriaTreeTableCellRenderer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.TreePath;
import org.jdesktop.swingx.JXTreeTable;

/**
 *
 * @author Wilson
 */
public class FinanceiroCategoriaTreeTable extends JXTreeTable {
    
    private final FinanceiroCategoriaTreeTableModel modelo;
    private final FinanceiroCategoriaTreeTableCellRenderer renderer;

    public FinanceiroCategoriaTreeTable() {
        this.modelo = new FinanceiroCategoriaTreeTableModel();
        this.renderer = new FinanceiroCategoriaTreeTableCellRenderer();
        
        initComponents();
    }
    
    private void initComponents() {
        this.setTreeTableModel(this.modelo);
        this.setDefaultRenderer(Object.class, renderer);
        
        this.setLeafIcon(null);
        this.setClosedIcon(null);
        this.setOpenIcon(null);
    }

    public Object getGrupoCategoriaSelected() {
        TreePath path = this.getPathForRow(this.getSelectedRow());
        Object node = path.getLastPathComponent();

	return node;
    }

    public List<Object> getGrupoCategoriasSelected() {
        
        List<Object> itens = new ArrayList<>(0);
        
        int rows[] = this.getSelectedRows();
        
        for(int i = 0; i < rows.length; i++) {
            TreePath path = this.getPathForRow(rows[i]);
            itens.add(path.getLastPathComponent());
        }
        
	return itens;
    }

    public void reload(List<FinanceiroCategoriaGrupo> gruposCategoria) {
        this.modelo.reload((List<Object>) (Object) gruposCategoria);
        this.expandAll();
    }
    
    public void addItem(Object obj) {
        this.modelo.addItem(obj);
        this.expandAll();
    }
    
    public void addItens(List<Object> objs) {
        this.modelo.addItens(objs);
        this.expandAll();
    }
    
    public void removeItem(Object obj) {
        this.modelo.removeItem(obj);
        this.expandAll();
    }
    
    public void removeItens(List<Object> objs) {
        this.modelo.removeItens(objs);
        this.expandAll();
    }
}
