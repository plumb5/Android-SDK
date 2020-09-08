package in.bajajfinservmarkets.app.loans.insurance.emistore.investment.credit.cards.upi.uat;

import android.app.Activity;
import android.app.Fragment;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.plugin.plumb5.CommunicationMode;
import com.plugin.plumb5.P5DeviceInfo;
import com.plugin.plumb5.P5Engine;
import com.plugin.plumb5.P5LifeCycle;
import com.razorpay.Checkout;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Date;
import java.util.List;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Checkout.preload(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


            }
        });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        startPayment();
                    }
                });


            }
        });

       Log.d("deviceLog", P5DeviceInfo.getDeviceInfoAsJson(this)) ;
        P5Engine.p5Init(this, "p5m1a2i3sdk2", "https://p5mobtrk.bajajfinservmarket.in/mTracker.svc/", CommunicationMode.FCM,true);
//
        P5Engine.p5SetUserInfo(this, "", "", "8867590095", null);

//      //  P5Engine.p5SetUserInfo(this, null, null, "3333333333", null);
//
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            List<NotificationChannel> notificationChannels = notificationManager.getNotificationChannels();

            Log.d("notificationChannels", Arrays.toString(notificationChannels.toArray()));


        }
        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
        Log.d("data",appLinkAction.toString());
    }


     public void startPayment() {
        startActivity(new Intent(this,TestingActivity.class));
     }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("result", String.valueOf(resultCode));

        if (requestCode == 1001) {
            if (resultCode == RESULT_OK) { // Activity.RESULT_OK

          Log.i("redirect","done");
//

            }
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

    }
}


