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
import com.example.android.persistence.model.Comment;
import com.example.android.persistence.ui.CommentClickCallback;

public abstract class CommentItemBinding extends ViewDataBinding {
  @Bindable
  protected Comment mComment;

  @Bindable
  protected CommentClickCallback mCallback;

  protected CommentItemBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount) {
    super(_bindingComponent, _root, _localFieldCount);
  }

  public abstract void setComment(@Nullable Comment comment);

  @Nullable
  public Comment getComment() {
    return mComment;
  }

  public abstract void setCallback(@Nullable CommentClickCallback callback);

  @Nullable
  public CommentClickCallback getCallback() {
    return mCallback;
  }

  @NonNull
  public static CommentItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static CommentItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<CommentItemBinding>inflate(inflater, com.example.android.persistence.R.layout.comment_item, root, attachToRoot, component);
  }

  @NonNull
  public static CommentItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static CommentItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<CommentItemBinding>inflate(inflater, com.example.android.persistence.R.layout.comment_item, null, false, component);
  }

  public static CommentItemBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static CommentItemBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (CommentItemBinding)bind(component, view, com.example.android.persistence.R.layout.comment_item);
  }
}
