package com.example.a521internal_files;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                TextView editTextLogin = findViewById(R.id.editTextLogin);
                TextView editTextPassword = findViewById(R.id.editTextPassword);
                CheckBox checkBox = findViewById(R.id.checkBox);
                String editTextLoginToString = editTextLogin.getText().toString();
                String editTextPasswordToString = editTextPassword.getText().toString();

                if(editTextLoginToString.isEmpty() || editTextPasswordToString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Не заполнены поля логин-пароль", Toast.LENGTH_LONG).show();
                    return;
                }

                String login = "";
                String password = "";

                if (checkBox.isChecked()) {
                    try {
                        BufferedReader loginreader = new BufferedReader(new InputStreamReader(openFileInput("login.txt")));
                        BufferedReader passwordreader = new BufferedReader(new InputStreamReader(openFileInput("password.txt")));
                        login = loginreader.readLine();
                        password = passwordreader.readLine();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        InputStreamReader inputStreamReader = new InputStreamReader(openFileInput("loginpassword.txt"));
                        BufferedReader reader = new BufferedReader(inputStreamReader);
                        login = reader.readLine();
                        password = reader.readLine();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (editTextLoginToString.equals(login) && editTextPasswordToString.equals(password)) {
                    Toast.makeText(MainActivity.this, "Login-password is correct\n" + login + " " + password, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Некорректная пара Login-password\n" + "Вы ввели логин " + editTextLoginToString + " а надо " + login + "\n" + "Вы ввели пароль " + editTextPasswordToString + " а надо " + password, Toast.LENGTH_LONG).show();
                }

            }

        });


        Button buttonRegistration = findViewById(R.id.buttonRegistration);
        buttonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView editTextLogin = findViewById(R.id.editTextLogin);
                TextView editTextPassword = findViewById(R.id.editTextPassword);
                CheckBox checkBox = findViewById(R.id.checkBox);

                String login = editTextLogin.getText().toString();
                String password = editTextPassword.getText().toString();

                if(login.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Не заполнены поля логин-пароль", Toast.LENGTH_LONG).show();
                    return;
                }

                if (checkBox.isChecked()) {
                    String fileContents = login + "\n" + password;
                    try {
                        FileOutputStream outputStreamlogin = openFileOutput("login.txt", Context.MODE_PRIVATE);
                        outputStreamlogin.write(login.getBytes());
                        outputStreamlogin.close();
                        FileOutputStream outputStreampassword = openFileOutput("password.txt", Context.MODE_PRIVATE);
                        outputStreampassword.write(password.getBytes());
                        outputStreampassword.close();
                        Toast.makeText(MainActivity.this, "Вы зарегистрированы с логин паролем: " + login +" " + password, Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    String fileContents = login + "\n" + password;
                    try {
                        FileOutputStream outputStream = openFileOutput("loginpassword.txt", Context.MODE_PRIVATE);
                        outputStream.write(fileContents.getBytes());
                        outputStream.close();
                        Toast.makeText(MainActivity.this, "Вы зарегистрированы с логин паролем: " + login +" " + password, Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });


    }
}
