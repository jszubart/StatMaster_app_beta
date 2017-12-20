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
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DruzynyActivity extends AppCompatActivity {

    private static final String TAG = "TeamList";
    private ListView TeamList;
    DataBaseHelper DaneDrużyn;
    ArrayList<DruzynaRow> ListData;
    DruzynaRow druzynaRow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_druzyny);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TeamList = (ListView) findViewById(R.id.TeamList);
        DaneDrużyn = new DataBaseHelper(this);
        fillTeamList();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.dodaj);
        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DruzynyActivity.this, DodajTeamActivity.class);
                startActivity(intent);
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void fillTeamList(){
        Log.d(TAG, "Dodawanie listy");
        Cursor data = DaneDrużyn.getTeams();
        ListData = new ArrayList<>();
        while (data.moveToNext()){
            druzynaRow = new DruzynaRow(data.getString(1),data.getString(2));
            ListData.add(druzynaRow);
        }
        TeamListAdapter adapter = new TeamListAdapter(this, R.layout.druzyna_row,ListData);
        TeamList.setAdapter(adapter);
    }

}
