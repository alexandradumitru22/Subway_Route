package eu.ase.ro.dam.subway_route.util_class;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class RouteAdapter extends ArrayAdapter <Route> {
    private Context context;
    private int resource;
    private List<Route> routes;


    public RouteAdapter(@NonNull Context context, int resource, @NonNull List<Route> routes) {
        super(context, resource, routes);
        this.context = context;
        this.resource = resource;
        this.routes = routes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
