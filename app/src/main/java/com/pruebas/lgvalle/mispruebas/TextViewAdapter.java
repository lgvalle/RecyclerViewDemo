package com.pruebas.lgvalle.mispruebas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

/**
 * Created by lgvalle on 31/01/15.
 */
public class TextViewAdapter extends RecyclerView.Adapter<TextViewAdapter.TextViewHolder> {
    private static final String TAG = TextViewAdapter.class.getSimpleName();
    private final AutoSpanGridLayoutManager autoSpanManager;
    private String[] nodesSrc = {"I need a", "handyman", "at", "Flat 5 58 Cleveland Way E14UF, London", "to", "fix my toilet"};
    private List<String> nodes;


    public TextViewAdapter(AutoSpanGridLayoutManager autoSpanGridLayoutManager) {
        this.autoSpanManager = autoSpanGridLayoutManager;
        this.nodes = Arrays.asList(nodesSrc);
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_row, parent, false);
        return new TextViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        String node = nodes.get(position);
        holder.bind(node);
        MeasurableView itemView = (MeasurableView) holder.itemView;
        autoSpanManager.calculateItemSpan(position, itemView.automeasure());
    }

    @Override
    public int getItemCount() {
        return nodes.size();
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