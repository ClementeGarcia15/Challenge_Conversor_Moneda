package com.cmg.conversor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import org.json.*;

public class ConversorMoneda extends JFrame implements ActionListener {

	// variables
	private static final long serialVersionUID = 1817408114523977488L;// se crea para evitar la advertencia serial
	private JLabel label1, label2;
	private JTextField txtField1, txtField2;
	private JButton btnConvertir, btnSalir, btnVolver;
	private JComboBox<String> comboMonedaOrigen, comboMonedaDestino;

	// se crea el menu de monedas
	private String[] monedas = { "MXN", "USD", "EUR", "GBP", "JPY", "KRW" };

	public ConversorMoneda() {
		// se crea el Titulo de la ventana
		super("Conversor de Moneda");

		// se crean los botones, TextField(entrada y resultado) y JComboBox(Monedas)
		label1 = new JLabel("Monto:");
		label2 = new JLabel("Convertido:");
		txtField1 = new JTextField(5);
		txtField2 = new JTextField(5);
		txtField2.setEditable(false);
		btnConvertir = new JButton("Convertir");
		btnSalir = new JButton("Salir");
		btnVolver = new JButton("Volver");
		comboMonedaOrigen = new JComboBox<>(monedas);
		comboMonedaDestino = new JComboBox<>(monedas);

		// Se crea un panel y se añaden los componentes anteriores
		JPanel panel = new JPanel(new GridLayout(5, 2));
		panel.add(label1);
		panel.add(txtField1);
		panel.add(comboMonedaOrigen);
		panel.add(comboMonedaDestino);
		panel.add(label2);
		panel.add(txtField2);
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

	// Logica de obtencion de moneda
	public void actionPerformed(ActionEvent e) {

		// se crea una variable monto para almacenar el valor que ingrese el usuario
		double monto = 0.00;

		// Se verifica si el usuario presiono el boton Convertir
		if (e.getSource() == btnConvertir) {

			// Se valida que ingrese numeros y no letras
			try {
				monto = Double.parseDouble(txtField1.getText());
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Ingresar un valor valido");
			}
			// se crean variables string y double para enviar la monedaOrigen y
			// monedaDestino
			String monedaOrigen = (String) comboMonedaOrigen.getSelectedItem();
			String monedaDestino = (String) comboMonedaDestino.getSelectedItem();
			double tasaCambio = obtenerResultado(monedaOrigen, monedaDestino);
			double montoConvertido = monto * tasaCambio;

			// se muestra el resultado
			txtField2.setText(String.format("%.2f %s", montoConvertido, monedaDestino));
			// se verifica si el usuario Presiono el boton salir
		} else if (e.getSource() == btnSalir) {
			// se llama al metodo MenuSalida
			MenuPrincipal.MenuSalida();

		}else if(e.getSource() == btnVolver) {
			MenuPrincipal.main(null);
			dispose();
		}
	}

	// Aqui es la logica para la obtencion del resultado de la conversion mediante
	// la consulta de una api
	private double obtenerResultado(String monedaOrigen, String monedaDestino) {
		double tasa = 0.00;
		// se capturan excepciones para poder continuar
		try {
			URL url = new URL(String.format("https://api.exchangerate-api.com/v4/latest/%s", monedaOrigen));
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			JSONObject jsonObj = new JSONObject(response.toString());
			tasa = jsonObj.getJSONObject("rates").getDouble(monedaDestino);
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
		return tasa;
	}

	// Aqui inicia el Programa
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ConversorMoneda conversor = new ConversorMoneda();
	}
}
