import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import net.proteanit.sql.DbUtils;

import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;

public class CustomerInf extends JFrame {

	Connection connection=null;
	
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField;
	private JTextField search_textField_9;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerInf frame = new CustomerInf();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//******************************************refreshTable_auto*********************************************
	public void refreshTable() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
					"password");
			
			String sql = "select first_name, last_name, address1, address2, city, zipcode, state, email_address from customer";
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
			stmt.close();
			rs.close();

		} catch (Exception e2) {
			
			System.out.println(e2);
		}
	}

	/**
	 * Create the frame.
	 */
	public CustomerInf() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1550, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton firstNameButton = new JButton("First Name");
		firstNameButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		firstNameButton.setForeground(new Color(0, 0, 0));
		firstNameButton.setBackground(new Color(176, 224, 230));
		firstNameButton.setBounds(39, 157, 182, 35);
		contentPane.add(firstNameButton);
		
		JButton add1Button = new JButton("Address1");
		add1Button.setFont(new Font("Times New Roman", Font.BOLD, 25));
		add1Button.setBackground(new Color(176, 224, 230));
		add1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add1Button.setBounds(39, 305, 182, 35);
		contentPane.add(add1Button);
		
		JButton emailButton = new JButton("Email");
		emailButton.setBackground(new Color(176, 224, 230));
		emailButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		emailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		emailButton.setBounds(39, 689, 182, 32);
		contentPane.add(emailButton);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		textField_1.setBounds(261, 158, 186, 32);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		textField_2.setBounds(261, 234, 186, 32);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		textField_3.setBounds(261, 306, 186, 32);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		//******************************************Insert******************************************************
		
		JButton insertButton = new JButton("Insert");
		insertButton.setBackground(new Color(135, 206, 250));
		insertButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");
					
					String sql = "insert into customer ( first_name, last_name, address1, address2, city, zipcode, state, email_address) values (?,?,?,?,?,?,?,?) ";
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
					
					//stmt.setString(1, textField.getText() );
					stmt.setString(1, textField_1.getText() );
					stmt.setString(2, textField_2.getText() );
					stmt.setString(3, textField_3.getText() );
					stmt.setString(4, textField_4.getText() );
					stmt.setString(5, textField_5.getText() );
					stmt.setString(6, textField_6.getText() );
					stmt.setString(7, textField_7.getText() );
					stmt.setString(8, textField_8.getText() );
					
					stmt.execute();
					
					JOptionPane.showMessageDialog(null, "New Customer created successfully");
					
					stmt.close();
									
				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				refreshTable();
			}
		});
		
		insertButton.setBounds(509, 201, 141, 35);
		contentPane.add(insertButton);
		
		//******************************************Delete*******************************************************
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBackground(new Color(135, 206, 250));
		deleteButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");
														
					String sql1 = "delete from customer where customerid='"+textField.getText()+"' ";
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql1);
										
					stmt.execute();
					
					JOptionPane.showMessageDialog(null, "Customer data deleted successfully");
					
					stmt.close();
									
				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				refreshTable();
			}
		});
		
		deleteButton.setBounds(509, 383, 141, 35);
		contentPane.add(deleteButton);
		
		//******************************************Update******************************************************
		
		JButton updateButton = new JButton("Update");
		updateButton.setBackground(new Color(135, 206, 250));
		updateButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");
					
					String sql = "Update customer set customerid='"+textField.getText()+"', first_name='"+textField_1.getText()+"',last_name='"+textField_2.getText()+"',address1='"+textField_3.getText()+"',address2='"+textField_4.getText()+"',city='"+textField_5.getText()+"', zipcode='"+textField_6.getText()+"',state='"+textField_7.getText()+"',email_address='"+textField_8.getText()+"' where customerid='"+textField.getText()+"' ";
					PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
											
					stmt.execute();
					
					JOptionPane.showMessageDialog(null, "Customer data updated successfully");
					
					stmt.close();
										
				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				refreshTable();
			}
		});
		updateButton.setBounds(509, 292, 141, 35);
		contentPane.add(updateButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(713, 279, 694, 442);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		//******************************************Customer_Information_button******************************************************
		
		JButton csInfButton = new JButton("Customer Information");
		csInfButton.setBackground(new Color(176, 224, 230));
		csInfButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		csInfButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");

					Statement stmt = (Statement) conn.createStatement();
					String sql = "select first_name, last_name, address1, address2, city, zipcode, state, email_address from customer";
					ResultSet rs = stmt.executeQuery(sql);
					
					table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e2) {
					
					System.out.println(e2);
				}
			}
		});
		csInfButton.setBounds(713, 174, 701, 62);
		contentPane.add(csInfButton);
		
		JButton add2Button = new JButton("Address2");
		add2Button.setFont(new Font("Times New Roman", Font.BOLD, 25));
		add2Button.setBackground(new Color(176, 224, 230));
		add2Button.setBounds(39, 383, 182, 35);
		contentPane.add(add2Button);
		
		JButton cityButton = new JButton("City");
		cityButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		cityButton.setBackground(new Color(176, 224, 230));
		cityButton.setBounds(39, 462, 182, 35);
		contentPane.add(cityButton);
		
		JButton zipButton = new JButton("Zipcode");
		zipButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		zipButton.setBackground(new Color(176, 224, 230));
		zipButton.setBounds(39, 537, 182, 35);
		contentPane.add(zipButton);
		
		JButton stateButton = new JButton("State");
		stateButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		stateButton.setBackground(new Color(176, 224, 230));
		stateButton.setBounds(39, 614, 182, 35);
		contentPane.add(stateButton);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		textField_4.setBounds(261, 383, 186, 32);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		textField_5.setBounds(261, 463, 186, 32);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		textField_6.setBounds(261, 538, 186, 32);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		textField_7.setBounds(261, 615, 186, 32);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		JButton lastButton = new JButton("Last Name");
		lastButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lastButton.setBackground(new Color(176, 224, 230));
		lastButton.setBounds(39, 231, 182, 35);
		contentPane.add(lastButton);
		
		textField_8 = new JTextField();
		textField_8.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		textField_8.setBounds(261, 689, 186, 32);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		JButton csidButton = new JButton("Customer ID");
		csidButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		csidButton.setBackground(new Color(176, 224, 230));
		csidButton.setBounds(39, 81, 182, 35);
		contentPane.add(csidButton);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		textField.setBounds(261, 82, 186, 32);
		contentPane.add(textField);
		textField.setColumns(10);
		
		//******************************************Clear_button******************************************************
		
		JButton clearButton = new JButton("Clear");
		clearButton.setBackground(new Color(135, 206, 250));
		clearButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textField_6.setText("");
				textField_7.setText("");
				textField_8.setText("");
				search_textField_9.setText("");
			}
		});
		clearButton.setBounds(509, 462, 141, 35);
		contentPane.add(clearButton);
		
		//******************************************NAME_SearchTextField******************************************************
		
		search_textField_9 = new JTextField();
		search_textField_9.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		search_textField_9.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");

					String sql = "select * from customer where first_name =? ";
					
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
					stmt.setString(1, search_textField_9.getText() );
										
					ResultSet rs = stmt.executeQuery();
					
					if(rs.next()) {
						String add1=rs.getString("customerid");
						textField.setText(add1);
						String add2=rs.getString("first_name");
						textField_1.setText(add2);
						String add3=rs.getString("last_name");
						textField_2.setText(add3);
						String add4=rs.getString("address1");
						textField_3.setText(add4);
						String add5=rs.getString("address2");
						textField_4.setText(add5);
						String add6=rs.getString("city");
						textField_5.setText(add6);
						String add7=rs.getString("zipcode");
						textField_6.setText(add7);
						String add8=rs.getString("state");
						textField_7.setText(add8);
						String add9=rs.getString("email_address");
						textField_8.setText(add9);
						
					}
					
					table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				refreshTable();
			}
		});
		search_textField_9.setBounds(713, 100, 193, 35);
		contentPane.add(search_textField_9);
		search_textField_9.setColumns(10);
		
		//******************************************Back_button******************************************************
		
		JButton backButton = new JButton("Back");
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		backButton.setBackground(new Color(176, 196, 222));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventory inventory = new Inventory();
				inventory.setVisible(true);
				dispose();
				
			}
		});
		backButton.setBounds(38, 21, 90, 24);
		contentPane.add(backButton);
		
		JLabel lblNewLabel = new JLabel("Customer search:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(712, 50, 348, 42);
		contentPane.add(lblNewLabel);
	}
}
