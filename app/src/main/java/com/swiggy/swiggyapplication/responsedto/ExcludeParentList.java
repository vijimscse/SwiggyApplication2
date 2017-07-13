package com.swiggy.swiggyapplication.responsedto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Accolite on 7/12/2017.
 */

public class ExcludeParentList implements Parcelable  {

    public List<ExcludeItem> excludeListItem;


    protected ExcludeParentList(Parcel in) {
        excludeListItem = in.createTypedArrayList(ExcludeItem.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(excludeListItem);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ExcludeParentList> CREATOR = new Creator<ExcludeParentList>() {
        @Override
        public ExcludeParentList createFromParcel(Parcel in) {
            return new ExcludeParentList(in);
        }

        @Override
        public ExcludeParentList[] newArray(int size) {
            return new ExcludeParentList[size];
        }
    };

    public List<ExcludeItem> getExcludeListItem() {
        return excludeListItem;
    }
}
