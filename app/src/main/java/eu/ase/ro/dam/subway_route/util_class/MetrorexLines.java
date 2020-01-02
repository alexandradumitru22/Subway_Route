package eu.ase.ro.dam.subway_route.util_class;

import eu.ase.ro.dam.subway_route.database.table.SubwayLine;

public class MetrorexLines {
    public static SubwayLine[] METROREX_LINES = {
            new SubwayLine(1,"M1","Dristor","Pantelimon","galben",1),
            new SubwayLine(2,"M2","Berceni","Pipera","albastru",1),
            new SubwayLine(3,"M3","Preciziei","Anghel Saligny","rosu",1),
            new SubwayLine(4,"M4","Gara de Nord","Lac Straulesti","verde",1),
            new SubwayLine(5,"M5","Raul Doamnei","Pantelimon","portocaliu",0),
            new SubwayLine(6,"M6","Gara de Nord","Aeroport Henri Coanda-Otopeni","mov",0)
    };
}
