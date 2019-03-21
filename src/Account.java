
public class Account 
{
	private double balance;
	
	public Account(double b)//overloaded constructor
	{
		balance=b;
	}

	public double getBalance()
	{
		return balance;
	}
	public void deposit(double amt)//deposit
	{
		balance+=amt;
	}
	public void withdraw(double amt) throws NegativeBalanceException//withdraw
	{
		if (balance<amt)
		{
			throw new NegativeBalanceException("Insufficient funds! ");
		}
		else
		{
			balance-=amt;
		}
	}
	public boolean hasMoney()
	{
		return balance>0;
	}
	
	public String toString()
	{
		return "Balance: "+balance;
	}
}