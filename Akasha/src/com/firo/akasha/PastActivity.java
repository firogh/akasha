package com.firo.akasha;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PastActivity extends Activity{
	public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
  
        TextView textview = new TextView(this);  
        textview.setText("This is the past tab");  
        setContentView(R.layout.past_layout);  
    }  
}