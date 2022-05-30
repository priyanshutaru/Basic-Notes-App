package edu.ewubd.mynotes2019160068;
public class NoteList {
    int noteId;
    String courseId, topic, date, note;

    public NoteList(int noteId, String courseId , String topic, String date, String note) {
        this.noteId = noteId;
        this.courseId = courseId;
        this.topic = topic;
        this.date = date;
        this.note = note;
    }
    public NoteList(){}

    public int getId() {
        return noteId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getTopic() {
        return topic;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return note;
    }


}

