package br.com.tamarozzi.util;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
 
/**
 * Campo para a inser��o de n�meros com base em um formato.
 *
 * @author Dyorgio da Silva Nascimento
 */
public class JNumberFormatFieldUtil extends JTextField {
    private static final long serialVersionUID = -7506506392528621022L;
    private static final NumberFormat MONETARY_FORMAT = new DecimalFormat("R$ #,##0.00");
    private NumberFormat numberFormat;
    private int limit = -1;
 
    public JNumberFormatFieldUtil(int casasDecimais) {
        this(new DecimalFormat((casasDecimais == 0 ? "#,##0" : "#,##0.") + makeZeros(casasDecimais)));
    }
 
    public JNumberFormatFieldUtil() {
        this(MONETARY_FORMAT);
    }
    
    public JNumberFormatFieldUtil(NumberFormat format, int limit) {// define o formato do
        this.setLimit(limit);
    	// n�mero
        numberFormat = format;// alinhamento horizontal para o texto
        setHorizontalAlignment(RIGHT);// documento respons�vel pela formata��o
        // do campo
        setDocument(new PlainDocument() {
            private static final long serialVersionUID = 1L;
 
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                String text = new StringBuilder(JNumberFormatFieldUtil.this.getText().replaceAll("[^0-9]", "")).append(str.replaceAll("[^0-9]", "")).toString();
                super.remove(0, getLength());
                if (text.isEmpty()) {
                    text = "0";
                } else {
                    text = new BigInteger(text).toString();
                }
                super.insertString(0, numberFormat.format(new BigDecimal(getLimit() > 0 == text.length() > getLimit() ? text.substring(0, getLimit()) : text).divide(new BigDecimal(Math.pow(10, numberFormat.getMaximumFractionDigits())))), a);
            }
 
            @Override
            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);
                if (len != getLength()) {
                    insertString(0, "", null);
                }
            }
        });// mantem o cursor no final
        // do campo
        addCaretListener(new CaretListener() {
            boolean update = false;
 
            @Override
            public void caretUpdate(CaretEvent e) {
                if (!update) {
                    update = true;
                    setCaretPosition(getText().length());
                    update = false;
                }
            }
        });// limpa o campo se
        // apertar DELETE
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    setText("");
                }
            }
        });// formato
        // inicial
        setText("0");
        setCaretPosition(getText().length());
    }
 
    public JNumberFormatFieldUtil(NumberFormat format) {// define o formato do
        // n�mero
        numberFormat = format;// alinhamento horizontal para o texto
        setHorizontalAlignment(RIGHT);// documento respons�vel pela formata��o
        // do campo
        setDocument(new PlainDocument() {
            private static final long serialVersionUID = 1L;
 
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                String text = new StringBuilder(JNumberFormatFieldUtil.this.getText().replaceAll("[^0-9]", "")).append(str.replaceAll("[^0-9]", "")).toString();
                super.remove(0, getLength());
                if (text.isEmpty()) {
                    text = "0";
                } else {
                    text = new BigInteger(text).toString();
                }
                super.insertString(0, numberFormat.format(new BigDecimal(getLimit() > 0 == text.length() > getLimit() ? text.substring(0, getLimit()) : text).divide(new BigDecimal(Math.pow(10, numberFormat.getMaximumFractionDigits())))), a);
            }
 
            @Override
            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);
                if (len != getLength()) {
                    insertString(0, "", null);
                }
            }
        });// mantem o cursor no final
        // do campo
        addCaretListener(new CaretListener() {
            boolean update = false;
 
            @Override
            public void caretUpdate(CaretEvent e) {
                if (!update) {
                    update = true;
                    setCaretPosition(getText().length());
                    update = false;
                }
            }
        });// limpa o campo se
        // apertar DELETE
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    setText("");
                }
            }
        });// formato
        // inicial
        setText("0");
        setCaretPosition(getText().length());
    }
 
    /***
     * Define um valor BigDecimal ao campo**
     *
     * @param value
     */
    public void setValue(BigDecimal value) {
        super.setText(numberFormat.format(value));
    }
 
    /***
     * Recupera um valor BigDecimal do campo**
     *
     * @return
     */
    public final BigDecimal getValue() {
        return new BigDecimal(getText().replaceAll("[^0-9]", "")).divide(new BigDecimal(Math.pow(10, numberFormat.getMaximumFractionDigits())));
    }
 
    /***
     * Recupera o formatador atual do campo**
     *
     * @return
     */
    public NumberFormat getNumberFormat() {
        return numberFormat;
    }
 
    /***
     * Define o formatador do campo** @param numberFormat
     */
    public void setNumberFormat(NumberFormat numberFormat) {
        this.numberFormat = numberFormat;
    }
 
    /***
     * Preenche um StringBuilder com zeros** @param zeros*
     *
     * @return
     */
    private static final String makeZeros(int zeros) {
        if (zeros >= 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < zeros; i++) {
                builder.append('0');
            }
            return builder.toString();
        } else {
            throw new RuntimeException("N�mero de casas decimais inv�lida (" + zeros + ")");
        }
    }
 
    /***
     * Recupera o limite do campo.** @return
     */
    public int getLimit() {
        return limit;
    }
 
    /***
     * Define o limite do campo, limit < 0 para deixar livre (default) Ignora os
     * pontos e virgulas do formato, conta* somente com os n�meros** @param
     * limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }
 
    // testes, pode ser removido
    /*public static void main(String[] args) {
        JFrame frame = new JFrame("Teste do campo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new JNumberFormatFieldUtil(new DecimalFormat("0.000")) {
            {// limita a 4
                // caracteres
                setLimit(4);
            }
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }*/
}
