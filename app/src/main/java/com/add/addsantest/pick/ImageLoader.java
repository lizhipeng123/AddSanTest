package com.add.addsantest.pick;

import android.content.Context;
import android.widget.ImageView;

import java.io.Serializable;

/**为自定义控件加载图片，项目需要实现该接口
 * Created by wangtaian
 * on 2018/5/25
 */
public interface ImageLoader extends Serializable {
    void displayImage(Context activity, Object path, ImageView imageView, int width, int height);

    void displayImage(Context context, Object url, ImageView imageView);

    void clearMemoryCache();
}
