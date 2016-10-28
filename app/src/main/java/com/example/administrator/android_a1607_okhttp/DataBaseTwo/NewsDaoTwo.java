package com.example.administrator.android_a1607_okhttp.DataBaseTwo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.android_a1607_okhttp.DataBase.DbHelper;

/**
 * 
 * @类名称: NewsDao
 * @类描述: dao操作类.
 * @创建人：一一哥
 * @创建时间：2015年9月7日 下午10:17:19 
 * @备注：     
 * @version V1.0
 */
public class NewsDaoTwo {

	private SQLiteDatabase db;

	public NewsDaoTwo(Context context) {
		DbHelperTwo helper = new DbHelperTwo(context);
		db = helper.getWritableDatabase();
	}

	//添加...
	public long insert(String table, ContentValues values) {

		return db.insert(table, null, values);
	}

	//查询...
	public Cursor select(String sql, String[] selectionArgs) {
		
		return db.rawQuery(sql, selectionArgs);
	}

	//删除...
	public int delete(String table, String whereClause, String[] whereArgs) {
		
		return db.delete(table, whereClause, whereArgs);
	}
	//用来执行一个sql语句
	public void execSql(String sql, String[] args) {
		if (args != null) {
			db.execSQL(sql, args);
		} else {
			db.execSQL(sql);
		}
	}
}
