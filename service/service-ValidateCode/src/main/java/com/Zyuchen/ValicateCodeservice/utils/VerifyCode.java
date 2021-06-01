package com.Zyuchen.ValicateCodeservice.utils;

public class VerifyCode {
    private String UUID;
    private String code;
    private byte[] imgBytes;
    private long expireTime;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public byte[] getImgBytes() {
        return imgBytes;
    }
    public void setImgBytes(byte[] imgBytes) {
        this.imgBytes = imgBytes;
    }
    public long getExpireTime() {
        return expireTime;
    }
    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
}
