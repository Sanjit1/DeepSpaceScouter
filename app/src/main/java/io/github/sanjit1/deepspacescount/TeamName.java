package io.github.sanjit1.deepspacescount;
import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

public class TeamName extends Activity {
    private int STORAGE_PERMISSION_CODE = 1;



    RadioButton aut;
    RadioButton bl;
    RadioButton cam;

    RadioButton L1;
    RadioButton L2;
    RadioButton L3;

    Button Tp1;
    Button Tp2;
    Button Tp3;
    Button Tp4;
    Button Tm1;
    Button Tm2;
    Button Tm3;
    Button Tm4;

    TextView Tv1;
    TextView Tv2;
    TextView Tv3;
    TextView Tv4;

    int TV1=0;
    int TV2=0;
    int TV3=0;
    int TV4=0;

    String SG = "Did not answer";

    String Rad="Did not answer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {








       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //    setSupportActionBar(toolbar);

        if (ContextCompat.checkSelfPermission(TeamName.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

        } else {
            requestStoragePermission();
        }
        if (ContextCompat.checkSelfPermission(TeamName.this,
                Manifest.permission.ACCESS_NETWORK_STATE) == PackageManager.PERMISSION_GRANTED) {

        } else {
            requestAccessPermission();
        }
        if (ContextCompat.checkSelfPermission(TeamName.this,
                Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED) {

        } else {
            requestInternetPermission();
        }
        if (ContextCompat.checkSelfPermission(TeamName.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

        } else {
            requestStorageWritePermission();
        }



        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_team_name);

        Tv1 = findViewById (R.id.Tv1);
        Tv2 = findViewById (R.id.Tv2);
        Tv3 = findViewById (R.id.Tv3);
        Tv4 = findViewById (R.id.Tv4);

        aut = findViewById (R.id.aut);
        bl = findViewById (R.id.bl);
        cam = findViewById (R.id.cam);

        L1 = findViewById (R.id.L1);
        L2 = findViewById (R.id.L2);
        L3 = findViewById (R.id.L3);

        Tp1 = findViewById(R.id.Tp1);
        Tp2 = findViewById(R.id.Tp1);
        Tp3 = findViewById(R.id.Tp1);
        Tp4 = findViewById(R.id.Tp1);

        Tm1 = findViewById(R.id.Tm1);
        Tm2 = findViewById(R.id.Tm2);
        Tm3 = findViewById(R.id.Tm3);
        Tm4 = findViewById(R.id.Tm4);




    }

    public void t1p(View view) {
        TV1++;
        Tv1.setText(String.valueOf(TV1));


    }

    public void t2p(View view) {
        TV2++;
        Tv2.setText(String.valueOf(TV2));


    }

    public void t3p(View view) {
        TV3++;
        Tv3.setText(String.valueOf(TV3));


    }

    public void t4p(View view) {
        TV4++;
        Tv4.setText(String.valueOf(TV4));


    }




    public void t1m(View view) {
        TV1--;
        Tv1.setText(String.valueOf(TV1));


    }

    public void t2m(View view) {
        TV2--;
        Tv2.setText(String.valueOf(TV2));


    }

    public void t3m(View view) {
        TV3--;
        Tv3.setText(String.valueOf(TV3));


    }

    public void t4m(View view) {
        TV4--;
        Tv4.setText(String.valueOf(TV4));


    }




    public void Climb(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.L1:
                if (checked)
                    Rad = "Level 1";
                break;
            case R.id.L2:
                if (checked)
                    Rad = "Level 2";
                break;
            case R.id.L3:
                if (checked)
                    Rad = "Level 3";
                break;

        }


    }

    public void ctrl(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.aut:
                if (checked)
                    SG = "Auto";
                break;
            case R.id.cam:
                if (checked)
                    SG = "Blind";
                break;
            case R.id.bl:
                if (checked)
                    SG = "Camera";
                break;

        }


    }


    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(TeamName.this,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    private void requestInternetPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.INTERNET)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("Permission needed to get ")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(TeamName.this,
                                    new String[]{Manifest.permission.INTERNET}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET}, STORAGE_PERMISSION_CODE);
        }
    }

    public void getTextFile() {
        String path = "https://sanjit1.github.io/Android/data.xls";
        URL u = null;
        try {
            u = new URL(path);
            BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
            int i = 0;
            String replicated = "";
            do {
                String str = in.readLine();
                replicated = replicated + "/n" + str;
                i++;
            } while (i < 85);
            in.close();
        } catch (Exception e) {

        }
    }

    private void requestStorageWritePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(TeamName.this,
                                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    private void requestAccessPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_NETWORK_STATE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(TeamName.this,
                                    new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, STORAGE_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_NETWORK_STATE}, STORAGE_PERMISSION_CODE);
        }
    }
    public void shareFile(View view) {
        String filePath = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)), "/data.xls").toString();
        File f = new File(filePath);

        Intent intentShareFile = new Intent(Intent.ACTION_SEND);
        File fileWithinMyDir = new File(filePath);

        if (fileWithinMyDir.exists()) {
           // intentShareFile.setType("text/*");
            intentShareFile.setType(URLConnection.guessContentTypeFromName(f.getName()));
            intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse( filePath));
            intentShareFile.putExtra(Intent.EXTRA_SUBJECT, "Scout Data" + f.getName());
            intentShareFile.putExtra(Intent.EXTRA_TEXT, "Using DeepSpaceScouter" + f.getName());

            this.startActivity(Intent.createChooser(intentShareFile, f.getName()));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

            }
        }
    }




    public void export(View view) throws IOException {
        String team = ((EditText) findViewById(R.id.team)).getText().toString();
        String hatch = ((EditText) findViewById(R.id.hat)).getText().toString();
        String cargo = ((EditText) findViewById(R.id.car)).getText().toString();
        String lift = ((EditText) findViewById(R.id.lift)).getText().toString();
        if (TextUtils.isEmpty(team)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Enter a team name!", Toast.LENGTH_LONG);
            toast.show();
        } else         if (TextUtils.isEmpty(hatch)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Enter hatch mechanism!", Toast.LENGTH_LONG);
            toast.show();
        } else         if (TextUtils.isEmpty(cargo)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Enter cargo mechanism!", Toast.LENGTH_LONG);
            toast.show();
        } else         if (TextUtils.isEmpty(lift)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Enter lift!", Toast.LENGTH_LONG);
            toast.show();
        } else{


            String[] files = fileList();


            File mediaStorageDir = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)), "/data.xls");


            if (!mediaStorageDir.exists()) {


                String filepath = mediaStorageDir.toString();
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET}, 1);
                String csvFile = "data.xls";

                File directory = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()));
                File file = new File(directory, csvFile);

                getTextFile();


                String url = "https://sanjit1.github.io/Android/data.xls";
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setDescription("The excel table that will store your scouted data.");
                request.setTitle("data.xls");

                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "data.xls");


                DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);
                Toast toast = Toast.makeText(getApplicationContext(), "Downloading a file to store data. Do not click export until download is complete.", Toast.LENGTH_LONG);
                toast.show();
            } else {
                //file exits

                InputStream ExcelFileToRead = new FileInputStream((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/data.xls");
                HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
                HSSFSheet sheet = wb.getSheetAt(0);
                HSSFRow row;
                HSSFCell cell;
                Iterator rows = sheet.rowIterator();
                while (rows.hasNext()) {
                    row = (HSSFRow) rows.next();
                    Iterator cells = row.cellIterator();

                    while (cells.hasNext()) {
                        cell = (HSSFCell) cells.next();


                    }


                }
                String o = wb.getSheetAt(0).getRow(200).getCell(255).toString();
                int c = 1;
                int mNumb = 1;

                while (wb.getSheetAt(0).getRow(0).getCell(c).toString() != o) {
                    if (Integer.parseInt(team) == Integer.parseInt(wb.getSheetAt(0).getRow(0).getCell(c).toString())) {

                        mNumb++;
                    }

                    c++;

                }
                Toast toastb = Toast.makeText(getApplicationContext(), "Creating table", Toast.LENGTH_SHORT);
                toastb.show();
                wb.getSheetAt(0).getRow(0).createCell(c).setCellValue(team);
                wb.getSheetAt(0).getRow(1).createCell(c).setCellValue(SG);
                wb.getSheetAt(0).getRow(2).createCell(c).setCellValue(hatch);
                wb.getSheetAt(0).getRow(3).createCell(c).setCellValue(cargo);
                wb.getSheetAt(0).getRow(4).createCell(c).setCellValue(lift);
                wb.getSheetAt(0).getRow(6).createCell(c).setCellValue(TV1);
                wb.getSheetAt(0).getRow(7).createCell(c).setCellValue(TV2);
                wb.getSheetAt(0).getRow(8).createCell(c).setCellValue(TV3);
                wb.getSheetAt(0).getRow(9).createCell(c).setCellValue(TV4);
                wb.getSheetAt(0).getRow(10).createCell(c).setCellValue(Rad);
                wb.getSheetAt(0).getRow(11).createCell(c).setCellValue(mNumb);

                FileOutputStream out = new FileOutputStream((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/data.xls");
                wb.write(out);
                out.close();
                Toast toasta = Toast.makeText(getApplicationContext(), "File exported to downloads/data.xls", Toast.LENGTH_SHORT);
                toasta.show();
            }


        }
    }

}

