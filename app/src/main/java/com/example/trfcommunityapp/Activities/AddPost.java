package com.example.trfcommunityapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.trfcommunityapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddPost extends AppCompatActivity {


    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_AUTHOR = "author";

    EditText ed_title,ed_description,ed_author;
    Button upload;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        ed_title = (EditText)findViewById(R.id.title);
        ed_author = (EditText)findViewById(R.id.author);
        ed_description = (EditText)findViewById(R.id.description);
        upload = (Button)findViewById(R.id.upload_btn);
    }

    public void upload(View v) {
        String title = ed_title.getText().toString();
        String description = ed_description.getText().toString();
        String author = ed_author.getText().toString();
        Map<String, Object> note = new HashMap<>();
        note.put(KEY_TITLE, title);
        note.put(KEY_DESCRIPTION, description);
        note.put(KEY_AUTHOR, author);
        db.collection("Blogs").document(title).set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddPost.this, "Blog saved", Toast.LENGTH_SHORT).show();
                        updateUI();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddPost.this, "Error in uploading the blog!", Toast.LENGTH_SHORT).show();
                        //Log.d( TAG,e.toString());
                    }
                });
    }

    private void updateUI() {
        Intent homeActivity = new Intent(AddPost.this, RecyclerActivity.class);
        startActivity(homeActivity);
        finish();

    }






}
