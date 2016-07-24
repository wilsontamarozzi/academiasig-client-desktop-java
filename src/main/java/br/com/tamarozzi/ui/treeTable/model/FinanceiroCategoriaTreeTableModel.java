/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.ui.treeTable.model;

import br.com.tamarozzi.interfaces.MyAbstractTableModel;
import br.com.tamarozzi.model.FinanceiroCategoria;
import br.com.tamarozzi.model.FinanceiroCategoriaGrupo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;

/**
 *
 * @author Wilson
 */
public class FinanceiroCategoriaTreeTableModel extends AbstractTreeTableModel implements MyAbstractTableModel {

    private List<Object> grupos;
    
    private final String[] colNomes = {"Nome", "Tipo"};
    
    private final Class<?>[] colTipo = {String.class, String.class};
    
    public FinanceiroCategoriaTreeTableModel() {
        super(new Object());
        this.grupos = new ArrayList<>(0);
    }
    
    @Override
    public int getColumnCount() {
        return this.colNomes.length;
    }

    @Override
    public String getColumnName(int column) {
        return this.colNomes[column];
    }

    @Override
    public Class<?> getColumnClass(int column) {
        return this.colTipo[column];
    }
    
    @Override
    public Object getValueAt(Object node, int column) {
        if (node instanceof FinanceiroCategoriaGrupo) {
            FinanceiroCategoriaGrupo grupo = (FinanceiroCategoriaGrupo) node;    
            switch (column) {
                case 0:
                    return grupo.getNome();
                case 1:
                    return grupo.getTipo();
            }
        } else if (node instanceof FinanceiroCategoria) {
            FinanceiroCategoria categoria = (FinanceiroCategoria) node;
            switch (column) {
                case 0:
                    return categoria.getNome();
                case 1:
                    return categoria.getTipo();
            }
        }
        return null;
    }

    @Override
    public Object getChild(Object parent, int index) {
        if (parent instanceof FinanceiroCategoriaGrupo) {
            FinanceiroCategoriaGrupo grupo = (FinanceiroCategoriaGrupo) parent;
            return grupo.getCategorias().get(index);
        }
        
        return this.grupos.get(index);
    }

    @Override
    public int getChildCount(Object parent) {
        if (parent instanceof FinanceiroCategoriaGrupo) {
            FinanceiroCategoriaGrupo grupo = (FinanceiroCategoriaGrupo) parent;
            
            if(grupo.getCategorias() == null) {
                return 0;
            } else {
                return grupo.getCategorias().size();
            }
        }
        
        return this.grupos.size();
    }    

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        if(parent instanceof FinanceiroCategoriaGrupo) {
            FinanceiroCategoriaGrupo grupo = (FinanceiroCategoriaGrupo) parent;
            FinanceiroCategoria categoria = (FinanceiroCategoria) child;
            
            if(grupo.getCategorias() == null) {
                return 0;
            } else {
                return grupo.getCategorias().indexOf(categoria);
            }
        }
        
        return 0;
    }

    @Override
    public boolean isLeaf(Object node) {
        return node instanceof FinanceiroCategoria;
    }

    @Override
    public void reload(List<Object> itens) {
        this.grupos = new ArrayList<>(0);
        this.grupos.addAll(itens);
        
        this.modelSupport.fireNewRoot();
    }

    @Override
    public void addItem(Object item) {
        if(item instanceof FinanceiroCategoriaGrupo) {
            this.grupos.add(item);
            //this.grupos.sort(Comparator.comparing(g -> ((FinanceiroCategoriaGrupo) g).getNome()));
        }
        
        if(item instanceof FinanceiroCategoria) {
            FinanceiroCategoria categoria = (FinanceiroCategoria) item;
            
            this.grupos.forEach(g -> {
                FinanceiroCategoriaGrupo grupo = (FinanceiroCategoriaGrupo) g;
                
                if(grupo.getUUID().equals(categoria.getGrupoCategoriaUUID())) {
                    grupo.getCategorias().add(categoria);
                    //grupo.getCategorias().sort(Comparator.comparing(c -> c.getNome()));
                }
            });
        }
        
        this.modelSupport.fireNewRoot();
    }

    @Override
    public void addItens(List<Object> itens) {
        this.grupos.addAll(itens);
        
        this.modelSupport.fireNewRoot();
    }

    @Override
    public void removeItem(Object item) {
        this.grupos.remove(item);
        
        this.modelSupport.fireNewRoot();
    }

    @Override
    public void removeItens(List<Object> itens) {
        this.grupos.removeAll(itens);
        
        this.modelSupport.fireNewRoot();
    }

    @Override
    public Object getItemAt(int index) {
        return this.grupos.get(index);
    }  
}
