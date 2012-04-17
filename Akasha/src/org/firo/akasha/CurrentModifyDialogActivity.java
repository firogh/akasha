package org.firo.akasha;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

public class CurrentModifyDialogActivity extends Activity {

	public CurrentDbHelper db = CurrentActivity.currentDb;
	public Cursor cursor;
	public ListView listview;
	public EditText actionEditText;
	public EditText descriptionEditText;
	public TimePicker modifyTimePicker;
	public int _id;
	public int modifyHour;
	public int modifyMinute;
	String actionString;
	String descriptionString;
	int numColumn;
	int totalTime;
	public Button currentDialogOkButton;
	public Button currentDialogNoButton;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.current_dialog_layout);

		Intent intent = this.getIntent();
		_id = intent.getIntExtra("_id", -2);
		System.out.println("print for modify dialog " + _id + "\n");

		if (_id != -2) {
			System.out.println("before " + _id + "\n");
			cursor = db.select(_id);
			cursor.moveToFirst();
			System.out.println("after " + _id + "\n");
			modifyTimePicker = (TimePicker) findViewById(R.id.dialog_timePicker);
			numColumn = cursor.getColumnIndex(CurrentDbHelper.FIELD_ID);
			totalTime = cursor.getInt(numColumn);
			actionString = cursor.getString(cursor
					.getColumnIndex(CurrentDbHelper.FIELD_ACTION));
			descriptionString = cursor.getString(cursor
					.getColumnIndex(CurrentDbHelper.FIELD_DESCRIPTION));

			actionEditText = (EditText) findViewById(R.id.action_edittext);
			descriptionEditText = (EditText) findViewById(R.id.description_edittext);
			modifyHour = totalTime / 60;
			modifyMinute = totalTime % 60;

			modifyTimePicker.setCurrentHour(modifyHour);
			modifyTimePicker.setCurrentMinute(modifyMinute);
			actionEditText.setText(actionString);
			descriptionEditText.setText(descriptionString);
		}

		currentDialogOkButton = (Button) findViewById(R.id.dialog_ok_button);
		currentDialogOkButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(getApplicationContext(),
						"I am Clicked", Toast.LENGTH_LONG);// 提示被点击了
				toast.show();

				modifyHour = modifyTimePicker.getCurrentHour();
				modifyMinute = modifyTimePicker.getCurrentMinute();
				int temp_id = modifyHour * 60 + modifyMinute;

				actionString = actionEditText.getText().toString();
				descriptionString = descriptionEditText.getText().toString();

				System.out.println("\nID:" + _id + "before \n");
				if (temp_id != _id) {
					db.delete(_id);
					_id = temp_id;
					db.insert(_id, actionString, descriptionString);
					System.out.println("\n 1 insert\n");
				} else {
					db.update(_id, actionString, descriptionString);
					System.out.println("\n 2 update\n");
				}
				System.out.println("\nID:" + _id + "\n description string"
						+ descriptionString + "\n" + "action string:"
						+ actionString + "\n" + "hour:" + modifyHour
						+ "  minute " + modifyMinute + "\n");
				finish();
			}
		});
		currentDialogNoButton = (Button) findViewById(R.id.dialog_no_button);
		currentDialogNoButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast toast = Toast.makeText(getApplicationContext(), "no~~",
						Toast.LENGTH_LONG);
				toast.show();
				finish();
			}
		});
	}
}