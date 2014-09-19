package com.soucheng.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.soucheng.vo.Config;
import com.soucheng.vo.User;

/**
 * 数据库适配器
 *
 * @author lichen
 */
public class DbAdapter {

	private static final String DATABASE_NAME = "soucheng.db";

	private Context context;

	private SQLiteDatabase db;
	private DatabaseHelper dbHelper;

	public DbAdapter(Context context) {
		this.context = context;
		dbHelper = DatabaseHelper.init(this.context, DATABASE_NAME, null, DbConstants.DATABASE_VERSION);
	}

	/**
	 * 打开数据库
	 */
	public void open() {
		try {
			db = dbHelper.getWritableDatabase();
		} catch (Exception e) {
			db = dbHelper.getReadableDatabase();
		}
	}

	public void close() {
		dbHelper.close();
	}

	/**
	 * 保存用户
	 *
	 * @param user 用户
	 */
	public void saveUser(User user) {
		ContentValues values = new ContentValues();
		values.put(DbConstants.COLUMN_USER_USERNAME, user.getUsername());
		values.put(DbConstants.COLUMN_USER_PASSWORD, user.getPassword());
		db.insert(DbConstants.TABLE_USER, null, values);
	}

	/**
	 * 查询用户
	 *
	 * @param username 用户名
	 */
	public User getUser(String username) {
		Cursor cursor = db.query(DbConstants.TABLE_USER,
				new String[]{DbConstants.COLUMN_USER_ID, DbConstants.COLUMN_USER_USERNAME,
						DbConstants.COLUMN_USER_PASSWORD}, "USERNAME = ?",
				new String[]{username}, null, null, null);
		if (cursor == null) {
			return null;
		}

		User user = null;
		if (cursor.moveToNext()) {
			user = new User();
			user.setId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_USER_ID)));
			user.setUsername(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_USER_USERNAME)));
			user.setPassword(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_USER_PASSWORD)));
		}
		cursor.close();

		return user;
	}

	/**
	 * 修改用户
	 *
	 * @param user 用户
	 */
	public void updateUser(User user) {
		ContentValues values = new ContentValues();
		values.put(DbConstants.COLUMN_USER_USERNAME, user.getUsername());
		values.put(DbConstants.COLUMN_USER_PASSWORD, user.getPassword());
		db.update(DbConstants.TABLE_USER, values, "_id = " + user.getId(), null);
	}

	/**
	 * 查询配置
	 */
	public Config getConfig() {
		Cursor cursor = db.query(DbConstants.TABLE_CONFIG,
				new String[]{DbConstants.COLUMN_CONFIG_ID, DbConstants.COLUMN_CONFIG_FIRST_OPEN,
						DbConstants.COLUMN_CONFIG_LOGIN_USER}, null, null, null, null, null);
		Config config = null;
		if (cursor.moveToNext()) {
			config = new Config();
			config.setId(cursor.getInt(cursor.getColumnIndex(DbConstants.COLUMN_CONFIG_ID)));
			config.setFirstOpen(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_CONFIG_FIRST_OPEN)));
			config.setLoginUsername(cursor.getString(cursor.getColumnIndex(DbConstants.COLUMN_CONFIG_LOGIN_USER)));
		}
		cursor.close();

		return config;
	}

	/**
	 * 更新配置
	 *
	 * @param config 配置信息
	 */
	public void updateConfig(Config config) {
		ContentValues values = new ContentValues();
		values.put(DbConstants.COLUMN_CONFIG_FIRST_OPEN, config.getFirstOpen());
		values.put(DbConstants.COLUMN_CONFIG_LOGIN_USER, config.getLoginUsername());
		db.update(DbConstants.TABLE_CONFIG, values, "_id = " + config.getId(), null);
	}

	/**
	 * 删除数据库
	 *
	 * @param databaseName 数据库名称
	 */
	public void dropDatabase(String databaseName) {
		context.deleteDatabase(databaseName);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {

		private static DatabaseHelper databaseHelper = null;

		private DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
			super(context, name, factory, version);
		}

		public static synchronized DatabaseHelper init(Context context, String name,
		                                               SQLiteDatabase.CursorFactory factory,
		                                               int version) {
			if (databaseHelper == null) {
				databaseHelper = new DatabaseHelper(context, name, factory, version);
			}

			return databaseHelper;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			createTables(db);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.i("DbAdapter", "Upgrade database from version:" + oldVersion + " to version:" + newVersion);
			dropTables(db);
			onCreate(db);
		}

		private void createTables(SQLiteDatabase db) {
			db.execSQL(DbConstants.CREATE_TABLE_USER);
			db.execSQL(DbConstants.CREATE_TABLE_CONFIG);
			db.execSQL(DbConstants.INSERT_CONFIG);
		}

		private void dropTables(SQLiteDatabase db) {
			db.execSQL(DbConstants.DROP_TABLE_USER);
		}
	}


}
