package eu.ase.ro.dam.subway_route.database.dao;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import eu.ase.ro.dam.subway_route.database.table.SubwayLine;

@Dao
public interface SubwayLineDao {

    @Query("select * from lines")
    List<SubwayLine> getAllLines();

    @Insert
    long insertLine(SubwayLine subwayLine);

    @Update
    int updateLine (SubwayLine subwayLine);

    @Delete
    int deleteLine(SubwayLine subwayLine);

    @Query("select COUNT(*) from lines")
    int countLines();
}
