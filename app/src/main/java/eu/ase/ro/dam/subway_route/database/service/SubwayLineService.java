package eu.ase.ro.dam.subway_route.database.service;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;

import eu.ase.ro.dam.subway_route.database.DatabaseManager;
import eu.ase.ro.dam.subway_route.database.dao.SubwayLineDao;
import eu.ase.ro.dam.subway_route.database.table.SubwayLine;

public class SubwayLineService {
    private static SubwayLineDao subwayLineDao;

    public static class GetAllLines extends AsyncTask<Void, Void, List<SubwayLine>>{
        public GetAllLines(Context context){
            subwayLineDao = DatabaseManager.getInstance(context).getSubwayLineDao();
        }

        @Override
        protected List<SubwayLine> doInBackground(Void... voids) {
            return subwayLineDao.getAllLines();
        }
    }

    public static class InsertLine extends AsyncTask<SubwayLine, Void, SubwayLine>{
        public InsertLine(Context context){
            subwayLineDao = DatabaseManager.getInstance(context).getSubwayLineDao();
        }

        @Override
        protected SubwayLine doInBackground(SubwayLine... subwayLines) {
            if(subwayLines == null || subwayLines.length != 1) {
                return null;
            }
            SubwayLine subwayLine = subwayLines[0];
            long id = subwayLineDao.insertLine(subwayLine);
            if(id != -1){
                subwayLine.setId(id);
                return subwayLine;
            }
            return null;
        }
    }

    public static class UpdateLine extends AsyncTask<SubwayLine, Void, Integer>{
        public UpdateLine(Context context){
            subwayLineDao = DatabaseManager.getInstance(context).getSubwayLineDao();
        }

        @Override
        protected Integer doInBackground(SubwayLine... subwayLines) {
            if(subwayLines == null || subwayLines.length != 1) {
                return -1;
            }
            return subwayLineDao.updateLine(subwayLines[0]);
        }
    }

    public static class DeleteLine extends AsyncTask<SubwayLine, Void, Integer>{
        public DeleteLine(Context context){
            subwayLineDao = DatabaseManager.getInstance(context).getSubwayLineDao();
        }

        @Override
        protected Integer doInBackground(SubwayLine... subwayLines) {
            if(subwayLines == null || subwayLines.length != 1) {
                return -1;
            }
            return subwayLineDao.deleteLine(subwayLines[0]);
        }
    }
}
