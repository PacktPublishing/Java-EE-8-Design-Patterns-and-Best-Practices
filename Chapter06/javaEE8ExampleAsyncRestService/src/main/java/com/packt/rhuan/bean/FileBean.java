package com.packt.rhuan.bean;

import java.io.File;

public class FileBean {

    private File file;

    private String mimeType;

    public FileBean(){}

    public FileBean(File file, String mimeType){

        this.file = file;
        this.mimeType = mimeType;

    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
