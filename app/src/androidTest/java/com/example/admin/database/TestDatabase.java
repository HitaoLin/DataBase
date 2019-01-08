package com.example.admin.database;

import android.test.AndroidTestCase;

/**
 * Created by Admin on 2019/1/5.
 * 描述：这是一个测试类
 */

public class TestDatabase extends AndroidTestCase {

    public void testCreate() {
        //测试创建数据库
    }

    public void testInsert() {
        //测试插入数据
        Dao dao = new Dao(getContext());
        dao.insert();
    }

    public void testDelete() {
        //测试删除数据
        Dao dao = new Dao(getContext());
        dao.delete();

    }

    public void testUpdate() {
        //测试修改数据
        Dao dao = new Dao(getContext());
        dao.update();
    }

    public void testQuery() {
        //测试查询数据
        Dao dao = new Dao(getContext());
        dao.query();
    }
}
