/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.util;

import br.com.tamarozzi.dao.impl.PessoaDaoImpl;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Panda
 */
public class ClassMergeUtil {
    
    public static void merge(Object objectMain, Object objectCopy) {
                
        for (Field field : objectMain.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                field.set(objectMain, field.get(objectCopy));    
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(PessoaDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
