package com.pruebas.lgvalle.mispruebas;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;


public class MyActivity extends Activity {

    private static final int MAX = 4;
    private final String DUMMY_SERVICE = "https://placeimg.com/640/480/any";

    private int numItems;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] nodes = {"I need a", "handyman", "at", "Flat 5 58 Cleveland Way E14UF, London", "to", "fix my toilet"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = getAdapter();
        mLayoutManager = getLayoutManager();

        setupLayout();

    }

    private RecyclerView.Adapter getAdapter() {
        return new AutoMeasurablesAdapter(Arrays.asList(nodes));
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        int longestSpan = getLongestSpan();
        GridLayoutManager manager = new GridLayoutManager(this, longestSpan);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int nodeLength = nodes[position].length()/2;
                if (nodeLength == 0) {
                    nodeLength++;
                }


                return Math.min(MAX, nodeLength);

            }
        });
        return manager;
    }

    private int getLongestSpan() {

        int longestNode = 0;
        for (String node : nodes) {
            if (node.length() > longestNode) {
                longestNode = node.length();
            }
        }
        return Math.min(MAX, longestNode);
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


            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
