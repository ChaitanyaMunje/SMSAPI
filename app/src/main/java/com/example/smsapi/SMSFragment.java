package com.example.smsapi;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
// These fragment is used for displaying the SMS sent to the recipents..

/**
 * A simple {@link Fragment} subclass.
 */
public class SMSFragment extends Fragment {
    private SQLiteDatabase mDatabase;
    TextView txt;
    private SqliteAdapter mAdapter;

    String name,phno,date;
    int otp;



    public SMSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view= inflater.inflate(R.layout.fragment_sm, container, false);

        DBHandler dbHelper = new DBHandler(getContext());
        mDatabase = dbHelper.getWritableDatabase();
        txt=view.findViewById(R.id.text);
        if (getArguments()!=null) {
            name = getArguments().getString("name");
            phno = getArguments().getString("phno");
            otp = getArguments().getInt("otp");
            date = getArguments().getString("date");

            txt.setVisibility(View.INVISIBLE);


        }
        else {

            txt.setVisibility(View.VISIBLE);
        }
        RecyclerView recyclerView =view. findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new SqliteAdapter(getContext(), getAllItems());
        recyclerView.setAdapter(mAdapter);
       return view;
    }
    private void addItem() {

        if (name.length() == 0 || otp == 0) {
            return;
        }

        ContentValues cv = new ContentValues();
        cv.put(Contract.Entry.COLUMN_NAME, name);
        cv.put(Contract.Entry.COLUMN_OTP, otp);

        mDatabase.insert(Contract.Entry.TABLE_NAME, null, cv);
        mAdapter.swapCursor(getAllItems());

    }
    private Cursor getAllItems() {
        return mDatabase.query(
                Contract.Entry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                Contract.Entry.COLUMN_TIMESTAMP + " DESC"
        );
    }
}
