package com.pruebas.lgvalle.mispruebas;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity {

    private final String DUMMY_SERVICE = "https://placeimg.com/640/320/";

    private int numItems;
    private RecyclerView mRecyclerView;
    private DummyAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new DummyAdapter();
        mLayoutManager = new LinearLayoutManager(this);

        setupLayout();

    }

    private void setupLayout() {
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.addFirst(createDummyItem());
    }

    private Item createDummyItem() {
        return new Item("Photo " + (++numItems), DUMMY_SERVICE + numItems);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_add) {
            mAdapter.addFirst(createDummyItem());
            mLayoutManager.scrollToPosition(0);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
