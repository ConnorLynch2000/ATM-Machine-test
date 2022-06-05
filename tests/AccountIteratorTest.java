import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class AccountIteratorTest {
    private ArrayList<Account> accounts;
    private AccountIterator iter;

    @Before
    public void setUp(){
        accounts = new ArrayList<Account>(10);
        iter = new AccountIterator(accounts);
    }

    @Test
    public void hasNextTest1(){
        assertTrue("HasNext failed.", iter.hasNext(8));
    }

    @Test
    public void hasNextTest2(){
        assertTrue("HasNext failed.", iter.hasNext(9));
    }

    @Test
    public void hasNextTest3(){
        assertFalse("HasNext failed.", iter.hasNext(10));
    }

    @Test
    public void hasNextTest4(){
        assertFalse("HasNext failed.", iter.hasNext(-1));
    }

    @Test
    public void hasNextTest5(){
        assertTrue("HasNext failed.", iter.hasNext(0));
    }
}

