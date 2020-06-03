package xu.walt.com.homepage.DispatchPKP.moreFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xu.walt.com.homepage.DispatchPKP.event.MessageEvent;
import xu.walt.com.homepage.DispatchPKP.vm.DispatchVM;
import xu.walt.com.homepage.R;
import xu.walt.com.homepage.page.NativeFragment;
import xu.walt.com.homepage.service.BindServiceActivity;
import xu.walt.com.homepage.service.MyService;


public class CasualClientFragment extends NativeFragment  {
    private static final String TAG = "xdb";


    private boolean isViewCreated;
    private boolean isFirstVisible = true;
    private boolean willOnstart0 =false;
    private boolean isVisibleToUserStart=false;
    private boolean isFragmentVisible;
    private DispatchVM vm;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isViewCreated = true;
        View view = inflater.inflate(R.layout.fragment_casual_client, container, false);


        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button btnStart = getActivity().findViewById(R.id.btn_service_start);
        btnStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getActivity(), MyService.class);
                getActivity().startService(startIntent);
            }
        });
        Button btnStop = getActivity().findViewById(R.id.btn_service_stop);
        btnStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stopIntent = new Intent(getActivity(), MyService.class);
                getActivity().stopService(stopIntent);
            }
        });

        Button button = getActivity().findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BindServiceActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isFragmentVisible = isVisibleToUser;

/*        //当 View 创建完成切 用户可见的时候请求 且仅当是第一次对用户可见的时候请求自动数据
        if (isVisibleToUser && isViewCreated && isFirstVisible) {
            Log.e(TAG, "isFirstVisible，只有自动请求一次数据  散户");
//            requestData();
            isFirstVisible = false;

        }*/

        // 由于每次可见都需要刷新所以我们只需要判断  Fragment 展示在用户面面前了，view 初始化完成了 然后即可以请求数据了
        if (isVisibleToUser&&isViewCreated ) {
            Log.e(TAG, "isVisibleToUser  散户");
            isVisibleToUserStart=true;
            Toast.makeText(getActivity().getApplicationContext(),"散户",Toast.LENGTH_SHORT).show();
            vm = new DispatchVM();
            vm.getCasualClientNet();
//            requestDataAutoRefresh();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (willOnstart0 && isFragmentVisible) {
            if (isVisibleToUserStart) {
                Log.e(TAG, "onStart:  散户不工作" );
                isVisibleToUserStart=false;
            } else {
                Log.e(TAG, "onStart可见数据  散户");
            }
        }
        else if (!willOnstart0&&isFragmentVisible){
            Log.e(TAG, "onStart false数据  散户");
        }else {
            willOnstart0 =true;
            Log.e(TAG, "onStart: else散户" );
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getNet(MessageEvent event) {
        if (event.isCasual()) {
            String message = event.getMessage();
            Log.i(TAG, "getNet: "+message);
        }
    }


}
