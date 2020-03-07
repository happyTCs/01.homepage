package xu.walt.com.homepage.DispatchPKP.moreFragment;

import android.content.Context;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.design.widget.TabLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import xu.walt.com.homepage.R;

public class MoreFragmentAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private ArrayList<Fragment> mList;
    private String[] titles;
    private TabLayout tableLayout;
    private int[] images;

    public MoreFragmentAdapter(FragmentManager fm, Context mContext, ArrayList<Fragment> mList, String[] titles, TabLayout tableLayout,int[] images) {
        super(fm);
        this.mContext = mContext;
        this.mList = mList;
        this.titles = titles;
        this.images = images;
        this.tableLayout = tableLayout;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

//    public TabLayout getTableLayout() {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.tab_custom, null);
//        TextView tv = view.findViewById(R.id.tv_title);
//        return view;
//    }
public View getTabView(int position) {
    View v = LayoutInflater.from(mContext).inflate(R.layout.tab_custom, null);
    TextView textView = (TextView) v.findViewById(R.id.tv_title);
    ImageView imageView = (ImageView) v.findViewById(R.id.iv_icon);
    textView.setText(titles[position]);
    imageView.setImageResource(images[position]);
    textView.setTextColor(tableLayout.getTabTextColors());
    return v;
}
}