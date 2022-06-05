import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {
    
    private Account account;


    @Before
    public void setUp(){
        account = new Account("test", 12345, 54321, 1000, 1000, 0);
    }
    
    @Test
    public void validatePINTest1(){
        assertTrue(account.validatePIN(54321));
        assertFalse(account.validatePIN(12345));
        assertFalse(account.validatePIN(11111));
    }

    @Test
    public void getTotalBalanceTest(){
        assertEquals("invalid total balance", 1000, account.getTotalBalance(), 0);
    }

    @Test
    public void getAvailableBalanceTest(){
        assertEquals("invalid available balance", 1000, account.getAvailableBalance(), 0);
    }

    @Test
    public void creditTest1(){
        account.credit(1000);
        assertEquals("Credit Failed", 2000, account.getTotalBalance(), 0);
    }

    @Test
    public void creditTest2(){
        account.credit(0);
        assertEquals("Credit Failed", 1000, account.getTotalBalance(), 0);
    }

    @Test
    public void creditTest3(){
        account.credit(-1000);
        assertEquals("Credit Failed", 1000, account.getTotalBalance(), 0);
    }

    @Test
    public void creditTest4(){
        account.credit(-2000);
        assertEquals("Credit Failed", 1000, account.getTotalBalance(), 0);
    }


    @Test
    public void debitTest1(){
        account.debit(1000);
        assertEquals("Debit Failed", 0, account.getTotalBalance(), 0);
        assertEquals("Debit Failed", 0, account.getAvailableBalance(), 0);
    }

    @Test
    public void debitTest2(){
        account.debit(0);
        assertEquals("Debit Failed", 1000, account.getTotalBalance(), 0);
        assertEquals("Debit Failed", 1000, account.getAvailableBalance(), 0);
    }

    @Test
    public void debitTest3(){
        account.debit(-1000);
        assertEquals("Debit Failed", 1000, account.getTotalBalance(), 0);
        assertEquals("Debit Failed", 1000, account.getAvailableBalance(), 0);
    }

    @Test
    public void debitTest4(){
        account.debit(2000);
        assertEquals("Debit Failed", 1000, account.getTotalBalance(), 0);
        assertEquals("Debit Failed", 1000, account.getAvailableBalance(), 0);
    }
}
