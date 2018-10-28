package example.lucas.laptoplist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import example.lucas.laptoplist.utils.UIUtils;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.getSimpleName();

    TextView mTitleTextView;
    TextView mDescriptionTextVIew;
    ImageView mLaptopImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Activity initialized");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mTitleTextView = findViewById(R.id.laptopTitleView);
        mDescriptionTextVIew = findViewById(R.id.laptopDescView);
        mLaptopImageView = findViewById(R.id.laptopImageView);

        Intent mNavActivityIntent = getIntent();

        mTitleTextView.setText(mNavActivityIntent.getStringExtra("title"));
        mDescriptionTextVIew.setText(mNavActivityIntent.getStringExtra("description"));
        mLaptopImageView.setImageBitmap(UIUtils.convertByteArrayToBitmap(getIntent().getByteArrayExtra("bitmap_image")));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Activity UI ready for being used");
    }
}
