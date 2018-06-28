package com.example.android.persistence.databinding;
import com.example.android.persistence.R;
import com.example.android.persistence.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ProductFragmentBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(5);
        sIncludes.setIncludes(0, 
            new String[] {"product_item"},
            new int[] {3},
            new int[] {R.layout.product_item});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.comments_list_wrapper, 4);
    }
    // views
    @NonNull
    public final android.support.v7.widget.RecyclerView commentList;
    @NonNull
    public final android.widget.FrameLayout commentsListWrapper;
    @NonNull
    public final android.widget.TextView loadingCommentsTv;
    @Nullable
    private final com.example.android.persistence.databinding.ProductItemBinding mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView01;
    // variables
    @Nullable
    private boolean mIsLoading;
    @Nullable
    private com.example.android.persistence.viewmodel.ProductViewModel mProductViewModel;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ProductFragmentBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.commentList = (android.support.v7.widget.RecyclerView) bindings[2];
        this.commentList.setTag(null);
        this.commentsListWrapper = (android.widget.FrameLayout) bindings[4];
        this.loadingCommentsTv = (android.widget.TextView) bindings[1];
        this.loadingCommentsTv.setTag(null);
        this.mboundView0 = (com.example.android.persistence.databinding.ProductItemBinding) bindings[3];
        setContainedBinding(this.mboundView0);
        this.mboundView01 = (android.widget.LinearLayout) bindings[0];
        this.mboundView01.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
        }
        mboundView0.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (mboundView0.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.isLoading == variableId) {
            setIsLoading((boolean) variable);
        }
        else if (BR.productViewModel == variableId) {
            setProductViewModel((com.example.android.persistence.viewmodel.ProductViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setIsLoading(boolean IsLoading) {
        this.mIsLoading = IsLoading;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.isLoading);
        super.requestRebind();
    }
    public boolean getIsLoading() {
        return mIsLoading;
    }
    public void setProductViewModel(@Nullable com.example.android.persistence.viewmodel.ProductViewModel ProductViewModel) {
        this.mProductViewModel = ProductViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.productViewModel);
        super.requestRebind();
    }
    @Nullable
    public com.example.android.persistence.viewmodel.ProductViewModel getProductViewModel() {
        return mProductViewModel;
    }

    @Override
    public void setLifecycleOwner(@Nullable android.arch.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        mboundView0.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeProductViewModelProduct((android.databinding.ObservableField<com.example.android.persistence.db.entity.ProductEntity>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeProductViewModelProduct(android.databinding.ObservableField<com.example.android.persistence.db.entity.ProductEntity> ProductViewModelProduct, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        android.databinding.ObservableField<com.example.android.persistence.db.entity.ProductEntity> productViewModelProduct = null;
        boolean isLoading = mIsLoading;
        com.example.android.persistence.viewmodel.ProductViewModel productViewModel = mProductViewModel;
        com.example.android.persistence.db.entity.ProductEntity productViewModelProductGet = null;
        boolean IsLoading1 = false;

        if ((dirtyFlags & 0xaL) != 0) {



                // read !isLoading
                IsLoading1 = !isLoading;
        }
        if ((dirtyFlags & 0xdL) != 0) {



                if (productViewModel != null) {
                    // read productViewModel.product
                    productViewModelProduct = productViewModel.product;
                }
                updateRegistration(0, productViewModelProduct);


                if (productViewModelProduct != null) {
                    // read productViewModel.product.get()
                    productViewModelProductGet = productViewModelProduct.get();
                }
        }
        // batch finished
        if ((dirtyFlags & 0xaL) != 0) {
            // api target 1

            com.example.android.persistence.ui.BindingAdapters.showHide(this.commentList, IsLoading1);
            com.example.android.persistence.ui.BindingAdapters.showHide(this.loadingCommentsTv, isLoading);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            this.mboundView0.setProduct(productViewModelProductGet);
        }
        executeBindingsOn(mboundView0);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ProductFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ProductFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ProductFragmentBinding>inflate(inflater, com.example.android.persistence.R.layout.product_fragment, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ProductFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ProductFragmentBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.example.android.persistence.R.layout.product_fragment, null, false), bindingComponent);
    }
    @NonNull
    public static ProductFragmentBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ProductFragmentBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/product_fragment_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ProductFragmentBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): productViewModel.product
        flag 1 (0x2L): isLoading
        flag 2 (0x3L): productViewModel
        flag 3 (0x4L): null
    flag mapping end*/
    //end
}