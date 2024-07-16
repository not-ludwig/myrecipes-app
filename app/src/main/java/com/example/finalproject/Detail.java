package com.example.finalproject;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity {
    TextView recipeNametxt, recipeDescriptiontxt;
    String recipeName, recipeDesc, recipeImg, currentUser;
    DBHelper dbHelper;
    Button btn_delete, btn_edit;
    AlertDialog.Builder dialog;
    ImageView recipeImgV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        recipeNametxt = findViewById(R.id.recipe_name);
        recipeDescriptiontxt = findViewById(R.id.recipe_description);
        recipeImgV = findViewById(R.id.recipe_detail_img);
        btn_delete = findViewById(R.id.btn_delete);
        btn_edit = findViewById(R.id.btn_edit);
        Intent intent = getIntent();
        recipeName = intent.getStringExtra("currentRecipe");
        recipeDesc = intent.getStringExtra("currentRecipeDescription");
        recipeImg = intent.getStringExtra("currentRecipeImg");
        currentUser = intent.getStringExtra("currentUser");
        dbHelper = new DBHelper(this);
        dialog = new AlertDialog.Builder(this)
                .setTitle("Estas seguro?")
                .setMessage("Estas seguro que quieres eliminar esta receta?")
                .setNegativeButton("Si", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int
                                    i) {
                                dbHelper.delete_recipe(recipeDesc);
                                Toast.makeText(Detail.this, "Eliminado con exito!",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                .setPositiveButton("No", new
                        DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int
                                    i) {
                                dialogInterface.dismiss();
                            }
                        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.create();
                dialog.show();
            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Detail.this, Edit.class);
                intent1.putExtra("oldName", recipeName);
                intent1.putExtra("oldDesc", recipeDesc);
                intent1.putExtra("oldURL", recipeImg);
                intent1.putExtra("currentUser", currentUser);
                startActivity(intent1);
            }
        });
        Picasso.get().load(recipeImg).resize(200, 200).into(recipeImgV);
        recipeNametxt.setText(recipeName);
        recipeDescriptiontxt.setText(recipeDesc);
    }
}