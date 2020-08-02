package com.example.autocomplete;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText addWord;
    private EditText searchWord;
    private Button addWordBtn;
    private TextView foundWords;

    private Trie trie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addWord = findViewById(R.id.et_add_word);
        searchWord = findViewById(R.id.et_search_word);
        addWordBtn = findViewById(R.id.btn_add);

        foundWords = findViewById(R.id.tv_found_words);
        trie = new Trie();

        setupListeners();
    }

    private void setupListeners() {
        addWordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trie.insert(addWord.getText().toString());
                addWord.setText("");
            }
        });

        searchWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = searchWord.getText().toString();
                if(text.equals("")){
                    foundWords.setText("");
                    return;
                }

                List<String> words = trie.findWords(text);
                foundWords.setText(words.isEmpty() ? "No results" : words.toString());
            }
        });
    }
}