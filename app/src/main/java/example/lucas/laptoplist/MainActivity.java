package example.lucas.laptoplist;

import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import example.lucas.laptoplist.adapters.RecyclerViewLinearAdapter;
import example.lucas.laptoplist.client.LaptopDataDownloaderAsynctask;
import example.lucas.laptoplist.model.entities.LaptopData;

public class MainActivity extends AppCompatActivity implements LaptopDataDownloaderAsynctask.LaptopDataCallback {
    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerLinearAdapter;
    private RecyclerView.LayoutManager mRecyclerLayoutManager;
    ProgressBar mProgressBar;
    private TextView mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Activity initialized");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.progress_bar);
        mRecyclerView = findViewById(R.id.recycler_view_container);
        mLoading = findViewById(R.id.loading_text);
        mRecyclerView.setVisibility(View.GONE);
        mLoading.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);

        LaptopDataDownloaderAsynctask mAsyncTask = new LaptopDataDownloaderAsynctask(this, this);
        mAsyncTask.execute();
    }

    @Override
    public void getLaptopsData(ArrayList<LaptopData> mLaptopData) {
        mLoading.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);

        mRecyclerLinearAdapter = new RecyclerViewLinearAdapter(this, mLaptopData);
        mRecyclerLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mRecyclerLayoutManager);
        mRecyclerView.setAdapter(mRecyclerLinearAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Activity UI ready for being used");
    }
}
