package view;

import controller.AmigoController;
import javax.swing.*;

public class Ventana extends JFrame {
    public Ventana() {
        setTitle("CRUD de Amigos");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        Panel panel = new Panel();
        panel.crearPanel();

        add(panel);

        new AmigoController(panel); // luego se asigna el controlador
    }
}
