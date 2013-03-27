package com.koraysels.SimplePieChartView;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

import java.text.DecimalFormat;
import java.util.List;

public class PieChartView extends View {
    private static final int WAIT = 0;
    private static final int IS_READY_TO_DRAW = 1;
    private static final int IS_DRAW = 2;
    private static final float START_INC = 30;
    private Paint mBackgroundPaint = new Paint();
    private Paint mTextPaint = new Paint();
    private int mOverlayId;
    private int mWidth;
    private int mHeight;
    private int mGapLeft;
    private int mGapRight;
    private int mGapTop;
    private int mGapBottom;
    private int mState = WAIT;
    private float mStart;
    private float mSweep;
    private List<PieItem> mDataArray;
    private float Percent;
    private Typeface font;
    private float fontsize;
    private int textColor;

    public PieChartView(Context context) {
        super(context);
    }

    public PieChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mState != IS_READY_TO_DRAW)
            return;
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setStyle(Paint.Style.FILL);


        RectF mOvals = new RectF(mGapLeft, mGapTop, mWidth - mGapRight, mHeight
                - mGapBottom);

        mStart = START_INC;
        PieItem Item;
        for (PieItem aMDataArray : mDataArray) {
            Item = aMDataArray;
            mBackgroundPaint.setColor(Item.getColor());
            Item = aMDataArray;
            Percent = Item.getPercent();
            mSweep = (360 * Percent);
            canvas.drawArc(mOvals, mStart, mSweep, true, mBackgroundPaint);
            mStart += mSweep;
        }

        drawLegend(canvas);

        mState = IS_DRAW;
    }

    private void drawLegend(Canvas canvas) {
        PieItem Item;

        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(textColor);
        mTextPaint.setTextSize(fontsize);
        mTextPaint.setTypeface(font);

        mStart = START_INC;
        float lblX;
        float lblY;
        DecimalFormat FloatFormatter = new DecimalFormat("0.## %");
        float CenterOffset = (mWidth / 2); // Pie Center from Top-Left origin
        float Conv = (float) (2 * Math.PI / 360); // Constant for convert Degree
        // to rad.
        float Radius = 2 * (mWidth / 2) / 3; // Radius of the circle will be
        // drawn the legend.
        Rect bounds = new Rect();
        for (PieItem aMDataArray : mDataArray) {
            Item = aMDataArray;
            Percent = Item.getPercent();
            mSweep = (360 * Percent);
            // Format Label
            String Label = Item.getLabel() + " "
                    + FloatFormatter.format(Percent);
            // Get Label width and height in pixels
            mTextPaint.getTextBounds(Label, 0, Label.length(), bounds);
            // Calculate final coordinates for Label positions
            lblX = (float) (CenterOffset + Radius
                    * Math.cos(Conv * (mStart + mSweep / 2)))
                    - bounds.width() / 2;
            lblY = (float) (CenterOffset + Radius
                    * Math.sin(Conv * (mStart + mSweep / 2)))
                    + bounds.height() / 2;
            canvas.drawText(Label, lblX, lblY, mTextPaint);
            mStart += mSweep;
        }
    }

    public void setGeometry(int width, int height, int GapLeft, int GapRight,
                            int GapTop, int GapBottom) {
        mWidth = width;
        mHeight = height;
        mGapLeft = GapLeft;
        mGapRight = GapRight;
        mGapTop = GapTop;
        mGapBottom = GapBottom;
        mOverlayId = 0;
    }

    public void setData(List<PieItem> data) {
        mDataArray = data;
        mState = IS_READY_TO_DRAW;
    }

    public void setLabelFont(Typeface font, float textSize, int textColor) {
        this.font = font;
        this.fontsize = textSize;
        this.textColor = textColor;
    }

}