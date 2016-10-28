package com.example.administrator.android_a1607_okhttp.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationSet;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.android_a1607_okhttp.DataBase.NewsDao;
import com.example.administrator.android_a1607_okhttp.Fragment.BaoBaoFragment;
import com.example.administrator.android_a1607_okhttp.Fragment.ChiXiuFragment;
import com.example.administrator.android_a1607_okhttp.Fragment.GaoxiaoFragment;
import com.example.administrator.android_a1607_okhttp.Fragment.GirlFragment;
import com.example.administrator.android_a1607_okhttp.Fragment.GuangPaiFragment;
import com.example.administrator.android_a1607_okhttp.Fragment.HongWuFragment;
import com.example.administrator.android_a1607_okhttp.Fragment.ManFragment;
import com.example.administrator.android_a1607_okhttp.Fragment.MeiShiFragment;
import com.example.administrator.android_a1607_okhttp.Fragment.RemenFragmen;
import com.example.administrator.android_a1607_okhttp.Fragment.StarFragment;
import com.example.administrator.android_a1607_okhttp.Fragment.WuDaoFragment;
import com.example.administrator.android_a1607_okhttp.Fragment.YinYueFragment;
import com.example.administrator.android_a1607_okhttp.Fragment.ZhangZiSiFragment;
import com.example.administrator.android_a1607_okhttp.Fragment.meizhangFragment;
import com.example.administrator.android_a1607_okhttp.Adapter.MyPagerAdapter;
import com.example.administrator.android_a1607_okhttp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private RadioGroup radioGroup;
    private HorizontalScrollView scrollView;
    private ViewPager pager;
    private List<Fragment> mViewData;
    private float mCurrentCheckedRadioLeft;//当前选中的左侧距离

    //全局变量Fragment
    private static SparseArray<Fragment> fragments = new SparseArray<>();
    private PagerTabStrip tabStrip;
    private ImageView image;
    private AnimationSet anmation;
    private RadioButton rb_one;
    private RadioButton rb_two;
    private RadioButton rb_three;
    private RadioButton rb_four;
    private RadioButton rb_five;
    private RadioButton rb_six;
    private RadioButton rb_seven;
    private RadioButton rb_eight;
    private RadioButton rb_nine;
    private RadioButton rb_ten;
    private RadioButton rb_eleven;
    private RadioButton rb_twelve;
    private RadioButton rb_thirteen;
    private RadioButton rb_fourteen;
    private NewsDao dao;
    private TabLayout tab_layout;
    private String[] navigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView(); //初始化
        initNavigationListener(); //home菜单被点击事件
        initPagerData();//适配器数据源
        initPagerAdapter(); //pager适配器
        initTabLayout(); //导航条初始化
//        initViewpagerListener(); //vip事件
//        initRadioGroupListenner();//RadioGroup监听事件
        dao = new NewsDao(this);
    }




    //初始化
    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toobar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        radioGroup = (RadioGroup) findViewById(R.id.rg_menu);
        /*水平滚动栏*/
        scrollView = (HorizontalScrollView) findViewById(R.id.hsv_Navi);
        /*Viewpager*/
        pager = (ViewPager) findViewById(R.id.vp);
        /*滑动条*/
        /*Button*/
        rb_one = (RadioButton) findViewById(R.id.rb_one);
        rb_two = (RadioButton) findViewById(R.id.rb_two);
        rb_three = (RadioButton) findViewById(R.id.rb_three);
        rb_four = (RadioButton) findViewById(R.id.rb_four);
        rb_five = (RadioButton) findViewById(R.id.rb_five);
        rb_six = (RadioButton) findViewById(R.id.rb_six);
        rb_seven = (RadioButton) findViewById(R.id.rb_Seven);
        rb_eight = (RadioButton) findViewById(R.id.rb_eight);
        rb_nine = (RadioButton) findViewById(R.id.rb_nine);
        rb_ten = (RadioButton) findViewById(R.id.rb_ten);
        rb_eleven = (RadioButton) findViewById(R.id.rb_Eleven);
        rb_twelve = (RadioButton) findViewById(R.id.rb_Twelve);
        rb_thirteen = (RadioButton) findViewById(R.id.rb_thirteen);
        rb_fourteen = (RadioButton) findViewById(R.id.rb_Fourteen);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);

        //标题数据源
        navigation = getResources().getStringArray(R.array.title);
    }

    //导航条初始化
    private void initTabLayout() {

        //tablayout和viewpager关联在一起
        tab_layout.setupWithViewPager(pager,true);
        //设置指示器的颜色
        tab_layout.setSelectedTabIndicatorColor(Color.parseColor("#6BFE69"));
        //设置指示器的高度
        tab_layout.setSelectedTabIndicatorHeight(5);
        //设置文字的颜色
        tab_layout.setTabTextColors(Color.WHITE,Color.parseColor("#F5C838"));
        //导航模式
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    //home菜单点击事件
    private void initNavigationListener() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断抽屉是否打开
                if (!drawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    //打开抽屉
                    drawerLayout.openDrawer(Gravity.LEFT);
                } else {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
            }
        });
    }

    //pager适配器
    private void initPagerAdapter() {
        MyPagerAdapter myadpter = new MyPagerAdapter(getSupportFragmentManager(), mViewData,navigation);
        pager.setAdapter(myadpter);
        pager.setOffscreenPageLimit(13);
        pager.setCurrentItem(0);
//        ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
    }

    //适配器数据源
    private void initPagerData() {
        mViewData = new ArrayList<>();
        //添加数据源
        for (int i = 0; i <navigation.length; i++) {
            Fragment fragment = createFragment(i);
            mViewData.add(fragment);
        }
    }

    //创建Fragment对象存储数据源
    public Fragment createFragment(int index) {
        //从集合中获取
        Fragment fragment = fragments.get(index);
        if (fragment == null) {
            switch (index) {
                case 0:
                    fragment = new RemenFragmen();
                    break;
                case 1:
                    fragment = new GaoxiaoFragment();
                    break;
                case 2:
                    fragment = new meizhangFragment();
                    break;
                case 3:
                    fragment = new MeiShiFragment();
                    break;
                case 4:
                    fragment = new ChiXiuFragment();
                    break;
                case 5:
                    fragment = new YinYueFragment();
                    break;
                case 6:
                    fragment = new WuDaoFragment();
                    break;
                case 7:
                    fragment = new BaoBaoFragment();
                    break;
                case 8:
                    fragment = new StarFragment();
                    break;
                case 9:
                    fragment = new GirlFragment();
                    break;
                case 10:
                    fragment = new ZhangZiSiFragment();
                    break;
                case 11:
                    fragment = new HongWuFragment();
                    break;
                case 12:
                    fragment = new ManFragment();
                    break;
                case 13:
                    fragment = new GuangPaiFragment();
                    break;
            }
            //存在集合中
            fragments.put(index, fragment);
        }
        return fragment;
    }

    //ViewPager选择监听事件
    private void initViewpagerListener() {
        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                ((RadioButton) radioGroup.getChildAt(position)).setChecked(true);
                radioGroup.getChildAt(position).setSelected(true);
            }
        });
    }

    //radio点击监听事件
    private void initRadioGroupListenner() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < group.getChildCount(); i++) {
                    if (group.getChildAt(i).getId() == checkedId) {
                        //处理监听
                        findViewById(checkedId).setTag(i);
                        findViewById(checkedId).setOnClickListener(MainActivity.this);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int which = (int) v.getTag();
        pager.setCurrentItem(which);
    }

    //drawer点击跳转到登陆界面
    public void SignIn(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    //drawer点击跳转到注册界面
    public void Register(View view) {
        startActivity(new Intent(this, SingInActivity.class));
        finish();
    }

    //跳转到收藏
    public void Btn_Collection(View view) {
        startActivity(new Intent(this, See_Collect_Activity.class));
    }

    //清除下载
    public void Delect_Down(View view) {

        String Delect_sql = "delete from collect"; //删除语句

        String sql = "select * from collect"; //查询语句
        Cursor cursor = dao.select(sql, null);
        int count = cursor.getCount();
        if (count<1) {
            Toast.makeText(this, "清除失败！", Toast.LENGTH_SHORT).show();
        }else {
            dao.execSql(Delect_sql,null);
            Toast.makeText(this, "删除成功!", Toast.LENGTH_SHORT).show();

        }



    }
}
