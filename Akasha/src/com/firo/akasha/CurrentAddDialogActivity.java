package com.firo.akasha;

import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class CurrentAddDialogActivity extends Activity {

	private DbHelper db;
	private Cursor myCursor;
	private ListView myListView;
	private EditText myEditText;
	private int _id;
	protected final static int MENU_ADD = Menu.FIRST;
	protected final static int MENU_EDIT = Menu.FIRST + 1;
	protected final static int MENU_DELETE = Menu.FIRST + 2;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		CharSequence addCharSequence = "ADD____";
		CharSequence editCharSequence = "EDIT____";
		CharSequence deleteCharSequence = "DELETE____";
		menu.add(Menu.NONE, MENU_ADD, 0, addCharSequence);
		menu.add(Menu.NONE, MENU_EDIT, 0, editCharSequence);
		menu.add(Menu.NONE, MENU_DELETE, 0, deleteCharSequence);
		return true;
	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// TODO Auto-generated method stub
//
//		super.onOptionsItemSelected(item);
//		switch (item.getItemId()) {
//		case MENU_ADD:
//			operation("add");
//			break;
//		case MENU_EDIT:
//			operation("edit");
//			break;
//		case MENU_DELETE:
//			operation("delete");
//			break;
//		default:
//			break;
//		}
//		return true;
//	}

	/** Called when the activity is first created. */
	@Override
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
				String descriptionString = descriptionEdiText.getText()
						.toString();

				System.out.println("description string" + descriptionString
						+ "\n" + "action string:" + actionString + "\n"
						+ "hour:" + addHour + "  minute " + addMinute + "\n");
				finish();
			}
		});

//		myEditText = (EditText) findViewById(R.id.EditText1);
//		myListView = (ListView) findViewById(R.id.ListView1);
//		db = new DbHelper(testDbActivity.this);
//		myCursor = db.select();
//		SimpleCursorAdapter adpater = new SimpleCursorAdapter(this,
//				R.layout.test, myCursor, new String[] { DbHelper.FIELD_TITLE },
//				new int[] { R.id.topTextView });
//		myListView.setAdapter(adpater);
//
//		myListView.setOnItemClickListener(new OnItemClickListener() {
//
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				// TODO Auto-generated method stub
//				myCursor.moveToPosition(arg2);
//				_id = myCursor.getInt(0);
//				myEditText.setText(myCursor.getString(1));
//			}
//		});
//
//		myListView.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int arg2, long arg3) {
//				// TODO Auto-generated method stub
//				SQLiteCursor sc = (SQLiteCursor) arg0.getSelectedItem();
//				_id = sc.getInt(0);
//				myEditText.setText(sc.getString(1));
//			}
//
//			public void onNothingSelected(AdapterView<?> arg0) {
//				// TODO Auto-generated method stub
//
//			}
//		});
//	}
//
//	private void operation(String cmd) {
//		if (myEditText.getText().toString().equals(""))
//			return;
//		if (cmd == "add")
//			db.insert(myEditText.getText().toString());
//		if (cmd == "edit")
//			db.update(_id, myEditText.getText().toString());
//		if (cmd == "delete")
//			db.delete(_id);
//		myCursor.requery();
//		myListView.invalidateViews();
//		myEditText.setText("");
//		_id = 0;
//
	}
}