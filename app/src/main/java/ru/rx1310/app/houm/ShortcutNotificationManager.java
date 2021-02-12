// ! rx1310 <rx1310@inbox.ru> | Copyright (c) rx1310, 2020 | MIT License

package ru.rx1310.app.houm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import ru.rx1310.app.houm.activities.MainActivity;

public class ShortcutNotificationManager {
	private static final int NOTIFICATION_ID = 0;

    public static int getPriorityFromString(String priority) {
        int i_priority = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (priority.toLowerCase().equals("max")) {
                i_priority = Notification.PRIORITY_MAX;
            } else if (priority.toLowerCase().equals("min")) {
                i_priority = Notification.PRIORITY_MIN;
            }
        }
        return i_priority;
    }

    public void showNotification(Context context, int priority) {
        final Intent resultIntent = new Intent(context, MainActivity.class);
        resultIntent.setAction(Intent.ACTION_MAIN);
        resultIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        //noinspection deprecation
        final Notification notification = new Notification.Builder(
                context)
				.setSmallIcon(R.drawable.ic_logo)
				.setContentTitle(context.getString(R.string.title_activity_search))
                .setOngoing(true)
                .setContentIntent(pendingIntent)
                .getNotification();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notification.priority = priority;
        }

        final NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(NOTIFICATION_ID, notification);
	}

	public void cancelNotification(Context context) {

        final NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

		mNotificationManager.cancel(NOTIFICATION_ID);
	}
}
