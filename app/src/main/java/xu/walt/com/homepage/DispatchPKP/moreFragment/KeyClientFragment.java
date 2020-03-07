package xu.walt.com.homepage.DispatchPKP.moreFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xu.walt.com.homepage.DispatchPKP.event.MessageEvent;
import xu.walt.com.homepage.DispatchPKP.vm.DispatchVM;
import xu.walt.com.homepage.R;
import xu.walt.com.homepage.page.NativeFragment;


public class KeyClientFragment extends NativeFragment {

    private View rootView;
    private static final String TAG = "xdb";

    private boolean isViewCreated;
    private boolean isFirstVisible = true;
    private boolean isFragmentVisible;
    private DispatchVM vm;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       /* view = inflater.inflate(R.layout.fragment_key_client, container, false);
        isViewCreated = true;


        return view;*/

        if(rootView==null){
            rootView=inflater.inflate(R.layout.fragment_key_client, null);
            isViewCreated = true;
        }
            //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isFragmentVisible = isVisibleToUser;

        //当 View 创建完成切 用户可见的时候请求 且仅当是第一次对用户可见的时候请求自动数据
        if (isVisibleToUser && isViewCreated && isFirstVisible) {
            Log.e(TAG, "只有自动请求一次数据  大客户");
//            requestData();
            isFirstVisible = false;

        }

        // 由于每次可见都需要刷新所以我们只需要判断  Fragment 展示在用户面面前了，view 初始化完成了 然后即可以请求数据了
        if (isVisibleToUser && isViewCreated) {
             Log.e(TAG, "每次都可见数据  大客户");
            vm = new DispatchVM();
            vm.getKeyClientNet();
//            requestDataAutoRefresh();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSubscribe(MessageEvent event) {
        if (event.isKey()) {
            String message = event.getMessage();
            Log.i(TAG, "getSubscribe: "+message);
        }
    }


}
