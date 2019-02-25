package io.github.sanjit1.deepspacescout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Custom extends AppCompatActivity {
    String activityName;
    int textNumber;
    int spinnerNumber;
    int numericNumber;
    String scoutLead ="notYet";
    Workbook wb;
    Workbook export;
    ArrayList<String> paramType = new ArrayList<>();
    ArrayList<String> paramName = new ArrayList<>();
    ArrayList<String> textConfig = new ArrayList<>();
    ArrayList<String> minConfig = new ArrayList<>();
    ArrayList<String> maxConfig = new ArrayList<>();
    ArrayList<Integer> spinnerConfig = new ArrayList<>();
    ArrayList<Button> increment = new ArrayList<>();
    ArrayList<TextView> valueNumeric = new ArrayList<>();
    ArrayList<Button> decrement = new ArrayList<>();
    ArrayList<Integer> numericValue = new ArrayList<>();


    ArrayList<CardView> cardTextList = new ArrayList<>();
    ArrayList<EditText> textInCard = new ArrayList<>();

    ArrayList<CardView> cardSpinnerList = new ArrayList<>();
    ArrayList<Spinner> spinnerInCard = new ArrayList<>();


    ArrayList<CardView> cardNumericList = new ArrayList<>();

    Button exportButton;
    Button mail;
    LinearLayout parentActivity;

    CardView tempCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        // If the string is not yet, we will ask the app to enter the mail of any scout lead. Then we will use the mail address to send a mail

        File email = new File ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/email.😘");
        if (!email.exists()) {
            try{
                FileWriter writer = new FileWriter(email);
                writer.append("scoutLead");
                writer.flush();
                writer.close();

            }catch(IOException e){
            }
        } else {

            try{
                BufferedReader br = new BufferedReader(new FileReader(email));
                scoutLead = br.readLine();
            }catch(IOException e){
            }
        }


        if (Objects.equals(scoutLead , "notYet")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);


            alert.setTitle(getString(R.string.title));
            alert.setMessage(getString(R.string.message));

// Set an EditText view to get user input
            final EditText input = new EditText(this);
            alert.setView(input);

            alert.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    scoutLead = String.valueOf(input.getText());
                    try{
                        FileWriter writer = new FileWriter(email);
                        writer.append(scoutLead);
                        writer.flush();
                        writer.close();

                    }catch(IOException e){
                    }
                }
            });

            alert.setNegativeButton(getString(R.string.gokul), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    scoutLead= getString(R.string.gokul_mail);
                    try{
                        FileWriter writer = new FileWriter(email);
                        writer.append(scoutLead);
                        writer.flush();
                        writer.close();

                    }catch(IOException e){
                    }
                }
            });

            alert.show();

        }

        ArrayList<String> spinnerOptionCreater = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        wb = new HSSFWorkbook();
        wb.createSheet("👾");
        try {
            FileReader activity = new FileReader ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString() + "/ScouterAppData/ActivityData/activity.🚀🤖");
            BufferedReader br = new BufferedReader(activity);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            String fileAsString = sb.toString();
            String[] arrOfStr = fileAsString.split(System.lineSeparator(), 0);
            activityName = arrOfStr[0];
            int lineNumb = 1;
            while (!Objects.equals(arrOfStr[lineNumb],"endAct🚀🤖")){
                if (Objects.equals(arrOfStr[lineNumb],"T")){
                    lineNumb++;
                    paramName.add(arrOfStr[lineNumb]);
                    paramType.add("T");
                    lineNumb++;
                    textConfig.add(arrOfStr[lineNumb]);
                    textNumber++;
                } else if (Objects.equals(arrOfStr[lineNumb],"S")){
                    spinnerNumber++;
                    lineNumb++;
                    paramName.add(arrOfStr[lineNumb]);
                    lineNumb++;
                    paramType.add("S");
                    while (!Objects.equals(arrOfStr[lineNumb],"endSpinner🚀🤖🚀🤖")) {
                        spinnerOptionCreater.add(arrOfStr[lineNumb]);
                        lineNumb++;
                    }
                    spinnerConfig.add(spinnerOptionCreater.size());
                    wb.getSheetAt(0).createRow(spinnerNumber-1);
                    for (int i = 0; i < spinnerOptionCreater.size(); i++) {
                        wb.getSheetAt(0).getRow(spinnerNumber-1).createCell(i).setCellValue(spinnerOptionCreater.get(i));
                    }

                    spinnerOptionCreater.removeAll(spinnerOptionCreater);
                } else if (Objects.equals(arrOfStr[lineNumb],"N")){
                    lineNumb++;
                    paramName.add(arrOfStr[lineNumb]);
                    paramType.add("N");
                    lineNumb++;
                    minConfig.add(arrOfStr[lineNumb]);
                    lineNumb++;
                    maxConfig.add(arrOfStr[lineNumb]);
                    numericNumber++;

                }
                lineNumb++;
            }
        } catch (IOException e) {

        }
        configureActivity();
    }


    public void  configureActivity(){
        int numberText=0;
        int numberSpinner=0;
        int numberNumeric=0;
        parentActivity=findViewById(R.id.parentView);
        tempCard = findViewById(R.id.templateCard);


        TextView actName = findViewById(R.id.activityName);
        actName.setText(activityName);
        for(int i = 0; i<paramType.size();i++){
            if(Objects.equals(paramType.get(i),"T")){
                cardTextList.add(new CardView(this));
                cardTextList.get(numberText).setUseCompatPadding(true);
                cardTextList.get(numberText).setLayoutParams(tempCard.getLayoutParams());
                cardTextList.get(numberText).setCardElevation(5);
                textInCard.add(new EditText(this));
                textInCard.get(numberText).setHint(paramName.get(i));
                if(Objects.equals(textConfig.get(numberText),"Text")){
                    textInCard.get(numberText).setInputType(InputType.TYPE_CLASS_TEXT);
                }else{
                    textInCard.get(numberText).setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                cardTextList.get(numberText).addView(textInCard.get(numberText));
                parentActivity=findViewById(R.id.parentView);
                parentActivity.addView(cardTextList.get(numberText));
                numberText++;
            } else if(Objects.equals(paramType.get(i),"S")){
                cardSpinnerList.add(new CardView(this));
                cardSpinnerList.get(numberSpinner).setUseCompatPadding(true);
                cardSpinnerList.get(numberSpinner).setLayoutParams(tempCard.getLayoutParams());
                cardSpinnerList.get(numberSpinner).setCardElevation(5);
                spinnerInCard.add(new Spinner(this));
                ArrayList<String> spinStuff = new ArrayList<>();
                for (int b=0; b <spinnerConfig.get(numberSpinner); b++){
                   spinStuff.add(wb.getSheetAt(0).getRow(numberSpinner).getCell(b).toString());
                }
                spinnerInCard.get(numberSpinner).setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,spinStuff));
                TextView title = new TextView(this);
                title.setTextSize(30);
                title.setText(paramName.get(i));
                LinearLayout cardholder = new LinearLayout(this);
                cardholder.setLayoutParams(tempCard.getLayoutParams());
                cardholder.setOrientation(LinearLayout.VERTICAL);
                cardholder.addView(title);
                cardholder.addView(spinnerInCard.get(numberSpinner));
                cardSpinnerList.get(numberSpinner).addView(cardholder);
                parentActivity=findViewById(R.id.parentView);
                parentActivity.addView(cardSpinnerList.get(numberSpinner));
                numberSpinner++;
            } else if(Objects.equals(paramType.get(i),"N")){
                numericValue.add(Integer.parseInt(minConfig.get(numericNumber-1)));
                LinearLayout cardholder = new LinearLayout(this);
                cardholder.setLayoutParams(tempCard.getLayoutParams());
                TextView nameParam = new TextView(this);
                nameParam.setTextSize(24);
                nameParam.setText(paramName.get(i));
                increment.add(new Button(this));
                increment.get(numberNumeric).setText("+");
                increment.get(numberNumeric).setLayoutParams(findViewById(R.id.referenceButton).getLayoutParams());
                increment.get(numberNumeric).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (v == exportButton){
                            export();
                        }else if(v == mail){
                            mailFile();
                        } else {
                            for (int arrayNumb = 0; arrayNumb<numericNumber; arrayNumb++){
                                if (v == increment.get(arrayNumb)){
                                    if (!(numericValue.get(arrayNumb)+1>Integer.parseInt(maxConfig.get(arrayNumb)))){
                                        numericValue.set(arrayNumb,numericValue.get(arrayNumb)+1) ;
                                        valueNumeric.get(arrayNumb).setText(String.valueOf(numericValue.get(arrayNumb)));
                                    }
                                } else {
                                    if (!(numericValue.get(arrayNumb)-1<Integer.parseInt(minConfig.get(arrayNumb)))){
                                        numericValue.set(arrayNumb,numericValue.get(arrayNumb)-1) ;
                                        valueNumeric.get(arrayNumb).setText(String.valueOf(numericValue.get(arrayNumb)));
                                    }
                                }
                            }
                        }

                    }
                });
                valueNumeric.add(new TextView(this));
                numericValue.set(numberNumeric,Integer.parseInt(minConfig.get(numberNumeric)));
                valueNumeric.get(numberNumeric).setText(String.valueOf(minConfig.get(numberNumeric)));
                valueNumeric.get(numberNumeric).setTextSize(24);
                decrement.add((new Button(this)));
                decrement.get(numberNumeric).setText("-");
                decrement.get(numberNumeric).setLayoutParams(findViewById(R.id.referenceButton).getLayoutParams());
                decrement.get(numberNumeric).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (v == exportButton){
                            export();
                        }else if(v == mail){
                            mailFile();
                        } else {
                            for (int arrayNumb = 0; arrayNumb<numericNumber; arrayNumb++){
                                if (v == increment.get(arrayNumb)){
                                    if (!(numericValue.get(arrayNumb)+1>Integer.parseInt(maxConfig.get(arrayNumb)))){
                                        numericValue.set(arrayNumb,numericValue.get(arrayNumb)+1) ;
                                        valueNumeric.get(arrayNumb).setText(String.valueOf(numericValue.get(arrayNumb)));
                                    }
                                } else {
                                    if (!(numericValue.get(arrayNumb)-1<Integer.parseInt(minConfig.get(arrayNumb)))){
                                        numericValue.set(arrayNumb,numericValue.get(arrayNumb)-1) ;
                                        valueNumeric.get(arrayNumb).setText(String.valueOf(numericValue.get(arrayNumb)));
                                    }
                                }
                            }
                        }

                    }
                });
                cardholder.addView(nameParam);
                cardholder.addView(increment.get(numberNumeric));
                cardholder.addView(valueNumeric.get(numberNumeric));
                cardholder.addView(decrement.get(numberNumeric));
                cardNumericList.add(new CardView(this));
                cardNumericList.get(numberNumeric).setUseCompatPadding(true);
                cardNumericList.get(numberNumeric).setLayoutParams(tempCard.getLayoutParams());
                cardNumericList.get(numberNumeric).setCardElevation(5);
                cardNumericList.get(numberNumeric).addView(cardholder);
                parentActivity=findViewById(R.id.parentView);
                parentActivity.addView(cardNumericList.get(numberNumeric));
                numberNumeric++;
            }
        }
        exportButton = new Button(this);
        mail = new Button(this);

        exportButton.setText(getString(R.string.export));
        mail.setText(getString(R.string.mail));
        exportButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == exportButton){
                    export();
                }else if(v == mail){
                    mailFile();
                } else {
                    for (int arrayNumb = 0; arrayNumb<numericNumber; arrayNumb++){
                        if (v == increment.get(arrayNumb)){
                            if (!(numericValue.get(arrayNumb)+1>Integer.parseInt(maxConfig.get(arrayNumb)))){
                                numericValue.set(arrayNumb,numericValue.get(arrayNumb)+1) ;
                                valueNumeric.get(arrayNumb).setText(String.valueOf(numericValue.get(arrayNumb)));
                            }
                        } else {
                            if (!(numericValue.get(arrayNumb)-1<Integer.parseInt(minConfig.get(arrayNumb)))){
                                numericValue.set(arrayNumb,numericValue.get(arrayNumb)-1) ;
                                valueNumeric.get(arrayNumb).setText(String.valueOf(numericValue.get(arrayNumb)));
                            }
                        }
                    }
                }

            }
        });
        mail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v == exportButton){
                    export();
                }else if(v == mail){
                    mailFile();
                } else {
                    for (int arrayNumb = 0; arrayNumb<numericNumber; arrayNumb++){
                        if (v == increment.get(arrayNumb)){
                            if (!(numericValue.get(arrayNumb)+1>Integer.parseInt(maxConfig.get(arrayNumb)))){
                                numericValue.set(arrayNumb,numericValue.get(arrayNumb)+1) ;
                                valueNumeric.get(arrayNumb).setText(String.valueOf(numericValue.get(arrayNumb)));
                            }
                        } else {
                            if (!(numericValue.get(arrayNumb)-1<Integer.parseInt(minConfig.get(arrayNumb)))){
                                numericValue.set(arrayNumb,numericValue.get(arrayNumb)-1) ;
                                valueNumeric.get(arrayNumb).setText(String.valueOf(numericValue.get(arrayNumb)));
                            }
                        }
                    }
                }

            }
        });
        CardView buttons;
        buttons = new CardView(this);
        buttons.setUseCompatPadding(true);
        buttons.setLayoutParams(tempCard.getLayoutParams());
        buttons.setCardElevation(5);
        LinearLayout cardChild = new LinearLayout(this);

        cardChild.addView(exportButton);
        cardChild.addView(mail);
        buttons.addView(cardChild);
        parentActivity.addView(buttons);
    }



    private void export() {
        File xls = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).getAbsoluteFile(), ("/" + activityName + ".xls"));
        int numberText = 0;
        int numberSpinner = 0;
        int numberNumeric = 0;
        int rowNumb = 0;
        if (!xls.exists()) {
            export = new HSSFWorkbook();
            export.createSheet("🤖").createRow(0);
            for (int i = 0; i < paramName.size(); i++) {
                export.getSheetAt(0).getRow(0).createCell(i).setCellValue(paramName.get(i));
            }
            for (int i = 1; i <6000; i++){
                export.getSheetAt(0).createRow(i).createCell(0).setCellValue(" ");
            }
        }

                while (export.getSheetAt(0).getRow(rowNumb).getCell(0).toString() != " ") {
                    rowNumb++;
                }

            boolean textEmpty = false;
            for (int texCheck = 0; texCheck < textConfig.size(); texCheck++){
                if (TextUtils.isEmpty(textInCard.get(texCheck).getText().toString())) {
                    textEmpty = true;
                    Toast.makeText(getApplicationContext(), "You did not answer one of the text inputs", Toast.LENGTH_LONG).show();
                }}


                if(textEmpty){} else {

                    for (int i = 0; i < paramType.size(); i++) {
                        if (Objects.equals(paramType.get(i), "T")) {
                            export.getSheetAt(0).getRow(rowNumb).createCell(i).setCellValue(textInCard.get(numberText).getText().toString());
                            numberText++;
                        } else if (Objects.equals(paramType.get(i), "S")) {
                            export.getSheetAt(0).getRow(rowNumb).createCell(i).setCellValue(spinnerInCard.get(numberSpinner).getSelectedItem().toString());
                            numberSpinner++;
                        } else if (Objects.equals(paramType.get(i), "N")) {
                            export.getSheetAt(0).getRow(rowNumb).createCell(i).setCellValue(numericValue.get(numberNumeric));
                            numberNumeric++;
                        }
                    }
                }

                try {
                    FileWriter writer = new FileWriter((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/" + activityName + ".xls"));
                    writer.flush();
                    writer.close();
                    FileOutputStream out = new FileOutputStream((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + ("/" + activityName + ".xls"));
                    export.write(out);
                    // now we have to save the file to data.xls phew more work.
                    out.close();
                    // now that we have successfully finished the conversion, we toast a message to the user telling them about our success
                    Toast.makeText(getApplicationContext(), "Exported to "+activityName+".xls", Toast.LENGTH_SHORT).show();
                }
                catch (IOException e){

                }
            }


            public void mailFile (){
                // now the user wants to send the data over mail
                // first we find the file
                String filePath = new File(((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).getAbsoluteFile()), ("/" + activityName + ".xls")).toString();
                File f = new File(filePath);
                // then we make a new intent to share the data
                Intent intentShareFile = new Intent(Intent.ACTION_SEND);
                File fileWithinMyDir = new File(filePath);
                // we do not want to crash the app, so if the file exists, then only we will send the intent
                if (fileWithinMyDir.exists()) {
                    intentShareFile.setType("application/xls");
                    intentShareFile.putExtra(Intent.EXTRA_STREAM, "file://" + filePath);
                    intentShareFile.putExtra(Intent.EXTRA_SUBJECT, "Scout Data " + f.getName());
                    intentShareFile.putExtra(Intent.EXTRA_EMAIL, new String[]{scoutLead});
                    intentShareFile.putExtra(Intent.EXTRA_TEXT, "Using DeepSpaceScouter " + f.getName());
                    // now we send the intent
                    this.startActivity(Intent.createChooser(intentShareFile, f.getName()));
                }

            }



}



