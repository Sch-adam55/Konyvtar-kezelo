package com.example.konyvtar_kezelo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText EditKonyvcim, EditSzerzo, EditOldalszam;
    private Button AddButton;
    private ListView ListViewBooks;

    private ArrayList<konyvadatok> bookList = new ArrayList<>();
    private konyvAdapter konyvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditKonyvcim = findViewById(R.id.EditKonyvcim);
        EditSzerzo = findViewById(R.id.EditSzerzo);
        EditOldalszam = findViewById(R.id.EditOldalszam);
        AddButton = findViewById(R.id.AddButton);
        ListViewBooks = findViewById(R.id.ListViewBooks);
        konyvAdapter = new konyvAdapter(this, bookList);
        ListViewBooks.setAdapter(konyvAdapter);

        AddButton.setOnClickListener(v -> addBook());

        ListViewBooks.setOnItemClickListener((parent, view, position, id) -> {
            konyvadatok selectedBook = bookList.get(position);
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("konyvcím", selectedBook.getTitle());
            intent.putExtra("konyvszerző", selectedBook.getAuthor());
            intent.putExtra("konyvoldalszam", selectedBook.getPagesCount());
            startActivity(intent);
        });
    }

    private void addBook() {
        String title = EditKonyvcim.getText().toString().trim();
        String author = EditSzerzo.getText().toString().trim();
        String pagesStr = EditOldalszam.getText().toString().trim();

        if (title.isEmpty() || author.isEmpty() || pagesStr.isEmpty()) {
            Toast.makeText(this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show();
            return;
        }

        int pages;
        try {
            pages = Integer.parseInt(pagesStr);
            if (pages < 50) {
                Toast.makeText(this, "Az oldalszám minimum 50 kell legyen!", Toast.LENGTH_SHORT).show();
                return;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Oldalszámnak számnak kell lennie!", Toast.LENGTH_SHORT).show();
            return;
        }

        konyvadatok newKonyv = new konyvadatok(title, author, pages);
        bookList.add(newKonyv);
        konyvAdapter.notifyDataSetChanged();

        EditKonyvcim.setText("");
        EditSzerzo.setText("");
        EditOldalszam.setText("");
    }
}
