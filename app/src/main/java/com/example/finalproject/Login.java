package com.example.finalproject;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    String currentUser;
    Button btn_register, btn_login;
    EditText edt_email, edt_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_register = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_in);
        DBHelper dbHelper = new DBHelper(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_email.getText().toString();
                currentUser = email;
                String password = edt_password.getText().toString();
                if (email.equals("") || password.equals("")){
                    Toast.makeText(Login.this, "Rellena todos los campos!",
                            Toast.LENGTH_SHORT).show();
                } else{
                    Boolean check_data = dbHelper.check_email_password(email,
                            password);
                    if (check_data == true){
                        Toast.makeText(Login.this, "Inicio sesion correctamente!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),
                                Dashboard.class);
                        intent.putExtra("useremail", currentUser);
                        startActivity(intent);
                    } else{
                        Toast.makeText(Login.this, "Credenciales invalidas!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}