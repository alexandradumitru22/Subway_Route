package eu.ase.ro.dam.subway_route.util_class;

import android.os.Parcel;
import android.os.Parcelable;

public class Feedback implements Parcelable {
    String username;
    String comentariu;
    float nota;

    public Feedback() {
    }

    public Feedback(String comentariu, float nota) {
        this.comentariu = comentariu;
        this.nota = nota;
    }

    protected Feedback(Parcel in) {
        comentariu = in.readString();
        nota = in.readFloat();
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

    public String getComentariu() {
        return comentariu;
    }

    public void setComentariu(String comentariu) {
        this.comentariu = comentariu;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "username='" + username + '\'' +
                ", comentariu='" + comentariu + '\'' +
                ", nota=" + nota +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(comentariu);
        dest.writeFloat(nota);
    }
}
