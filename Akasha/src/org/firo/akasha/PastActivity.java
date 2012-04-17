package org.firo.akasha;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TimePicker;

public class PastActivity extends Activity {
	private PastDbHelper pastDb;
	private Cursor pastDbCursor;
	private ListView pastListView;
	private EditText pastEditText;
	private int _id;
	protected final static int MENU_ADD = Menu.FIRST;
	protected final static int MENU_EDIT = Menu.FIRST + 1;
	protected final static int MENU_DELETE = Menu.FIRST + 2;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		menu.add(Menu.NONE, MENU_ADD, 0, R.string.ADD);
		menu.add(Menu.NONE, MENU_EDIT, 0, R.string.EDIT);
		menu.add(Menu.NONE, MENU_DELETE, 0, R.string.DELETE);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case MENU_ADD:
			operation("add");
			break;
		case MENU_EDIT:
			operation("edit");
			break;
		case MENU_DELETE:
			operation("delete");
			break;
		default:
			break;
		}
		return true;
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.past_layout);

		pastEditText = (EditText) findViewById(R.id.EditText1);
		pastListView = (ListView) findViewById(R.id.ListView1);
		pastDb = new PastDbHelper(PastActivity.this);
		pastDbCursor = pastDb.select();
		@SuppressWarnings("deprecation")
		SimpleCursorAdapter pastAdpater = new SimpleCursorAdapter(this,
				R.layout.dumb, pastDbCursor,
				new String[] { PastDbHelper.FIELD_TITLE },
				new int[] { R.id.topTextView });
		pastListView.setAdapter(pastAdpater);
		pastListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				pastDbCursor.moveToPosition(position);
				_id = pastDbCursor.getInt(0);
				pastEditText.setText(pastDbCursor.getString(1));
				if (((ListView) parent).getTag() != null) {
					((View) ((ListView) parent).getTag())
							.setBackgroundDrawable(null);
				}
				((ListView) parent).setTag(view);

				view.setBackgroundColor(Color.CYAN);// R.drawable.ic_launcher

			}
		});

		pastListView.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				SQLiteCursor sc = (SQLiteCursor) arg0.getSelectedItem();
				_id = sc.getInt(0);
				pastEditText.setText(sc.getString(1));
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	@SuppressWarnings("deprecation")
	private void operation(String cmd) {
		if (pastEditText.getText().toString().equals(""))
			return;
		if (cmd == "add") {
			TimePicker addTimePicker = (TimePicker) findViewById(R.id.timePicker1);
			int addHour = addTimePicker.getCurrentHour();
			int addMinute = addTimePicker.getCurrentMinute();
			String oneTimeTaskString = String.valueOf(addHour) + ":"
					+ String.valueOf(addMinute) + "\n"
					+ pastEditText.getText().toString();
			pastDb.insert(addHour * 60 + addMinute, oneTimeTaskString);
		}
		if (cmd == "edit")
			pastDb.update(_id, pastEditText.getText().toString());
		if (cmd == "delete")
			pastDb.delete(_id);
		pastDbCursor.requery();
		pastListView.invalidateViews();
		pastEditText.setText("");
		_id = 0;
	}
}