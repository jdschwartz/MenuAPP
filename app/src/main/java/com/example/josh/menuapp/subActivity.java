package com.example.josh.menuapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

public class subActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] bundle;
    Spinner breadSpin;
    Spinner sizeSpin;
    String[] breads = {"Italian", "Flat", "Wheat", "Herb"};
    String[] sizes = {"Six-inch", "Twelve-inch"};

    public String StuName;
    public String StuID;

    double itemCost = 0;
    double totalCost = 0;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_activity);

        breadSpin =findViewById(R.id.breadspinner);
        sizeSpin = findViewById(R.id.sizespinner);
        ArrayAdapter<String>adapter1 = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,breads);
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        breadSpin.setAdapter(adapter1);
        breadSpin.setOnItemSelectedListener(this);
        ArrayAdapter<String>adapter2 = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,sizes);
        sizeSpin.setAdapter(adapter2);
        sizeSpin.setOnItemSelectedListener(this);
        Bundle info = getIntent().getExtras();
        if(info != null){
            bundle = info.getStringArray("info");
            StuName = bundle[0];
            StuID = bundle[1];

        }

    }

    public void RTMsub (View v){
        finish();
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id){

        if(parent == breadSpin){
            switch (position) {
                case 0:
                    itemCost += 0.50;
                    break;

                case 1:
                    itemCost += 0.75;
                    break;

                case 2:
                    itemCost += 1.00;
                    break;

                case 3:
                    itemCost += 1.10;
                    break;

                case 4:
                    itemCost += 2.50;
                    break;

              }
        }else if(parent == sizeSpin){
            switch (position){
                case 0:
                    itemCost += 3.00;
                    break;

                case 1:
                    itemCost += 5.00;
                    break;
            }
        }
    }

    public void onNothingSelected(AdapterView<?> parent){

    }

    public void onCheckboxClicked(View v){
        boolean checked = ((CheckBox) v).isChecked();

        switch (v.getId()){
            case R.id.Turkey:
                if(checked){
                    itemCost += 0.75;
                }
                break;

            case R.id.Ham:
                if (checked){
                    itemCost += 0.95;
                }
                break;

            case R.id.Chicken:
                if(checked){
                    itemCost += 0.85;
                }
                break;

            case R.id.ChickenStrip:
                if(checked){
                    itemCost += 0.80;
                }
                break;

            case R.id.Mustard:
                if(checked){
                    itemCost += 0.25;
                }
                break;

            case R.id.Mayo:
                if(checked){
                    itemCost += 0.25;
                }
                break;

            case R.id.Italian:
                if (checked){
                    itemCost += 0.35;
                }
                break;

            case R.id.BBQ:
                if (checked){
                    itemCost +=0.25;
                }
                break;

            case R.id.American:
                if (checked){
                    itemCost += 0.45;
                }
                break;

            case R.id.Swiss:
                if(checked){
                    itemCost += 0.55;
                }
                break;

            case R.id.PepperJack:
                if (checked){
                    itemCost += 0.50;
                }
                break;

            case R.id.Mozzarella:
                if (checked){
                    itemCost += 0.60;
                }

            case R.id.Lettuce:
                if(checked){
                    itemCost += 0.15;
                }
                break;

            case R.id.Bacon:
                if(checked){
                    itemCost += 0.20;
                }
                break;

            case R.id.Peppers:
                if(checked){
                    itemCost += 0.30;
                }
                break;

            case R.id.Onions:
                if (checked){
                    itemCost += 0.10;
                }
                break;

        }
    }

    public void AIsub(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(subActivity.this);
        builder.setTitle("Add item?");
        String dCost = Double.toString(itemCost);
        String.format(dCost, "%.2f");
        builder.setMessage("Item cost: $" + dCost);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                totalCost += itemCost;
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                totalCost +=0;
            }
        });

        AlertDialog dialog1 = builder.create();
        dialog1.show();

    }

    public void COsub (View v){

        AlertDialog.Builder builder2 = new AlertDialog.Builder(subActivity.this);
        builder2.setTitle("Confirm Order?");
        String dCost2 = Double.toString(totalCost);
        String.format(dCost2, "%.2f");

        builder2.setMessage("Total Cost: $" + dCost2);
        builder2.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String TC = String.valueOf(totalCost);
                String[] info = {StuName, StuID, TC, "Subway"};
                Intent i = new Intent(subActivity.this,payActivity.class);
                i.putExtra("info",info);
                startActivity(i);
            }
        });
        builder2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog2 = builder2.create();
        dialog2.show();
    }
}
