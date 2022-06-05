import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import javax.swing.JTextField;

import org.junit.*;

public class BankDatabaseTest {
    
    private BankDatabase bankDatabase;

    @Before
    public void setUp(){
        bankDatabase = new BankDatabase();
    }

    @Test
    public void invalidAccountNumberGetAccountTest(){    
        assertEquals("Negative number as account number",null, bankDatabase.getAccount(-123));
    }
    @Test
    public void validAccountNumberGetAccountTest(){
        Account actual = bankDatabase.getAccount(98765);
        assertNotEquals("Valid Account number not null test",null, actual);
        if(actual != null){
            assertEquals("Correct account retrieval test",22222, actual.getPin());
        }
    }
    @Test
    public void authenticateUserNoSuchPINTest(){
        assertEquals("No such user test",false, bankDatabase.authenticateUser(88888));
    }
    @Test
    public void authenticateUserValidPINTest(){
        assertEquals("valid PIN test",true, bankDatabase.authenticateUser(00000));
    }
    @Test
    public void authenticateUserNegativePINTest(){
        assertEquals("negative PIN test",false, bankDatabase.authenticateUser(-11111));
    }
    @Test
    public void getadminTest(){
        /**PIN is passed to getadmin instead of userAccountNumber but AS the AccountNumber in order to see 
        how the outcome will be;There is no user with such AccountNumber so 
        If this returns the admin(number 1), then the test fails**/
        int actual = bankDatabase.getadmin(00000);
        assertEquals(0, actual);
    }
    @Test
    public void getaccpinTest(){
        int actual = bankDatabase.getaccpin(22222);
        assertEquals("Retrieving a user account number Test",98765, actual);
    }
    @Test
    public void adduserTest(){
        Screen.Inputfield1 = new JTextField();
        Screen.Inputfield2 = new JTextField();
        Screen.Inputfield3 = new JTextField();
        Screen.Inputfield4 = new JTextField();
        Screen.Inputfield1.setText("Jamil");
        Screen.Inputfield2.setText("33333");
        Screen.Inputfield3.setText("44444");
        Screen.Inputfield4.setText("0");
        bankDatabase.Adduser();
        Account actual = bankDatabase.getAccount(33333);
        assertNotEquals("Account retrieval test",null, actual);
    }
    @Test
    public void deleteUserTest1(){
        try{
            bankDatabase.Deleteuser(6);
            fail("Test failed. Out of bounds exception.");
        }catch(Exception e){
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }
    }

    @Test
    public void deleteUserTest2(){
        try{
            bankDatabase.Deleteuser(-1);
            fail("Test failed. Out of bounds exception.");
        }catch(Exception e){
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }
    }
}
