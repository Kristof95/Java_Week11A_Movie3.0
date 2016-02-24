package movieHandlerUpgrade;
import java.io.Serializable;
import java.util.*;

public class Game extends Product implements Buyable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7621217413414361627L;
	boolean preOrdered;
	List<Person> staff;
	int price;
	
	public Game(String title,boolean preOrdered, List<Person> staff,int price)
	{
		super();
		this.preOrdered = preOrdered;
		this.staff = staff;
		this.price = price;
		this.id = IdGenerator.generate(this);
		this.title = title;
	}

	public boolean isPreOrdered()
	{
		return preOrdered;
	}
	
	public void setPreOrdered(boolean preOrdered)
	{
		this.preOrdered = preOrdered;
	}
	
	public List<Person> getStaff()
	{
		return staff;
	}
	
	public void setStaff(List<Person> staff)
	{
		this.staff = staff;
	}
	
	@Override
	public int getPrice()
	{
		if (preOrdered)
		{
			return (int)(price * 0.8);
		}
		return price;
	}

	public void setPrice(int price)
	{
		this.price = price;
	}
	
	@Override
	public long getInvestment()
	{
		long total = 0;
		for (Person person : staff)
		{
			total += person.getSalary();
		}
		return total;
	}
}
