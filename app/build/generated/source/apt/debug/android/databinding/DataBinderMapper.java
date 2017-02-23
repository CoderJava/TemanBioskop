
package android.databinding;
import ysn.temanbioskop.BR;
class DataBinderMapper {
    final static int TARGET_MIN_SDK = 17;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case ysn.temanbioskop.R.layout.activity_main:
                    return ysn.temanbioskop.databinding.ActivityMainBinding.bind(view, bindingComponent);
                case ysn.temanbioskop.R.layout.fragment_check_movie_today:
                    return ysn.temanbioskop.databinding.FragmentCheckMovieTodayBinding.bind(view, bindingComponent);
                case ysn.temanbioskop.R.layout.fragment_home:
                    return ysn.temanbioskop.databinding.FragmentHomeBinding.bind(view, bindingComponent);
                case ysn.temanbioskop.R.layout.activity_check_movie_today:
                    return ysn.temanbioskop.databinding.ActivityCheckMovieTodayBinding.bind(view, bindingComponent);
                case ysn.temanbioskop.R.layout.fragment_result_check_movie_today:
                    return ysn.temanbioskop.databinding.FragmentResultCheckMovieTodayBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 423753077: {
                if(tag.equals("layout/activity_main_0")) {
                    return ysn.temanbioskop.R.layout.activity_main;
                }
                break;
            }
            case 1037108738: {
                if(tag.equals("layout/fragment_check_movie_today_0")) {
                    return ysn.temanbioskop.R.layout.fragment_check_movie_today;
                }
                break;
            }
            case -1115993926: {
                if(tag.equals("layout/fragment_home_0")) {
                    return ysn.temanbioskop.R.layout.fragment_home;
                }
                break;
            }
            case -999210687: {
                if(tag.equals("layout/activity_check_movie_today_0")) {
                    return ysn.temanbioskop.R.layout.activity_check_movie_today;
                }
                break;
            }
            case 1162800500: {
                if(tag.equals("layout/fragment_result_check_movie_today_0")) {
                    return ysn.temanbioskop.R.layout.fragment_result_check_movie_today;
                }
                break;
            }
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"};
    }
}