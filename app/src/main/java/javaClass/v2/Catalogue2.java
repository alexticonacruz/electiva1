package javaClass.v2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import javaClass.v1.Product;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalogue2 implements Parcelable  {
    private Map<String, Integer> products;

    public Catalogue2() {
        products = new HashMap<>();
    }

    protected Catalogue2(Parcel in) {
        // Leer el mapa de productos desde el Parcel
        int size = in.readInt();
        products = new HashMap<>();
        for (int i = 0; i < size; i++) {
            String key = in.readString();
            int value = in.readInt();
            products.put(key, value);
        }
    }
    public void remove(Producto2 product) {
        String key = product.ObtenerNombre();

        if (products.containsKey(key)) {
            products.put(key, products.get(key) - 1);
        }
    }
    public String bbr(){
        return String.valueOf(products.get("pera"));
    }
    public String bbr (String valor){
        return  String.valueOf(products.get(valor));
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

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }
    public void add(Producto2 product, int amount) {
        String key = product.ObtenerNombre();
        // if the product already exists, we just increase the amount to the previous amount
        if (products.containsKey(key)) {
            products.put(key, products.get(key) + amount);
        } else {
            products.put(key, amount);
        }
    }
    public Map<String,Integer> obtenerProductos(){
        return products;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        // Escribir el mapa de productos en el Parcel
        dest.writeInt(products.size());
        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeInt(entry.getValue());
        }
    }
}
