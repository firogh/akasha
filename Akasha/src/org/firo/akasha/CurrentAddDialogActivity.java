package org.firo.akasha;

import android.app.Activity;
import org.firo.akasha.CurrentActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

public class CurrentAddDialogActivity extends Activity {

	public CurrentDbHelper db = CurrentActivity.currentDb;
	public Cursor cursor;
	public ListView listview;
	public EditText actionEditText;
	public EditText descriptionEditText;
	public TimePicker addTimePicker;
	public int _id;
	public int addHour;
	public int addMinute;
	String actionString;
	String descriptionString;
	public Button DialogOkButton;
	public Button DialogNoButton;
	private Toast toast;
	private String showString;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.current_dialog_layout);

		addTimePicker = (TimePicker) findViewById(R.id.dialog_timePicker);
		actionEditText = (EditText) findViewById(R.id.action_edittext);
		descriptionEditText = (EditText) findViewById(R.id.description_edittext);

		DialogOkButton = (Button) findViewById(R.id.dialog_ok_button);
		DialogOkButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				addHour = addTimePicker.getCurrentHour();
				addMinute = addTimePicker.getCurrentMinute();
				_id = addHour * 60 + addMinute;

				actionString = actionEditText.getText().toString();
				descriptionString = descriptionEditText.getText().toString();
				showString = String.valueOf(addHour)+":"+String.valueOf(addMinute);
				if(!actionString.isEmpty())
					showString += "   "+actionString;
				if(!descriptionString.isEmpty())
					showString += "\n"+descriptionString;
				if (actionString.isEmpty() && descriptionString.isEmpty()) {
					toast = Toast.makeText(getApplicationContext(),
							"please insert anything", Toast.LENGTH_LONG);
					toast.show();
				} else {
					db.insert(_id, actionString, descriptionString, showString);

					System.out.println("\nID:" + _id + "\ndescription string:"
							+ descriptionString + "\n" + "action string:"
							+ actionString + "\n" + "hour:" + addHour
							+ "  minute " + addMinute + "\n");
					finish();
				}

			}
		});
		DialogNoButton = (Button) findViewById(R.id.dialog_no_button);
		DialogNoButton.setOnClickListener(new View.OnClickListener() {

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