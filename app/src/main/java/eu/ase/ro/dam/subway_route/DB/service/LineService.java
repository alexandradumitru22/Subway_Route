package eu.ase.ro.dam.subway_route.DB.service;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import eu.ase.ro.dam.subway_route.DB.DatabaseManager;
import eu.ase.ro.dam.subway_route.DB.dao.LineDao;
import eu.ase.ro.dam.subway_route.DB.table.Line;

public class LineService {
    private static LineDao lineDao;

    public static class GetAllLines extends AsyncTask<Void, Void, List<Line>>{
        public GetAllLines(Context context) {
            lineDao = DatabaseManager.getInstance(context).getLineDao();
        }

        @Override
        protected List<Line> doInBackground(Void... voids) {
            return lineDao.getAllLines();
        }
    }

    public static class InserLine extends AsyncTask<Line, Void, Line>{
        public InserLine(Context context) {
            lineDao = DatabaseManager.getInstance(context).getLineDao();
        }

        @Override
        protected Line doInBackground(Line... lines) {
            if(lines == null || lines.length != 1){
                return null;
            }
            Line line = lines[0];
            long id = lineDao.insert(line);
            if(id != -1){
                line.setId(id);
                return line;
            }
            return null;
        }
    }

    public static class UpdateLine extends AsyncTask<Line, Void, Integer>{
        public UpdateLine(Context context) {
            lineDao = DatabaseManager.getInstance(context).getLineDao();
        }

        @Override
        protected Integer doInBackground(Line... lines) {
            if (lines == null || lines.length != 1) {
                return -1;
            }
            return lineDao.update(lines[0]);
        }
    }
}
