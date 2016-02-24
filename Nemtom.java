package movieHandlerUpgrade;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import movieHandlerUpgrade.Person.Gender;

public class Nemtom
{
	private static final String FILEPATH = "C:\\Users\\Kristof\\Desktop\\Workspace\\Week_10A\\src\\movieHandlerUpgrade\\data.ser";
	enum Bazdmeg
	{
		EXIT
	}
	
	public void save(Object obj)
	{
		FileOutputStream fileOut = null;
		ObjectOutputStream objectOut = null;
		try
		{
			fileOut = new FileOutputStream(FILEPATH,true);
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
	
	
	public static void main(String[] args)
	{
		Nemtom nm = new Nemtom();
		Person abdulMahri = new Person("Abdul","Mahri",Gender.MALE,100);
		nm.save(abdulMahri);
		Object person = (List<Object>)nm.load();
		for (Object obj : (List)person)
		{
			System.out.println(obj);
		}
//		nm.save();

	}
}
