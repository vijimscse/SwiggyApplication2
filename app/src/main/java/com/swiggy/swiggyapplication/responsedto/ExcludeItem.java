
package com.swiggy.swiggyapplication.responsedto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExcludeItem implements Parcelable {

    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("variation_id")
    @Expose
    private String variationId;

    protected ExcludeItem(Parcel in) {
        groupId = in.readString();
        variationId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(groupId);
        dest.writeString(variationId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ExcludeItem> CREATOR = new Creator<ExcludeItem>() {
        @Override
        public ExcludeItem createFromParcel(Parcel in) {
            return new ExcludeItem(in);
        }

        @Override
        public ExcludeItem[] newArray(int size) {
            return new ExcludeItem[size];
        }
    };

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getVariationId() {
        return variationId;
    }

    public void setVariationId(String variationId) {
        this.variationId = variationId;
    }

}
