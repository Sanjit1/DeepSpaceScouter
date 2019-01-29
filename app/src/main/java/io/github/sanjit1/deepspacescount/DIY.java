package io.github.sanjit1.deepspacescount;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class DIY extends AppCompatActivity {
    CardView text;
    CardView spinner;
    CardView numeric;

    String scoutLead ="notYet";
    Spinner textConfiguration;
    int textNumber;
    int spinnerNumber;
    int numericNumber;

    Workbook wb;


    ArrayList<String> paramType = new ArrayList<>();
    ArrayList<String> paramName = new ArrayList<>();
    ArrayList<String> textConfig = new ArrayList<>();
    ArrayList<String> minConfig = new ArrayList<>();
    ArrayList<String> maxConfig = new ArrayList<>();
    ArrayList<String> spinnerOptionCreater = new ArrayList<>();
    ArrayList<Integer> spinnerConfig = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        File scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData");
        createDir(scout);
        scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData");
        createDir(scout);

        wb = new HSSFWorkbook();
        wb.createSheet("👾");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy);
        Spinner paramChoice = findViewById(R.id.parameter);

        text = findViewById(R.id.textConfig);
        spinner = findViewById(R.id.spinnerConfig);
        numeric = findViewById(R.id.numberConfig);
        paramChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //stuff here to handle item selection
                if (Objects.equals(parent.getItemAtPosition(position).toString(), "Text")) {
                    text.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.GONE);
                    numeric.setVisibility(View.GONE);
                } else if (Objects.equals(parent.getItemAtPosition(position).toString(), "Spinner")) {
                    spinner.setVisibility(View.VISIBLE);
                    numeric.setVisibility(View.GONE);
                    text.setVisibility(View.GONE);
                } else if (Objects.equals(parent.getItemAtPosition(position).toString(), "Quantitative")) {
                    numeric.setVisibility(View.VISIBLE);
                    text.setVisibility(View.GONE);
                    spinner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void  addSpinner(View v){
        spinnerOptionCreater.add(((EditText) findViewById(R.id.buttonName)).getText().toString());

    }

    public void createDir(File scout){
        if (!scout.exists() && !scout.isDirectory()) {
            // create empty directory
            if (scout.mkdirs()) {
                Log.i("CreateDir", "App dir created");
            } else {

            }
        } else {
            Log.i("CreateDir", "App dir already exists");
        }
    }


    public void add(View v) {
        if (text.getVisibility() == View.VISIBLE) {
            textConfiguration = findViewById(R.id.text_config);
            textNumber++;
            paramType.add("T");
            textConfig.add(textConfiguration.getSelectedItem().toString());
            paramName.add(((EditText) findViewById(R.id.parameterName)).getText().toString());
        } else if (spinner.getVisibility() == View.VISIBLE) {
            spinnerNumber++;
            paramType.add("S");
            spinnerConfig.add(spinnerOptionCreater.size());
            wb.getSheetAt(0).createRow(spinnerNumber-1);
            for (int i = 0; i < spinnerOptionCreater.size(); i++) {
            wb.getSheetAt(0).getRow(spinnerNumber-1).createCell(i).setCellValue(spinnerOptionCreater.get(i));
            }
            spinnerOptionCreater.removeAll(spinnerOptionCreater);
            paramName.add(((EditText) findViewById(R.id.parameterName)).getText().toString());
        } else if (numeric.getVisibility() == View.VISIBLE) {
            numericNumber++;
            paramType.add("N");
            minConfig.add((((EditText) findViewById(R.id.minVal)).getText() !=null) ? ((EditText) findViewById(R.id.minVal)).getText().toString() : "0");
            maxConfig.add((((EditText) findViewById(R.id.maxVal)).getText() !=null) ? ((EditText) findViewById(R.id.maxVal)).getText().toString() : "255");
            paramName.add(((EditText) findViewById(R.id.parameterName)).getText().toString());
        }


    }



    public void saveAndLoad(View v){
        if(v == findViewById(R.id.save)){

            File activity = new File ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData/activity.🚀🤖");
            try {
                FileWriter writer = new FileWriter(activity);
                writer.append(((EditText) findViewById(R.id.activityName)).getText().toString());
                writer.append(System.lineSeparator());
                int t = 0;
                int s = 0;
                int n = 0;
                for (int i = 0; i < paramType.size(); i++) {
                    if (Objects.equals(paramType.get(i), "T")) {
                        writer.append("T");
                        writer.append(System.lineSeparator());
                        writer.append(paramName.get(i));
                        writer.append(System.lineSeparator());
                        writer.append(textConfig.get(t));
                        writer.append(System.lineSeparator());
                        t++;
                    } else if (Objects.equals(paramType.get(i), "S")) {
                        writer.append("S");
                        writer.append(System.lineSeparator());
                        writer.append(paramName.get(i));
                        writer.append(System.lineSeparator());
                        for (int ii = 0; ii<spinnerConfig.get(s); ii++) {
                            writer.append(wb.getSheetAt(0).getRow(s).getCell(ii).toString());
                            writer.append(System.lineSeparator());
                        }
                        writer.append("endSpinner🚀🤖🚀🤖");
                        writer.append(System.lineSeparator());
                        s++;
                    } else if (Objects.equals(paramType.get(i), "N")) {
                        writer.append("N");
                        writer.append(System.lineSeparator());
                        writer.append(paramName.get(i));
                        writer.append(System.lineSeparator());
                        writer.append(minConfig.get(n));
                        writer.append(System.lineSeparator());
                        writer.append(maxConfig.get(n));
                        writer.append(System.lineSeparator());
                        t++;
                    }
                }
                writer.append("endAct🚀🤖");
                writer.flush();
                writer.close();
            }catch (IOException e){


            }
        } else {
            Intent myIntent = new Intent(DIY.this,
                    Custom.class);
            startActivity(myIntent);
        }

    }
}