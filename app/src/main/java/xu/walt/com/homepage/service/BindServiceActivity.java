package xu.walt.com.homepage.service;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import xu.walt.com.homepage.R;

public class BindServiceActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "xdb";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);
    }


    private MyBindService service;
    ServiceConnection conn= new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBindService.LocalBinder binder = (MyBindService.LocalBinder) iBinder;
            binder.start();
            binder.end();
            service = binder.getService();
            service.myWay();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            service = null;
            Log.e(TAG, "onServiceDisconnected: ");
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                 Intent intent =new Intent(this,MyBindService.class);
                 startService(intent);
                break;
            case R.id.stop:
                Intent stopIntent =new Intent(this,MyBindService.class);
                stopService(stopIntent);
                break;
            case R.id.bind:
                Intent bindIntent =new Intent(this,MyBindService.class);
                bindService(bindIntent,conn,Service.BIND_AUTO_CREATE);
                break;
            case R.id.unbind:
                unbindService(conn);
                break;
            default:
                break;
        }
    }
}
