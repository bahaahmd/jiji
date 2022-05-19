package com.example.project2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Adapter.NewsAdapter;
import Adapter.PopularAdapter;
import Adapter.ProvinceAdapter;


public class HomeFragement extends Fragment {
    RecyclerView recyclerView,recyclerView2;
    PopularAdapter adapter;
    NewsAdapter adapter_new;
    ArrayList<Product>list;
    ArrayList<Product>news_list;
    ArrayList<Province>provinces_list;
    Spinner spinner_provinces;
    DatabaseReference databaseReference;
    ImageView btnHeart;
    FirebaseAuth mAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_home_fragement, container, false);
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView);
        setPopularRecycler();
        recyclerView2=(RecyclerView) view.findViewById(R.id.recyclerView_2);
        setNewsRecycler();
        mAuth = FirebaseAuth.getInstance();
        spinner_provinces=(Spinner) view.findViewById(R.id.spinner);
        setProvinceSpinner(provinces_list);
        btnHeart=view.findViewById(R.id.btnHeart);
        btnHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),FavoriteActivity.class));
            }
        });
        return view;


    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list=new ArrayList<>();
        list.add(new Product("Sneakers", R.drawable.sneakers,"7000 DA","4000 DA","Jusqu'au 25 Mai","Good"));
        list.add(new Product("Super-Star", R.drawable.star,"7000 DA","4000 DA","Jusqu'au 25 Mai","Good"));
        list.add(new Product("Sneakers", R.drawable.sneakers,"7000 DA","4000 DA","Jusqu'au 25 Mai","Good"));
        list.add(new Product("Super-Star", R.drawable.star,"7000 DA","4000 DA","Jusqu'au 25 Mai","Good"));
        list.add(new Product("Sneakers", R.drawable.sneakers,"7000 DA","4000 DA","Jusqu'au 25 Mai","Good"));

        news_list=new ArrayList<>();
        news_list.add(new Product("Sneakers", R.drawable.sneakers,"7000 DA","4000 DA","Jusqu'au 25 Mai","Good"));
        news_list.add(new Product("Super-Star", R.drawable.star,"7000 DA","4000 DA","Jusqu'au 25 Mai","Good"));
        news_list.add(new Product("Super-Star", R.drawable.star,"7000 DA","4000 DA","Jusqu'au 25 Mai","Good"));
        news_list.add(new Product("Sneakers", R.drawable.sneakers,"7000 DA","4000 DA","Jusqu'au 25 Mai","Good"));
        news_list.add(new Product("Sneakers", R.drawable.sneakers,"7000 DA","4000 DA","Jusqu'au 25 Mai","Good"));
        news_list.add(new Product("Sneakers", R.drawable.sneakers,"7000 DA","4000 DA","Jusqu'au 25 Mai","Good"));




        provinces_list=new ArrayList<>();
        provinces_list.add(new Province("Oran"));
        provinces_list.add(new Province("Alger"));
        provinces_list.add(new Province("Tiaret"));
        provinces_list.add(new Province("Setif"));
        provinces_list.add(new Province("Bayed"));
    }
    public void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(getActivity(), Identification.class));
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    private void setPopularRecycler()
    {

        databaseReference = FirebaseDatabase.getInstance().getReference("Products");
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new PopularAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Map<String,String> product = (Map<String, String>) dataSnapshot.getValue();
                    list.add(new Product(product.get("name"), 0,product.get("rating"),product.get("price_ancien"),product.get("price_nouveau"),product.get("date")));


                    try {
                        keys.add(dataSnapshot.getKey());
                        Product product1 = dataSnapshot.getValue(Product.class);
                        list.add(product1);
                    }catch (Exception e){
                        System.out.println("err:"+e.getMessage());
                    }
                };
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    private void setNewsRecycler()
    {
        databaseReference = FirebaseDatabase.getInstance().getReference("Products");

        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2);
        recyclerView2.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        adapter_new=new NewsAdapter(getActivity(),news_list);
        recyclerView2.setAdapter(adapter_new);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                news_list.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Map<String,String> product = (Map<String, String>) dataSnapshot.getValue();
                    news_list.add(new Product(product.get("name"), 0,product.get("rating"),product.get("price_ancien"),product.get("price_nouveau"),product.get("date")));

                    try {
                        keys.add(dataSnapshot.getKey());
                        Product product1 = dataSnapshot.getValue(Product.class);
                        news_list.add(product1);
                    }catch (Exception e){
                        System.out.println("err:"+e.getMessage());
                    }
                };
                adapter_new.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }





    private void setProvinceSpinner(ArrayList<Province> provinces_list)
    {
        ProvinceAdapter provinces=new ProvinceAdapter(getContext(),R.layout.province_item,provinces_list);
        spinner_provinces.setAdapter(provinces);
    }





}

