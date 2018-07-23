package com.example.isilu.dailyplanner.activities;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.example.isilu.dailyplanner.R;
import com.example.isilu.dailyplanner.fragments.PlanElement;
import com.example.isilu.dailyplanner.staticData.Constant;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    public static MainActivity MAIN_ACTIVITY;
    /**
     * Файл, в котором хранятся данные о планах
     * Формат - "заголовок" "определение"
     */
    private static final String FILE ="plans.txt";

    /**
     * Во время исполнения программы все планы хранятся здесь.
     */
    public static final List<PlanElement> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       setActionBar(findViewById(R.id.toolbar));
       getActionBar().setDisplayShowTitleEnabled(false);
        MAIN_ACTIVITY = this;
        fillData();
    }

    /**
     * Заполняет поле data данными из файла FILE
     */
    public void fillData(){
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(FILE)))) {
            String line;
            while ((line = bufferedReader.readLine())!=null){
                String[] lines = line.split(" ");
                boolean isEmpty = false;

                for (String l:lines)
                    if (l.equals("null"))
                        isEmpty = true;

                if (!isEmpty && (lines.length > 0))
                    data.add(
                            PlanElement.newInstance(lines[0]==null?"":lines[0],
                            lines[lines.length-1]==null?"":lines[lines.length-1])
                    );

            }
        } catch (IOException e) {
            Log.v(this.getClass().getSimpleName(),"Error:"+e.toString());
        }
        Log.v(this.getClass().getSimpleName(),"file has been red");
    }

    /**
     * Записывает данные из поля data в файл FILE
     */
    public void writeData(){
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(openFileOutput(FILE,MODE_PRIVATE)))){
            if (!data.isEmpty()) {
                for (PlanElement planElement:data){
                    bufferedWriter.write(planElement.toString());
                    bufferedWriter.newLine();
                }
            }
        }catch (IOException e){Log.v(this.getClass().getSimpleName(),"Error:"+e.toString());}
        Log.v(this.getClass().getSimpleName(),"all data has been wrote");
    }

    @Override
    protected void onPause() {
        super.onPause();
        writeData();
    }

    /**
     * вызывается при создании списка меню
     * @param menu - объект, который содержит список меню
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(getString(R.string.addNewPlan));
        Log.v(this.getClass().getSimpleName(),"menu New Plan element was added");
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * вызывается при нажатии элемента из меню
     */
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        if (item.getTitle().toString().equals(getString(R.string.addNewPlan))){
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),NewPlanActivity.class);

            Log.v(this.getClass().getSimpleName(),"starting NewPlanActivity");

            startActivityForResult(intent,Constant.REQUEST_CODE);
        }
        return super.onMenuItemSelected(featureId, item);
    }

    /**
     * Вызывается, когда NewPlanActivity завершает работу по сбору данных о новом плане
     * @param requestCode - номер кода запроса нового плана
     * @param resultCode - код резульата получения данных о запросе
     * @param data - намерение, содержащее данные о плане
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if ((requestCode == Constant.REQUEST_CODE) && (resultCode == RESULT_OK)){
            Log.v(this.getClass().getSimpleName(),"NewPlanActivity executed successfully");
            addData(data);
            refresh();
        }
    }

    /**
     * добавляет новосозданный объект плана по данным из намерения в общий список планов
     * @param data - намерение, содержащее исходные данные о плане
     */
    private void addData(Intent data){
        PlanElement element = getElement(data);
        MainActivity.data.add(element);
        Log.v(this.getClass().getSimpleName(),"new plan was added successfully:"+MainActivity.data.get(MainActivity.data.size()-1));
    }

    /**
     * возвращает новосозданный объект плана по данным из намерения.
     * @param data - намерение, содержащее исходные данные о плане
     * @return объект плана
     */
    private PlanElement getElement(Intent data){
        assert data != null;

        Bundle args = data.getBundleExtra(Constant.ARGUMENTS);
        String title = args.getString(Constant.TITLE);
        String desc = args.getString(Constant.DESCRIPTION);

        return PlanElement.newInstance(title,desc);
    }

    /**
     * обновляет представление списка планов
     */
    public void refresh(){
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.list_fragment);
        assert fragment != null;
        fragment.onActivityCreated(null);
    }
}
