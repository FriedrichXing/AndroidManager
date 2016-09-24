package com.joythink.xk.phonetools.view;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class ClearView extends View {
	private int state;
	protected static final int STATE_FORWARD = 1;
	protected static final int STATE_BACKWARD = 0;
	private int [] speed_f = {11,10,9,8,7,6,5,4};
	private int [] speed_b = {4,5,6,7,8,9,10,11};
	private int index_b,index_f;
	private RectF oval;
	private float startAngle = -90;
	private float sweepAngle = 360;
	private Paint paint;
	private boolean isRunning;
	private AngleChangeListener listener;
	

	public void setListener(AngleChangeListener listener) {
		this.listener = listener;
	}

	public ClearView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		oval = new RectF();
		paint = new Paint();
		paint.setAntiAlias(true);
		post(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				oval.set(0, 0, getWidth(), getHeight());
			}
		});
	}

	public void setAngleWithAnim(final int targetAngle) {
		// TODO Auto-generated method stub
		final Timer timer = new Timer();
		if (isRunning) {
			return;
		}
		isRunning = true;
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				switch (state) {
				case STATE_BACKWARD:
					sweepAngle -= speed_b[index_b++];
					if (index_b >= speed_b.length) {
						index_b = speed_b.length-1;
					}
					if (sweepAngle <= 0) {
						sweepAngle = 0;
						state = STATE_FORWARD;
					}
					break;
				case STATE_FORWARD:
					sweepAngle += speed_f[index_f++];
					if (index_f >= speed_f.length) {
						index_f = speed_f.length-1;
					}
					if (sweepAngle >= targetAngle) {
						sweepAngle = targetAngle;
						timer.cancel();
						isRunning = false;
						state = STATE_BACKWARD;
					}
					break;

				default:
					break;
				}
				
				if (listener!=null) {
					listener.setAngle(sweepAngle);
				}
				postInvalidate();
			}
		}, 500, 30);
	}

	// @Override
	// protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	// // TODO Auto-generated method stub
	// int w = MeasureSpec.getSize(widthMeasureSpec);
	// int h = MeasureSpec.getSize(heightMeasureSpec);
	// setMeasuredDimension(w, h);
	// oval.set(0, 0, w, h);
	// }

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		paint.setColor(Color.GREEN);
		canvas.drawArc(oval, startAngle, sweepAngle, true, paint);
		// paint.setColor(Color.BLUE);
		// canvas.drawArc(oval, startAngle + 90, sweepAngle, true, paint);
		// paint.setColor(Color.RED);
		// canvas.drawArc(oval, startAngle + 180, sweepAngle, true, paint);
		// paint.setColor(Color.YELLOW);
		// canvas.drawArc(oval, startAngle + 270, sweepAngle, true, paint);
	}
	
	public interface AngleChangeListener{
		void setAngle(float sweepAngle);
	}
}
