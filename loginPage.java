import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class loginPage extends JFrame {

	private JPanel contentPane;
	private JTextField userTextField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginPage frame = new loginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
	/**
	 * Create the frame.
	 */
	public loginPage() {		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1250, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnUsername = new JButton("Username");
		btnUsername.setBackground(new Color(0, 206, 209));
		btnUsername.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btnUsername.setBounds(199, 124, 319, 72);
		contentPane.add(btnUsername);
		
		JButton btnPassword = new JButton("Password");
		btnPassword.setBackground(new Color(0, 206, 209));
		btnPassword.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btnPassword.setBounds(199, 227, 319, 72);
		contentPane.add(btnPassword);
		
		userTextField = new JTextField();
		userTextField.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		userTextField.setBounds(589, 125, 364, 71);
		contentPane.add(userTextField);
		userTextField.setColumns(10);
		
		//****************************************LoginPage*************************************************************************
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(173, 216, 230));
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					
					Class.forName("com.mysql.jdbc.Driver");  
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false","lemons","password");  
					 
					Statement stmt=con.createStatement();  
														
					String sql1 = "select * from logintb where user_name='"+userTextField.getText()+"' and password1='"+passwordField.getText()+"' ";
					ResultSet rs=stmt.executeQuery(sql1);
										
					while(rs.next()) {
						JOptionPane.showMessageDialog(null, "login successfully...");
						dispose();
						MainMenu window = new MainMenu();
						window.frame.setVisible(true);
					}
					con.close();
					
				}				
					catch (Exception e2) {
					
					System.out.println(e2);
				}
				
			
			}
			
		});
		btnLogin.setBounds(469, 354, 174, 55);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("Login Page");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 43));
		lblNewLabel.setBounds(384, 32, 347, 71);
		contentPane.add(lblNewLabel);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		passwordField.setBounds(589, 227, 364, 72);
		contentPane.add(passwordField);
	}
}
