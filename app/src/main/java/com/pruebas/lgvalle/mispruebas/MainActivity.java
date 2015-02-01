package com.pruebas.lgvalle.mispruebas;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

/**
 * Created by lgvalle on 31/01/15.
 */
public class MainActivity extends Activity {
    int COLS = 5;
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autofit);
        setupLayout();
    }

    private void setupLayout() {
        recyclerView = (RecyclerView) findViewById(R.id.autofitView);

        AutoSpanGridLayoutManager layoutManager = new AutoSpanGridLayoutManager(this, COLS);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new TextViewAdapter(layoutManager));
    }

}
