package graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import msjClienteAServidor.MsjConectarSala;
import msjClienteAServidor.MsjCrearPartida;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;

public class SalaPartida extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Cliente cliente;
	private JTextField textField;
	private int cantUsr=1;
	private DefaultListModel modelo = new DefaultListModel();
	private JList listUsr;
	private List<Socket> clientesConectados = new LinkedList<Socket>();

	public SalaPartida() throws UnknownHostException, IOException {
		cliente = new Cliente(); 
		cliente.setSalaPartida(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnComenzarJuego = new JButton("IniciarJuego");
		btnComenzarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modelo.getSize() > 1) {
					cliente.enviarMsj(new MsjCrearPartida(2));
				}
			}
		});
		btnComenzarJuego.setBounds(165, 211, 136, 23);
		contentPane.add(btnComenzarJuego);
		
		textField = new JTextField();
		textField.setBounds(148, 180, 163, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre usuario");
		lblNewLabel.setBounds(38, 183, 100, 14);
		contentPane.add(lblNewLabel);
		
		listUsr = new JList();
		listUsr.setModel(modelo);
		listUsr.setBounds(148, 30, 163, 129);
		contentPane.add(listUsr);
		
		JLabel lblNewLabel_1 = new JLabel("Usuarios conectados");
		lblNewLabel_1.setBounds(148, 11, 163, 14);
		contentPane.add(lblNewLabel_1);
		
		cliente.enviarMsj(new MsjConectarSala("Nico"));
	}
	
	public void updateListUsr(List<String> listausuarios) {
		modelo.removeAllElements();
		modelo.addAll(listausuarios);
	}
	public List<Socket> getClientesConectados() {
		return clientesConectados;
	}
	public void setClientesConectados(List<Socket> clientesConectados) {
		this.clientesConectados = clientesConectados;
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		SalaPartida frame = new SalaPartida();
		frame.setVisible(true);
	}
}
