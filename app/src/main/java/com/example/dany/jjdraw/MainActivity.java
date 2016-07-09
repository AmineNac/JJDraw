package com.example.dany.jjdraw;

import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.graphics.Color;
/**
 * Copyright (c) 2016 Dany Madden
 * Please see LICENSE file for licensing detail.
 *
 * Based on Sue Smith's tutorial:
 * http://code.tutsplus.com/tutorials/android-sdk-create-a-drawing-app-interface-creation--mobile-19021
 **/



public class MainActivity extends AppCompatActivity {
	private DrawingView drawView;
	private ImageButton currPaint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		drawView = (DrawingView) findViewById(R.id.drawing);
		LinearLayout paintLayout = (LinearLayout)findViewById(R.id.paint_colors);
		currPaint = (ImageButton)paintLayout.getChildAt(0);
		currPaint.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.paint_pressed, null));
    }
	
	public void paintClicked (View view) {
		if (view != currPaint){
			ImageButton imgView = (ImageButton)view;
			String color = view.getTag().toString();
			drawView.setColor(color);

			imgView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.paint_pressed, null));
			currPaint.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.paint, null));
			currPaint=(ImageButton)view;
		}
	}
}
