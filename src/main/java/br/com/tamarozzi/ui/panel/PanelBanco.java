/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.ui.panel;

import br.com.tamarozzi.controller.BancoController;
import br.com.tamarozzi.interfaces.Observable;
import br.com.tamarozzi.interfaces.Observer;
import br.com.tamarozzi.model.Banco;
import br.com.tamarozzi.ui.frame.FrmCadastroBanco;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Panda
 */
public class PanelBanco extends javax.swing.JPanel implements Observable, Observer {

    private List<Observer> observers;
    
    private BancoController bancoController;
    
    private List<String> listCampoFiltro;
    
    private FrmCadastroBanco frmCadastroBanco;
    
    /**
     * Creates new form PanelBanco
     */
    public PanelBanco() {
        preInitComponents();
        initComponents();
    }
    
    private void preInitComponents() {
        this.observers = new ArrayList<>();
        
        //Filters
        this.listCampoFiltro = Arrays.asList("search", "nome", "numero");
        
        //Controllers
        this.bancoController = new BancoController();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        spTableBanco = new javax.swing.JScrollPane();
        tableBanco = new br.com.tamarozzi.ui.table.TableBanco();

        setName("Bancos"); // NOI18N

        lblPesquisa.setText("Pesquisa:");

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo-icon.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setFocusable(false);
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
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

        cbxEm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Nome", "Número" }));
        cbxEm.setMinimumSize(new java.awt.Dimension(125, 20));
        cbxEm.setPreferredSize(new java.awt.Dimension(125, 20));

        tableBanco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBancoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableBancoMousePressed(evt);
            }
        });
        spTableBanco.setViewportView(tableBanco);

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
            .addComponent(spTableBanco)
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
                .addComponent(spTableBanco, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.editBanco();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        this.deleteBanco();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        this.tableBanco.reload(
            this.bancoController.getAllBanco(
                this.listCampoFiltro.get(this.cbxEm.getSelectedIndex()),
                this.txtPesquisa.getText()
            )
        );
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        this.addBanco();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void tableBancoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBancoMouseClicked
        if(evt.getClickCount() == 2) {
            notifyObservers();
        }
    }//GEN-LAST:event_tableBancoMouseClicked

    private void tableBancoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBancoMousePressed
        if(!this.btnEditar.isEnabled()) {
            this.btnEditar.setEnabled(true);
            this.btnExcluir.setEnabled(true);
        }
    }//GEN-LAST:event_tableBancoMousePressed

    private void addBanco() {
        this.frmCadastroBanco = new FrmCadastroBanco();
        this.frmCadastroBanco.registerObserver(this);
        this.frmCadastroBanco.setVisible(true);
    }
    
    private void deleteBanco() {
        List<Banco> bancos = this.tableBanco.getBancosSelected();
        
        if(bancos != null) {
            if(this.bancoController.deleteBanco(bancos)) {
                this.tableBanco.removeItens((List<Object>) (Object) bancos);
            }
        }
    }
    
    public void editBanco() {
        Banco bancoSelected = this.tableBanco.getBancoSelected();
        
        if(bancoSelected != null) {
            Banco b = this.bancoController.getBanco(bancoSelected);
            new FrmCadastroBanco(b).setVisible(true);
        }
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
    private javax.swing.JScrollPane spTableBanco;
    private br.com.tamarozzi.ui.table.TableBanco tableBanco;
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
            ob.update(this.tableBanco.getBancoSelected());
        }
    }

    @Override
    public void update(Object obj) {
        this.tableBanco.addItem(obj);
    }
}
