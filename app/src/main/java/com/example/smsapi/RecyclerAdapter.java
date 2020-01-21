package com.example.smsapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
// Adapter class for displaying Contacts from Json file in Recycler view present in Contact Fragment.

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    ArrayList<ContactData> data;
    private Context mContext;

    private static final String TAG = "Main";

    public RecyclerAdapter(Context mContext, ArrayList<ContactData> data) {
        this.mContext = mContext;
        this.data = data;
    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.contactitem,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {

        final ContactData ctcdata=data.get(position);
        holder.name.setText(ctcdata.getName());
        holder.phno.setText(ctcdata.getPhno());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {



        Intent i=new Intent(mContext,NewSMS.class);
        i.putExtra("name",ctcdata.getName());
        i.putExtra("phno",ctcdata.getPhno());
        i.putExtra("email",ctcdata.getEmail());
        mContext.startActivity(i);

    }
});

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView name,phno,email;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            name=itemView.findViewById(R.id.txtname);
            phno=itemView.findViewById(R.id.phnotxt);

        }


    }
}
