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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button addToListBtn;
    TextView addToListTV;
    EditText addToListItem;
    RecyclerView recyclerView;
    RVAdapter adapter;
    ArrayList<String> arrayRV = new ArrayList<>();
    ArrayList<String> arrayListNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting IDs
        addToListBtn = findViewById(R.id.add_to_list_btn);
        addToListTV = findViewById(R.id.add_to_list_tv);
        addToListItem = findViewById(R.id.add_to_list_item);
        recyclerView = findViewById(R.id.recyclerView);

        adapter = new RVAdapter(arrayRV, arrayListNames);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //
        for (int i = 1; i <= 20; i++){
            arrayRV.add(i + " ");
            arrayListNames.add("This is item " + i);
            adapter.notifyDataSetChanged();
        }
    }
}