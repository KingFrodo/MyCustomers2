package com.example.mycustomers;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<Customer> customers = new ArrayList<>();
    public List<String> names = new ArrayList<>();
    public ArrayAdapter<String> arrAdapter;
    static File file = new File("customers_data.csv");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readAssets();

        arrAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                names);

        ListView listView = (ListView) findViewById(R.id.listView);
        SearchView searchView = (SearchView) findViewById(R.id.searchView);

        listView.setAdapter(arrAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(names.contains(query)){
                    arrAdapter.getFilter().filter(query);
                }

                else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                arrAdapter.getFilter().filter(newText);
                return false;
            }

            
        });
    }

    private InputStream getInputStreamForAsset(String filename){
        AssetManager assets = getAssets();

        try {
            return assets.open(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void readAssets(){
        InputStream in = getInputStreamForAsset(file.getName());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;

        try {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                String id = split[0];
                int idInt = Integer.parseInt(id);
                String firstName = split[1];
                String lastName = split[2];

                Customer customer = new Customer(idInt, firstName, lastName);

                customers.add(idInt - 1, customer);
                names.add(customer.toString());
            }

            Collections.sort(names);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
