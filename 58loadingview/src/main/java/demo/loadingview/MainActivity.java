package demo.loadingview;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import demo.loadingview.view.LoadingLayout;


public class MainActivity extends ActionBarActivity {

    private LoadingLayout mLoadingLayout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadingLayout = (LoadingLayout) findViewById(R.id.id_loading_view) ;
        mLoadingLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadingLayout.setVisibility(View.VISIBLE);
            }
        },500) ;
        mLoadingLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadingLayout.setVisibility(View.INVISIBLE);
            }
        },4000) ;
        mLoadingLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadingLayout.setVisibility(View.VISIBLE);
            }
        },6000) ;
    }
}
