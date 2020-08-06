package com.example.trfcommunityapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.trfcommunityapp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {


    Button addpost_btn;
    ArrayList<String> titles = new ArrayList<>();
    //ArrayList<String> authors = new ArrayList<>();
    ListView listView;
    ArrayAdapter<String> arrayAdapter;


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference blogRef = db.collection("Blogs");  //path to the collection on firebase


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        addpost_btn = (Button)findViewById(R.id.btn_addpost);
        listView = (ListView)findViewById(R.id.listview);


        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, titles);
        listView.setAdapter(arrayAdapter);


        addpost_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addPost = new Intent(RecyclerActivity.this, AddPost.class);
                startActivity(addPost);
                finish();

            }
            });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showblog = new Intent(RecyclerActivity.this, ShowBlog.class);
                showblog.putExtra("title", titles.get(position));
                startActivity(showblog);


            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
            titles.clear();
            //authors.clear();

        blogRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot blog : queryDocumentSnapshots){
                    blogs b = blog.toObject(blogs.class);
                    titles.add(b.getTitle());
                    //authors.add(b.getAuthor());
                }
                arrayAdapter.notifyDataSetChanged();
                //Notifies the attached observers that the underlying data has been changed and
                // any View reflecting the data set should refresh itself.

            }
        });
    }
}
