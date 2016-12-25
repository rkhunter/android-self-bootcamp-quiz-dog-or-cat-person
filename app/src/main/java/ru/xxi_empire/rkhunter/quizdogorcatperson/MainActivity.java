package ru.xxi_empire.rkhunter.quizdogorcatperson;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private RadioGroup canineRadioGroup;
    private RadioButton canineRadioButton;
    private SeekBar seekBar;
    private TextView seekBarTextView;
    private CheckBox cutestCheckBoxDog, cutestCheckBoxCat, cutestCheckBoxParrot;
    private RadioGroup droolRadioGroup;
    private RadioButton droolRadioButton;
    private Button showResultsButton;
    private int dogCounter, catCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUp();

        seekBar                 = (SeekBar) findViewById(R.id.seekBarFeline);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        seekBarTextView.setText("Comfortableness: " + i + "/" + seekBar.getMax());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }

    private void setUp() {
        dogCounter              = 0;
        catCounter              = 0;

        canineRadioGroup        = (RadioGroup)  findViewById(R.id.radioGroupCanine);
        droolRadioGroup         = (RadioGroup)  findViewById(R.id.radioGroupDrool);
        seekBarTextView         = (TextView)    findViewById(R.id.seekBarProgressTextView);

        // check boxes
        cutestCheckBoxDog       = (CheckBox)    findViewById(R.id.checkboxCutestDog);
        cutestCheckBoxCat       = (CheckBox)    findViewById(R.id.checkboxCutestCat);
        cutestCheckBoxParrot    = (CheckBox)    findViewById(R.id.checkboxCutestParrot);

        showResultsButton       = (Button)      findViewById(R.id.showResults);
        showResultsButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // call methods
                        processDrool(droolRadioGroup);
                        processCanines(canineRadioGroup);
                        processCutest(cutestCheckBoxDog, cutestCheckBoxCat, cutestCheckBoxParrot);
                        Intent intent  = new Intent(MainActivity.this, ResultActivity.class);
                        intent.putExtra("catCounter", catCounter);
                        intent.putExtra("dogCounter", dogCounter);
                        startActivity(intent);

                    }
                }
        );
    }

    private void processCutest(CheckBox dog, CheckBox cat, CheckBox parrot) {
        if (dog.isChecked() && !cat.isChecked() && !parrot.isChecked()) {
            dogCounter += 5;
        } else if (cat.isChecked() && !dog.isChecked() && !parrot.isChecked()) {
            catCounter += 5;
        }
    }

    private void processDrool(final RadioGroup radioGroup) {
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        int radioId = radioGroup.getCheckedRadioButtonId();

                        droolRadioButton = (RadioButton) findViewById(radioId);

                        if (droolRadioButton.getText().equals("Yes")) {
                            dogCounter += 5;
                        }
                    }
                }
        );
    }

    private void processCanines(final RadioGroup radioGroup) {
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        int radioId = radioGroup.getCheckedRadioButtonId();

                        canineRadioButton = (RadioButton) findViewById(radioId);

                        if (canineRadioButton.getText().equals("No")) {
                            dogCounter += 5;
                        }
                    }
                }
        );
    }
}
