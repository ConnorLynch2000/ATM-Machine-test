import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class AccountIteratorTest {
    private ArrayList<Account> accounts;
    private AccountIterator iter;

    @Before
    public void setUp(){
        accounts = new ArrayList<Account>(10);
        for(int i=0; i<10; i++){
            accounts.add(new Account("Username", i, i, 1000, 1000, 0));
        }
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

    @Test
    public void nextTest1(){
        try{
            iter.next(10);
            fail("Next Failed. expected out of bounds exception.");
        }catch(Exception e){
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }
    }

    @Test
    public void nextTest2(){
        try{
            iter.next(-1);
            fail("Next Failed. expected out of bounds exception.");
        }catch(Exception e){
            assertEquals(ArrayIndexOutOfBoundsException.class, e.getClass());
        }
    }

    @Test
    public void hasPrevTest1(){
        assertFalse("hasPrev failed.", iter.hasPrev(-1));
    }

    @Test
    public void hasPrevTest2(){
        assertFalse("hasPrev failed.", iter.hasPrev(15));
    }

    @Test
    public void hasPrevTest3(){
        assertTrue("hasPrev failed.", iter.hasPrev(5));
    }

}

