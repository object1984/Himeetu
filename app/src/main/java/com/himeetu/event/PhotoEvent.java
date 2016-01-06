package com.himeetu.event;

import android.net.Uri;

/**
 * Created by object1984 on 16/1/5.
 */
public class PhotoEvent {
    public Uri fileUri;
    public PhotoEvent(Uri fileUri){
        this.fileUri = fileUri;
    }
}
