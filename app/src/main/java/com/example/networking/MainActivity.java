package com.example.networking;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private ArrayList<Mountain> mountainList;
    private ArrayList<String> mountainNameList;

    private MountainAdapter mountainAdapter;

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=brom";
    private final String JSON_FILE = "mountains.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mountainNameList = new ArrayList<String>();
        RecyclerView recyclerView = findViewById(R.id.recyview);

        mountainAdapter = new MountainAdapter(mountainNameList);
        recyclerView.setAdapter(mountainAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        new JsonTask(this).execute(JSON_URL);
    }

    @Override
    public void onPostExecute(String json) {
        Gson gson = new Gson();

        Type type = new TypeToken<List<Mountain>>() {}.getType();
        mountainList = gson.fromJson(json, type);

        mountainNameList = new ArrayList<String>();

        for (int i = 0; i < mountainList.size(); i++){
            mountainNameList.add(mountainList.get(i).toString());
        }

        mountainAdapter.setMountains(mountainNameList);
        mountainAdapter.notifyDataSetChanged();
    }

}
