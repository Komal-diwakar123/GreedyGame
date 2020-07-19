package com.example.greedygame.imageloader;

import android.content.Context;
import android.util.AttributeSet;

public class ReddItImageNetworkImageView extends VolleyNetworkImageView{

    /**
     * Constructors.
     */
    public ReddItImageNetworkImageView(Context context) {
        super(context);
    }

    public ReddItImageNetworkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReddItImageNetworkImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Sets URL of the image that should be loaded into this view.
     *
     * @param url The URL that should be loaded into this ImageView.
     */
    public void setImageUrl(final String url) {
        super.setImageUrl(url);
    }

    /**
     * Sets the default image resource ID to be used for this view until the attempt to load it
     * completes.
     */
    public void setDefaultImageResId(final int defaultImage) {
        super.setDefaultImageResId(defaultImage);
    }

    /**
     * Sets the error image resource ID to be used for this view in the event that the image
     * requested fails to load.
     */
    public void setErrorImageResId(final int errorImage) {
        super.setErrorImageResId(errorImage);
    }

    /**
     * Set listener which is used to get callback whether image was successfully loaded or not.
     */
    public void setNetworkImageViewListenerListener(final NetworkImageViewListener loadImageListener) {
        super.setNetworkImageViewListenerListener(loadImageListener);
    }

    /**
     * Used to load image from network.
     *
     * @param url               The URL that should be loaded into this ImageView.
     * @param defaultImage      The default image resource ID to be used for this view until the attempt to load it completes.
     * @param errorImage        The error image resource ID to be used for this view in the event that the image requested fails to load.
     * @param loadImageListener The listener which is used to get callback whether image was successfully loaded or not.
     */
    public void loadImage(final String url, final int defaultImage, final int errorImage, final NetworkImageViewListener loadImageListener) {
        setDefaultImageResId(defaultImage);
        setErrorImageResId(errorImage);
        setNetworkImageViewListenerListener(loadImageListener);
        setImageUrl(url);
    }

    /**
     * Used to load image from network.
     *
     * @param url               The URL that should be loaded into this ImageView.
     * @param loadImageListener The listener which is used to get callback whether image was successfully loaded or not.
     */
    public void loadImage(final String url, final NetworkImageViewListener loadImageListener) {
        setNetworkImageViewListenerListener(loadImageListener);
        setImageUrl(url);
    }

}
