package example.lucas.laptoplist.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Transformation;

import java.io.ByteArrayOutputStream;

/**
 * UIUtil class used to place util and app-access useful methods.
 */
public class UIUtils {

    private static final String TAG = UIUtils.class.getSimpleName();

    // Applying a transformation for high density images in order to keep performance and avoid memory leaks.
    public static Transformation getTransformation() {
        return new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                Log.d(TAG, "BitmapTransform called");
                Bitmap mResult = Bitmap.createScaledBitmap(source, 500, 400, true);
                if (mResult != source) {
                    source.recycle();
                }
                return mResult;
            }

            @Override
            public String key() {
                return "Transformation re-scaling";
            }
        };
    }

    public static byte[] convertBitmapToByteArray(Bitmap mBitmap) {
        // Convert Bitmap to byte array
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap convertByteArrayToBitmap(byte[] mByteArrayImage) {
        // Convert ByteArray into Bitmap
        return  BitmapFactory.decodeByteArray(mByteArrayImage, 0, mByteArrayImage.length);
    }
}
