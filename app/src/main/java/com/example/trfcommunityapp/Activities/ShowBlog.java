package com.example.trfcommunityapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.trfcommunityapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShowBlog extends AppCompatActivity {

    TextView title,author;
    TextView description;
    Bundle extras;
    String getId,display_title,display_des,display_author;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    CollectionReference blogRef = db.collection("Blogs");
//    DocumentReference noteRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_blog);


        title = (TextView)findViewById(R.id.sb_title);
        description = (TextView) findViewById(R.id.sb_description);
        author = (TextView)findViewById(R.id.sb_author);


        extras = getIntent().getExtras();
        if (extras != null) {
             getId = extras.getString("title");
            // and get whatever type user account id is
        }

        //noteRef = db.document("Blogs/" +getId);

//        Log.i(String , )

        DocumentReference docRef = db.collection("Blogs").document(getId);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                blogs display_blog = documentSnapshot.toObject(blogs.class);
                display_title =(String) display_blog.getTitle();
                display_author =(String)display_blog.getAuthor();
                display_des =(String) display_blog.getDescription();
                //Log.d(display_title,display_des);
                title.setText(display_title);
                description.setText(display_des);
                description.setMovementMethod(new ScrollingMovementMethod());
                author.setText((String)"Author : " + display_author);

            }


        });



    }


}
