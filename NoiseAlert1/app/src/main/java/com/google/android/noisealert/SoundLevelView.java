/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.noisealert;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.androidexample.noisealert.R;

class SoundLevelView extends View {
	private Drawable mGreen;
	private Drawable mRed;
	private Paint mBackgroundPaint;

	private int mHeight;
	private int mWidth;

	private int mThreshold = 0;
	private int mVol = 0;


	public SoundLevelView(Context context, AttributeSet attrs) {
		super(context, attrs);

		mGreen  = context.getResources().getDrawable(
				R.drawable.greenbar);
		mRed    = context.getResources().getDrawable(
				R.drawable.redbar);

		mWidth  = mGreen.getIntrinsicWidth();
		setMinimumWidth(mWidth*10);

		mHeight = mGreen.getIntrinsicHeight();
		setMinimumHeight(mHeight);

		//Used to paint canvas background color
		mBackgroundPaint = new Paint();
		mBackgroundPaint.setColor(Color.BLACK);

	}

	public void setLevel(int volume, int threshold) {
		if (volume == mVol && threshold == mThreshold) return;
		mVol = volume;
		mThreshold = threshold;

		// invalidate Call onDraw method and draw voice points
		invalidate();
	}

	@Override
	public void onDraw(Canvas canvas) {

		canvas.drawPaint(mBackgroundPaint);

		for (int i=0; i<= mVol; i++) {
			Drawable bar;
			if (i< mThreshold)
				bar = mGreen;
			else
				bar = mRed;

			bar.setBounds((10-i)*mWidth, 0, (10-i+1)*mWidth, mHeight);
			bar.draw(canvas);
		}
	}
}