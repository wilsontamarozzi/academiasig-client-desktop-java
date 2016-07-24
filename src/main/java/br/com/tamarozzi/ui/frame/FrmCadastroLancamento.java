/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.ui.frame;

import br.com.tamarozzi.controller.LancamentoCategoriaController;
import br.com.tamarozzi.controller.LancamentoController;
import br.com.tamarozzi.controller.LancamentoMovimentacaoController;
import br.com.tamarozzi.interfaces.Observable;
import br.com.tamarozzi.interfaces.Observer;
import br.com.tamarozzi.model.Conta;
import br.com.tamarozzi.model.FinanceiroCategoria;
import br.com.tamarozzi.model.FinanceiroCategoriaGrupo;
import br.com.tamarozzi.model.Lancamento;
import br.com.tamarozzi.model.LancamentoCategoria;
import br.com.tamarozzi.model.LancamentoMovimentacao;
import br.com.tamarozzi.model.Pessoa;
import br.com.tamarozzi.model.UsuarioLogado;
import br.com.tamarozzi.typeEnum.EnumTipoCategoria;
import br.com.tamarozzi.typeEnum.EnumTipoLancamento;
import br.com.tamarozzi.util.DateUtils;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.java.balloontip.BalloonTip;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Wilson
 */
public class FrmCadastroLancamento extends javax.swing.JDialog implements Observable, Observer {

    private List<Observer> observers;
    
    private String tipoLancamento;
    
    private Lancamento lancamento;
    private Pessoa pessoa;
    
    private List<LancamentoMovimentacao> movimentacoes;
    private List<LancamentoCategoria> categorias;
    
    private LancamentoController lancamentoController;
    private LancamentoCategoriaController lancamentoCategoriaController;
    private LancamentoMovimentacaoController lancamentoMovimentacaoController;
    
    private FrmPesquisaFinanceiroCategoria frmPesquisaFinanceiroCategoria;
    private FrmCadastroLancamentoCategoria frmCadastroLancamentoCategoria;
    private FrmCadastroLancamentoMovimentacao frmCadastroLancamentoMovimentacao;
    
    private FrmPesquisaConta frmPesquisaConta;
    private FrmPesquisaPessoa frmPesquisaPessoa;
        
    private SimpleDateFormat dateFormat;
    
    private BalloonTip balloonTip;
    
    public FrmCadastroLancamento(String tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
        
        if(tipoLancamento.equalsIgnoreCase(EnumTipoLancamento.CONTA_RECEBER)) {
            preInitComponents("Cadastrar Conta a Receber");
        } else {
            preInitComponents("Cadastrar Conta a Pagar");
        }
        
        initComponents();
        setComponents();
    }
    
    public FrmCadastroLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
        this.tipoLancamento = lancamento.getTipo();
        
        if(lancamento.getTipo().equalsIgnoreCase(EnumTipoLancamento.CONTA_RECEBER)) {
            preInitComponents("Alterar Conta a Receber");
        } else {
            preInitComponents("Alterar Conta a Pagar");
        }
        
        initComponents();
        setComponents();
        
        setView(lancamento);
    }
    
    private void preInitComponents(String title) {
        this.setTitle(title);
        
        this.observers = new ArrayList<>(0);
        this.categorias = new ArrayList<>(0);
        this.movimentacoes = new ArrayList<>(0);
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.frmPesquisaFinanceiroCategoria = new FrmPesquisaFinanceiroCategoria();
        this.frmPesquisaPessoa = new FrmPesquisaPessoa();
        this.frmPesquisaConta = new FrmPesquisaConta();
        this.lancamentoController = new LancamentoController();
        this.lancamentoCategoriaController = new LancamentoCategoriaController();
        this.lancamentoMovimentacaoController = new LancamentoMovimentacaoController();
    }
    
    private void setComponents() {
        this.setLocationRelativeTo(null);
        this.dtcDataEmissao.setDate(new Date());
        this.dtcDataVencimento.setDate(new Date());
        this.tabCategoriaMovimentacao.remove(this.tabMenuMovimentacao);

        this.frmPesquisaFinanceiroCategoria.panelPesquisaFinanceiroCategoria.registerObserver(new Observer() {
            @Override
            public void update(Object obj) {
                if(obj instanceof FinanceiroCategoriaGrupo) {
                    frmPesquisaFinanceiroCategoria.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Selecione uma categoria.");
                }
                
                if(obj instanceof FinanceiroCategoria) {
                    frmPesquisaFinanceiroCategoria.setVisible(false);
                    addCategoria((FinanceiroCategoria) obj);
                }
            }
        });
        
        this.frmPesquisaPessoa.panelPessoaPesquisa.registerObserver(new Observer() {
            @Override
            public void update(Object obj) {
                pessoa = (Pessoa) obj;
                frmPesquisaPessoa.setVisible(false);
                txtPessoa.setText(pessoa.getNome());
            }
        });
        
        this.frmPesquisaConta.panelPesquisaConta.registerObserver(new Observer() {
            @Override
            public void update(Object obj) {
                frmPesquisaConta.setVisible(false);
                addMovimentacao((Conta) obj);
            }
        });
        
        this.dtcDataLiquidacao.addPropertyChangeListener("date", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                Date date = (Date)evt.getNewValue();
                
                if(date != null) {
                    mostraAbaMovimentacao();
                } else {
                    //escondeAbaMovimentacao();
                }
            }
        });
    }
    
    private void setView(Lancamento l) {
        this.categorias.addAll(l.getCategorias());
        this.movimentacoes.addAll(l.getMovimentacoes());
        
        this.txtNumero.setText(l.getUUID());
        this.dtcDataEmissao.setDate(l.getDataEmissao());
        this.dtcDataVencimento.setDate(l.getDataVencimento());
        this.dtcDataLiquidacao.setDate(l.getDataLiquidacao());
        this.txtPessoa.setText(l.getPessoa().getNome());
        this.txtDescricao.setText(l.getDescricao());
        this.txtValorFinal.setValue(l.getValorFinal());
        this.txaObservacao.setText(l.getObservacao());
        
        this.lblDataCadastro.setText(this.dateFormat.format(l.getDataCadastro()));
        this.lblNomeCadastrante.setText(l.getCadastradoPor().getNome());
        
        this.tableLancamentoCategoria.reload(this.categorias);
        this.tableLancamentoMovimentacao.reload(this.movimentacoes);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabMenuGeral = new javax.swing.JTabbedPane();
        tabDetalhes = new javax.swing.JPanel();
        pnlInformacao = new javax.swing.JPanel();
        lblNumero = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        lblDataEmissao = new javax.swing.JLabel();
        dtcDataEmissao = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        lblPessoa = new javax.swing.JLabel();
        txtPessoa = new javax.swing.JTextField();
        btnPessoa = new javax.swing.JButton();
        lblDescricao = new javax.swing.JLabel();
        txtDescricao = new javax.swing.JTextField();
        lblDocumento = new javax.swing.JLabel();
        txtDocumento = new javax.swing.JTextField();
        pnlFinanceiro = new javax.swing.JPanel();
        lblVencimento = new javax.swing.JLabel();
        dtcDataVencimento = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        lblValorFinal = new javax.swing.JLabel();
        txtValorFinal = new br.com.tamarozzi.util.JNumberFormatFieldUtil(new DecimalFormat("R$ #,##0.00"));
        lblLiquidacao = new javax.swing.JLabel();
        dtcDataLiquidacao = new com.toedter.calendar.JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
        lblObservacao = new javax.swing.JLabel();
        spObservacao = new javax.swing.JScrollPane();
        txaObservacao = new javax.swing.JTextArea();
        tabCategoriaMovimentacao = new javax.swing.JTabbedPane();
        tabMenuCategoria = new javax.swing.JPanel();
        toolBarCategoria = new javax.swing.JToolBar();
        btnNovoCategoria = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnEditarCategoria = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnExcluirCategoria = new javax.swing.JButton();
        spCategoria = new javax.swing.JScrollPane();
        tableLancamentoCategoria = new br.com.tamarozzi.ui.table.TableLancamentoCategoria();
        tabMenuMovimentacao = new javax.swing.JPanel();
        toolBarMovimentacao = new javax.swing.JToolBar();
        btnNovoMovimentacao = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnEditarMovimentacao = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnExcluirMovimentacao = new javax.swing.JButton();
        spMovimentacao = new javax.swing.JScrollPane();
        tableLancamentoMovimentacao = new br.com.tamarozzi.ui.table.TableLancamentoMovimentacao();
        lblCadastradoEm = new javax.swing.JLabel();
        lblDataCadastro = new javax.swing.JLabel();
        lblCadastradoPor = new javax.swing.JLabel();
        lblNomeCadastrante = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnCadastrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlInformacao.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblNumero.setText("Número:");

        txtNumero.setEditable(false);
        txtNumero.setPreferredSize(new java.awt.Dimension(125, 20));

        lblDataEmissao.setText("Emissão:");

        lblPessoa.setText("Pessoa:");

        txtPessoa.setEditable(false);

        btnPessoa.setText("jButton1");
        btnPessoa.setPreferredSize(new java.awt.Dimension(23, 23));
        btnPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPessoaActionPerformed(evt);
            }
        });

        lblDescricao.setText("Descrição:");

        lblDocumento.setText("Documento:");

        javax.swing.GroupLayout pnlInformacaoLayout = new javax.swing.GroupLayout(pnlInformacao);
        pnlInformacao.setLayout(pnlInformacaoLayout);
        pnlInformacaoLayout.setHorizontalGroup(
            pnlInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPessoa)
                    .addComponent(lblDescricao)
                    .addComponent(lblNumero)
                    .addComponent(lblDataEmissao)
                    .addComponent(lblDocumento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescricao)
                    .addComponent(txtDocumento)
                    .addGroup(pnlInformacaoLayout.createSequentialGroup()
                        .addGroup(pnlInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dtcDataEmissao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNumero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlInformacaoLayout.createSequentialGroup()
                        .addComponent(txtPessoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlInformacaoLayout.setVerticalGroup(
            pnlInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInformacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumero)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblDataEmissao)
                    .addComponent(dtcDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPessoa)
                    .addComponent(txtPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDescricao)
                    .addComponent(txtDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlInformacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDocumento)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlFinanceiro.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblVencimento.setText("Vencimento:");

        lblValorFinal.setText("Valor Final:");

        lblLiquidacao.setText("Liquidação:");

        dtcDataLiquidacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dtcDataLiquidacaoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlFinanceiroLayout = new javax.swing.GroupLayout(pnlFinanceiro);
        pnlFinanceiro.setLayout(pnlFinanceiroLayout);
        pnlFinanceiroLayout.setHorizontalGroup(
            pnlFinanceiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFinanceiroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFinanceiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblVencimento)
                    .addComponent(lblValorFinal)
                    .addComponent(lblLiquidacao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFinanceiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtcDataVencimento, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(dtcDataLiquidacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtValorFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlFinanceiroLayout.setVerticalGroup(
            pnlFinanceiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFinanceiroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFinanceiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblVencimento)
                    .addComponent(dtcDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFinanceiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblValorFinal)
                    .addComponent(txtValorFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlFinanceiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblLiquidacao)
                    .addComponent(dtcDataLiquidacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblObservacao.setText("Observações:");

        txaObservacao.setColumns(20);
        txaObservacao.setRows(5);
        spObservacao.setViewportView(txaObservacao);

        tabCategoriaMovimentacao.setName(""); // NOI18N

        toolBarCategoria.setFloatable(false);
        toolBarCategoria.setRollover(true);

        btnNovoCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo-icon.png"))); // NOI18N
        btnNovoCategoria.setText("Novo");
        btnNovoCategoria.setFocusable(false);
        btnNovoCategoria.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNovoCategoria.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovoCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoCategoriaActionPerformed(evt);
            }
        });
        toolBarCategoria.add(btnNovoCategoria);
        toolBarCategoria.add(jSeparator1);

        btnEditarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar-icon.png"))); // NOI18N
        btnEditarCategoria.setText("Editar");
        btnEditarCategoria.setEnabled(false);
        btnEditarCategoria.setFocusable(false);
        btnEditarCategoria.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEditarCategoria.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarCategoriaActionPerformed(evt);
            }
        });
        toolBarCategoria.add(btnEditarCategoria);
        toolBarCategoria.add(jSeparator2);

        btnExcluirCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir-icon.png"))); // NOI18N
        btnExcluirCategoria.setText("Excluir");
        btnExcluirCategoria.setEnabled(false);
        btnExcluirCategoria.setFocusable(false);
        btnExcluirCategoria.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnExcluirCategoria.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExcluirCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirCategoriaActionPerformed(evt);
            }
        });
        toolBarCategoria.add(btnExcluirCategoria);

        tableLancamentoCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLancamentoCategoriaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableLancamentoCategoriaMousePressed(evt);
            }
        });
        spCategoria.setViewportView(tableLancamentoCategoria);

        javax.swing.GroupLayout tabMenuCategoriaLayout = new javax.swing.GroupLayout(tabMenuCategoria);
        tabMenuCategoria.setLayout(tabMenuCategoriaLayout);
        tabMenuCategoriaLayout.setHorizontalGroup(
            tabMenuCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(tabMenuCategoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabMenuCategoriaLayout.setVerticalGroup(
            tabMenuCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMenuCategoriaLayout.createSequentialGroup()
                .addComponent(toolBarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabCategoriaMovimentacao.addTab("Categorias", tabMenuCategoria);

        tabMenuMovimentacao.setName("Movimentações"); // NOI18N

        toolBarMovimentacao.setFloatable(false);
        toolBarMovimentacao.setRollover(true);

        btnNovoMovimentacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/novo-icon.png"))); // NOI18N
        btnNovoMovimentacao.setText("Novo");
        btnNovoMovimentacao.setFocusable(false);
        btnNovoMovimentacao.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNovoMovimentacao.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovoMovimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoMovimentacaoActionPerformed(evt);
            }
        });
        toolBarMovimentacao.add(btnNovoMovimentacao);
        toolBarMovimentacao.add(jSeparator3);

        btnEditarMovimentacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/editar-icon.png"))); // NOI18N
        btnEditarMovimentacao.setText("Editar");
        btnEditarMovimentacao.setEnabled(false);
        btnEditarMovimentacao.setFocusable(false);
        btnEditarMovimentacao.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEditarMovimentacao.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditarMovimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarMovimentacaoActionPerformed(evt);
            }
        });
        toolBarMovimentacao.add(btnEditarMovimentacao);
        toolBarMovimentacao.add(jSeparator4);

        btnExcluirMovimentacao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/excluir-icon.png"))); // NOI18N
        btnExcluirMovimentacao.setText("Excluir");
        btnExcluirMovimentacao.setEnabled(false);
        btnExcluirMovimentacao.setFocusable(false);
        btnExcluirMovimentacao.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnExcluirMovimentacao.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExcluirMovimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirMovimentacaoActionPerformed(evt);
            }
        });
        toolBarMovimentacao.add(btnExcluirMovimentacao);

        tableLancamentoMovimentacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLancamentoMovimentacaoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableLancamentoMovimentacaoMousePressed(evt);
            }
        });
        spMovimentacao.setViewportView(tableLancamentoMovimentacao);

        javax.swing.GroupLayout tabMenuMovimentacaoLayout = new javax.swing.GroupLayout(tabMenuMovimentacao);
        tabMenuMovimentacao.setLayout(tabMenuMovimentacaoLayout);
        tabMenuMovimentacaoLayout.setHorizontalGroup(
            tabMenuMovimentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBarMovimentacao, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
            .addGroup(tabMenuMovimentacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spMovimentacao)
                .addContainerGap())
        );
        tabMenuMovimentacaoLayout.setVerticalGroup(
            tabMenuMovimentacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMenuMovimentacaoLayout.createSequentialGroup()
                .addComponent(toolBarMovimentacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spMovimentacao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        tabCategoriaMovimentacao.addTab("Movimentações", tabMenuMovimentacao);

        lblCadastradoEm.setText("Cadastrado em:");

        lblDataCadastro.setText("Data Cadastro");

        lblCadastradoPor.setText("por:");

        lblNomeCadastrante.setText("Nome Cadastrante");

        javax.swing.GroupLayout tabDetalhesLayout = new javax.swing.GroupLayout(tabDetalhes);
        tabDetalhes.setLayout(tabDetalhesLayout);
        tabDetalhesLayout.setHorizontalGroup(
            tabDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spObservacao)
                    .addComponent(tabCategoriaMovimentacao)
                    .addGroup(tabDetalhesLayout.createSequentialGroup()
                        .addComponent(pnlInformacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlFinanceiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabDetalhesLayout.createSequentialGroup()
                        .addGroup(tabDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblObservacao)
                            .addGroup(tabDetalhesLayout.createSequentialGroup()
                                .addComponent(lblCadastradoEm)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDataCadastro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCadastradoPor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNomeCadastrante)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabDetalhesLayout.setVerticalGroup(
            tabDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabDetalhesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlInformacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlFinanceiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblObservacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spObservacao, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabCategoriaMovimentacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabDetalhesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblCadastradoEm)
                    .addComponent(lblDataCadastro)
                    .addComponent(lblCadastradoPor)
                    .addComponent(lblNomeCadastrante))
                .addContainerGap())
        );

        tabMenuGeral.addTab("Detalhes", tabDetalhes);

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
                    .addComponent(tabMenuGeral)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabMenuGeral)
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

    private void btnNovoCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoCategoriaActionPerformed
        this.frmPesquisaFinanceiroCategoria.setVisible(true);
    }//GEN-LAST:event_btnNovoCategoriaActionPerformed

    private void tableLancamentoCategoriaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLancamentoCategoriaMousePressed
        if(!this.btnEditarCategoria.isEnabled()) {
            this.btnEditarCategoria.setEnabled(true);
            this.btnExcluirCategoria.setEnabled(true);
        }
    }//GEN-LAST:event_tableLancamentoCategoriaMousePressed

    private void tableLancamentoCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLancamentoCategoriaMouseClicked
        if(evt.getClickCount() == 2) {
            editCategoria();
        }
    }//GEN-LAST:event_tableLancamentoCategoriaMouseClicked

    private void btnExcluirCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirCategoriaActionPerformed
        this.deleteCategoria();
    }//GEN-LAST:event_btnExcluirCategoriaActionPerformed

    private void btnEditarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarCategoriaActionPerformed
        this.editCategoria();
    }//GEN-LAST:event_btnEditarCategoriaActionPerformed

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        //if(this.validacaoFrame()) {
            JSONObject errors = this.lancamentoController.edit(this.loadLancamento());

            if(errors != null) {
                this.parseErrors(errors);
            } else {
                this.notifyObservers();
                this.dispose();
            }
        //}
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPessoaActionPerformed
        this.frmPesquisaPessoa.setVisible(true);
    }//GEN-LAST:event_btnPessoaActionPerformed

    private void btnNovoMovimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoMovimentacaoActionPerformed
        this.frmPesquisaConta.setVisible(true);
    }//GEN-LAST:event_btnNovoMovimentacaoActionPerformed

    private void btnEditarMovimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarMovimentacaoActionPerformed
        this.editMovimentacao();
    }//GEN-LAST:event_btnEditarMovimentacaoActionPerformed

    private void btnExcluirMovimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirMovimentacaoActionPerformed
        this.deleteMovimentacao();
    }//GEN-LAST:event_btnExcluirMovimentacaoActionPerformed

    private void tableLancamentoMovimentacaoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLancamentoMovimentacaoMousePressed
        if(!this.btnEditarMovimentacao.isEnabled()) {
            this.btnEditarMovimentacao.setEnabled(true);
            this.btnExcluirMovimentacao.setEnabled(true);
        }
    }//GEN-LAST:event_tableLancamentoMovimentacaoMousePressed

    private void dtcDataLiquidacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtcDataLiquidacaoMouseClicked
        JOptionPane.showMessageDialog(null, "Passei aqui");
    }//GEN-LAST:event_dtcDataLiquidacaoMouseClicked

    private void tableLancamentoMovimentacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLancamentoMovimentacaoMouseClicked
        if(evt.getClickCount() == 2) {
            editMovimentacao();
        }
    }//GEN-LAST:event_tableLancamentoMovimentacaoMouseClicked

    private void mostraAbaMovimentacao() {
        this.tabCategoriaMovimentacao.add(this.tabMenuMovimentacao.getName(), this.tabMenuMovimentacao);
        this.tabCategoriaMovimentacao.setSelectedComponent(this.tabMenuMovimentacao);
    }
    
    private void escondeAbaMovimentacao() {
        this.tabCategoriaMovimentacao.remove(this.tabMenuMovimentacao);
        this.movimentacoes = new ArrayList(0);
        this.tableLancamentoMovimentacao.reload(this.movimentacoes);
    }
    
    private void addCategoria(FinanceiroCategoria categoria) {
        this.frmCadastroLancamentoCategoria = new FrmCadastroLancamentoCategoria(new LancamentoCategoria(categoria, this.calculaSaldoCategoria()));
        this.frmCadastroLancamentoCategoria.registerObserver(this);
        this.frmCadastroLancamentoCategoria.setVisible(true);
    }
    
    private void editCategoria() {
        LancamentoCategoria categoriaSelected = this.tableLancamentoCategoria.getLancamentoCategoriaSelected();
        
        if(categoriaSelected != null) {
            new FrmCadastroLancamentoCategoria(categoriaSelected).setVisible(true);
        }
    }
    
    private void deleteCategoria() {
        List<LancamentoCategoria> categorias = this.tableLancamentoCategoria.getLancamentoCategoriasSelected();
        
        if(categorias != null) {
            int opcao = JOptionPane.showConfirmDialog(null,
                "Confirma a exclusão deste(s) registro(s)?",
                "Atenção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

            if (opcao == 0) {
                List<LancamentoCategoria> categoriasCadastradas = new ArrayList<>(0);
                
                categorias.forEach(c -> {
                    if(c.getUUID() != null && !c.getUUID().isEmpty()) {
                        categoriasCadastradas.add(c);
                    }
                });
                
                this.lancamentoCategoriaController.deleteLancamentoCategoria(categoriasCadastradas);
                this.categorias.removeAll(categorias);
                this.tableLancamentoCategoria.removeItens((List<Object>) (Object) categorias);
            }
        }
    }
    
    private void addMovimentacao(Conta conta) {
        this.frmCadastroLancamentoMovimentacao = new FrmCadastroLancamentoMovimentacao(new LancamentoMovimentacao(conta, this.dtcDataLiquidacao.getDate(), this.calculaSaldoMovimentacao()), this.tipoLancamento);
        this.frmCadastroLancamentoMovimentacao.registerObserver(this);
        this.frmCadastroLancamentoMovimentacao.setVisible(true);
    }
    
    private void editMovimentacao() {
        LancamentoMovimentacao movimentacaoSelected = this.tableLancamentoMovimentacao.getLancamentoMovimentacaoSelected();
        
        if(movimentacaoSelected != null) {
            new FrmCadastroLancamentoMovimentacao(movimentacaoSelected, this.lancamento.getTipo()).setVisible(true);
        }
    }
    
    private void deleteMovimentacao() {
        List<LancamentoMovimentacao> movimentacoes = this.tableLancamentoMovimentacao.getLancamentoMovimentacaosSelected();
        
        if(movimentacoes != null) {
            int opcao = JOptionPane.showConfirmDialog(null,
                "Confirma a exclusão deste(s) registro(s)?",
                "Atenção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);

            if (opcao == 0) {
                List<LancamentoMovimentacao> movimentacoesCadastradas = new ArrayList<>(0);
                
                movimentacoes.forEach(c -> {
                    if(c.getUUID() != null && !c.getUUID().isEmpty()) {
                        movimentacoesCadastradas.add(c);
                    }
                });
                
                this.lancamentoMovimentacaoController.deleteLancamentoMovimentacao(movimentacoesCadastradas);
                this.movimentacoes.removeAll(movimentacoes);
                this.tableLancamentoMovimentacao.removeItens((List<Object>) (Object) movimentacoes);
            }
        }
    }
    
    private Lancamento loadLancamento() {
        
        if(this.lancamento == null) {
            this.lancamento = new Lancamento();
            this.lancamento.setTipo(this.tipoLancamento);
        }
        
        if(this.pessoa != null) {
            this.lancamento.setPessoa(this.pessoa);
        }
        
        switch(this.tipoLancamento) {
            case EnumTipoLancamento.CONTA_PAGAR:
                this.lancamento.setValorFinal(this.txtValorFinal.getValue().negate());
            break;
            case EnumTipoLancamento.CONTA_RECEBER:
                this.lancamento.setValorFinal(this.txtValorFinal.getValue());
            break;
        }
        
        this.lancamento.setCadastradoPor(UsuarioLogado.getInstance());
        this.lancamento.setDescricao(this.txtDescricao.getText());
        this.lancamento.setDataCadastro(new Date());
        this.lancamento.setDataEmissao(this.dtcDataEmissao.getDate());
        this.lancamento.setDataVencimento(this.dtcDataVencimento.getDate());
        this.lancamento.setDataLiquidacao(this.dtcDataLiquidacao.getDate());
        this.lancamento.setObservacao(this.txaObservacao.getText());
        
        this.lancamento.setCategorias(this.categorias);
        this.lancamento.setMovimentacoes(this.movimentacoes);
        
        return this.lancamento;
    }
    
    private BigDecimal calculaSaldoCategoria() {
                
        BigDecimal valorFinal = BigDecimal.ZERO;
        BigDecimal valorCategoria = BigDecimal.ZERO;
        BigDecimal resultadoFinal = BigDecimal.ZERO;
        
        //Trasforma valor em negativo ou positivo para realizar o calculo de subtração
        switch(this.tipoLancamento) {
            case EnumTipoLancamento.CONTA_PAGAR:
                valorFinal = this.txtValorFinal.getValue().plus();
            break;
            case EnumTipoLancamento.CONTA_RECEBER:
                valorFinal = this.txtValorFinal.getValue();
            break;
        }
                
        valorCategoria = this.calculaCategorias();     
        
        resultadoFinal = valorFinal.subtract(valorCategoria);
        
        return resultadoFinal;
    }
    
    private BigDecimal calculaCategorias() {
        
        BigDecimal valorCategoria = BigDecimal.ZERO;
        
        for(LancamentoCategoria c : this.categorias) {
            switch(c.getCategoria().getTipo()) {
                case EnumTipoCategoria.DESPESA:
                    valorCategoria = valorCategoria.add(c.getValor().negate());
                break;
                case EnumTipoCategoria.RECEITA:
                    valorCategoria = valorCategoria.subtract(c.getValor().plus());
                break;
            }
        }
        
        return valorCategoria;
    }
    
    private BigDecimal calculaSaldoMovimentacao() {
        
        BigDecimal valorFinal = BigDecimal.ZERO;
        BigDecimal valorMovimentacao = BigDecimal.ZERO;
        BigDecimal resultadoFinal = BigDecimal.ZERO;
        
        //Trasforma valor em negativo ou positivo para realizar o calculo de subtração
        switch(this.tipoLancamento) {
            case EnumTipoLancamento.CONTA_PAGAR:
                valorFinal = this.txtValorFinal.getValue().negate();
            break;
            case EnumTipoLancamento.CONTA_RECEBER:
                valorFinal = this.txtValorFinal.getValue();
            break;
        }
        
        valorMovimentacao = this.calculaMovimentacoes();     
        
        resultadoFinal = valorFinal.subtract(valorMovimentacao);
        
        return resultadoFinal;
    }
    
    private BigDecimal calculaMovimentacoes() {
        
        BigDecimal valorMovimentacao = BigDecimal.ZERO;
        
        for(LancamentoMovimentacao c : this.movimentacoes) {
            valorMovimentacao = valorMovimentacao.add(c.getValor());
        }
        
        return valorMovimentacao;
    }
    
    private void parseErrors(JSONObject errors) {
        
        errors.keys().forEachRemaining((String key) -> {
            
            JSONArray obj = errors.getJSONArray(key);

            JComponent comp = null;
            
            switch(key) {
                case "descricao":
                    comp = this.txtDescricao;
                break;
                case "valorFinal":
                    comp = this.txtValorFinal;
                break;
                case "dataEmissao":
                    comp = this.dtcDataEmissao;
                break;
                case "dataVencimento":
                    comp = this.dtcDataVencimento;
                break;
                case "saldoCategoria":
                    comp = this.tabMenuCategoria;
                break;
                case "saldoMovimentacao":
                    comp = this.tabMenuMovimentacao;
                break;
            }
            
            if(this.balloonTip == null && comp != null) {
                this.balloonTip = new BalloonTip(comp, obj.getString(0));
            } else if(comp != null && !this.balloonTip.isVisible()) {
                this.balloonTip = new BalloonTip(comp, obj.getString(0));
            }
        });
    }
    
    private boolean validacaoFrame() {
        
        Date dataEmissao = this.dtcDataEmissao.getDate();
        Date dataVencimento = this.dtcDataVencimento.getDate();
        Date dataLiquidacao = this.dtcDataLiquidacao.getDate();
        
        BigDecimal valorFinal = this.txtValorFinal.getValue();
        BigDecimal saldoCategorias = this.calculaSaldoCategoria();
        BigDecimal saldoMovimentacoes = this.calculaSaldoMovimentacao();
        
        /*
        if(valorFinal.equals(BigDecimal.ZERO)) {
            JOptionPane.showMessageDialog(null, "O valor do lançamento não pode ser zero.");
            return false;
        }*/
        
        /*
        if(dataEmissao == null) {
            JOptionPane.showMessageDialog(null, "Data de emissão não pode estar vázia.");
            return false;
        }*/
        
        /*
        if(dataVencimento == null) {
            JOptionPane.showMessageDialog(null, "Data de vencimento não pode estar vázia.");
            return false;
        } */           
        
        if(dataVencimento.after(dataEmissao) && DateUtils.isSameDay(dataEmissao, dataVencimento) == false) {
            JOptionPane.showMessageDialog(null, "Data de vencimento não pode ser inferior a data de emissão.");
            return false;
        }
        
        if(dataLiquidacao != null) {
            if(dataLiquidacao.before(dataEmissao) && DateUtils.isSameDay(dataEmissao, dataLiquidacao) == false) {
                JOptionPane.showMessageDialog(null, "Date de liquidação não pode ser inferior a data de emissão.");
                return false;
            }
        }

        if(this.pessoa == null) {
            JOptionPane.showMessageDialog(null, "Pessoa não pode estar vázio.");
            return false;
        }        
        
        /*
        if(!saldoCategorias.equals(BigDecimal.ZERO)) {
            JOptionPane.showMessageDialog(null, "Valor das categorias não batem com o valor final do lançamento.");
            return false;
        }*/
        
        /*
        if(dataLiquidacao != null) {
            if(!saldoMovimentacoes.equals(BigDecimal.ZERO)) {
                JOptionPane.showMessageDialog(null, "Valor das movimentações não batem com o valor final do lançamento.");
                return false;
            }
        }*/
        
        return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditarCategoria;
    private javax.swing.JButton btnEditarMovimentacao;
    private javax.swing.JButton btnExcluirCategoria;
    private javax.swing.JButton btnExcluirMovimentacao;
    private javax.swing.JButton btnNovoCategoria;
    private javax.swing.JButton btnNovoMovimentacao;
    private javax.swing.JButton btnPessoa;
    private com.toedter.calendar.JDateChooser dtcDataEmissao;
    private com.toedter.calendar.JDateChooser dtcDataLiquidacao;
    private com.toedter.calendar.JDateChooser dtcDataVencimento;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JLabel lblCadastradoEm;
    private javax.swing.JLabel lblCadastradoPor;
    private javax.swing.JLabel lblDataCadastro;
    private javax.swing.JLabel lblDataEmissao;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblDocumento;
    private javax.swing.JLabel lblLiquidacao;
    private javax.swing.JLabel lblNomeCadastrante;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblObservacao;
    private javax.swing.JLabel lblPessoa;
    private javax.swing.JLabel lblValorFinal;
    private javax.swing.JLabel lblVencimento;
    private javax.swing.JPanel pnlFinanceiro;
    private javax.swing.JPanel pnlInformacao;
    private javax.swing.JScrollPane spCategoria;
    private javax.swing.JScrollPane spMovimentacao;
    private javax.swing.JScrollPane spObservacao;
    private javax.swing.JTabbedPane tabCategoriaMovimentacao;
    private javax.swing.JPanel tabDetalhes;
    private javax.swing.JPanel tabMenuCategoria;
    private javax.swing.JTabbedPane tabMenuGeral;
    private javax.swing.JPanel tabMenuMovimentacao;
    private br.com.tamarozzi.ui.table.TableLancamentoCategoria tableLancamentoCategoria;
    private br.com.tamarozzi.ui.table.TableLancamentoMovimentacao tableLancamentoMovimentacao;
    private javax.swing.JToolBar toolBarCategoria;
    private javax.swing.JToolBar toolBarMovimentacao;
    private javax.swing.JTextArea txaObservacao;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtPessoa;
    private br.com.tamarozzi.util.JNumberFormatFieldUtil txtValorFinal;
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
            ob.update(this.lancamento);
        }
    }

    @Override
    public void update(Object obj) {
        if(obj instanceof LancamentoCategoria) {
            this.categorias.add((LancamentoCategoria) obj);
            tableLancamentoCategoria.addItem(obj);
        }
        
        if(obj instanceof LancamentoMovimentacao) {
            this.movimentacoes.add((LancamentoMovimentacao) obj);
            tableLancamentoMovimentacao.addItem(obj);
        }
    }
}
