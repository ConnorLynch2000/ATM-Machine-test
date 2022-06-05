import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AccountFactoryTest {

    public AccountFactory account;

    @Before
    public void setUp(){
        account = new AccountFactory("Username", 12345, 54321, 1000, 1000, 0);
    }

    @Test
    public void AccountFactoryConstructorTest(){
        assertEquals("Set username failed.", "Username", account.getUsername());
        assertEquals("Set account number failed.", 12345, account.getAccountNumber(), 0);
        assertEquals("Set pin number failed.", 54321, account.getPin(), 0);
        assertEquals("Set available balanced failed.", 1000, account.getAvailableBalance(), 0);
        assertEquals("Set total balanced failed.", 1000, account.getTotalBalance(), 0);
        assertEquals("Set admin failed.", 0, account.getAdmin(), 0);
    }
}
