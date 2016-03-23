package com.example.demo.awesomeview;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prashant.android.advanceviewswitcher.AdvanceViewSwitcher;

public class MyFragment extends Fragment {
    private static final int TAG_LOADING_VIEW = 1;
    private static final int TAG_EXCEPTION_VIEW = 2;
    private View view;
    private AdvanceViewSwitcher advanceViewSwitcher;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        view = inflater.inflate(R.layout.content_main, container, false);
        advanceViewSwitcher = new AdvanceViewSwitcher(getContext(), view.findViewById(R.id.txt));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        advanceViewSwitcher.addViewWithTag(getExceptionView(), TAG_EXCEPTION_VIEW);
        advanceViewSwitcher.addAndDisplayWithTag(R.layout.loader_layout, TAG_LOADING_VIEW);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                advanceViewSwitcher.displayViewWithTag(TAG_EXCEPTION_VIEW);
            }
        }, 4000);

    }

    private View getExceptionView() {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.exception_layout,
                null);
        view.findViewById(R.id.btnTryAgain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advanceViewSwitcher.displayViewWithTag(TAG_LOADING_VIEW);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        advanceViewSwitcher.displayMainView();
                    }
                }, 4000);
            }
        });
        return view;
    }

    private View getLoadingView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.loader_layout, null);
    }
}
