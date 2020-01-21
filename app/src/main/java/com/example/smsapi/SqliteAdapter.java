package com.example.smsapi;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SqliteAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private Cursor mCursor;

    public SqliteAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.sms_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
    }


        String name = mCursor.getString(mCursor.getColumnIndex(Contract.Entry.COLUMN_NAME));
        int amount = mCursor.getInt(mCursor.getColumnIndex(Contract.Entry.COLUMN_OTP));

       // holder.nameText.setText(name);
        //holder.countText.setText(String.valueOf(amount));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nametxt;
        public TextView otptxt;

        public ViewHolder(View itemView) {
            super(itemView);

            nametxt = itemView.findViewById(R.id.textview_name);
            otptxt = itemView.findViewById(R.id.textview_otp);
        }
    }

}
