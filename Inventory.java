import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Inventory extends JFrame {
    private JPanel contentPane;
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	public void run() {
	try {
       	Inventory frame = new Inventory();
	frame.setVisible(true);
	} catch (Exception e) {
	e.printStackTrace();
	}
        }
        });
    }
	
   public Inventory() {
    //Frame attributes
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(50, 50, 1550, 900);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    contentPane.setBackground(new Color(255, 255, 255));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    //Buttons
    //BackButton
    JButton backButton = new JButton("Back");
    backButton.setBounds(21, 37, 122, 38);
    backButton.setFont (new Font("Times New Roman", Font.BOLD, 16));
    backButton.setBackground(new Color(176, 196, 222));
    contentPane.add(backButton);
    backButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
	MainMenu window = new MainMenu();
	window.frame.setVisible(true);
	dispose();
    }
    });
    //Logout Button		
    JButton button = new JButton("Logout");
    button.setBounds(1348, 37, 122, 38);
    button.setFont (new Font("Times New Roman", Font.BOLD, 16));
    button.setBackground(new Color(176, 196, 222));
    contentPane.add(button);
    button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
    });
    //Customer Button
    JButton customerButton = new JButton("Customer Info");
    customerButton.setBounds(122, 121, 533, 85);
    customerButton.setFont (new Font("Times New Roman", Font.BOLD, 30));
    customerButton.setBackground(new Color(0, 206, 209));
    contentPane.add(customerButton);
    customerButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
	dispose();
	CustomerInf customer = new CustomerInf();
	customer.setVisible(true);
    }
    });
    //ProductInfo Button		
    JButton pinfoButton = new JButton("Product Info");
    pinfoButton.setBounds(122, 324, 533, 85);
    pinfoButton.setFont (new Font("Times New Roman", Font.BOLD, 30));
    pinfoButton.setBackground(new Color(0, 206, 209));
    contentPane.add(pinfoButton);
    pinfoButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent arg0) {
	dispose();
	ProductInfo product = new ProductInfo();
	product.setVisible(true);
    }
    });
    //OrderInfo Button
    JButton oinfoButton = new JButton("Order Info");
    oinfoButton.setBounds(122, 544, 533, 85);
    oinfoButton.setFont (new Font("Times New Roman", Font.BOLD, 30));
    oinfoButton.setBackground(new Color(0, 206, 209));
    contentPane.add(oinfoButton);
    oinfoButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        dispose();
	OrderInfo order = new OrderInfo();
	order.setVisible(true);
    }   
    });
	
    }
}
