/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.ui.panel;

import br.com.tamarozzi.controller.FinanceiroCategoriaController;
import br.com.tamarozzi.interfaces.MyAbstractPanelModel;
import br.com.tamarozzi.interfaces.Observable;
import br.com.tamarozzi.interfaces.Observer;
import br.com.tamarozzi.model.FinanceiroCategoria;
import br.com.tamarozzi.model.FinanceiroCategoriaGrupo;
import br.com.tamarozzi.ui.frame.FrmCadastroFinanceiroCategoria;
import br.com.tamarozzi.ui.frame.FrmCadastroFinanceiroCategoriaGrupo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Panda
 */
public class PanelFinanceiroCategoria extends javax.swing.JPanel implements MyAbstractPanelModel, Observable, Observer {

    private List<Observer> observers;
    
    private FinanceiroCategoriaController lancamentoCategoriaController;
    
    private List<String> listCampoFiltro;
    
    private FrmCadastroFinanceiroCategoriaGrupo frmCadastroLancamentoCategoriaGrupo;
    
    private FrmCadastroFinanceiroCategoria frmCadastroLancamentoCategoria;
    
    /**
     * Creates new form PanelLancamentoCategoria
     */
    public PanelFinanceiroCategoria() {
        preInitComponents();
        initComponents();
    }
    
    private void preInitComponents() {
        this.observers = new ArrayList<>();
        
        //Filters
        this.listCampoFiltro = Arrays.asList("search", "nome");
        
        //Controllers
        this.lancamentoCategoriaController = new FinanceiroCategoriaController();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMenu = new javax.swing.JPopupMenu();
        mnuItemCategoriaGrupo = new javax.swing.JMenuItem();
        mnuItemCategoria = new javax.swing.JMenuItem();
        lblPesquisa = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        toolBar = new javax.swing.JToolBar();
        btnNovo = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnEditar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnExcluir = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnPesquisar = new javax.swing.JButton();
        lblEm = new javax.swing.JLabel();
        cbxEm = new javax.swing.JComboBox<>();
        spTable = new javax.swing.JScrollPane();
        tableLancamentoCategoria = new br.com.tamarozzi.ui.treeTable.FinanceiroCategoriaTreeTable();

        mnuItemCategoriaGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo-icon.png"))); // NOI18N
        mnuItemCategoriaGrupo.setText("Grupo de Categoria");
        mnuItemCategoriaGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemCategoriaGrupoActionPerformed(evt);
            }
        });
        popMenu.add(mnuItemCategoriaGrupo);

        mnuItemCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo-icon.png"))); // NOI18N
        mnuItemCategoria.setText("Categoria");
        mnuItemCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemCategoriaActionPerformed(evt);
            }
        });
        popMenu.add(mnuItemCategoria);

        setName("Categorias"); // NOI18N

        lblPesquisa.setText("Pesquisa:");

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo-icon.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setFocusable(false);
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNovoMousePressed(evt);
            }
        });
        toolBar.add(btnNovo);
        toolBar.add(jSeparator1);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar-icon.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnEditar.setFocusable(false);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        toolBar.add(btnEditar);
        toolBar.add(jSeparator2);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir-icon.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setEnabled(false);
        btnExcluir.setFocusable(false);
        btnExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        toolBar.add(btnExcluir);
        toolBar.add(jSeparator3);

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar-icon.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.setFocusable(false);
        btnPesquisar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnPesquisar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        toolBar.add(btnPesquisar);

        lblEm.setText("Em:");

        cbxEm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Nome" }));
        cbxEm.setMinimumSize(new java.awt.Dimension(125, 20));
        cbxEm.setPreferredSize(new java.awt.Dimension(125, 20));

        tableLancamentoCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLancamentoCategoriaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableLancamentoCategoriaMousePressed(evt);
            }
        });
        spTable.setViewportView(tableLancamentoCategoria);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPesquisa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEm)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxEm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(290, Short.MAX_VALUE))
            .addComponent(spTable)
            .addComponent(toolBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPesquisa)
                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEm)
                    .addComponent(cbxEm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.editItem();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        this.deleteItem();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        this.refreshCategorias();
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void tableLancamentoCategoriaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLancamentoCategoriaMousePressed
        if(!this.btnEditar.isEnabled()) {
            this.btnEditar.setEnabled(true);
            this.btnExcluir.setEnabled(true);
        }
    }//GEN-LAST:event_tableLancamentoCategoriaMousePressed

    private void tableLancamentoCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLancamentoCategoriaMouseClicked
        if(evt.getClickCount() == 2) {
            notifyObservers();
        }
    }//GEN-LAST:event_tableLancamentoCategoriaMouseClicked

    private void btnNovoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNovoMousePressed
        this.popMenu.show(this.btnNovo, evt.getX(), evt.getY());
    }//GEN-LAST:event_btnNovoMousePressed

    private void mnuItemCategoriaGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemCategoriaGrupoActionPerformed
        this.addItem("categoriaGrupo");
    }//GEN-LAST:event_mnuItemCategoriaGrupoActionPerformed

    private void mnuItemCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemCategoriaActionPerformed
        this.addItem("categoria");
    }//GEN-LAST:event_mnuItemCategoriaActionPerformed

    private void refreshCategorias() {
        this.tableLancamentoCategoria.reload(
            this.lancamentoCategoriaController.getAllGrupoCategoria(
                this.listCampoFiltro.get(this.cbxEm.getSelectedIndex()),
                this.txtPesquisa.getText()
            )
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JComboBox<String> cbxEm;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JLabel lblEm;
    private javax.swing.JLabel lblPesquisa;
    private javax.swing.JMenuItem mnuItemCategoria;
    private javax.swing.JMenuItem mnuItemCategoriaGrupo;
    private javax.swing.JPopupMenu popMenu;
    private javax.swing.JScrollPane spTable;
    private br.com.tamarozzi.ui.treeTable.FinanceiroCategoriaTreeTable tableLancamentoCategoria;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JTextField txtPesquisa;
    // End of variables declaration//GEN-END:variables

    @Override
    public void registerObserver(Observer ob) {
        this.observers.add(ob);
        System.out.println("** Sistema: Observado " + this.getClass().getName() + " - Observador " + ob.getClass().getName() + " está registrado.");
    }

    @Override
    public void removeObserver(Observer ob) {
        this.observers.remove(ob);
        System.out.println("** Sistema: Observado " + this.getClass().getName() + " - Observador " + ob.getClass().getName() + " foi removido.");
    }

    @Override
    public void notifyObservers() {
        for(Observer ob : this.observers) {
            ob.update(this.tableLancamentoCategoria.getGrupoCategoriaSelected());
        }
    }

    @Override
    public void update(Object obj) {
        this.refreshCategorias();
    }

    @Override
    public void addItem(String typeItem) {
        switch(typeItem) {
            case "categoriaGrupo": 
                this.frmCadastroLancamentoCategoriaGrupo = new FrmCadastroFinanceiroCategoriaGrupo();
                this.frmCadastroLancamentoCategoriaGrupo.registerObserver(this);
                this.frmCadastroLancamentoCategoriaGrupo.setVisible(true);
            break;
            case "categoria":
                this.frmCadastroLancamentoCategoria = new FrmCadastroFinanceiroCategoria();
                this.frmCadastroLancamentoCategoria.registerObserver(this);
                this.frmCadastroLancamentoCategoria.setVisible(true);
            break;
        }
    }

    @Override
    public void editItem() {
        Object categoriaSelected = this.tableLancamentoCategoria.getGrupoCategoriaSelected();
        
        if(categoriaSelected != null) {
            
            if(categoriaSelected instanceof FinanceiroCategoriaGrupo) {
                FinanceiroCategoriaGrupo g = this.lancamentoCategoriaController.getGrupoCategoria((FinanceiroCategoriaGrupo) categoriaSelected);
                new FrmCadastroFinanceiroCategoriaGrupo(g).setVisible(true);
            }
            
            if(categoriaSelected instanceof FinanceiroCategoria) {
                FinanceiroCategoria c = this.lancamentoCategoriaController.getCategoria((FinanceiroCategoria) categoriaSelected);
                new FrmCadastroFinanceiroCategoria(c).setVisible(true);
            }
        }
    }

    @Override
    public void deleteItem() {
        List<Object> categorias = this.tableLancamentoCategoria.getGrupoCategoriasSelected();
        
        if(categorias != null) {
            
            if(this.lancamentoCategoriaController.deleteCategoria(categorias)) {
                this.refreshCategorias();
            }
        }
    }
}
