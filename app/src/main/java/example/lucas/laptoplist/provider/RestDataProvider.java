package example.lucas.laptoplist.provider;

import java.util.ArrayList;

import example.lucas.laptoplist.model.entities.LaptopData;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestDataProvider {

    String TAG = RestDataProvider.class.getSimpleName();

    @GET("list")
    Call<ArrayList<LaptopData>> getLaptopsData();
}
