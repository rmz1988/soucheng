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
    public static final String COLUMN_USER_GOLD = "GOLD";
	public static final String CREATE_TABLE_USER =
			"CREATE TABLE USER(_id integer primary key autoincrement,USERNAME text not null,PASSWORD text not null,GOLD integer)";
	public static final String DROP_TABLE_USER = "DROP TABLE IF EXISTS USER";

	//配置表，字段名，创建sql，删除sql,插入基本数据
	public static final String TABLE_CONFIG = "CONFIG";
	public static final String COLUMN_CONFIG_ID = "_id";
	public static final String COLUMN_CONFIG_FIRST_OPEN = "FIRST_OPEN";
	public static final String COLUMN_CONFIG_LOGIN_USER = "LOGIN_USER";
	public static final String COLUMN_CONFIG_OPEN_LOCATION = "OPEN_LOCATION";
	public static final String CREATE_TABLE_CONFIG =
			"create table CONFIG(_id integer primary key autoincrement,FIRST_OPEN text,LOGIN_USER text,OPEN_LOCATION text)";
	public static final String DROP_TABLE_CONFIG = "DROP TABLE IF EXISTS CONFIG";
	public static final String INSERT_CONFIG = "INSERT INTO CONFIG(FIRST_OPEN,OPEN_LOCATION) VALUES ('1','1')";

	//地址表，字段名、创建sql，删除sql
	public static final String TABLE_ADDRESS = "ADDRESS";
	public static final String COLUMN_ADDRESS_ID = "_id";
	public static final String COLUMN_ADDRESS_NAME = "NAME";
	public static final String COLUMN_ADDRESS_PROVINCE = "PROVINCE";
	public static final String COLUMN_ADDRESS_CITY = "CITY";
	public static final String COLUMN_ADDRESS_LATITUDE = "LATITUDE";
	public static final String COLUMN_ADDRESS_LONGITUDE = "LONGITUDE";
	public static final String COLUMN_ADDRESS_DETAIL = "DETAIL";
	public static final String COLUMN_ADDRESS_CAN_NOTIFY = "CAN_NOTIFY";
	public static final String CREATE_TABLE_ADDRESS = "create table ADDRESS(_id integer primary key autoincrement," +
			"NAME text,PROVINCE text,CITY text,LATITUDE double,LONGITUDE double,DETAIL text,CAN_NOTIFY text)";
	public static final String DROP_TABLE_ADDRESS = "DROP TABLE IF EXISTS ADDRESS";
}
