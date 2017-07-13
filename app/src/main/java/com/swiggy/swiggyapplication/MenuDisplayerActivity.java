package com.swiggy.swiggyapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.swiggy.swiggyapplication.adapter.VariantDisplayAdapter;
import com.swiggy.swiggyapplication.network.IOManager;
import com.swiggy.swiggyapplication.responsedto.ExcludeItem;
import com.swiggy.swiggyapplication.responsedto.ExcludeParentList;
import com.swiggy.swiggyapplication.responsedto.VariantGroup;
import com.swiggy.swiggyapplication.responsedto.VariantsResponse;
import com.swiggy.swiggyapplication.responsedto.Variation;
import com.swiggy.swiggyapplication.utility.DialogUtils;
import com.swiggy.swiggyapplication.utility.IBundleKeys;
import com.swiggy.swiggyapplication.utility.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuDisplayerActivity extends AppCompatActivity implements VariantDisplayAdapter.OnItemClickListener {
    private static final String TAG = MenuDisplayerActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private VariantDisplayAdapter mVariantDisplayAdapter;
    private VariantsResponse mVariantResponse;
    private String mGroupName;
    private int mSelectedVariationID;
    private ArrayList<Variation> mVariationList = new ArrayList<>();
    private Toolbar mToolBar;
    private ProgressBar mProgressBar;

    private int mGrpID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        Bundle bundle;
        if ((bundle = getIntent().getExtras()) != null) {
            mVariantResponse = bundle.getParcelable(IBundleKeys.VARIANT_RESPONSE);

            ArrayList<Variation> variationList =  bundle.getParcelableArrayList(IBundleKeys.VARIANT_LIST);

            if (variationList != null) {
                mVariationList.addAll(variationList);
            }

            mGroupName = bundle.getString(IBundleKeys.SELECTED_GROUP_NAME);

            if (!TextUtils.isEmpty(mGroupName)) {
                mToolBar.setTitle(getString(R.string.select) + " " + mGroupName);
            } else {
                mToolBar.setTitle(getString(R.string.select_food));
            }
            mGrpID = bundle.getInt(IBundleKeys.GROUP_ID);
            mSelectedVariationID = bundle.getInt(IBundleKeys.SELECTED_VARIATION_ID);

        //    processExcludeList();
        }

        mVariantDisplayAdapter = new VariantDisplayAdapter(this, mVariationList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mVariantDisplayAdapter);

        if ((mVariationList == null || mVariationList.isEmpty())) {
            if (NetworkUtil.isConnectionAvailable(this)) {
                mToolBar.setTitle(R.string.select_food);
                requestVariantCategories();
            } else {

                DialogUtils.showToast(R.string.no_internet_connection, this);
            }
        } else {
            mProgressBar.setVisibility(View.GONE);
        }
    }

  /*  private void processExcludeList() {
        if (mVariantResponse != null) {
            List<ExcludeParentList> excludeList = mVariantResponse.getVariants().getExcludeList();

            for (ExcludeParentList excludeList1 : excludeList) {
                for (ExcludeItem excludeItem2 : excludeList1.getExcludeListItem()) {
                    if (Integer.parseInt(excludeItem2.getGroupId()) == (mGrpID + 1)) {
                        List<Variation> variationList = (List<Variation>) mVariantResponse.getVariants().getVariantGroups().get(mGrpID).getVariations();

                        for (Variation variation : variationList) {
                            if (variation.getId().equalsIgnoreCase(excludeItem2.getVariationId())) {
                                variation.setSelectable(false);
                                break;
                            } else {
                                variation.setSelectable(true);
                            }
                        }
                    }
                }
            }
        }
    }*/

    public void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }

    public void requestVariantCategories() {
        IOManager.requestCategories(new Callback<VariantsResponse>() {
            @Override
            public void onResponse(Call<VariantsResponse> call, Response<VariantsResponse> response) {
                mProgressBar.setVisibility(View.GONE);
                if (response != null && response.body() != null) {
                    Log.d(TAG, "" + response.body().getVariants());
                    mVariantResponse = response.body();
                    ArrayList<Variation> variationList;
                    if (mVariantResponse.getVariants().getVariantGroups() != null) {
                        VariantGroup variantGroup = mVariantResponse.getVariants().getVariantGroups().get(0);
                        variationList = variantGroup.getVariations();
                        mVariationList.addAll(variationList);
                        mGrpID = Integer.parseInt(variantGroup.getGroupId());
                    }

                  /*  for (Variation variation : mVariationList) {
                        variation.setSelectable(true);
                    }*/
                    mVariantDisplayAdapter.notifyItemRangeInserted(0, mVariationList.size());
                }
            }

            @Override
            public void onFailure(Call<VariantsResponse> call, Throwable t) {
                mProgressBar.setVisibility(View.GONE);
                DialogUtils.showToast(R.string.response_failed, MenuDisplayerActivity.this);
            }
        });
    }

    @Override
    public void OnItemClicked(String variantID) {
        Intent intent = new Intent(this, MenuDisplayerActivity.class);

        if ((mGrpID + 1) <= (mVariantResponse.getVariants().getVariantGroups().size())) {
            VariantGroup nextVariantGroup = mVariantResponse.getVariants().getVariantGroups().get(mGrpID);
            intent.putExtra(IBundleKeys.VARIANT_RESPONSE, mVariantResponse);
            intent.putExtra(IBundleKeys.GROUP_ID, mGrpID + 1);
            intent.putExtra(IBundleKeys.SELECTED_GROUP_NAME, nextVariantGroup.getName());
            intent.putExtra(IBundleKeys.SELECTED_VARIATION_ID, mSelectedVariationID);
            intent.putExtra(IBundleKeys.VARIANT_LIST, nextVariantGroup.getVariations());
            startActivity(intent);
        }
    }
}
