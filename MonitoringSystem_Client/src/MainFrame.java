import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class MainFrame {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
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
		initialize();
		initialTable();
	}
	
	private void initialTable() {
		DefaultTableModel model = new DefaultTableModel(null, columnNames);
		Object[] data = new Object[] { 1, "test", "test", "test", "test", "test" };
		model.addRow(data);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setMaxWidth(50);
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1087, 821);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IP:");
		lblNewLabel.setBounds(26, 16, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(109, 7, 167, 34);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(26, 54, 61, 16);
		frame.getContentPane().add(lblPort);
		
		textField_1 = new JTextField();
		textField_1.setBounds(109, 45, 167, 34);
		textField_1.setColumns(10);
		frame.getContentPane().add(textField_1);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(26, 92, 61, 16);
		frame.getContentPane().add(lblName);
		
		textField_2 = new JTextField();
		textField_2.setBounds(109, 83, 167, 34);
		textField_2.setColumns(10);
		frame.getContentPane().add(textField_2);
		
		JButton btnNewButton = new JButton("Connect");
		btnNewButton.setBounds(285, 46, 117, 34);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane spTable = new JScrollPane();
		spTable.setToolTipText("");
		spTable.setBounds(26, 164, 1031, 603);
		frame.getContentPane().add(spTable);
		
		table = new JTable();
		table.setBorder(new LineBorder(Color.BLACK));
		spTable.setColumnHeaderView(table);
		spTable.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Load Data");
		btnNewButton_1.setBounds(927, 118, 130, 34);
		frame.getContentPane().add(btnNewButton_1);
	}
}
