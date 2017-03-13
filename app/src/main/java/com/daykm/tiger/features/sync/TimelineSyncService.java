package com.daykm.tiger.features.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import hugo.weaving.DebugLog;

public class TimelineSyncService extends Service {

	public static final String TAG = TimelineSyncService.class.getSimpleName();

	// Storage for an instance of the sync adapter
	private static TimelineSyncAdapter sSyncAdapter = null;
	// Object to use as a thread-safe lock
	private static final Object sSyncAdapterLock = new Object();

	/*
	 * Instantiate the sync adapter object.
	 */
	@Override @DebugLog public void onCreate() {
				/*
				 * Create the sync adapter as a singleton.
         * Set the sync adapter as syncable
         * Disallow parallel syncs
         */
		synchronized (sSyncAdapterLock) {
			if (sSyncAdapter == null) {
				sSyncAdapter = new TimelineSyncAdapter(getApplicationContext(), true);
			}
		}
	}

	@Override public IBinder onBind(Intent intent) {
				/*
         * Get the object that allows external processes
         * to call onPerformSync(). The object is created
         * in the base class code when the SyncAdapter
         * constructors call super()
         */
		return sSyncAdapter.getSyncAdapterBinder();
	}
}