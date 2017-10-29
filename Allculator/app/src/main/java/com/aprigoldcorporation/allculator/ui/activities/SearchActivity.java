package com.aprigoldcorporation.allculator.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aprigoldcorporation.allculator.R;
import com.aprigoldcorporation.allculator.ui.adapter.SearchAdapter;

import java.util.Arrays;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    public static final int SEARCH_ACTIVITY_REQUEST = 987;
    ArrayAdapter<String> adapter;

    public static void getInstance(Activity activity,int senderId, int search_data_id){
        Intent intent = new Intent(activity,SearchActivity.class);
        intent.putExtra("adapterData",search_data_id);
        intent.putExtra("sender_id",senderId);
        activity.startActivityForResult(intent,SEARCH_ACTIVITY_REQUEST);
    }

    public static void getInstance(Activity activity,int senderId, String[] search_data_array){
        Intent intent = new Intent(activity,SearchActivity.class);
        intent.putExtra("adapterData",search_data_array);
        intent.putExtra("sender_id",senderId);
        activity.startActivityForResult(intent,SEARCH_ACTIVITY_REQUEST);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ListView listView = (ListView) findViewById(R.id.search_activity_listview);

        Object adapterData = getIntent().getExtras().get("adapterData");
        String[] adapterArray;
        if (adapterData instanceof Integer) {
            int array_id = ((int) adapterData);
            if (array_id == 0) {
                Toast.makeText(this, "Array id getirilemedi", Toast.LENGTH_SHORT).show();
                return;
            }
            adapterArray = getResources().getStringArray(array_id);

        }
        else{
            adapterArray = ((String[]) adapterData);
        }
        adapter = new SearchAdapter(this,R.layout.search_list_row, Arrays.asList(adapterArray));
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent result = new Intent();
                result.putExtra("selectedIndex",position);
                result.putExtra("sender_id",getIntent().getIntExtra("sender_id",0));
                setResult(RESULT_OK,result);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem searchItem = menu.findItem(R.id.menu_btn_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
