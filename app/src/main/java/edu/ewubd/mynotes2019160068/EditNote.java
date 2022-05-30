package edu.ewubd.mynotes2019160068;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditNote extends AppCompatActivity {
    private int noteId;
    EditText courseId, topic, date, description;
    Button back, save, delete;
    MyDatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note_layout);

        DB= new MyDatabaseHelper(this);

        back = findViewById(R.id.btn_back_edit_note);
        save = findViewById(R.id.btn_save_edit_note);
        delete = findViewById(R.id.btn_delete_edit_note);
        courseId = findViewById(R.id.et_course_id_edit_note);
        topic = findViewById(R.id.et_topic_edit_note);
        date = findViewById(R.id.et_date_edit_note);
        description = findViewById(R.id.et_write_note_edit_note);

        back.setOnClickListener(v->finish());
        save.setOnClickListener(v->saveEditedNotes());
        delete.setOnClickListener(v->deleteNote());

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            noteId = extras.getInt("noteId");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        String notes[];
        notes = DB.getNote(noteId);
        courseId.setText(notes[0]);
        topic.setText(notes[1]);
        date.setText(notes[2]);
        description.setText(notes[3]);
    }

    void saveEditedNotes(){
        String prvCourseId = "", prvTopic ="", prvDate ="",prvDescription ="";

        if(courseId.getText().toString().trim().length() < 1 || topic.getText().toString().trim().length() < 1 ||
                date.getText().toString().trim().length() < 1 || description.getText().toString().trim().length() < 1){
            Toast.makeText(this,"Please Fill All The Field Appropriately.",Toast.LENGTH_LONG).show();
        } else {
            prvCourseId = courseId.getText().toString().trim();
            prvTopic = topic.getText().toString().trim();
            prvDate = date.getText().toString().trim();
            prvDescription = description.getText().toString().trim();
            Boolean noError = DB.updateNote(noteId, prvCourseId, prvTopic, prvDate, prvDescription);
            if(noError==true){
                Toast.makeText(this,"Note Updated Successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else System.out.println("Got some error");
        }


    }
    void deleteNote(){
        Boolean noError = DB.deleteNote(noteId);
        if(noError==true){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else System.out.println("Got some error");
    }
}