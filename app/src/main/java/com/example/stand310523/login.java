package com.example.stand310523;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;




public class login extends AppCompatActivity {
    EditText spiritualName,email, password;
    Button btnlogin;
    DBHelper DB;
    public static String spiritualNametosend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        TextView btn = findViewById(R.id.userSignup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, register.class));
            }
        });


        spiritualName = findViewById(R.id.userSpiritualNameLoggin);
        email = findViewById(R.id.userEmailInputLoggin);
        password = findViewById(R.id.userPasswordInputLoggin);
        btnlogin = findViewById(R.id.btnuserLogin);

        DB = new DBHelper(this);
        //DB.deleteAllUsers();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = spiritualName.getText().toString();
                String pass = password.getText().toString();
                String userEmail = email.getText().toString();

                if (name.equals("") || pass.equals("") || userEmail.equals("")) {
                    Toast.makeText(login.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean checkSpiritualNameEmailPassword = DB.checkSpiritualNameEmailPassword(name, userEmail, pass);
                    if (checkSpiritualNameEmailPassword) {
                        Toast.makeText(login.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                         spiritualNametosend = String.valueOf(spiritualName);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else {
                        String errorMessage = "Invalid Credentials";
                        if (!DB.checkSpiritualName(name)) {
                            errorMessage = "Invalid Spiritual Name";
                        } else if (!DB.checkEmailExists(userEmail)) {
                            errorMessage = "Invalid Email";
                        } else if (!DB.checkPassword(name, pass)) {
                            errorMessage = "Invalid Password";
                        }
                        Toast.makeText(login.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }
             }

            });

        TextView forgotPasswordTextView = findViewById(R.id.userForgotpassword);
        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(login.this);
                builder.setMessage("Please contact the administrator at admistrator@hwrstudent");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }
}