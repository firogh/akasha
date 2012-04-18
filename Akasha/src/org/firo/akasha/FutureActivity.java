/*
 * Copyright (C) 2010 Draggable and Droppable ListView Project Project
 * Copyright (C) 2012 Firo yang
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.firo.akasha;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FutureActivity extends ListActivity {
	private ListView mExampleList;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.future_layout);
        
        mExampleList = getListView();
        mExampleList.setOnCreateContextMenuListener(this);
        ((DDListView) mExampleList).setDropListener(mDropListener);
        mExampleList.setAdapter(new ExampleArrayAdapter(getApplicationContext(), 
        		R.layout.rowexample,new String[]{"Row one", "Row two", "Row tree"}));
    }
    
    //ArrayAdapter
    private class ExampleArrayAdapter extends ArrayAdapter<String>{
    	private Context mContext;
    	private int mLayoutId;
    	private String[] mListContent;
		public ExampleArrayAdapter(Context context, int textViewResourceId,
				String[] objects) {
			super(context, textViewResourceId, objects);
			mContext = context;
			mLayoutId = textViewResourceId;
			mListContent = objects;
		}
		
		public View getView(int position, View rowView, ViewGroup parent) {
			if (rowView != null) {
				return rowView;
			}
			LayoutInflater inflater= LayoutInflater.from(mContext);
			View v  = inflater.inflate(mLayoutId, null);
		
			TextView rowTitle = (TextView)v.findViewById(R.id.text1);
			rowTitle.setText(mListContent[position]);
			
			return v;
		}
    	
    }
    
    //Drop Listener
    private DDListView.DropListener mDropListener =
        new DDListView.DropListener() {
        public void drop(int from, int to) {
        }
    };
}