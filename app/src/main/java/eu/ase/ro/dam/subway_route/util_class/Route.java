package eu.ase.ro.dam.subway_route.util_class;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import eu.ase.ro.dam.subway_route.activities.SearchRouteActivity;

public class Route implements Parcelable{
    private String depart;
    private String destination;
    private Date date;
    private String type;
    private String shortestRoute;
    private String username;

    public Route() {
    }

    public Route(String depart, String destination, Date date, String type, String shortestRoute) {
        this.depart = depart;
        this.destination = destination;
        this.date = date;
        this.type = type;
        this.shortestRoute = shortestRoute;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShortestRoute() {
        return shortestRoute;
    }

    public void setShortestRoute(String shortestRoute) {
        this.shortestRoute = shortestRoute;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Route{" +
                "depart='" + depart + '\'' +
                ", destination='" + destination + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", shortestRoute='" + shortestRoute + '\'' +
                '}';
    }

    protected Route(Parcel in) {
        this.depart = in.readString();
        this.destination = in.readString();
        try {
            this.date = new SimpleDateFormat(SearchRouteActivity.MY_DATE_FORMAT, Locale.CANADA).parse(in.readString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.type = in.readString();
        this.shortestRoute = in.readString();
    }

    public static final Creator<Route> CREATOR = new Creator<Route>() {
        @Override
        public Route createFromParcel(Parcel in) {
            return new Route(in);
        }

        @Override
        public Route[] newArray(int size) {
            return new Route[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(depart);
        dest.writeString(destination);String dateString = this.date != null ? new SimpleDateFormat(SearchRouteActivity.MY_DATE_FORMAT, Locale.CANADA).format(this.date) : null;
        dest.writeString(dateString);
        dest.writeString(type);
        dest.writeString(shortestRoute);
    }


}

