package com.cmg.conversor;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class ConversorTemperatura extends JFrame implements ActionListener {

	// variables 
	private static final long serialVersionUID = 1817408114523977488L;// se crea para evitar la advertencia serial
	private JLabel label1, label2;
	private JTextField txtField1, txtField2;
	private JButton btnConvertir, btnSalir, btnVolver;
	private JComboBox<?> comboTemperaturaOrigen, comboTemperaturaDestino;

	private String[] temperaturas = { "Celsius", "Fahrenheit", "Kelvin" };

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ConversorTemperatura() {
		super("Conversor de Temperatura");

		label1 = new JLabel("Temperatura:");
		label2 = new JLabel("Convertido:");
		txtField1 = new JTextField(5);
		txtField2 = new JTextField(5);
		txtField2.setEditable(false);
		btnConvertir = new JButton("Convertir");
		btnSalir = new JButton("Salir");
		btnVolver = new JButton("Volver");
		comboTemperaturaOrigen = new JComboBox(temperaturas);
		comboTemperaturaDestino = new JComboBox(temperaturas);

		JPanel panel = new JPanel(new GridLayout(5, 2));
		panel.add(label1);
		panel.add(txtField1);
		panel.add(comboTemperaturaOrigen);
		panel.add(comboTemperaturaDestino);
		panel.add(label2);
		panel.add(txtField2);
		panel.add(btnConvertir);
		panel.add(btnSalir);
		panel.add(btnVolver);

		btnConvertir.addActionListener(this);
		btnSalir.addActionListener(this);
		btnVolver.addActionListener(this);

		add(panel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 200);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		double temperatura = 0.00;
		if (e.getSource() == btnConvertir) {
			try {
				temperatura = Double.parseDouble(txtField1.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Ingresar un valor valido");
			}
			String temperaturaOrigen = (String) comboTemperaturaOrigen.getSelectedItem();
			String temperaturaDestino = (String) comboTemperaturaDestino.getSelectedItem();
			double temperaturaConvertida = 0.00;
			if (temperaturaOrigen.equals("Celsius")) {
				if (temperaturaDestino.equals("Fahrenheit")) {
					temperaturaConvertida = (temperatura * 9 / 5) + 32;
				} else if (temperaturaDestino.equals("Kelvin")) {
					temperaturaConvertida = temperatura + 273.15;
				}
			} else if (temperaturaOrigen.equals("Fahrenheit")) {
				if (temperaturaDestino.equals("Celsius")) {
					temperaturaConvertida = (temperatura - 32) * 5 / 9;
				} else if (temperaturaDestino.equals("Kelvin")) {
					temperaturaConvertida = (temperatura + 459.67) * 5 / 9;
				}
			} else if (temperaturaOrigen.equals("Kelvin")) {
				if (temperaturaDestino.equals("Celsius")) {
					temperaturaConvertida = temperatura - 273.15;
				} else if (temperaturaDestino.equals("Fahrenheit")) {
					temperaturaConvertida = (temperatura * 9 / 5) - 459.67;
				}
			}
			txtField2.setText(String.format("%.2f %s", temperaturaConvertida, temperaturaDestino));
		} else if (e.getSource() == btnSalir) {
			MenuPrincipal.MenuSalida();

		}else if(e.getSource() == btnVolver) {
			MenuPrincipal.main(null);
			dispose();
		}
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ConversorTemperatura temperatura = new ConversorTemperatura();

	}

}
