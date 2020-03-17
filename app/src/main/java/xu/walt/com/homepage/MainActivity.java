package xu.walt.com.homepage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


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
    private String[] titles={"散户","大客户","定向推荐"};;
    private int images[] = {R.drawable.sanhu,R.drawable.dakehu,R.drawable.directional_recommended};
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.tool_bar);
        tableLayout = findViewById(R.id.tl_tabs);
//        viewPager = findViewById(R.id.vp_content);
        titleTv = findViewById(R.id.tv_title);
        button = findViewById(R.id.btn_dialog);
        initToolbar();
//        initViewPager();
//        initMoreViewPager();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showNormalDialog();
                Intent intent = new Intent(MainActivity.this, DispatchpkpActivity.class);
                startActivity(intent);
            }
        });

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
