package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button addToListBtn, deleteFromListBtn;
    TextView addToListTV;
    EditText addToListItem;
    RecyclerView recyclerView;
    RVAdapter adapter;
    ArrayList<String> arrayRV = new ArrayList<>();
    ArrayList<String> arrayListNames = new ArrayList<>();

    private static final String FILE_NAME = "todolist.json";

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

        adapter = new RVAdapter(arrayRV, arrayListNames, deleteFromListBtn); //added this

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //saving and loading json
        loadDataFromJson();
        adapter.notifyDataSetChanged();

        addToListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredItem = addToListItem.getText().toString();

                if (!enteredItem.isEmpty()){
                    int nextNumber = arrayRV.size() + 1;

                    arrayRV.add(nextNumber + ". ");
                    arrayListNames.add(enteredItem);

                    saveToDataJson();

                    adapter.notifyDataSetChanged();

                    addToListItem.setText(""); //clear the EditText

                }
            }
        });
        deleteFromListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void saveToDataJson(){
        JSONArray jsonArray = new JSONArray();
        try{
            for (int i = 0; i < arrayListNames.size(); i++){
                JSONObject obj = new JSONObject();
                obj.put("number", arrayRV.get(i));
                obj.put("item", arrayListNames.get(i));
                jsonArray.put(obj);
            }

            try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                 OutputStreamWriter osw = new OutputStreamWriter(fos)){
                osw.write(jsonArray.toString());
                osw.flush();
                Toast.makeText(this, "Data Saved.", Toast.LENGTH_SHORT).show();
                }
            } catch (IOException | JSONException e){
            e.printStackTrace();
            Toast.makeText(this, "Error saving data.", Toast.LENGTH_SHORT).show();
        }

    }

    private void loadDataFromJson(){
        arrayRV.clear();;
        arrayListNames.clear();

        try (FileInputStream fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr)){

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line);
            }

            JSONArray jsonArray = new JSONArray(sb.toString());
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                arrayRV.add(obj.getString("number"));
                arrayListNames.add(obj.getString("item"));
            }
            Toast.makeText(this, "Data Loaded.", Toast.LENGTH_SHORT).show();
        } catch (IOException | JSONException e){
            e.printStackTrace();
        }
    }
}
