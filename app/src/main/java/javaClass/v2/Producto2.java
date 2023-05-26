package javaClass.v2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class Producto2 implements Parcelable {
    private final String productName;
    private int price;

    public Producto2(String productName, int price) {
        this.productName = productName;
        this.price = price;
    }

    protected Producto2(Parcel in) {
        productName = in.readString();
        price = in.readInt();
    }

    public static final Creator<Producto2> CREATOR = new Creator<Producto2>() {
        @Override
        public Producto2 createFromParcel(Parcel in) {
            return new Producto2(in);
        }

        @Override
        public Producto2[] newArray(int size) {
            return new Producto2[size];
        }
    };

    public String ObtenerNombre(){
        return productName;
    }
    public int obtenerPrecio (){
        return price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Producto2 producto = (Producto2) obj;
        return price == producto.price && Objects.equals(productName, producto.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, price);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(productName);
        dest.writeInt(price);
    }
}
