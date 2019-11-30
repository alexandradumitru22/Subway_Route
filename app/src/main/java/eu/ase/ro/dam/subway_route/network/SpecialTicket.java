package eu.ase.ro.dam.subway_route.network;

public class SpecialTicket {
    private String station;
    private String line;
    private ExtraInfo extraInfo;

    public SpecialTicket(String station, String line, ExtraInfo extraInfo) {
        this.station = station;
        this.line = line;
        this.extraInfo = extraInfo;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public ExtraInfo getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(ExtraInfo extraInfo) {
        this.extraInfo = extraInfo;
    }

    @Override
    public String toString() {
        return "SpecialTicket{" +
                "station='" + station + '\'' +
                ", line='" + line + '\'' +
                ", extraInfo=" + extraInfo +
                '}';
    }
}
