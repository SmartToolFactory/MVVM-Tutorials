package com.example.android.codelabs.lifecycle.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class ChronoActivityDatabindingBinding extends ViewDataBinding {
  @NonNull
  public final RelativeLayout activityMain;

  @NonNull
  public final TextView helloTextview;

  @Bindable
  protected ObservableField<Long> mElapsedTime;

  protected ChronoActivityDatabindingBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, RelativeLayout activityMain, TextView helloTextview) {
    super(_bindingComponent, _root, _localFieldCount);
    this.activityMain = activityMain;
    this.helloTextview = helloTextview;
  }

  public abstract void setElapsedTime(@Nullable ObservableField<Long> elapsedTime);

  @Nullable
  public ObservableField<Long> getElapsedTime() {
    return mElapsedTime;
  }

  @NonNull
  public static ChronoActivityDatabindingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ChronoActivityDatabindingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ChronoActivityDatabindingBinding>inflate(inflater, com.example.android.codelabs.lifecycle.R.layout.chrono_activity_databinding, root, attachToRoot, component);
  }

  @NonNull
  public static ChronoActivityDatabindingBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ChronoActivityDatabindingBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ChronoActivityDatabindingBinding>inflate(inflater, com.example.android.codelabs.lifecycle.R.layout.chrono_activity_databinding, null, false, component);
  }

  public static ChronoActivityDatabindingBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ChronoActivityDatabindingBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ChronoActivityDatabindingBinding)bind(component, view, com.example.android.codelabs.lifecycle.R.layout.chrono_activity_databinding);
  }
}
