package fast.kopach.math;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.Toast;

import fast.kopach.math.games.VariablesInGame;
import fast.kopach.math.menu.MenuActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

public class Broadcast extends BroadcastReceiver{

    final public static String ONE_TIME="onetime";

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm=(PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl= pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"YOUR TAG");
        //Осуществляем блокировку
        wl.acquire();

        //Здесь можно делать обработку.
        Bundle extras= intent.getExtras();
        StringBuilder msgStr=new StringBuilder();

        if(extras!=null & extras.getBoolean(ONE_TIME, Boolean.FALSE)){
        //проверяем параметр ONE_TIME, если это одиночный будильник,
        //выводим соответствующее сообщение.
            msgStr.append(" ");
        }
        Format formatter=new SimpleDateFormat("hh:mm:ss a");
        msgStr.append(formatter.format(new Date()));

       // Toast.makeText(context, msgStr + "hgkjdl", Toast.LENGTH_LONG).show();
        sendBigTextStyleNotification(context);

        //Разблокируем поток.
        wl.release();
    }

    public void SetAlarm(Context context) {
        AlarmManager am =(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, Broadcast.class);
        intent.putExtra(ONE_TIME, Boolean.FALSE);//Задаем параметр интента
        PendingIntent pi= PendingIntent.getBroadcast(context,0, intent,0);
        //Устанавливаем интервал срабатывания в 5 секунд.
        am.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + VariablesInGame.TIME_TO_BONUS_COINS, VariablesInGame.TIME_TO_BONUS_COINS, pi);
    }

    public void sendBigTextStyleNotification(Context context) {
        String bigText = "You have received 10 gift coins";  //Вам в подарунок нараховано 10 монет

        Intent intent = new Intent(context, MenuActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);

        Notification.Builder builder = new Notification.Builder(context)
                .setTicker("You got a gift")  //Ви отримали подарунок!
                .setContentTitle("Gift")  //Подарунок
                .setContentText("You have received 10 gift coins")  //Вам в подарунок нараховано 10 монет
                .setSmallIcon(R.drawable.coin_128x128)
                .setContentIntent(pIntent)
               // .addAction(R.mipmap.ic_launcher, "Запустити гру", pIntent).setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH);

        PreferenceHelper.addCoin(10);

        Notification notification = new Notification.BigTextStyle(builder).bigText(bigText).build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(567, notification);
    }
}
