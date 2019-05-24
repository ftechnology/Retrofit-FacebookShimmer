package com.faruk.retrofitshimmer.model;

import android.content.Context;

import com.faruk.retrofitshimmer.appfrm.Constants;
import com.faruk.retrofitshimmer.appfrm.LogUtil;
import com.faruk.retrofitshimmer.appfrm.ResponseObject;
import com.faruk.retrofitshimmer.data.PetsData;
import com.faruk.retrofitshimmer.data.PetsDataList;
import com.faruk.retrofitshimmer.webservice.GetWeatherDataService;
import com.faruk.retrofitshimmer.webservice.RetrofitClientInstance;
import com.faruk.retrofitshimmer.model.BaseModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetsDataModel extends BaseModel {

    public static PetsDataModel mPetsDataModel;

    private ArrayList<PetsData> mPetList = new ArrayList<>();

    private String mContentUrl = "";
    private String mContentTitle = "";

    private PetsDataModel(Context context) {
        super(context);
    }

    public static PetsDataModel getInstance(Context context) {
        if (mPetsDataModel == null)
            mPetsDataModel = new PetsDataModel(context);
        return mPetsDataModel;
    }

    @Override
    public ResponseObject execute() {
        this.clear(true);

        GetWeatherDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetWeatherDataService.class);

        /*Call the method with parameter in the interface to get the data*/
        Call<PetsDataList> call = service.getAllData();

        /*Log the URL called*/
        String REQ_URL = call.request().url().toString();
        LogUtil.d("URL Called", REQ_URL + "");
        if (mNotifyObserver != null)
            mNotifyObserver.update(new ResponseObject(Constants.CODE_LOAD_START, "", null));
        call.enqueue(new Callback<PetsDataList>() {
            @Override
            public void onResponse(Call<PetsDataList> call, Response<PetsDataList> response) {
                if (mNotifyObserver != null) {
                    if (response.isSuccessful()) {
                        setPetsArrayList(response.body().getPetsArrayList());
                        mNotifyObserver.update(new ResponseObject(Constants.CODE_SUCCESS, "", null));
                    } else {
                        mNotifyObserver.update(new ResponseObject(Constants.CODE_UNSUCCESSFULL, "", null));
                    }
                }
            }

            @Override
            public void onFailure(Call<PetsDataList> call, Throwable t) {
                if (mNotifyObserver != null)
                    mNotifyObserver.update(new ResponseObject(Constants.CODE_FAIL, "", null));
            }
        });
        return null;
    }

    public ArrayList<PetsData> getPetsArrayList() {
        return mPetList;
    }

    public void setPetsArrayList(ArrayList<PetsData> dataList) {
        this.mPetList = dataList;
    }

    public void setContentUrl(String url) {
        this.mContentUrl = url;
    }

    public String getContentUrl() {
        return this.mContentUrl;
    }

    public void setContentTitle(String title) {
        this.mContentTitle = title;
    }

    public String getContentTitle() {
        return this.mContentTitle;
    }


    @Override
    public int getCount() {
        return this.mPetList.size();
    }


    @Override
    public Object getItem(int position) {
        return "";
    }

}
