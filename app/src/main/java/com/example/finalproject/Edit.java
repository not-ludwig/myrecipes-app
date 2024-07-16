package com.example.finalproject;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit extends AppCompatActivity {
    Button confirm;
    String currentUser;
    DBHelper dbHelper;
    EditText new_name, new_desc, new_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        String currentName = intent.getStringExtra("oldName");
        new_name = findViewById(R.id.new_name);
        new_name.setText(currentName);
        String currentDesc = intent.getStringExtra("oldDesc");
        new_desc = findViewById(R.id.new_desc);
        new_desc.setText(currentDesc);
        String currentURL = intent.getStringExtra("oldURL");
        new_url = findViewById(R.id.new_url);
        new_url.setText(currentURL);
        currentUser = intent.getStringExtra("currentUser");
        confirm = findViewById(R.id.btn_editar_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int user_id = dbHelper.getId(currentUser);
                int recipe_id = dbHelper.get_recipe_id(user_id, currentName);
                dbHelper.update_recipe(recipe_id,
                        new_name.getText().toString(), new_desc.getText().toString(),
                        new_url.getText().toString());
                Toast.makeText(Edit.this, "Editado con EXITO!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}