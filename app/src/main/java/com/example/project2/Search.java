package com.example.project2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Adapter.PopularAdapter;
import Adapter.SearchAdapter;

public class Search extends Fragment {
    RecyclerView recyclerView;
    SearchAdapter adapter;
    SearchView searchView;
    ArrayList<Search_item> list;
    RelativeLayout parent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_search, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.search_recView);
        setSearchRecycler(list);
        parent=(RelativeLayout) view.findViewById(R.id.parent);
        searchView= view.findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        list=new ArrayList<>();
        list.add(new Search_item("Nike",R.drawable.nike));
        list.add(new Search_item("Adidas",R.drawable.adidas));
        list.add(new Search_item("Adidas",R.drawable.adidas));
        list.add(new Search_item("Intenr",R.drawable.nike));
        list.add(new Search_item("Puma",R.drawable.puma));
        list.add(new Search_item("Jaguar",R.drawable.jaguar));








    }



    private void setSearchRecycler(ArrayList<Search_item> list)
    {
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new SearchAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);

    }
    private void filter(String text) {
        ArrayList<Search_item> filteredList = new ArrayList<>();

        for (Search_item item : list) {
            if (item.getName_item().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }
}