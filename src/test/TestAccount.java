package test;

import bank.*;
import org.junit.*;

import java.io.*;

import static java.lang.System.*;
import static org.junit.Assert.*;

public class TestAccount
{
    /*new account with $100*/
    @Test
    public void test0()
    {
        out.println("test 0: new account w/ $100");
        Account a = new Account(100);
        assertEquals(a.getBalance(), 100, 0);
    }

    /*deposit 100 in account*/
    @Test
    public void test1()
    {
        out.println("test 1: deposit $100");
        Account a = new Account(0);
        a.deposit(100);
        assertEquals(a.getBalance(), 100, 0);
    }
    /*deposit 100 withdraw 50*/
    @Test
    public void test2()
    {
        out.println("test 2: deposit $100, withdraw $50");
        Account a = new Account(0);
        a.deposit(100);
        a.withdraw(50);
        assertEquals(a.getBalance(), 50, 0);
    }

    /*deposit 100 withdraw 200*/
    @Test
    public void test3() throws IOException
    {
        out.println("test 3: deposit $100, withdraw $200");
        Account a = new Account(0);
        try
        {
            a.deposit(100);
            a.withdraw(200);
        }
        catch(Exception e)
        {
            PrintWriter pw = new PrintWriter(new File("withdraw.err"));
            e.printStackTrace(pw);
            pw.close();
        }
        finally {assertEquals(a.getBalance(), 100, 0);}
    }
}
