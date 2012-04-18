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
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TimePicker;

public class PastActivity extends Activity {
	private PastDbHelper pastDb;
	private Cursor pastDbCursor;
	private ListView pastListView;
	private EditText pastEditText;
	private int _id;
	private TimePicker pastTimePicker;
	private int pastHour;
	private int pastMinute;
	private String oneTimeTaskString;
	private String oneTaskString;
	protected final static int MENU_ADD = Menu.FIRST;
	protected final static int MENU_EDIT = Menu.FIRST + 1;
	protected final static int MENU_DELETE = Menu.FIRST + 2;
	private Toast toast;

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
		pastTimePicker = (TimePicker) findViewById(R.id.pastTimePicker);

		pastDb = new PastDbHelper(PastActivity.this);
		pastDbCursor = pastDb.select();
		@SuppressWarnings("deprecation")
		SimpleCursorAdapter pastAdpater = new SimpleCursorAdapter(this,
				R.layout.dumb, pastDbCursor,
				new String[] { PastDbHelper.FIELD_SHOW },
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
							.setBackgroundColor(Color.BLACK);
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
			pastHour = pastTimePicker.getCurrentHour();
			pastMinute = pastTimePicker.getCurrentMinute();

			oneTaskString = pastEditText.getText().toString();
			oneTimeTaskString = String.valueOf(pastHour) + ":"
					+ String.valueOf(pastMinute) + "\n" + oneTaskString;
			pastDb.insert(pastHour * 60 + pastMinute, oneTaskString,
					oneTimeTaskString);
		}
		if (cmd == "edit") {
			if (pastListView.getTag() == null) {
				toast = Toast.makeText(getApplicationContext(),
						"Save? please select any modified one~~",
						Toast.LENGTH_LONG);
				toast.show();
			} else {
				int tempHour = _id / 60;
				int tempMinute = _id % 60;
				((View)pastListView.getTag()).setBackgroundColor(Color.BLACK);
				pastDb.update(
						_id,
						pastEditText.getText().toString(),
						String.valueOf(tempHour) + ":"
								+ String.valueOf(tempMinute) + "\n"
								+ pastEditText.getText().toString());
				pastListView.setTag(null);
			}
		}

		if (cmd == "delete")
		{
			if (pastListView.getTag() == null) {
				toast = Toast.makeText(getApplicationContext(),
						"Delete? please select any modified one~~",
						Toast.LENGTH_LONG);
				toast.show();
			} else {
				((View)pastListView.getTag()).setBackgroundColor(Color.BLACK);
				pastDb.delete(_id);
				pastListView.setTag(null);
			}
		}

		pastDbCursor.requery();
		pastListView.invalidateViews();
		pastEditText.setText("");
		_id = 0;
	}
}