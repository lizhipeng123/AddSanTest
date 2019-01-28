package com.add.addsantest.pick;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.add.addsantest.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 查看大图适配器
 */
public class MyDetailImageAdapter<T extends ImageResource> extends PagerAdapter {
    public static final String TAG = MyDetailImageAdapter.class.getSimpleName();
    private int positions;
    private static ImageLoader imageLoader;
    private BannerViewPager.OnViewPagerClickListener<T> mOnViewPagerClickListner;
    private PicLookActivity itemPiclistViewFm;

    public void setmOnViewPagerClickListner(BannerViewPager.OnViewPagerClickListener<T> mOnViewPagerClickListner) {
        this.mOnViewPagerClickListner = mOnViewPagerClickListner;
    }

    public MyDetailImageAdapter(int position, PicLookActivity itemPiclistViewFm) {
        this.positions = position;
        this.itemPiclistViewFm = itemPiclistViewFm;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        ImageView photoView = new ImageView(itemPiclistViewFm);
        if (positions==0){
            Glide.with(itemPiclistViewFm).load(R.mipmap.bg_defalut).into(photoView);
        }else if (positions==1){
            Glide.with(itemPiclistViewFm).load(R.mipmap.bg_select).into(photoView);
        }else if (positions==2){
            Glide.with(itemPiclistViewFm).load(R.mipmap.bg_error).into(photoView);
        }else if (positions==3){
            Glide.with(itemPiclistViewFm).load(R.mipmap.bg_correct).into(photoView);
        }

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemPiclistViewFm.finish();
                Log.e("photoview", "photo" + 3);
            }
        });
        container.addView(photoView);
        return photoView;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    public interface OnViewPagerClickListener<T> {
        void onClick(View view, T item);
    }

    public static void setImageLoader(ImageLoader imageLoader) {
        MyDetailImageAdapter.imageLoader = imageLoader;
    }
}