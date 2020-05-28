package com.iitbhu.spardha2019.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.iitbhu.spardha2019.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FeedbackActivity extends AppCompatActivity {

    EditText edtfb;
    Button btnsubmit;
    LinearLayout llback;
    RadioGroup radioGroup;
    RadioButton radioButton;

    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        edtfb = findViewById(R.id.edtfb);
        btnsubmit = findViewById(R.id.btnsubmit);
        llback = findViewById(R.id.llback);
        radioGroup = findViewById(R.id.radiogroup);




        llback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

         database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference();

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtfb.getText().toString().equals(""))
                {
                    Toast.makeText(FeedbackActivity.this, "Feedback is empty !", Toast.LENGTH_SHORT).show();
                }
                else {
                    //databaseReference.child("feedbacks").push().setValue(edtfb.getText().toString());
                    int selectedId = radioGroup.getCheckedRadioButtonId();

                    radioButton = findViewById(selectedId);
                    String feedbacktype = radioButton.getText().toString();

                    String s = databaseReference.child("feedbacks").child(feedbacktype).push().getKey();

                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String formattedDate = df.format(c);

                    databaseReference.child("feedbacks").child(feedbacktype).child(s).child("date").setValue(formattedDate);
                    databaseReference.child("feedbacks").child(feedbacktype).child(s).child("feedback").setValue(edtfb.getText().toString());
                    Toast.makeText(FeedbackActivity.this, "Thank you for submitting your feedback !", Toast.LENGTH_SHORT).show();
                    finish();
                }

                //databaseReference.child("1").setValue(edtfb.getText().toString());
            }
        });

    }
}
