package bank;

class NegativeBalanceException extends RuntimeException//used for withdrawing
{
	private static final long serialVersionUID = 6278880227654651432L;

	//thrown if user tries to split on a hand w/o pairs
	NegativeBalanceException(String message){ super(message);}
}
