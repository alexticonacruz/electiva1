package javaClass.v2;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class cantidadProductos implements Parcelable {
    private Map<String, Integer> cantidadProdutos;

    public cantidadProductos() {
    }
    public void Add (Map<String,Integer> valor){
        cantidadProdutos = valor;
    }

    protected cantidadProductos(Parcel in) {
        // Leer el mapa de productos desde el Parcel
        int size = in.readInt();
        cantidadProdutos = new HashMap<>();
        for (int i = 0; i < size; i++) {
            String key = in.readString();
            int value = in.readInt();
            cantidadProdutos.put(key, value);
        }
    }

    public static final Creator<cantidadProductos> CREATOR = new Creator<cantidadProductos>() {
        @Override
        public cantidadProductos createFromParcel(Parcel in) {
            return new cantidadProductos(in);
        }

        @Override
        public cantidadProductos[] newArray(int size) {
            return new cantidadProductos[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        // Escribir el mapa de productos en el Parcel
        dest.writeInt(cantidadProdutos.size());
        for (Map.Entry<String, Integer> entry : cantidadProdutos.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeInt(entry.getValue());
        }
    }
}
