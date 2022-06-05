import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;


public class WithdrawalTest {

    private Withdrawal withdrawal;
    private CashDispenser atmCashDispenser;

    @Before
    public void setUp(){
        Screen atmScreen = new Screen();
        atmScreen.getContentPane().removeAll();
        atmScreen.revalidate();
        atmScreen.messageJLabel7 = new JLabel();
        BankDatabase atmBankDatabase = new BankDatabase();
        Keypad atmKeypad = new Keypad();
        this.atmCashDispenser = new CashDispenser();
        this.withdrawal = new Withdrawal(12345, atmScreen, atmBankDatabase, atmKeypad, atmCashDispenser);
    }

    @Test
    public void transactionNotEnoughCashInAccountTest(){
        int exceededAmount = (int)withdrawal.getBankDatabase().getAccount(12345).getAvailableBalance()+10;
        withdrawal.transaction(exceededAmount);
        String expected = "\nInsufficient funds in your account." +
        "\n\nPlease choose a smaller amount.";
        String actual = withdrawal.getScreen().messageJLabel7.getText();
        assertEquals(expected, actual);
    }
    @Test
    public void transactionEnoughCashInAccountTest(){
        int availableAmount = (int)withdrawal.getBankDatabase().getAccount(12345).getAvailableBalance();
        withdrawal.transaction(availableAmount);
        String expected = "\nYour cash has been" +
        " dispensed. Please take your cash now.";
        String actual = withdrawal.getScreen().messageJLabel7.getText();
        assertEquals(expected, actual);
    }
    @Test
    public void transactionNotEnoughCashInAtmTest(){
        int availableAmount = (int)withdrawal.getBankDatabase().getAccount(12345).getAvailableBalance()-10;
        while(atmCashDispenser.isSufficientCashAvailable(availableAmount)){
            atmCashDispenser.dispenseCash(availableAmount);
        }
        withdrawal.transaction(availableAmount);
        String expected = "\nInsufficient cash available in the ATM." +
        "\n\nPlease choose a smaller amount.";
        String actual = withdrawal.getScreen().messageJLabel7.getText();
        assertEquals(expected, actual);
    }
    @Test
    public void transactionNegativeAmountTest(){
        double preBalance = withdrawal.getBankDatabase().getAccount(12345).getAvailableBalance();
        withdrawal.transaction(-10);
        double postBalance = withdrawal.getBankDatabase().getAccount(12345).getAvailableBalance();
        assertEquals("Negative Number for withdrawal",preBalance,postBalance,0);
    }
    @Test
    public void transactionInvalidAmountTest(){
        double preBalance = withdrawal.getBankDatabase().getAccount(12345).getAvailableBalance();
        withdrawal.transaction('J');
        double postBalance = withdrawal.getBankDatabase().getAccount(12345).getAvailableBalance();
        assertEquals("Character for withdrawal",preBalance,postBalance,0);
    }

}
