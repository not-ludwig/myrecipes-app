package com.example.finalproject;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class AddRecipe extends AppCompatActivity {
    String currentEmail;
    EditText recipe_name, recipe, recipe_img;
    Button btn_add_recipe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        Intent intent = getIntent();
        currentEmail = intent.getStringExtra("logged");
        recipe_name = findViewById(R.id.edt_recipe_name);
        recipe = findViewById(R.id.edt_actual_recipe);
        recipe_img = findViewById(R.id.edt_recipe_img_url);
        btn_add_recipe = findViewById(R.id.btn_add_recipe);
        DBHelper dbHelper = new DBHelper(this);
        btn_add_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = dbHelper.getId(currentEmail);
                String name = recipe_name.getText().toString();
                String desc = recipe.getText().toString();
                String link = recipe_img.getText().toString();
                Recipe recipe= new Recipe(name, desc, link, id);
                dbHelper.add_recipe(recipe);
                Toast.makeText(AddRecipe.this, "Receta agregada con EXITO!",
                        Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
