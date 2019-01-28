package com.add.addsantest.pick;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.add.addsantest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzp on 2018/4/28.
 */
public class BannerViewPager<T extends ImageResource> extends ViewPager {
    private boolean isLoop = false;
    private int interval = 3000;
    private int index = 0;
    private List<T> data;
    private static ImageLoader imageLoader;
    private List<OnPageSelectedListener> pageSelectedListener;
    private OnViewPagerClickListener<T> mOnViewPagerClickListner;

    public BannerViewPager(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @Override
    protected void onDetachedFromWindow() {
        stop();
        super.onDetachedFromWindow();
    }

    private void init(Context context, AttributeSet attrs) {
        if (context == null) return;
        pageSelectedListener = new ArrayList<>();
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BannerViewPager);
            if (ta != null) {
                isLoop = ta.getBoolean(R.styleable.BannerViewPager_loop, false);
                interval = ta.getInteger(R.styleable.BannerViewPager_interval, 3000);
                ta.recycle();
            }
        }
        addOnPageChangeListener(mPageChangeListener);
        setAdapter(mAdapter);

    }

    public BannerViewPager addOnPageSelectedListener(OnPageSelectedListener onPageSelectedListener) {
        pageSelectedListener.add(onPageSelectedListener);
        return this;
    }

    public BannerViewPager<T> setOnViewPagerClickListner(OnViewPagerClickListener<T> onViewPagerClickListner) {
        mOnViewPagerClickListner = onViewPagerClickListner;
        return this;
    }

    public BannerViewPager setData(List<T> data, int i) {
        if (data != null) {
            this.data = data;
            this.index = i;
            mAdapter.notifyDataSetChanged();
            if (index >= 0 && index < data.size()) setCurrentItem(index);
            if (pageSelectedListener != null) {
                for (OnPageSelectedListener listener : pageSelectedListener) {
                    listener.setTotalCount(data.size());
                    listener.onPageSelected(i);
                }
            }
            if (isLoop) start();
        }
        return this;
    }

    /**
     * 开始
     */
    public void start() {
        stop();
        postDelayed(player, interval);
    }

    /**
     * 停止
     */
    public void stop() {
        removeCallbacks(player);
    }

    /**
     * 暂停轮播
     */
    public void pause() {
        stop();
    }

    /**
     * 播放器
     */
    private Runnable player = new Runnable() {
        @Override
        public void run() {
            play();
        }
    };

    /**
     * 执行播放
     */
    private synchronized void play() {
        int count = mAdapter.getCount();
        if (count > 0) {
            index++;
            if (index >= count) {
                index = 0;
            }
            setCurrentItem(index);
        }
        start();
    }

    protected PagerAdapter mAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView imageView = new ImageView(container.getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (imageLoader != null)
                imageLoader.displayImage(container.getContext(), data.get(position).thumbnailUrl(), imageView);
//            imageView.setOnClickListener(new ItemClick<>(data.get(position)));
            imageView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnViewPagerClickListner != null)
                        mOnViewPagerClickListner.onClick(v, data.get(position));
                }
            });
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };
    private SimpleOnPageChangeListener mPageChangeListener = new SimpleOnPageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            if (pageSelectedListener != null) {
                for (OnPageSelectedListener listener : pageSelectedListener) {
                    listener.onPageSelected(data == null || data.isEmpty() ? 0 : position % data.size());
                }
            }
        }
    };

    public interface OnViewPagerClickListener<T> {
        void onClick(View view, T item);
    }

    public static void setImageLoader(ImageLoader imageLoader) {
        BannerViewPager.imageLoader = imageLoader;
    }
}