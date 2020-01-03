package eu.ase.ro.dam.subway_route.util_class;

import eu.ase.ro.dam.subway_route.DB.table.Line;

public class MetrorexLines {
    public static Line[] METROREX_LINES = {
            new Line(1,"M1","Dristor","Pantelimon","galben",1),
            new Line(2,"M2","Berceni","Pipera","albastru",1),
            new Line(3,"M3","Preciziei","Anghel Saligny","rosu",1),
            new Line(4,"M4","Gara de Nord","Lac Straulesti","verde",1),
            new Line(5,"M5","Raul Doamnei","Pantelimon","portocaliu",0),
            new Line(6,"M6","Gara de Nord","Aeroport Henri Coanda-Otopeni","mov",0)
    };
}
