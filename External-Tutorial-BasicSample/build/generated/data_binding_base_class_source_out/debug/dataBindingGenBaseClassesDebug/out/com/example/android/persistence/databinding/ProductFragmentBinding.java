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
import android.widget.FrameLayout;
import android.widget.TextView;
import com.example.android.persistence.viewmodel.ProductViewModel;

public abstract class ProductFragmentBinding extends ViewDataBinding {
  @NonNull
  public final RecyclerView commentList;

  @NonNull
  public final FrameLayout commentsListWrapper;

  @NonNull
  public final TextView loadingCommentsTv;

  @Bindable
  protected boolean mIsLoading;

  @Bindable
  protected ProductViewModel mProductViewModel;

  protected ProductFragmentBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, RecyclerView commentList, FrameLayout commentsListWrapper,
      TextView loadingCommentsTv) {
    super(_bindingComponent, _root, _localFieldCount);
    this.commentList = commentList;
    this.commentsListWrapper = commentsListWrapper;
    this.loadingCommentsTv = loadingCommentsTv;
  }

  public abstract void setIsLoading(boolean isLoading);

  public boolean getIsLoading() {
    return mIsLoading;
  }

  public abstract void setProductViewModel(@Nullable ProductViewModel productViewModel);

  @Nullable
  public ProductViewModel getProductViewModel() {
    return mProductViewModel;
  }

  @NonNull
  public static ProductFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ProductFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ProductFragmentBinding>inflate(inflater, com.example.android.persistence.R.layout.product_fragment, root, attachToRoot, component);
  }

  @NonNull
  public static ProductFragmentBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ProductFragmentBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ProductFragmentBinding>inflate(inflater, com.example.android.persistence.R.layout.product_fragment, null, false, component);
  }

  public static ProductFragmentBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ProductFragmentBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ProductFragmentBinding)bind(component, view, com.example.android.persistence.R.layout.product_fragment);
  }
}
