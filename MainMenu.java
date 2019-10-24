import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JTable;
import java.awt.Color;

public class MainMenu {

	JFrame frame;
	public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        MainMenu window = new MainMenu();
                        window.frame.setVisible(true);
                    } 
                    catch (Exception e) {
			e.printStackTrace();
                    }
		}
	});
}
    public MainMenu() {
        initialize();
    }

    private void initialize() {
        //Frame Attributes 
        frame = new JFrame();
        frame.getContentPane().setForeground(Color.BLACK);
        frame.setBounds(50, 50, 1550, 900);
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        //Title
        JLabel Lemonade = new JLabel("Lemonade", JLabel.CENTER);
        Lemonade.setBounds(247,83,1000,202);
        Lemonade.setFont (new Font("Montserrat", Font.BOLD, 160));
       
        frame.getContentPane().add(Lemonade);
       
        //Buttons: Logout, Inventory, Shipping, Profit & Loss  
        //Logout Button Function
        JButton Logout = new JButton("Logout");
        Logout.setForeground(new Color(0, 0, 0));
        Logout.setFont (new Font("Times New Roman", Font.BOLD, 20));
        Logout.setBackground(new Color(176, 196, 222));
        Logout.setBounds(1386, 41, 95, 28);
	frame.getContentPane().add(Logout);
	//Logout Button Function	
	Logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
            }
	});
	//ProfitLoss Button
	JButton profitLoss= new JButton("Profit & Loss");
	profitLoss.setForeground(Color.BLACK);
        profitLoss.setFont (new Font("Times New Roman", Font.BOLD, 30));
        profitLoss.setBackground(new Color(0, 206, 209));
        profitLoss.setBounds(1120, 601, 294, 92);
	frame.getContentPane().add(profitLoss);
        //ProfitLoss Button Function
	profitLoss.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
		ProfitLoss profitloss = new ProfitLoss();
		profitloss.setVisible(true);
		}
        });
	//Shipping Button		
	JButton shipping = new JButton("Shipping");
        shipping.setFont (new Font("Times New Roman", Font.BOLD, 30));
        shipping.setBackground(new Color(0, 206, 209));
        shipping.setBounds(610, 601, 282, 92);
	frame.getContentPane().add(shipping);
        //Shipping Button Function
	shipping.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
            frame.dispose();
            shippingInfo sp = new shippingInfo();
            sp.setVisible(true);
            }
	});
	//Inventory Button	
	JButton inventory = new JButton("Inventory");
        inventory.setFont (new Font("Times New Roman", Font.BOLD, 30));
        inventory.setBackground(new Color(0, 206, 209));
        inventory.setBounds(82, 601, 282, 89);
	frame.getContentPane().add(inventory);
	
	JLabel lblNewLabel = new JLabel("Inventory Management System");
	lblNewLabel.setBackground(new Color(255, 255, 255));
	lblNewLabel.setForeground(new Color(0, 0, 0));
	lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
	lblNewLabel.setBounds(749, 294, 407, 51);
	frame.getContentPane().add(lblNewLabel);
        //Inventory Button Function
	inventory.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
            frame.dispose();
            Inventory inventory = new Inventory();
            inventory.setVisible(true);
            }
	});
		
    }
}
