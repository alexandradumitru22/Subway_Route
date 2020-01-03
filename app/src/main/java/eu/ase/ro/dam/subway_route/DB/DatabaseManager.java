package eu.ase.ro.dam.subway_route.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import eu.ase.ro.dam.subway_route.DB.dao.LineDao;
import eu.ase.ro.dam.subway_route.DB.dao.StationDao;
import eu.ase.ro.dam.subway_route.DB.table.Line;
import eu.ase.ro.dam.subway_route.DB.table.Station;
import eu.ase.ro.dam.subway_route.util_class.MetrorexLines;
import eu.ase.ro.dam.subway_route.util_class.MetrorexSubwayStations;
import eu.ase.ro.dam.subway_route.util_interface.Const;

@Database(entities = {Line.class, Station.class}, version = 1, exportSchema = false)
public abstract class DatabaseManager extends RoomDatabase {
    public static DatabaseManager databaseManager;

    public abstract LineDao getLineDao();
    public abstract StationDao getStationDao();

    private void insertValuesIntoLines(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(getLineDao().countLines() == 0){
                    for(int i = 0; i< MetrorexLines.METROREX_LINES.length; i++){
                        getLineDao().insert(MetrorexLines.METROREX_LINES[i]);
                    }
                }
            }
        }).start();
    }

    private void insertValuesIntoStations(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(getStationDao().countStations() == 0){
                    for(int i = 0; i< MetrorexSubwayStations.METROREX_STATIONS.length; i++){
                        getStationDao().insert(MetrorexSubwayStations.METROREX_STATIONS[i]);
                    }
                }
            }
        }).start();
    }

    public static DatabaseManager getInstance(Context context){
        if(databaseManager == null){
            synchronized (DatabaseManager.class){
                if(databaseManager == null){
                    databaseManager = Room.databaseBuilder(context.getApplicationContext(), DatabaseManager.class, Const.DATABASE_NAME).fallbackToDestructiveMigration().build();
                    databaseManager.insertValuesIntoLines();
                    databaseManager.insertValuesIntoStations();
                    return databaseManager;
                }
            }
        }
        return databaseManager;
    }
}
