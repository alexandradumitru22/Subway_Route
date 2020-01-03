package eu.ase.ro.dam.subway_route.DB.table;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "lines")
public class Line implements Parcelable {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    long id;

    @ColumnInfo(name = "line")
    String name;

    @ColumnInfo(name = "firstStation")
    String firstStation;

    @ColumnInfo(name = "lastStation")
    String lastStation;

    @ColumnInfo(name = "color")
    String lineColor;

    @ColumnInfo(name = "used")
    Integer used;

    @Ignore
    public Line() {
    }

    public Line(long id, String name, String firstStation, String lastStation, String lineColor, Integer used) {
        this.id = id;
        this.name = name;
        this.firstStation = firstStation;
        this.lastStation = lastStation;
        this.lineColor = lineColor;
        this.used = used;
    }

    protected Line(Parcel in) {
        id = in.readLong();
        name = in.readString();
        firstStation = in.readString();
        lastStation = in.readString();
        lineColor = in.readString();
        if (in.readByte() == 0) {
            used = null;
        } else {
            used = in.readInt();
        }
    }

    public static final Creator<Line> CREATOR = new Creator<Line>() {
        @Override
        public Line createFromParcel(Parcel in) {
            return new Line(in);
        }

        @Override
        public Line[] newArray(int size) {
            return new Line[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    @Override
    public String toString() {
        return "Line{" +
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
        if (used == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(used);
        }
    }
}
