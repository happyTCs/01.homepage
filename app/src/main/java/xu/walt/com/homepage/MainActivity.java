package xu.walt.com.homepage;

import android.app.AlertDialog;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import xu.walt.com.homepage.DispatchPKP.activity.DispatchpkpActivity;
import xu.walt.com.homepage.DispatchPKP.moreFragment.CasualClientFragment;
import xu.walt.com.homepage.DispatchPKP.moreFragment.DirectionalRecommentFragment;
import xu.walt.com.homepage.DispatchPKP.moreFragment.KeyClientFragment;
import xu.walt.com.homepage.DispatchPKP.moreFragment.MoreFragmentAdapter;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Toolbar toolbar;
   private TabLayout tableLayout;
    private TextView titleTv;
//   所有fragment
    private ArrayList<Fragment> mFragmentList;
    private String[] titles={"散户","大客户","定向推荐"};
    private int images[] = {R.drawable.sanhu,R.drawable.dakehu,R.drawable.directional_recommended};
    private Button button;
    public  int countTemp=2;
    private TextView timeCount;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.tool_bar);
        tableLayout = findViewById(R.id.tl_tabs);
//        viewPager = findViewById(R.id.vp_content);
        titleTv = findViewById(R.id.tv_title);
        button = findViewById(R.id.btn_dialog);
        timeCount = (TextView)findViewById(R.id.timer_count);
        initToolbar();
//        initViewPager();
//        initMoreViewPager();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showNormalDialog();
               /* Intent intent = new Intent(MainActivity.this, DispatchpkpActivity.class);
                startActivity(intent);*/
//                notificationMethod();
                createNofication();
            }
        });


        //由于以1秒间隔不准，所以取其一半来做间隔

        CountDownTimer countDownTimer = new CountDownTimer(15000, 500) {

            @Override
            public void onTick(long l) {
                if (countTemp == 2) {
                    //可以看出来其实millis并不是非常标准的
                    Log.w(TAG, String.format("%d秒", l));
                    Log.w(TAG, String.format("%d秒", Math.round(l / 1000.0)));
                    timeCount.setText(String.format("%d秒", Math.round(l / 1000.0)));

                    countTemp--;
                } else {
                    countTemp++;
                }
            }

            @Override
            public void onFinish() {
                timeCount.setText("结束");
                boolean isCountDown = true;
                countTemp = 2;
            }
        };
        countDownTimer.start();

    }

    private void notificationMethod() {
        Bitmap bigPicture = BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher);
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, DispatchpkpActivity.class);
          /* 第一个数是开始振动前的等待时间（震动关闭），
          第二个数是第一次开启振动的持续时间，
          第三个数是下一次关闭时间*/
          int count =50;
        long[] vibrate = {0, 3000, 2000, 5000, 2000, 3000, 2000, 5000};
        PendingIntent p = PendingIntent.getActivity(this, count, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification builder = new Notification.Builder(this)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("【中国邮政】")
                .setContentText("【中国邮政新一代】订单号：" + "20200426")

                .setTicker("新消息")
                .setLargeIcon(bigPicture)
                .setAutoCancel(true)
                //自定义声音
                //.setSound(Uri.parse("android.resource://cn.chinapost.jdpt.pda.pickup/" + R.raw.notification))
                //自定义震动
                .setVibrate(vibrate)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(p)
                .setNumber(count)
                .build();

        nm.notify(count, builder);
    }

    private void createNofication(){
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder ;

        Intent intent = new Intent(this, DispatchpkpActivity.class);
        int count =50;
        long[] vibrate = {0, 3000, 2000, 5000, 2000, 3000, 2000, 5000};
        PendingIntent p = PendingIntent.getActivity(this, count, intent, PendingIntent.FLAG_CANCEL_CURRENT);


        int channelId = 1 ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){        //Android 8.0适配
            NotificationChannel channel = new NotificationChannel(String.valueOf(channelId),
                    "channel_name",
                    NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
            manager.createNotificationChannel(channel);
            builder = new NotificationCompat.Builder(this,String.valueOf(channelId));
        }else{
            builder = new NotificationCompat.Builder(this);
        }
        builder.setContentTitle("this is content title")            //指定通知栏的标题内容
                .setContentText("this is content text")             //通知的正文内容
                .setWhen(System.currentTimeMillis())                //通知创建的时间
                .setSmallIcon(R.drawable.ic_launcher_background)    //通知显示的小图标，只能用alpha图层的图片进行设置
                //自定义震动

                .setWhen(System.currentTimeMillis())
                .setContentIntent(p)
                .setNumber(count)
                .setAutoCancel(true)
                .setVibrate(new long[]{0,1000,1000,1000})
                //设置默认声音和振动
//                .setDefaults(NotificationCompat.DEFAULT_SOUND|NotificationCompat.DEFAULT_VIBRATE)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background));

        Notification notification = builder.build() ;
        //channelId为本条通知的id
        manager.notify(channelId,notification);
    }
    private void showNormalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("完成退件");
        builder.setMessage("已开箱，完成退件");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"tuichu",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();

    }

    private void initMoreViewPager() {

            CasualClientFragment casualClientFragment = new CasualClientFragment();
            KeyClientFragment keyClientFragment = new KeyClientFragment();
            DirectionalRecommentFragment directionalRecommentFragment = new DirectionalRecommentFragment();
            mFragmentList= new ArrayList<>();
            mFragmentList.add(casualClientFragment);
            mFragmentList.add(keyClientFragment);
            mFragmentList.add(directionalRecommentFragment);
            MoreFragmentAdapter moreFragmentAdapter = new MoreFragmentAdapter(getSupportFragmentManager(), this, mFragmentList, titles, tableLayout,images);
            viewPager.setAdapter(moreFragmentAdapter);
            tableLayout.setupWithViewPager(viewPager);
            for (int i = 0; i < tableLayout.getTabCount(); i++) {
                TabLayout.Tab tab = tableLayout.getTabAt(i);
                tab.setCustomView(moreFragmentAdapter.getTabView(i));

            }



    }

    private void initViewPager() {
        SampleFragmentPagerAdapter sampleFragmentPagerAdapter = new SampleFragmentPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(sampleFragmentPagerAdapter);
        tableLayout.setupWithViewPager(viewPager);
    }


    private void initToolbar() {

        toolbar.setTitle("主界面");
        titleTv.setText("模块");
        toolbar.setTitleTextAppearance(this, R.style.Toolbar_TitleText);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        //设置是否有返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "转", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this,DispatchpkpActivity.class);
                startActivity(intent);

            }
        });
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                Toast.makeText(this, "后退", Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
