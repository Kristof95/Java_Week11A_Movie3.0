package movieHandlerUpgrade;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import movieHandlerUpgrade.Person.Gender;
import movieHandlerUpgrade.RentManager.Command;

public class ObjectServer
{
	private static final String FILEPATH = "C:\\Users\\Kristof\\Desktop\\Workspace\\Week_10A\\src\\movieHandlerUpgrade\\data.ser";
	
	enum ServerMode
	{
		LOAD, SAVE
	}
	
	public void save(Object obj)
	{
		FileOutputStream fileOut = null;
		ObjectOutputStream objectOut = null;
		try
		{
			fileOut = new FileOutputStream(FILEPATH,true);
			if(!new File(FILEPATH).exists())
			{
				new File(FILEPATH).createNewFile();
			}
			objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(obj);
			objectOut.close();
			System.out.println("Object successfully saved!");
		}
		catch(FileNotFoundException e)
		{
			System.err.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
	}
	
	
	public Object load() 
	{
		FileInputStream fileIn = null;
		ObjectInputStream objectIn = null;
		Object obj;
		List<Object> receivedObjects = new ArrayList<>();
		try
		{
			fileIn = new FileInputStream(FILEPATH);
			objectIn = new ObjectInputStream(fileIn);
			obj = objectIn.readObject();
			System.out.println("Objects load from file...");
			receivedObjects.add(obj);
			objectIn.close();
			return receivedObjects;
		} 
		catch(FileNotFoundException e)
		{
			System.err.println(e.getMessage());
		}
		catch(EOFException e)
		{
			System.err.println(e.getMessage());
		}
		catch(ClassNotFoundException e)
		{
			System.err.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, EOFException, FileNotFoundException
	{
		ServerSocket serverSocket = new ServerSocket(8080);
		Socket clientSocket = null;
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		ObjectServer objsrv = new ObjectServer();
		
		while(true)
		{

			System.out.println("I'm here to serve you, Master!");
			
			try 
			{ 
		         clientSocket = serverSocket.accept(); 
		         System.out.println();
		         System.out.println ("Client address:"+clientSocket.getRemoteSocketAddress());
		    } 
			catch(BindException e)
			{
				System.err.println(e.getMessage());
			}
			catch(ConnectException e)
			{
				System.err.println(e.getMessage());
			}
		    catch (IOException e) 
		    { 
		         System.err.println("Accept failed."); 
		         System.exit(1); 
		    }
			try
			{
				in = new ObjectInputStream(clientSocket.getInputStream());
				out = new ObjectOutputStream(clientSocket.getOutputStream());
			}
			catch(EOFException e)
			{
				System.err.println(e.getMessage());
				System.exit(1);
			}
	
			Person person = null;
//			Movie movie = null;
//			Game game = null;
//			Book book = null;
			
			Object getCommandFromClient = in.readObject();
			if(getCommandFromClient instanceof Command)
			{
				Command  cmd = (Command)getCommandFromClient;
				if(cmd.equals(Command.GET))
				{
					System.out.println();
					System.out.println("SWITCH TO "+ ServerMode.LOAD.name() +" MODE");
					out.writeObject(objsrv.load());
					out.flush();
				}
				else if(cmd.equals(Command.PUT))
				{
					System.out.println();
					System.out.println("SWITCH TO "+ ServerMode.SAVE.name() +" MODE");
					try
					{
						person = (Person) in.readObject();
						objsrv.save(person);
					}
					catch (EOFException e)
					{
						System.err.println(e.getMessage());
					}
				}
				else if(cmd.equals(Command.EXIT))
				{
					System.out.println("SERVER SHUTDOWN!");
					clientSocket.close();
					serverSocket.close();
					System.exit(1);
				}
			}			
			out.close();
			in.close();

		}
	}
}
