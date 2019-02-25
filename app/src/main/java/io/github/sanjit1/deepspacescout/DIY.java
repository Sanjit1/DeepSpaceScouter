package io.github.sanjit1.deepspacescout;

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
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;




public class DIY extends AppCompatActivity {
    // Bonjour! This is the activity that helps you configure your own activity
    // There are gonna be many crazy stuff mostly arrays
    // I am commenting after one day so lets hope I get it all right!!
    // Lets go!

    // Before I start, the boring part: theory.
    // We want our users to be able to make there own scouting activities. They have 3 view Options
    // For qualitative info, So we have edit text
    // For choice, we have spinners
    // For quantitative, we have 4 views combined. Lol lets just call it quantum view
    // So for each of these we have configuration options, to personalise them.
    // EditText: Text Input. Eg:Hatch grabber Mechanism, or notes or Team Name,(if your scouting team is Einstein at memory ) Numeric Input. Ex: Team Number
    // Spinner: Start Game: Choices. Eg:Lift Levels. L1, L2, L3
    // Quantum: minimum Value. Eg: You cannot have -10 cargo.  maximum Value. Eg: You cannot have 20 climb levels.
    // So ya. We take the config data and arrange it into order and then into a text file which can be read from the next class. That is all you need to know of the next class now.
    // Also the file format and how it is gonna be arranged in the text file is gonna be discussed



    // These card views are the parameters for each view
    // for example, when we want to take input a text team Number. In the edit text, number or numeric input is a configuration of the parameter team number.
    // Remember parameter and config meaning it is reaaally important!!
    // Again. these card views will be holding the configuration options so when a user changes the spinner for parameter type, we change configuration views accordingly.
    // That makes sense, cause we do not want to have text or numeric as options for quantitative! That's silly!!
    CardView text;
    CardView spinner;
    CardView numeric;


    //Remember the magical spinner we were talking about? huuuuuh!! that's right there!!
    Spinner textConfiguration;
    // these are numbers used for tracking stuff in the program
    // we will talk about them in a while.
    int textNumber;
    int spinnerNumber;
    int numericNumber;

    // If you remember the spinner configs from somewhere around line 40, the config has a list of choices and that can be arranged into an arraylist.
    // And we will be arranging configs in arrayslists too, because who knows hoe many the user wants. So that is an arraylist of an arraylist.
    // Sorry this is very frustrating. My fault I could not get the hang of arrays in arrays.
    // But! What is an array? kind of like a name list! And what is an arraylist of an arraylist?
    // Kind of like s name list with a description for each kid with maybe fields like height, width etc
    // How can you arrange such data? a table! which explains the next line.

    Workbook spinnerConfigStuff;


    // So here are some arrays cause everthing has to be arraylisted(is that even a word?) cause we do not know the ui of the user.
    // So the arrylist paramtype will help us know which parameter type we are working with.
    // Together with the..Number variables above, we can know which configtype we need to look at, and based on the number variables,
    // we can also figure out how much of that config type has been covered and figure out the exact position. Long explanation
    // We also need to have something that tells us which order the user has put the views in, we need on of these as a structure.
    // SO this one mainly acts as a structure.
    ArrayList<String> paramType = new ArrayList<>();
    // So we already have a structure element. and anyway paramName cannot be the structure, cause it is a name.
    // I think having one is better, cause otherwise we would have to have 3 arrays. So ya. This is more efficient
    // Also since I have not directly said it yet, this is a name variable. You gotta tell your user some thing Are you asking for notes or a taco size!
    ArrayList<String> paramName = new ArrayList<>();
    // text config is an array which Either stores the word text or numeric
    ArrayList<String> textConfig = new ArrayList<>();
    // min and max config are brothers. THey are both config options for quantum stuff
    ArrayList<String> minConfig = new ArrayList<>();
    ArrayList<String> maxConfig = new ArrayList<>();
    // So we need this helper variable to tell us stuff will cover it when we reach the function
    ArrayList<String> spinnerOptionCreater = new ArrayList<>();
    // I told you that the config was the workbook, but this is just counting the number of spinner options there are in each parameter,
    // so that when we save the file we can tell it how many times to get the options
    ArrayList<Integer> spinnerConfig = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // so we make a directory, to store app data.
        // and then another to store the activity data. Once I figure it out, you can have i guess infinite activities, nut that is not my top concerns at this moment
        File scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData");
        createDir(scout);
        scout = new File((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData");
        createDir(scout);

        // initialise the workbook to save the spinner stuff in
        spinnerConfigStuff = new HSSFWorkbook();
        // we need a new "sheet" or as I prefer "table" any name should be fine, cause we find it as the first workbook anyway.
        spinnerConfigStuff.createSheet("👾");
        // initialize layout
        super.onCreate(savedInstanceState);
        // setup xml stuff
        setContentView(R.layout.activity_diy);
        // now we go on initializing spreeee wheeee!
        // We have to make one of the three cardviews visible based on the selection made by the user. SO that is why we have an onselect listener for it
        Spinner paramChoice = findViewById(R.id.parameter);

        // These three are the cards from that will reveal themselves if their option is selected
        text = findViewById(R.id.textConfig);
        spinner = findViewById(R.id.spinnerConfig);
        numeric = findViewById(R.id.numberConfig);
        // here is the onselect listener that I was talking about
        paramChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //stuff here to handle item selection
                if (Objects.equals(parent.getItemAtPosition(position).toString(), "Text")) {
                    // It hides the other 2 cards and selects only one card
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
        // So when we click add value, it adds the option one by on to this array


    }

    public void createDir(File scout){
        // sorry this is in the middle of nowhere
        if (!scout.exists() && !scout.isDirectory()) {
            // Ity takes the directory and creates it and then checks if it workedor if it failed
            if (scout.mkdirs()) {
                Log.i("CreateDir", "App dir created");
            } else {
            }
        } else {
            Log.i("CreateDir", "App dir already exists");
        }
    }


    public void add(View v) {
        // Now when the user adds the view to the layout
        // we first check which view has been touched
        if (text.getVisibility() == View.VISIBLE) {
            // We initialise the configuration to the the spinner which inputs text or numeric
            textConfiguration = findViewById(R.id.text_config);
            // Then we increase the number of text Views in the layout, this is gonna be needed later.
            textNumber++;
            // Remember the structure element param type, we are gonna be using it right now and add it to the structure
            paramType.add("T");
            // Then we add the value numeric or text to the arraylist
            textConfig.add(textConfiguration.getSelectedItem().toString());
            // Then we get the parameter name and then add it to the arraylist
            paramName.add(((EditText) findViewById(R.id.parameterName)).getText().toString());
        } else if (spinner.getVisibility() == View.VISIBLE) {
            // We increase the number of spinners in the layout
            spinnerNumber++;
            // Then we add the spinner parameter type
            paramType.add("S");
            // Now we add the size to the config
            // we need this to add the right amount of options in the spinner. You will see
            spinnerConfig.add(spinnerOptionCreater.size());
            // Then we create a new row to add data to
            spinnerConfigStuff.getSheetAt(0).createRow(spinnerNumber-1);
            // Now we will get the size of spinner config and then add cell parameter the number of values the array has
            // That is why we need to add the number of items in the arraylist, because when we need to make the text file, we will take out the value just as many times as there are options
            // Otherwise it will add text a billion times and then tell you that there are not enough cells
            for (int i = 0; i < spinnerOptionCreater.size(); i++) {
            spinnerConfigStuff.getSheetAt(0).getRow(spinnerNumber-1).createCell(i).setCellValue(spinnerOptionCreater.get(i));
            }
            // Then we empty the spinner for reuse
            spinnerOptionCreater.removeAll(spinnerOptionCreater);
            // The the usual, we add the parameter name
            paramName.add(((EditText) findViewById(R.id.parameterName)).getText().toString());
        } else if (numeric.getVisibility() == View.VISIBLE) {
            // First we increase the number of quantum views there are
            numericNumber++;
            // We add the parameter name
            paramType.add("N");
            // We use a turnery turnary? turkey? I forgot how it is spelt
            // We use it to set a min val 0 if it is not entered or 255 if not entered
            minConfig.add((((EditText) findViewById(R.id.minVal)).getText() !=null) ? ((EditText) findViewById(R.id.minVal)).getText().toString() : "0");
            maxConfig.add((((EditText) findViewById(R.id.maxVal)).getText() !=null) ? ((EditText) findViewById(R.id.maxVal)).getText().toString() : "255");
            // THe usual param name
            paramName.add(((EditText) findViewById(R.id.parameterName)).getText().toString());
        }


    }



    public void saveAndLoad(View v){
        // Welcome to the saving and loading(intent) function!
        // First we need to check which button has been clicked
        // I merged them into one for Idk what reason
        // If it is save, then we do:
        if(v == findViewById(R.id.save)){

            // then we make a file object called activity.🚀🤖 its a cool name ri8? Win+. gives you emojis
            // Anyway, we need to save stuff to this file so.. YA!
            File activity = new File ((Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)) + "/ScouterAppData/ActivityData/activity.🚀🤖");
            // We need to do try catch with IOException which is basically if the file does not exist
            try {
                // We initialise a file writer
                // IMPORTANT
                // I am following a file protocol to basically figure out where stuff is and thus I think I should go over what kind of protocol I used,
                // cause that was the first thing I had to do before designing this function :

                /* Activity Name
                * Parameter Type
                * Parameter Name
                * Config Stuff(T for text, S for Spinner and N for Quantum)   // Config Stuff based on parameter Type varies I will start with text then spinner then Quantum
                * Text: Param Type(Possible values are text and numeric)
                * Spinner: Param Type(List of spinner values followed by endSpinner🚀🤖🚀🤖*)
                * Quantum: Param Type(minimum value followed by maximum value on the next line)
                * Followed by endAct🚀🤖
                */

                // That could have been confusing so I will give an example, so you get the heck of it
                // Also note that we are not using stuff like "ParamType:T" Cause It could get more complicated during debugging
                /*Deep Space
                * T
                * Team Name
                * Numeric
                * S
                * Start Game
                * Autonomous
                * Camera
                * endSpinner🚀🤖🚀🤖
                * T
                * Hatch Grabber mechanism
                * Text
                * N
                * Hatch installed pre-game
                * 0
                * 100
                * S
                * Favorite Ice-Cream Flavor
                * Chocolate
                * Tigger
                * Vanilla
                * Strawberry
                * Pizza
                * Python
                * Cherry
                * endSpinner🚀🤖🚀🤖
                * T
                * Notes
                * Text
                * endAct🚀🤖
                * */
                FileWriter writer = new FileWriter(activity);
                // Writes the activity name to the file
                writer.append(((EditText) findViewById(R.id.activityName)).getText().toString());
                // We have to write a line separator / n every time we make a new line you know better
                writer.append(System.lineSeparator());
                // Counters for tracking how many things we have written
                int t = 0;
                int s = 0;
                int n = 0;
                // Param size should be the same size as the number of view, and we want all views to exist so that is why we add paramtype many views
                for (int i = 0; i < paramType.size(); i++) {
                    // Lets check which paramtype it is
                    if (Objects.equals(paramType.get(i), "T")) {
                        // We first write T
                        writer.append("T");
                        writer.append(System.lineSeparator());
                        // Then we write the parameter name
                        writer.append(paramName.get(i));
                        writer.append(System.lineSeparator());
                        // then the type of config selected
                        // If you notice the t is for counting the number of config we need to add else we would not know what to add
                        writer.append(textConfig.get(t));
                        writer.append(System.lineSeparator());
                        // We increase the counter
                        t++;
                    } else if (Objects.equals(paramType.get(i), "S")) {
                        writer.append("S");
                        writer.append(System.lineSeparator());
                        writer.append(paramName.get(i));
                        writer.append(System.lineSeparator());
                        // we have to write the arraylist stuff which is as long as the spinner config number
                        for (int ii = 0; ii<spinnerConfig.get(s); ii++) {
                            // We write the stuff from the sheet
                            writer.append(spinnerConfigStuff.getSheetAt(0).getRow(s).getCell(ii).toString());
                            writer.append(System.lineSeparator());
                        }
                        // we end the spinner to remind the app that this is the end of life.. I mean the spinner
                        writer.append("endSpinner🚀🤖🚀🤖");
                        writer.append(System.lineSeparator());
                        s++;
                    } else if (Objects.equals(paramType.get(i), "N")) {
                        writer.append("N");
                        writer.append(System.lineSeparator());
                        writer.append(paramName.get(i));
                        writer.append(System.lineSeparator());
                        // We first write min config and then max config
                        writer.append(minConfig.get(n));
                        writer.append(System.lineSeparator());
                        writer.append(maxConfig.get(n));
                        writer.append(System.lineSeparator());
                        t++;
                    }
                }
                // now we have to end the activity
                writer.append("endAct🚀🤖");
                // Now we flush the toil.. I mean the file I still D K what that is for I am guessing to stop input into the text?!?!?!?
                writer.flush();
                // Then we close the file, which means it has been exported
                writer.close();
            }catch (IOException e){
                // You are toast!!
                Toast.makeText(getApplicationContext(), "Fail try it again", Toast.LENGTH_LONG).show();
            }
        } else {
            // If you got too engrossed ha ha !
            // Here is the intent to the view that displays this stuff and makes the excel table
            Intent myIntent = new Intent(DIY.this,
                    Custom.class);
            startActivity(myIntent);
        }

    }
}