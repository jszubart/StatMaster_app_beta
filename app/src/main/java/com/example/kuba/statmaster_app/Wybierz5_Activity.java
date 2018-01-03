package com.example.kuba.statmaster_app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Wybierz5_Activity extends AppCompatActivity {

    private static final String TAG = "First5List";

    private ListView PlayersListView;
    private int selectedTeamID;
    DataBaseHelper DaneDrużyn;
    ArrayList<First5_row> ListData;
    First5_row first5_row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wybierz5_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent receivedIntent = getIntent();
        selectedTeamID = receivedIntent.getIntExtra("ID", -1);

        PlayersListView = (ListView) findViewById(R.id.First5_List);
        PlayersListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        DaneDrużyn = new DataBaseHelper(this);
        fillPlayersList();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void fillPlayersList() {
        //Log.d(TAG, "Dodawanie listy");
        Cursor data = DaneDrużyn.getPlayers(selectedTeamID);
        ListData = new ArrayList<>();
        while (data.moveToNext()) {
            first5_row = new First5_row(data.getString(0));
            ListData.add(first5_row);
        }
        PlayerListAdapter adapter = new PlayerListAdapter(this, R.layout.first5_row, ListData);
        PlayersListView.setAdapter(adapter);

        PlayersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                First5_row player = ListData.get(i);
                String nazwa = player.getNazwa();
                Log.d(TAG, "U clicked at " + nazwa + " platyer");
            }
        });
    }
}
