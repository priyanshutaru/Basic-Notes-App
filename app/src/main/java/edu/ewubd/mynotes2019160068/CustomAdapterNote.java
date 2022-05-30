package edu.ewubd.mynotes2019160068;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterNote extends BaseAdapter {

    Context context;
    ArrayList<NoteList> arrayList;
    public CustomAdapterNote(Context context, ArrayList<NoteList> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.custom_notes_layout, parent, false);

        TextView courseId = rowView.findViewById(R.id.tv_course_id);
        TextView topic = rowView.findViewById(R.id.tv_topic);
        TextView date = rowView.findViewById(R.id.tv_date);
        TextView description = rowView.findViewById(R.id.tv_description);

        NoteList allNotes = arrayList.get(position);

        courseId.setText(allNotes.getCourseId()+": ");

        if(allNotes.getTopic().length() > 10){
            topic.setText(allNotes.getTopic().substring(0,10)+"...");
        }
        else topic.setText(allNotes.getTopic());

        date.setText(allNotes.getDate());

        if(allNotes.getDescription().length() > 35){
            description.setText(allNotes.getDescription().substring(0,35)+"...");
        }
        else description.setText(allNotes.getDescription());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = allNotes.getId();
                Intent intent = new Intent(context, EditNote.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("noteId", id);
                context.startActivity(intent);
            }
        });

        return rowView;
    }
}
