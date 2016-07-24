/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.ui;

import br.com.tamarozzi.interfaces.Observer;
import br.com.tamarozzi.model.UsuarioLogado;
import br.com.tamarozzi.util.ButtonTabComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Panda
 */
public class FrmPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        setComponents();        
    }
    
    private void setComponents() {
        this.tabMenuGeral.removeAll();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setBothTool();
        
        this.tabPessoa.registerObserver(new Observer() {
            @Override
            public void update(Object obj) {
                tabPessoa.editPessoa();
            }
        });
        
        this.tabBanco.registerObserver(new Observer() {
            @Override
            public void update(Object obj) {
                tabBanco.editBanco();
            }
        });
        
        this.tabConta.registerObserver(new Observer() {
            @Override
            public void update(Object obj) {
                tabConta.editItem();
            }
        });
        
        this.tabTarefa.registerObserver(new Observer() {
            @Override
            public void update(Object obj) {
                tabTarefa.editItem();
            }
        });
        
        this.tabLancamentoCategoria.registerObserver(new Observer() {
            @Override
            public void update(Object obj) {
                tabLancamentoCategoria.editItem();
            }
        });
    }
    
    private void setBothTool() {
        this.lblUsuario.setText(UsuarioLogado.getInstance().getNome());
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
        toolBarGeral = new javax.swing.JToolBar();
        btnPessoa = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnMensalidade = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnLancamento = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnTarefas = new javax.swing.JButton();
        tabMenuGeral = new javax.swing.JTabbedPane();
        tabPessoa = new br.com.tamarozzi.ui.panel.PanelPessoa();
        tabBanco = new br.com.tamarozzi.ui.panel.PanelBanco();
        tabConta = new br.com.tamarozzi.ui.panel.PanelConta();
        tabTarefa = new br.com.tamarozzi.ui.panel.PanelTarefa();
        tabLancamentoCategoria = new br.com.tamarozzi.ui.panel.PanelLancamentoCategoria();
        bothBar = new javax.swing.JSplitPane();
        lblVersao = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuCadastro = new javax.swing.JMenu();
        itemBancoCadastro = new javax.swing.JMenuItem();
        itemLancamentoCategoria = new javax.swing.JMenuItem();
        itemContaCadastro = new javax.swing.JMenuItem();
        itemPessoaCadastro = new javax.swing.JMenuItem();
        itemTarefaCadastro = new javax.swing.JMenuItem();
        menuFerramenta = new javax.swing.JMenu();
        itemAlterarSenha = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AcademiaSIG - Principal");
        setMinimumSize(new java.awt.Dimension(756, 572));
        setSize(new java.awt.Dimension(756, 572));

        toolBarGeral.setFloatable(false);
        toolBarGeral.setRollover(true);

        btnPessoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pessoa-icon.png"))); // NOI18N
        btnPessoa.setText("Pessoas");
        btnPessoa.setFocusable(false);
        btnPessoa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnPessoa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPessoaActionPerformed(evt);
            }
        });
        toolBarGeral.add(btnPessoa);
        toolBarGeral.add(jSeparator4);

        btnMensalidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/event-icon.png"))); // NOI18N
        btnMensalidade.setText("Mensalidades");
        btnMensalidade.setFocusable(false);
        btnMensalidade.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnMensalidade.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarGeral.add(btnMensalidade);
        toolBarGeral.add(jSeparator1);

        btnLancamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/dollar-icon.png"))); // NOI18N
        btnLancamento.setText("Contas a Pagar / Receber");
        btnLancamento.setFocusable(false);
        btnLancamento.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnLancamento.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLancamentoActionPerformed(evt);
            }
        });
        toolBarGeral.add(btnLancamento);
        toolBarGeral.add(jSeparator2);

        btnTarefas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alarm-clock-icon.png"))); // NOI18N
        btnTarefas.setText("Tarefas");
        btnTarefas.setFocusable(false);
        btnTarefas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTarefas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTarefas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTarefasActionPerformed(evt);
            }
        });
        toolBarGeral.add(btnTarefas);

        tabMenuGeral.addTab("Pessoas", tabPessoa);
        tabMenuGeral.addTab("Bancos", tabBanco);

        tabConta.setName("Contas"); // NOI18N
        tabMenuGeral.addTab("Contas", tabConta);
        tabMenuGeral.addTab("Tarefas", tabTarefa);
        tabMenuGeral.addTab("Categorias", tabLancamentoCategoria);

        bothBar.setDividerSize(3);
        bothBar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblVersao.setText("Versão: 1.0.0");
        lblVersao.setPreferredSize(new java.awt.Dimension(150, 14));
        bothBar.setLeftComponent(lblVersao);
        lblVersao.getAccessibleContext().setAccessibleParent(bothBar);

        bothBar.setRightComponent(lblUsuario);

        javax.swing.GroupLayout pnlGeralLayout = new javax.swing.GroupLayout(pnlGeral);
        pnlGeral.setLayout(pnlGeralLayout);
        pnlGeralLayout.setHorizontalGroup(
            pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabMenuGeral)
            .addComponent(bothBar)
            .addComponent(toolBarGeral, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlGeralLayout.setVerticalGroup(
            pnlGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGeralLayout.createSequentialGroup()
                .addComponent(toolBarGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabMenuGeral)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bothBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabMenuGeral.getAccessibleContext().setAccessibleName("tabMenuGeral");

        menuCadastro.setText("Cadastro");

        itemBancoCadastro.setText("Bancos");
        itemBancoCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemBancoCadastroActionPerformed(evt);
            }
        });
        menuCadastro.add(itemBancoCadastro);

        itemLancamentoCategoria.setText("Categorias");
        itemLancamentoCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLancamentoCategoriaActionPerformed(evt);
            }
        });
        menuCadastro.add(itemLancamentoCategoria);

        itemContaCadastro.setText("Contas");
        itemContaCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemContaCadastroActionPerformed(evt);
            }
        });
        menuCadastro.add(itemContaCadastro);

        itemPessoaCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pessoa-icon.png"))); // NOI18N
        itemPessoaCadastro.setText("Pessoas");
        itemPessoaCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemPessoaCadastroActionPerformed(evt);
            }
        });
        menuCadastro.add(itemPessoaCadastro);

        itemTarefaCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/alarm-clock-icon.png"))); // NOI18N
        itemTarefaCadastro.setText("Tarefas");
        itemTarefaCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTarefaCadastroActionPerformed(evt);
            }
        });
        menuCadastro.add(itemTarefaCadastro);

        menuBar.add(menuCadastro);

        menuFerramenta.setText("Ferramentas");

        itemAlterarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/senha-icon-small.png"))); // NOI18N
        itemAlterarSenha.setText("Alterar Senha");
        itemAlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAlterarSenhaActionPerformed(evt);
            }
        });
        menuFerramenta.add(itemAlterarSenha);

        menuBar.add(menuFerramenta);

        setJMenuBar(menuBar);

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

    private void itemBancoCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemBancoCadastroActionPerformed
        this.addTabMenuGeral(this.tabBanco);
    }//GEN-LAST:event_itemBancoCadastroActionPerformed

    private void itemPessoaCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemPessoaCadastroActionPerformed
        this.addTabMenuGeral(this.tabPessoa);
    }//GEN-LAST:event_itemPessoaCadastroActionPerformed

    private void btnPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPessoaActionPerformed
        this.addTabMenuGeral(this.tabPessoa);
    }//GEN-LAST:event_btnPessoaActionPerformed

    private void itemContaCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemContaCadastroActionPerformed
        this.addTabMenuGeral(this.tabConta);
    }//GEN-LAST:event_itemContaCadastroActionPerformed

    private void itemAlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAlterarSenhaActionPerformed
//        FrmAlterarSenha frmAlterarSenha = new FrmAlterarSenha(UsuarioLogado.getInstance().getUsuario());
//        frmAlterarSenha.setVisible(true);
           FrmCadastroPessoa f = new FrmCadastroPessoa(UsuarioLogado.getInstance());
           f.setVisible(true);
           f.toolBarGeral.setSelectedIndex(0);
    }//GEN-LAST:event_itemAlterarSenhaActionPerformed

    private void btnTarefasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTarefasActionPerformed
        this.addTabMenuGeral(this.tabTarefa);
        //this.tabTarefa.setView();
    }//GEN-LAST:event_btnTarefasActionPerformed

    private void itemTarefaCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTarefaCadastroActionPerformed
        this.addTabMenuGeral(this.tabTarefa);
    }//GEN-LAST:event_itemTarefaCadastroActionPerformed

    private void btnLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLancamentoActionPerformed
        
    }//GEN-LAST:event_btnLancamentoActionPerformed

    private void itemLancamentoCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLancamentoCategoriaActionPerformed
        this.addTabMenuGeral(this.tabLancamentoCategoria);
    }//GEN-LAST:event_itemLancamentoCategoriaActionPerformed
        
    public void addTabMenuGeral(JPanel p) {
        int index = this.tabMenuGeral.indexOfComponent(p);

        if(index == -1) {
            this.tabMenuGeral.addTab(p.getName(), p);
            this.tabMenuGeral.setTabComponentAt(
                this.tabMenuGeral.indexOfComponent(p),
                new ButtonTabComponent(this.tabMenuGeral));
        }

        this.tabMenuGeral.setSelectedComponent(p);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane bothBar;
    private javax.swing.JButton btnLancamento;
    private javax.swing.JButton btnMensalidade;
    private javax.swing.JButton btnPessoa;
    private javax.swing.JButton btnTarefas;
    private javax.swing.JMenuItem itemAlterarSenha;
    private javax.swing.JMenuItem itemBancoCadastro;
    private javax.swing.JMenuItem itemContaCadastro;
    private javax.swing.JMenuItem itemLancamentoCategoria;
    private javax.swing.JMenuItem itemPessoaCadastro;
    private javax.swing.JMenuItem itemTarefaCadastro;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblVersao;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuCadastro;
    private javax.swing.JMenu menuFerramenta;
    private javax.swing.JPanel pnlGeral;
    private br.com.tamarozzi.ui.panel.PanelBanco tabBanco;
    private br.com.tamarozzi.ui.panel.PanelConta tabConta;
    private br.com.tamarozzi.ui.panel.PanelLancamentoCategoria tabLancamentoCategoria;
    private javax.swing.JTabbedPane tabMenuGeral;
    private br.com.tamarozzi.ui.panel.PanelPessoa tabPessoa;
    private br.com.tamarozzi.ui.panel.PanelTarefa tabTarefa;
    private javax.swing.JToolBar toolBarGeral;
    // End of variables declaration//GEN-END:variables
}