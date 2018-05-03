package com.example.josh.menuapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class payActivity extends AppCompatActivity{

    String[] bundle;
    TextView Rname;
    TextView StuName;

    private SeekBar tipPercent;
    private TextView tipNum;
    private TextView totalPrice;
    private EditText StuID;
    private EditText StuPIN;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_activity);
        Rname = findViewById(R.id.Restaurant_name);
        StuName = findViewById(R.id.Student_Name);
        tipPercent = findViewById(R.id.TipBar);
        tipNum = findViewById(R.id.tipNum);
        totalPrice = findViewById(R.id.Total_Cost);
        StuID = findViewById(R.id.Student_ID);
        StuPIN = findViewById(R.id.Student_PIN);

        tipPercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tipNum.setText(i+"");
                TCost(seekBar);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        Bundle info = getIntent().getExtras();
        if(info != null){
            bundle = info.getStringArray("info");
            Rname.setText(bundle[3]);
            StuName.setText(bundle[0]);

        }

    }

    public void TCost(View v){
        double bill = Double.parseDouble(bundle[2]);
        double percent = tipPercent.getProgress();
        double result;
        double btotal;
        result = (bill*(percent/100));
        btotal = bill + result;

        totalPrice.setText(String.format("%.2f",btotal));

    }

    public void COPay(View v){
        Intent i = new Intent(payActivity.this,MainActivity.class);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
        finish();
    }

    public void RTMpay(View v){
        finish();
    }

    public void CPButton(View v){
        String input1 = StuID.getText().toString();
        double stuID = Double.parseDouble(input1);
        String input2 = StuPIN.getText().toString();
        if(stuID != Double.parseDouble(bundle[1]) || input2 == null){
            AlertDialog.Builder builder = new AlertDialog.Builder(payActivity.this);
            builder.setTitle("Error");
            builder.setMessage("Please ensure ID and PIN are entered correctly");
            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialog1 = builder.create();
            dialog1.show();
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(payActivity.this);
            builder.setTitle("Order Complete");
            builder.setMessage("Your order will be delivered shortly");
            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(payActivity.this,MainActivity.class);

                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(i);
                    finish();
                }
            });

            AlertDialog dialog1 = builder.create();
            dialog1.show();
        }
    }

}
