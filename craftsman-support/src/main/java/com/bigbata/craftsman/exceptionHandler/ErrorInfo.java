package com.bigbata.craftsman.exceptionHandler;

/**
 * Created by lixianghui on 15-2-10.
 */
public class ErrorInfo {
    public ErrorInfo(String status,String code,String message){
        this.status = status;
        this.code = code;
        this.message = message;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDevelopMessage() {
        return developMessage;
    }

    public void setDevelopMessage(String developMessage) {
        this.developMessage = developMessage;
    }

    public String getInfoLink() {
        return infoLink;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    private String status ;
    private String code;
    private String message;
    private String developMessage;
    private String infoLink;
}
