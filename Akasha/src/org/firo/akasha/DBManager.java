package org.firo.akasha;

public class DBManager {
	private  static CurrentDbHelper currentDb = null;
	public static void initDB(CurrentActivity ca){
		if(currentDb != null)
			currentDb = new CurrentDbHelper(ca);
	}
	public static void addInfo(int _id,String action,String description){
		currentDb.insert(_id, action, description);
	}
	public static void updateInfo(int _id,String action,String description){
		currentDb.update(_id, action, description);
	}
	public static CurrentDbHelper getCurrentDb(){
		return currentDb;
	}
}
