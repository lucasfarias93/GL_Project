package example.lucas.laptoplist.client;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import example.lucas.laptoplist.helper.RetrofitHelper;
import example.lucas.laptoplist.model.entities.LaptopData;
import example.lucas.laptoplist.provider.RestDataProvider;
import example.lucas.laptoplist.utils.AppConstants;
import retrofit2.Call;
import retrofit2.Response;

public class LaptopDataDownloaderAsynctask extends AsyncTask<Void, Void, ArrayList<LaptopData>> {

    private static final String TAG = LaptopDataDownloaderAsynctask.class.getSimpleName();

    private LaptopDataCallback mCallback;
    private Context mContext;

    public interface LaptopDataCallback {
        void getLaptopsData(ArrayList<LaptopData> mLaptopData);
    }

    public LaptopDataDownloaderAsynctask(Context context, LaptopDataCallback callback) {
        this.mCallback = callback;
        this.mContext = context;
    }

    @Override
    protected ArrayList<LaptopData> doInBackground(Void... voids) {
        ArrayList<LaptopData> mLaptopArrayData = new ArrayList<>();

        RetrofitHelper mHelper = new RetrofitHelper(AppConstants.API_URL);
        RestDataProvider mProvider = mHelper.createProvider(RestDataProvider.class);

        try {
            Call<ArrayList<LaptopData>> mRequest = mProvider.getLaptopsData();
            Response<ArrayList<LaptopData>> mResponse = mRequest.execute();
            mLaptopArrayData = mResponse.body();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

        return mLaptopArrayData;
    }

    @Override
    protected void onPostExecute(ArrayList<LaptopData> laptopData) {
        super.onPostExecute(laptopData);
        mCallback.getLaptopsData(laptopData);
    }
}
