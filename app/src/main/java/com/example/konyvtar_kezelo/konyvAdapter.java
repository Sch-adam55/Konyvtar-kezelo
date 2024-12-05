package com.example.konyvtar_kezelo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class konyvAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<konyvadatok> books;
    private LayoutInflater inflater;

    public konyvAdapter(Context context, ArrayList<konyvadatok> books) {
        this.context = context;
        this.books = books;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView != null ? convertView : inflater.inflate(R.layout.konyvitem, parent, false);
        konyvadatok book = books.get(position);

        TextView titleTextView = view.findViewById(R.id.tvBookTitle);
        titleTextView.setText(book.getTitle());

        Button deleteButton = view.findViewById(R.id.btnDelete);
        deleteButton.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setMessage("Biztosan törölni szeretnéd ezt a könyvet?")
                    .setPositiveButton("Igen", (dialog, which) -> {
                        books.remove(position);
                        notifyDataSetChanged();
                    })
                    .setNegativeButton("Nem", null)
                    .show();
        });

        view.setOnClickListener(v -> {
            Intent intent = new Intent(context, konyvAdapter.class);
            intent.putExtra("book", book);
            context.startActivity(intent);
        });

        return view;
    }
}

