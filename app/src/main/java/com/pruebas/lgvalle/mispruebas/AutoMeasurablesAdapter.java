package com.pruebas.lgvalle.mispruebas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lgvalle on 31/01/15.
 */
public class AutoMeasurablesAdapter extends RecyclerView.Adapter<TextViewHolder> {

    private List<String> nodes;

    public AutoMeasurablesAdapter(List<String> nodes) {
        this.nodes = nodes;
    }

    public AutoMeasurablesAdapter() {
        this.nodes = new ArrayList<>();
    }

    @Override
    public TextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.text_row, parent, false);
        return new TextViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TextViewHolder holder, int position) {
        String node = nodes.get(position);
        holder.bind(node);
    }

    @Override
    public int getItemCount() {
        return nodes.size();
    }

    public void addAll(List nodes) {
        this.nodes.addAll(nodes);
        notifyDataSetChanged();
    }

    public List<String> getNodes() {
        return nodes;
    }
}