package movieHandlerUpgrade;

import java.io.Serializable;

public class Book  extends Product implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2289246536973994264L;
	Person author;

	public Person getAuthor()
	{
		return author;
	}

	public void setAuthor(Person author)
	{
		this.author = author;
	}

	@Override
	public long getInvestment()
	{
		return author.getSalary();
	}

	public Book(String title, Person author)
	{
		this.author = author;
		this.id = IdGenerator.generate(this);
		this.title = title;
	}
	
	
}
