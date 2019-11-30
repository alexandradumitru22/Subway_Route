package eu.ase.ro.dam.subway_route.network;

import java.util.List;

public class HttpResponse {
    private List<SpecialTicket> eleviStudenti;
    private List<SpecialTicket> veteraniRazboi;
    private List<SpecialTicket> donatori;
    private List<SpecialTicket> persoaneHandicap;

    public HttpResponse(List<SpecialTicket> eleviStudenti, List<SpecialTicket> veteraniRazboi, List<SpecialTicket> donatori, List<SpecialTicket> persoaneHandicap) {
        this.eleviStudenti = eleviStudenti;
        this.veteraniRazboi = veteraniRazboi;
        this.donatori = donatori;
        this.persoaneHandicap = persoaneHandicap;
    }

    public List<SpecialTicket> getEleviStudenti() {
        return eleviStudenti;
    }

    public void setEleviStudenti(List<SpecialTicket> eleviStudenti) {
        this.eleviStudenti = eleviStudenti;
    }

    public List<SpecialTicket> getVeteraniRazboi() {
        return veteraniRazboi;
    }

    public void setVeteraniRazboi(List<SpecialTicket> veteraniRazboi) {
        this.veteraniRazboi = veteraniRazboi;
    }

    public List<SpecialTicket> getDonatori() {
        return donatori;
    }

    public void setDonatori(List<SpecialTicket> donatori) {
        this.donatori = donatori;
    }

    public List<SpecialTicket> getPersoaneHandicap() {
        return persoaneHandicap;
    }

    public void setPersoaneHandicap(List<SpecialTicket> persoaneHandicap) {
        this.persoaneHandicap = persoaneHandicap;
    }

    @Override
    public String toString() {
        return "HttpResponse{" +
                "eleviStudenti=" + eleviStudenti +
                ", veteraniRazboi=" + veteraniRazboi +
                ", donatori=" + donatori +
                ", persoaneHandicap=" + persoaneHandicap +
                '}';
    }
}
