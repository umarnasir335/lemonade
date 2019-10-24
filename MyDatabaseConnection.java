import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class MyDatabaseConnection {
	
	public static void main(String args[]) {
	
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lemons?allowPublicKeyRetrieval=true&useSSL=false","lemons","password");  
			 
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("select i.Product_id, p.product_name, i.Sold_Quantity, p.sales_price, p.cost_per_item from inventory i, product p where i.id = p.id;");
			System.out.println(rs.getRow());
			while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+" "+rs.getString(4));  
			con.close();  
			}catch(Exception e){ System.out.println(e);}  
			}  
	}

	
	

