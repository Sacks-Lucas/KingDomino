package graphics;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import msjClienteAServidor.MsjCrearPartida;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class EntrarAPartida extends JFrame {

	private JPanel contentPane;
	private Cliente cliente;
	/**
	 * Launch the application.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		EntrarAPartida frame = new EntrarAPartida();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public EntrarAPartida() throws UnknownHostException, IOException {
		cliente = new Cliente(); 
		cliente.setEntrarAPartida(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.enviarMsj(new MsjCrearPartida(4));
			}
		});
		contentPane.add(btnNewButton, BorderLayout.CENTER);
	}
	
	public void comenzarJuego () {
		
	}

}
