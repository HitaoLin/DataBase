package com.example.admin.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.annotation.Retention;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2019/1/5.
 * 描述：这个类是用于数据库的增删改查
 */

public class Dao {

    private final DatabaseHelper mHelper;
    private SQLiteDatabase db;
    String[] queryKey = {Constants.TABLE_ID, Constants.TABLE_CARID, Constants.TABLE_RECHARGEMONEY, Constants.TABLE_OPERATOR, Constants.TABLE_DATE};
    List<String> list = new ArrayList<>();

    public Dao(Context context) {
        //创建数据库
        mHelper = new DatabaseHelper(context);

    }

    /**
     * 添加数据
     *
     * @param db
     * @param carid
     * @param money
     * @param operator
     * @param data
     */
    public void insert(SQLiteDatabase db, int carid, int money, String operator, String data) {
        //打开数据库
        db = mHelper.getWritableDatabase();
       /* String sql = "insert into "+ Constants.TABLE_NAME +" ("
                +Constants.TABLE_ID+","
                +Constants.TABLE_CARID+","
                +Constants.TABLE_RECHARGEMONEY+","
                +Constants.TABLE_OPERATOR+","
                +Constants.TABLE_DATE+") value(?,?,?,?)";*/
        ContentValues values = new ContentValues();
        values.put(Constants.TABLE_CARID, carid);
        values.put(Constants.TABLE_RECHARGEMONEY, money);
        values.put(Constants.TABLE_OPERATOR, operator);
        values.put(Constants.TABLE_DATE, data);
        db.insert(Constants.TABLE_NAME, null, values);
        //db.execSQL(sql,new Object[]{id,carid,money,operator,data});
        db.close();

    }

   /* public void insert(){
        db = mHelper.getWritableDatabase();
        //String sql = "insert into "+ Constants.TABLE_NAME +"(_id,name,age,salary,phone,address) value(?,?,?,?,?,?)";
        //db.execSQL(sql,new Object[]{1,"BillGates",60,1,110,"USA"});
        String sql = "create table if not exists "+ Constants.TABLE_NAME +" ("
                +Constants.TABLE_ID+" integer,"
                +Constants.TABLE_CARID+" integer,"
                +Constants.TABLE_RECHARGEMONEY+" integer,"
                +Constants.TABLE_OPERATOR+" text,"
                +Constants.TABLE_DATE+" text) value(?,?,?,?)";

        db.close();
    }*/

    /**
     * 删除数据
     */
    public void delete() {
       /* SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "delete from "+ Constants.TABLE_NAME +" where age = 60";
        db.execSQL(sql);
        db.close();*/
    }

    /**
     * 更新数据
     */
    public void update() {
        /*SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "update "+ Constants.TABLE_NAME +" set salary = 2 where age = 60";
        db.execSQL(sql);
        db.close();*/
    }


    /*public void query(){
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "select * from "+ Constants.TABLE_NAME ;
        Cursor cursor = db.rawQuery(sql,null);

        while (cursor.moveToNext()){

            for (int i = 0;i<queryKey.length;i++){
                int index = cursor.getColumnIndex(queryKey[i]);
                list.add(cursor.getString(index));
                Log.e("list", String.valueOf(list));
            }


        }

        cursor.close();

        db.close();
    }*/

    /**
     * 查询数据
     *
     * @return
     */
    public List<RechargeBean> querylist() {
        db = mHelper.getWritableDatabase();
        RechargeBean rechargeBean = null;
        ArrayList<RechargeBean> list1 = new ArrayList<RechargeBean>();
        Cursor c = db.rawQuery("select * from " + Constants.TABLE_NAME, null);
        while (c.moveToNext()) {
            rechargeBean = new RechargeBean();
            rechargeBean.setId(c.getInt(0));
            //Log.e("getin", String.valueOf(c.getInt(0)));
            rechargeBean.setCarId(c.getInt(1));
            rechargeBean.setRechargeMoney(c.getInt(2));
            rechargeBean.setOperator("admin");
            rechargeBean.setTime(c.getString(4));

            RechargeBean rechargeBean2 = new RechargeBean(c.getInt(0), c.getInt(1),
                    c.getInt(2), "admin", c.getString(4));

            list1.add(rechargeBean);


        }
        //Log.e("getin", String.valueOf(list1.size()));

        for (int i = 0; i < list1.size(); i++) {
            Log.e("TAG", String.valueOf(list1.get(i).getId()));
            Log.e("TAG", String.valueOf(list1.get(i).getTime()));

        }
        c.close();
        db.close();
        return list1;
    }

}
