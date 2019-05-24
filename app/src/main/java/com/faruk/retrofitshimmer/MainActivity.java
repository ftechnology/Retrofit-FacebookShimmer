/**
 * @author Md Faruk Hossain
 * FIXME
 */

package com.faruk.retrofitshimmer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.faruk.retrofitshimmer.adapter.PetsListAdapter;
import com.faruk.retrofitshimmer.appfrm.BaseActivity;
import com.faruk.retrofitshimmer.appfrm.Constants;
import com.faruk.retrofitshimmer.appfrm.NetworkUtill;
import com.faruk.retrofitshimmer.appfrm.ResponseObject;
import com.facebook.shimmer.ShimmerFrameLayout;


public class MainActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private PetsListAdapter mAdapter;
    private ProgressDialog mProgressDoalog;
    ShimmerFrameLayout shimmerLayout = null;

    @Override
    protected void createView(Bundle savedInstanceState) {
        this.setContentView(R.layout.activity_main);
        initUI();
        setupRecycleView();

    }

    @Override
    protected void createAdapter() {
        mAdapter = new PetsListAdapter(this);
        mAdapter.setmNotifyObserver(this);
    }

    @Override
    protected void loadData() {
        if (!NetworkUtill.isNetworkAvailable(this)) {
            Toast.makeText(this, getString(R.string.STR_NO_INTERNET), Toast.LENGTH_LONG).show();
            return;
        }
        mAdapter.loadData();
    }

    private void initUI() {
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("");

        shimmerLayout = (ShimmerFrameLayout) findViewById(R.id.shimmerLayout);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setupRecycleView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider));
        mRecyclerView.addItemDecoration(itemDecorator);
    }


    @Override
    public void doUpdateRequest(@NonNull ResponseObject response) {
        int code = response.getResponseCode();
        if (code == Constants.CODE_LOAD_START) {
            showShimmerEffect();
        }

        if (code == Constants.CODE_SUCCESS) {
            hideShimmerEffect();
        }

        if (code == Constants.CODE_FAIL || code == Constants.CODE_UNSUCCESSFULL) {
            hideShimmerEffect();
            Toast.makeText(mInstance, getString(R.string.STR_FETCH_FAIL), Toast.LENGTH_LONG).show();
        }
    }

    private void showShimmerEffect() {
        shimmerLayout.startShimmerAnimation();
    }

    private void hideShimmerEffect() {
        shimmerLayout.stopShimmerAnimation();
        shimmerLayout.setVisibility(View.GONE);
    }
}
