package eu.ase.ro.dam.subway_route.database.table;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lines")
public class SubwayLine implements Parcelable {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    long id;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "firstStation")
    private String firstStation;

    @ColumnInfo(name = "lastStation")
    private String lastStation;

    @ColumnInfo(name = "color")
    private String lineColor;

    @ColumnInfo(name = "active")
    private int used;

    public SubwayLine(long id, @NonNull String name, String firstStation, String lastStation, String lineColor, int used) {
        this.id = id;
        this.name = name;
        this.firstStation = firstStation;
        this.lastStation = lastStation;
        this.lineColor = lineColor;
        this.used = used;
    }

    protected SubwayLine(Parcel in) {
        id = in.readLong();
        name = in.readString();
        firstStation = in.readString();
        lastStation = in.readString();
        lineColor = in.readString();
        used = in.readInt();
    }

    public static final Creator<SubwayLine> CREATOR = new Creator<SubwayLine>() {
        @Override
        public SubwayLine createFromParcel(Parcel in) {
            return new SubwayLine(in);
        }

        @Override
        public SubwayLine[] newArray(int size) {
            return new SubwayLine[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getFirstStation() {
        return firstStation;
    }

    public void setFirstStation(String firstStation) {
        this.firstStation = firstStation;
    }

    public String getLastStation() {
        return lastStation;
    }

    public void setLastStation(String lastStation) {
        this.lastStation = lastStation;
    }

    public String getLineColor() {
        return lineColor;
    }

    public void setLineColor(String lineColor) {
        this.lineColor = lineColor;
    }

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "SubwayLine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstStation='" + firstStation + '\'' +
                ", lastStation='" + lastStation + '\'' +
                ", lineColor='" + lineColor + '\'' +
                ", used=" + used +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(firstStation);
        dest.writeString(lastStation);
        dest.writeString(lineColor);
        dest.writeInt(used);
    }
}
