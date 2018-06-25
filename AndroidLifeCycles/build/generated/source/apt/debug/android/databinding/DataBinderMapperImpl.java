
package android.databinding;
import com.example.android.codelabs.lifecycle.BR;
class DataBinderMapperImpl extends android.databinding.DataBinderMapper {
    public DataBinderMapperImpl() {
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.example.android.codelabs.lifecycle.R.layout.chrono_activity_databinding:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/chrono_activity_databinding_0".equals(tag)) {
                            return new com.example.android.codelabs.lifecycle.databinding.ChronoActivityDatabindingBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for chrono_activity_databinding is invalid. Received: " + tag);
                }
        }
        return null;
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    @Override
    public int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 1236195411: {
                if(tag.equals("layout/chrono_activity_databinding_0")) {
                    return com.example.android.codelabs.lifecycle.R.layout.chrono_activity_databinding;
                }
                break;
            }
        }
        return 0;
    }
    @Override
    public String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"elapsedTime"};
    }
}