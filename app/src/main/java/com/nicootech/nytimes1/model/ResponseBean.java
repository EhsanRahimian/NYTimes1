package com.nicootech.nytimes1.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ResponseBean implements Parcelable {
    private List<Docs> docs;

    public ResponseBean(List<Docs> docs) {
        this.docs = docs;
    }
    public ResponseBean() {
    }

    public List<Docs> getDocs() {
        return docs;
    }
    public void setDocs(List<Docs> docs) {
        this.docs = docs;
    }


    protected ResponseBean(Parcel in) {
        docs = in.createTypedArrayList(Docs.CREATOR);
    }

    public static final Creator<ResponseBean> CREATOR = new Creator<ResponseBean>() {
        @Override
        public ResponseBean createFromParcel(Parcel in) {
            return new ResponseBean(in);
        }

        @Override
        public ResponseBean[] newArray(int size) {
            return new ResponseBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(docs);
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "docs=" + docs +
                '}';
    }
}
