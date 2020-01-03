package eu.ase.ro.dam.subway_route.DB.service;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import eu.ase.ro.dam.subway_route.DB.DatabaseManager;
import eu.ase.ro.dam.subway_route.DB.dao.StationDao;
import eu.ase.ro.dam.subway_route.DB.table.Line;
import eu.ase.ro.dam.subway_route.DB.table.Station;

public class StationService {
    private static StationDao stationDao;

    public static class GetAllStations extends AsyncTask<Void, Void, List<Station>> {
        public GetAllStations(Context context) {
            stationDao = DatabaseManager.getInstance(context).getStationDao();
        }

        @Override
        protected List<Station> doInBackground(Void... voids) {
            return stationDao.getAllStations();
        }
    }

    public static class InserStation extends AsyncTask<Station, Void, Station>{
        public InserStation(Context context) {
            stationDao = DatabaseManager.getInstance(context).getStationDao();
        }

        @Override
        protected Station doInBackground(Station... stations) {
            if(stations == null || stations.length != 1){
                return null;
            }
            Station station = stations[0];
            long id = stationDao.insert(station);
            if(id != -1){
                station.setId(id);
                return station;
            }
            return null;
        }
    }

    public static class UpdateStation extends AsyncTask<Station, Void, Integer>{
        public UpdateStation(Context context) {
            stationDao = DatabaseManager.getInstance(context).getStationDao();
        }


        @Override
        protected Integer doInBackground(Station... stations) {
            if (stations == null || stations.length != 1) {
                return -1;
            }
            return stationDao.update(stations[0]);
        }
    }

    public static class DeleteStation extends AsyncTask<Station, Void, Integer>{
        public DeleteStation(Context context) {
            stationDao = DatabaseManager.getInstance(context).getStationDao();
        }


        @Override
        protected Integer doInBackground(Station... stations) {
            if (stations == null || stations.length != 1) {
                return -1;
            }
            return stationDao.delete(stations[0]);
        }
    }
}
