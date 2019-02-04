package com.example.android.persistence.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class ListFragmentBinding extends ViewDataBinding {
  @NonNull
  public final TextView loadingTv;

  @NonNull
  public final RecyclerView productsList;

  @Bindable
  protected boolean mIsLoading;

  protected ListFragmentBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextView loadingTv, RecyclerView productsList) {
    super(_bindingComponent, _root, _localFieldCount);
    this.loadingTv = loadingTv;
    this.productsList = productsList;
  }

  public abstract void setIsLoading(boolean isLoading);

  public boolean getIsLoading() {
    return mIsLoading;
  }

  @NonNull
  public static ListFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ListFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ListFragmentBinding>inflate(inflater, com.example.android.persistence.R.layout.list_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static ListFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ListFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ListFragmentBinding>inflate(inflater, com.example.android.persistence.R.layout.list_fragment, null, false, component);
  }

  public static ListFragmentBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ListFragmentBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ListFragmentBinding)bind(component, view, com.example.android.persistence.R.layout.list_fragment);
  }
}
