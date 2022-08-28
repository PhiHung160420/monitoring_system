import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map.Entry;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class MainFrame implements ActionListener {

	private JFrame frame;
	private JTextField txtIP;
	private JTextField txtPort;
	public static JTable table;
	private JScrollPane spTable;
	private JButton btnLoadData;
	private JButton btnConnect;
	
	public static DefaultTableModel tblModel;
	public static Socket socket = null;
	public static String clientName = "Client";
	public static int serverPort;
	public static String serverIp;
	public static String Path = Constant.MY_PATH;
	
	private String[] columnNames = new String [] {"ID", "Monitoring Directory", "Time", "Action", "Name Client", "Description"};
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		initialTable();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public MainFrame(int PORT, String IP, String USERNAME) {
		init(PORT, IP, USERNAME);
		initialize(PORT, IP, USERNAME);
		connectWithServer(PORT, IP, USERNAME);
		try {
			new Thread(new WatcherService(socket, Paths.get(Path))).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init(int PORT, String IP, String USERNAME) {
         serverIp = IP;
         serverPort = PORT;
         clientName = USERNAME;
	}
	
	public void connectWithServer(int PORT, String IP, String USERNAME) {
		Path = Paths.get(".").normalize().toAbsolutePath().toString() + "/";
        if (socket != null && socket.isConnected()) {
            JOptionPane.showMessageDialog(frame, "Connected!");
        } else {
            try {
                socket = new Socket(IP, PORT);
                new SendToServer(socket, USERNAME, "2", "Connected", Path);
//                new Thread(new ClientReceive(socket)).start();
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(frame, "Can not connect to server. Please try again!");
            }
        }
	}
	
	private void initialTable() {
		tblModel = new DefaultTableModel(null, columnNames);
		Object[] data = new Object[] { 1, "test", "test", "test", "test", "test" };
		tblModel.addRow(data);
		table.setModel(tblModel);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
	}

	private void initialize(int PORT, String IP, String USERNAME) {
		frame = new JFrame();
		frame.setBounds(100, 100, 1087, 821);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("IP:");
		lblNewLabel.setBounds(26, 16, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		txtIP = new JTextField(String.valueOf(IP));
		txtIP.setBounds(109, 7, 167, 34);
		frame.getContentPane().add(txtIP);
		txtIP.setColumns(10);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(26, 54, 61, 16);
		frame.getContentPane().add(lblPort);
		
		txtPort = new JTextField(String.valueOf(PORT));
		txtPort.setBounds(109, 45, 167, 34);
		txtPort.setColumns(10);
		frame.getContentPane().add(txtPort);
		
		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(this);
		btnConnect.setBounds(62, 90, 117, 34);
		frame.getContentPane().add(btnConnect);
		
		spTable = new JScrollPane();
		spTable.setToolTipText("");
		spTable.setBounds(26, 164, 1031, 603);
		frame.getContentPane().add(spTable);
		
		table = new JTable();
		table.setBorder(new LineBorder(Color.BLACK));
		spTable.setColumnHeaderView(table);
		spTable.setViewportView(table);
		
		btnLoadData = new JButton("Load Data");
		btnLoadData.setBounds(927, 118, 130, 34);
		frame.getContentPane().add(btnLoadData);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnConnect) {
			try {
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
