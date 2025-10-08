package com.example.todolist;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button addToListBtn;
    TextView addToListTV;
    EditText addToListItem;
    RecyclerView itemsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting IDs
        addToListBtn = findViewById(R.id.add_to_list_btn);
        addToListTV = findViewById(R.id.add_to_list_tv);
        addToListItem = findViewById(R.id.add_to_list_item);
        itemsList = findViewById(R.id.items_list);

        ArrayList test = new ArrayList<>();
        test.add("a");

        adapter.notifyItemInserted(test.size() -1);


//        kotlin
//
//        val dataset = arrayOf("January", "February", "March")
//        val customAdapter = CustomAdapter(dataset)
//
//        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = customAdapter


//        java
//
//        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.layoutManager = new LinearLayoutManager(this)
//        recyclerView.setAdapter(customAdapter);
    }
}