/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prac1.utils;

import java.nio.file.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author manel
 */
public class FileUtils {
    
    /***
     * Si la URL és basura de tipus Windows, elimina el nom de la unitat
     * @return 
     */
    public static String normalizeURLFormat(String url)
    {
        String ret = "";
        
        if (IdentificaOS.getOS() == IdentificaOS.TipusOS.WIN)
            ret = url.replace("[A-Z]{1}:", "");
        
        return ret;
    }
    
    /***
     * Retorna la ruta al MP3 de prova
     * La ruta es torna en format URL. 
     * 
     * P.ex: 
     * 
     *  Si s'executa sobre Windows, ho retorna en el format: file:/C:/Users/abcd1234/Documents/NetBeansProjects/m03uf5prac1_22_23_enunciat/target/classes/sounds/test_sound.mp3
     *  Si s'executa sobre Linux, ho retorna en el format: file:/home/manel/NetBeansProjects/m03uf5prac1_22_23_enunciat/target/classes/sounds/test_sound.mp3
     * 
     *  https://en.wikipedia.org/wiki/File_URI_scheme
     * 
     * @param instance instància des de la qual es crida (this)
     * @return 
     */
    public static String getTestMP3(Object instance)
    {
        return instance.getClass().getClassLoader().getResource("sounds/test_sound.mp3").toString();
    }
    
    /***
     * Retorna una icona
     * 
     * @param instance
     * @param song
     * @return 
     */
    public static String getSong(Object instance, String song)
    {
        return instance.getClass().getClassLoader().getResource("sounds/" + song).toString();
    }
    
    /***
     * Retorna una icona
     * 
     * @param instance
     * @param nom
     * @return 
     */
    public static String getIcona(Object instance, String nom)
    {
        return instance.getClass().getClassLoader().getResource("icons/" + nom).toString();
    }
    
    /***
     * Permet seleccionar un fitxer MP3 d'una unitat de disc
     * 
     * @return 
     */
    public static Path getMP3Fromfile()
    {
        Path ret = null;
        
        Stage stage1 = new Stage();
        
        FileChooser filechooser1 = new FileChooser();
        
        // Añadimos un filtro para que sólo se puedan añadir archivos con extensión MP3
        FileChooser.ExtensionFilter filter;
        filter = new FileChooser.ExtensionFilter("MP3-File", "*.mp3");
        filechooser1.getExtensionFilters().add(filter);
        
        filechooser1.setTitle("Seleccionar fixter MP3");
        Path fitxerMP3 = filechooser1.showOpenDialog(stage1).toPath();
        
        if (fitxerMP3 != null)
            ret = fitxerMP3;
        
        return ret;
    }
    
}
