package xu.walt.com.homepage.DispatchPKP.vm;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import xu.walt.com.homepage.DispatchPKP.event.MessageEvent;

public class DispatchVM {
    private static final String TAG = "xdb";
    public void getCasualClientNet() {
        MessageEvent event = new MessageEvent();
        event.setCasual(true);
        event.setMessage("散户:2020-3-7");
        EventBus.getDefault().post(event);
    }

    public void getKeyClientNet() {
        MessageEvent event = new MessageEvent();
        event.setKey(true);
        event.setMessage("大客户:2020-3-7");
        Log.e(TAG, "getKeyClientNet: VM" );
        EventBus.getDefault().post(event);

    }

    public void getDirectionalRecommentNet() {
        MessageEvent event = new MessageEvent();
        event.setDirectional(true);
        event.setMessage("定向推荐:2020-3-7");
        EventBus.getDefault().post(event);
    }
}
