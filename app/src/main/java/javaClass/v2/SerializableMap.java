package javaClass.v2;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

public class SerializableMap implements Parcelable {
    public Map<String, Integer> map;

    public SerializableMap(Map<String, Integer> map) {
        this.map = map;
    }

    protected SerializableMap(Parcel in) {
        int size = in.readInt();
        map = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            String key = in.readString();
            int value = in.readInt();
            map.put(key, value);
        }
    }

    public static final Creator<SerializableMap> CREATOR = new Creator<SerializableMap>() {
        @Override
        public SerializableMap createFromParcel(Parcel in) {
            return new SerializableMap(in);
        }

        @Override
        public SerializableMap[] newArray(int size) {
            return new SerializableMap[size];
        }
    };

    public Map<String, Integer> getMap() {
        return map;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(map.size());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeInt(entry.getValue());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
