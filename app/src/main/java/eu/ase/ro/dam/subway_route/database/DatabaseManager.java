package eu.ase.ro.dam.subway_route.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import eu.ase.ro.dam.subway_route.database.dao.StationDao;
import eu.ase.ro.dam.subway_route.database.dao.SubwayLineDao;
import eu.ase.ro.dam.subway_route.database.table.Station;
import eu.ase.ro.dam.subway_route.database.table.SubwayLine;
import eu.ase.ro.dam.subway_route.util_class.MetrorexLines;
import eu.ase.ro.dam.subway_route.util_class.MetrorexSubwayStations;
import eu.ase.ro.dam.subway_route.util_interface.Const;

@Database(entities = {SubwayLine.class, Station.class}, version = 5, exportSchema = false)
public abstract class DatabaseManager extends RoomDatabase {
    public static DatabaseManager databaseManager;

    private void insertAllExistentLines(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(getSubwayLineDao().countLines() == 0){
                    for (int i=0; i< MetrorexLines.METROREX_LINES.length; i++){
                        getSubwayLineDao().insertLine(MetrorexLines.METROREX_LINES[i]);
                    }
                }
            }
        }).start();
    }

    private void insertAllExistentStations(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(getStationDao().countStations() == 0){
                    for (int i=0; i< MetrorexSubwayStations.METROREX_STATIONS.length; i++){
                        getStationDao().insertStation(MetrorexSubwayStations.METROREX_STATIONS[i]);
                    }
                }
            }
        }).start();
    }

    public static DatabaseManager getInstance(Context context){
        if(databaseManager == null){
            synchronized (DatabaseManager.class) {
                if(databaseManager == null) {
                    databaseManager = Room.databaseBuilder(context, DatabaseManager.class, Const.DATABASE_NAME).fallbackToDestructiveMigration().build();
                    databaseManager.insertAllExistentLines();
                    databaseManager.insertAllExistentStations();
                    return databaseManager;
                }
            }
        }
        return databaseManager;
    }

    public abstract SubwayLineDao getSubwayLineDao();
    public abstract StationDao getStationDao();
}
