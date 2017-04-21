package com.math497.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Sam on 4/7/2017.
 */

public class EntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        SharedPreferences sharedPref = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        Boolean firstTime = sharedPref.getBoolean("FIRST_TIME", true);

        if (!firstTime) {
            goToMain();
        }

    }

    public void updateData(View view) {
        String FILENAME = "user_data";

        EditText firstNameText = (EditText) findViewById(R.id.first_name_text);
        EditText lastNameText = (EditText) findViewById(R.id.last_name_text);
        EditText majorText = (EditText) findViewById(R.id.major_text);
        EditText classYearText = (EditText) findViewById(R.id.class_year_text);
        EditText emailText = (EditText) findViewById(R.id.email_text);

        String firstMessage = firstNameText.getText().toString();
        String lastMessage = lastNameText.getText().toString();
        String majorMessage = majorText.getText().toString();
        String yearMessage = classYearText.getText().toString();
        String emailMessage = emailText.getText().toString();

        if (firstMessage.length()<1) {
            firstNameError();
        } else if (lastMessage.length()<1) {
            lastNameError();
        } else if (majorMessage.length()<1) {
            majorError();
        } else if (yearMessage.length()!=4) {
            classYearError();
        } else if (emailMessage.length()<16) {
            emailError();
        } else {
            updatePreferences(firstMessage, lastMessage, majorMessage, yearMessage, emailMessage);
            goToMain();
        }
    }

    public void firstNameError() {
        Context context = getApplicationContext();
        CharSequence text = "Please enter your first name";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void lastNameError() {
        Context context = getApplicationContext();
        CharSequence text = "Please enter your last name";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void majorError() {
        Context context = getApplicationContext();
        CharSequence text = "Please enter your major";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void classYearError() {
        Context context = getApplicationContext();
        CharSequence text = "Please enter a valid graduation year";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void emailError() {
        Context context = getApplicationContext();
        CharSequence text = "Please enter a valid Saint Mary's College E-mail";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void updatePreferences(String firstMessage, String lastMessage, String majorMessage, String yearMessage, String emailMessage) {
        SharedPreferences sharedPref = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(getString(R.string.first_name_key), firstMessage);
        editor.putString(getString(R.string.last_name_key), lastMessage);
        editor.putString(getString(R.string.major_key), majorMessage);
        editor.putString(getString(R.string.class_year_key), yearMessage);
        editor.putString(getString(R.string.email_key), emailMessage);
        editor.putBoolean(getString(R.string.first_time), false);
        editor.commit();
    }

    public void goToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
