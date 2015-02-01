package com.pruebas.lgvalle.mispruebas;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by lgvalle on 01/02/15.
 */
public class MeasurableTextView extends TextView implements MeasurableView {


    private static final String TAG = MeasurableTextView.class.getSimpleName();
    private int fontSize;

    public MeasurableTextView(Context context) {
        super(context);
        init(context);
    }

    public MeasurableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MeasurableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.fontSize = context.getResources().getDimensionPixelSize(R.dimen.font_size);

    }

    @Override
    public float automeasure() {
        String node = getText().toString();
        Paint mTextPaint = new Paint();
        mTextPaint.setTextSize(fontSize);
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(node, 0, node.length(), textBounds);
        float size = mTextPaint.measureText(node);
        Log.d(TAG, node+" -> "+size);
        return size;
    }
}
