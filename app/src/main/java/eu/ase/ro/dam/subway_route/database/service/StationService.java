package eu.ase.ro.dam.subway_route.database.service;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;

import eu.ase.ro.dam.subway_route.database.DatabaseManager;
import eu.ase.ro.dam.subway_route.database.dao.StationDao;
import eu.ase.ro.dam.subway_route.database.table.Station;

public class StationService {
    private static StationDao stationDao;

    public static class GetAllStatios extends AsyncTask<Void, Void, List<Station>>{
        public GetAllStatios(Context context) {
            stationDao= DatabaseManager.getInstance(context).getStationDao();
        }

        @Override
        protected List<Station> doInBackground(Void... voids) {
            return stationDao.getAllStations();
        }
    }

    public static class InsertStation extends AsyncTask<Station, Void, Station>{
        public InsertStation(Context context) {
            stationDao= DatabaseManager.getInstance(context).getStationDao();
        }

        @Override
        protected Station doInBackground(Station... stations) {
            if(stations == null || stations.length != 1) {
                return null;
            }
            Station station = stations[0];
            long id = stationDao.insertStation(station);
            if(id != -1){
                station.setId(id);
                return station;
            }
            return null;
        }
    }

    public static class UpdateStation extends AsyncTask<Station, Void, Integer>{
        public UpdateStation(Context context) {
            stationDao= DatabaseManager.getInstance(context).getStationDao();
        }

        @Override
        protected Integer doInBackground(Station... stations) {
            if (stations == null || stations.length != 1) {
                return -1;
            }
            return stationDao.updateStation(stations[0]);
        }
    }

    public static class DeleteStation extends AsyncTask<Station, Void, Integer>{
        public DeleteStation(Context context) {
            stationDao= DatabaseManager.getInstance(context).getStationDao();
        }

        @Override
        protected Integer doInBackground(Station... stations) {
            if (stations == null || stations.length != 1) {
                return -1;
            }
            return stationDao.deleteStation(stations[0]);
        }
    }
}
