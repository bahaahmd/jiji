package Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.R;
import com.example.project2.Search_item;

import java.util.ArrayList;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private ArrayList<Search_item> mExampleList;
    private Context context;

    public static final class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView item_Image;
        TextView item_name;


        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            item_Image=itemView.findViewById(R.id.Image_item);
            item_name=itemView.findViewById(R.id.item_name);






        }
    }

    public SearchAdapter(Context context,ArrayList<Search_item> exampleList) {
        this.context=context;
        mExampleList = exampleList;
    }

    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.search_item,parent,false);
        return new SearchViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.item_Image.setImageResource(mExampleList.get(position).getImageUrl());
        holder.item_name.setText(mExampleList.get(position).getName_item());
    }


    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public void filterList(ArrayList<Search_item> filteredList) {
        mExampleList = filteredList;
        notifyDataSetChanged();
    }
}