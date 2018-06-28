package com.wilsonundrix.healthcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard_Doctor extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard__doctor);

        Button appDoc = findViewById(R.id.btnAppDoc);
        appDoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.wilsonundrix.healthcare.Appointment_Doctor");
                startActivity(intent);
            }
        });

    }
}
