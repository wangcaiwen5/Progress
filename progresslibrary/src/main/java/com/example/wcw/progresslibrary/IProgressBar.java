package com.example.wcw.progresslibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Author:wangcaiwen
 * Time:2018/7/24
 * Description:.
 */
public class IProgressBar extends android.widget.ProgressBar {

    private Paint paint;
    private String str="";
    private String des = "";
    private int count = 0;
    private Timer timer;
    private TimerTask task;
    private int desMax = 0;
    private static final String TAG = "IProgressBar";

    public IProgressBar(Context context) {
        this(context, null);
    }

    public IProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(40);
        paint.setColor(Color.parseColor("#F2F1F6"));
    }

    /**
     * 设置进度走到哪儿
     *
     * @param desMax
     * @return
     */
    public IProgressBar setDesMax(int desMax) {
        this.desMax = desMax;
        return this;
    }

    /**
     * 设置进度描述值
     *
     * @param des
     * @return
     */
    public IProgressBar setDesText(String des) {
        this.des = des;
        return this;
    }

    public void start() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                count++;
                if (count <= desMax) {
                    Log.e(TAG, "run" );
                    setProgress(count);
                    setDesText(des);

                } else {
                    Log.e(TAG, "stop" );
                    task.cancel();
                    timer.cancel();
                }

            }
        };

        timer.schedule(task, 10, 10);
    }

    public void stop() {
        task.cancel();
        timer.cancel();

    }

    @Override
    public synchronized void setProgress(int progress) {
        //此处设置百分比.注释解开即可,如果需要显示其他样式可自行修改代码
        //setText(progress);
        super.setProgress(progress);
    }

    private void setText(int progress) {
        int i = (int) ((progress * 1.0f / this.getMax()) * 100);
        str = String.valueOf(i) + "%";
    }


    /**
     * super.onDraw(canvas);
     * Rect rect = new Rect();
     * this.mPaint.getTextBounds(this.str, 0, this.str.length(), rect);
     * int x = (getWidth() / 2) - rect.centerX();// 让现实的字体处于中心位置;;
     * int y = (getHeight() / 2) - rect.centerY();// 让显示的字体处于中心位置;;
     * canvas.drawText(this.str, x, y, this.mPaint);
     *
     * @param canvas
     */
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect();
        paint.getTextBounds(des + str, 0, (des + str).length(), rect);
        int x = (getWidth() / 10);
        int y = (getHeight() / 2) - rect.centerY();
        canvas.drawText(des + str, x, y, paint);
    }
}
