import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

public class InputPortFrame implements ActionListener {

	private JFrame frame;
	private JTextField txtPort;
	private JButton btnStartServer;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputPortFrame window = new InputPortFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		
		btnStartServer = new JButton("Start Server");
		btnStartServer.addActionListener(this);
		btnStartServer.setBackground(Color.CYAN);
		btnStartServer.setBounds(121, 123, 163, 37);
		frame.getContentPane().add(btnStartServer);
		
		txtPort = new JTextField();
		txtPort.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		txtPort.setBounds(108, 64, 281, 37);
		frame.getContentPane().add(txtPort);
		txtPort.setColumns(10);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStartServer) {
			try {
				handleWithPort();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void handleWithPort() {
		int port = Integer.parseInt(txtPort.getText());
        if (port > 0 && port < 10000) {
            frame.setVisible(false);
        	new MainFrame(port);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid port. Please try again!");
        }
	}
}
