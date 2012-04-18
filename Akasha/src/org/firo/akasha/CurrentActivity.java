package org.firo.akasha;

import android.app.Activity;
import org.firo.akasha.CurrentDbHelper;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class CurrentActivity extends Activity {

	public static CurrentDbHelper currentDb = null;

	public Cursor currentDbCursor;
	public ListView currentListView;
	public int _id;
	Toast toast;

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

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.current_layout);
		if (currentDb == null) {
			currentDb = new CurrentDbHelper(this);
			System.out
					.println("=======================create database =================");
		}
		currentDb.insert(112, "dlnu", "dlnus","112  dlnu \n dlnus");
		currentDb.insert(111, "suan fad dao lun", "algorithms" ,"111  suan fa dao lun \nalgorithms");
		currentDb.insert(11, "kernel", "hobby","11 kernel\nhobby");
		currentListView = (ListView) findViewById(R.id.current_listview);

		currentListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				currentDbCursor.moveToPosition(position);
				_id = currentDbCursor.getInt(0);
				if (((ListView) parent).getTag() != null) {
					((View) ((ListView) parent).getTag())
							.setBackgroundColor(Color.BLACK);
				}
				((ListView) parent).setTag(view);
				view.setBackgroundColor(Color.CYAN);
			}
		});
	}

	@SuppressWarnings("deprecation")
	private void operation(String cmd) {
		if (cmd == "add") {
			Intent intent;
			intent = new Intent().setClass(CurrentActivity.this,
					CurrentAddDialogActivity.class);
			startActivity(intent);
		}
		if (cmd == "edit") {

			if (currentListView.getTag() == null) {
				toast = Toast.makeText(getApplicationContext(),
						"Modify? please select anyone~~", Toast.LENGTH_LONG);
				toast.show();
			} else {
				Intent intent;
				intent = new Intent().setClass(CurrentActivity.this,
						CurrentModifyDialogActivity.class);
				System.out.println("id form edit " + _id + "\n");
				intent.putExtra("_id", _id);
				startActivity(intent);
				currentListView.setTag(null);
			}
		}

		if (cmd == "delete") {
			if (currentListView.getTag() == null) {
				toast = Toast.makeText(getApplicationContext(),
						"Delete? please select anyone~~", Toast.LENGTH_LONG);
				toast.show();
			} else {
				((View) currentListView.getTag())
						.setBackgroundColor(Color.BLACK);
				currentDb.delete(_id);
				currentListView.setTag(null);
			}
		}
		currentDbCursor.requery();
		currentListView.invalidateViews();
		_id = 0;
	}

	public void onResume() {
		super.onResume();
		currentDbCursor = currentDb.select();
		@SuppressWarnings("deprecation")
		SimpleCursorAdapter currentAdapter = new SimpleCursorAdapter(this,
				R.layout.currentdumb, currentDbCursor,
				new String[] { CurrentDbHelper.FIELD_SHOW },
				new int[] { R.id.topTextView1 });

		currentListView.setAdapter(currentAdapter);
	}
}
