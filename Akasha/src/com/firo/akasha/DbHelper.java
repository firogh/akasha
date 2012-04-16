package com.firo.akasha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

	private final static String DATABASE_NAME = "sec_db";
	private final static int DATABASE_VERSION = 1;
	private final static String TABLE_NAME = "sec_pwd";
	public final static String FIELD_ID = "_id";
	public final static String FIELD_TITLE = "sec_Title";

	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "Create table " + TABLE_NAME + "(" + FIELD_ID
				+ " integer primary key ," + FIELD_TITLE
				+ " text );";
		db.execSQL(sql);// autoincrement  
		//insert into tablename(field,,) values(i,);
		// String sqlStr =
		// "INSERT INTO tablename (f1,f2,f3) values ('"+f1_value+"','"+f2_+"','')";
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String sql = " DROP TABLE IF EXISTS " + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}

	/*
	 * query(table, columns, selection, selectionArgs, groupBy, having,
	 * orderBy,limit)方法各参数的含义：
	 * table：表名。相当于select语句from关键字后面的部分。如果是多表联合查询，可以用逗号将两个表名分开。
	 * columns：要查询出来的列名。相当于select语句select关键字后面的部分。
	 * selection：查询条件子句，相当于select语句where关键字后面的部分，在条件子句允许使用占位符“?”
	 * selectionArgs：对应于selection语句中占位符的值，值在数组中的位置与占位符在语句中的位置必须一致，否则就会有异常。
	 * groupBy：相当于select语句group by关键字后面的部分 having：相当于select语句having关键字后面的部分
	 * orderBy：相当于select语句order by关键字后面的部分，如：personid desc, age asc;
	 * limit：指定偏移量和获取的记录数，相当于select语句limit关键字后面的部分。
	 */
	public Cursor select() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null,
				" _id desc");
		return cursor;
	}

	public long insert(Integer i, String Title) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(FIELD_ID, i);
		cv.put(FIELD_TITLE, Title);
		long row = db.insert(TABLE_NAME, null, cv);
		return row;
	}

	public void delete(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = FIELD_ID + "=?";
		String[] whereValue = { Integer.toString(id) };
		db.delete(TABLE_NAME, where, whereValue);
	}

	public void update(int id, String Title) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = FIELD_ID + "=?";
		String[] whereValue = { Integer.toString(id) };
		ContentValues cv = new ContentValues();
		cv.put(FIELD_TITLE, Title);
		db.update(TABLE_NAME, cv, where, whereValue);
	}

}
