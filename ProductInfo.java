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
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class ProductInfo extends JFrame {

	private JPanel contentPane;
	private JTextField pid_textField;
	private JTextField pn_textField;
	private JTextField pp_textField;
	private JTextField sq_textField;
	private JTable table;
	private JTextField sp_textField;
	private JTextField search_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductInfo frame = new ProductInfo();
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

			
			String sql = "select productid, name, stock_quantity, product_price, sales_price from product";
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
	public ProductInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1550, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton pidButton = new JButton("Product ID");
		pidButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		pidButton.setBackground(new Color(176, 224, 230));
		pidButton.setBounds(80, 127, 201, 35);
		contentPane.add(pidButton);
		
		JButton pnButton = new JButton("Product Name");
		pnButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		pnButton.setBackground(new Color(176, 224, 230));
		pnButton.setBounds(80, 203, 201, 35);
		contentPane.add(pnButton);
		
		JButton sqButton = new JButton("Stock Quantity");
		sqButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		sqButton.setBackground(new Color(176, 224, 230));
		sqButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sqButton.setBounds(80, 282, 201, 35);
		contentPane.add(sqButton);
		
		JButton ppButton = new JButton("Product Price");
		ppButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		ppButton.setBackground(new Color(176, 224, 230));
		ppButton.setBounds(80, 364, 201, 35);
		contentPane.add(ppButton);
		
		pid_textField = new JTextField();
		pid_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		pid_textField.setBounds(357, 129, 186, 32);
		contentPane.add(pid_textField);
		pid_textField.setColumns(10);
		
		pn_textField = new JTextField();
		pn_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		pn_textField.setBounds(357, 205, 186, 32);
		contentPane.add(pn_textField);
		pn_textField.setColumns(10);
		
		pp_textField = new JTextField();
		pp_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		pp_textField.setBounds(357, 366, 186, 32);
		contentPane.add(pp_textField);
		pp_textField.setColumns(10);
		
		sq_textField = new JTextField();
		sq_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		sq_textField.setBounds(357, 284, 186, 32);
		contentPane.add(sq_textField);
		sq_textField.setColumns(10);
		
		//******************************************Product_Information_button******************************************************
		
		JButton productinfoButton = new JButton("Product Information");
		productinfoButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		productinfoButton.setBackground(new Color(176, 224, 230));
		productinfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");

					Statement stmt = (Statement) conn.createStatement();
					String sql = "select productid, name, stock_quantity, product_price, sales_price, shipped_unit from product";
					ResultSet rs = stmt.executeQuery(sql);
					
					table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				
			}
		});
		productinfoButton.setBounds(653, 127, 727, 55);
		contentPane.add(productinfoButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(653, 234, 736, 425);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		//******************************************Insert******************************************************
		
		JButton insertButton = new JButton("Insert");
		insertButton.setBackground(new Color(135, 206, 250));
		insertButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");
					
					String sql = "insert into product (productid, name, stock_quantity, product_price, sales_price ) values (?,?,?,?,?) ";
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
					
					stmt.setString(1, pid_textField.getText() );
					stmt.setString(2, pn_textField.getText() );
					stmt.setString(3, sq_textField.getText() );
					stmt.setString(4, pp_textField.getText() );
					stmt.setString(5, sp_textField.getText() );
										
					stmt.execute();
					
					JOptionPane.showMessageDialog(null, "New Product created successfully");
					
					stmt.close();
					
					
				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				refreshTable();
			}
		});
		insertButton.setBounds(80, 531, 141, 35);
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
														
					String sql1 = "delete from product where productid='"+pid_textField.getText()+"' ";
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql1);
										
					stmt.execute();
					
					JOptionPane.showMessageDialog(null, "Product data deleted successfully");
					
					stmt.close();
										
				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				refreshTable();
			}
		});
		deleteButton.setBounds(402, 531, 141, 35);
		contentPane.add(deleteButton);
		
		//******************************************Update******************************************************
		
		JButton updateButton = new JButton("Update");
		updateButton.setBackground(new Color(135, 206, 250));
		updateButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");
					
					String sql = "Update product set productid='"+pid_textField.getText()+"', name='"+pn_textField.getText()+"',stock_quantity='"+sq_textField.getText()+"',product_price='"+pp_textField.getText()+"',sales_price='"+sp_textField.getText()+"' where productid='"+pid_textField.getText()+"' ";
					PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
							
					stmt.execute();
					
					JOptionPane.showMessageDialog(null, "Product data updated successfully");
					
					stmt.close();
					
					
				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				refreshTable();
			}
		});
		updateButton.setBounds(240, 531, 141, 35);
		contentPane.add(updateButton);
		
		JButton spButton = new JButton("Sales Price");
		spButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		spButton.setBackground(new Color(176, 224, 230));
		spButton.setBounds(80, 445, 201, 35);
		contentPane.add(spButton);
		
		sp_textField = new JTextField();
		sp_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		sp_textField.setBounds(357, 447, 186, 32);
		contentPane.add(sp_textField);
		sp_textField.setColumns(10);
		
		//******************************************Clear_button******************************************************
		
		JButton clearButton = new JButton("Clear");
		clearButton.setBackground(new Color(135, 206, 250));
		clearButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pid_textField.setText("");
				pn_textField.setText("");
				sq_textField.setText("");
				pp_textField.setText("");
				sp_textField.setText("");
				search_textField.setText("");
			}
		});
		clearButton.setBounds(240, 609, 141, 35);
		contentPane.add(clearButton);
		
		//******************************************Search_NAME_TextField****************************************************
		
		search_textField = new JTextField();
		search_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		search_textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");

					String sql = "select * from product where name =? ";
					
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
					stmt.setString(1, search_textField.getText() );
										
					ResultSet rs = stmt.executeQuery();
					
					if(rs.next()) {
						String add1=rs.getString("productid");
						pid_textField.setText(add1);
						String add2=rs.getString("name");
						pn_textField.setText(add2);
						String add3=rs.getString("stock_quantity");
						sq_textField.setText(add3);
						String add4=rs.getString("product_price");
						pp_textField.setText(add4);
						String add5=rs.getString("sales_price");
						sp_textField.setText(add5);
												
					}
					
					table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e2) {
					
					System.out.println(e2);
				}
				refreshTable();
			}
		});
		search_textField.setBounds(653, 74, 236, 32);
		contentPane.add(search_textField);
		search_textField.setColumns(10);
		
		//*************************************************Back_button*************************************************
		
		JButton backButton = new JButton("Back");
		backButton.setBackground(new Color(176, 196, 222));
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventory inventory = new Inventory();
				inventory.setVisible(true);
				dispose();
			}
		});
		backButton.setBounds(80, 30, 111, 24);
		contentPane.add(backButton);
		
		JLabel lblNewLabel = new JLabel("Product search:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setBounds(653, 27, 236, 26);
		contentPane.add(lblNewLabel);
	}
}
