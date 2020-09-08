package in.bajajfinservmarkets.app.loans.insurance.emistore.investment.credit.cards.upi.uat;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import com.plugin.plumb5.P5LifeCycle;


public class MyApplication extends Application  {

    @Override
    public void onCreate() {
        super.onCreate();




        createNotificationChannel();

        registerActivityLifecycleCallbacks(new P5LifeCycle());

    }


    private void createNotificationChannel() {
        final String CAHNNEL_ID = "plumb5";
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "plumb5";
            String description = "notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CAHNNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


}

