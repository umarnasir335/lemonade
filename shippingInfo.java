import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import net.proteanit.sql.DbUtils;

import javax.swing.JComboBox;
import java.awt.Label;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Color;

public class shippingInfo extends JFrame {

	private JPanel contentPane;
	private JComboBox OrderID_comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					shippingInfo frame = new shippingInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	private JTextField length_textField;
	private JTextField width_textField;
	private JTextField height_textField;
	private JTextField weight_textField;
	private JTextField totalcost_textField;
	private JTextField fn_textField;
	private JTextField ln_textField;
	private JTextField pn_textField;
	private JTextField pid_textField;
	private JTextField pq_textField;
	private JTextField add1_textField;
	private JTextField add2_textField;
	private JTextField c_textField;
	private JTextField zp_textField;
	private JTextField s_textField;
	
	//*********************************************orderID_jcomboBox************************************************
	public void fillComboBox() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
					"password");

			
			String sql = "select * from `order`";
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				OrderID_comboBox.addItem(rs.getString("orderid"));
			}
						
		} catch (Exception e2) {
			
			System.out.println(e2);
		}
	}
	
	/**
	 * Create the frame.
	 */
	public shippingInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1550, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		OrderID_comboBox = new JComboBox();
		OrderID_comboBox.setBackground(new Color(173, 216, 230));
		OrderID_comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		OrderID_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");

					
					String sql = "select * from `order` where orderid=?";
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
					stmt.setString(1, (String)OrderID_comboBox.getSelectedItem());
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next())
					{
						fn_textField.setText(rs.getString("customer_first_name"));
						ln_textField.setText(rs.getString("customer_last_name"));
						pid_textField.setText(rs.getString("product_id"));
						pn_textField.setText(rs.getString("product_name"));
						pq_textField.setText(rs.getString("ordered_unit"));
					}
								
				} catch (Exception e2) {
					
					System.out.println(e2);
				}
			}
		});
		OrderID_comboBox.setBounds(468, 98, 154, 32);
		contentPane.add(OrderID_comboBox);
		OrderID_comboBox.addItem("Select");
		
		Label c_o_nlabel = new Label("Choose order #");
		c_o_nlabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		c_o_nlabel.setBounds(244, 97, 157, 33);
		contentPane.add(c_o_nlabel);
		
		Label dim_label = new Label("Dimensions (inch)");
		dim_label.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		dim_label.setBounds(117, 206, 174, 33);
		contentPane.add(dim_label);
		
		length_textField = new JTextField();
		length_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		length_textField.setBounds(336, 206, 65, 32);
		contentPane.add(length_textField);
		length_textField.setColumns(10);
		
		width_textField = new JTextField();
		width_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		width_textField.setColumns(10);
		width_textField.setBounds(458, 207, 65, 32);
		contentPane.add(width_textField);
		
		height_textField = new JTextField();
		height_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		height_textField.setColumns(10);
		height_textField.setBounds(570, 207, 65, 32);
		contentPane.add(height_textField);
		
		Label label_1 = new Label("X");
		label_1.setAlignment(Label.CENTER);
		label_1.setBounds(418, 206, 34, 33);
		contentPane.add(label_1);
		
		Label label_2 = new Label("X");
		label_2.setAlignment(Label.CENTER);
		label_2.setBounds(530, 206, 34, 33);
		contentPane.add(label_2);
		
		Label weight_label = new Label("Weight");
		weight_label.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		weight_label.setBounds(366, 306, 104, 33);
		contentPane.add(weight_label);
		
		weight_textField = new JTextField();
		weight_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		weight_textField.setColumns(10);
		weight_textField.setBounds(489, 306, 95, 32);
		contentPane.add(weight_textField);
		
		Label t_s_c_label = new Label("Total Shipping Cost:");
		t_s_c_label.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		t_s_c_label.setBounds(229, 501, 207, 33);
		contentPane.add(t_s_c_label);
		
		Label c_s_c_label = new Label("Choose shipping carrier:");
		c_s_c_label.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		c_s_c_label.setBounds(194, 398, 242, 33);
		contentPane.add(c_s_c_label);
		
		totalcost_textField = new JTextField();
		totalcost_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		totalcost_textField.setBounds(502, 501, 130, 32);
		contentPane.add(totalcost_textField);
		totalcost_textField.setColumns(10);
		
		Label fnlabel = new Label("First Name");
		fnlabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		fnlabel.setBounds(797, 121, 120, 33);
		contentPane.add(fnlabel);
		
		Label lnlabel = new Label("Last Name");
		lnlabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lnlabel.setBounds(797, 195, 120, 33);
		contentPane.add(lnlabel);
		
		//*****************************************auto_insert***************************************************
		
		fn_textField = new JTextField();
		fn_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		fn_textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");

					String sql = "select * from customer where first_name=?";
					
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
					stmt.setString(1, fn_textField.getText() );
										
					ResultSet rs = stmt.executeQuery();
					
					if(rs.next()) {
						String add1=rs.getString("address1");
						add1_textField.setText(add1);
						String add2=rs.getString("address2");
						add2_textField.setText(add2);
						String add3=rs.getString("city");
						c_textField.setText(add3);
						String add4=rs.getString("zipcode");
						zp_textField.setText(add4);
						String add5=rs.getString("state");
						s_textField.setText(add5);
											
					}
								
				} catch (Exception e2) {
					
					System.out.println(e2);
				}
			}
		});
		fn_textField.setBounds(945, 122, 186, 32);
		contentPane.add(fn_textField);
		fn_textField.setColumns(10);
		
		ln_textField = new JTextField();
		ln_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		ln_textField.setColumns(10);
		ln_textField.setBounds(945, 196, 186, 32);
		contentPane.add(ln_textField);
		
		Label pnlabel = new Label("Poduct Name");
		pnlabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		pnlabel.setBounds(778, 606, 139, 33);
		contentPane.add(pnlabel);
		
		pn_textField = new JTextField();
		pn_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		pn_textField.setColumns(10);
		pn_textField.setBounds(945, 606, 186, 32);
		contentPane.add(pn_textField);
		
		Label pidlabel = new Label("Product ID #");
		pidlabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		pidlabel.setBounds(787, 539, 130, 33);
		contentPane.add(pidlabel);
		
		pid_textField = new JTextField();
		pid_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		pid_textField.setColumns(10);
		pid_textField.setBounds(945, 539, 186, 32);
		contentPane.add(pid_textField);
		
		Label iolabel = new Label("Number of items ordered:");
		iolabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		iolabel.setBounds(681, 672, 261, 33);
		contentPane.add(iolabel);
		
		pq_textField = new JTextField();
		pq_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		pq_textField.setBounds(955, 672, 181, 32);
		contentPane.add(pq_textField);
		pq_textField.setColumns(10);
		
		Label lblabel = new Label("lb");
		lblabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblabel.setBounds(590, 306, 45, 33);
		contentPane.add(lblabel);
		
		//*************************************************back_button*************************************************
		
		JButton backButton = new JButton("Back");
		backButton.setBackground(new Color(176, 196, 222));
		backButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu window = new MainMenu();
				window.frame.setVisible(true);
				dispose();
			}
		});
		backButton.setBounds(47, 21, 80, 32);
		contentPane.add(backButton);
		//**********************************************logout**************************************************
		JButton logoutButton = new JButton("Logout");
		logoutButton.setBackground(new Color(176, 196, 222));
		logoutButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		logoutButton.setBounds(1294, 21, 95, 32);
		contentPane.add(logoutButton);
		
		JLabel add1Label = new JLabel("Address1");
		add1Label.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		add1Label.setBounds(813, 268, 104, 29);
		contentPane.add(add1Label);
		
		add1_textField = new JTextField();
		add1_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		add1_textField.setColumns(10);
		add1_textField.setBounds(945, 266, 186, 32);
		contentPane.add(add1_textField);
		
		JLabel add2lable = new JLabel("Address2");
		add2lable.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		add2lable.setBounds(810, 337, 95, 26);
		contentPane.add(add2lable);
		
		add2_textField = new JTextField();
		add2_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		add2_textField.setColumns(10);
		add2_textField.setBounds(945, 334, 186, 32);
		contentPane.add(add2_textField);
		
		Label citylabel = new Label("City");
		citylabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		citylabel.setBounds(838, 398, 52, 33);
		contentPane.add(citylabel);
		
		c_textField = new JTextField();
		c_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		c_textField.setBounds(945, 399, 186, 32);
		contentPane.add(c_textField);
		c_textField.setColumns(10);
		
		zp_textField = new JTextField();
		zp_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		zp_textField.setBounds(1041, 471, 90, 32);
		contentPane.add(zp_textField);
		zp_textField.setColumns(10);
		
		Label s_zplabel = new Label("State & Zipcode");
		s_zplabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		s_zplabel.setBounds(758, 470, 163, 33);
		contentPane.add(s_zplabel);
		
		s_textField = new JTextField();
		s_textField.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		s_textField.setColumns(10);
		s_textField.setBounds(945, 471, 80, 32);
		contentPane.add(s_textField);
		//******************************************Carrier_jcomboBox******************************************
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(173, 216, 230));
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		comboBox.setBounds(481, 398, 154, 32);
		contentPane.add(comboBox);
		comboBox.addItem("Select");
		comboBox.addItem("USPS");
		comboBox.addItem("UPS");
		comboBox.addItem("FedEx");
		
		//*********************************************submit_button************************************************
		
		JButton submitButton = new JButton("Submit");
		submitButton.setBackground(new Color(135, 206, 250));
		submitButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Connection conn = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false", "lemons",
							"password");
					
					String sql = "insert into shipping (customer_first_name, customer_last_name, address1, address2, city, state, zipcode, productid, product_name, ordered_unit, length, width, height, weight,carrier, shipping_cost ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
					PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
					
					stmt.setString(1, fn_textField.getText() );
					stmt.setString(2, ln_textField.getText() );
					stmt.setString(3, add1_textField.getText() );
					stmt.setString(4, add2_textField.getText() );
					stmt.setString(5, c_textField.getText() );
					stmt.setString(6, s_textField.getText() );
					stmt.setString(7, zp_textField.getText() );
					stmt.setString(8, pid_textField.getText() );
					stmt.setString(9, pn_textField.getText() );
					stmt.setString(10, pq_textField.getText() );
					stmt.setString(11, length_textField.getText() );
					stmt.setString(12, width_textField.getText() );
					stmt.setString(13, height_textField.getText() );
					stmt.setString(14, weight_textField.getText() );
					stmt.setString(15, (String)comboBox.getSelectedItem());
					stmt.setString(16, totalcost_textField.getText() );
										
					stmt.execute();
					
					sql = "UPDATE product SET shipped_unit = shipped_unit + ? WHERE productid = ?";
					stmt = (PreparedStatement) conn.prepareStatement(sql);

					int i = 1;
					stmt.setString(i++, pq_textField.getText() );
					stmt.setString(i++, pid_textField.getText() );

					stmt.execute();
					
					JOptionPane.showMessageDialog(null, "Order shippment is placed");
					
					stmt.close();
									
				} catch (Exception e2) {
					
					System.out.println(e2);
				}
			}
		});
		submitButton.setBounds(1258, 598, 154, 49);
		contentPane.add(submitButton);
		
		//**************************************************clear_button*********************************************
		JButton clearButton = new JButton("Clear");
		clearButton.setBackground(new Color(135, 206, 250));
		clearButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fn_textField.setText("");
				ln_textField.setText("");
				add1_textField.setText("");
				add2_textField.setText("");
				c_textField.setText("");
				s_textField.setText("");
				zp_textField.setText("");
				pn_textField.setText("");
				pid_textField.setText("");
				pq_textField.setText("");
				length_textField.setText("");
				width_textField.setText("");
				height_textField.setText("");
				weight_textField.setText("");
				totalcost_textField.setText("");
			}
		});
		clearButton.setBounds(1258, 495, 154, 43);
		contentPane.add(clearButton);
		
		fillComboBox();
	}
}
