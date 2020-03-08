package com.example.android.myfragmentapplication;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemDetailFragment extends Fragment {

    private Person person;

    public ItemDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments().containsKey(Constants.PERSON_ID)){
            person = (Person) getArguments().getSerializable(Constants.PERSON_ID);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_item_detail, container, false);
        View viewRoot = inflater.inflate(R.layout.fragment_item_detail, container, false);

        if(person != null){
            TextView textViewToShowPerson = viewRoot.findViewById(R.id.textViewToShowPerson);
            textViewToShowPerson.setText(person.toString());
        }



        return viewRoot;
    }





}
