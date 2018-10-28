package example.lucas.laptoplist.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import example.lucas.laptoplist.DetailActivity;
import example.lucas.laptoplist.R;
import example.lucas.laptoplist.model.entities.LaptopData;
import example.lucas.laptoplist.utils.UIUtils;

public class RecyclerViewLinearAdapter extends RecyclerView.Adapter<RecyclerViewLinearAdapter.ViewHolder> {

    private static final String TAG = RecyclerViewLinearAdapter.class.getSimpleName();

    private ArrayList<LaptopData> mLaptopDataArray;
    private Context mContext;

    public RecyclerViewLinearAdapter(Context context, ArrayList<LaptopData> mData) {
        mLaptopDataArray = mData;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "ViewHolder instance created");
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_linear_layout, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder method called");
        final String mTitleText = mLaptopDataArray.get(position).getTitle();
        final String mDescriptionText = mLaptopDataArray.get(position).getDescription();

        holder.mTitleLaptopView.setText(mTitleText);
        holder.mDescLaptopView.setText(mDescriptionText);
        Picasso.with(mContext)
                .load(mLaptopDataArray.get(position).getImage())
                .transform(UIUtils.getTransformation())
                .placeholder(ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.no_image_placeholder, null))
                .error(ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.no_image_placeholder, null))
                .into(holder.mImageLaptopView);

        holder.mContentContainerView
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bitmap mImageBitmap = ((BitmapDrawable) holder.mImageLaptopView.getDrawable()).getBitmap();
                        byte[] mImageByteArray = UIUtils.convertBitmapToByteArray(mImageBitmap);

                        Intent mDetailActivityIntent = new Intent(mContext, DetailActivity.class);
                        mDetailActivityIntent.putExtra("title", mTitleText);
                        mDetailActivityIntent.putExtra("description", mDescriptionText);
                        mDetailActivityIntent.putExtra("bitmap_image", mImageByteArray);
                        mContext.startActivity(mDetailActivityIntent);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return mLaptopDataArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageLaptopView;
        TextView mTitleLaptopView;
        TextView mDescLaptopView;
        RelativeLayout mContentContainerView;

        ViewHolder(View itemView) {
            super(itemView);
            mImageLaptopView = itemView.findViewById(R.id.card_view_image);
            mTitleLaptopView = itemView.findViewById(R.id.card_view_title);
            mDescLaptopView = itemView.findViewById(R.id.card_view_description);
            mContentContainerView = itemView.findViewById(R.id.listItemContainer);
        }
    }
}
