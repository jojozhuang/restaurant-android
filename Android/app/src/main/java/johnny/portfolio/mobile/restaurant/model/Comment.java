package johnny.portfolio.mobile.restaurant.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Johnny on 10/28/2015.
 */
public class Comment implements Parcelable {
    int id;
    String content;
    int restid;
    String restname;
    int userid;
    String username;

    public Comment(int id, String content, int restid, String restname, int userid, String username) {
        this.id = id;
        this.content = content;
        this.restid = restid;
        this.restname = restname;
        this.userid = userid;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRestId() {
        return restid;
    }

    public void setRestId(int restid) {
        this.restid = restid;
    }

    public String getRestName() {
        return restname;
    }

    public void setRestName(String restname) {
        this.restname = restname;
    }

    public int getUserId() {
        return userid;
    }

    public void setUserId(int userid) {
        this.userid = userid;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(int userid) {
        this.username = username;
    }

    public String toString() {
        return content;
    }

    // implement Parcelable
    private Comment(Parcel in) {
        id = in.readInt();
        content = in.readString();
        restid = in.readInt();
        restname = in.readString();
        userid = in.readInt();
        username = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(content);
        out.writeInt(restid);
        out.writeString(restname);
        out.writeInt(userid);
        out.writeString(username);
    }

    public static final Creator<Comment> CREATOR
            = new Creator<Comment>() {
        public Comment createFromParcel(Parcel in) {
            return new Comment(in);
        }

        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}