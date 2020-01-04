package eu.ase.ro.dam.subway_route.util_class;

public class ProfilPicture {
    String URL;
    String user;

    public ProfilPicture() {
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ProfilPicture{" +
                "URL='" + URL + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
