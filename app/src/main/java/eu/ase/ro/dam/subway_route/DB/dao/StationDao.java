package eu.ase.ro.dam.subway_route.DB.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import eu.ase.ro.dam.subway_route.DB.table.Station;

@Dao
public interface StationDao {
    @Query("select * from stations")
    List<Station> getAllStations();

    @Insert
    long insert(Station station);

    @Update
    int update(Station station);

    @Delete
    int delete(Station station);

    @Query("SELECT COUNT (*) FROM stations")
    int countStations();
}
