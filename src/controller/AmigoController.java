package controller;

import model.Amigo;
import service.AmigoService;
import view.Panel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class AmigoController implements ActionListener {
    Panel amigoPanel;
    AmigoService amigoService = new AmigoService();
    File bd;

    public AmigoController(Panel panelAmigo) {
        this.amigoPanel = panelAmigo;

        // Asegúrate de que todos los botones estén conectados
        this.amigoPanel.createButton.addActionListener(this);
        this.amigoPanel.readButton.addActionListener(this);
        this.amigoPanel.updateButton.addActionListener(this);
        this.amigoPanel.deleteButton.addActionListener(this);
        this.amigoPanel.principalButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == amigoPanel.createButton) {
            try {
                Long id = Long.parseLong(amigoPanel.idField.getText());
                String nombre = amigoPanel.nameField.getText();

                String resultado = amigoService.create(new Amigo(id, nombre), bd);
                amigoPanel.idField.setText("");
                amigoPanel.nameField.setText("");
                amigoPanel.idUpdate.setText("");

                amigoPanel.mensajeLabel.setText(resultado);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        amigoPanel,
                        "El ID debe ser un número válido.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                // Aquí se capturan todos los errores generados por el servicio
                JOptionPane.showMessageDialog(
                        amigoPanel,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else if (e.getSource() == amigoPanel.readButton) {
            try {
                String resultado = amigoService.read(bd);
                amigoPanel.mensajeLabel.setText("<html>" + resultado.replaceAll("\n", "<br>") + "</html>");
                amigoPanel.idField.setText("");
                amigoPanel.nameField.setText("");
                amigoPanel.idUpdate.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        amigoPanel,
                        ex.getMessage(),
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        } else if (e.getSource() == amigoPanel.updateButton) {
            try {
                Long idBuscar = Long.parseLong(amigoPanel.idUpdate.getText());

                // Leer posibles nuevos valores
                String nuevoNombre = amigoPanel.nameField.getText();
                String idNuevoTexto = amigoPanel.idField.getText();
                Long nuevoId = null;

                if (idNuevoTexto != null && !idNuevoTexto.trim().isEmpty()) {
                    nuevoId = Long.parseLong(idNuevoTexto);
                }
                Amigo nuevoAmigo = new Amigo(nuevoId, nuevoNombre);

                String resultado = amigoService.update(idBuscar, nuevoAmigo, bd);
                amigoPanel.mensajeLabel.setText(resultado);

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        amigoPanel,
                        "El ID debe ser un número válido.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        amigoPanel,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } else if (e.getSource() == amigoPanel.deleteButton) {
            try {
                Long id = Long.parseLong(amigoPanel.idField.getText());
                String resultado = amigoService.delete(id, bd);
                amigoPanel.idField.setText("");
                amigoPanel.nameField.setText("");
                amigoPanel.idUpdate.setText("");

                amigoPanel.mensajeLabel.setText(resultado);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        amigoPanel,
                        "El ID debe ser un número válido.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        amigoPanel,
                        ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else if (e.getSource() == amigoPanel.principalButton) {
            try {
                bd = amigoService.crearArchivo();
                amigoPanel.principalLabel.setVisible(false);
                amigoPanel.principalButton.setVisible(false);
                amigoPanel.idLabel.setVisible(true);
                amigoPanel.idField.setVisible(true);
                amigoPanel.nameLabel.setVisible(true);
                amigoPanel.nameField.setVisible(true);
                amigoPanel.createButton.setVisible(true);
                amigoPanel.readButton.setVisible(true);
                amigoPanel.updateButton.setVisible(true);
                amigoPanel.deleteButton.setVisible(true);
                amigoPanel.mensajeLabel.setVisible(true);
                amigoPanel.idUpdate.setVisible(true);
                amigoPanel.titulo1Label.setVisible(true);
                amigoPanel.titulo2Label.setVisible(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
