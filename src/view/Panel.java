package view;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    public JLabel principalLabel, idLabel, nameLabel, titulo1Label, titulo2Label,mensajeLabel;
    public JTextField idField, nameField, idUpdate;
    public JButton principalButton ,createButton, readButton, updateButton, deleteButton;

    public void crearPanel () {
        this.setLayout(null);

        principalLabel = new JLabel("CRUD Amigos");
        principalLabel.setFont(new Font("Arial", Font.BOLD, 28));
        principalLabel.setBounds(100, 150, 300, 50);
        principalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(principalLabel);

        principalButton = new JButton("INICIAR");
        principalButton.setFont(new Font("Arial", Font.PLAIN, 18));
        principalButton.setBounds(200, 230, 100, 40);
        this.add(principalButton);

        titulo1Label = new JLabel("Create, read y delete");
        titulo1Label.setBounds(100, 5, 300, 25);
        titulo1Label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titulo1Label);
        titulo1Label.setVisible(false);

        idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 30, 80, 25);
        this.add(idLabel);
        idLabel.setVisible(false);

        idField = new JTextField();
        idField.setBounds(140, 30, 330, 25);
        this.add(idField);
        idField.setVisible(false);

        nameLabel = new JLabel("Nombre:");
        nameLabel.setBounds(20, 70, 80, 25);
        this.add(nameLabel);
        nameLabel.setVisible(false);

        nameField = new JTextField();
        nameField.setBounds(140, 70, 330, 25);
        this.add(nameField);
        nameField.setVisible(false);

        createButton = new JButton("Create ✚");
        createButton.setBounds(20, 110, 90, 25);
        this.add(createButton);
        createButton.setVisible(false);

        readButton = new JButton("Read 🔍");
        readButton.setBounds(200, 110, 90, 25);
        this.add(readButton);
        readButton.setVisible(false);

        deleteButton = new JButton("Delete ✖");
        deleteButton.setBounds(380, 110, 90, 25);
        this.add(deleteButton);
        deleteButton.setVisible(false);

        titulo2Label = new JLabel("Update: Escribe ID y arriba los nuevos campos");
        titulo2Label.setBounds(100, 150, 280, 25);
        titulo2Label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titulo2Label);
        titulo2Label.setVisible(false);


        updateButton = new JButton("Update ⟲");
        updateButton.setBounds(380, 180, 90, 25);
        this.add(updateButton);
        updateButton.setVisible(false);

        idUpdate = new JTextField();
        idUpdate.setBounds(20, 180, 330, 25);
        this.add(idUpdate);
        idUpdate.setVisible(false);

        mensajeLabel = new JLabel("");
        mensajeLabel.setBounds(100, 200, 300, 300);
        mensajeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mensajeLabel.setBorder(getBorder());
        this.add(mensajeLabel);
        mensajeLabel.setVisible(false);
    }
}
