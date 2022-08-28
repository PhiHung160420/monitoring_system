import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import java.io.IOException;
import java.io.IOException;

public class InputPortFrame {

	private JFrame frame;
	private JTextField txtPort;
	
	public InputPortFrame() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 404, 269);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Server");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(157, 6, 111, 46);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPort.setBounds(55, 64, 78, 37);
		frame.getContentPane().add(lblPort);
		
		JButton btnNewButton = new JButton("Start Server");
		btnNewButton.setBackground(Color.CYAN);
		btnNewButton.setBounds(121, 123, 163, 37);
		frame.getContentPane().add(btnNewButton);
		
		txtPort = new JTextField();
		txtPort.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtPort.setBounds(108, 64, 281, 37);
		frame.getContentPane().add(txtPort);
		txtPort.setColumns(10);
	}
}
