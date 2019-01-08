package com.example.admin.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Admin on 2019/1/4.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


   private static final String TAG = "DatabaseHelper";

   /**
    *
    * @context    上下文
    * @name       数据库名称
    * @factory    游标工厂
    * @version    版本号
    */
   public DatabaseHelper(Context context) {
      super(context, Constants.DATABASE_NAME, null, Constants.VERSION_CODE);
   }

    /**
     * 第一次创建数据库时被调用
    * @param db
     */
   @Override
   public void onCreate(SQLiteDatabase db) {
      //创建时的回调
      Log.d(TAG,"创建数据库");
      //创建字段
      //sql:create table table_name(_id integer,name varchar,age integer,salary integer);
      //String sql = "create table "+Constants.TABLE_NAME+"(_id integer,name varchar,age integer,salary integer)";
          /* String sql = "create table "+Constants.TABLE_NAME +" ("
              +Constants.TABLE_CARID+" integer,"
              +Constants.TABLE_RECHARGEMONEY+" integer,"
              // +Constants.TABLE_OPERATOR+" text,"
              +Constants.TABLE_DATE+" text)";*/

      String sql = "create table if not exists "+Constants.TABLE_NAME +" ("
              +Constants.TABLE_ID+" integer primary key autoincrement,"
              +Constants.TABLE_CARID+" integer,"
              +Constants.TABLE_RECHARGEMONEY+" integer,"
              +Constants.TABLE_OPERATOR+" text,"
              +Constants.TABLE_DATE+" text)";

      db.execSQL(sql);
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      //升级数据库时的回调
      Log.d(TAG,"升级数据库");
/*

      //sql: alter table table_name add phone integer
      String sql = "alter table "+Constants.TABLE_NAME+" add phone integer";
      db.execSQL(sql);
*/
     /* String sql;
      switch (oldVersion){
         case 1:
            //添加address和这个phone字段
            sql = "alter table "+Constants.TABLE_NAME+" add phone integer,address varchar";
            db.execSQL(sql);
            break;
         case 2:
            //添加这个address字段
            //sql: alter table table_name add phone integer
            sql = "alter table "+Constants.TABLE_NAME+" add address varchar";
            db.execSQL(sql);
            break;
         case 3:

            break;
      }*/
   }
}
