public class NoPairException extends Exception// used for the split function
{
	private static final long serialVersionUID = 1694935917440101962L;

	public NoPairException(String message)//thrown if user tries to split on a hand w/o pairs
	{
		super(message);
	}
}
