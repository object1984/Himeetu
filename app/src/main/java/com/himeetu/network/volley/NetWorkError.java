package com.himeetu.network.volley;

import com.android.volley.NetworkResponse;

/**
 * Created by object1984 on 15/12/18.
 */
public class NetWorkError  extends Exception  {
    public final NetworkResponse networkResponse;

    public NetWorkError(NetworkResponse networkResponse) {
        this.networkResponse = networkResponse;
    }

    public NetWorkError(String exceptionMessage) {
        super(exceptionMessage);
        networkResponse = null;
    }
}
