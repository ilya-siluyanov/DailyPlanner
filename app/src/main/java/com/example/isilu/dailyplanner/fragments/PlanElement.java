package com.example.isilu.dailyplanner.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.isilu.dailyplanner.R;
import com.example.isilu.dailyplanner.activities.MainActivity;
import com.example.isilu.dailyplanner.staticData.Constant;

import org.jetbrains.annotations.NotNull;

public class PlanElement extends Fragment {
    private String title;
    private String description;
    public static PlanElement newInstance(String title,String description){
        PlanElement element = new PlanElement();
        Bundle args = new Bundle();
        args.putString(Constant.TITLE,title);
        args.putString(Constant.DESCRIPTION,description);
        element.setTitle(title);
        element.setDescription(description);
        element.setArguments(args);
        return element;
    }

    public PlanElement() {
        Log.v(PlanElement.this.getClass().getSimpleName(),"creating new PlanElement");
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.v(this.getClass().getSimpleName(),"onCreateView method called");
        View view = inflater.inflate(R.layout.fragment_plan_element, container, false);
        TextView textView = view.findViewById(R.id.textTitle);
        assert getArguments() != null;
        if (getArguments().getString(Constant.TITLE)!=null)
            textView.setText(getArguments().getString(Constant.TITLE));
        Log.v(this.getClass().getSimpleName(),
                "new PlanElement was created:title = "+getArguments().getString(Constant.TITLE));
        return view;
    }

    @Override
    public String toString() {
        assert getArguments() != null;
        return getArguments().getString(Constant.TITLE)+" "+getArguments().getString(Constant.DESCRIPTION);
    }
}
