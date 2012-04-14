package com.firo.akasha;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class CurrentAddDialogActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.current_add_dialog_layout);
		
		// Dialog dialog = new Dialog(CurrentAddDialogActivity.this);
		//
		// dialog.setContentView(R.layout.current_add_dialog_layout);
		// dialog.setTitle("akasha add Dialog");
		//
		// dialog.show();
		//
		Button currentAddDialogOkButton = (Button) findViewById(R.id.add_ok_button);
		currentAddDialogOkButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(getApplicationContext(),
						"I am Clicked", Toast.LENGTH_LONG);// 提示被点击了
				toast.show();

				TimePicker addTimePicker = (TimePicker) findViewById(R.id.add_timePicker);
				int addHour = addTimePicker.getCurrentHour();
				int addMinute = addTimePicker.getCurrentMinute();
				
				EditText actionEditText = (EditText) findViewById(R.id.action_edittext);
				String actionString = actionEditText.getText().toString();
				EditText descriptionEdiText = (EditText) findViewById(R.id.description_edittext);
				String descriptionString = descriptionEdiText.getText().toString();
				
				System.out.println("description string"+ descriptionString+"\n"+"action string:"+ actionString+"\n"+"hour:" + addHour + "  minute " + addMinute
						+ "\n");
				finish();
			}
		});

	}
}