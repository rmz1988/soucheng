package com.soucheng.db;

/**
 * 数据库相关常量
 *
 * @author lichen
 */
public final class DbConstants {

	//数据库版本号
	public static final int DATABASE_VERSION = 1;

	//用户表,字段名,创建sql，删除sql
	public static final String TABLE_USER = "USER";
	public static final String COLUMN_USER_ID = "_id";
	public static final String COLUMN_USER_USERNAME = "USERNAME";
	public static final String COLUMN_USER_PASSWORD = "PASSWORD";
	public static final String CREATE_TABLE_USER =
			"CREATE TABLE USER(_id integer primary key autoincrement,USERNAME text not null,PASSWORD text not null)";
	public static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS USER";

	//配置表，字段名，创建sql，删除sql,插入基本数据
	public static final String TABLE_CONFIG = "CONFIG";
	public static final String COLUMN_CONFIG_ID = "_id";
	public static final String COLUMN_CONFIG_FIRST_OPEN = "FIRST_OPEN";
	public static final String COLUMN_CONFIG_LOGIN_USER = "LOGIN_USER";
	public static final String CREATE_TABLE_CONFIG =
			"create table CONFIG(_id integer primary key autoincrement,FIRST_OPEN text,LOGIN_USER text)";
	public static final String DROP_TABLE_CONFIG = "DROP TABLE IF EXISTS CONFIG";
	public static final String INSERT_CONFIG = "INSERT INTO CONFIG(FIRST_OPEN) VALUES ('1')";
}
