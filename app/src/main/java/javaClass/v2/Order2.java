package javaClass.v2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

import javaClass.v1.Customer;
import javaClass.v1.Product;

public class Order2 implements Parcelable {

        private final Customer2 customer;
        private final List<Producto2> products;
        private final String transaction;
        private final int amount;

        public Order2(Customer2 customer, List<Producto2> products, String transaction, int amount) {
            this.customer = customer;
            this.products = products;
            this.transaction = transaction;
            this.amount = amount;
        }
        public List<Producto2> obtenerProductos(){
            return products;
        }
        public Customer2 obtenerCustomer(){
            return customer;
        }
        public int obtenerTotal(){
            return amount;
        }

    protected Order2(Parcel in) {
        customer = in.readParcelable(Customer2.class.getClassLoader());
        products = in.createTypedArrayList(Producto2.CREATOR);
        transaction = in.readString();
        amount = in.readInt();
    }

    public static final Creator<Order2> CREATOR = new Creator<Order2>() {
        @Override
        public Order2 createFromParcel(Parcel in) {
            return new Order2(in);
        }

        @Override
        public Order2[] newArray(int size) {
            return new Order2[size];
        }
    };

    @Override
        public String toString() {
            return "Order{" +
                    "customer=" + customer +
                    ", products=" + products +
                    ", transaction='" + transaction + '\'' +
                    ", amount=" + amount +
                    '}';
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeParcelable(customer, flags);
        dest.writeTypedList(products);
        dest.writeString(transaction);
        dest.writeInt(amount);
    }
}
