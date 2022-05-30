package edu.ewubd.mynotes2019160068;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button createNotes, exit;
    ArrayList<NoteList> arrayList;
    CustomAdapterNote CustomAdapterNote;
    private ListView showNotes;
    MyDatabaseHelper DB;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exit = findViewById(R.id.btn_exit);
        createNotes = findViewById(R.id.btn_create);
        showNotes = findViewById(R.id.tv_listview);

        exit.setOnClickListener(v-> finishAffinity());
        createNotes.setOnClickListener(v-> createNotes());

        DB= new MyDatabaseHelper(this);
    }

    void createNotes(){
        Intent intent = new Intent(this, CreateNote.class);
        startActivity(intent);
    }

    public void loadDataInList(){
        arrayList = DB.getProblems();
        CustomAdapterNote = new CustomAdapterNote(this,arrayList);
        showNotes.setAdapter(CustomAdapterNote);
        CustomAdapterNote.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadDataInList();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}