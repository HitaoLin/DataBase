package com.example.admin.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //充值金额输入框
    @BindView(R.id.et_money)
    EditText et_money;
    @BindView(R.id.sp)
    Spinner sp;
    //账户充值按钮
    @BindView(R.id.bt_recharge)
    Button bt_recharge;
    //数据查询
    @BindView(R.id.bt_data)
    Button bt_data;
    //指针
    private int mInt = 1;
    //spinner下拉列表
    private String[] str = new String[]{
            "1",
            "2",
            "3",
    };
    private int money;
    DatabaseHelper dbHelper;
    Dao dao;
    //系统时间
    String str_time;

    //--------------------------------------
    //账单记录的spinner
    @BindView(R.id.sp_account)
    Spinner sp_account;
    //查询账单记录按钮
    @BindView(R.id.btn_account_query)
    Button btn_account_query;
    //指针
    private int aInt = 1;
    //账单记录 spinner
    private String[] str2 = new String[]{
            "时间升序", "时间降序"
    };

    private List<RechargeBean> mList = new ArrayList<RechargeBean>();
    //private List<RechargeBean> mList = new ArrayList<>();
    //适配器
    private MyAdapter myAdapter;
    @BindView(R.id.listview)
    ListView listview;
    //数据库
    SQLiteDatabase db;
    /*long date1 = 0;
    long date2 = 0;*/
    //时间排序返回值
    int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //实例化Dao
        dao = new Dao(this);
        //初始化spinner
        initSpinner();
        //账单记录spinner
        initSpinner2();
        //初始化View  查询按钮
        initView();

    }

    /**
     * 充值点击事件
     */
    @OnClick(R.id.bt_recharge)
    public void rechargeOnClick() {
        initDB();
        money = Integer.parseInt(et_money.getText().toString());
        Log.e("MONEY", String.valueOf(money));
        Log.e("click11", "点击");
        //获取系统时间
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        str_time = format.format(curDate);
        Log.e("time", str_time);
        dao.insert(dbHelper.getWritableDatabase(), mInt, money, "admin", str_time);
        Toast.makeText(this, "添加成功！", Toast.LENGTH_SHORT).show();
        db.close();
    }

    /**
     * 查询数据
     */
    private void Query() {
        Cursor cursor = db.query(Constants.TABLE_NAME, null, null, null, null, null, null);
        mList.clear();
        while (cursor.moveToNext()) {
            int id = Integer.parseInt(cursor.getString(0));
            int carId = Integer.parseInt(cursor.getString(1));
            int recharge_money = Integer.parseInt(cursor.getString(2));
            String operator = cursor.getString(3);
            String date = cursor.getString(4);
            RechargeBean rechargeBean = new RechargeBean(id, carId, recharge_money, operator, date);
            mList.add(rechargeBean);
        }
    }

    /**
     * 初始化View  查询按钮
     */
    private void initView() {
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mInt = position + 1;
                //initTrafficLights();//初始化路口升序
                Toast.makeText(MainActivity.this, "" + mInt, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /**
         * 账单记录  查询点击事件
         */
        btn_account_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                initDB();

                Collections.sort(mList, new Comparator<RechargeBean>() {
                    @Override
                    public int compare(RechargeBean r1, RechargeBean r2) {

                        /*
                        //时间转时间戳
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                        Date date = new Date();

                        try {
                            date = dateFormat.parse(r1.getTime());
                            date1=date.getTime();
                            date = dateFormat.parse(r2.getTime());
                            date2=date.getTime();
                            Log.e("date",date.getTime()+"");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }*/
                        /* Date date1 = Date.valueOf(r1.getTime());
                        Date date2 = Date.valueOf(r2.getTime());*/

                       /* if(date1.before(date2)==true){
                            return -1;
                        }*/

                        Log.e("TAG1",   ""+mList.size());
                        switch (aInt) {
                            case 1:
                                a = 0;
                                Log.e("TAG1", aInt + "");
                                break;
                            case 2:
                                a = -1;
                                Log.e("TAG2", aInt + "");
                                break;
                        }
                        return a;
                    }

                });
                auto();
                db.close();


            }
        });
    }

    /**
     * 添加数据，刷新数据
     */
    private void auto() {
        if (myAdapter == null) {
            myAdapter = new MyAdapter(this);
            listview.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
        } else{
            listview.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
        }

    }
    /**
     * 适配器
     */
    class MyAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater layoutInflater;

        public MyAdapter(Context context) {
            this.context = context;
            layoutInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mList.size();

        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup parent) {

            ViewHolder viewHolder = null;

            if (view == null) {
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.mItemId = view.findViewById(R.id.item_tv_serialNumber);
                viewHolder.mItemCarId = view.findViewById(R.id.item_tv_carId);
                viewHolder.mItemRechagre = view.findViewById(R.id.item_tv_recharge);
                viewHolder.mItemOperator = view.findViewById(R.id.item_tv_operator);
                viewHolder.mItemDate = view.findViewById(R.id.item_tv_rechargeTime);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
           // RechargeBean rechargeBean = mList.get(i);

            RechargeBean r = mList.get(i);
            viewHolder.mItemId.setText("" + r.getId());
            viewHolder.mItemCarId.setText("" + r.getCarId());
            viewHolder.mItemRechagre.setText("" + r.getRechargeMoney());
            viewHolder.mItemOperator.setText("" + r.getOperator());
            viewHolder.mItemDate.setText("" + r.getTime());
            return view;

        }

        class ViewHolder {
            TextView mItemId;
            TextView mItemCarId;
            TextView mItemRechagre;
            TextView mItemOperator;
            TextView mItemDate;
        }
    }


    /**
     * 账单记录spinner
     */
    private void initSpinner2() {

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, str2);
        sp_account.setAdapter(stringArrayAdapter);
        sp_account.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                aInt = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @OnClick(R.id.bt_data)
    public void btdataOncliclk() {
        //Log.e("click","点击");
        // dao.query();
        //dao.querylist();
    }


    /**
     * 打开数据库，查询数据
     */
    private void initDB() {

        //创建DatabaseHelper实例
        dbHelper = new DatabaseHelper(this);
        //打开数据库
        db = dbHelper.getWritableDatabase();
        //查询数据
        Query();
        //创建MyAdapter实例
        myAdapter = new MyAdapter(this);

    }

    /**
     * 初始化spinner
     */
    private void initSpinner() {
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str);
        //sp.setSelection(0);
        sp.setAdapter(stringArrayAdapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mInt = position + 1;
                //Toast.makeText(MainActivity.this, ""+mInt, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
