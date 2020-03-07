package xu.walt.com.homepage.DispatchPKP.activity;

import android.app.NativeActivity;
import android.graphics.Color;
import android.support.annotation.MainThread;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import xu.walt.com.homepage.DispatchPKP.event.MessageEvent;
import xu.walt.com.homepage.DispatchPKP.moreFragment.CasualClientFragment;
import xu.walt.com.homepage.DispatchPKP.moreFragment.DirectionalRecommentFragment;
import xu.walt.com.homepage.DispatchPKP.moreFragment.KeyClientFragment;
import xu.walt.com.homepage.DispatchPKP.moreFragment.MoreFragmentAdapter;
import xu.walt.com.homepage.DispatchPKP.vm.DispatchVM;
import xu.walt.com.homepage.R;
import xu.walt.com.homepage.page.NativePage;


public class DispatchpkpActivity extends NativePage {
    private static final String TAG = "DispatchpkpActivity";
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TabLayout tableLayout;
    private TextView titleTv;
    //   所有fragment
    private ArrayList<Fragment> mFragmentList;
    private String[] titles={"散户","大客户","定向推荐"};;
    private int images[] = {R.drawable.sanhu,R.drawable.dakehu,R.drawable.directional_recommended};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispatch_pkp);
        toolbar= findViewById(R.id.tool_bar);
        tableLayout = findViewById(R.id.tl_tabs);
        viewPager = findViewById(R.id.vp_content);
        titleTv = findViewById(R.id.tv_title);
        initToolbar();
        initMoreViewPager();

//        vm = new DispatchVM();
//        vm.getCasualClientNet();
//       getCasualClientNet();
//       getKeyClientNet();
//       getDirectionalRecommentNet();
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

    private void initToolbar() {

        toolbar.setTitle("派揽");
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
                Toast.makeText(getApplicationContext(), "转", Toast.LENGTH_SHORT).show();
            }
        });
    }

   @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSubscribe(MessageEvent event) {
        if (event.isDirectional()) {
            String message = event.getMessage();
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
        }
    }



}
