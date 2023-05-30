package javaClass.v2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import javaClass.v1.CreditCard;

public class Customer2 implements Parcelable {
    private String nombre;
    private CreditCard2 creditCard;


    public Customer2(String nombre, CreditCard2 creditCard) {
        this.nombre = nombre;
        this.creditCard = creditCard;
    }
    public String obtenerNombre(){
        return nombre;
    }

    public CreditCard2 obtenerCreditCard(){
        return creditCard;
    }
    protected Customer2(Parcel in) {

        nombre = in.readString();
        creditCard = in.readParcelable(CreditCard2.class.getClassLoader());
    }

    public static final Creator<Customer2> CREATOR = new Creator<Customer2>() {
        @Override
        public Customer2 createFromParcel(Parcel in) {
            return new Customer2(in);
        }

        @Override
        public Customer2[] newArray(int size) {
            return new Customer2[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeParcelable(creditCard, flags);
    }

}
