/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ExportarArchivo;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Component;
import sun.applet.Main;






//archivos
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
//librerias ajenas a itext
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
//import javax.swing.text.Document;
import sun.applet.Main;

public class LlamarPdf {
   
    private File ruta_destino=null;
     File archivo = new File ("C:\\archivo.txt");
    
    public LlamarPdf(){    
    }
    
    /* metodo que hace uso de la clase itext para manipular archivos PDF*/
   /* public void crear_PDF(String t, String a, String s, String k, String c){
        //abre ventana de dialogo "guardar"
        Colocar_Destino();
        //si destino es diferente de null
        if(this.ruta_destino!=null){
            
            try {
                // se crea instancia del documento
                Document mipdf = new Document() {};
                // se establece una instancia a un documento pdf
                PdfWriter.getInstance(mipdf, new FileOutputStream(this.ruta_destino + ".pdf"));
                mipdf.open();// se abre el documento
                mipdf.addTitle(t); // se añade el titulo
                mipdf.addAuthor(a); // se añade el autor del documento
                mipdf.addSubject(s); //se añade el asunto del documento
                mipdf.addKeywords(k); //Se agregan palabras claves  
                mipdf.add(new Paragraph(c)); // se añade el contendio del PDF
                mipdf.close(); //se cierra el PDF&
                
                
                JOptionPane.showMessageDialog(null,"Documento PDF creado");
            } catch (DocumentException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
           }    catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }   
                
            
            
        }  
              
    
    }*/  
    
    public String CopiarArchivo(String origen, String destino) throws IOException {
        String destino2,desFinal="";
       destino2=Colocar_Destino(destino);
        Path FROM = Paths.get(origen);
       // Path TO = Paths.get(destino);
        Path TO = Paths.get(destino2);
     //   desfinal=TO.toString();
         
        //sobreescribir el fichero de destino, si existe, y copiar
        // los atributos, incluyendo los permisos rwx
        if(this.ruta_destino!=null){
        CopyOption[] options = new CopyOption[]{
          StandardCopyOption.REPLACE_EXISTING,
          StandardCopyOption.COPY_ATTRIBUTES
        }; 
        Files.copy(FROM, TO, options);
        }
        return destino2;
    }
    /* abre la ventana de dialogo GUARDAR*/
    public String Colocar_Destino(String ruta){
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF","pdf","PDF");
       JFileChooser fileChooser = new JFileChooser();       
       fileChooser.setFileFilter(filter);
       int result = fileChooser.showSaveDialog(null);
       if ( result == JFileChooser.APPROVE_OPTION ){   
           this.ruta_destino = fileChooser.getSelectedFile().getAbsoluteFile();
           
           String f1=ruta_destino.toString()+ ".pdf";
           //txtDir.setText(f1);
          ruta=f1;
           String nl = System.getProperty("line.separator");
          JOptionPane.showMessageDialog(null, "Archivo guardado en: " + nl + ruta);
        
        }  else  if  ( result ==  JFileChooser . CANCEL_OPTION )  { 
   // System . out . println ( "Cancel fue seleccionado");
   // JOptionPane.showMessageDialog(null, "Cancelado");
  /*   //Creamos el objeto JFileChooser
JFileChooser fc=new JFileChooser();
        Component contentPane = null;
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
 
//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
int seleccion=fc.showOpenDialog(contentPane);
 
//Si el usuario, pincha en aceptar
if(seleccion==JFileChooser.APPROVE_OPTION){
 
    //Seleccionamos el fichero
    File fichero=fc.getSelectedFile();
 
    //Ecribe la ruta del fichero seleccionado en el campo de texto
    ruta=fichero.getAbsolutePath();
  */     }
      return ruta;
    } 
    
    
}


