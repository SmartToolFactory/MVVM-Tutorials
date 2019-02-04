package com.example.android.codelabs.lifecycle.databinding;
import com.example.android.codelabs.lifecycle.R;
import com.example.android.codelabs.lifecycle.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ChronoActivityDatabindingBindingImpl extends ChronoActivityDatabindingBinding  {

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
    private final android.widget.TextView mboundView1;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ChronoActivityDatabindingBindingImpl(@Nullable android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private ChronoActivityDatabindingBindingImpl(android.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.RelativeLayout) bindings[0]
            , (android.widget.TextView) bindings[2]
            );
        this.activityMain.setTag(null);
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
    /* flag mapping
        flag 0 (0x1L): elapsedTime
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}