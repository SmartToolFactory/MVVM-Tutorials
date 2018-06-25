package com.example.android.lifecycles.step4_solution;

import android.arch.lifecycle.GeneratedAdapter;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MethodCallsLogger;
import java.lang.Override;

public class BoundLocationManager_BoundLocationListener_LifecycleAdapter implements GeneratedAdapter {
  final BoundLocationManager.BoundLocationListener mReceiver;

  BoundLocationManager_BoundLocationListener_LifecycleAdapter(BoundLocationManager.BoundLocationListener receiver) {
    this.mReceiver = receiver;
  }

  @Override
  public void callMethods(LifecycleOwner owner, Lifecycle.Event event, boolean onAny,
      MethodCallsLogger logger) {
    boolean hasLogger = logger != null;
    if (onAny) {
      return;
    }
    if (event == Lifecycle.Event.ON_RESUME) {
      if (!hasLogger || logger.approveCall("addLocationListener", 1)) {
        mReceiver.addLocationListener();
      }
      return;
    }
    if (event == Lifecycle.Event.ON_PAUSE) {
      if (!hasLogger || logger.approveCall("removeLocationListener", 1)) {
        mReceiver.removeLocationListener();
      }
      return;
    }
  }
}
