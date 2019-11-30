package eu.ase.ro.dam.subway_route.util_class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import eu.ase.ro.dam.subway_route.R;
import eu.ase.ro.dam.subway_route.activities.SearchRouteActivity;

public class RouteAdapter extends ArrayAdapter <Route> {
    private Context context;
    private int resource;
    private List<Route> routes;
    private LayoutInflater layoutInflater;


    public RouteAdapter(@NonNull Context context, int resource, @NonNull List<Route> routes, LayoutInflater layoutInflater) {
        super(context, resource, routes);

        this.context = context;
        this.resource = resource;
        this.routes = routes;
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);
        Route route = routes.get(position);
        if(route != null){
            adaptDate(view,route.getDate());
            adaptType(view, route.getType());
            adaptDepart(view, route.getDepart());
            adaptDest(view, route.getDestination());
            adaptShort(view, route.getShortestRoute());
        }
        return view;
    }

    private void adaptDepart(View view, String depart){
        TextView textView = view.findViewById(R.id.ll_depart_tv_depart);
        if(depart != null && !depart.trim().isEmpty()){
            textView.setText(depart+" - ");
        }
        else{
            textView.setText("*");
        }
    }

    private void adaptDest(View view, String dest){
        TextView textView = view.findViewById(R.id.ll_dest_tv_dest);
        if(dest != null && !dest.trim().isEmpty()){
            textView.setText(dest);
        }
        else{
            textView.setText("*");
        }
    }

    private void adaptDate(View view, Date date){
        TextView textView = view.findViewById(R.id.ll_date_tv_date);
        if(date != null){
            textView.setText("Calatorie la data de: " + new SimpleDateFormat(SearchRouteActivity.MY_DATE_FORMAT, Locale.CANADA).format(date));
        }
        else{
            textView.setText("*");
        }
    }

    private void adaptType(View view, String type){
        TextView textView = view.findViewById(R.id.ll_type_tv_type);
        if(type != null && !type.trim().isEmpty()){
            textView.setText(" "+type);
        }
        else{
            textView.setText("*");
        }
    }

    private void adaptShort(View view, String srtRoute){
        TextView textView = view.findViewById(R.id.tv_shortest_list);
        if(srtRoute != null && !srtRoute.trim().isEmpty()){
            textView.setText("Cautare ruta cea mai scurta: " + srtRoute);
        }
        else{
            textView.setText("*");
        }
    }
}
