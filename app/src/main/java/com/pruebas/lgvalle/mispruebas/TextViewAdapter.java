package com.pruebas.lgvalle.mispruebas;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lgvalle on 31/01/15.
 */
public class TextViewAdapter extends RecyclerView.Adapter<TextViewAdapter.TextViewHolder> {
    private static final String TAG = TextViewAdapter.class.getSimpleName();
    private final AutoSpanGridLayoutManager autoSpanManager;

    private List<String> nodes;

    public TextViewAdapter(AutoSpanGridLayoutManager autoSpanGridLayoutManager, List<String> nodesSrc) {
        this.autoSpanManager = autoSpanGridLayoutManager;
        this.nodes = nodesSrc;
    }

    public TextViewAdapter(AutoSpanGridLayoutManager autoSpanGridLayoutManager) {
        this.autoSpanManager = autoSpanGridLayoutManager;
        this.nodes = new ArrayList<>();
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_row, parent, false);
        return new TextViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        Log.d(TAG, "On bind view holder: "+position);
        String node = nodes.get(position);
        Log.d(TAG, "node text: "+node);
        holder.bind(node);
        MeasurableTextView itemView = (MeasurableTextView) holder.itemView;
        Log.d(TAG, "text view: "+itemView.getText().toString());
        autoSpanManager.calculateItemSpan(position, itemView.automeasure());
    }

    @Override
    public int getItemCount() {
        return nodes.size();
    }

    public void addItem(String s) {
        int position = nodes.size();
        nodes.add(position, s);
        Log.d(TAG, "Adding: "+s);
        notifyItemInserted(position);
    }

    class TextViewHolder extends RecyclerView.ViewHolder{
        public TextViewHolder(View itemView) {
            super(itemView);
        }

        public void bind(String node) {
            ((MeasurableTextView)itemView).setText(node);
        }
    }

}