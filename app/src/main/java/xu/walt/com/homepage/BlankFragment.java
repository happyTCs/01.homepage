package xu.walt.com.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by walt on 2018/2/5.
 */

public class BlankFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    //特色：fragment 带页数
    public static BlankFragment newInstance(int page) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_PAGE,page);
        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(bundle);
        return  fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mPage == 2) {
            View view  =inflater.inflate(R.layout.fragment_two,container,false);
            return view;
        }
        else if (mPage == 1) {
            View view1 = inflater.inflate(R.layout.fragment_one,container,false);
            return view1;
        } else {
            View view = inflater.inflate(R.layout.fragment_blank, container, false);
            TextView textview = (TextView) view.findViewById(R.id.tv_fragment);
            textview.setText("Fragment #"+mPage);
            return view;
        }



    }
}
