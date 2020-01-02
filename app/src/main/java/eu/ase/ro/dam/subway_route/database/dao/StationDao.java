package eu.ase.ro.dam.subway_route.database.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import eu.ase.ro.dam.subway_route.database.table.Station;

@Dao
public interface StationDao {

    @Query("select * from stations")
    List<Station> getAllStations();

    @Insert
    long insertStation (Station station);

    @Update
    int updateStation (Station station);

    @Delete
    int deleteStation (Station station);

    @Query("select COUNT(*) from stations")
    int countStations();
}
