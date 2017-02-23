package ysn.temanbioskop.views.base;

/**
 * Created by root on 21/02/17.
 */

public interface Presenter<T extends View> {

    void onAttach(T view);

    void onDetach();

}
