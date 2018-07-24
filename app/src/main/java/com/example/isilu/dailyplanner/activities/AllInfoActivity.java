package com.example.isilu.dailyplanner.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.isilu.dailyplanner.R;
import com.example.isilu.dailyplanner.staticData.Constant;

import java.util.Objects;

public class AllInfoActivity extends Activity {
    EditText title;
    EditText desc;
    Button confirm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_all_info);
        setActionBar(findViewById(R.id.toolbar));
        if (getActionBar()!=null){
            getActionBar().setDisplayShowTitleEnabled(false);
        }


        initializeFields();

        confirm.setOnClickListener(i->{
            finishActivity(title.getText().toString(),desc.getText().toString());
        });

    }

    /**
     * инициализирует поля класса
     */
    private void initializeFields(){
        title= findViewById(R.id.plan_title);
        title.setText(getIntent().getStringExtra(Constant.TITLE));
        desc= findViewById(R.id.plan_desc);
        desc.setText(getIntent().getStringExtra(Constant.DESCRIPTION));
        confirm = findViewById(R.id.confirmButton);
    }

    /**
     * объединяет все данные о плане и возвращает их в MainActivity
     * @param title - заголовок плана
     * @param desc - подробности плана
     */
    private void finishActivity(String title,String desc){
        Intent args = new Intent();
        args.putExtra(Constant.TITLE,title);
        args.putExtra(Constant.DESCRIPTION,desc);
        args.putExtra(Constant.POSITION_ARG,getIntent().getIntExtra(Constant.POSITION_ARG,0));
        setResult(RESULT_OK,args);
        finish();
    }


}
