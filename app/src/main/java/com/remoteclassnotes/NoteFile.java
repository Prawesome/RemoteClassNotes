package com.remoteclassnotes;

/**
 * Created by praji on 2/13/2018.
 */

public class NoteFile {
    private String fileName;
    private String downloadUrl;
    private String description;
    private String subjectName;

    public NoteFile() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
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
