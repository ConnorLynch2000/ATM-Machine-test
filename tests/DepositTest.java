import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.security.Key;

import org.junit.Before;
import org.junit.Test;

public class DepositTest {
    private Deposit depo;
    private Account account;
    private Screen screen;

    @Before
    public void setUp(){
        BankDatabase bankdb = new BankDatabase();
        int accountnumber = 12345;
        account = bankdb.getAccount(accountnumber);
        screen = new Screen();
        screen.createlogin();
        Keypad keypad = new Keypad();
        keypad.setbuttons();
        depo = new Deposit(accountnumber, screen, bankdb, keypad, new DepositSlot());
    }

    @Test
    public void makeDepositTest1(){
        account.setTotalBalance(1000);
        account.setAvailableBalance(1000);
        depo.makedeposit(1000);
        assertEquals("MakeDeposit failed on total balance.", 2000, account.getTotalBalance(),0);
        assertEquals("MakeDeposit failed on available balance.", 1000, account.getAvailableBalance(),0);
    }

    @Test
    public void makeDepositTest2(){
        account.setTotalBalance(1000);
        account.setAvailableBalance(1000);
        depo.makedeposit(0);
        assertEquals("MakeDeposit failed on total balance.", 1000, account.getTotalBalance(),0);
        assertEquals("MakeDeposit failed on available balance.", 1000, account.getAvailableBalance(),0);
    }

    @Test
    public void makeDepositTest3(){
        account.setTotalBalance(1000);
        account.setAvailableBalance(1000);
        depo.makedeposit(-1000);
        assertEquals("MakeDeposit failed on total balance.", 1000, account.getTotalBalance(),0);
        assertEquals("MakeDeposit failed on available balance.", 1000, account.getAvailableBalance(),0);
    }

    @Test
    public void promptForDepositAmountTest(){
        depo.execute();
        assertEquals("Deposit", screen.button1.getText());
    }
}