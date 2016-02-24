package movieHandlerUpgrade;
import java.net.Socket;
import java.net.*;
import java.io.*;
import java.util.*;
import movieHandlerUpgrade.Movie.Genre;
//import movieHandlerUpgrade.Person.Gender;
import movieHandlerUpgrade.Person.Gender;

public class RentManager
{
	enum Command
	{
	GET, PUT, EXIT
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException
	{
		RentManager rm = new RentManager();
		
		Person ramiMalek = new Person("Rami","Malek",Gender.MALE,100);
		Person abdulMahri = new Person("Abdul","Mahri",Gender.MALE,100);
		Person rakeshMahanti = new Person("Rakesh","Mahanti",Gender.MALE,100);
		Person locksmith = new Person("Locksmith","Dzsumándzsi", Gender.MALE, 100);
			
		List<Person> person = new ArrayList<Person>();
		person.add(ramiMalek);
		person.add(abdulMahri);
		person.add(rakeshMahanti);
		person.add(locksmith);
		
		Game game1 = new Game("CS:GO",true,person,90);
		Game game2 = new Game("Goat Simulator",false,person,80);
		
		Movie movie1 = new Movie("Warcraft",Genre.HORROR,22,5.5,person,70);
		Movie movie2 = new Movie("Life of Brian",Genre.COMEDY,26,5.5,person,60);
		movie1.getCast().add(locksmith);
		movie1.getCast().add(abdulMahri);
		
		Book book1 = new Book("Vikings",ramiMalek);
		Book book2 = new Book("Universe",rakeshMahanti);
		
		List<Buyable> buyableProducts = new ArrayList<Buyable>();
		buyableProducts.add(movie1);
		buyableProducts.add(game1);	
		buyableProducts.add(game2);	
		
		//Client 
		Socket echoSocket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        String getInformation = "";
        
        try
		{
			echoSocket = new Socket("127.0.0.1", 8080);

			System.out.println("Connected to: "+echoSocket.getRemoteSocketAddress());
			System.out.println();
		} 
        catch (ConnectException e)
		{
        	System.err.println(e.getMessage());
        	System.exit(0);
		}
        catch(SocketException e)
        {
        	System.err.println(e.getMessage());
        }
        
        out = new ObjectOutputStream(echoSocket.getOutputStream());
        in = new ObjectInputStream(echoSocket.getInputStream());
        
        System.out.println();
        System.out.println("#---USER MAIN MENU---#");
		System.out.println("1. GET");
		System.out.println("2. PUT");
		System.out.println("3. EXIT");
		switch(rm.userInput())
		{
			case "1":
				System.out.println("GET command sent to server!");
				out.writeObject(Command.GET);
				out.flush();
				List<Object> getAnonym = new ArrayList<>();
		        try
				{
					getAnonym = (List<Object>) in.readObject();
					if(!getAnonym.isEmpty())
					{
						System.out.println("SERVER SENT AN OBJECT LIST!");
						for (Object object : getAnonym)
						{
							System.out.println(object);
						}
					}
					else
					{
						System.err.println("SERVER SENT AN EMPTY OBJECT LIST!");
					}
		        	
				} 
		        catch (EOFException e)
				{
					System.out.println(e.getMessage());
				}
				break;
			case "2":
				System.out.println();
				System.out.println("PUT command sent to server!");
		        out.writeObject(Command.PUT);
		        Thread.sleep(500); 
		        out.writeObject(ramiMalek);
		        out.flush();
				break;
			case "3":
				System.out.println("EXIT command sent to server!");
				out.writeObject(Command.EXIT);
				out.flush();
				break;
			default:
				System.err.println("This menu option is out of range!");
				System.err.println("Client socket will be closed!");
				break;
		}
       
        out.close();
        in.close();
	}
	
	public int priceOfTheProducts(List<Buyable> getListOfBuyables)
	{
		int total = 0;
		for (Buyable buyable : getListOfBuyables)
		{
			total += buyable.getPrice();
		}
		return total;
	}
	//USERINPUT///////////////////////////////////
	public String userInput()
	{
		BufferedReader bufferRead;
		System.out.println();
    	System.out.print("Command#: ");
		System.out.println();
	    String s = "";
	    
		try
		{
			bufferRead = new BufferedReader(new InputStreamReader(System.in));
			s = bufferRead.readLine();
		}
		catch (IOException e)
		{
			System.out.println(e.toString());
		}
	      
		return s;
	}
}
