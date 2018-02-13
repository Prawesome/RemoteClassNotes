package com.remoteclassnotes;

/**
 * Created by praji on 2/13/2018.
 */

public class NoteFile {
    public String fileName;
    public String downloadUrl;

    public NoteFile() {

    }

    public NoteFile(String fileName, String downloadUrl) {
        this.fileName = fileName;
        this.downloadUrl = downloadUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
