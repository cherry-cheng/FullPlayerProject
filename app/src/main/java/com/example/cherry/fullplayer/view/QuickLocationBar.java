package com.example.cherry.fullplayer.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/12/15.
 */

public class QuickLocationBar extends View {

    private String characters[] = {"#", "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};
    private int choose = -1;
    private Paint paint = new Paint();
    private OnTouchLetterChangedListener mOnTouchLetterChangedListener;
    private TextView mTextDialog;

    public QuickLocationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
    }

    public QuickLocationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public QuickLocationBar(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public void setOnTouchLitterChangedListener(
            OnTouchLetterChangedListener onTouchLetterChangedListener) {
        this.mOnTouchLetterChangedListener = onTouchLetterChangedListener;
    }

    public void setTextDialog(TextView dialog) {
        this.mTextDialog = dialog;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int singleHeight = height / characters.length;
        for (int i = 0; i < characters.length; i++) {
            // 对paint进行相关的参数设置
            paint.setColor(Color.parseColor("#FF00CFA1"));
            paint.setTypeface(Typeface.DEFAULT_BOLD);
            paint.setAntiAlias(true);
            paint.setTextSize(150 * (float) width / 320);
            if (i == choose) {// choose变量表示当前显示的字符  ，若没有触摸则为-1
                paint.setColor(Color.RED);
                paint.setFakeBoldText(true);
            }
            // 计算字符的绘制的位置
            float xPos = width / 2 - paint.measureText(characters[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            // 在画布上绘制字符
            canvas.drawText(characters[i], xPos, yPos, paint);
            paint.reset();// 每次绘制完成后不要忘记重制Paint
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float y = event.getY();
        int c = (int) (y / getHeight() * characters.length);

        switch (action) {
            case MotionEvent.ACTION_UP:
                choose = -1;//
//                setBackgroundColor(Color.parseColor("#ffffffff"));
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.GONE);
                }
                break;

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
//                setBackgroundColor(Color.DKGRAY);
                if (choose != c) {
                    if (c >= 0 && c < characters.length) {
                        if (mOnTouchLetterChangedListener != null) {
                            mOnTouchLetterChangedListener
                                    .touchLetterChanged(characters[c]);
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(characters[c]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }
                        choose = c;
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }

    public interface OnTouchLetterChangedListener {
        public void touchLetterChanged(String s);
    }

}

