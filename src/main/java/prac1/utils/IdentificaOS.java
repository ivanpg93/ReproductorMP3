/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prac1.utils;

/**
 * Classe que incorpora funcionalitats per a identificar el sistema operatiu amfitrió
 * @author manel
 */
public class IdentificaOS {

     private final static String OS = System.getProperty("os.name").toLowerCase();
     
     /***
      * retorna el OS amfitrió
      * @return 
      */
     public static TipusOS getOS()
     {
        TipusOS ret = null;
        
        if (OS.contains("win"))
            ret = IdentificaOS.TipusOS.WIN;
        else if (OS.contains("mac"))
            ret = IdentificaOS.TipusOS.MAC;
        else if (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0)
            ret = IdentificaOS.TipusOS.LINUX;
        else if (OS.contains("sunos"))
            ret = IdentificaOS.TipusOS.SUN;
        
        return ret;
     }
    
    public enum TipusOS{
        
        WIN,LINUX,SUN,MAC;
    }
    
}
