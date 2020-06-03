package xu.walt.com.homepage.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyBindService extends Service {
    private static final String TAG = "MyBindService";
    private LocalBinder mBinder = new LocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: " );
        return mBinder;
    }
   public class LocalBinder extends Binder {
       public MyBindService getService() {
           return MyBindService.this;
       }

       public void start() {
           Log.e(TAG, "start: " );
       }

       public void end() {
           Log.e(TAG, "end: " );
       }
    }

    @Override
    public void onCreate() {
        Log.e(TAG, "onCreate: " );
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: " );
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: " );
        super.onDestroy();
    }

    public String myWay() {
        Log.e(TAG, "myWay: helloworld" );
        return "helloworld";
    }
    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: " );
        return super.onUnbind(intent);
    }
}
