import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import javax.swing.JButton;

public class ClientFrame implements ActionListener {

	private JFrame frame;
	private JTextField txtIP;
	private JTextField txtPort;
	private JTextField txtNameClient;
	private JButton btnConnect;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame window = new ClientFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ClientFrame() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 428, 291);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Client");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblNewLabel.setBounds(154, 6, 128, 52);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("IP:");
		lblNewLabel_1.setBounds(34, 67, 61, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtIP = new JTextField();
		txtIP.setBounds(154, 56, 227, 35);
		frame.getContentPane().add(txtIP);
		txtIP.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Port:");
		lblNewLabel_1_1.setBounds(34, 116, 61, 16);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		txtPort = new JTextField();
		txtPort.setColumns(10);
		txtPort.setBounds(154, 105, 227, 35);
		frame.getContentPane().add(txtPort);
		
		JLabel lblNewLabel_1_2 = new JLabel("User name:");
		lblNewLabel_1_2.setBounds(34, 163, 95, 16);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		txtNameClient = new JTextField();
		txtNameClient.setColumns(10);
		txtNameClient.setBounds(154, 152, 227, 35);
		frame.getContentPane().add(txtNameClient);
		
		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(this);
		btnConnect.setBounds(154, 198, 122, 40);
		frame.getContentPane().add(btnConnect);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnConnect) {
			try {
				handleInfoClent();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public boolean checkInfo(int port, String ip, String username) {
		return port > 0 && port < 10000 && ip.length() != 0 && username.length() != 0;
	}
	
	public void handleInfoClent() {
		int PORT = Integer.parseInt(txtPort.getText());
        String IP = txtIP.getText();
        String USERNAME = txtNameClient.getText();

        if (checkInfo(PORT, IP, USERNAME)) {
        	frame.setVisible(false);
            new MainFrame(PORT, IP, USERNAME);    
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid information!!");
        }
	}

}
