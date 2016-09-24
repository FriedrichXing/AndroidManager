package com.joythink.xk.phonetools.view;

import java.util.Timer;
import java.util.TimerTask;

import com.joythink.xk.phonetools.phone.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class PieChartView extends View {

	private RectF oval;
	private Paint paint;
	private float sweepAngle;
	private float sweepAngle2;
	private float speed1 = 5;
	private float speed2 = 6;
	private int color_bg,color_inside,color_outside;

	public PieChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		oval = new RectF();
		paint = new Paint();
		paint.setAntiAlias(true);
		post(new Runnable() {
			
			@Override
			public void run() {
				oval.set(0, 0, getWidth(), getHeight());
			}
		});
		
		color_bg = context.getResources().getColor(R.color.color_piebg);
		color_inside = context.getResources().getColor(R.color.color_inside);
		color_outside = context.getResources().getColor(R.color.color_outside);
	}
	
	public void setAngle(final float targetAngle,final float targetAngle2){
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			

			@Override
			public void run() {
				if (sweepAngle<targetAngle) {
					sweepAngle +=speed1; 
				} else{
					sweepAngle = targetAngle;
				}
				
				if (sweepAngle2<targetAngle2) {
					sweepAngle2 +=speed2; 
				} else{
					sweepAngle2 = targetAngle2;
				}
				
				if (sweepAngle == targetAngle && sweepAngle2 == targetAngle2) {
					timer.cancel();
				}
				
				postInvalidate();
			}
		}, 300, 40);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		paint.setColor(color_bg);
		canvas.drawArc(oval, -90, 360, true, paint);
		paint.setColor(color_inside);
		canvas.drawArc(oval, -90, sweepAngle, true, paint);
		paint.setColor(color_outside);
		canvas.drawArc(oval, sweepAngle-90, sweepAngle2, true, paint);
	}
	

}
