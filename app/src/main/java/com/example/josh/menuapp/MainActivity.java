package com.example.josh.menuapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public EditText stuname;
    public EditText stuID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stuname = findViewById(R.id.NameInput);
        stuID = findViewById(R.id.IDinput);
    }

    public void cafePressed(View v){
        Intent i = new Intent(this, cafeActivity.class);
        startActivity(i);
    }

    public void subPressed(View v){
        String in1 = stuname.getText().toString();
        String in2 = stuID.getText().toString();
        if(TextUtils.isEmpty(in1) || TextUtils.isEmpty(in2)){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Error");
            builder.setMessage("Must enter Name and ID");
            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialog1 = builder.create();
            dialog1.show();
        }else {
            String[] bundle = {stuname.getText().toString(), stuID.getText().toString()};
            Intent i = new Intent(this, subActivity.class);
            i.putExtra("info", bundle);
            startActivity(i);
        }
    }

    public void NApressed(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Sorry");
        builder.setMessage("Sorry this menu is not available");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }
}
