package ysn.temanbioskop.databinding;
import ysn.temanbioskop.R;
import ysn.temanbioskop.BR;
import android.view.View;
public class ActivityMainBinding extends android.databinding.ViewDataBinding  {

    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.image_view_background_activity_main, 1);
        sViewsWithIds.put(R.id.toolbar_activity_main, 2);
        sViewsWithIds.put(R.id.image_view_icon_app_activity_main, 3);
        sViewsWithIds.put(R.id.relative_layout_footer_activity_main, 4);
        sViewsWithIds.put(R.id.text_view_do_you, 5);
        sViewsWithIds.put(R.id.text_view_or, 6);
        sViewsWithIds.put(R.id.text_view_sign_in, 7);
        sViewsWithIds.put(R.id.text_view_sign_up, 8);
        sViewsWithIds.put(R.id.tab_layout_menu_activity_main, 9);
        sViewsWithIds.put(R.id.view_pager_menu_activity_main, 10);
    }
    // views
    public final android.widget.ImageView imageViewBackgroundActivityMain;
    public final android.widget.ImageView imageViewIconAppActivityMain;
    public final android.widget.RelativeLayout relativeLayoutActivityMain;
    public final android.widget.RelativeLayout relativeLayoutFooterActivityMain;
    public final android.support.design.widget.TabLayout tabLayoutMenuActivityMain;
    public final android.widget.TextView textViewDoYou;
    public final android.widget.TextView textViewOr;
    public final android.widget.TextView textViewSignIn;
    public final android.widget.TextView textViewSignUp;
    public final android.support.v7.widget.Toolbar toolbarActivityMain;
    public final android.support.v4.view.ViewPager viewPagerMenuActivityMain;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityMainBinding(android.databinding.DataBindingComponent bindingComponent, View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.imageViewBackgroundActivityMain = (android.widget.ImageView) bindings[1];
        this.imageViewIconAppActivityMain = (android.widget.ImageView) bindings[3];
        this.relativeLayoutActivityMain = (android.widget.RelativeLayout) bindings[0];
        this.relativeLayoutActivityMain.setTag(null);
        this.relativeLayoutFooterActivityMain = (android.widget.RelativeLayout) bindings[4];
        this.tabLayoutMenuActivityMain = (android.support.design.widget.TabLayout) bindings[9];
        this.textViewDoYou = (android.widget.TextView) bindings[5];
        this.textViewOr = (android.widget.TextView) bindings[6];
        this.textViewSignIn = (android.widget.TextView) bindings[7];
        this.textViewSignUp = (android.widget.TextView) bindings[8];
        this.toolbarActivityMain = (android.support.v7.widget.Toolbar) bindings[2];
        this.viewPagerMenuActivityMain = (android.support.v4.view.ViewPager) bindings[10];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
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

    public boolean setVariable(int variableId, Object variable) {
        switch(variableId) {
        }
        return false;
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
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    public static ActivityMainBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityMainBinding inflate(android.view.LayoutInflater inflater, android.view.ViewGroup root, boolean attachToRoot, android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityMainBinding>inflate(inflater, ysn.temanbioskop.R.layout.activity_main, root, attachToRoot, bindingComponent);
    }
    public static ActivityMainBinding inflate(android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityMainBinding inflate(android.view.LayoutInflater inflater, android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(ysn.temanbioskop.R.layout.activity_main, null, false), bindingComponent);
    }
    public static ActivityMainBinding bind(android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    public static ActivityMainBinding bind(android.view.View view, android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_main_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityMainBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}