package com.example.filip.sorrisoesalute;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by filip on 08/03/2018.
 */

public class MedicalListActivity extends AppCompatActivity {




    ArrayList<HashMap<String, String>> data = new ArrayList<>();
    private ArrayList<String> arrayKey = new ArrayList<>();
    private ListView listView;
    private String region;
    private String category;
    private String speciality;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_medical_list);
        Toolbar toolbar = findViewById(R.id.toolbar_medical_net);
        toolbar.setNavigationIcon(R.drawable.ic_menu_send);
        setSupportActionBar(toolbar);
        listView = findViewById(R.id.medicalList_activity_listView);






        //prendo i dati dall' activity precedente per la query sulle strutture
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            region = extras.getString("regioneScelta");
            category = extras.getString("categoriaScelta");
            speciality = extras.getString("specialitaScelta");
        }



        //*********************dentro il for

        // creo hashMap per ogni risultato
        // ne sunop due solo perche non ho ancora messo il for
        HashMap<String, String> map = new HashMap<>();
        HashMap<String, String> map1 = new HashMap<>();

        //resource è il layout di come voglio ogni singolo item
        int resource = R.layout.list_view_item_medical_list;

        //qui salvo una stringa con gli stessi nomi messi nell hashMAp
        String[] from = {"structure", "city", "address"};

        //qui salvo un altro array contenenti l id di ogni widget del mio singolo item
        int[] to = {R.id.medicalList_activity_itemStructure_textView, R.id.medicalList_activity_itemCity_textView, R.id.medicalList_activity_itemAddress_textView};

        map.put("structure", "Ospedale San Martino");
        map.put("city", "Genova(GE)");
        map.put("address", "Via Mosso 12");

        arrayKey.add("Ospedale San Martino");

        data.add(map);


        map1.put("structure", "Ospedale Gaslini");
        map1.put("city", "Genova(GE)");
        map1.put("address", "Via V Maggio 17");

        arrayKey.add("Ospedale Gaslini");


        //inserisco l hashMap nell arrayList
        data.add(map1);
        //*******************chiudo for








        SimpleAdapter adapter = new SimpleAdapter(this, data, resource, from, to);
        listView.setAdapter(adapter);



        // onCLickListener per ogni item della listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position,
                                    long id) {

                        Intent intent = new Intent(getBaseContext(), StructureActivity.class);
                        intent.putExtra("key", arrayKey.get(position));
                        startActivity(intent);

            }

        });



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
