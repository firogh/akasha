package com.firo.akasha;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class CurrentActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.current_layout);

		Button addTimeTaskButton = (Button) findViewById(R.id.add_button);

		addTimeTaskButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent intent;
				intent = new Intent().setClass(CurrentActivity.this,CurrentAddDialogActivity.class);
				startActivity(intent);
				System.out.println("Just for you~~");
				// Dialog dialog = new Dialog(CurrentActivity.this);
				// dialog.setContentView(R.layout.current_add_dialog_layout);
				// dialog.setTitle("akasha add Dialog");
				// dialog.show();
			}
		});

		


	}
}
