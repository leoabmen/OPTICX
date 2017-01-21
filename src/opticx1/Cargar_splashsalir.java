/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opticx1;

import javax.swing.JProgressBar;

/**
 *
 * @author IsraelCM
 */
public class Cargar_splashsalir extends Thread{
    JProgressBar progreso;
    public Cargar_splashsalir(JProgressBar progreso){
        super();
        this.progreso=progreso;
    }
    @Override
    public void run(){
        for(int i=1;i<=100;i++){
            progreso.setValue(i);
            pausa(50);
        }
    }
    public void pausa(int mlSeg){
        try{
            Thread.sleep(mlSeg);
        }
        catch(Exception e){
            
        }
    }
}
