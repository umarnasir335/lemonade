import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Color;

public class OrderInfo extends JFrame {

	private JPanel contentPane;
	private JTextField oid_textField;
	private JTextField csearch_textField;
	private JTextField fn_textField;
	private JTextField ln_textField;
	private JTextField cid_textField;
	private JButton pnbutton;
	private JButton pidbutton;
	private JTextField pnsearch_textField;
	private JTextField pn_textField;
	private JTextField pid_textField;
	private JTextField pq_textField;
	private JButton insertButton;
	private JButton deletebutton;
	private JTable table;
	private JButton updateButton;
	private JButton backButton;
	private JTextField orderID_search_tetField;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderInfo frame = new OrderInfo();
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

			
			String sql = "select orderid, customer_first_name, customer_last_name, customer_id, product_name, product_id, ordered_unit from `order`";
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
	public OrderInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1550, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton oidButton = new JButton("Order ID");
		oidButton.setBackground(new Color(176, 224, 230));
		oidButton.setForeground(new Color(0, 0, 0));
		oidButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		oidButton.setBounds(120, 107, 141, 35);
		contentPane.add(oidButton);
		
		oid_textField = new JTextField();
		oid_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		oid_textField.setBounds(313, 109, 169, 32);
		contentPane.add(oid_textField);
		oid_textField.setColumns(10);
		
		//******************************************Customer_NAME_SearchTextField****************************************************
		
		csearch_textField = new JTextField();
		csearch_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		csearch_textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");

					String sql = "select * from customer where first_name =? ";
					
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
					stmt.setString(1, csearch_textField.getText() );
										
					ResultSet rs = stmt.executeQuery();
					
					if(rs.next()) {
						String add1=rs.getString("customerid");
						cid_textField.setText(add1);
						String add2=rs.getString("first_name");
						fn_textField.setText(add2);
						String add3=rs.getString("last_name");
						ln_textField.setText(add3);
											
					}
								
				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				
			}
		});
		csearch_textField.setBounds(313, 196, 169, 32);
		contentPane.add(csearch_textField);
		csearch_textField.setColumns(10);
		
		JButton fnButton = new JButton("First Name");
		fnButton.setBackground(new Color(176, 224, 230));
		fnButton.setFont(new Font("Times New Roman", Font.BOLD, 22));
		fnButton.setBounds(120, 257, 186, 29);
		contentPane.add(fnButton);
		
		JButton lnButton = new JButton("Last Name");
		lnButton.setBackground(new Color(176, 224, 230));
		lnButton.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lnButton.setBounds(120, 311, 186, 29);
		contentPane.add(lnButton);
		
		JButton cid_button = new JButton("Customer ID");
		cid_button.setBackground(new Color(176, 224, 230));
		cid_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		cid_button.setFont(new Font("Times New Roman", Font.BOLD, 22));
		cid_button.setBounds(120, 361, 183, 32);
		contentPane.add(cid_button);
		
		fn_textField = new JTextField();
		fn_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		fn_textField.setBounds(333, 257, 149, 29);
		contentPane.add(fn_textField);
		fn_textField.setColumns(10);
		
		ln_textField = new JTextField();
		ln_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		ln_textField.setColumns(10);
		ln_textField.setBounds(333, 311, 149, 29);
		contentPane.add(ln_textField);
		
		cid_textField = new JTextField();
		cid_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		cid_textField.setColumns(10);
		cid_textField.setBounds(333, 361, 149, 32);
		contentPane.add(cid_textField);
		
		pnbutton = new JButton("Product Name");
		pnbutton.setBackground(new Color(176, 224, 230));
		pnbutton.setFont(new Font("Times New Roman", Font.BOLD, 22));
		pnbutton.setBounds(123, 523, 183, 32);
		contentPane.add(pnbutton);
		
		pidbutton = new JButton("Product ID");
		pidbutton.setBackground(new Color(176, 224, 230));
		pidbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		pidbutton.setFont(new Font("Times New Roman", Font.BOLD, 22));
		pidbutton.setBounds(123, 576, 183, 32);
		contentPane.add(pidbutton);
		
		//******************************************Product_NAME_SearchTextField****************************************************
		
		pnsearch_textField = new JTextField();
		pnsearch_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		pnsearch_textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");

					String sql = "select * from product where name =? ";
					
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
					stmt.setString(1, pnsearch_textField.getText() );
										
					ResultSet rs = stmt.executeQuery();
					
					if(rs.next()) {
						String add1=rs.getString("name");
						pn_textField.setText(add1);
						String add2=rs.getString("productid");
						pid_textField.setText(add2);
																
					}
										
				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				
			}
		});
		pnsearch_textField.setColumns(10);
		pnsearch_textField.setBounds(313, 455, 169, 32);
		contentPane.add(pnsearch_textField);
		
		pn_textField = new JTextField();
		pn_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		pn_textField.setColumns(10);
		pn_textField.setBounds(333, 523, 149, 33);
		contentPane.add(pn_textField);
		
		pid_textField = new JTextField();
		pid_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		pid_textField.setColumns(10);
		pid_textField.setBounds(333, 576, 149, 33);
		contentPane.add(pid_textField);
		
		pq_textField = new JTextField();
		pq_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		pq_textField.setColumns(10);
		pq_textField.setBounds(313, 678, 169, 32);
		contentPane.add(pq_textField);
		
		//******************************************Insert******************************************************
		
		insertButton = new JButton("Insert");
		insertButton.setBackground(new Color(135, 206, 250));
		insertButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");
					
					String sql = "insert into `order` (customer_first_name, customer_last_name, customer_id, product_name, product_id, ordered_unit) values (?,?,?,?,?,?) ";
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
					
					stmt.setString(1, fn_textField.getText() );
					stmt.setString(2, ln_textField.getText() );
					stmt.setString(3, cid_textField.getText() );
					stmt.setString(4, pn_textField.getText() );
					stmt.setString(5, pid_textField.getText() );
					stmt.setString(6, pq_textField.getText() );
										
					stmt.execute();
					
					JOptionPane.showMessageDialog(null, "Order created successfully");
					
					stmt.close();
									
				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				refreshTable();
			}
		});
		insertButton.setBounds(523, 207, 141, 35);
		contentPane.add(insertButton);
		
		//******************************************Delete*******************************************************
		
		deletebutton = new JButton("Delete");
		deletebutton.setBackground(new Color(135, 206, 250));
		deletebutton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		deletebutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");
														
					String sql1 = "delete from `order` where orderid='"+oid_textField.getText()+"' ";
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql1);
										
					stmt.execute();
					
					JOptionPane.showMessageDialog(null, "Order deleted successfully");
					
					stmt.close();
									
				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				refreshTable();
			}
		});
		deletebutton.setBounds(523, 437, 141, 35);
		contentPane.add(deletebutton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(704, 291, 714, 438);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		//******************************************Order_Information_button******************************************************
		
		JButton OrderInfoButton = new JButton("Order Information");
		OrderInfoButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		OrderInfoButton.setBackground(new Color(176, 224, 230));
		OrderInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");

					Statement stmt = (Statement) conn.createStatement();
					String sql = "select orderid, customer_first_name, customer_last_name, customer_id, product_name,product_id, ordered_unit from `order`";
					ResultSet rs = stmt.executeQuery(sql);
					
					table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				refreshTable();
			}
		});
		OrderInfoButton.setBounds(704, 198, 714, 52);
		contentPane.add(OrderInfoButton);
		
		//******************************************Update******************************************************
		
		updateButton = new JButton("Update");
		updateButton.setBackground(new Color(135, 206, 250));
		updateButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");
					
					String sql = "Update `order` set orderid='"+oid_textField.getText()+"', product_id='"+pid_textField.getText()+"',product_name='"+pn_textField.getText()+"',customer_id='"+cid_textField.getText()+"',customer_first_name='"+fn_textField.getText()+"',customer_last_name='"+ln_textField.getText()+"',ordered_unit='"+pq_textField.getText()+"' where orderid='"+oid_textField.getText()+"' ";
					PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
							
					stmt.execute();
					
					JOptionPane.showMessageDialog(null, "Order data updated successfully");
					
					stmt.close();
					
					
				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				refreshTable();
			}
		});
		updateButton.setBounds(523, 321, 141, 35);
		contentPane.add(updateButton);
		
		//******************************************Back_button****************************************************
		
		backButton = new JButton("Back");
		backButton.setBackground(new Color(176, 196, 222));
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventory inventory = new Inventory();
				inventory.setVisible(true);
				dispose();
			}
		});
		backButton.setBounds(21, 11, 99, 23);
		contentPane.add(backButton);
		
		//***************************************************orderID_SEARCH_textField******************************************
		
		orderID_search_tetField = new JTextField();
		orderID_search_tetField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		orderID_search_tetField.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");

					String sql = "select * from `order` where orderid =? ";
					
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
					stmt.setString(1, orderID_search_tetField.getText() );
										
					ResultSet rs = stmt.executeQuery();
					
					if(rs.next()) {
						String add1=rs.getString("orderid");
						oid_textField.setText(add1);
						String add2=rs.getString("customer_first_name");
						fn_textField.setText(add2);
						String add3=rs.getString("customer_last_name");
						ln_textField.setText(add3);
						String add4=rs.getString("customer_id");
						cid_textField.setText(add4);
						String add5=rs.getString("product_name");
						pn_textField.setText(add5);
						String add6=rs.getString("product_id");
						pid_textField.setText(add6);
						String add7=rs.getString("ordered_unit");
						pq_textField.setText(add7);
												
					}
					
					table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				
				refreshTable();
			}
		});
		orderID_search_tetField.setBounds(704, 121, 186, 32);
		contentPane.add(orderID_search_tetField);
		orderID_search_tetField.setColumns(10);
			
		//***************************************************Clear*******************************************************
		
		JButton clearButton = new JButton("Clear");
		clearButton.setBackground(new Color(135, 206, 250));
		clearButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oid_textField.setText("");
				csearch_textField.setText("");
				fn_textField.setText("");
				ln_textField.setText("");
				cid_textField.setText("");
				pnsearch_textField.setText("");
				pn_textField.setText("");
				pid_textField.setText("");
				pq_textField.setText("");
				orderID_search_tetField.setText("");
				
			}
		});
		clearButton.setBounds(523, 547, 141, 35);
		contentPane.add(clearButton);
		
		lblNewLabel = new JLabel("Order ID Search:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(704, 86, 200, 26);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Customer Search:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1.setBounds(104, 196, 183, 29);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Product Search:");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_2.setBounds(104, 455, 188, 31);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Product Quantity:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_3.setBounds(100, 681, 187, 26);
		contentPane.add(lblNewLabel_3);
	}
}
