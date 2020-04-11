package resource.bank;

public class Account
{
	private double balance;
	
	public Account(double b) { balance=b; }

	public double getBalance() { return balance; }
	public void deposit(double amt) { balance+=amt;}
	public void withdraw(double amt) throws NegativeBalanceException//withdraw
	{
		if (balance<amt)
			throw new NegativeBalanceException("Insufficient funds! ");
		else
			balance-=amt;
	}
	
	public String toString() { return "Balance: $"+balance+"\n"; }
}