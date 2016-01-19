package com.himeetu.network.http;

import java.io.File;
import java.util.Map;

/**
 * Created by object1984 on 16/1/19.
 */
public interface MultiPartRequest {

    public void addFileUpload(String param,File file);

    public void addStringUpload(String param,String content);

    public Map<String,File> getFileUploads();

    public Map<String,String> getStringUploads();
}