package xu.walt.com.homepage.page;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

public class NativeFragment extends Fragment {

    private static final String TAG = "NativeFragment";
    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
            Log.i(TAG, "onStart: ");
        }
    }





    @Override
    public void onStop() {
        super.onStop();
        if (EventBus.getDefault().isRegistered(this)) {
            Log.i(TAG, "onDestroy: ");
            EventBus.getDefault().unregister(this);
        }
    }
}
