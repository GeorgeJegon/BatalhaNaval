package components.graphic;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import components.client.Client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnectWindow extends JFrame {
  private JTextField addressField;
  private JTextField portField;

  public ClientConnectWindow(final Client client) {
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
          client.setHost(addressField.getText());
          client.setPort(Integer.parseInt(portField.getText()));
          client.connect();
          setVisible(false);
        }
      }
    });
    GroupLayout groupLayout = new GroupLayout(getContentPane());
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
        Alignment.LEADING).addGroup(
        Alignment.TRAILING,
        groupLayout
            .createSequentialGroup()
            .addContainerGap(45, Short.MAX_VALUE)
            .addGroup(
                groupLayout
                    .createParallelGroup(Alignment.LEADING)
                    .addGroup(
                        groupLayout
                            .createSequentialGroup()
                            .addGroup(
                                groupLayout
                                    .createParallelGroup(Alignment.TRAILING)
                                    .addComponent(portLabel)
                                    .addComponent(addressLabel))
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addGroup(
                                groupLayout
                                    .createParallelGroup(Alignment.LEADING)
                                    .addComponent(addressField,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                    .addComponent(portField,
                                        GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)))
                    .addGroup(
                        groupLayout.createSequentialGroup().addGap(28)
                            .addComponent(btnNewButton))).addGap(43)));
    groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
        Alignment.LEADING).addGroup(
        groupLayout
            .createSequentialGroup()
            .addGap(41)
            .addGroup(
                groupLayout
                    .createParallelGroup(Alignment.BASELINE)
                    .addComponent(addressLabel)
                    .addComponent(addressField, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(ComponentPlacement.UNRELATED)
            .addGroup(
                groupLayout
                    .createParallelGroup(Alignment.BASELINE)
                    .addComponent(portLabel)
                    .addComponent(portField, GroupLayout.PREFERRED_SIZE,
                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGap(18).addComponent(btnNewButton)
            .addContainerGap(50, Short.MAX_VALUE)));
    getContentPane().setLayout(groupLayout);
  }
}
