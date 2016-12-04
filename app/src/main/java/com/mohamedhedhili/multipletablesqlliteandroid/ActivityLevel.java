package com.mohamedhedhili.multipletablesqlliteandroid;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import PackageModel.Categorie;
import PackageModel.DatabaseHelper;
import PackageModel.Question;


public class ActivityLevel extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ArrayList<String> listIndex = new ArrayList<String>();
    ArrayList<String> listIndex_Search = new ArrayList<String>();
    ArrayList<Categorie> listCategorie=new ArrayList<Categorie>();
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_level);

        listView = (ListView) findViewById(R.id.listView);
        db = new DatabaseHelper(getApplicationContext());
        listCategorie= (ArrayList<Categorie>) db.getAllCategorie();

        for (int i=0;i<listCategorie.size();i++)
        {
            listIndex.add(listCategorie.get(i).getLibelle());

        }
        db.closeDB();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) listView.getItemAtPosition(position);
                long cat = db.CheckCategorie(item);
                List<Question> questions = db.getAllQuestion((int) cat);
                if (questions.size() > 0) {
                    Bundle b = getIntent().getExtras();
                    int x = b.getInt("idUser");
                    Intent i = new Intent(getApplicationContext(), QuestionsActivity.class);
                    i.putExtra("category", item);
                    i.putExtra("idUser", x);
                    startActivity(i);
                }else
                { Toast.makeText(getApplication(), "Not Data found " , Toast.LENGTH_SHORT).show();}




            }
    });

     //   String[] items = getResources().getStringArray(R.array.index);

     //   for (String i : items) {
     //       listIndex.add(i);
       // }
   All_Level();


    }

    public void All_Level() {
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listIndex);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.menu_searchable));
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        search(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        search(newText);
        return false;
    }

    public void search(String word) {
        if (word.length() > 1) {
            listIndex_Search.clear();
            for (int i = 0; i < listIndex.size(); i++) {
                String name = listIndex.get(i);
                if (name.contains(word)) {
                    listIndex_Search.add(name);
                }
            }


            if (word.equals(""))
            {  String[] items = getResources().getStringArray(R.array.index);

                for (String i : items) {
                    listIndex.add(i);
                }
                All_Level();}
            else
            {
                arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listIndex_Search);
                listView.setAdapter(arrayAdapter);
            }



        }

    }
}

