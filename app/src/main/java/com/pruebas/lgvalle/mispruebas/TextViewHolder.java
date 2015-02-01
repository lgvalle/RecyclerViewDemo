package com.pruebas.lgvalle.mispruebas;

import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by lgvalle on 01/02/15.
 */
public class TextViewHolder extends RecyclerView.ViewHolder{
    public TextViewHolder(LinearLayout itemView) {
        super(itemView);
    }

    public void bind(String node) {
        ((TextView)itemView.findViewById(R.id.text_row_title)).setText(node);
    }
}
