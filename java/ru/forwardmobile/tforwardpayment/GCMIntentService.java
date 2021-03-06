package ru.forwardmobile.tforwardpayment;

/**
 * Created by PiskunovI on 24.07.2014.
 */

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Base64;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

import java.io.UnsupportedEncodingException;

import static ru.forwardmobile.tforwardpayment.CommonUtilities.SENDER_ID;
import static ru.forwardmobile.tforwardpayment.CommonUtilities.displayMessage;

public class GCMIntentService extends GCMBaseIntentService {

    private static final String TAG = "GCMIntentService";

    public GCMIntentService() {
        super(SENDER_ID);
    }

    /**
     * Метод вызывается когда устройство зарегистрированно
     **/
    @Override
    protected void onRegistered(Context context, String registrationId) {
        Log.i(TAG, "Device registered: regId = " + registrationId);
        displayMessage(context, "Your device registred with GCM");
        Log.d("NAME", MainActivity.point);
        ServerUtilities.register(context, MainActivity.point, registrationId);
    }

    /**
     * Метод вызывается когда отменена регистрация устройства
     * */
    @Override
    protected void onUnregistered(Context context, String registrationId) {
        Log.i(TAG, "Device unregistered");
        displayMessage(context, getString(R.string.gcm_unregistered));
        ServerUtilities.unregister(context, registrationId);
    }

    /**
     * Метод вызывается когда получено новое сообщение
     * */
    @Override
    protected void onMessage(Context context, Intent intent) {
        Log.i(TAG, "Received message");
        String message = intent.getExtras().getString("message");

        //message = new String(getFromBase64(message));
        //Log.d("GCMOnReceicedMessage", message);
        //try {
            //message= new String(message.getBytes("UTF-8"));
            Log.d("GCMMessage",message);
        //} catch (UnsupportedEncodingException e) {
        //    e.printStackTrace();
        //}
        displayMessage(context, message);
        // notifies user
        generateNotification(context, message);
    }

    /**
     * Метод вызывается когда получено удаленное сообщение
     * */
    @Override
    protected void onDeletedMessages(Context context, int total) {
        Log.i(TAG, "Received deleted messages notification");
        String message = getString(R.string.gcm_deleted, total);
        displayMessage(context, message);
        // уведомление пользователя
        generateNotification(context, message);
    }

    /**
     * Метод вызывается на Ошибке
     * */
    @Override
    public void onError(Context context, String errorId) {
        Log.i(TAG, "Received error: " + errorId);
        displayMessage(context, getString(R.string.gcm_error, errorId));
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
        // логируем сообщение
        Log.i(TAG, "Received recoverable error: " + errorId);
        displayMessage(context, getString(R.string.gcm_recoverable_error,
                errorId));
        return super.onRecoverableError(context, errorId);
    }

    /**
     * Выдает уведомление, информирующее пользователя, что сервер послал сообщение.
     */
    private static void generateNotification(Context context, String message) {

        int icon = R.drawable.ic_launcher;
        //message = "Rododendron message to use in our application";
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);

        String title = context.getString(R.string.app_name);


        Intent notificationIntent = new Intent(context, GCMActivity.class);
        // устанавливаем intent таким образом, чтобы он не начал новую активити
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent =
                PendingIntent.getActivity(context, 0, notificationIntent, 0);
        notification.setLatestEventInfo(context, title, message, intent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // воспроизводим звук уведомления, установленный по-умолчанию
        notification.defaults |= Notification.DEFAULT_SOUND;

        // устройство вибриует, если вибрация включена
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(0, notification);

    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    private String getFromBase64(String message){
        byte[] data1 = Base64.decode(message, Base64.DEFAULT);
        String someText = null;
        try {
            someText = new String(data1, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return someText;
    }

}