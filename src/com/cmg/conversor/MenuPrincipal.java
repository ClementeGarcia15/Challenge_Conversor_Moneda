package com.cmg.conversor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MenuPrincipal extends JFrame implements ActionListener {

	// variables para crear ventana
	private static final long serialVersionUID = 1817408114523977488L;
	private JLabel label1;
	private JButton botonSeleccionar, botonSalir;
	private JComboBox<String> conversorSeleccionado;
	private String[] conversores = { "Convertir Divisas", "Convertir Temperatura", "Conversor Distancias", "Salir" };

	public MenuPrincipal() {
		// Ajuste del titulo de la ventana
		super("Menu Principal");

		// crear botones y label con sus nombres
		label1 = new JLabel("Selecciona la opcion que deseas realizar:");
		botonSeleccionar = new JButton("Seleccionar");
		botonSalir = new JButton("Salir");
		// se asigna el array conversores al JComboBox
		conversorSeleccionado = new JComboBox<String>(conversores);

		// se crea un panel
		JPanel panel = new JPanel(new GridBagLayout());

		// ajuste de los botones y label para centrarlos
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(0, 0, 20, 0);
		panel.add(label1, gbc);

		gbc.gridy = 1;
		gbc.insets = new Insets(0, 0, 20, 0);
		panel.add(conversorSeleccionado, gbc);

		gbc.gridy = 2;
		gbc.insets = new Insets(0, 0, 0, 100);
		panel.add(botonSeleccionar, gbc);

		gbc.gridy = 2;
		gbc.insets = new Insets(0, 150, 0, 0);
		panel.add(botonSalir, gbc);

		botonSeleccionar.addActionListener(this);
		botonSalir.addActionListener(this);

		add(panel);

		// Ajustes de la ventana 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 200);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		// se verifica si se presiono el boton de seleccionar
		if (e.getSource() == botonSeleccionar) {

			// se crea una variable string para almacenar la opcion seleccionada
			String Seleccionado = (String) conversorSeleccionado.getSelectedItem();

			//se comprueba seleccionado
			switch (Seleccionado) {

			case "Convertir Divisas":

				try {
					// en este caso se llama a main de Converso de moneda para crear la ventana
					ConversorMoneda.main(null);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error: Ingrese un valor numérico válido");
				}
				break;

			case "Convertir Temperatura":

				try {
					// en este caso se llama a main de Converso de temperatura para crear la ventana
					ConversorTemperatura.main(null);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error: Ingrese un valor numérico válido");
				}
				break;

			case "Conversor Distancias":

				try {
					// en este caso se llama a main de Converso de distancias para crear la ventana
					ConversorDistancias.main(null);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Error: Ingrese un valor numérico válido");
				}
				break;

			case "Salir":
				// se llama al metodo MenuSalida para avisar al usuario que a salido del programa
				MenuSalida();
				break;

			default:
				break;

			}
			// Se avisa al usuario que selecciono
			JOptionPane.showMessageDialog(null, "Haz seleccionado: " + Seleccionado);
			// Esto es para cerrar esta ventana despues de seleccionar una opcion
			dispose();

			// Aqui se verifica si se presiono el boton salir y llama al metodo MenuSalida
		} else if (e.getSource() == botonSalir) {
			MenuSalida();

		}
			
	}

	// Crear ventana de Aviso de salida de aplicacion
	public static void MenuSalida() {
		JOptionPane.showMessageDialog(null, "Has Salido de la aplicacion");
		System.exit(0);

	}

	public static void main(String[] args) {

		new MenuPrincipal();

	}

}
