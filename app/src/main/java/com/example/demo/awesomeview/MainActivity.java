package com.example.demo.awesomeview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.prashant.android.advanceviewswitcher.AdvanceViewSwitcher;

public class MainActivity extends AppCompatActivity {

    private static final int TAG_LOADING_VIEW = 1;
    private static final int TAG_EXCEPTION_VIEW = 2;
    private AdvanceViewSwitcher advanceViewSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        advanceViewSwitcher = new AdvanceViewSwitcher(this, findViewById(R.id.txt));
    }

    @Override
    public void onStart() {
        super.onStart();
        advanceViewSwitcher.addViewWithTag(getLoadingView(), TAG_LOADING_VIEW);
        advanceViewSwitcher.addViewWithTag(getExceptionView(), TAG_EXCEPTION_VIEW);

        advanceViewSwitcher.displayViewWithTag(TAG_LOADING_VIEW);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                advanceViewSwitcher.displayViewWithTag(TAG_EXCEPTION_VIEW);
            }
        }, 4000);

    }

    private View getExceptionView() {
        final View view = LayoutInflater.from(this).inflate(R.layout.exception_layout,
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
        return LayoutInflater.from(this).inflate(R.layout.loader_layout, null);
    }
}
