package com.example.isilu.dailyplanner.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.isilu.dailyplanner.R;
import com.example.isilu.dailyplanner.activities.MainActivity;
import com.example.isilu.dailyplanner.fragments.PlanElement;

import java.util.List;

public class PlanAdapter extends ArrayAdapter<PlanElement> {
    private Context context;
    public PlanAdapter(@NonNull Context context, int resource) {
        super(context, resource, MainActivity.data);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_plan_element, parent,false);

        TextView title = rowView.findViewById(R.id.textTitle);

        title.setText(MainActivity.data.get(position).getTitle());
        ImageButton button = rowView.findViewById(R.id.deletePlanButton);
       button .setOnClickListener(v -> {
           MainActivity.data.remove(position);
           MainActivity.MAIN_ACTIVITY.refresh();
       });

        rowView.setOnClickListener(v -> Log.v(this.getClass().getSimpleName(),"title clicked"));;
        return rowView;
    }


    @Nullable
    @Override
    public PlanElement getItem(int position) {
        return MainActivity.data.get(position);
    }
}
