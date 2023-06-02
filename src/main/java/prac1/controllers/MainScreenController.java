/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package prac1.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import prac1.utils.FileUtils;
import prac1.utils.IdentificaOS;

/**
 * FXML Controller class
 *
 * @author manel
 */
public class MainScreenController implements Initializable {

    Media media = null;

    MediaPlayer player = null;

    //Llista d'imatges del reproductor
    List<Image> imageList = new ArrayList();

    // Colección que almacena las rutas de las canciones
    ObservableList<String> elements = FXCollections.observableArrayList();

    // Colección que almacena los nombres de las canciones
    ObservableList<String> songs = FXCollections.observableArrayList();

    // ListView que muestra la lista de reproducción
    @FXML
    private ListView<String> lv_1;

    // Botón Play
    @FXML
    private Button btnPlay;

    // Botón Següent cançó
    @FXML
    private Button segSong;

    // Botón Anterior Cançó
    @FXML
    private Button prevSong;

    // Botón Mute
    @FXML
    private Button btnMute;

    // Botón Añadir Canción
    @FXML
    private Button btnAddSong;

    // Botón Eliminar Canción
    @FXML
    private Button btnDelSong;

    // ImagenView
    @FXML
    private ImageView imagen;

    // Botón Pause
    @FXML
    private Button btnPause;

    // Botón Stop
    @FXML
    private Button btnStop;

    // Botón avanzar 15s la canción
    @FXML
    private Button btnEndevant;

    // Botón retroceder 15s la canción
    @FXML
    private Button btnEndarrere;

    // Botón Reproducción en Modo Bucle
    @FXML
    private Button btnBucle;

    // Botón Reproducción en Modo Aleatorio
    @FXML
    private Button btnAleatori;

    // Función que carga los iconos en los botones
    private void putIcons() {
        // Indicamos las URL para cada icono    
        URL linkPlay = getClass().getResource("/icons/play.png");
        URL linkMute = getClass().getResource("/icons/mute.png");
        URL linkAddSong = getClass().getResource("/icons/addSong.png");
        URL linkDelSong = getClass().getResource("/icons/delSong.png");
        URL linkSegSong = getClass().getResource("/icons/segCanço.PNG");
        URL linkPrevSong = getClass().getResource("/icons/antCanço.PNG");
        URL linkPause = getClass().getResource("/icons/pause.png");
        URL linkStop = getClass().getResource("/icons/stop.png");
        URL linkEndevant = getClass().getResource("/icons/endevant.png");
        URL linkEndarrere = getClass().getResource("/icons/endarrere.png");
        URL linkBucle = getClass().getResource("/icons/bucle.png");
        URL linkAleatori = getClass().getResource("/icons/Aleatorio.png");

        // Creamos las imágenes de cada icono con el tamaño deseado
        Image imagePlay = new Image(linkPlay.toString(), 24, 24, false, true);
        Image imageMute = new Image(linkMute.toString(), 24, 24, false, true);
        Image imageAddSong = new Image(linkAddSong.toString(), 28, 28, false, true);
        Image imageDelSong = new Image(linkDelSong.toString(), 28, 28, false, true);
        Image imageSegSong = new Image(linkSegSong.toString(), 24, 24, false, true);
        Image imagePrevSong = new Image(linkPrevSong.toString(), 24, 24, false, true);
        Image imagePause = new Image(linkPause.toString(), 24, 24, false, true);
        Image imageStop = new Image(linkStop.toString(), 24, 24, false, true);
        Image imageEndevant = new Image(linkEndevant.toString(), 24, 24, false, true);
        Image imageEndarrere = new Image(linkEndarrere.toString(), 24, 24, false, true);
        Image imageBucle = new Image(linkBucle.toString(), 24, 24, false, true);
        Image imageAleatori = new Image(linkAleatori.toString(), 24, 24, false, true);

        // Seteamos gráficamente los botones para aplicarles los iconos
        btnPlay.setGraphic(new ImageView(imagePlay));
        btnMute.setGraphic(new ImageView(imageMute));
        btnAddSong.setGraphic(new ImageView(imageAddSong));
        btnDelSong.setGraphic(new ImageView(imageDelSong));
        segSong.setGraphic(new ImageView(imageSegSong));
        prevSong.setGraphic(new ImageView(imagePrevSong));
        btnPause.setGraphic(new ImageView(imagePause));
        btnStop.setGraphic(new ImageView(imageStop));
        btnEndevant.setGraphic(new ImageView(imageEndevant));
        btnEndarrere.setGraphic(new ImageView(imageEndarrere));
        btnBucle.setGraphic(new ImageView(imageBucle));
        btnAleatori.setGraphic(new ImageView(imageAleatori));
    }

    //Funció que afegeix les imatges del reproductor
    private void imagesLoad() {
        imageList.add(new Image("/icons/creador_del_sexo.jpg"));
        imageList.add(new Image("/icons/sponja.png"));
        imageList.add(new Image("/icons/bachata.jpg"));
        imageList.add(new Image("/icons/jorgeElSalvaje.jpg"));
        imageList.add(new Image("/icons/sotoobo.png"));
    }

    @FXML
    private void onActionBtnAfegir(ActionEvent event) {

        // Añadimos la ruta y el nombre de la canción a la colección correspondiente
        // Obtenemos el archivo mp3 en la lista de reproducción
        String path = FileUtils.getMP3Fromfile().toString();
        elements.add(path);

        // Identificamos si estamos en SO Windows o Linux para formatear el nombre
        /* Formateamos el nombre del archivo para que sólo aparezca el nombre 
        de la canción y no toda la ruta*/
        if (IdentificaOS.getOS() == IdentificaOS.TipusOS.WIN) {
            String song = path.substring(path.lastIndexOf('\\') + 1, path.lastIndexOf('.'));
            songs.add(song);
        } else if (IdentificaOS.getOS() == IdentificaOS.TipusOS.LINUX) {
            String song = path.substring(path.lastIndexOf('/') + 1, path.lastIndexOf('.'));
            songs.add(song);
        }
    }

    @FXML
    private void onActionBtnEliminar(ActionEvent event) {

        // Obtenemos la posición seleccionada de la lista de reproducción
        int posicioElementSeleccionat = lv_1.getSelectionModel().getSelectedIndex();

        /* Si la posición es correcta (puede ser -1 si no hay ninguna selección), 
        eliminamos el elemento correspondiente a la posición en las dos colecciones */
        if (posicioElementSeleccionat > -1) {
            elements.remove(posicioElementSeleccionat);
            songs.remove(posicioElementSeleccionat);
        }

        //Si la lista se ha quedado sin elementos, desactivamos el botón "eliminar"
        if (elements.isEmpty()) {
            btnDelSong.setDisable(true);
        }
    }

    @FXML
    private void onActionPlay(ActionEvent event) {

        // Obtenemos la posición seleccionada de la lista de reproducción
        int posicioElementSeleccionat = lv_1.getSelectionModel().getSelectedIndex();

        // Guardamos la ruta absoluta de la canción a reproducir en una variable
        String cancion = elements.get(posicioElementSeleccionat);

        /* Si la posición es correcta (puede ser -1 si no hay ninguna selección), 
        reproducimos la canción correspondiente a la posición y activamos los 
        botones "mute" y "eliminar" */
        if (posicioElementSeleccionat > -1) {
            openMedia(cancion);
        }

        if (this.player != null) {
            player.play();
            
            // Deshabilita los botones Play y Eliminar Canción de la Lista
            btnPlay.setDisable(true);
            btnDelSong.setDisable(true);
            
            // Habilita los botones que interactuan con la reproducción de la canción
            btnMute.setDisable(false);
            btnPause.setDisable(false);
            btnStop.setDisable(false);
            btnEndarrere.setDisable(false);
            btnEndevant.setDisable(false);

            imagen.setImage(imageList.get(new Random().nextInt(imageList.size())));
        }
    }

    @FXML
    private void onActionMute(ActionEvent event) {
        // Si el reproductor está muteado, lo desmutea y viceversa
        if (player.isMute()) {
            player.setMute(false);
        } else {
            player.setMute(true);
        }
    }

    /*
    Següent cançó
    Anterior cançó
     */
    @FXML
    public void onActionNextSong(ActionEvent event) {
        player.stop();
        int posicioElementSeleccionat = lv_1.getSelectionModel().getSelectedIndex();

        if (posicioElementSeleccionat == elements.size() - 1) {
            lv_1.getSelectionModel().selectFirst();
        } else {
            lv_1.getSelectionModel().selectNext();
        }
        posicioElementSeleccionat = lv_1.getSelectionModel().getSelectedIndex();

        String cancion = elements.get(posicioElementSeleccionat);

        openMedia(cancion);
        player.play();
        imagen.setImage(imageList.get(new Random().nextInt(imageList.size())));

    }

    @FXML
    public void onActionPreviousSong(ActionEvent event) {

        player.stop();
        int posicioElementSeleccionat = lv_1.getSelectionModel().getSelectedIndex();

        if (posicioElementSeleccionat == 0) {
            lv_1.getSelectionModel().selectLast();
        } else {
            lv_1.getSelectionModel().selectPrevious();
        }
        posicioElementSeleccionat = lv_1.getSelectionModel().getSelectedIndex();

        String cancion = elements.get(posicioElementSeleccionat);

        openMedia(cancion);
        player.play();
        imagen.setImage(imageList.get(new Random().nextInt(imageList.size())));

    }

    @FXML
    private void onActionPause(ActionEvent event) {

        // Controlamos el evento del botón según el estado del player
        MediaPlayer.Status currentStatus = player.getStatus();

        // Si se está reproduciendo la canción, se pausa al clicar el botón
        if (currentStatus == MediaPlayer.Status.PLAYING) {
            player.pause();
            btnPlay.setDisable(false);
            btnMute.setDisable(true);
        } // Si la canción está pausada, se reanuda al clicar al botón
        else if (currentStatus == MediaPlayer.Status.PAUSED) {
            player.play();
            btnPlay.setDisable(true);
            btnMute.setDisable(false);
        }
    }

    @FXML
    private void onActionStop(ActionEvent event) {
        // Para la reproducción y vuelve el tiempo a 0.
        player.stop();
        
        // Activa el botón de Play
        btnPlay.setDisable(false);
        
        // Desactiva los botones que no pueden interactuar si la canción no se está reproduciendo
        btnPause.setDisable(true);
        btnStop.setDisable(true);
        btnEndarrere.setDisable(true);
        btnEndevant.setDisable(true);
        btnMute.setDisable(true);
    }

    @FXML
    private void onActionEndevant(ActionEvent event) {
        // Si el tiempo de reproducción será mayor de la duración de la canción, pasa a la siguiente canción
        if (player.getCurrentTime().add(Duration.seconds(15)).toMillis() > player.getTotalDuration().toMillis()) {
            onActionNextSong(event);
            // Sino avanza 15 segundos el tiempo de reproducción de la canción
        } else {
            player.seek(player.getCurrentTime().add(Duration.seconds(15)));
        }
    }

    @FXML
    private void onActionEndarrere(ActionEvent event) {
        // Si el tiempo de reproducción será menor de 0 segundos, pasa a la canción anterior
        if (player.getCurrentTime().subtract(Duration.seconds(15)).toMillis() < 0) {
            onActionPreviousSong(event);
            // Sino retrocede 15 segundos el tiempo de reproducción de la canción
        } else {
            player.seek(player.getCurrentTime().subtract(Duration.seconds(15)));
        }
    }

    @FXML
    private void onActionBucle(ActionEvent event) {
        //Quan la cançó acabi entrara al run() i posarà la cançó a 00:00 i després la tornarem a reproduir
        player.setOnEndOfMedia(() -> {
            player.seek(Duration.ZERO);
        });
        player.play();

    }

    @FXML
    private void onActionAleatori(ActionEvent event) {
        //Paro la cançó per a que no soni una sobre de l'altra
        if (this.player != null) {
            player.stop();
        }
        //Declaro un nombre random
        int rand = new Random().nextInt(elements.size());
        //L'igualo a la selecció per a que es vegi que esta sonant
        lv_1.getSelectionModel().select(rand);
        //l'hi passo la cançó per a que la reprodueixi i es posi una imatge aleatoria
        String cancion = elements.get(rand);
        openMedia(cancion);
        player.play();
        imagen.setImage(imageList.get(new Random().nextInt(imageList.size())));
        
        // Desactivamos los botones play y borrar canción
        btnPlay.setDisable(true);
        btnDelSong.setDisable(true);
        
        // Activamos los botones que pueden interactuar con la canción
        btnMute.setDisable(false);
        btnPause.setDisable(false);
        btnStop.setDisable(false);
        btnEndarrere.setDisable(false);
        btnEndevant.setDisable(false);
    }

    // Obtener duración de canción en Horas, Minutos y Segundos
    public static String getTimeString(double millis) {
        millis /= 1000;
        String s = formatTime(millis % 60);
        millis /= 60;
        String m = formatTime(millis % 60);
        millis /= 60;
        String h = formatTime(millis % 24);
        return h + ":" + m + ":" + s;
    }

    // Formatear la duración de la canción en HH:MM:SS
    public static String formatTime(double time) {
        int t = (int) time;
        if (t > 9) {
            return String.valueOf(t);
        }
        return "0" + t;
    }

    /**
     * *
     * Inicializa el controlador
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Cargamos los iconos en los botones
        putIcons();

        //Cargamos las images
        imagesLoad();

        /* Si la lista de reproducción está vacía, desactivamos los botones 
        que interactúan con la canción*/
        if (elements.isEmpty()) {
            btnDelSong.setDisable(true);
            btnMute.setDisable(true);
            btnPlay.setDisable(true);
            btnPause.setDisable(true);
            btnStop.setDisable(true);
            btnEndarrere.setDisable(true);
            btnEndevant.setDisable(true);
            segSong.setDisable(true);
            prevSong.setDisable(true);
            btnBucle.setDisable(true);
            btnAleatori.setDisable(true);
        }

        // Asignamos la ListView a la colección de Songs
        lv_1.setItems(songs);

        lv_1.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            // Cuando se produce la acción, queremos hacer lo siguiente:
            //Activar el botón "play" por si el usuario quiere reproducir la canción
            btnPlay.setDisable(false);

            // Activar los botones que pueden interactuar con la lista de reproducción
            btnDelSong.setDisable(false);
            segSong.setDisable(false);
            prevSong.setDisable(false);
            btnBucle.setDisable(false);
            btnAleatori.setDisable(false);
        });
    }

    /**
     * *
     * Inicializa el reproductor con un fichero MP3
     *
     * El formato tiene que ser de tipo URL
     *
     */
    private void openMedia(String path) {
        try {
            File audio = new File(path);

            // Actualizamos el recurso MP3
            this.media = new Media(audio.toURI().toString());

            // Inicializamos el reproductor
            this.player = new MediaPlayer(media);

        } catch (MediaException e) {

            System.out.println("ERROR obrint fitxer demo: " + path + ":" + e.toString());
        }
    }
}
