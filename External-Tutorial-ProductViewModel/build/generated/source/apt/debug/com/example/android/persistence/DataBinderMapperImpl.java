package com.example.android.persistence;

import android.databinding.DataBinderMapper;
import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import com.example.android.persistence.databinding.CommentItemBindingImpl;
import com.example.android.persistence.databinding.ListFragmentBindingImpl;
import com.example.android.persistence.databinding.ProductFragmentBindingImpl;
import com.example.android.persistence.databinding.ProductItemBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_COMMENTITEM = 1;

  private static final int LAYOUT_LISTFRAGMENT = 2;

  private static final int LAYOUT_PRODUCTFRAGMENT = 3;

  private static final int LAYOUT_PRODUCTITEM = 4;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(4);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.android.persistence.R.layout.comment_item, LAYOUT_COMMENTITEM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.android.persistence.R.layout.list_fragment, LAYOUT_LISTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.android.persistence.R.layout.product_fragment, LAYOUT_PRODUCTFRAGMENT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.android.persistence.R.layout.product_item, LAYOUT_PRODUCTITEM);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_COMMENTITEM: {
          if ("layout/comment_item_0".equals(tag)) {
            return new CommentItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for comment_item is invalid. Received: " + tag);
        }
        case  LAYOUT_LISTFRAGMENT: {
          if ("layout/list_fragment_0".equals(tag)) {
            return new ListFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for list_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_PRODUCTFRAGMENT: {
          if ("layout/product_fragment_0".equals(tag)) {
            return new ProductFragmentBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for product_fragment is invalid. Received: " + tag);
        }
        case  LAYOUT_PRODUCTITEM: {
          if ("layout/product_item_0".equals(tag)) {
            return new ProductItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for product_item is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new com.android.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(7);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "isLoading");
      sKeys.put(2, "product");
      sKeys.put(3, "callback");
      sKeys.put(4, "comment");
      sKeys.put(5, "productViewModel");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(4);

    static {
      sKeys.put("layout/comment_item_0", com.example.android.persistence.R.layout.comment_item);
      sKeys.put("layout/list_fragment_0", com.example.android.persistence.R.layout.list_fragment);
      sKeys.put("layout/product_fragment_0", com.example.android.persistence.R.layout.product_fragment);
      sKeys.put("layout/product_item_0", com.example.android.persistence.R.layout.product_item);
    }
  }
}
