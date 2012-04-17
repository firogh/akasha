package org.firo.akasha;


import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class AkashaActivity extends TabActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature( Window.FEATURE_NO_TITLE );
		this.getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN
                , WindowManager.LayoutParams.FLAG_FULLSCREEN);
                
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Intent intentnew;
		intentnew = new Intent().setClass(this, PastActivity.class);
		
		//TabHost akashaTabHost = (TabHost) findViewById(R.id.tabhost);
		TabHost akashaTabHost = (TabHost) getTabHost();
		akashaTabHost.setup();
		
		TabSpec akashaPastTab = akashaTabHost.newTabSpec("Past");
		akashaPastTab.setIndicator("Past");
//		akashaPastTab.setContent(R.id.text1);
		akashaPastTab.setContent(intentnew);
		akashaTabHost.addTab(akashaPastTab);
		
		intentnew = new Intent().setClass(this, CurrentActivity.class);
		TabSpec akashaCurrentTab = akashaTabHost.newTabSpec("Current");
		akashaCurrentTab.setIndicator("Current");
		akashaCurrentTab.setContent(intentnew);
//		akashaCurrentTab.setContent(R.id.text1);
		akashaTabHost.addTab(akashaCurrentTab);
		
		intentnew = new Intent().setClass(this, FutureActivity.class);
		TabSpec akashaFutureTab = akashaTabHost.newTabSpec("Future");
		akashaFutureTab.setIndicator("Future");
		akashaFutureTab.setContent(intentnew);
		akashaTabHost.addTab(akashaFutureTab);

		akashaTabHost.setCurrentTab(0);
	}

}