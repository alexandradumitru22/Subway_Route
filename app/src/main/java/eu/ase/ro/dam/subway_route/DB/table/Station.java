package eu.ase.ro.dam.subway_route.DB.table;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "stations",
        indices = {@Index(value = "lineId", unique = true)},
        foreignKeys = @ForeignKey(entity = Line.class, parentColumns = "id", childColumns = "lineId", onDelete = CASCADE))
public class Station implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    long id;

    @ColumnInfo(name = "cod")
    String cod;

    @ColumnInfo(name = "station")
    String station;

    @ColumnInfo(name = "node")
    Integer node;

    @ColumnInfo(name = "lineId")
    long lineId;

    @Ignore
    public Station() {
    }

    public Station(String station, long lineId) {
        this.station = station;
        this.lineId = lineId;
    }

    @Ignore
    public Station(long id, String cod, String station, Integer node, long lineId) {
        this.id = id;
        this.cod = cod;
        this.station = station;
        this.node = node;
        this.lineId = lineId;
    }

    protected Station(Parcel in) {
        id = in.readLong();
        cod = in.readString();
        station = in.readString();
        if (in.readByte() == 0) {
            node = null;
        } else {
            node = in.readInt();
        }
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

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Integer getNode() {
        return node;
    }

    public void setNode(Integer node) {
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
        return "STATIA: " + station + " - MAGISTRALA: " + lineId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(cod);
        dest.writeString(station);
        if (node == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(node);
        }
        dest.writeLong(lineId);
    }
}
