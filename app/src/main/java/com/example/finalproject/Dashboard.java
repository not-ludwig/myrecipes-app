package com.example.finalproject;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    DBHelper dbHelper;
    String currentUser, currentRecipeName, currentRecipeDesc,
            currentRecipeImg;
    ArrayList recipes = new ArrayList<Recipe>();
    RecyclerView recyclerView;
    FloatingActionButton add_btn_widget;
    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        add_btn_widget = findViewById(R.id.add_btn_widget);
        Button close = findViewById(R.id.close);
        Intent intentEmailReceive = getIntent();
        currentUser = intentEmailReceive.getStringExtra("useremail");
        dbHelper = new DBHelper(this);
        recyclerView = findViewById(R.id.recycler);
        add_btn_widget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, AddRecipe.class);
                intent.putExtra("logged", currentUser);
                startActivity(intent);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Login.class);
                startActivity(intent);
            }
        });
        RecipeListAdapter adapter = new RecipeListAdapter(recipes);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRecipeName =
                        recipes.get(recyclerView.getChildAdapterPosition(view)).toString();
                currentRecipeDesc =
                        dbHelper.getDescription(currentRecipeName);
                currentRecipeImg = dbHelper.getImageURL(currentRecipeName);
                Toast.makeText(Dashboard.this, "Seleccionaste: " +
                        currentRecipeName, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Dashboard.this, Detail.class);
                intent.putExtra("currentRecipe", currentRecipeName);
                intent.putExtra("currentRecipeDescription",
                        currentRecipeDesc);
                intent.putExtra("currentRecipeImg", currentRecipeImg);
                intent.putExtra("currentUser", currentUser);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        get_user_recipes();
    }
    private void get_user_recipes() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        int currentUserId = dbHelper.getId(currentUser);
        Cursor cursor = database.rawQuery("SELECT recipe_name, description, image_url, user_id_onrecipe FROM recipe JOIN user ON user_id=user_id_onrecipe WHERE user_id_onrecipe='"+currentUserId+"'", null);
        while(cursor.moveToNext()){
            recipes.add(new Recipe(cursor.getString(0),cursor.getString(1),
                    cursor.getString(2), cursor.getInt(3)));
        }
    }
}