package movieHandlerUpgrade;

import java.io.Serializable;
import java.util.*;

public class Movie extends Product implements Buyable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6219496324253510202L;

	public enum Genre
	{
		ACTION,THRILLER,HORROR,SCI_FI,ROMANTIC,COMEDY,DRAMA
	}
	
	Genre genre;
	long duration;
	double rate;
	List<Person> cast;
	int price;
	
	public Movie(String title,Genre genre, long duration, double rate, List<Person> cast, int price)
	{
		super();
		this.genre = genre;
		this.duration = duration;
		this.rate = rate;
		this.cast = cast;
		this.price = price;
		this.id = IdGenerator.generate(this);
		this.title = title;
	}
	
	public Genre getGenre()
	{
		return genre;
	}
	
	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}
	
	public long getDuration()
	{
		return duration;
	}
	
	public void setDuration(long duration)
	{
		this.duration = duration;
	}
	
	public double getRate()
	{
		return rate;
	}
	
	public void setRate(double rate)
	{
		this.rate = rate;
	}
	
	public List<Person> getCast()
	{
		return cast;
	}
	
	public void setCast(List<Person> cast)
	{
		this.cast = cast;
	}
	
	
	@Override
	public int getPrice()
	{
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
		for (Person person : cast)
		{
			total += person.getSalary();
		}
		return total;
	}
	
	
	
}
