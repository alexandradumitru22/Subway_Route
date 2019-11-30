package eu.ase.ro.dam.subway_route.util_class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import eu.ase.ro.dam.subway_route.R;
import eu.ase.ro.dam.subway_route.network.ExtraInfo;
import eu.ase.ro.dam.subway_route.network.SpecialTicket;

public class StationAdapter extends ArrayAdapter <SpecialTicket> {
    private Context context;
    private int resource;
    private List<SpecialTicket> spcList;
    private LayoutInflater layoutInflater;

    public StationAdapter(@NonNull Context context,
                          int resource,
                          @NonNull List<SpecialTicket> spc,
                          LayoutInflater inflater) {
        super(context, resource, spc);

        this.context = context;
        this.resource = resource;
        this.spcList = spc;
        this.layoutInflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);
        SpecialTicket st = spcList.get(position);
        if(st != null){
            adaptStation(view,st.getStation());
            adaptLine(view, st.getLine());

            ExtraInfo extraInfo = st.getExtraInfo();
            String stdProg = extraInfo.getStandardProgram();
            String weekendProg = extraInfo.getWeekendProgram();
            String details = extraInfo.getDetails();

            adaptStdProgram(view, stdProg);
            adaptWeekendProg(view, weekendProg);
            adaptDetails(view, details);
        }
        return view;
    }

    private void adaptStation(View view, String station){
        TextView textView = view.findViewById(R.id.ll_station_tv_station);
        if(station != null && !station.trim().isEmpty()){
            textView.setText("Statia: "+station);
        }
        else{
            textView.setText("*");
        }
    }

    private void adaptLine(View view, String line){
        TextView textView = view.findViewById(R.id.ll_line_tv_line);
        if(line != null && !line.trim().isEmpty()){
            textView.setText(line);
        }
        else{
            textView.setText("*");
        }
    }

    private void adaptStdProgram(View view, String stdProg){
        TextView textView = view.findViewById(R.id.ll_std_program_tv_std);
        if(stdProg != null && !stdProg.trim().isEmpty()){
            textView.setText("Program saptamanal: "+stdProg);
        }
        else{
            textView.setText("*");
        }
    }

    private void adaptWeekendProg(View view, String weekendProg){
        TextView textView = view.findViewById(R.id.ll_weekend_prog_tv_wp);
        if(weekendProg != null && !weekendProg.trim().isEmpty()){
            textView.setText("Program weekend: "+weekendProg);
        }
        else{
            textView.setText("*");
        }
    }

    private void adaptDetails(View view, String details){
        TextView textView = view.findViewById(R.id.ll_details_tv_details);
        if(details != null && !details.trim().isEmpty()){
            textView.setText(details);
        }
        else{
            textView.setText("*");
        }
    }
}
