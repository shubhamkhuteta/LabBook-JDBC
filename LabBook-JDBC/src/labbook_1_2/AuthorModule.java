package labbook_1_2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;



public class AuthorModule {
	
	

	static Scanner sc=new Scanner(System.in);
	static String firstName,middleName,lastName,phoneNo;
	static int authorid , choice, update;
	static String column_name = null, newValue=null;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		int close = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		// First Step //
		//loading the driver class --> Oracle Class available in --> oracle.jdbc.driver
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// Second Step //
		// Create the connection --> Driver Manager

		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "abc", "abc123");

		while(close==0){

			System.out.println("1. Create Account ");
			System.out.println("2. Show Info ");
			System.out.println("3. Update Info");
			System.out.println("4. Delete Info");
			System.out.println("5. Books Info");
			System.out.println("6. Update Book Price");
			System.out.println("Enter your choice :");

			int input=sc.nextInt();

			
			
			
			switch(input) {
			case 1 : 
				String Q_insert = "insert into Author(authorid, firstName, middleName, lastName,phoneNo,bookisbn) values(?, ?, ?, ?, ?, isbn_seq.nextval)";
				
				System.out.println("Enter your Author ID : ");
				authorid=sc.nextInt();
				
				System.out.println("Enter Your First Name : ");
				firstName=sc.next();
				
				System.out.println("Enter Your Middle Name : ");
				middleName=sc.next();
				
				System.out.println("Enter Your Last Name : ");
				lastName=sc.next();
				
				System.out.println("Enter your Phone No : ");
				phoneNo=sc.next();
			

				pstmt = conn.prepareStatement(Q_insert); // create a statement
				pstmt.setInt(1,authorid ); // set input parameter 1
				pstmt.setString(2, firstName); // set input parameter 2
				pstmt.setString(3, middleName); // set input parameter 3
				pstmt.setString(4, lastName);
				pstmt.setString(5, phoneNo);
				//pstmt.setString(6, isbn_seq);
				int i=pstmt.executeUpdate(); // execute insert statement
				if(i==1) {
					
					System.out.println("**********Account created successfully!**********");
				}else {
					System.out.println("Account creation failed!");
				}
				
				
				break;

			case 2:
				
				String Q_select = "select authorid, firstName, middleName, lastName,phoneNo from Author where authorid = ?";
			    pstmt = conn.prepareStatement(Q_select); // create a statement
			    System.out.println("Enter your Author ID : ");
			    authorid=sc.nextInt();
			    
			    pstmt.setInt(1, authorid); // set input parameter
			    rs = pstmt.executeQuery();
			   
			    // extract data from the ResultSet
			    while (rs.next()) {
			    	authorid = rs.getInt(1);
			    	firstName  = rs.getString(2);
			    	middleName= rs.getString(3);
			    	lastName = rs.getString(4);
			    	phoneNo = rs.getString(5);
			    	
			    	System.out.println("Your ID : "+authorid);
			    	System.out.println("Your Name : "+firstName);
			    	System.out.println("Your Middle : "+middleName);
			    	System.out.println("Your Last Name : "+lastName);
			    	System.out.println("Your Phone No : "+phoneNo+"\n");
			      }

			    
				break;

			case 3:
				System.out.println("Enter your Author ID : ");
				authorid = sc.nextInt();
				
				
				System.out.println("What you want to update ?\n1. First Name\n2. Middle Name\n3. Last Name\n4. PhoneNo");
				choice = sc.nextInt();
				if(choice ==1) {
					 column_name="firstName";
				}else if(choice ==2){
					column_name="middleName";
				}else if(choice ==3){
					column_name="lastName";
				}else if(choice ==4){
					column_name="phoneNo";
				}else {
					System.out.println("Enter a valid choice !!");
				}
				
				System.out.println("Enter new value :");
				newValue  = sc.next();
				
				String Q_update_2 = "update Author set " +column_name+" = ? where authorid = ? ";
				pstmt = conn.prepareStatement(Q_update_2);
				
				pstmt.setString(1,newValue);
				pstmt.setInt(2, authorid); 
				update = pstmt.executeUpdate(); 
				if(update ==1) {
					System.out.println("Info Updated");
				}else {
					System.out.println("Sorry, Something went wrong!!");
				}
				break;
			case 4:
				System.out.println("Enter your Author ID : ");
				authorid = sc.nextInt();
				
				System.out.println("What you want to delete ?\n1. First Name\n2. Middle Name\n3. Last Name\n4. PhoneNo");
				choice = sc.nextInt();
				if(choice ==1) {
					 column_name="firstName";
				}else if(choice ==2){
					column_name="middleName";
				}else if(choice ==3){
					column_name="lastName";
				}else if(choice ==4){
					column_name="phoneNo";
				}else {
					System.out.println("Enter a valid choice !!");
				}
				
				
				
				String Q_delete = "update Author set " +column_name+" = null where authorid = ? ";
				pstmt = conn.prepareStatement(Q_delete);
				
				
				pstmt.setInt(1, authorid); 
				update = pstmt.executeUpdate(); 
				if(update ==1) {
					System.out.println("Info Updated");
				}else {
					System.out.println("Sorry, Something went wrong!!");
				}
				
				
				break;
		
			case 5:
				System.out.println("Enter your Author ID : ");
				authorid = sc.nextInt();
				String showBookTitle = "select a.authorid,b.title from Author a, Book b where a.authorid = b.authorid";
				pstmt = conn.prepareStatement(showBookTitle);
				
				ResultSet resultSet =pstmt.executeQuery();
				
					while(resultSet.next()) {
						int i1=1;
						int id = resultSet.getInt(1);
						if(id==authorid) {
							String title =resultSet.getString(2);
							System.out.println(i1+" "+title);
							i1++;
						}

					}
			
				break;

			case 6:
				System.out.println("Enter Author Name : ");
				firstName = sc.next();
				
				System.out.println("Enter Author ID : ");
				authorid = sc.nextInt();
				
				String showBooks = "select a.authorid,b.title from Author a, Book b where a.authorid = b.authorid";
				pstmt = conn.prepareStatement(showBooks);
				
				ResultSet resultSet1 =pstmt.executeQuery();
				
					while(resultSet1.next()) {
						
						int id = resultSet1.getInt(1);
						if(id==authorid) {
							String title =resultSet1.getString(2);
							System.out.println(" "+title);
							
							System.out.println("Enter Book Name : ");
							String bookName = sc.next();
							System.out.println("Enter Price : ");
							int bookPrice = sc.nextInt();
							
							
							String updatePrice="update Book set price = ? where title = ?";
							pstmt = conn.prepareStatement(updatePrice);
							
							pstmt.setInt(1,bookPrice);
							pstmt.setString(2, bookName); 
							update = pstmt.executeUpdate();
							if(update==1) {
								System.out.println("Price Updated!");
							}else {
								System.out.println("Operation Failed!");
							}
						}
						
						
						
					}
			
				break;
			default: 
				System.out.println("Please enter valid choice!");

				break;

			}	
			//sc.close();
		}	
	}
}
