import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private String accountHolder;
    private double balance;
    private List<String> transactions;

    public BankAccount(String accountHolder) {
        this.accountHolder = accountHolder;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        transactions.add("Deposited: ₹" + amount);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance.");
        }
        balance -= amount;
        transactions.add("Withdrawn: ₹" + amount);
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactions;
    }

    public String getAccountHolder() {
        return accountHolder;
    }
}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("Pooja");
    }

    @Test
    void testDeposit() {
        account.deposit(1000);
        assertEquals(1000, account.getBalance());
    }

    @Test
    void testDepositNegativeAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-500);
        });
        assertEquals("Deposit amount must be positive.", exception.getMessage());
    }

    @Test
    void testWithdraw() {
        account.deposit(2000);
        account.withdraw(500);
        assertEquals(1500, account.getBalance());
    }

    @Test
    void testWithdrawInsufficientBalance() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(1000);
        });
        assertEquals("Insufficient balance.", exception.getMessage());
    }

    @Test
    void testWithdrawNegativeAmount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(-100);
        });
        assertEquals("Withdrawal amount must be positive.", exception.getMessage());
    }

    @Test
    void testTransactionHistory() {
        account.deposit(1000);
        account.withdraw(400);
        List<String> history = account.getTransactionHistory();
        assertEquals(2, history.size());
        assertTrue(history.contains("Deposited: ₹1000.0"));
        assertTrue(history.contains("Withdrawn: ₹400.0"));
    }

    @Test
    void testAccountHolderName() {
        assertEquals("Pooja", account.getAccountHolder());
    }
}
