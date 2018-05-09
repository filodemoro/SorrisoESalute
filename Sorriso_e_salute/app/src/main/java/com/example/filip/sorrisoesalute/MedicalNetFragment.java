package com.example.filip.sorrisoesalute;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by filip on 28/02/2018.
 */

public class MedicalNetFragment extends Fragment {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
    private TextView regioneScelta;
    private TextView categoriaScelta;
    private TextView specialitaScelta;
    private Button findDoctor;

    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medical_net, container, false);
        getActivity().setTitle(R.string.main_activity_medicalNet_drawer);

        listView = (ExpandableListView) view.findViewById(R.id.medicalNet_fragment_listRegion_listView);
        initData();
        listAdapter = new ExpandableListAdapter(this.getContext(),listDataHeader,listHash);



        regioneScelta = view.findViewById(R.id.regioneScelta);
        regioneScelta.setText("nessuna");
        categoriaScelta = view.findViewById(R.id.categoriaScelta);
        categoriaScelta.setText("nessuna");
        specialitaScelta = view.findViewById(R.id.specialitaScelta);
        specialitaScelta.setText("nessuna");
        findDoctor = view.findViewById(R.id.medicalNet_fragment_button);

        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(validateForm()){
                    // Start the SecondActivity
                    Intent intent = new Intent(getActivity(), MedicalListActivity.class);
                    intent.putExtra("regioneScelta", regioneScelta.getText());
                    intent.putExtra("categoriaScelta", categoriaScelta.getText());
                    intent.putExtra("specialitaScelta", specialitaScelta.getText());
                    startActivity(intent);
                }
                else{
                    Toast toast = Toast.makeText(getContext(),"selezionare tutti gli elementi", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }


        });


        listView.setAdapter(listAdapter);

/*
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }

        });
*/

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String regionSelected = null;
                String categorySelected = null;
                String specialitySelected = null;

                listView.collapseGroup(groupPosition);


                if(groupPosition == 0){

                    regionSelected = listAdapter.getChild(groupPosition, childPosition).toString();
                    regioneScelta.setText(regionSelected);
                    categoriaScelta.setText("nessuna");
                    specialitaScelta.setText("nessuna");


                    List<String> category = new ArrayList<>();

                    switch (regionSelected){

                        case "Liguria":
                            category.add("Abruzzo");
                            category.add("Basilicata");
                            break;
                        default:
                            category.add("non Liguria");
                            category.add("Italia");
                            category.add("Francia");
                            category.add("Spagna");
                            category.add("Germania");
                    }
                    listHash.remove(listDataHeader.get(1));
                    listHash.put(listDataHeader.get(1), category);
                   // listView.expandGroup(1);

                    if(categoriaScelta.getText().equals("nessuna")){
                        List<String> speciality = new ArrayList<>();

                        speciality.add("scegli prima una categoria");

                        listHash.remove(listDataHeader.get(2));
                        listHash.put(listDataHeader.get(2), speciality);
                    }


                    listView.collapseGroup(1);
                    listView.collapseGroup(2);
                }
                if(groupPosition == 1){

                    categorySelected = listAdapter.getChild(groupPosition, childPosition).toString();
                    categoriaScelta.setText(categorySelected);
                    specialitaScelta.setText("nessuna");
                    List<String> speciality = new ArrayList<>();

                    listView.collapseGroup(2);
                    listView.collapseGroup(0);


                    switch (categorySelected){

                        case "non Liguria":
                            speciality.add("pongo");
                            speciality.add("peggi");
                            break;
                        case "Italia":
                            speciality.add("roma");
                            speciality.add("milano");
                            break;
                        case "Francia":
                            speciality.add("parigi");
                            speciality.add("lione");
                            break;
                        case "Spagna":
                            speciality.add("barcellona");
                            speciality.add("madrid");
                            break;
                        case "Germania":
                            speciality.add("berlino");
                            break;
                        default:
                            speciality.add("pollo");
                    }
                    listHash.remove(listDataHeader.get(2));
                    listHash.put(listDataHeader.get(2), speciality);


                   // listView.expandGroup(2);
                }
                if (groupPosition == 2){
                    specialitySelected = listAdapter.getChild(groupPosition, childPosition).toString();
                    specialitaScelta.setText(specialitySelected);

                    listView.collapseGroup(0);
                    listView.collapseGroup(1);

                }


                Toast t = Toast.makeText(getContext(), regionSelected + categorySelected + specialitySelected , Toast.LENGTH_SHORT);


                t.show();
                // update the text view with the country
                return true;
            }
        });

        return view;
    }


    private boolean validateForm(){
        if (regioneScelta.getText().equals("nessuna"))
            return false;
        else if(categoriaScelta.getText().equals("nessuna"))
            return false;
        else if(specialitaScelta.getText().equals("nessuna"))
            return false;
        else
            return true;
    }



    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Scegli regione");
        listDataHeader.add("Categorie disponibili");
        listDataHeader.add("Scegli specialità");

        List<String> region = new ArrayList<>();
        region.add("Abruzzo");
        region.add("Basilicata");
        region.add("Calabria");
        region.add("Campania");
        region.add("Emilia-Romagna");
        region.add("Friuli-Venezia-Giulia");
        region.add("Lazio");
        region.add("Liguria");
        region.add("Lombardia");
        region.add("Marche");
        region.add("Molise");
        region.add("Piemonte");
        region.add("Puglia");
        region.add("Sardegna");
        region.add("Sicilia");
        region.add("Toscana");
        region.add("Trentino-Alto-Adige");
        region.add("Umbria");
        region.add("Valle d'Aosta");
        region.add("Veneto");

        List<String> category = new ArrayList<>();
        category.add("scegli prima una regione");

        List<String> speciality = new ArrayList<>();
        speciality.add("scegli prima una categoria");

        listHash.put(listDataHeader.get(0),region);
        listHash.put(listDataHeader.get(1),category);
        listHash.put(listDataHeader.get(2),speciality);


    }
}
