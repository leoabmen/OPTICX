/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opticx1;

import javax.swing.JProgressBar;

/**
 *
 * @author LEO
 */
public class Cargar_splash_ayuda_ventanas extends Thread {
    JProgressBar pprogreso;
    public Cargar_splash_ayuda_ventanas(JProgressBar ppprogreso){
        super();
        this.pprogreso=ppprogreso;
    }
    public void run(){
        for(int i=1;i<=100;i++){
            pprogreso.setValue(i);
            pausa(0);
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

