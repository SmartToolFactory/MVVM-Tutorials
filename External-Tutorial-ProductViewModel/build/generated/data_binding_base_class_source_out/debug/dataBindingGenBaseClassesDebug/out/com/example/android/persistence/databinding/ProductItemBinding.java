package com.example.android.persistence.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.persistence.model.Product;
import com.example.android.persistence.ui.ProductClickCallback;

public abstract class ProductItemBinding extends ViewDataBinding {
  @NonNull
  public final TextView name;

  @Bindable
  protected Product mProduct;

  @Bindable
  protected ProductClickCallback mCallback;

  protected ProductItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, TextView name) {
    super(_bindingComponent, _root, _localFieldCount);
    this.name = name;
  }

  public abstract void setProduct(@Nullable Product product);

  @Nullable
  public Product getProduct() {
    return mProduct;
  }

  public abstract void setCallback(@Nullable ProductClickCallback callback);

  @Nullable
  public ProductClickCallback getCallback() {
    return mCallback;
  }

  @NonNull
  public static ProductItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ProductItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ProductItemBinding>inflate(inflater, com.example.android.persistence.R.layout.product_item, root, attachToRoot, component);
  }

  @NonNull
  public static ProductItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static ProductItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<ProductItemBinding>inflate(inflater, com.example.android.persistence.R.layout.product_item, null, false, component);
  }

  public static ProductItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static ProductItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (ProductItemBinding)bind(component, view, com.example.android.persistence.R.layout.product_item);
  }
}
