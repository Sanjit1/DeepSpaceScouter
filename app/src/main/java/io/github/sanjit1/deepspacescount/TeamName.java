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
    boolean Check = false;
    String Rad = "Did Not Answer";
    CheckBox checkBox;
    String Cross = "Cross";
    String AutoT1 = "Hatch Placed";
    String AutoT2 = "Cargo Placed";
    String AutoT3 = "Hatch Dropped";
    String AutoT4 = "Cargo Dropped";
    String AutoT5 = "Hatch Placed";
    String AutoT6 = "Cargo PLaced";
    String AutoT7 = "Hatch Dropped";
    String AutoT8 = "Cargo Dropped";
    String AutoT9 = "Hatch Placed";
    String AutoT10 = "Cargo PLaced";
    String AutoT11 = "Hatch Dropped";
    String AutoT12 = "Cargo Dropped";
    private EditText teamName;


    int Auto1 = 0;
    int Auto2 = 0;
    int Auto3 = 0;
    int Auto4 = 0;
    int Auto5 = 0;
    int Auto6 = 0;
    int Auto7 = 0;
    int Auto8 = 0;
    int Auto9 = 0;
    int Auto10 = 0;
    int Auto11 = 0;
    int Auto12 = 0;
    int Drive1 = 0;
    int Drive2 = 0;
    int Drive3 = 0;
    int Drive4 = 0;
    int Drive5 = 0;
    int Drive6 = 0;
    int Drive7 = 0;
    int Drive8 = 0;
    int Drive9 = 0;
    int Drive10 = 0;
    int Drive11 = 0;
    int Drive12 = 0;


    TextView AutoCross;// (TextView)findViewById(R.id.AutoCrosstxt);
    Button export;

    TextView AutoVal1;// (TextView)findViewById(R.id.Auto1Val);
    TextView AutoVal2;// (TextView)findViewById(R.id.Auto2Val);
    TextView AutoVal3;// (TextView)findViewById(R.id.Auto3Val);
    TextView AutoVal4;// (TextView)findViewById(R.id.Auto1Val);
    TextView AutoVal5;// (TextView)findViewById(R.id.Auto2Val);
    TextView AutoVal6;// (TextView)findViewById(R.id.Auto3Val);
    TextView AutoVal7;// (TextView)findViewById(R.id.Auto1Val);
    TextView AutoVal8;// (TextView)findViewById(R.id.Auto2Val);
    TextView AutoVal9;// (TextView)findViewById(R.id.Auto3Val);
    TextView AutoVal10;// (TextView)findViewById(R.id.Auto1Val);
    TextView AutoVal11;// (TextView)findViewById(R.id.Auto2Val);
    TextView AutoVal12;// (TextView)findViewById(R.id.Auto3Val);

    TextView DriveVal1;// (TextView)findViewById(R.id.Drive1Val);
    TextView DriveVal2;// (TextView)findViewById(R.id.Drive2Val);
    TextView DriveVal3;// (TextView)findViewById(R.id.Drive3Val);
    TextView DriveVal4;// (TextView)findViewById(R.id.Drive1Val);
    TextView DriveVal5;// (TextView)findViewById(R.id.Drive2Val);
    TextView DriveVal6;// (TextView)findViewById(R.id.Drive3Val);
    TextView DriveVal7;// (TextView)findViewById(R.id.Drive1Val);
    TextView DriveVal8;// (TextView)findViewById(R.id.Drive2Val);
    TextView DriveVal9;// (TextView)findViewById(R.id.Drive3Val);
    TextView DriveVal10;// (TextView)findViewById(R.id.Drive1Val);
    TextView DriveVal11;// (TextView)findViewById(R.id.Drive2Val);
    TextView DriveVal12;// (TextView)findViewById(R.id.Drive3Val);

    TextView AutoTask1;// (TextView)findViewById(R.id.AutoTask1);
    TextView AutoTask2;// (TextView)findViewById(R.id.AutoTask2);
    TextView AutoTask3;// (TextView)findViewById(R.id.AutoTask3);
    TextView AutoTask4;// (TextView)findViewById(R.id.AutoTask1);
    TextView AutoTask5;// (TextView)findViewById(R.id.AutoTask2);
    TextView AutoTask6;// (TextView)findViewById(R.id.AutoTask3);
    TextView AutoTask7;// (TextView)findViewById(R.id.AutoTask1);
    TextView AutoTask8;// (TextView)findViewById(R.id.AutoTask2);
    TextView AutoTask9;// (TextView)findViewById(R.id.AutoTask3);
    TextView AutoTask10;// (TextView)findViewById(R.id.AutoTask1);
    TextView AutoTask11;// (TextView)findViewById(R.id.AutoTask2);
    TextView AutoTask12;// (TextView)findViewById(R.id.AutoTask3);


    TextView DriveTask1;// (TextView)findViewById(R.id.DriveTask1);
    TextView DriveTask2;// (TextView)findViewById(R.id.DriveTask2);
    TextView DriveTask3;// (TextView)findViewById(R.id.DriveTask3);
    TextView DriveTask4;// (TextView)findViewById(R.id.DriveTask1);
    TextView DriveTask5;// (TextView)findViewById(R.id.DriveTask2);
    TextView DriveTask6;// (TextView)findViewById(R.id.DriveTask3);
    TextView DriveTask7;// (TextView)findViewById(R.id.DriveTask1);
    TextView DriveTask8;// (TextView)findViewById(R.id.DriveTask2);
    TextView DriveTask9;// (TextView)findViewById(R.id.DriveTask3);
    TextView DriveTask10;// (TextView)findViewById(R.id.DriveTask1);
    TextView DriveTask11;// (TextView)findViewById(R.id.DriveTask2);
    TextView DriveTask12;// (TextView)findViewById(R.id.DriveTask3);

    Button A1p;
    Button A2p;
    Button A3p;
    Button A4p;
    Button A5p;
    Button A6p;
    Button A7p;
    Button A8p;
    Button A9p;
    Button A10p;
    Button A11p;
    Button A12p;
    Button A1m;
    Button A2m;
    Button A3m;
    Button A4m;
    Button A5m;
    Button A6m;
    Button A7m;
    Button A8m;
    Button A9m;
    Button A10m;
    Button A11m;
    Button A12m;

    Button D1p;
    Button D2p;
    Button D3p;
    Button D4p;
    Button D5p;
    Button D6p;
    Button D7p;
    Button D8p;
    Button D9p;
    Button D10p;
    Button D11p;
    Button D12p;
    Button D1m;
    Button D2m;
    Button D3m;
    Button D4m;
    Button D5m;
    Button D6m;
    Button D7m;
    Button D8m;
    Button D9m;
    Button D10m;
    Button D11m;
    Button D12m;

    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


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
        edit = findViewById(R.id.team);


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_team_name);


        A1p = findViewById(R.id.A1p);
        A2p = findViewById(R.id.A2p);
        A3p = findViewById(R.id.A3p);
        A4p = findViewById(R.id.A4p);
        A5p = findViewById(R.id.A5p);
        A6p = findViewById(R.id.A6p);
        A7p = findViewById(R.id.A7p);
        A8p = findViewById(R.id.A8p);
        A9p = findViewById(R.id.A9p);
        A1m = findViewById(R.id.A1m);
        A2m = findViewById(R.id.A2m);
        A3m = findViewById(R.id.A3m);
        A4m = findViewById(R.id.A4m);
        A5m = findViewById(R.id.A5m);
        A6m = findViewById(R.id.A6m);
        A7m = findViewById(R.id.A7m);
        A8m = findViewById(R.id.A8m);
        A9m = findViewById(R.id.A9m);
        D1p = findViewById(R.id.D1p);
        D2p = findViewById(R.id.D2p);
        D3p = findViewById(R.id.D3p);
        D4p = findViewById(R.id.D4p);
        D5p = findViewById(R.id.D5p);
        D6p = findViewById(R.id.D6p);
        D7p = findViewById(R.id.D7p);
        D8p = findViewById(R.id.D8p);
        D9p = findViewById(R.id.D9p);
        D1m = findViewById(R.id.D1m);
        D2m = findViewById(R.id.D2m);
        D3m = findViewById(R.id.D3m);
        D4m = findViewById(R.id.D4m);
        D5m = findViewById(R.id.D5m);
        D6m = findViewById(R.id.D6m);
        D7m = findViewById(R.id.D7m);
        D8m = findViewById(R.id.D8m);
        D9m = findViewById(R.id.D9m);
        export = findViewById(R.id.export);
        changeButt(A1p);
        changeButt(A2p);
        changeButt(A3p);
        changeButt(A4p);
        changeButt(A5p);
        changeButt(A6p);
        changeButt(A7p);
        changeButt(A8p);
        changeButt(A9p);
        changeButt(A1m);
        changeButt(A2m);
        changeButt(A3m);
        changeButt(A4m);
        changeButt(A5m);
        changeButt(A6m);
        changeButt(A7m);
        changeButt(A8m);
        changeButt(A9m);
        changeButt(D1p);
        changeButt(D2p);
        changeButt(D3p);
        changeButt(D4p);
        changeButt(D5p);
        changeButt(D6p);
        changeButt(D7p);
        changeButt(D8p);
        changeButt(D9p);
        changeButt(D1m);
        changeButt(D2m);
        changeButt(D3m);
        changeButt(D4m);
        changeButt(D5m);
        changeButt(D6m);
        changeButt(D7m);
        changeButt(D8m);
        changeButt(D9m);

        AutoCross = findViewById(R.id.AutoCrosstxt);
        AutoTask1 = findViewById(R.id.AutoTask1);
        AutoTask2 = findViewById(R.id.AutoTask2);
        AutoTask3 = findViewById(R.id.AutoTask3);
        AutoTask4 = findViewById(R.id.AutoTask4);
        AutoTask5 = findViewById(R.id.Auto1Task1);
        AutoTask6 = findViewById(R.id.Auto1Task2);
        AutoTask7 = findViewById(R.id.Auto1Task3);
        AutoTask8 = findViewById(R.id.Auto1Task4);
        AutoTask9 = findViewById(R.id.Auto2Task1);
        AutoTask10 = findViewById(R.id.Auto2Task2);
        AutoTask11 = findViewById(R.id.Auto2Task3);
        AutoTask12 = findViewById(R.id.Auto2Task4);
        DriveTask1 = findViewById(R.id.DriveTask1);
        DriveTask2 = findViewById(R.id.DriveTask2);
        DriveTask3 = findViewById(R.id.DriveTask3);
        DriveTask4 = findViewById(R.id.DriveTask4);
        DriveTask5 = findViewById(R.id.Drive1Task1);
        DriveTask6 = findViewById(R.id.Drive1Task2);
        DriveTask7 = findViewById(R.id.Drive1Task3);
        DriveTask8 = findViewById(R.id.Drive1Task4);
        DriveTask9 = findViewById(R.id.Drive2Task1);
        DriveTask10 = findViewById(R.id.Drive2Task2);
        DriveTask11 = findViewById(R.id.Drive2Task3);
        DriveTask12 = findViewById(R.id.Drive2Task4);
        AutoCross.setText(Cross);

        AutoVal1 = findViewById(R.id.Auto1Val);
        AutoVal2 = findViewById(R.id.Auto2Val);
        AutoVal3 = findViewById(R.id.Auto3Val);
        AutoVal4 = findViewById(R.id.Auto4Val);
        AutoVal5 = findViewById(R.id.Auto5Val);
        AutoVal6 = findViewById(R.id.Auto6Val);
        AutoVal7 = findViewById(R.id.Auto7Val);
        AutoVal8 = findViewById(R.id.Auto8Val);
        AutoVal9 = findViewById(R.id.Auto9Val);
        AutoVal10 = findViewById(R.id.Auto10Val);
        AutoVal11 = findViewById(R.id.Auto11Val);
        AutoVal12 = findViewById(R.id.Auto12Val);

        AutoTask1.setText(AutoT1);
        AutoTask2.setText(AutoT2);
        AutoTask3.setText(AutoT3);
        AutoTask4.setText(AutoT4);
        AutoTask5.setText(AutoT5);
        AutoTask6.setText(AutoT6);
        AutoTask7.setText(AutoT7);
        AutoTask8.setText(AutoT8);
        AutoTask9.setText(AutoT9);
        AutoTask10.setText(AutoT10);
        AutoTask11.setText(AutoT11);
        AutoTask12.setText(AutoT12);

        DriveVal1 = findViewById(R.id.Drive1Val);
        DriveVal2 = findViewById(R.id.Drive2Val);
        DriveVal3 = findViewById(R.id.Drive3Val);
        DriveVal4 = findViewById(R.id.Drive4Val);
        DriveVal5 = findViewById(R.id.Drive5Val);
        DriveVal6 = findViewById(R.id.Drive6Val);
        DriveVal7 = findViewById(R.id.Drive7Val);
        DriveVal8 = findViewById(R.id.Drive8Val);
        DriveVal9 = findViewById(R.id.Drive9Val);
        DriveVal10 = findViewById(R.id.Drive10Val);
        DriveVal11 = findViewById(R.id.Drive11Val);
        DriveVal12 = findViewById(R.id.Drive12Val);


        DriveTask1.setText(AutoT1);
        DriveTask2.setText(AutoT2);
        DriveTask3.setText(AutoT3);
        DriveTask4.setText(AutoT4);
        DriveTask5.setText(AutoT5);
        DriveTask6.setText(AutoT6);
        DriveTask7.setText(AutoT7);
        DriveTask8.setText(AutoT8);
        DriveTask9.setText(AutoT9);
        DriveTask10.setText(AutoT10);
        DriveTask11.setText(AutoT11);
        DriveTask12.setText(AutoT12);


    }

    public void A1p(View view) {
        Auto1++;
        AutoVal1.setText(String.valueOf(Auto1));


    }

    public void A1m(View view) {
        Auto1--;
        AutoVal1.setText(String.valueOf(Auto1));
    }

    public void A2p(View view) {
        Auto2++;
        AutoVal2.setText(String.valueOf(Auto2));
    }

    public void A2m(View view) {
        Auto2--;
        AutoVal2.setText(String.valueOf(Auto2));
    }

    public void A3p(View view) {
        Auto3++;
        AutoVal3.setText(String.valueOf(Auto3));
    }

    public void A3m(View view) {
        Auto3--;
        AutoVal3.setText(String.valueOf(Auto3));
    }

    public void A4p(View view) {
        Auto4++;
        AutoVal4.setText(String.valueOf(Auto4));
    }

    public void A4m(View view) {
        Auto4--;
        AutoVal4.setText(String.valueOf(Auto4));
    }

    public void A5p(View view) {
        Auto5++;
        AutoVal5.setText(String.valueOf(Auto5));
    }

    public void A5m(View view) {
        Auto5--;
        AutoVal5.setText(String.valueOf(Auto5));
    }


    public void A6p(View view) {
        Auto6++;
        AutoVal6.setText(String.valueOf(Auto6));
    }

    public void A6m(View view) {
        Auto6--;
        AutoVal6.setText(String.valueOf(Auto6));
    }

    public void A7p(View view) {
        Auto7++;
        AutoVal7.setText(String.valueOf(Auto7));
    }

    public void A7m(View view) {
        Auto7--;
        AutoVal7.setText(String.valueOf(Auto7));
    }


    public void A8p(View view) {
        Auto8++;
        AutoVal8.setText(String.valueOf(Auto8));
    }

    public void A8m(View view) {
        Auto8--;
        AutoVal8.setText(String.valueOf(Auto8));
    }


    public void A9p(View view) {
        Auto9++;
        AutoVal9.setText(String.valueOf(Auto9));
    }

    public void A9m(View view) {
        Auto9--;
        AutoVal9.setText(String.valueOf(Auto9));
    }

    public void A10p(View view) {
        Auto10++;
        AutoVal10.setText(String.valueOf(Auto10));
    }

    public void A10m(View view) {
        Auto10--;
        AutoVal10.setText(String.valueOf(Auto10));
    }


    public void A11p(View view) {
        Auto11++;
        AutoVal11.setText(String.valueOf(Auto11));
    }

    public void A11m(View view) {
        Auto11--;
        AutoVal11.setText(String.valueOf(Auto11));
    }


    public void A12p(View view) {
        Auto12++;
        AutoVal12.setText(String.valueOf(Auto12));
    }

    public void A12m(View view) {
        Auto12--;
        AutoVal12.setText(String.valueOf(Auto12));
    }

    public void D1p(View view) {
        Drive1++;
        DriveVal1.setText(String.valueOf(Drive1));
    }

    public void D1m(View view) {
        Drive1--;
        DriveVal1.setText(String.valueOf(Drive1));
    }


    public void D2p(View view) {
        Drive2++;
        DriveVal2.setText(String.valueOf(Drive2));
    }

    public void D2m(View view) {
        Drive2--;
        DriveVal2.setText(String.valueOf(Drive2));
    }


    public void D3p(View view) {
        Drive3++;
        DriveVal3.setText(String.valueOf(Drive3));
    }

    public void D3m(View view) {
        Drive3--;
        DriveVal3.setText(String.valueOf(Drive3));
    }

    public void D4p(View view) {
        Drive4++;
        DriveVal4.setText(String.valueOf(Drive4));
    }

    public void D4m(View view) {
        Drive4--;
        DriveVal4.setText(String.valueOf(Drive4));
    }


    public void D5p(View view) {
        Drive5++;
        DriveVal5.setText(String.valueOf(Drive5));
    }

    public void D5m(View view) {
        Drive5--;
        DriveVal5.setText(String.valueOf(Drive5));
    }


    public void D6p(View view) {
        Drive6++;
        DriveVal6.setText(String.valueOf(Drive6));
    }

    public void D6m(View view) {
        Drive6--;
        DriveVal6.setText(String.valueOf(Drive6));
    }

    public void D7p(View view) {
        Drive7++;
        DriveVal7.setText(String.valueOf(Drive7));
    }

    public void D7m(View view) {
        Drive7--;
        DriveVal7.setText(String.valueOf(Drive7));
    }


    public void D8p(View view) {
        Drive8++;
        DriveVal8.setText(String.valueOf(Drive8));
    }

    public void D8m(View view) {
        Drive8--;
        DriveVal8.setText(String.valueOf(Drive8));
    }


    public void D9p(View view) {
        Drive9++;
        DriveVal9.setText(String.valueOf(Drive9));
    }

    public void D9m(View view) {
        Drive9--;
        DriveVal9.setText(String.valueOf(Drive9));
    }

    public void D10p(View view) {
        Drive10++;
        DriveVal10.setText(String.valueOf(Drive10));
    }

    public void D10m(View view) {
        Drive10--;
        DriveVal10.setText(String.valueOf(Drive10));
    }


    public void D11p(View view) {
        Drive11++;
        DriveVal11.setText(String.valueOf(Drive11));
    }

    public void D11m(View view) {
        Drive11--;
        DriveVal11.setText(String.valueOf(Drive11));
    }


    public void D12p(View view) {
        Drive12++;
        DriveVal12.setText(String.valueOf(Drive12));
    }

    public void D12m(View view) {
        Drive12--;
        DriveVal12.setText(String.valueOf(Drive12));
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
            case R.id.NT:
                if (checked)
                    Rad = "Did not Climb";
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

    public void changeButt(View view) {
        int b = view.getHeight();
        view.getLayoutParams().width = b;
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET}, 1);


    }


    public void export(View view) throws IOException {
        String team =  ((EditText) findViewById(R.id.team)).getText().toString();

        checkBox = findViewById(R.id.AutoCross);
        Check = checkBox.isChecked();

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

            while (wb.getSheetAt(0).getRow(0).getCell(c).toString()!= o) {


                c++;

            }
            Toast toastb = Toast.makeText(getApplicationContext(), "Creating table", Toast.LENGTH_SHORT);
            toastb.show();


            wb.getSheetAt(0).getRow(0).createCell(c).setCellValue(team);
            wb.getSheetAt(0).getRow(1).createCell(c).setCellValue(Check);
            wb.getSheetAt(0).getRow(3).createCell(c).setCellValue(Auto1);
            wb.getSheetAt(0).getRow(4).createCell(c).setCellValue(Auto2);
            wb.getSheetAt(0).getRow(5).createCell(c).setCellValue(Auto3);
            wb.getSheetAt(0).getRow(6).createCell(c).setCellValue(Auto4);
            wb.getSheetAt(0).getRow(8).createCell(c).setCellValue(Auto5);
            wb.getSheetAt(0).getRow(9).createCell(c).setCellValue(Auto6);
            wb.getSheetAt(0).getRow(10).createCell(c).setCellValue(Auto7);
            wb.getSheetAt(0).getRow(11).createCell(c).setCellValue(Auto8);
            wb.getSheetAt(0).getRow(13).createCell(c).setCellValue(Auto9);
            wb.getSheetAt(0).getRow(14).createCell(c).setCellValue(Auto10);
            wb.getSheetAt(0).getRow(15).createCell(c).setCellValue(Auto11);
            wb.getSheetAt(0).getRow(16).createCell(c).setCellValue(Auto12);
            wb.getSheetAt(0).getRow(19).createCell(c).setCellValue(Drive1);
            wb.getSheetAt(0).getRow(20).createCell(c).setCellValue(Drive2);
            wb.getSheetAt(0).getRow(21).createCell(c).setCellValue(Drive3);
            wb.getSheetAt(0).getRow(22).createCell(c).setCellValue(Drive4);
            wb.getSheetAt(0).getRow(24).createCell(c).setCellValue(Drive5);
            wb.getSheetAt(0).getRow(25).createCell(c).setCellValue(Drive6);
            wb.getSheetAt(0).getRow(26).createCell(c).setCellValue(Drive7);
            wb.getSheetAt(0).getRow(27).createCell(c).setCellValue(Drive8);
            wb.getSheetAt(0).getRow(29).createCell(c).setCellValue(Drive9);
            wb.getSheetAt(0).getRow(30).createCell(c).setCellValue(Drive10);
            wb.getSheetAt(0).getRow(31).createCell(c).setCellValue(Drive11);
            wb.getSheetAt(0).getRow(32).createCell(c).setCellValue(Drive12);

            FileOutputStream out = new FileOutputStream((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/data.xls");
            wb.write(out);
            out.close();
            Toast toasta = Toast.makeText(getApplicationContext(), "File exported to downloads/data.xls", Toast.LENGTH_SHORT);
            toasta.show();
        }


    }


}

