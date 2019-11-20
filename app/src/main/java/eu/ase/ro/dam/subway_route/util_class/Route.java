package eu.ase.ro.dam.subway_route.util_class;

import android.os.Parcel;
import android.os.Parcelable;

public class Route implements Parcelable{
    private String from;
    private String destination;
    private String shortestRoute;

    public Route(String from, String destination, String shortestRoute) {
        this.from = from;
        this.destination = destination;
        this.shortestRoute = shortestRoute;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getShortestRoute() {
        return shortestRoute;
    }

    public void setShortestRoute(String shortestRoute) {
        this.shortestRoute = shortestRoute;
    }

    @Override
    public String toString() {
        return "Se cauta ruta" +
                " de la " + from +
                " la " + destination +
                ". Ruta cea mai scuta - " + shortestRoute +
                ' ';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(from);
        dest.writeString(destination);
        dest.writeString(shortestRoute);
    }

    private Route (Parcel in){
        this.from = in.readString();
        this.destination=in.readString();
        this.shortestRoute=in.readString();
    }

    public static Creator<Route> CREATOR = new Creator<Route>() {
        @Override
        public Route createFromParcel(Parcel source) {
            return new Route(source);
        }

        @Override
        public Route[] newArray(int size) {
            return new Route[size];
        }
    };
}

