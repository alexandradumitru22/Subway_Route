package eu.ase.ro.dam.subway_route.network;

public class ExtraInfo {
    private String standardProgram;
    private String weekendProgram;
    private String details;

    public ExtraInfo(String standardProgram, String weekendProgram, String details) {
        this.standardProgram = standardProgram;
        this.weekendProgram = weekendProgram;
        this.details = details;
    }

    public String getStandardProgram() {
        return standardProgram;
    }

    public void setStandardProgram(String standardProgram) {
        this.standardProgram = standardProgram;
    }

    public String getWeekendProgram() {
        return weekendProgram;
    }

    public void setWeekendProgram(String weekendProgram) {
        this.weekendProgram = weekendProgram;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Program standard: " + standardProgram + '\'' +
                " program in weekend: " + weekendProgram + "." + '\n' +
                "Alte detalii: " + details + '\'';
    }
}
