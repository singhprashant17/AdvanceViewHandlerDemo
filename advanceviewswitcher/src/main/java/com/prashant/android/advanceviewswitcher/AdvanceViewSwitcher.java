package com.prashant.android.advanceviewswitcher;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Prashant Singh
 */
public class AdvanceViewSwitcher {

    private final Context context;
    /**
     * the view which will be replaced by other state view
     */
    private final View mainView;
    /**
     * basic ViewFlipper for switching between different view states
     */
    private ViewFlipper viewFlipper;
    /**
     * default tag for the mainView
     */
    public static final int TAG_MAIN_VIEW = Integer.MAX_VALUE;
    /**
     * list of all views
     */
    private List<Cloner> viewsList;


    public AdvanceViewSwitcher(Context context, View mainView) {
        this.context = context;
        this.mainView = mainView;
        viewsList = new ArrayList<>();
        init();
    }

    private void init() {
        viewFlipper = new ViewFlipper(context);

        final ViewGroup superView = (ViewGroup) mainView.getParent();

        addViewFlipper(superView);
        addMainView(mainView);
    }

    /**
     * finding the parent view of mainView, replacing it with viewFlipper
     *
     * @param superView
     */
    private void addViewFlipper(ViewGroup superView) {
        for (int i = 0; i < superView.getChildCount(); i++) {
            if (superView.getChildAt(i).equals(mainView)) {
                superView.removeView(mainView);
                superView.addView(viewFlipper, i, new ViewGroup.LayoutParams(ViewGroup
                        .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                break;
            }
        }
    }

    /**
     * adding the mainView to viewFlipper
     *
     * @param mainView
     */
    private void addMainView(View mainView) {
        addViewWithTag(mainView, TAG_MAIN_VIEW);
    }

    /**
     * adding the view with specified tag. if tag is already present, returns
     *
     * @param view
     * @param viewTag
     */
    public void addViewWithTag(View view, int viewTag) {
        for (Cloner cloner : viewsList) {
            if (cloner.getTag() == viewTag) {
                Log.e(getClass().getSimpleName(), "view for the tag value " + viewTag + " is " +
                        "already present");
                return;
            }
        }

        /**
         * adding the cloner object of the view to the view list and viewFlipper.
         */
        viewsList.add(new Cloner(view, viewTag));
        viewFlipper.addView(view);
    }

    public void addViewWithTag(int viewId, int viewTag) {
        addViewWithTag(LayoutInflater.from(context).inflate(viewId, null), viewTag);
    }

    /**
     * displays view associated with the provided viewTag.
     * if not found, returns
     * @param viewTag
     */
    public void displayViewWithTag(int viewTag) {
        for (Cloner cloner : viewsList) {
            if (cloner.getTag() == viewTag) {
                for (int i = 0; i < viewFlipper.getChildCount(); i++) {
                    if (cloner.getView().equals(viewFlipper.getChildAt(i))) {
                        viewFlipper.setDisplayedChild(i);
                        return;
                    }
                }
                return;
            }
        }
    }

    /**
     * method to display mainView
     */
    public void displayMainView() {
        displayViewWithTag(TAG_MAIN_VIEW);
    }

    /**
     * method to add the view to the viewFlipper and display the view as soon as after adding
     *
     * @param view
     * @param viewTag
     */
    public void addAndDisplayWithTag(View view, int viewTag) {
        addViewWithTag(view, viewTag);
        displayViewWithTag(viewTag);
    }

    /**
     * TODO add transition animation to the views in viewFlipper
     *
     * @param inAnimation
     * @param outAnimation
     * @param interval
     */
    private void setTransitionAnimation(int inAnimation, int outAnimation, int interval) {
        if (inAnimation > 0) {
            viewFlipper.setInAnimation(context, inAnimation);
        }
        if (outAnimation > 0) {
            viewFlipper.setInAnimation(context, outAnimation);
        }
        if (interval > 0) {
            viewFlipper.setFlipInterval(interval);
        }
    }

    private void setTransitionAnimation(int inAnimation, int outAnimation) {
        setTransitionAnimation(inAnimation, outAnimation, 0);
    }

    /**
     * class for binding a view with its tag
     */
    class Cloner {

        private final View view;
        private final int tag;

        public Cloner(View view, int tag) {
            this.view = view;
            this.tag = tag;
        }

        public View getView() {
            return view;
        }

        public int getTag() {
            return tag;
        }
    }
}
