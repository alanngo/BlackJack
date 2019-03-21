public class NegativeBalanceException extends Exception//used for withdrawing
{
	private static final long serialVersionUID = 6278880227654651432L;
	
	public NegativeBalanceException(String message)//thrown if user tries to split on a hand w/o pairs
	{
		super(message);
	}
}
