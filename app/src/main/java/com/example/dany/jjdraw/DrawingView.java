package com.example.dany.jjdraw;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.content.res.ResourcesCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.MotionEvent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.TypedValue;
import android.widget.ImageView;
import android.graphics.BitmapFactory;
import android.util.Log;

/**
 * Copyright (c) 2016 Dany Madden
 * This is released under the MIT license.
 * Please see LICENSE file for licensing detail.
 *
 * Based on Sue Smith's tutorial: 
 * http://code.tutsplus.com/tutorials/android-sdk-create-a-drawing-app-interface-creation--mobile-19021
 **/



public class DrawingView extends ImageView {

	private Path drawPath;
	private Paint drawPaint;
	private Paint canvasPaint;
	// initial color
	private int paintColor = 0xFF660000;
	private Canvas drawCanvas;
	private Bitmap canvasBitmap;
	private float brushSize, lastBrushSize;
	private boolean erase = false;

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }
    private void setupDrawing() {
		brushSize = getResources().getInteger(R.integer.medium_size);
		lastBrushSize = brushSize;

		drawPath = new Path();
		drawPaint = new Paint();

		drawPaint.setColor(paintColor);
		drawPaint.setAntiAlias(true);
		drawPaint.setStrokeWidth(brushSize);
		drawPaint.setStyle(Paint.Style.STROKE);
		drawPaint.setStrokeJoin(Paint.Join.ROUND);
		drawPaint.setStrokeCap(Paint.Cap.ROUND);

		canvasPaint = new Paint(Paint.DITHER_FLAG);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    	super.onSizeChanged (w, h, oldw, oldh);
		canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		drawCanvas = new Canvas(canvasBitmap);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
		canvas.drawPath(drawPath, drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
		float touchX = event.getX();
      	float touchY = event.getY();

    	switch (event.getAction()) {
		// user touches a location, move there
		case MotionEvent.ACTION_DOWN:
    		drawPath.moveTo(touchX, touchY);
    		break;
		// draw line when move
		case MotionEvent.ACTION_MOVE:
    		drawPath.lineTo(touchX, touchY);
    		break;
		// user releases the touch
		case MotionEvent.ACTION_UP:
    		drawCanvas.drawPath(drawPath, drawPaint);
    		drawPath.reset();
    		break;
		default:
    		return false;
		}
		invalidate();
		return true;
	
    }
	public void setBrushSize(float newSize) {
		brushSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				newSize, getResources().getDisplayMetrics());
		drawPaint.setStrokeWidth(brushSize);
	}

	public void setLastBrushSize(float lastSize) {
		lastBrushSize = lastSize;
	}

	public float getLastBrushSize() {
		return lastBrushSize;
	}

	public void setColor(String newColor) {
		invalidate();
		paintColor = Color.parseColor(newColor);
		drawPaint.setColor(paintColor);
	}

	public void setErase(boolean isErase) {
		erase = isErase;

		if(erase)
			drawPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		else
			drawPaint.setXfermode(null);
	}

	public void startNew(String path){

		if (path == null) {
			drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
			invalidate();
			setErase(false);
		} else {
			Bitmap workingBitmap = 	BitmapFactory.decodeFile(path);
			Bitmap mutableBitmap = workingBitmap.copy(Bitmap.Config.ARGB_8888, true);
			drawCanvas.drawBitmap(mutableBitmap, 0, 0, canvasPaint);
			setImageBitmap(mutableBitmap);

			//drawCanvas = new Canvas(mutableBitmap);
			Log.d("DEBUG", "should set image background");
			//invalidate();
			//setErase(false);
		}
	}

}
