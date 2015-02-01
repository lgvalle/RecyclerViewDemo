package com.pruebas.lgvalle.mispruebas;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by lgvalle on 31/01/15.
 */
public class AutoSpanGridLayoutManager extends GridLayoutManager {
    private static final String TAG = AutoSpanGridLayoutManager.class.getSimpleName();
    private AutoMeasurablesAdapter adapter;
    private RecyclerView recyclerView;

    public AutoSpanGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
        init(context, spanCount);
    }

    public AutoSpanGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
        init(context, spanCount);
    }



    private void init(final Context context, final int spanCount) {
        this.adapter = new AutoMeasurablesAdapter();
        setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                int spanSize = 1;
                if (recyclerView != null ) {
                    final int columWidth = recyclerView.getMeasuredWidth() / spanCount;
                    Log.d(TAG, "ColunmWidth: "+columWidth);
                    //MeasurableView m = (MeasurableView) getChildAt(position);
                    //float viewWidth = m.automeasure();
                    float viewWidth = innerMeasure(position, context);

                    double calculatedSpan = Math.ceil(viewWidth / columWidth);

                    spanSize = (int) Math.min(spanCount, Math.max(1, calculatedSpan));
                }
                Log.d(TAG, "SPAN: "+spanSize);
                return spanSize;
            }
        });
    }

    private float innerMeasure(int position, Context context) {
        String node = getAdapter().getNodes().get(position);
        int fontSize = context.getResources().getDimensionPixelSize(R.dimen.font_size);
        Paint mTextPaint = new Paint();
        mTextPaint.setTextSize(fontSize);
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(node, 0, node.length(), textBounds);
        return mTextPaint.measureText(node);
    }

    /*
    final int columWidth = recyclerView.getMeasuredWidth() / spanCount;
                    String node = adapter.getNodes().get(position);
                    MeasurableView m = (MeasurableView) getChildAt(position);
                    float viewWidth = m.automeasure();


                    Paint mTextPaint = new Paint();
                    mTextPaint.setTextSize(fontSize);
                    Rect textBounds = new Rect();
                    mTextPaint.getTextBounds(node, 0, node.length(), textBounds);
                    float mTextWidth = mTextPaint.measureText(node);

                    double calculatedSpan = Math.ceil(mTextWidth / columWidth);

                    spanSize = (int) Math.min(spanCount, Math.max(1, calculatedSpan));
     */

    public AutoMeasurablesAdapter getAdapter() {
        return adapter;
    }


    public void setMyRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
}
