package eu.ase.ro.dam.subway_route.util_class;

import android.os.Parcel;
import android.os.Parcelable;

public class Feedback implements Parcelable {
    String username;
    String comment;
    float mark;

    public Feedback(String comment, float mark) {
        this.comment = comment;
        this.mark = mark;
    }

    public Feedback(String username, String comment, float mark) {
        this.username = username;
        this.comment = comment;
        this.mark = mark;
    }

    protected Feedback(Parcel in) {
        comment = in.readString();
        mark = in.readFloat();
    }

    public static final Creator<Feedback> CREATOR = new Creator<Feedback>() {
        @Override
        public Feedback createFromParcel(Parcel in) {
            return new Feedback(in);
        }

        @Override
        public Feedback[] newArray(int size) {
            return new Feedback[0];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(comment);
        dest.writeFloat(mark);
    }
}
