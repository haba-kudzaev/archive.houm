// ! rx1310 <rx1310@inbox.ru> | Copyright (c) rx1310, 2020 | MIT License

package ru.rx1310.app.houm.activities;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.view.Menu;

import ru.rx1310.app.houm.R;
import ru.rx1310.app.houm.ShortcutNotificationManager;

public class SettingsActivity extends PreferenceActivity implements
		OnSharedPreferenceChangeListener {

	public static final String KEY_PREF_NOTIFICATION = "pref_notification";
    public static final String KEY_PREF_NOTIFICATION_PRIORITY = "pref_notification_priority";

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.preferences);

		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
			//remove priority preference (not supported)
			PreferenceCategory notificationCategory =
					(PreferenceCategory) findPreference("pref_category_notification");
			ListPreference priorityPreference =
					(ListPreference) findPreference("pref_notification_priority");
			notificationCategory.removePreference(priorityPreference);
		}
	}


	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
        if (key.equals(KEY_PREF_NOTIFICATION) || key.equals(KEY_PREF_NOTIFICATION_PRIORITY)) {
            boolean notificationEnabled =
                    sharedPreferences.getBoolean(KEY_PREF_NOTIFICATION, false);
            ShortcutNotificationManager shortcutNotificationManager = new ShortcutNotificationManager();
			shortcutNotificationManager.cancelNotification(this);
			if (notificationEnabled) {
                final String strPriority =
                        sharedPreferences.getString(SettingsActivity.KEY_PREF_NOTIFICATION_PRIORITY,
                                "low");
                final int priority = ShortcutNotificationManager.getPriorityFromString(strPriority);
                shortcutNotificationManager.showNotification(this, priority);
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//MenuInflater inflater = getMenuInflater();
		//inflater.inflate(R.menu.menu_about, menu);
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		super.onResume();
		getPreferenceScreen().getSharedPreferences()
				.registerOnSharedPreferenceChangeListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onPause() {
		getPreferenceScreen().getSharedPreferences()
				.unregisterOnSharedPreferenceChangeListener(this);
		super.onPause();
		finish();
	}

}
