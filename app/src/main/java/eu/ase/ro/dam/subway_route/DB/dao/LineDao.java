package eu.ase.ro.dam.subway_route.DB.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import eu.ase.ro.dam.subway_route.DB.table.Line;

@Dao
public interface LineDao {
    @Query("select * from lines")
    List<Line> getAllLines();

    @Insert
    long insert(Line Line);

    @Update
    int update(Line Line);

    @Delete
    int delete(Line Line);

    @Query("SELECT COUNT (*) FROM lines")
    int countLines();
}
