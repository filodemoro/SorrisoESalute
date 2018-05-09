package com.example.filip.sorrisoesalute;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by filip on 13/03/2018.
 */

public class StructureActivity extends AppCompatActivity {

    private String key;
    private TextView prova;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_structure);
        Toolbar toolbar = findViewById(R.id.toolbar_structure);
        toolbar.setNavigationIcon(R.drawable.ic_menu_send);
        setSupportActionBar(toolbar);

        prova = findViewById(R.id.structure_activity_prova_textView);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            key = extras.getString("key");
        }

        prova.setText(key);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
