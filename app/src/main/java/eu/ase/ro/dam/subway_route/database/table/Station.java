package eu.ase.ro.dam.subway_route.database.table;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "stations",
        indices = {@Index(value = "lineId", unique = true)},
        foreignKeys = @ForeignKey(entity = SubwayLine.class, parentColumns = "id", childColumns = "lineId", onDelete = CASCADE))
public class Station implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "cod")
    private String cod;

    @ColumnInfo(name = "station")
    private String stationName;

    @ColumnInfo(name = "node")
    private int node;

    @ColumnInfo(name = "lineId")
    private long lineId;

    public Station(long id, String cod, String stationName, int node, long lineId) {
        this.id = id;
        this.cod = cod;
        this.stationName = stationName;
        this.node = node;
        this.lineId = lineId;
    }

    protected Station(Parcel in) {
        id = in.readLong();
        cod = in.readString();
        stationName = in.readString();
        node = in.readInt();
        lineId = in.readLong();
    }

    public static final Creator<Station> CREATOR = new Creator<Station>() {
        @Override
        public Station createFromParcel(Parcel in) {
            return new Station(in);
        }

        @Override
        public Station[] newArray(int size) {
            return new Station[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public long getLineId() {
        return lineId;
    }

    public void setLineId(long lineId) {
        this.lineId = lineId;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", cod='" + cod + '\'' +
                ", stationName='" + stationName + '\'' +
                ", node=" + node +
                ", lineId=" + lineId +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(cod);
        dest.writeString(stationName);
        dest.writeInt(node);
        dest.writeLong(lineId);
    }
}

