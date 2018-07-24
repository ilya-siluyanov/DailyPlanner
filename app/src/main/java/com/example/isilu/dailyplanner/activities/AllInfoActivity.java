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

public class AllInfoActivity extends Activity {
    EditText title;
    EditText desc;
    Button confirm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_all_info);
        setActionBar(findViewById(R.id.toolbar));
        getActionBar().setDisplayShowTitleEnabled(false);

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
        desc= findViewById(R.id.plan_desc);
        confirm = findViewById(R.id.confirmButton);
    }

    /**
     * объединяет все данные о плане и возвращает их в MainActivity
     * @param title - заголовок плана
     * @param desc - подробности плана
     */
    private void finishActivity(String title,String desc){
        Bundle args = new Bundle();
        args.putString(Constant.TITLE,title);
        args.putString(Constant.DESCRIPTION,desc);
        setResult(RESULT_OK,new Intent().putExtra(Constant.ARGUMENTS,args));
        finish();
    }


}
