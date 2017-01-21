/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tickets;
import java.awt.*;
import java.awt.print.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.Doc;
import javax.print.PrintException;
import javax.print.ServiceUI;
import javax.print.attribute.*;

public class TicketTres {

    private String contentTicket = "    COMERCIAL {{nameLocal}}\n"
            + "    MERCADO # 2 - PISCO\n"
            + "    =========================================\n"
            + "    Ticket # {{ticket}}\n"
            + "    LE ATENDIO: {{cajero}}\n"
            + "    {{dateTime}}\n"
            + "    Cant.  Descripcion              Importe\n"
            + "    =========================================\n"
            + "    {{items}}\n"
            + "    =========================================\n"
            + "    SUBTOTAL: {{subTotal}}\n"
            + "    IGV: {{tax}}      |     RECIBIDO: {{recibo}}\n"
            + "    TOTAL: {{total}}     |    CAMBIO: {{change}}\n"
            + "    =========================================\n"
            + "    GRACIAS POR SU COMPRA...\n"
            + "                ******::::::::*******"
            + "\n           "
            + "\n           "
            + "\n           "
            + "\n           "
            + "\n           "
            + "\n           "
            + "\n           "
            + "\n           "
            + "\n           "
            + "\n           ";

    //El constructor que setea los valores a la instancia
    public TicketTres(String nameLocal, String expedition, String ticket, String caissier, String dateTime, String items, String subTotal, String tax, String total, String recibo, String change) {
        this.contentTicket = this.contentTicket.replace("{{nameLocal}}", nameLocal);
        this.contentTicket = this.contentTicket.replace("{{expedition}}", expedition);
        this.contentTicket = this.contentTicket.replace("{{ticket}}", ticket);
        this.contentTicket = this.contentTicket.replace("{{cajero}}", caissier);
        this.contentTicket = this.contentTicket.replace("{{dateTime}}", dateTime);
        this.contentTicket = this.contentTicket.replace("{{items}}", items);
        this.contentTicket = this.contentTicket.replace("{{subTotal}}", subTotal);
        this.contentTicket = this.contentTicket.replace("{{tax}}", tax);
        this.contentTicket = this.contentTicket.replace("{{total}}", total);
        this.contentTicket = this.contentTicket.replace("{{recibo}}", recibo);
        this.contentTicket = this.contentTicket.replace("{{change}}", change);
    }


    public void Imprimir() {
        //Especificamos el tipo de dato a imprimir
        //Tipo: bytes; Subtipo: autodetectado
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;

        //Aca obtenemos el servicio de impresion por defatul
        //Si no quieres ver el dialogo de seleccionar impresora usa esto
        //PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();


        //Con esto mostramos el dialogo para seleccionar impresora
        //Si quieres ver el dialogo de seleccionar impresora usalo
        //Solo mostrara las impresoras que soporte arreglo de bits
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        PrintService service = ServiceUI.printDialog(null, 700, 200, printService, defaultService, flavor, pras);

        //Creamos un arreglo de tipo byte
        byte[] bytes;

        //Aca convertimos el string(cuerpo del ticket) a bytes tal como
        //lo maneja la impresora(mas bien ticketera :p)

        bytes = this.contentTicket.getBytes();

        //Creamos un documento a imprimir, a el se le appendeara
        //el arreglo de bytes
        Doc doc = new SimpleDoc(bytes, flavor, null);

        //Creamos un trabajo de impresiÃ³n
        DocPrintJob job = service.createPrintJob();

        //Imprimimos dentro de un try de a huevo
        try {
            //El metodo print imprime
            job.print(doc, null);
        } catch (Exception er) {
            JOptionPane.showMessageDialog(null, "Error al imprimir: " + er.getMessage());
        }
    }
}

//Usar clase (Borrar comentarios xD)
//===============================
//String productos = "";
//                            int filas = jTable1.getRowCount();
//
//                            int espacio1 = 7;
//                            int espacio2 = 23;
//                            int espacio3 = 8;
//
//                            int resta1 = 0;
//                            int resta2 = 0;
//
//                            String espacios1 = "";
//                            String espacios2 = "";
//
//                            String can = "";
//                            String pro = "";
//                            String pre = "";
//
//                            for (int i = 0; i < filas; i++) {
//                                if (dtm.getValueAt(i, 1).toString().length() < espacio1) {
//                                    can = dtm.getValueAt(i, 1).toString();
//                                    resta1 = espacio1 - dtm.getValueAt(i, 1).toString().length();
//
//                                } else {
//                                    can = dtm.getValueAt(i, 1).toString().substring(0, espacio1 - 1);
//                                }
//                                if (dtm.getValueAt(i, 2).toString().length() < espacio2) {
//                                    resta2 = espacio2 - dtm.getValueAt(i, 2).toString().length();
//                                    pro = dtm.getValueAt(i, 2).toString();
//                                } else {
//                                    pro = dtm.getValueAt(i, 2).toString().substring(0, espacio2 - 1);
//                                    pro += " ";
//                                }
//                                if (dtm.getValueAt(i, 4).toString().length() < espacio3) {
//                                    pre = dtm.getValueAt(i, 4).toString();
//                                } else {
//                                    pre = dtm.getValueAt(i, 4).toString().substring(0, espacio3 - 1);
//                                }
//                                for (int j = 0; j < resta1; j++) {
//                                    espacios1 += " ";
//                                }
//                                for (int j = 0; j < resta2; j++) {
//                                    espacios2 += " ";
//                                }
//                                productos += can + espacios1 + pro + espacios2 + pre + "\n    ";
//                                espacios1 = "";
//                                espacios2 = "";
//                            }
//                            try {
//                                
//                                Ticket tk = new Ticket("CHANCHOCLOC", "LOCAL PRINCIPAL", tbxserie.getText() + " - " + tbxnrodocumento.getText(), "-", jdprFecVenta.getDate().toString(), productos, jtfdSubTotal.getText(), jtfdIGV.getText(), jlblTotal.getText(), String.valueOf(recidido), String.valueOf(vuelto));
////                                JOptionPane.showMessageDialog(rootPane, productos);
////                                llamando al metodo                            
//                                tk.Imprimir();
//                            } catch (Exception e) {
//                                JOptionPane.showMessageDialog(rootPane, "No se imprimira el ticket");
//                            }
