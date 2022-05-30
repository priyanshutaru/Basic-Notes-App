package edu.ewubd.mynotes2019160068;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class CreateNote extends AppCompatActivity {
    EditText courseId, topic, date, description;
    Button back, create;
    MyDatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cerate_note_layout);

        DB= new MyDatabaseHelper(this);
        back = findViewById(R.id.btn_back_create_note);
        create = findViewById(R.id.btn_save_create_note);
        back.setOnClickListener(v-> onBackPressed());
        create.setOnClickListener(v->saveNotes());

        courseId = findViewById(R.id.et_course_id);
        topic = findViewById(R.id.et_topic);
        date = findViewById(R.id.et_date);
        description = findViewById(R.id.et_write_note);
    }
    void saveNotes(){
        String prvCourseId = "", prvTopic ="", prvDate ="", prvDescription ="";

        if(courseId.getText().toString().trim().length() < 1 || topic.getText().toString().trim().length() < 1 ||
                date.getText().toString().trim().length() < 1 || description.getText().toString().trim().length() < 1){
           Toast.makeText(this,"Please fill all the field appropriately.",Toast.LENGTH_LONG).show();
        } else {
            prvCourseId = courseId.getText().toString().trim();
            prvTopic = topic.getText().toString().trim();
            prvDate = date.getText().toString().trim();
            prvDescription = description.getText().toString().trim();
            Boolean noError = DB.insertNotes(prvCourseId, prvTopic, prvDate, prvDescription);
            if(noError==true ){
                Toast.makeText(this,"Note Created Successfully",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
            else System.out.println("Got some error");
        }
    }
}