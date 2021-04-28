package com.forbitbd.diploma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class Cgpacalculator extends AppCompatActivity implements View.OnClickListener {

    private EditText et_first,et_second,et_third,et_forth,et_fifth,et_sixth,et_seventh,et_eight;

    private TextView result;

    private MaterialButton GetGPA;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpacalculator);



        et_first = findViewById(R.id.first);
        et_second = findViewById(R.id.second);
        et_third = findViewById(R.id.third);
        et_forth = findViewById(R.id.forth);
        et_fifth = findViewById(R.id.fifth);
        et_sixth = findViewById(R.id.sixth);
        et_seventh = findViewById(R.id.seventh);
        et_eight = findViewById(R.id.eighth);

        result = findViewById(R.id.cgpa);

        GetGPA = findViewById(R.id.btn_get);
        GetGPA.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {


            Double first_semister = Double.parseDouble(et_first.getText().toString().trim());

                Double first =(first_semister) * 5;
                Log.d("fffffff", "onClick: "+first);









//
        Log.d("fffffff", "onClick: "+first);



            Double second_semister = Double.parseDouble(et_second.getText().toString().trim());

            Double second =(second_semister ) * 5;




            Double third_semister= Double.parseDouble(et_third.getText().toString().trim());

            Double third = (third_semister ) * 5;





            Double forth_semister = Double.parseDouble(et_forth.getText().toString().trim());

            Double forth = (forth_semister ) * 10;


           Double fifth_semister = Double.parseDouble(et_fifth.getText().toString().trim());

           Double fifth = (fifth_semister ) * 15;






        Double sixth_semister = Double.parseDouble(et_sixth.getText().toString().trim());

        Double sixth = (sixth_semister ) * 20;

        Double seventh_semister = Double.parseDouble(et_seventh.getText().toString().trim());

        Double seventh = (seventh_semister ) * 25;

        Double eight_semister = Double.parseDouble(et_eight.getText().toString().trim());

        Double eight = (eight_semister ) * 15;


        Double sum = (first+second+third+forth+fifth+sixth+seventh+eight)/100;

        Toast.makeText(this, "your CGPA is " +sum, Toast.LENGTH_SHORT).show();


        String final_sum =  new Double(sum).toString();
        result.setText(final_sum);




    }
}