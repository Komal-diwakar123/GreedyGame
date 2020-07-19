package com.example.imageloaderlib.imageloader;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.collection.LruCache;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;

import java.net.CookieHandler;
import java.net.CookieManager;

public class VolleySingleton {
    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    // Default maximum disk usage in bytes
    private static final int DEFAULT_DISK_USAGE_BYTES = 25 * 1024 * 1024;

    /**
     * Constructor.
     *
     * @param appContext Application Context.
     */
    private VolleySingleton(final Context appContext) {

        Cache cache = new DiskBasedCache(appContext.getCacheDir(), DEFAULT_DISK_USAGE_BYTES);
        Network network = new BasicNetwork(new HurlStack());
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });

    }

    /**
     * Used to singleton instance of VolleySingleton.
     *
     * @param context Context.
     * @return Singleton instance of VolleySingleton.
     */
    static synchronized VolleySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    /**
     * Used to get volley request queue.
     *
     * @return Volley request queue.
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    RequestQueue getRequestQueue() {
        /**
         * 1. Initialize CookieManager for each request - such that we don't accept any cookie from server.
         * 2. For REST API we don't need any cookie's.
         */
        CookieHandler.setDefault(new CookieManager());
        /**
         * Vignesh Ramachandra - Is it really necessary for not accepting cookies?. As we use exclusive mobile
         * controllers from the api, can't we ensure that we aren't sending any cookies??
         * This will affect the agent collision functionality. AWS cookie is set during each xhr poll during socket
         * connections. If this cookie policy is sent the subsequent polls are rejected by the transport layer and hence
         * the auth fails on the AWS load balancer. @vasanth Hence please review this and we can explore if there are
         * any other cookie policy which will work
         */
        // ((CookieManager) CookieHandler.getDefault()).setCookiePolicy(CookiePolicy.ACCEPT_NONE);

        return mRequestQueue;
    }

    /**
     * Used to add the request to the request queue.
     *
     * @param request Request to be added to the queue.
     */
    <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

    /**
     * Used to get Image Loader.
     *
     * @return Image Loader.
     */
    ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
