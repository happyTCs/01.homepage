package xu.walt.com.homepage.DispatchPKP.moreFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xu.walt.com.homepage.DispatchPKP.event.MessageEvent;
import xu.walt.com.homepage.DispatchPKP.vm.DispatchVM;
import xu.walt.com.homepage.R;
import xu.walt.com.homepage.page.NativeFragment;


public class DirectionalRecommentFragment extends NativeFragment {

    private View view;
    private static final String TAG = "xdb";

    private boolean isViewCreated;
    private boolean isFirstVisible2 = true;

    private boolean willOnstart2 =false;
    private boolean isFragmentVisible;
    private DispatchVM vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isViewCreated = true;
        view = inflater.inflate(R.layout.fragment_directioanl_recommend, container, false);


        return view;
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        /*isFragmentVisible = isVisibleToUser;
        //当 View 创建完成切 用户可见的时候请求 且仅当是第一次对用户可见的时候请求自动数据
        if (isVisibleToUser && isViewCreated && isFirstVisible) {
            Log.e(TAG, "只有自动请求一次数据  定向推荐");
//            requestData();
            isFirstVisible = false;
        }
        // 由于每次可见都需要刷新所以我们只需要判断  Fragment 展示在用户面面前了，view 初始化完成了 然后即可以请求数据了
        if (isVisibleToUser && isViewCreated) {
            Log.e(TAG, "每次都可见数据  定向推荐");
            vm = new DispatchVM();
            vm.getDirectionalRecommentNet();
//            requestDataAutoRefresh();
        }*/
    /*    if (isFirstVisible2&isVisibleToUser) {
            Log.e(TAG, "isFirstVisible，推荐派揽第一次创建");
            isFirstVisible2 = false;
        }*/

        if (isVisibleToUser&&isViewCreated) {
            Log.e(TAG, "isVisibleToUser  定向推荐");
            Toast.makeText(getActivity().getApplicationContext(),"定向推荐",Toast.LENGTH_SHORT).show();
            vm = new DispatchVM();
            vm.getDirectionalRecommentNet();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        if (willOnstart2&&isFragmentVisible) {
            Log.e(TAG, "onStart可见数据  推荐派揽");
        } else {
            willOnstart2 =true;
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDirectionalSubscribe(MessageEvent event) {
        if (event.isDirectional()) {
            String message = event.getMessage();
            Log.i(TAG, "getSubscribe: "+message);
        }
    }

}
