package com.example.androidtest;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.util.Log;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.view.View;
import android.os.Bundle;
//更改一下提交这是第二十改的
public class MainActivity extends AppCompatActivity {

    EditText txtContent, txtTitle;
    int listNumber = 0;
    Button btnGo;
    String listNote = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnGo = findViewById(R.id.btnGo);
        txtTitle = findViewById(R.id.txtTitle);
        txtContent = findViewById(R.id.txtContent);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //the list of note for calculation;
                listNumber++;

                listNote = "note";
                NoteModel note = new NoteModel();

                //for the edit the content
                note.title = txtTitle.getText().toString();
                note.content = note.title;

                //open the preferences(contentWrite), if exit, open it, if it not exit, create a new
                SharedPreferences contentWrite = getPreferences(0);

                //let contentWrite in the state of editing.
                SharedPreferences.Editor editor = contentWrite.edit();
                String json = note.toJson();
                Log.i("json", json);
                note.fromJson(json);

                //this is for storing the data.
                editor.putString(note.title, json);

                //submit the data
                editor.commit();
                txtTitle.setText(note.title);

                //the final content
                txtContent.setText(txtContent.getText().toString() + listNote + " "
                        + listNumber +":  " + note.content+"\n" + "Edit :"+ "\n" + "\n");
            }
        });

    }
}