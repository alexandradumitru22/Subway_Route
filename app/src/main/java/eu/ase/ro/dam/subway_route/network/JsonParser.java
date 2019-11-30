package eu.ase.ro.dam.subway_route.network;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.ArrayList;
import java.text.ParseException;
import java.util.List;

public class JsonParser {
    public static HttpResponse parseJson (String json){
        if(json == null){
            return null;
        }
        try {
            JSONObject jsonObject = new JSONObject(json);
            List<SpecialTicket> elevi = getSpecialTicketListFromJson(jsonObject.getJSONArray("eleviStudenti"));
            List<SpecialTicket> veterani = getSpecialTicketListFromJson(jsonObject.getJSONArray("veteraniRazboi"));
            List<SpecialTicket> donatori = getSpecialTicketListFromJson(jsonObject.getJSONArray("donatori"));
            List<SpecialTicket> handicap = getSpecialTicketListFromJson(jsonObject.getJSONArray("persoaneHandicap"));

            return new HttpResponse(elevi, veterani, donatori, handicap);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static List<SpecialTicket> getSpecialTicketListFromJson(JSONArray jsonArray) throws JSONException {
        if(jsonArray == null){
            return null;
        }
        List<SpecialTicket> results = new ArrayList<>();
        for(int i=0; i<jsonArray.length(); i++){
            SpecialTicket specialTicket = getSpecialTicketFromJson(jsonArray.getJSONObject(i));
            if(specialTicket != null){
                results.add(specialTicket);
            }
        }
        return results;
    }

    private static SpecialTicket getSpecialTicketFromJson(JSONObject jsonObject) throws JSONException {
        if(jsonObject == null){
            return null;
        }
        String station = jsonObject.getString("statia");
        String lines = jsonObject.getString("magistrale");
        ExtraInfo extraInfo = getExtraInfoFromJson(jsonObject.getJSONObject("extraInfo"));

        return new SpecialTicket(station, lines, extraInfo);
    }

    private static ExtraInfo getExtraInfoFromJson(JSONObject jsonObject) throws JSONException {
        if(jsonObject == null){
            return null;
        }

        String standardProgram = jsonObject.getString("programSaptamana");
        String weekendProgram = jsonObject.getString("programWeekend");
        String details = jsonObject.getString("detalii");

        return new ExtraInfo(standardProgram,weekendProgram,details);
    }
}
