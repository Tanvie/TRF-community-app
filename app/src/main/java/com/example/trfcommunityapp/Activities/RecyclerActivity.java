package com.example.trfcommunityapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.trfcommunityapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RecyclerActivity extends AppCompatActivity {


    Button addpost_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        addpost_btn = (Button)findViewById(R.id.btn_addpost);


        addpost_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addPost = new Intent(RecyclerActivity.this, AddPost.class);
                startActivity(addPost);
                finish();

            }
            });
    }


}
