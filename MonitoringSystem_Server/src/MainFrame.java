import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame implements ActionListener {
	public static JFrame frame;
	private JTable table;
	private JScrollPane spTable;
	private JScrollPane clientPane;
	public DefaultTableModel tblModel;
	
	public static String addressIP;
	private String[] columnNames = new String [] {"ID", "Monitoring Directory", "Time", "Action", "Name Client", "Description"};
	private JTextField txtSearch;

	public MainFrame() {
		initialTable();
	}
	
	/**
	* @wbp.parser.entryPoint
	*/
	public MainFrame(int port) {
		getAddressIP();
		initialize(port);
    }
	
	public void getAddressIP() {
		try {
            addressIP = InetAddress.getLocalHost().getHostAddress();
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(frame, "Cannot start server");
        }
	}
	
	private void initialTable() {
		tblModel = new DefaultTableModel(null, columnNames);
		Object[] data = new Object[] { 1, "test", "test", "test", "test", "test" };
		tblModel.addRow(data);
		table.setModel(tblModel);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
	}


	private void initialize(int port) {
		frame = new JFrame();
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
		lblPortValue.setBounds(964, 6, 61, 16);
		frame.getContentPane().add(lblPortValue);
		
		JLabel lblAddressIP = new JLabel(addressIP);
		lblAddressIP.setBounds(964, 34, 61, 16);
		frame.getContentPane().add(lblAddressIP);
		
		clientPane = new JScrollPane();
		clientPane.setBounds(6, 164, 207, 603);
		frame.getContentPane().add(clientPane);
		
		JList clientList = new JList();
		clientPane.setColumnHeaderView(clientList);
		
		JLabel lblNewLabel_1 = new JLabel("Clients");
		lblNewLabel_1.setBounds(6, 127, 150, 25);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel_1);
		
		txtSearch = new JTextField();
		txtSearch.setBounds(6, 20, 342, 44);
		frame.getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSearch.setBounds(360, 22, 117, 42);
		frame.getContentPane().add(btnSearch);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
