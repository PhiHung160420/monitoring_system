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
		lblNewLabel.setBounds(965, 6, 61, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(965, 34, 61, 16);
		frame.getContentPane().add(lblPort);
		
		JScrollPane spTable = new JScrollPane();
		spTable.setToolTipText("");
		spTable.setBounds(31, 164, 1026, 603);
		frame.getContentPane().add(spTable);
		
		table = new JTable();
		table.setBorder(new LineBorder(Color.BLACK));
		spTable.setColumnHeaderView(table);
		spTable.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Load Data");
		btnNewButton_1.setBounds(927, 118, 130, 34);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setBounds(1005, 6, 61, 16);
		frame.getContentPane().add(lblValue);
		
		JLabel lblValue_1 = new JLabel("Value");
		lblValue_1.setBounds(1014, 34, 61, 16);
		frame.getContentPane().add(lblValue_1);
	}
}
