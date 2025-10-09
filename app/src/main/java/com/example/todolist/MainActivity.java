package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        addToListTV.setText("What would you like to do?");

        adapter = new RVAdapter(arrayRV, arrayListNames);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        addToListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredItem = addToListItem.getText().toString();

                if (!enteredItem.isEmpty()){
                    int nextNumber = arrayRV.size() + 1;

                    arrayRV.add(nextNumber + ". ");
                    arrayListNames.add(enteredItem);

                    adapter.notifyDataSetChanged();

                    addToListItem.setText(""); //clear the EditText
                }
            }
        });
    }
}