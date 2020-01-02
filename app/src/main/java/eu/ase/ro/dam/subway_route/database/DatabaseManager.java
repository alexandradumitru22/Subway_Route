package eu.ase.ro.dam.subway_route.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import eu.ase.ro.dam.subway_route.database.dao.StationDao;
import eu.ase.ro.dam.subway_route.database.dao.SubwayLineDao;
import eu.ase.ro.dam.subway_route.database.table.Station;
import eu.ase.ro.dam.subway_route.database.table.SubwayLine;
import eu.ase.ro.dam.subway_route.util_interface.Const;

@Database(entities = {SubwayLine.class, Station.class}, version = 1, exportSchema = false)
public abstract class DatabaseManager extends RoomDatabase {
    public static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context){
        if(databaseManager == null){
            synchronized (DatabaseManager.class) {
                if(databaseManager == null) {
                    databaseManager = Room.databaseBuilder(context, DatabaseManager.class, Const.DATABASE_NAME).fallbackToDestructiveMigration().build();
                    return databaseManager;
                }
            }
        }
        return databaseManager;
    }

    public abstract SubwayLineDao getSubwayLineDao();
    public abstract StationDao getStationDao();
}
