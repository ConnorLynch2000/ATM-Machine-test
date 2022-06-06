import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;


public class CashDispenserTest {

    private CashDispenser cashDispenser;

    @Before
    public void setUp(){
        this.cashDispenser = new CashDispenser();
    }

    @Test
    public void tooMuchCashDispensingTest(){
        int preCount = this.cashDispenser.count;
        int exceededAmount = (this.cashDispenser.count*20)+1000;
        this.cashDispenser.dispenseCash(exceededAmount);
        int postCount = this.cashDispenser.count;
        assertEquals("Too much cash dispensing test",preCount, postCount);
    }
    @Test
    public void negativeCashDispensingTest(){
        int preCount = this.cashDispenser.count;
        int amount = -100;
        this.cashDispenser.dispenseCash(amount);
        int postCount = this.cashDispenser.count;
        assertEquals("Negative Cash dispensing test",preCount, postCount);
    }
    @Test
    public void negativeIsSufficientCashAvailableTest(){
        boolean bool = cashDispenser.isSufficientCashAvailable(-100);
        assertFalse("Negative number",bool);
    }
    @Test
    public void tooMuchCashAvailabilityTest(){
        boolean bool = cashDispenser.isSufficientCashAvailable(10000000);
        assertFalse("Too much cash",bool);
    }
    @Test
    public void cashAvailabilityTest(){
        boolean bool = cashDispenser.isSufficientCashAvailable(10);
        assertTrue("Available cash",bool);
    }
    
}
