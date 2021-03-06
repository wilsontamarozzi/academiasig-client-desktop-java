/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.ui.frame;

import br.com.tamarozzi.controller.ContaController;
import br.com.tamarozzi.interfaces.Observable;
import br.com.tamarozzi.interfaces.Observer;
import br.com.tamarozzi.model.Banco;
import br.com.tamarozzi.model.Conta;
import br.com.tamarozzi.model.ContaCorrente;
import br.com.tamarozzi.model.Pessoa;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import net.java.balloontip.BalloonTip;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Wilson
 */
public class FrmCadastroContaCorrente extends javax.swing.JDialog implements Observable {

    private List<Observer> observers;
    
    private ContaController contaController;
    
    private FrmPesquisaPessoa frmPesquisaPessoa;
    private FrmPesquisaBanco frmPesquisaBanco;
    
    private ContaCorrente contaCorrente = null;
    private Pessoa titular;
    private Banco banco;
    
    private BalloonTip balloonTip;
        
    public FrmCadastroContaCorrente() {
        preInitComponents();
        initComponents();
        setComponents();
    }
    
    public FrmCadastroContaCorrente(ContaCorrente c) {
        this.contaCorrente = c;
        
        preInitComponents();
        initComponents();
        setComponents();
        
        setView(c);
    }
    
    private void preInitComponents() {
        this.observers = new ArrayList<>();
        this.titular = new Pessoa();
        this.banco = new Banco();
        this.contaController = new ContaController();
        this.frmPesquisaPessoa = new FrmPesquisaPessoa();
        this.frmPesquisaBanco = new FrmPesquisaBanco();
    }
    
    private void setComponents() {
        this.setLocationRelativeTo(null);
        this.frmPesquisaPessoa.panelPessoaPesquisa.registerObserver(new Observer() {
            @Override
            public void update(Object obj) {
                titular = (Pessoa) obj;
                frmPesquisaPessoa.setVisible(false);
                txtTitular.setText(titular.getNome());
            }
        });
        
        this.frmPesquisaBanco.panelPesquisaBanco.registerObserver(new Observer() {
            @Override
            public void update(Object obj) {
                banco = (Banco) obj;
                frmPesquisaBanco.setVisible(false);
                txtBanco.setText(banco.getNome());
            }
        });
    }
    
    private void setView(ContaCorrente c) {
        this.txtDescricao.setText(c.getDescricao());
        this.txtAgencia.setValue(new BigDecimal(c.getAgencia()));
        this.txtAgenciaDigito.setValue(new BigDecimal(c.getAgenciaDigito()));
        this.txtConta.setValue(new BigDecimal(c.getConta()));
        this.txtContaDigito.setValue(new BigDecimal(c.getContaDigito()));
        this.chkAtivo.setSelected(c.isAtivo());
        
        if(c.getTitular() != null) {
            this.txtTitular.setText(c.getTitular().getNome());
        }
        
        if(c.getBanco() != null) {
            this.txtBanco.setText(c.getBanco().getNome());
        }
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
        tabMenuGeral = new javax.swing.JTabbedPane();
        pnlDetalhes = new javax.swing.JPanel();
        lblDescricao = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        lblTitular = new javax.swing.JLabel();
        txtTitular = new javax.swing.JTextField();
        lblBanco = new javax.swing.JLabel();
        txtBanco = new javax.swing.JTextField();
        lblAgencia = new javax.swing.JLabel();
        txtAgencia = new br.com.tamarozzi.util.JNumberFormatFieldUtil(new DecimalFormat("#"), 4);
        txtAgenciaDigito = new br.com.tamarozzi.util.JNumberFormatFieldUtil(new DecimalFormat("#"), 1);
        lblConta = new javax.swing.JLabel();
        txtConta = new br.com.tamarozzi.util.JNumberFormatFieldUtil(new DecimalFormat("#"), 11);
        txtContaDigito = new br.com.tamarozzi.util.JNumberFormatFieldUtil(new DecimalFormat("#"), 1);
        btnTitular = new javax.swing.JButton();
        btnBanco = new javax.swing.JButton();
        chkAtivo = new javax.swing.JCheckBox();
        btnCancelar = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conta Corrente");
        setResizable(false);

        lblDescricao.setText("Descrição:");

        lblTitular.setText("Titular:");

        txtTitular.setEditable(false);

        lblBanco.setText("Banco");

        txtBanco.setEditable(false);

        lblAgencia.setText("Agência:");

        txtAgenciaDigito.setPreferredSize(new java.awt.Dimension(6, 20));

        lblConta.setText("Conta:");

        btnTitular.setText("jButton1");
        btnTitular.setPreferredSize(new java.awt.Dimension(23, 23));
        btnTitular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTitularMouseClicked(evt);
            }
        });

        btnBanco.setText("jButton1");
        btnBanco.setPreferredSize(new java.awt.Dimension(23, 23));
        btnBanco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBancoMouseClicked(evt);
            }
        });

        chkAtivo.setSelected(true);
        chkAtivo.setText("Ativa");

        javax.swing.GroupLayout pnlDetalhesLayout = new javax.swing.GroupLayout(pnlDetalhes);
        pnlDetalhes.setLayout(pnlDetalhesLayout);
        pnlDetalhesLayout.setHorizontalGroup(
            pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescricao)
                    .addComponent(lblTitular)
                    .addComponent(lblBanco)
                    .addComponent(lblAgencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDetalhesLayout.createSequentialGroup()
                        .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTitular)
                            .addComponent(txtBanco))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBanco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlDetalhesLayout.createSequentialGroup()
                        .addComponent(txtAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAgenciaDigito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblConta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtConta, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtContaDigito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDetalhesLayout.createSequentialGroup()
                        .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkAtivo)))
                .addContainerGap())
        );
        pnlDetalhesLayout.setVerticalGroup(
            pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescricao)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkAtivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitular)
                    .addComponent(btnTitular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBanco)
                    .addComponent(btnBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConta)
                    .addComponent(lblAgencia)
                    .addComponent(txtAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAgenciaDigito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtContaDigito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabMenuGeral.addTab("Detalhes", pnlDetalhes);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnCadastrar.setText("Ok");
        btnCadastrar.setMaximumSize(new java.awt.Dimension(75, 23));
        btnCadastrar.setMinimumSize(new java.awt.Dimension(75, 23));
        btnCadastrar.setPreferredSize(new java.awt.Dimension(75, 23));
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlGeralLayout = new javax.swing.GroupLayout(pnlGeral);
        pnlGeral.setLayout(pnlGeralLayout);
        pnlGeralLayout.setHorizontalGroup(
            pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tabMenuGeral)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGeralLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        pnlGeralLayout.setVerticalGroup(
            pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabMenuGeral)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlGeral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlGeral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        JSONObject errors = this.contaController.editConta(this.loadConta());
        
        if(errors != null) {
            this.parseErrors(errors);
        } else {
            this.notifyObservers();
            this.dispose();
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnTitularMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTitularMouseClicked
        this.frmPesquisaPessoa.setVisible(true);
    }//GEN-LAST:event_btnTitularMouseClicked

    private void btnBancoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBancoMouseClicked
        this.frmPesquisaBanco.setVisible(true);
    }//GEN-LAST:event_btnBancoMouseClicked

    private Conta loadConta() {
        
        if(this.contaCorrente == null) {
            this.contaCorrente = new ContaCorrente();
        }
        
        this.contaCorrente.setDescricao(this.txtDescricao.getText());
        this.contaCorrente.setAgencia(this.txtAgencia.getValue().intValue());
        this.contaCorrente.setAgenciaDigito(this.txtAgenciaDigito.getValue().intValue());
        this.contaCorrente.setConta(this.txtConta.getValue().intValue());
        this.contaCorrente.setContaDigito(this.txtContaDigito.getValue().intValue());
        this.contaCorrente.setAtivo(this.chkAtivo.isSelected());
        
        if(titular.getUUID() != null) {
            this.contaCorrente.setTitular(titular);
        }
        
        if(banco.getUUID() != null) {
            this.contaCorrente.setBanco(banco);
        }        
        
        return this.contaCorrente;
    }
    
    private void parseErrors(JSONObject errors) {
        
        errors.keys().forEachRemaining((String key) -> {
            
            JSONArray obj = errors.getJSONArray(key);

            JComponent comp = null;
            
            switch(key) {
                case "descricao":
                    comp = this.txtDescricao;
                break;
                case "agencia":
                    comp = this.txtAgencia;
                break;
                case "conta":
                    comp = this.txtConta;
                break;
                case "banco":
                    comp = this.txtBanco;
                break;
                case "tipoConta":
                    JOptionPane.showMessageDialog(null, obj.getString(0), "Erro Critico", JOptionPane.ERROR_MESSAGE);
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
    private javax.swing.JButton btnBanco;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnTitular;
    private javax.swing.JCheckBox chkAtivo;
    private javax.swing.JLabel lblAgencia;
    private javax.swing.JLabel lblBanco;
    private javax.swing.JLabel lblConta;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblTitular;
    private javax.swing.JPanel pnlDetalhes;
    private javax.swing.JPanel pnlGeral;
    private javax.swing.JTabbedPane tabMenuGeral;
    private br.com.tamarozzi.util.JNumberFormatFieldUtil txtAgencia;
    private br.com.tamarozzi.util.JNumberFormatFieldUtil txtAgenciaDigito;
    private javax.swing.JTextField txtBanco;
    private br.com.tamarozzi.util.JNumberFormatFieldUtil txtConta;
    private br.com.tamarozzi.util.JNumberFormatFieldUtil txtContaDigito;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtTitular;
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
            ob.update(this.contaCorrente);
        }
    }
}