// ! rx1310 <rx1310@inbox.ru> | Copyright (c) rx1310, 2020 | MIT License

package ru.rx1310.app.houm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

public class PackageChangedReceiver extends BroadcastReceiver {

        public void onReceive(Context context, Intent intent) {
            final String packageChangedName = intent.getData().getSchemeSpecificPart();

            if (packageChangedName != null && !packageChangedName.isEmpty()) {
                Log.d("Received thing", "EXTRA_CHANGED_COMPONENT_NAME_LIST=" +
                        packageChangedName);
                final SharedPreferences sharedPreferences = context.getSharedPreferences(
                        context.getPackageName() + "_preferences",
                        Context.MODE_PRIVATE);
                final SharedPreferences.Editor editor = sharedPreferences.edit();
                String packageChangedNames=sharedPreferences.getString("package_changed_names","");
                if(!packageChangedNames.contains(packageChangedName)) {
                    packageChangedNames += " " + packageChangedName;
                    editor.putString("package_changed_name", packageChangedNames.trim());
                }
                editor.apply();
            }
        }

}
