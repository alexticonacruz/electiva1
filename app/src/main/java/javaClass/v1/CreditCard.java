package javaClass.v1;

public class CreditCard {
    private final long cardNumber;
    private final int credit = 200;

    public CreditCard(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public int getCredit() {
        return credit;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "cardNumber=" + cardNumber +
                '}';
    }
}
