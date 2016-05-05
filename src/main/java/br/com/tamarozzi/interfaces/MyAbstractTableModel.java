/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tamarozzi.interfaces;

import java.util.List;

/**
 *
 * @author Wilson
 */
public interface MyAbstractTableModel {
    
    public void reload(List<Object> itens);
    
    public void addItem(Object item);
    
    public void addItens(List<Object> itens);
    
    public void removeItem(Object item);
    
    public void removeItens(List<Object> itens);
    
    public Object getItemAt(int index);
}
