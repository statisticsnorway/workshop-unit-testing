package refactorExample;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import refactorexample.Account;
import refactorexample.FeeCalculator;

import static org.junit.jupiter.api.Assertions.*;

public class FeeCalculatorTest {
    FeeCalculator feeCalculator;

    @BeforeEach
    void setUp() {
        feeCalculator = new FeeCalculator();
    }

    @Test
    @DisplayName("Test Fee calculated is correct")
    public void testCalculatedFeeIsCorrect() {
        Account[] accounts = new Account[3];

        accounts[0] = new Account();
        accounts[0].principal = 35;
        accounts[0].rate = (float) .04;
        accounts[0].daysActive = 365;
        accounts[0].accountType = Account.PREMIUM;

        accounts[1] = new Account();
        accounts[1].principal = 100;
        accounts[1].rate = (float) .035;
        accounts[1].daysActive = 100;
        accounts[1].accountType = Account.BUDGET;

        accounts[2] = new Account();
        accounts[2].principal = 50;
        accounts[2].rate = (float) .04;
        accounts[2].daysActive = 600;
        accounts[2].accountType = Account.PREMIUM_PLUS;

        float result = feeCalculator.calculateFee(accounts);
        assertEquals((float) 0.060289, result, (float) 0.00001);

    }

    @Test
    @DisplayName("Test fee is not calculated for Non-Premium accounts")
    public void testFeeNotCalculatedForNonPremiumAccounts() {
        Account[] accounts = new Account[2];
        accounts[0] = new Account();
        accounts[0].principal = 12;
        accounts[0].rate = (float) .025;
        accounts[0].rate = (float) .025;
        accounts[0].accountType = Account.BUDGET;

        accounts[1] = new Account();
        accounts[1].principal = 50;
        accounts[1].rate = (float) .0265;
        accounts[1].daysActive = 150;
        accounts[1].accountType = Account.STANDARD;
        float result = feeCalculator.calculateFee(accounts);
        assertEquals(0, result, 0.0001);
    }

    @Test
    @DisplayName("Test for zero Rate")
    public void testCalculatedFeeIsZeroIfRateIsZero() {
        Account[] accounts = new Account[1];
        accounts[0] = new Account();
        accounts[0].principal = 1000;
        accounts[0].rate = (float) 0;
        accounts[0].daysActive = 100;
        accounts[0].accountType = Account.PREMIUM;
        float result = feeCalculator.calculateFee(accounts);
        assertEquals(0, result, 0.00001);
    }

    @Test
    @DisplayName("Test for negative principal amount")
    public void testCalculatedFeeIsCorrectIfPrincipalisNegativeValue() {
        Account[] accounts = new Account[1];
        accounts[0] = new Account();
        accounts[0].principal = -10000;
        accounts[0].rate = (float) 0.263;
        accounts[0].daysActive = 100;
        accounts[0].accountType = Account.PREMIUM;
        float result = feeCalculator.calculateFee(accounts);
        assertEquals(-9.33265, result, 0.0001);
    }

    @Test
    @DisplayName("Test for duplicate reference")
    public void testCalculatedFeeIsCalculatedIfReferencedAmountIsDuplicated() {
        Account[] accounts = new Account[3];
        accounts[0] = new Account();
        accounts[0].principal = 35;
        accounts[0].rate = (float) .04;
        accounts[0].daysActive = 365;
        accounts[0].accountType = Account.PREMIUM;
        accounts[1] = accounts[0];
        accounts[2] = new Account();
        accounts[2].principal = 50;
        accounts[2].rate = (float) .04;
        accounts[2].daysActive = 600;
        accounts[2].accountType = Account.PREMIUM_PLUS;
        float result = feeCalculator.calculateFee(accounts);
        assertEquals(0.0781316, result, 0.000001);
    }

    @Test
    @DisplayName("Test for null account, method to calculate fee chokes")
    public void testCalculatedFeeChokesIfAccountReferenceIsNull() {
        Account[] accounts = null;
        try {
            float result = feeCalculator.calculateFee(accounts);
            fail();
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }
}
