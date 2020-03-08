package com.example.android.myfragmentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class ItemDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);



        if(savedInstanceState == null){

            Bundle arguments = new Bundle();
            arguments.putSerializable(Constants.PERSON_ID, getIntent().getSerializableExtra(Constants.PERSON_ID) );

            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();

        }


    }
}
