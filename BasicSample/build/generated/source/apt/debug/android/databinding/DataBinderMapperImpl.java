
package android.databinding;
import com.example.android.persistence.BR;
class DataBinderMapperImpl extends android.databinding.DataBinderMapper {
    public DataBinderMapperImpl() {
    }
    @Override
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.example.android.persistence.R.layout.product_fragment:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/product_fragment_0".equals(tag)) {
                            return new com.example.android.persistence.databinding.ProductFragmentBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for product_fragment is invalid. Received: " + tag);
                }
                case com.example.android.persistence.R.layout.comment_item:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/comment_item_0".equals(tag)) {
                            return new com.example.android.persistence.databinding.CommentItemBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for comment_item is invalid. Received: " + tag);
                }
                case com.example.android.persistence.R.layout.product_item:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/product_item_0".equals(tag)) {
                            return new com.example.android.persistence.databinding.ProductItemBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for product_item is invalid. Received: " + tag);
                }
                case com.example.android.persistence.R.layout.list_fragment:
 {
                        final Object tag = view.getTag();
                        if(tag == null) throw new java.lang.RuntimeException("view must have a tag");
                    if ("layout/list_fragment_0".equals(tag)) {
                            return new com.example.android.persistence.databinding.ListFragmentBinding(bindingComponent, view);
                    }
                        throw new java.lang.IllegalArgumentException("The tag for list_fragment is invalid. Received: " + tag);
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
            case -399488426: {
                if(tag.equals("layout/product_fragment_0")) {
                    return com.example.android.persistence.R.layout.product_fragment;
                }
                break;
            }
            case 198676841: {
                if(tag.equals("layout/comment_item_0")) {
                    return com.example.android.persistence.R.layout.comment_item;
                }
                break;
            }
            case 144912601: {
                if(tag.equals("layout/product_item_0")) {
                    return com.example.android.persistence.R.layout.product_item;
                }
                break;
            }
            case 727741277: {
                if(tag.equals("layout/list_fragment_0")) {
                    return com.example.android.persistence.R.layout.list_fragment;
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
            ,"callback"
            ,"comment"
            ,"isLoading"
            ,"product"
            ,"productViewModel"};
    }
}