import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
	public 	JTable table = new JTable();
	private JScrollPane spTable;
	private JButton btnLoadData, btnSearch;
	private JButton btnConnect;
	
	public DefaultTableModel tblModel;
	public static Socket socket = null;
	public String clientName = "Client";
	public static int serverPort;
	public static String serverIp;
	public static String Path = Constant.MY_PATH;
	
	private String[] columnNames = new String [] {"ID", "Monitoring Directory", "Time", "Action", "Name Client", "Description"};
	private JTextField txtSearch;
	
	
	public MainFrame() {
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public MainFrame(int PORT, String IP, String USERNAME) {
		init(PORT, IP, USERNAME);
		connectToServer(PORT, IP, USERNAME);
		initialTable();
		initialize(PORT, IP, USERNAME);
		try {
			new Thread(new WatcherService(socket, Path)).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void init(int PORT, String IP, String USERNAME) {
         serverIp = IP;
         serverPort = PORT;
         clientName = USERNAME;
	}
	
	public void connectToServer(int PORT, String IP, String USERNAME) {
		Path = Paths.get(".").normalize().toAbsolutePath().toString() + "/";
		System.out.printf("Path: " + Path);
		
        if (socket != null && socket.isConnected()) {
            JOptionPane.showMessageDialog(null, "Connected", "Notification", JOptionPane.DEFAULT_OPTION);
        } else {
            try {
                socket = new Socket(IP, PORT);
                new SendToServer(socket, Constant.LOGIN, USERNAME, "Connected", Path);
//                new Thread(new ClientReceive(socket)).start();
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Can not connect to server. Please try again!");
            }
        }
	}
	
	public void initialTable() {
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
		spTable.setBounds(26, 182, 1031, 585);
		frame.getContentPane().add(spTable);
		
		table = new JTable();
		table.setBorder(new LineBorder(Color.BLACK));
		spTable.setColumnHeaderView(table);
		spTable.setViewportView(table);
		
		btnLoadData = new JButton("Load Data");
		btnLoadData.setBounds(927, 136, 130, 34);
		frame.getContentPane().add(btnLoadData);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(26, 136, 250, 34);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(288, 138, 117, 32);
		frame.getContentPane().add(btnSearch);
		
		handleEvent();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnConnect) {
			try {
				if (socket == null) {
                    try {
                    	btnConnect.setText("Disconnect"); 
                    	serverIp = txtIP.getText();
                        serverPort = Integer.parseInt(txtPort.getText());
                        socket = new Socket(serverIp, serverPort);
                        btnConnect.setText("Disconnect"); 

                        new SendToServer(socket, clientName, Constant.LOGIN, "Connected", Path);
                        new Thread(new ReceiveFromServer(socket)).start();
                        new Thread(new WatcherService(socket, Path)).start();

                        DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY HH:mm:ss");
                        Date date = new Date();

                        Object[] obj = new Object[] { tblModel.getRowCount() + 1, Path, dateFormat.format(date), "Connected", clientName, clientName + " connected to server!" };
                        
                        tblModel.addRow(obj);
                        table.setModel(tblModel);
                    	                
                    } catch (Exception e2) {
                        JOptionPane.showMessageDialog(null, "Can't connect check ip and port");
                    }
                } else if (socket != null && socket.isConnected()) {
                    try {
                    	btnConnect.setText("Connect");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (socket != null && socket.isConnected()) {
            try {
                new SendToServer(socket, clientName, Constant.DISCONNECT, "Disconnected", Path);
                
                btnConnect.setText("Connect");
                
                WatcherService.watcher.close();
                
                socket.close();
                
                socket = null;
                
                DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYYY HH:mm:ss");
                
                Date date = new Date();

                Object[] obj = new Object[] { tblModel.getRowCount() + 1, Path, dateFormat.format(date), "Disconnected", clientName,clientName + " disconnected to server!" };

                tblModel.addRow(obj);
                table.setModel(tblModel);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
	}
	
	public void handleEvent() {
		frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (socket != null && socket.isConnected()) {
                    try {
                        new SendToServer(socket, clientName, Constant.DISCONNECT, "Disconnected", Path);
                        WatcherService.watcher.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                System.exit(0);
            }
        });
	}
}
