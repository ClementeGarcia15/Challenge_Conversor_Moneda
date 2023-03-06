package com.cmg.conversor;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ConversorDistancias extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1817408114523977488L;
	private JLabel label1, label2;
	private JTextField txtDistanciaOrigen, txtDistanciaDestino;
	private JButton btnConvertir, btnSalir, btnVolver;
	private JComboBox<String> comboDistanciaOrigen, comboDistanciaDestino;

	private String[] distancias = { "Metros", "Kilómetros", "Millas" };

	public ConversorDistancias() {
		super("Conversor de Distancias");

		// se crean los botones, TextField(entrada y resultado) y JComboBox(distancias)
		label1 = new JLabel("Distancia:");
		label2 = new JLabel("Convertido:");
		txtDistanciaOrigen = new JTextField(5);
		txtDistanciaDestino = new JTextField(5);
		txtDistanciaDestino.setEditable(false);
		btnConvertir = new JButton("Convertir");
		btnSalir = new JButton("Salir");
		btnVolver = new JButton("Volver");
		comboDistanciaOrigen = new JComboBox<String>(distancias);
		comboDistanciaDestino = new JComboBox<String>(distancias);

		// Se crea un panel y se añaden los componentes anteriores
		JPanel panel = new JPanel(new GridLayout(5, 2));
		panel.add(label1);
		panel.add(txtDistanciaOrigen);
		panel.add(comboDistanciaOrigen);
		panel.add(comboDistanciaDestino);
		panel.add(label2);
		panel.add(txtDistanciaDestino);
		panel.add(btnConvertir);
		panel.add(btnSalir);
		panel.add(btnVolver);

		btnConvertir.addActionListener(this);
		btnSalir.addActionListener(this);
		btnVolver.addActionListener(this);

		add(panel);
		
		// Ajuestes de ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 200);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// Logica de obtencion de distancias
	public void actionPerformed(ActionEvent e) {
		
		// se crea una variable distancia para almacenar el valor que ingrese el usuario
		double distancia = 0.00;
		if (e.getSource() == btnConvertir) {
			try {
				distancia = Double.parseDouble(txtDistanciaOrigen.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Ingresar un valor válido para la distancia.");
			}
			String distanciaOrigen = (String) comboDistanciaOrigen.getSelectedItem();
			String distanciaDestino = (String) comboDistanciaDestino.getSelectedItem();
			double distanciaConvertida = 0.00;
			if (distanciaOrigen.equals("Metros")) {
				if (distanciaDestino.equals("Kilómetros")) {
					distanciaConvertida = distancia / 1000;
				} else if (distanciaDestino.equals("Millas")) {
					distanciaConvertida = distancia / 1609;
				}
			} else if (distanciaOrigen.equals("Kilómetros")) {
				if (distanciaDestino.equals("Metros")) {
					distanciaConvertida = distancia * 1000;
				} else if (distanciaDestino.equals("Millas")) {
					distanciaConvertida = distancia / 1.609;
				}
			} else if (distanciaOrigen.equals("Millas")) {
				if (distanciaDestino.equals("Kilómetros")) {
					distanciaConvertida = distancia * 1.609;
				} else if (distanciaDestino.equals("Metros")) {
					distanciaConvertida = distancia * 1609;
				}
			}
			txtDistanciaDestino.setText(String.format("%.2f %s", distanciaConvertida, distanciaDestino));

		} else if (e.getSource() == btnSalir) {
			MenuPrincipal.MenuSalida();

		}else if(e.getSource() == btnVolver) {
			MenuPrincipal.main(null);
			dispose();
		}
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ConversorDistancias conversor = new ConversorDistancias();
	}

}
