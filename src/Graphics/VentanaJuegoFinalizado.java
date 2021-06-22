package graphics;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import juego.App;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaJuegoFinalizado extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private FrameJuego framePadre = null;

	public VentanaJuegoFinalizado(FrameJuego padre,String string) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setFocusable(true);
		this.framePadre = padre;
		setBounds(100, 100, 450, 300);
		contentPanel.setBounds(0, 0, 434, 250);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setVisible(true);
		JLabel lblTextGanador = new JLabel("El ganador es: "+string+"!");
		lblTextGanador.setBounds(121, 104, 250, 40);
		contentPanel.add(lblTextGanador);
		JButton btn = new JButton ("Cerrar");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btn.setBounds(250, 188, 100, 30);
		contentPanel.add(btn);
		
		JButton btnJugarDeNuevo = new JButton("Jugar de nuevo");
		btnJugarDeNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				framePadre.jugarDeNuevo();
				dispose();
			}
		});
		btnJugarDeNuevo.setBounds(68, 188, 148, 30);
		contentPanel.add(btnJugarDeNuevo);
	}
}
