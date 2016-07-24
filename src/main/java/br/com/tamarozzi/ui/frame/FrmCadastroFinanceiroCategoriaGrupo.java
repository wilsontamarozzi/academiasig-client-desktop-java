/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.ui;

import br.com.tamarozzi.controller.LancamentoCategoriaController;
import br.com.tamarozzi.interfaces.Observable;
import br.com.tamarozzi.interfaces.Observer;
import br.com.tamarozzi.model.LancamentoCategoriaGrupo;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import net.java.balloontip.BalloonTip;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Wilson
 */
public class FrmCadastroLancamentoCategoriaGrupo extends javax.swing.JDialog implements Observable {

    private List<Observer> observers;
    
    private LancamentoCategoriaController lancamentoCategoriaController;
    
    private LancamentoCategoriaGrupo categoriaGrupo;
    
    private BalloonTip balloonTip;
    
    public FrmCadastroLancamentoCategoriaGrupo() {
        preInitComponents("Cadastro de Grupo de Categoria");
        initComponents();
        setComponents();
    }    
    
    public FrmCadastroLancamentoCategoriaGrupo(LancamentoCategoriaGrupo categoriaGrupo) {
        this.categoriaGrupo = categoriaGrupo;
        
        preInitComponents("Alterar Grupo de Categoria");
        initComponents();
        setComponents();
        setView(categoriaGrupo);
    }
    
    private void preInitComponents(String title) {
        this.setTitle(title);
        
        this.observers = new ArrayList<>(0);
        this.lancamentoCategoriaController = new LancamentoCategoriaController();
    }
    
    private void setComponents() {
        this.setLocationRelativeTo(null);
        this.setModal(true);
    }
    
    private void setView(LancamentoCategoriaGrupo g) {
        this.txtDescricao.setText(g.getNome());
        this.cbxTipo.setSelectedItem(g.getTipo());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlGeral = new javax.swing.JPanel();
        lblDescricao = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        lblTipo = new javax.swing.JLabel();
        cbxTipo = new javax.swing.JComboBox<>();
        btnCancelar = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Grupo de Categoria");

        pnlGeral.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblDescricao.setText("Descrição:");

        lblTipo.setText("Tipo:");

        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Despesa", "Receita" }));
        cbxTipo.setSelectedIndex(-1);

        javax.swing.GroupLayout pnlGeralLayout = new javax.swing.GroupLayout(pnlGeral);
        pnlGeral.setLayout(pnlGeralLayout);
        pnlGeralLayout.setHorizontalGroup(
            pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescricao)
                    .addComponent(lblTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescricao)
                    .addComponent(cbxTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlGeralLayout.setVerticalGroup(
            pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescricao)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo)
                    .addComponent(cbxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnCadastrar.setText("OK");
        btnCadastrar.setPreferredSize(new java.awt.Dimension(77, 23));
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlGeral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 120, Short.MAX_VALUE)
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlGeral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        JSONObject errors = this.lancamentoCategoriaController.editGrupoCategoria(this.loadCategoria());
        
        if(errors != null) {
            this.parseErrors(errors);
        } else {
            this.notifyObservers();
            this.dispose();
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    public LancamentoCategoriaGrupo loadCategoria() {
        
        if(this.categoriaGrupo == null) {
            this.categoriaGrupo = new LancamentoCategoriaGrupo();
        }
        
        this.categoriaGrupo.setNome(this.txtDescricao.getText());
        this.categoriaGrupo.setTipo(this.cbxTipo.getSelectedItem().toString());
        
        return this.categoriaGrupo;
    }
    
    private void parseErrors(JSONObject errors) {
        
        errors.keys().forEachRemaining((String key) -> {
            
            JSONArray obj = errors.getJSONArray(key);

            JComponent comp = null;
            
            switch(key) {
                case "descricao":
                    comp = this.txtDescricao;
                break;
            }
            
            if(this.balloonTip == null && comp != null) {
                this.balloonTip = new BalloonTip(comp, obj.getString(0));
            } else if(comp != null && !this.balloonTip.isVisible()) {
                this.balloonTip = new BalloonTip(comp, obj.getString(0));
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cbxTipo;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JPanel pnlGeral;
    private javax.swing.JTextField txtDescricao;
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
            ob.update(this.categoriaGrupo);
        }
    }
}