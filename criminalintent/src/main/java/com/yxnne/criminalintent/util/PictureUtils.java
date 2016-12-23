package com.yxnne.criminalintent.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * Created by yxnne on 2016/12/23.
 *
 */

public class PictureUtils {

    /**
     * get a Scaled Bitmap from path
     * @param path 路径
     * @param destWidth 计划宽
     * @param destHeight 计划高
     * @return 位图
     */
    public static Bitmap getScaledBitmap(String path,int destWidth,int destHeight){
        //首先读取path的大小
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);

        float srcWidth = options.outWidth;
        float srcHeigth = options.outHeight;

        //算下要缩放的倍数
        int inSampleSize = 1;
        if(srcHeigth > destHeight || srcWidth > destWidth){
            if(srcWidth > srcHeigth){
                inSampleSize = Math.round(srcHeigth / destHeight);
            }else{
                inSampleSize = Math.round(srcWidth / destWidth);
            }
        }

        options = new BitmapFactory.Options();
        options.inSampleSize = inSampleSize;

        //创建新的Bitmap
        return BitmapFactory.decodeFile(path,options);

    }

    /**
     * 重载的getScaledBitmap()方法，用于估算缩放大小
     * 直接先弄一个和屏幕一样的大小
     * @param path 路径
     * @param activity context to get window manager
     * @return bitmap
     */
    public static Bitmap getScaledBitmap(String path, Activity activity){
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return getScaledBitmap(path,size.x,size.y);
    }

}
