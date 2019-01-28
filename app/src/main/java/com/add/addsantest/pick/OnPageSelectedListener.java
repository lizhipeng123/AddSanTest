package com.add.addsantest.pick;

/**
 * 轮播指示器的接口，可自定义指示器的View并实现该接口
 * 参考{@link }
 * {@link BannerViewPager#mPageChangeListener}
 * Created by lzp
 * on 2018/6/7
 */
public interface OnPageSelectedListener {
    void onPageSelected(int position);

    OnPageSelectedListener setTotalCount(int totalCount);
}
