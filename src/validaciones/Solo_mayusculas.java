/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validaciones;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author LEO
 */
public class Solo_mayusculas extends PlainDocument {
    
    public void insertString(int offset, String str, AttributeSet atrr) throws BadLocationException{
             super.insertString(offset,str.toUpperCase(),atrr);
}
    
}
