// ! rx1310 <rx1310@inbox.ru> | Copyright (c) rx1310, 2020 | MIT License

package ru.rx1310.app.houm;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import ru.rx1310.app.houm.threading.SimpleTaskConsumerManager;


public class ImageLoadingTask extends SimpleTaskConsumerManager.Task {
    private final ImageView mImageView;
    private final LaunchableActivity mLaunchableActivity;
    private final SharedData mSharedData;

    public ImageLoadingTask(final ImageView imageView, final LaunchableActivity launchableActivity,
                            final SharedData sharedData) {
        this.mImageView = imageView;
        this.mLaunchableActivity = launchableActivity;
        this.mSharedData = sharedData;
    }

    public boolean doTask() {
        final Drawable activityIcon =
                mLaunchableActivity.getActivityIcon(mSharedData.mPackageManager, mSharedData.mContext,
                        mSharedData.mIconSizePixels);
        mSharedData.mActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (mImageView.getTag() == mLaunchableActivity)
                    mImageView.setImageDrawable(activityIcon);
            }
        });
        return true;
    }

    public static class SharedData {
        private final Activity mActivity;
        private final PackageManager mPackageManager;
        private final Context mContext;
        private final int mIconSizePixels;

        public SharedData(final Activity activity, final PackageManager packageManager,
                          final Context context, final int iconSizePixels) {
            this.mActivity = activity;
            this.mPackageManager = packageManager;
            this.mContext = context;
            this.mIconSizePixels = iconSizePixels;
        }
    }

}
