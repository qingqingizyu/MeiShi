package com.example.administrator.android_a1607_okhttp.DataBaseTwo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 * @类名称: DbHelper
 * @类描述: 创建数据库
 * @创建人：一一哥
 * @创建时间：2015年9月7日 下午10:16:56 
 * @备注：     
 * @version V1.0
 */
public class DbHelperTwo extends SQLiteOpenHelper {

	private static String DB_NAME = "Users.db";
	private static int DB_VERSION = 1;

	public DbHelperTwo(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	//建表:collet表.
	@Override
	public void onCreate(SQLiteDatabase db) {

		String sql = "CREATE TABLE users (_id INTEGER PRIMARY KEY AUTOINCREMENT,username VARCHAR(500),password VARCHAR(1000))";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
