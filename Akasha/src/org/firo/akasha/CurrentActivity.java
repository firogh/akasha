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
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class CurrentActivity extends Activity {

	public static  CurrentDbHelper currentDb = null;
	
	public Cursor currentDbCursor;
	public ListView currentListView;
	public int _id;
	
	
	
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
		if(currentDb == null){
			currentDb = new CurrentDbHelper(this);
			System.out.println("=======================create database =================");
		}
		currentDb.insert(112, "dlnu", "dlnus");
		currentDb.insert(111, "suan fad dao lun", "algorithms");
		currentDb.insert(11, "kernel", "hobby");
		currentListView = (ListView) findViewById(R.id.current_listview);
//		currentDbCursor = currentDb.select();
//		@SuppressWarnings("deprecation")
//		SimpleCursorAdapter currentAdapter = new SimpleCursorAdapter(this,
//				R.layout.currentdumb, currentDbCursor,
//				new String[] { CurrentDbHelper.FIELD_ACTION,CurrentDbHelper.FIELD_DESCRIPTION},
//				new int[] { R.id.topTextView1,R.id.topTextView2 });	
//		
//		cu rrentListView.setAdapter(currentAdapter);
		
		
		currentListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				currentDbCursor.moveToPosition(position);
				_id = currentDbCursor.getInt(0);
				if (((ListView) parent).getTag() != null) {
					((View) ((ListView) parent).getTag())
							.setBackgroundDrawable(null);
				}
				((ListView) parent).setTag(view);
				view.setBackgroundColor(Color.CYAN);// R.drawable.ic_launcher
			}
		});

		Button addTimeTaskButton = (Button) findViewById(R.id.add_button);
		addTimeTaskButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent intent;
				intent = new Intent().setClass(CurrentActivity.this,
						CurrentAddDialogActivity.class);
				intent.putExtra("_id", String.valueOf(-1));
				startActivity(intent);
				System.out.println("Just for you~~");
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
			Intent intent;
			intent = new Intent().setClass(CurrentActivity.this,
					CurrentModifyDialogActivity.class);
			System.out.println("id form edit " + _id +"\n");
			intent.putExtra("_id", _id);
			startActivity(intent);
		}

		if (cmd == "delete")
			currentDb.delete(_id);
		currentDbCursor.requery();
		currentListView.invalidateViews();
		_id = 0;
	}
	
    public void	onResume(){
    	super.onResume();
    	currentDbCursor = currentDb.select();
		@SuppressWarnings("deprecation")
		SimpleCursorAdapter currentAdapter = new SimpleCursorAdapter(this,
				R.layout.currentdumb, currentDbCursor,
				new String[] { CurrentDbHelper.FIELD_ACTION,CurrentDbHelper.FIELD_DESCRIPTION},
				new int[] { R.id.topTextView1,R.id.topTextView2 });	
		
		currentListView.setAdapter(currentAdapter);
	}
}
