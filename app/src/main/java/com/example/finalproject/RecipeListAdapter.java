package com.example.finalproject;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class RecipeListAdapter extends
        RecyclerView.Adapter<RecipeListAdapter.ViewHolder> implements
        View.OnClickListener {
    private View.OnClickListener listener;
    ArrayList<Recipe> lst_recipe;
    public RecipeListAdapter(ArrayList<Recipe> lst_recipe) {
        this.lst_recipe = lst_recipe;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_model, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.asign_data(lst_recipe.get(position));
    }
    @Override
    public int getItemCount() {
        return lst_recipe.size();
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        if(listener != null){
            listener.onClick(view);
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipe_name;
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView2);
            recipe_name = itemView.findViewById(R.id.textViewNombreReceta);
        }
        public void asign_data(Recipe recipe) {
            Picasso.get().load(recipe.getImage_url()).into(img);
            recipe_name.setText(recipe.getName());
        }
    }
}