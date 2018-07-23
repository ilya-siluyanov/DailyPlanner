package com.example.isilu.dailyplanner.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.isilu.dailyplanner.R;
import com.example.isilu.dailyplanner.activities.MainActivity;
import com.example.isilu.dailyplanner.adapters.PlanAdapter;
import com.example.isilu.dailyplanner.staticData.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListPlanFragment extends ListFragment {
    private int index = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment,container,false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v(this.getClass().getSimpleName(),"onActivityCreated method was called");
        setListAdapter(getListAdapter());
        Log.v(this.getClass().getSimpleName(),"adapter has set");
   }


   public ListAdapter getListAdapter(){
       List<HashMap<String,String>> arrayList = new ArrayList<>();
       HashMap<String,String> map = new HashMap<>();
       for (int i = 0;i<MainActivity.data.size();i++){
           map = new HashMap<>();
           Log.v(this.getClass().getSimpleName(),MainActivity.data.get(i).toString());
               map.put(Constant.TITLE, MainActivity.data.get(i).getTitle());
               arrayList.add(map);
       }

       int[] ids = {R.id.textTitle};
       String[] from = {Constant.TITLE};
       if (arrayList.isEmpty()) {
           map = new HashMap<>();
           map.put(Constant.TITLE, "здесь должен быть заголовок");
           arrayList.add(map);
       }
           return new PlanAdapter(getActivity(),R.layout.fragment_plan_element);
   }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.v(this.getClass().getSimpleName(),"onRowItemClick called");
    }
}
