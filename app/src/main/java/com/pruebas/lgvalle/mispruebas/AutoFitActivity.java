package com.pruebas.lgvalle.mispruebas;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;

/**
 * Created by lgvalle on 31/01/15.
 */
public class AutoFitActivity extends Activity {
    int COLS = 5;
    private static final String TAG = AutoFitActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private String[] nodes = {"I need a", "handyman", "at", "Flat 5 58 Cleveland Way E14UF, London", "to", "fix my toilet"};
    private AutoMeasurablesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_autofit);
        setupLayout();
    }

    private void setupLayout() {
        AutoSpanGridLayoutManager layoutManager = getLayoutManager();
        adapter = layoutManager.getAdapter();
        adapter.addAll(Arrays.asList(nodes));

        recyclerView = (RecyclerView) findViewById(R.id.autofitView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        layoutManager.setMyRecyclerView(recyclerView);


    }

    private AutoSpanGridLayoutManager getLayoutManager() {
        return new AutoSpanGridLayoutManager(this, COLS);
    }


}
