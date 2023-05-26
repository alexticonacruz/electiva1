package javaClass.v2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import javaClass.v1.Product;

public class Catalogue2 implements Parcelable {
    private Map<String, Integer> products = new HashMap<>();//Dictionary
    public  Catalogue2(){

    }
    protected Catalogue2(Parcel in) {
    }

    public static final Creator<Catalogue2> CREATOR = new Creator<Catalogue2>() {
        @Override
        public Catalogue2 createFromParcel(Parcel in) {
            return new Catalogue2(in);
        }

        @Override
        public Catalogue2[] newArray(int size) {
            return new Catalogue2[size];
        }
    };

    public void add(Producto2 product, int amount) {
        String key = product.ObtenerNombre();
        // if the product already exists, we just increase the amount to the previous amount
        if (products.containsKey(key)) {
            products.put(key, products.get(key) + amount);
        } else {
            products.put(key, amount);
        }
    }

    public void remove(Producto2 product) {
        String key = product.ObtenerNombre();

        if (products.containsKey(key)) {
            products.put(key, products.get(key) - 1);
        }
    }

    @Override
    public String toString() {
        return "Catalogue{" +
                "products=" + products +
                '}';
    }
    public Map<String, Integer> obtenerProductos(){
        return products;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeMap(products);
    }
}