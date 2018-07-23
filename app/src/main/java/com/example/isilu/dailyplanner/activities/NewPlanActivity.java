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

public class NewPlanActivity extends Activity {
    /**
     *поле для ввода заголовка нового плана
     */
    EditText titleEditText;
    /**
     * полсе для ввода подробностей о новом плане
     */
    EditText descEditText;

    /**
     * кнопка для создания нового плана
     */
    Button confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_plan);
        setActionBar(findViewById(R.id.toolbar));
        getActionBar().setDisplayShowTitleEnabled(false);
        initializeFields();

        confirm.setOnClickListener(i->{

            String title = titleEditText.getText().toString();
            String desc = descEditText.getText().toString();

            if (TextUtils.isEmpty(title)||TextUtils.isEmpty(desc)){
                Toast.makeText(getApplicationContext(),getString(R.string.enterData),Toast.LENGTH_LONG).show();
            }else{
                finishActivity(title,desc);
            }
        });
    }

    /**
     * инициализирует поля класса
     */
    private void initializeFields(){
        titleEditText = findViewById(R.id.newTitle);
        descEditText = findViewById(R.id.newDesc);
        confirm = findViewById(R.id.confirmNewPlan);
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
