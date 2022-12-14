import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame implements ActionListener {
	private JScrollPane spTable;
	private JScrollPane clientPane;
	
	public static JFrame frame;
	public static String Path = Constant.MY_PATH;
	public static String addressIP;
	
	public JTable table = new JTable();
	public DefaultTableModel tblModel;
	public JList<String> clients;
	public Map<String, String> mapPath = new HashMap<String, String>();
    public Map<String, Socket> map = new HashMap<String, Socket>();;
	public JButton btnDisconnect, btnSearch;
	
	private String[] columnNames = new String [] {"ID", "Monitoring Directory", "Time", "Action", "Name Client", "Description"};
	private JTextField txtSearch;
	

	public MainFrame() {}
	
	/**
	* @wbp.parser.entryPoint
	*/
	public MainFrame(int port) {
		getConnect(port);
		initialize(port);
		initialTable();
    }
	
	public void getConnect(int port) {
		if (Server.serverSocket != null && !Server.serverSocket.isClosed()) {
            JOptionPane.showMessageDialog(frame, "Server is running!");
        } else {
        	 try {
        		 
        		 Server.checked = true;
        		 
                 addressIP = InetAddress.getLocalHost().getHostAddress();
                 
                 new Thread(new Server(port)).start();
                 
                 Path = Paths.get(".").normalize().toAbsolutePath().toString();
             } catch (IOException e1) {
                 JOptionPane.showMessageDialog(frame, "Can not start server. Please try again!");
             }
        }
	}
	
	private void initialTable() {
		tblModel = new DefaultTableModel(null, columnNames);
		table.setModel(tblModel);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
	}


	private void initialize(int port) {
		frame = new JFrame("Monitoring System");
		frame.setBounds(100, 100, 1087, 821);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP:");
		lblNewLabel.setBounds(907, 6, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(907, 34, 61, 16);
		frame.getContentPane().add(lblPort);
		
		spTable = new JScrollPane();
		spTable.setBounds(225, 164, 832, 603);
		spTable.setToolTipText("");
		frame.getContentPane().add(spTable);
		
		table = new JTable();
		table.setBorder(new LineBorder(Color.BLACK));
		spTable.setColumnHeaderView(table);
		spTable.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Show Logs");
		btnNewButton_1.setBounds(927, 118, 130, 34);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblPortValue = new JLabel(String.valueOf(port));
		lblPortValue.setBounds(962, 34, 61, 16);
		frame.getContentPane().add(lblPortValue);
		
		JLabel lblAddressIP = new JLabel(String.valueOf(addressIP));
		lblAddressIP.setBounds(962, 6, 61, 16);
		frame.getContentPane().add(lblAddressIP);
		
		clientPane = new JScrollPane();
		clientPane.setBounds(6, 164, 207, 603);
		frame.getContentPane().add(clientPane);
		
		clients = new JList<String>();
		clientPane.setColumnHeaderView(clients);
		
		JLabel lblNewLabel_1 = new JLabel("Clients");
		lblNewLabel_1.setBounds(6, 127, 150, 25);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel_1);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(6, 20, 342, 44);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setBounds(0, 0, 0, 0);
        frame.getContentPane().add(btnDisconnect);
		
        btnSearch = new JButton("Search");
		btnSearch.setBounds(360, 22, 117, 42);
		frame.getContentPane().add(btnSearch);
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
		if (e.getSource() == btnDisconnect) {
			try {
				handleDisconnectServer();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void handleDisconnectServer() throws IOException {
		if (Server.serverSocket == null || Server.serverSocket.isClosed()) {
            JOptionPane.showMessageDialog(frame, "Server Disconnected!");
        } else {
			    if (Server.clients != null && Server.clients.size() != 0) {
                try {
                	new SendToClient(Server.clients , "Server Not Working", Constant.SERVER_NOT_WORKING, "Server");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
			    }
        	
        	Server.resetServer();    
        }
	}
	
	public void handleEvent() {
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (Server.clients != null && Server.clients.size() != 0) {
                    try {
                        new SendToClient(Server.clients , "Server Not Working", Constant.SERVER_NOT_WORKING, "Server");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                System.exit(0);
            }
        });
    }
}
