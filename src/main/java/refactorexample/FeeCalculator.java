package refactorexample;

import java.util.Arrays;
import java.util.stream.Stream;

public class FeeCalculator {

    static final double BROKER_FEE_PERCENT = 0.0125;

    public float calculateFee(Account[] accounts) {
        float totalFee = 0;
        Account account;

        for (int i = 0; i < accounts.length; i++) {
            account = accounts[i];
            if (account.accountType == Account.PREMIUM ||
                    account.accountType == Account.PREMIUM_PLUS) {
                totalFee += .0125 * (account.principal * Math.exp(account.rate * (account.daysActive / 365.25))
                        - account.principal);
            }
        }
        return totalFee;
    }

    /*public float calculateFee(Account[] accounts) {
        final float[] totalFee = {0};
        Stream<Account> accountStream = Arrays.stream(accounts).filter(acct -> acct.isPremium());

        accountStream.forEach(accnt -> {
            totalFee[0] += BROKER_FEE_PERCENT * accnt.interestEarned(accnt);
        });

        return totalFee[0];
    }*/
}
