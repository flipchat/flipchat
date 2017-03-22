package com.flipchat;

import java.sql.Date;

/**
 * Data class representing a comment
 *
 * @author Alex Ilea, Jonathan Lucuix-Andre, Kevin Tran, Zain, Virani
 * @version 1.0
 */
public class Comment {
    private long cid;
    private String content;
    private Date datetime;
    private long userID;
    private long pid;

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }
}
