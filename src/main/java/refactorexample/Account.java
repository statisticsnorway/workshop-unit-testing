package refactorexample;

public class Account {
    public float principal;
    public float rate;
    public int daysActive;
    public int accountType;

    public static final int STANDARD = 0;
    public static final int BUDGET = 1;
    public static final int PREMIUM = 2;
    public static final int PREMIUM_PLUS = 3;

    public double interestEarned(Account account) {
        float years = daysActive / (float) 365.25;
        float compoundInterest = principal * (float) Math.exp(rate * years);
        return (compoundInterest - principal);
    }

    public boolean isPremium() {
        return (accountType == Account.PREMIUM || accountType == Account.PREMIUM_PLUS);
    }
}
