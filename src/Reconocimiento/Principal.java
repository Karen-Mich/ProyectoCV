/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reconocimiento;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author ASUS
 */
public class Principal{
    
    public static void main(String arg[]) throws InterruptedException {
    // Leyendo librería nativa
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

    // Se crea el JFrame
    JFrame frame = new JFrame("Detección de rostros");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    DetectarRostros detectorRostros = new DetectarRostros();
    PanelDeRostros panel = new PanelDeRostros();
    frame.setSize(400, 400);
    frame.setBackground(Color.BLUE);
    frame.add(panel, BorderLayout.CENTER);
    frame.setVisible(true);

    // Se crea una matriz que contendrá la imagen
    Mat imagenDeWebCam = new Mat();
    VideoCapture webCam = new VideoCapture(0);

    if (webCam.isOpened()) {
      Thread.sleep(500); // Se interrumpe el thread para permitir que la webcam se inicialice
      while (true) {
        webCam.read(imagenDeWebCam);
        if (!imagenDeWebCam.empty()) {
          Thread.sleep(200); // Permite que la lectura se complete
          frame.setSize(imagenDeWebCam.width() + 40, imagenDeWebCam.height() + 60);
          // Invocamos la rutina de opencv que detecta rostros sobre la imagen obtenida por la webcam
          imagenDeWebCam = detectorRostros.detecta(imagenDeWebCam);
          // Muestra la imagen
          panel.convierteMatABufferedImage(imagenDeWebCam);
          panel.repaint();
        } else {
          System.out.println("No se capturó nada");
          break;
        }
      }
    }
    webCam.release(); // Se libera el recurso de la webcam
  }
    
}
