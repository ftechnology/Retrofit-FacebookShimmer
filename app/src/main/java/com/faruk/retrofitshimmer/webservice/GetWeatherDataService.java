/**
 * @author Md Faruk Hossain
 * FIXME
 */

package com.faruk.retrofitshimmer.webservice;

import com.faruk.retrofitshimmer.data.PetsDataList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetWeatherDataService {

    String REQUEST_PARAM = "pets.json";

    @GET(REQUEST_PARAM)
    Call<PetsDataList> getAllData();
}
