


# **Plumb5 Analytics SDK for Android Devices**

The document details steps required to integrate Plumb5 Analytics SDK into your application for Android devices. For any questions or queries please email us at developer@plumb5.com


## **SDK Integration**

To get up and running with Plumb5 on Android, there are a couple of steps to get you there.


### **Android Studio:**



*   Step 1 : Select File → New →New Module
*   Step 2 : Select " Import .JAR or .AAR Package under More Modules.
*   Step 3 : Select the plugin-release.aar by clicking on the File chooser.
*   Step 4 : Select Finish.
*   Step 5 : Right click on app folder → Open Module Settings.
*   Step 6 : Select Dependency at the top right corner.
*   Step 7 : Select '+' symbol and choose 'Module Dependency' and It will list down the existing jar files.
*   Step 8 : Select plugin-release.aar


### **Eclipse**



*   Step 1 : Add plugin-release.aar to your project's libs folder.
*   Step 2 : Right-click the JAR file and select Build Path → Add to Build Path.


## **SDK Configuration and Permissions**

Once you get the APP KEY from the Plumb5 dashboard,please add it to the manifest file as shown below.

```xml
<uses-permissionandroid:name= "android.permission.INTERNET"/>
<uses-permission android:name = "android.permission.WAKE_LOCK" />
<uses-permission android:name = "android.permission.READ_PHONE_STATE" />
<uses-permission android:name = "android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name = "android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name = "android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name = "android.permission.ACCESS_WIFI_STATE" />
<uses-permission android:name = "android.permission.SEND_SMS" />
<uses-permission android:name = "android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name = "android.permission.RECEIVE_BOOT_COMPLETED" />
<uses-permission android:name = "com.google.android.providers.gsf.permission.READ_GSERVICES" />
<uses-permission android:name = "android.permission.BLUETOOTH" />
<uses-permission android:name = "android.permission.BLUETOOTH_ADMIN" />
<uses-permission android:name = "android.permission.CALL_PHONE" />
<uses-permission android:name = "com.google.android.c2dm.permission.RECEIVE" />
```

Runtime permission required for ACCESS_FINE_lOCATION and ACCESS_COARSE_LOCATION ,if you are using Android marshmallow and above.


### **Modify AndroidManifest.xml**

Change application name for tracking.
```xml
android:name = "com.plugin.plumb5.P5Monitor"
//Add plumb5 Receiver.
<receiver android:name = "com.plugin.plumb5.P5BootCompleteReceiver">

android:exported="false"

android:permission="android.intent.action.BOOT_COMPLETED"

<intent-filter>
<action android:name = "android.intent.action.BOOT_COMPLETED"/>
</intent-filter>
</receiver>
```
### **Track Event**

In extends Application class file
```java
registerActivityLifecycleCallbacks(new P5LifeCycle());
```

### **Add Plumb5 Dependencies**

Plumb5 Android SDK is hosted on jcenter maven repository.

Update your project's build.gradle script to include jcenter.


```java
buildscript {
repositories {
google()
jcenter()
}
dependencies {
classpath 'com.android.tools.build:gradle:3.4.1'
classpath 'com.google.gms:google-services:4.2.0'
// NOTE: Do not place your application dependencies here; they belong
// in the individual module build.gradle files }
}
allprojects {
repositories {
google()
jcenter()
}
}
```


Include Plumb5 dependencies in your app/build.gradle.


```java
dependencies {
implementation 'com.android.support:appcompat-v7:28.0.0'
implementation 'com.android.support.constraint:constraint-layout:1.1.3'
testImplementation 'junit:junit:4.12'
androidTestImplementation 'com.android.support.test:runner:1.0.2'
androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
implementation 'com.google.code.gson:gson:2.8.2'
implementation 'com.android.support:design:28.0.0'
implementation 'com.android.support:cardview-v7:28.0.0'
implementation 'com.android.support:support-v4:28.0.0'
implementation 'com.google.firebase:firebase-auth:17.0.0'
implementation 'com.google.firebase:firebase-core:16.0.9'
implementation 'com.google.android.gms:play-services-location:16.0.0'
implementation 'com.google.android.gms:play-services-places:16.1.0'
implementation 'com.google.android.gms:play-services-gcm:16.1.0'
}
```



### **Proguard rule**

-keep public class com.google.android.gms.*{ public *; }

-dontwarn com.google.android.gms.**

Note: Client need to add google-service.json file for FCM in project/app/ path.


### **SDK Initialization**

First Import plumb5 plugin:- import com.plugin.plumb5.P5Engine;

Initialise the SDK from the onCreate method of your Application class and start Session.

If GCM :-
```java
P5Engine.p5Init(this, "yourAppkey","service url" ,CommunicationMode.GCM);
```
If FCM:-
```java
P5Engine.p5Init(this,"yourAppkey","service url" CommunicationMode.FCM);
```

### **Example:**


```java
//Pass the APP KEY and CommunicationMode.GCM or .FCM(Currently using) to P5Engine.p5Init method to start tracking the user actions.
public class MyApplication extends Application {
@Override
public void onCreate() {
super.onCreate();
P5Engine.p5Init(getApplicationContext(),"p5m1a2i3sdk2011","serviceurl",CommunicationMode.FCM);
}
}
Or
public class MyApplication extends AppCompatActivity
{
@Override
public void onCreate(Bundle savedInstanceState)
{
super.onCreate(savedInstanceState);
P5Engine.p5Init(this,"p5m1a2i3sdk2011","serviceurl",CommunicationMode.FCM);
}
}
```


*this,getApplicationContext() identify corrent context of class or Activity.

The client can also add additional properties like user information.

```java
P5Engine.p5SetUserInfo( this, "test","test@gmail.com","9916193666",null);
```



### **Track Event**

Event tracking lets you track user activities in your application and tie them to the corresponding engagement campaigns or to trigger an in-app notification.

For Event tracking only one method in this API P5Engine.P5Eventdata

```java
P5Engine.P5Eventdata(this, "BUTTON_CLICK", "btn-name", "value");
Example:
P5Engine.P5Eventdata(this, "BUTTON_CLICK", "Cart", "2")
```



### **Transaction**

Transaction API allow you to pass business events to Plumb5 for analytics., user segmentation or to trigger engagements or other actions. All passed events are captured in the Event analytics screen on the dashboard.

Every event has 2 attributes, action name and key, value pairs which represent additional information about the action. Add all the additional information which you think would be useful for segmentation while creating campaigns. For eg. the following code tracks a purchase event of a product. We are including attributes like ProductId, ProductName, Quantity, TotalAmount, ProductImage, OrderId, TransStatus which describe the event we are tracking.

For Transaction only one method in the API P5Engine.P5Transdata


```java
P5Engine.P5Transdata(this, [TransData]);
this- context
[TransData]-JSONArray

JSONArray jsonTransaction = new JSONArray();
try {
final JSONObject jsonItem = new JSONObject();
jsonItem.put("ProductId", 1);
jsonItem.put("ProductName", "samsung");
jsonItem.put("Quantity", 1);
jsonItem.put("TotalAmount", 200);
jsonItem.put("ProductImage", "");
jsonItem.put("OrderId", "234");
jsonItem.put("TransStatus", 0);
jsonTransaction .put(jsonItem);
} catch (JSONException ignored) {}
P5Engine.P5Transdata(this, jsonTransaction );
```



### **Static Form**

If clients have their own captcha form then they can add there from through this Method API.

```java
P5Engine.P5PostStaticFormData(this,FormId,[DataList]);
this- context
FormId- captcha form Id.
[DataList]- JSONArray

JSONArray DataList =new JSONArray();

DataList.put("sunny");

DataList.put("sunny@gmail.com");

DataList.put("991619345");

DataList.put("Male");

DataList.put("Bangalore");

DataList.put("Ready to buy"); }

P5Engine.P5PostStaticFormData(this, FormId, DataList);
```

### **Engagement / Push**

WebEngage SDK supports both FCM or GCM for push messaging. To use FCM follow our Firebase Cloud Messaging Integration guide. Otherwise, follow our Google Cloud Messaging Integration guide.


### **Firebase Cloud Messaging (FCM) integration**



1. Add Firebase to your project
2. Follow the official Firebase instruction on how to add Firebase to your existing project.Add FCM dependency to your build.gradle file.

Update Plumb5 dependency in your project's top level project/build.gradle file


```java
classpath'com.google.gms:google-services:3.1.0'
```


Include Plumb5 dependencies in your app/build.gradle.


```java
compile'com.google.firebase:firebase-auth:11.8.0'
compile'com.google.firebase:firebase-core:11.8.0'
compile'com.google.firebase:firebase-messaging:11.8.0'
apply plugin:'com.google.gms.google-services'
```


dependency com.google.gms:google-services is a doc file which has information about firebase project_number,project_id, package_name of your application. Which you have created at the time of project creation from firebase. And that doc file you have to add in your app build file.

3. Add P5FireBaseManager and P5FireBasePushNotificationService inside application tag of your AndroidManifest.xml as shown below


```xml
<service
android:name="com.plugin.plumb5.P5FireBaseManager"
android:exported="true">
<intent-filter>
<action android:name="com.google.firebase.INSTANCE_ID_EVENT" />
</intent-filter>
</service>
<service
android:name="com.plugin.plumb5.P5FireBasePushNotificationService"
android:exported="true" >
<intent-filter>
< action android:name="com.google.firebase.MESSAGING_EVENT" />
</intent-filter>
</service>
```
