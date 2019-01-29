package io.github.sanjit1.deepspacescount;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;


import java.util.Objects;

public class TeamName extends Activity {
    //Initialise the strings to contain the values of the radio buttons for climb and start game
    String climb;
    String control;

    // initialise the scout leaders email address to netYet, because we need to remind the app to ask for the email to send to.
    //
    String scoutLead = "notYet";
    //Initialise the views for the quantitative stuff for Hatch panels and cargo installed before and after the game
    TextView hatchPreGame;
    TextView cargoPreGame;
    TextView hatch;
    TextView cargo;

    //Initialise the variables that count the number stuff
    int HatchPreGame = 0;
    int CargoPreGame = 0;
    int Hatch = 0;
    int Cargo = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // request permission, for internet access, read and write storage if it has not been already given
        if (ContextCompat.checkSelfPermission(TeamName.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE
            }, 1);

        if (ContextCompat.checkSelfPermission(TeamName.this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_NETWORK_STATE
            }, 1);

        if (ContextCompat.checkSelfPermission(TeamName.this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.INTERNET
            }, 1);

        if (ContextCompat.checkSelfPermission(TeamName.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 1);


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
        // in case the user does not answer, we are covered
        climb = getString(R.string.no_answer);
        control = getString(R.string.no_answer);


        super.onCreate(savedInstanceState);
        //initialise the activity  and ⬇
        setContentView(R.layout.activity_team_name);
        //set value to variable of views 👆 👇
        hatchPreGame = findViewById(R.id.preHatch);
        cargoPreGame = findViewById(R.id.preCargo);
        hatch = findViewById(R.id.hatch);
        cargo = findViewById(R.id.cargo);

    }

    public void onClick(View button) {
        //updating the values and text for the buttons
        switch (button.getId()) {
            // Buttons for increasing the value of the number stuff
            case R.id.incPreHatch:
                HatchPreGame++;
                hatchPreGame.setText(String.valueOf(HatchPreGame));
                break;

            case R.id.incPreCargo:
                CargoPreGame++;
                cargoPreGame.setText(String.valueOf(CargoPreGame));
                break;

            case R.id.incHatch:
                Hatch++;
                hatch.setText(String.valueOf(Hatch));
                break;

            case R.id.incCargo:
                Cargo++;
                cargo.setText(String.valueOf(Cargo));
                break;

            //decrease value of the number stuff after making sure that it is more than 0
            case R.id.decPreHatch:
                if (HatchPreGame > 0) HatchPreGame--;
                hatchPreGame.setText(String.valueOf(HatchPreGame));
                break;

            case R.id.decPreCargo:
                if (CargoPreGame > 0) CargoPreGame--;
                cargoPreGame.setText(String.valueOf(CargoPreGame));
                break;

            case R.id.decHatch:
                if (Hatch > 0) Hatch--;
                hatch.setText(String.valueOf(Hatch));
                break;

            case R.id.decCargo:
                if (Cargo > 0) Cargo--;
                cargo.setText(String.valueOf(Cargo));
                break;


        }
    }


    public void Climb(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked for climb
        switch (view.getId()) {
            case R.id.Level1:
                if (checked)
                    climb = getString(R.string.level_1);
                break;
            case R.id.Level2:
                if (checked)
                    climb = getString(R.string.level_2);
                break;
            case R.id.Level3:
                if (checked)
                    climb = getString(R.string.level_3);
                break;

        }


    }

    public void Control(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked for control
        switch (view.getId()) {
            case R.id.auto:
                if (checked)
                    control = getString(R.string.auto);
                break;
            case R.id.camera:
                if (checked)
                    control = getString(R.string.blind);
                break;
            case R.id.blind:
                if (checked)
                    control = getString(R.string.camera);
                break;

        }


    }


    public void export(View view) throws IOException {
        //convert the edit text boxes to text
        String team = ((EditText) findViewById(R.id.teamNumber)).getText().toString();
        String hatch = ((EditText) findViewById(R.id.hatchMechanism)).getText().toString();
        String cargo = ((EditText) findViewById(R.id.cargoMechanism)).getText().toString();
        String lift = ((EditText) findViewById(R.id.liftMechanism)).getText().toString();
        String notes = ((EditText) findViewById(R.id.notes)).getText().toString();

        //make sure that the edit text boxes are not empty. if they are empty then there will be an reminder toast
        if (TextUtils.isEmpty(team)) {
            Toast.makeText(getApplicationContext(), getString(R.string.no_team_name), Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(hatch)) {
            Toast.makeText(getApplicationContext(), getString(R.string.no_hatch_mechanism), Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(cargo)) {
            Toast.makeText(getApplicationContext(), getString(R.string.no_cargo_mechanism), Toast.LENGTH_LONG).show();
        } else if (TextUtils.isEmpty(lift)) {
            Toast.makeText(getApplicationContext(), getString(R.string.no_lift_mechanism), Toast.LENGTH_LONG).show();
        } else {

            if (TextUtils.isEmpty(notes)) {
                notes = getString(R.string.no_notes);
            }
            //if none of the texts are empty, then we shall continue
            //check if there is already an excel file, or if it has been deleted or moved or not downloaded
            File xls = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)), "/data.xls");

            if (!xls.exists()) {
                // since it does not, we will download it from my website
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(getString(R.string.url)));
                //now we will design the notification to tell the user that a file is being downloaded
                request.setDescription(getString(R.string.download_notification));
                request.setTitle("data.xls");
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                //now we are telling the download manager where to download the file to
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "data.xls");
                DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);
                // we are telling the user not to click export again before the download is complete.
                Toast.makeText(getApplicationContext(), getString(R.string.download_toast), Toast.LENGTH_LONG).show();
            } else {
                //file exits
                //we will take the file for modification
                InputStream ExcelFileToRead = new FileInputStream((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/data.xls");
                //initialising a workbook for data entry
                HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
                // reading the cell at IV 255 or something like that, which is a dot.
                // we will compare the dot to the cells in the first row in order.
                // If it is equal to the dot, then we know that we can input new data over there
                String o = wb.getSheetAt(0).getRow(200).getCell(255).toString();
                // the column number where we will store data
                int column = 1;
                // this is the match number, the number of the match that we have recorded so if 2658 already an entry, the value will change to 2 and so on.
                int matchNumber = 1;

                while (!Objects.equals(wb.getSheetAt(0).getRow(0).getCell(column).toString(), o)) { // that statement checks if the cells mentioned above  at line 223 are equal
                    // if they are not then it will increase the column number and check again
                    if (Integer.parseInt(team) == Integer.parseInt(wb.getSheetAt(0).getRow(0).getCell(column).toString())) {
                        // meanwhile the above statement checks if there is already an entry for the team number, by comparing the strings if there is, then it updates match number else it continues
                        matchNumber++;
                    }

                    column++;

                }
                // this line toasts the fact that it is making a table.
                Toast.makeText(getApplicationContext(), "Creating table", Toast.LENGTH_SHORT).show();
                // after the toast, we create the table. we update the values of the workbook to a new cell in the column given to us in the rows, which the values should go in
                wb.getSheetAt(0).getRow(0).createCell(column).setCellValue(team);
                wb.getSheetAt(0).getRow(1).createCell(column).setCellValue(control);
                wb.getSheetAt(0).getRow(2).createCell(column).setCellValue(hatch);
                wb.getSheetAt(0).getRow(3).createCell(column).setCellValue(cargo);
                wb.getSheetAt(0).getRow(4).createCell(column).setCellValue(lift);
                wb.getSheetAt(0).getRow(6).createCell(column).setCellValue(HatchPreGame);
                wb.getSheetAt(0).getRow(7).createCell(column).setCellValue(CargoPreGame);
                wb.getSheetAt(0).getRow(8).createCell(column).setCellValue(Hatch);
                wb.getSheetAt(0).getRow(9).createCell(column).setCellValue(Cargo);
                wb.getSheetAt(0).getRow(10).createCell(column).setCellValue(climb);
                wb.getSheetAt(0).getRow(0).createCell(column).setCellValue(notes);
                wb.getSheetAt(0).getRow(11).createCell(column).setCellValue(matchNumber);

                // after creating the work book and saving it to the first sheet, we have to convert it into a file
                FileOutputStream out = new FileOutputStream((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/data.xls");
                wb.write(out);
                // now we have to save the file to data.xls phew more work.
                out.close();
                // now that we have successfully finished the conversion, we toast a message to the user telling them about our success
                Toast.makeText(getApplicationContext(), getString(R.string.export_toast), Toast.LENGTH_SHORT).show();
            }


        }
    }

    public void mailFile(View view) {
        // now the user wants to send the data over mail
        // first we find the file
        String filePath = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).getAbsoluteFile(), "/data.xls").toString();
        File f = new File(filePath);
        // then we make a new intent to share the data
        Intent intentShareFile = new Intent(Intent.ACTION_SEND);
        File fileWithinMyDir = new File(filePath);
        // we do not want to crash the app, so if the file exists, then only we will send the intent
        if (fileWithinMyDir.exists()) {
            intentShareFile.setType("application/xls");
            intentShareFile.putExtra(Intent.EXTRA_STREAM, "file://"+filePath);
            intentShareFile.putExtra(Intent.EXTRA_SUBJECT, "Scout Data " + f.getName());
            intentShareFile.putExtra(Intent.EXTRA_EMAIL, new String[]{scoutLead});
            intentShareFile.putExtra(Intent.EXTRA_TEXT, "Using DeepSpaceScouter " + f.getName());
            // now we send the intent
            this.startActivity(Intent.createChooser(intentShareFile, f.getName()));
        }

    }
    public void clear(View v){
        CargoPreGame = 0 ;
        cargoPreGame.setText(String.valueOf(CargoPreGame));
        HatchPreGame = 0 ;
        hatchPreGame.setText(String.valueOf(CargoPreGame));
        Hatch = 0 ;
        hatch.setText(String.valueOf(CargoPreGame));
        Cargo = 0 ;
        cargo.setText(String.valueOf(CargoPreGame));

        RadioButton toUncheck;

        toUncheck= findViewById(R.id.Level1);
        toUncheck.setChecked(false);
        toUncheck= findViewById(R.id.Level2);
        toUncheck.setChecked(false);
        toUncheck= findViewById(R.id.Level3);
        toUncheck.setChecked(false);

        toUncheck= findViewById(R.id.auto);
        toUncheck.setChecked(false);
        toUncheck= findViewById(R.id.blind);
        toUncheck.setChecked(false);
        toUncheck= findViewById(R.id.camera);
        toUncheck.setChecked(false);

        EditText toErase;
        toErase = findViewById(R.id.hatchMechanism);
        toErase.setText("", TextView.BufferType.EDITABLE);

        toErase = findViewById(R.id.cargoMechanism);
        toErase.setText("", TextView.BufferType.EDITABLE);

        toErase = findViewById(R.id.teamNumber);
        toErase.setText("", TextView.BufferType.EDITABLE);

        toErase = findViewById(R.id.liftMechanism);
        toErase.setText("", TextView.BufferType.EDITABLE);

        toErase = findViewById(R.id.notes);
        toErase.setText("", TextView.BufferType.EDITABLE);





    }

}