package components.graphic;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import components.client.Client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientConnectWindow extends JFrame {
  private JTextField addressField;
  private JTextField portField;
  private JTextField nameField;

  public ClientConnectWindow(final Client client,
      final ClientGameWindow clientGameWindow) {
    JLabel addressLabel = new JLabel("Address");

    addressField = new JTextField();
    addressLabel.setLabelFor(addressField);
    addressField.setText("localhost");
    addressField.setColumns(10);

    JLabel portLabel = new JLabel("Port:");

    portField = new JTextField();
    portLabel.setLabelFor(portField);
    portField.setText("3322");
    portField.setColumns(10);

    JButton btnNewButton = new JButton("Connect");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (!client.isConnect()) {
          if (addressField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha o campo de Endereço do Servidor!");
            return;
          }
          if (portField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Preencha o campo de Porta do Servidor!");
            return;
          }
          if (nameField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite seu Nome!");
            return;
          }
          client.setHost(addressField.getText());
          client.setPort(Integer.parseInt(portField.getText()));
          client.setName(nameField.getText());
          client.connect();
          if (client.isConnect()) {
            setVisible(false);
            clientGameWindow.setVisible(true);
          } else {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao Servidor!");
          }
          
        }
      }
    });
    
    JLabel nameLabel = new JLabel("Name:");
    
    nameField = new JTextField();
    nameField.setColumns(10);

    GroupLayout groupLayout = new GroupLayout(getContentPane());
    groupLayout.setHorizontalGroup(
      groupLayout.createParallelGroup(Alignment.TRAILING)
        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
          .addGap(73)
          .addComponent(btnNewButton)
          .addContainerGap(77, Short.MAX_VALUE))
        .addGroup(groupLayout.createSequentialGroup()
          .addContainerGap(45, Short.MAX_VALUE)
          .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
            .addComponent(nameLabel)
            .addComponent(portLabel)
            .addComponent(addressLabel))
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
            .addComponent(addressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(portField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
          .addGap(43))
    );
    groupLayout.setVerticalGroup(
      groupLayout.createParallelGroup(Alignment.LEADING)
        .addGroup(groupLayout.createSequentialGroup()
          .addGap(41)
          .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
            .addComponent(addressLabel)
            .addComponent(addressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
            .addComponent(portLabel)
            .addComponent(portField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
          .addPreferredGap(ComponentPlacement.UNRELATED)
          .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
            .addComponent(nameLabel)
            .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
          .addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
          .addComponent(btnNewButton)
          .addGap(22))
    );
    getContentPane().setLayout(groupLayout);
  }
}
