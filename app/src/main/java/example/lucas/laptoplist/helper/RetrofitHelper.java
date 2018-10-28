package example.lucas.laptoplist.helper;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Retrofit builder class
 */
public class RetrofitHelper {

    private static final String TAG = RetrofitHelper.class.getSimpleName();

    private Retrofit mRetrofit;

    public RetrofitHelper(String mBaseUrl) {
        Log.d(TAG, "RetrofitHelper instance created");

        Gson mGson = new GsonBuilder().create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();
    }
    // Method used for generic provider's creation in case of having more than one provider
    // contained in the app

    /**
     * @param mServiceClass RestProvider class used for the creation of this particular provider class implementation
     * @param <S> Generic type
     * @return Implementation of a Retrofit's GitHubService implementation.
     * @see <a href="https://square.github.io/retrofit/">https://square.github.io/retrofit/</a>
     */
    public <S> S createProvider(Class<S> mServiceClass) {
        return mRetrofit.create(mServiceClass);
    }
}
