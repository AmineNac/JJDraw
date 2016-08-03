package com.example.dany.jjdraw;

import android.graphics.Paint;
import android.graphics.Path;
/**
 * Copyright (c) 2016 Dany Madden
 * This is released under the MIT license.
 * Please see LICENSE file for licensing detail.
 *
 **/
public class Stroke extends Object {
    int color;
    Paint paint;
    Path path;
    public Stroke() {
        paint = new Paint();
        color = 0xFFFFFFFF;
        path = new Path();
    }

    public void setColor(int c) {

        color = c;
    }

    public void setPaint(Paint p) {

        paint = p;
    }

    public void setPath(Path p) {

        path = p;
    }

}
