package javaClass.v2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class CreditCard2 implements Parcelable {
    private final long cardNumber;
    private  int credit = 200;

    public CreditCard2(long cardNumber, int credit) {
        this.cardNumber = cardNumber;
        this.credit = credit;
    }

    protected CreditCard2(Parcel in) {
        cardNumber = in.readLong();
        credit = in.readInt();
    }

    public static final Creator<CreditCard2> CREATOR = new Creator<CreditCard2>() {
        @Override
        public CreditCard2 createFromParcel(Parcel in) {
            return new CreditCard2(in);
        }

        @Override
        public CreditCard2[] newArray(int size) {
            return new CreditCard2[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(cardNumber);
        dest.writeInt(credit);
    }
}
