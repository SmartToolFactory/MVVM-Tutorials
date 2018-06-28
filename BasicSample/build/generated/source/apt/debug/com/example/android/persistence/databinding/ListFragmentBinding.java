package com.example.android.persistence.databinding;
import com.example.android.persistence.R;
import com.example.android.persistence.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ListFragmentBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    public final android.widget.TextView loadingTv;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.support.v7.widget.RecyclerView productsList;
    // variables
    @Nullable
    private boolean mIsLoading;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ListFragmentBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.loadingTv = (android.widget.TextView) bindings[1];
        this.loadingTv.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.productsList = (android.support.v7.widget.RecyclerView) bindings[2];
        this.productsList.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.isLoading == variableId) {
            setIsLoading((boolean) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setIsLoading(boolean IsLoading) {
        this.mIsLoading = IsLoading;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.isLoading);
        super.requestRebind();
    }
    public boolean getIsLoading() {
        return mIsLoading;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        boolean isLoading = mIsLoading;
        boolean IsLoading1 = false;

        if ((dirtyFlags & 0x3L) != 0) {



                // read !isLoading
                IsLoading1 = !isLoading;
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            com.example.android.persistence.ui.BindingAdapters.showHide(this.loadingTv, isLoading);
            com.example.android.persistence.ui.BindingAdapters.showHide(this.productsList, IsLoading1);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ListFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ListFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ListFragmentBinding>inflate(inflater, com.example.android.persistence.R.layout.list_fragment, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ListFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ListFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.example.android.persistence.R.layout.list_fragment, null, false), bindingComponent);
    }
    @NonNull
    public static ListFragmentBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ListFragmentBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/list_fragment_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ListFragmentBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): isLoading
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}