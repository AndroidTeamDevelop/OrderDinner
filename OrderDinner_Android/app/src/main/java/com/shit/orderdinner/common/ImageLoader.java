package com.shit.orderdinner.common;

import android.graphics.Bitmap;
import android.util.LruCache;

public class ImageLoader {

	private static LruCache<String, Bitmap> mMemoryCache;

	private static ImageLoader mImageLoader;
	
	private ImageLoader() {
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int cacheSize = maxMemory / 8;
		mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getByteCount();
			}
		};
	}
	

	public static ImageLoader getInstance() {
		if (mImageLoader == null) {
			mImageLoader = new ImageLoader();
		}
		return mImageLoader;
	}

	public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (getBitmapFromMemoryCache(key) == null) {
			mMemoryCache.put(key, bitmap);
		}
	}

	public Bitmap getBitmapFromMemoryCache(String key) {
		return mMemoryCache.get(key);
	}
	
	public void removeBitmapFromMemoryCache(String key) {
		mMemoryCache.remove(key);
	}
}