/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author Wilson
 */
public class MoneyFormatUtil {
    
    private static DecimalFormat REAL_FORMAT = new DecimalFormat("R$ #,##0.00");
    
    public static String formatReal(BigDecimal valor) {
        return REAL_FORMAT.format(valor);
    }
}
