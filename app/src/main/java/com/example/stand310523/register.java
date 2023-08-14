package com.example.stand310523;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {

    EditText spiritualName, email, password, repassword;
    Button register;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView btn=findViewById(R.id.useerGoToLogin);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(register.this,login.class));
            }
        });

        spiritualName = findViewById(R.id.spiritualNameInput);
        email = findViewById(R.id.userEmailInputLoggin);
        password = findViewById(R.id.userPasswordInputLoggin);
        repassword = findViewById(R.id.userRepeatPasswordInput);
        register = findViewById(R.id.userRegisterbutton);

        DB = new DBHelper(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = spiritualName.getText().toString();
                String userEmail = email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (name.equals("") || userEmail.equals("") || pass.equals("") || repass.equals("")) {
                    Toast.makeText(register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }else if (!isValidEmail(userEmail)) {
                    Toast.makeText(register.this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(repass)) {
                        boolean checkSpiritualName = DB.checkSpiritualName(name);
                        if (!checkSpiritualName) {
                            boolean emailExists = DB.checkEmailExists(userEmail);
                            if (!emailExists) {
                                boolean insert = DB.insertData(name, userEmail, pass);
                                if (insert) {
                                    Toast.makeText(register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), login.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(register.this, "Email already exists! Please Login in", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(register.this, "User already exists! Change Spiritual Name", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(register.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            private boolean isValidEmail(String email) {
                // Use a regular expression to check for a valid email format
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                return email.matches(emailPattern);
            }

        });

    }
}
