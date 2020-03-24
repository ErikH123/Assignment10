package com.example.assignment10;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button_add;
    Button button_clear;
    Button button_fetch;
    TextView textView;
    EditText editText;
    ListView listView;
    Adapteri adapteri;

    ArrayList<String> tag_list;
    ArrayList<Bitmap> lista;

    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_add = findViewById(R.id.button_add);
        button_clear = findViewById(R.id.button_clear);
        button_fetch = findViewById(R.id.button_fetch);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        listView = findViewById(R.id.listView);
        tag_list = new ArrayList<>();
        lista = new ArrayList<>();
        adapteri = new Adapteri(this, R.layout.bitmap_layout, lista);
        listView.setAdapter(adapteri);
        queue = Volley.newRequestQueue(this);

        button_add.setOnClickListener(v -> {
            StringBuilder tagit = new StringBuilder();
            if (!editText.getText().toString().isEmpty()) {
                tag_list.add(editText.getText().toString());
                editText.setText("");
                for (int i = 0; i < tag_list.size(); i++) {
                    String s = tag_list.get(i);
                    tagit.append(s);
                    if (i < tag_list.size() - 1)
                        tagit.append(",");
                }
                textView.setText(tagit.toString());
            } else
                Toast.makeText(getApplicationContext(), "Anna ensin tagi", Toast.LENGTH_SHORT).show();
        });

        button_clear.setOnClickListener(v -> {
            tag_list.clear();
            textView.setText("");
        });

        button_fetch.setOnClickListener(v -> {
            StringBuilder tagit = new StringBuilder();
            String url = "https://loremflickr.com/320/240/";

            if (!tag_list.isEmpty()) {
                for (int i = 0; i < tag_list.size(); i++) {
                    String s = tag_list.get(i);
                    tagit.append(s);
                    if (i < tag_list.size() - 1)
                        tagit.append(",");
                }
            }
            url += tagit.toString();
            Log.d("Fetch onClick", "URL: " + url);

            if (!tag_list.isEmpty()) {
                try {
                    ImageRequest request = new ImageRequest(
                            url, response -> {
                        lista.add(response);
                        adapteri.notifyDataSetChanged();
                    }, 500, 500, ImageView.ScaleType.CENTER, null, Throwable::printStackTrace);
                    queue.add(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else
                Toast.makeText(getApplicationContext(), "Anna ensin tagi", Toast.LENGTH_SHORT).show();
        });
    }
}