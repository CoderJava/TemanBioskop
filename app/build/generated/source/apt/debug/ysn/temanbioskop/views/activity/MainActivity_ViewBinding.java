// Generated code from Butter Knife. Do not modify!
package ysn.temanbioskop.views.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import ysn.temanbioskop.R;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.imageViewBackgroundActivityMain = Utils.findRequiredViewAsType(source, R.id.image_view_background_activity_main, "field 'imageViewBackgroundActivityMain'", ImageView.class);
    target.tabLayoutMenuActivityMain = Utils.findRequiredViewAsType(source, R.id.tab_layout_menu_activity_main, "field 'tabLayoutMenuActivityMain'", TabLayout.class);
    target.viewPagerMenuActivityMain = Utils.findRequiredViewAsType(source, R.id.view_pager_menu_activity_main, "field 'viewPagerMenuActivityMain'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageViewBackgroundActivityMain = null;
    target.tabLayoutMenuActivityMain = null;
    target.viewPagerMenuActivityMain = null;
  }
}
