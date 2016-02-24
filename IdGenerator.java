package movieHandlerUpgrade;

public class IdGenerator
{
	private static int identifier = 1;
	public static String generate(Product obj)
	{
		if (obj instanceof Movie)
		{
			return "MOV"+identifier++;
		}
		else if (obj instanceof Game)
		{
			return "GAM"+identifier++;
		}
		else if (obj instanceof Book)
		{
			return "BOO"+identifier++;
		}
		return null;
	}
}
