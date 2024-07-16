package com.example.finalproject;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    // SETUP
    String UserTable = "CREATE TABLE user(user_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, password TEXT);";
    String RecipeTable = "CREATE TABLE recipe(recipe_id INTEGER PRIMARY KEY AUTOINCREMENT, recipe_name TEXT, description TEXT, image_url TEXT, user_id_onrecipe INTEGER);";
    private static DBHelper sqLiteManager;
    private static int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Recipes";
    // USER
    private static final String TABLE_NAME_USER = "user";
    private static final String ID_FIELD_USER = "id_user";
    private static final String NAME_FIELD_USER = "name";
    private static final String EMAIL_FIELD_USER = "email";
    private static final String PASSWORD_FIELD_USER = "password";
    // RECIPE
    private static final String TABLE_NAME_RECIPE = "recipe";
    private static final String ID_FIELD_RECIPE = "id_recipe";
    private static final String NAME_FIELD_RECIPE = "recipe_name";
    private static final String DESCRIPTION_FIELD_RECIPE = "description";
    private static final String IMAGEURL_FIELD_RECIPE = "image_url";
    private static final String USERID_FIELD_RECIPE = "user_id_onrecipe";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static DBHelper instanceOfDatabase(Context context){
        if (sqLiteManager == null){
            sqLiteManager = new DBHelper(context);
        }
        return sqLiteManager;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(UserTable);
        sqLiteDatabase.execSQL(RecipeTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS user");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS recipe");
    }
    public Boolean register_user(User user){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_FIELD_USER, user.getName());
        contentValues.put(EMAIL_FIELD_USER, user.getEmail());
        contentValues.put(PASSWORD_FIELD_USER, user.getPassword());
        long result = sqLiteDatabase.insert(TABLE_NAME_USER, null, contentValues);
        if(result == -1){
            return false;
        } else{
            return true;
        }
    }
    public Boolean check_email(String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from user where email = ?", new String[]{email});
        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
    public Boolean check_email_password(String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from user where email = ? and password =?", new String[]{email, password});
        if (cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
    public void add_recipe(Recipe recipe){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_FIELD_RECIPE, recipe.getName());
        contentValues.put(DESCRIPTION_FIELD_RECIPE, recipe.getDescription());
        contentValues.put(IMAGEURL_FIELD_RECIPE, recipe.getImage_url());
        contentValues.put(USERID_FIELD_RECIPE, recipe.getUser_id());
        sqLiteDatabase.insert(TABLE_NAME_RECIPE, null, contentValues);
    }
    public void delete_recipe(String recipeDesc){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("recipe", DESCRIPTION_FIELD_RECIPE + " =?", new
                String[]{String.valueOf(recipeDesc)});
    }
    public void update_recipe(int recipe_id, String name, String desc, String
            imgURL){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_FIELD_RECIPE, name);
        contentValues.put(DESCRIPTION_FIELD_RECIPE, desc);
        contentValues.put(IMAGEURL_FIELD_RECIPE, imgURL);
        sqLiteDatabase.update("recipe", contentValues, "recipe_id" + " =?",
                new String[]{String.valueOf(recipe_id)});
    }
    public int get_recipe_id(int user_id, String recipe_name){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("Select recipe_id from recipe Where user_id_onrecipe='"+user_id+"' and recipe_name ='"+recipe_name+"'",null, null);
        c.moveToNext();
        int id = c.getInt(0);
        return id;
    }
    public int getId(String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("Select user_id from user Where email='"+email+"'",null, null);
        c.moveToNext();
        int id = c.getInt(0);
        return id;
    }
    public String getDescription(String recipeName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select description from recipe where recipe_name='"+recipeName+"'",null, null);
        c.moveToNext();
        String description = c.getString(0);
        return description;
    }
    public String getImageURL(String recipeName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("select image_url from recipe where recipe_name='"+recipeName+"'",null, null);
        c.moveToNext();
        String image_url = c.getString(0);
        return image_url;
    }
}
