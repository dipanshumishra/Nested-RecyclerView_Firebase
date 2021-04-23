package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Model.Category;
import com.example.myapplication.Model.CategoryTwo;
import com.example.myapplication.ViewHolder.CategoryTwoViewHolder;
import com.example.myapplication.ViewHolder.CategoryViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    FirebaseDatabase database;
    DatabaseReference reference;


    FirebaseRecyclerAdapter<Category, CategoryViewHolder> adapter;
    FirebaseRecyclerAdapter<CategoryTwo, CategoryTwoViewHolder> adapter2;
    RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Category");

     manager =new LinearLayoutManager(this);
     recyclerView = findViewById(R.id.recyclerView);
     recyclerView.setLayoutManager(manager);

        FirebaseRecyclerOptions<Category> options = new FirebaseRecyclerOptions.Builder<Category>()
                .setQuery(reference,Category.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<Category, CategoryViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CategoryViewHolder holder, int position, @NonNull Category model) {

                  holder.categoryName.setText(model.getCategoryName());
                  FirebaseRecyclerOptions<CategoryTwo> options2 = new FirebaseRecyclerOptions.Builder<CategoryTwo>()
                          .setQuery(reference.child(model.getCategoryId()).child("data"),CategoryTwo.class)
                          .build();

                adapter2 = new FirebaseRecyclerAdapter<CategoryTwo, CategoryTwoViewHolder>(options2) {
                    @Override
                    protected void onBindViewHolder(@NonNull CategoryTwoViewHolder holder, int position, @NonNull CategoryTwo model) {

                        holder.dataId.setText(model.getDataId());
                        holder.dataName.setText(model.getDataName());
                        holder.dataAge.setText(model.getDataAge());

                    }

                    @NonNull
                    @Override
                    public CategoryTwoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View v2 = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_view_two,parent,false);
                        return new CategoryTwoViewHolder(v2);
                    }
                };


                adapter2.startListening();
                adapter2.notifyDataSetChanged();
                holder.category_recyclerView.setAdapter(adapter2);

            }

            @NonNull
            @Override
            public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v1 = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_view_one,parent,false);
                return new CategoryViewHolder(v1);
            }
        };

        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}