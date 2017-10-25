package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.word_list);

            //create an array of words
            ArrayList<Word> words = new ArrayList<Word>();

            //words.add("One");
            words.add(new Word("red", "wetetti"));
            words.add(new Word("green", "chokokki"));
            words.add(new Word("brown", "takaakki"));
            words.add(new Word("gray", "topoppi"));
            words.add(new Word("black", "kululli"));
            words.add(new Word("white", "kelelli"));
            words.add(new Word("dusty yellow", "topiise"));
            words.add(new Word("musty yellow", "chiwiite"));


            WordAdaptor adapter =
                    new WordAdaptor(this, words);

            ListView listView = (ListView) findViewById(R.id.list);

            listView.setAdapter(adapter);
        }

}
