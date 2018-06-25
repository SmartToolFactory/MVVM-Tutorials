package com.example.android.codelabs.lifecycle.databinding;
import com.example.android.codelabs.lifecycle.R;
import com.example.android.codelabs.lifecycle.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ChronoActivityDatabindingBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.hello_textview, 2);
    }
    // views
    @NonNull
    public final android.widget.RelativeLayout activityMain;
    @NonNull
    public final android.widget.TextView helloTextview;
    @NonNull
    private final android.widget.TextView mboundView1;
    // variables
    @Nullable
    private android.databinding.ObservableField<java.lang.Long> mElapsedTime;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ChronoActivityDatabindingBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds);
        this.activityMain = (android.widget.RelativeLayout) bindings[0];
        this.activityMain.setTag(null);
        this.helloTextview = (android.widget.TextView) bindings[2];
        this.mboundView1 = (android.widget.TextView) bindings[1];
        this.mboundView1.setTag(null);
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
        if (BR.elapsedTime == variableId) {
            setElapsedTime((android.databinding.ObservableField<java.lang.Long>) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setElapsedTime(@Nullable android.databinding.ObservableField<java.lang.Long> ElapsedTime) {
        updateRegistration(0, ElapsedTime);
        this.mElapsedTime = ElapsedTime;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.elapsedTime);
        super.requestRebind();
    }
    @Nullable
    public android.databinding.ObservableField<java.lang.Long> getElapsedTime() {
        return mElapsedTime;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeElapsedTime((android.databinding.ObservableField<java.lang.Long>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeElapsedTime(android.databinding.ObservableField<java.lang.Long> ElapsedTime, int fieldId) {
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
        java.lang.Long elapsedTimeGet = null;
        android.databinding.ObservableField<java.lang.Long> elapsedTime = mElapsedTime;
        java.lang.String stringFormatMboundView1AndroidStringSecondsElapsedTime = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (elapsedTime != null) {
                    // read elapsedTime.get()
                    elapsedTimeGet = elapsedTime.get();
                }


                // read String.format(@android:string/seconds, elapsedTime.get())
                stringFormatMboundView1AndroidStringSecondsElapsedTime = java.lang.String.format(mboundView1.getResources().getString(R.string.seconds), elapsedTimeGet);
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, stringFormatMboundView1AndroidStringSecondsElapsedTime);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ChronoActivityDatabindingBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ChronoActivityDatabindingBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ChronoActivityDatabindingBinding>inflate(inflater, com.example.android.codelabs.lifecycle.R.layout.chrono_activity_databinding, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ChronoActivityDatabindingBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ChronoActivityDatabindingBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.example.android.codelabs.lifecycle.R.layout.chrono_activity_databinding, null, false), bindingComponent);
    }
    @NonNull
    public static ChronoActivityDatabindingBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ChronoActivityDatabindingBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/chrono_activity_databinding_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ChronoActivityDatabindingBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): elapsedTime
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}