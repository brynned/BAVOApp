package com.math497.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences("USER_DATA", MODE_PRIVATE);;

        String firstName = sharedPref.getString("FIRST_NAME","");
        String lastName = sharedPref.getString("LAST_NAME","");
        String major = sharedPref.getString("MAJOR","");
        String classYear = sharedPref.getString("CLASS_YEAR","");
        String email = sharedPref.getString("EMAIL","");

        TextView firstView = (TextView)findViewById(R.id.first_name_text);
        TextView lastView = (TextView)findViewById(R.id.last_name_text);
        TextView majorView = (TextView)findViewById(R.id.major_text);
        TextView yearView = (TextView)findViewById(R.id.class_year_text);
        TextView emailView = (TextView)findViewById(R.id.email_text);

        firstView.setText(firstName);
        lastView.setText(lastName);
        majorView.setText(major);
        yearView.setText(classYear);
        emailView.setText(email);
    }

    public void updateData(View view) {
        Intent intent = new Intent(this, EntryActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

        //System.exit(0);
    }
}
