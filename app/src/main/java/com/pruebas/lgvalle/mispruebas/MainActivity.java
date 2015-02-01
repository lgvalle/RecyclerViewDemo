package com.pruebas.lgvalle.mispruebas;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by lgvalle on 31/01/15.
 */
public class MainActivity extends Activity {
    int COLS = 5;
    private String[] nodesSrc = {"I need a", "at", "Flat 5 58 Cleveland Way E14UF, London", "handyman", "to", "fix my toilet"};

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private TextViewAdapter adapter;
    private int index;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setupLayout();
        index = 0;
    }

    private void setupLayout() {
        recyclerView = (RecyclerView) findViewById(R.id.autofitView);
        AutoSpanGridLayoutManager layoutManager = new AutoSpanGridLayoutManager(this, COLS);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new TextViewAdapter(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (index < nodesSrc.length) {
            adapter.addItem(nodesSrc[index++]);
            return true;
        }
        return false;

    }
}

