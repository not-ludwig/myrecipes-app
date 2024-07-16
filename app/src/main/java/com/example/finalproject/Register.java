package com.example.finalproject;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Register extends AppCompatActivity {
    Button btn_registrar_r;
    EditText edt_nombre, edt_email, edt_password, edt_password_again;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_registrar_r = findViewById(R.id.btn_registrar);
        edt_nombre = findViewById(R.id.edt_nombre);
        edt_email = findViewById(R.id.edt_email_r);
        edt_password = findViewById(R.id.edt_password_r);
        edt_password_again = findViewById(R.id.edt_password_again);
        DBHelper dbHelper = new DBHelper(this);
        btn_registrar_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = edt_nombre.getText().toString();
                String email = edt_email.getText().toString();
                String password = edt_password.getText().toString();
                String password_again = edt_password_again.getText().toString();
                User newUser = new User(nombre, email, password);
                if(email.equals("") || password.equals("") ||
                        password_again.equals("")){
                    Toast.makeText(Register.this, "Rellena todos los campos!", Toast.LENGTH_SHORT).show();
                }else{
                    if (password.equals(password_again)){
                        Boolean check_email = dbHelper.check_email(email);
                        if(check_email == false){
                            Boolean insert = dbHelper.register_user(newUser);
                            if(insert == true){
                                Toast.makeText(Register.this, "Registrado Correctamente!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(Register.this, "No se pudo registrar!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(Register.this, "Este usuario ya EXISTE!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(Register.this, "Contraseña invalida, revise!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}