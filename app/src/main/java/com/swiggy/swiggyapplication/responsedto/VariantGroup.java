
package com.swiggy.swiggyapplication.responsedto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VariantGroup implements Parcelable {

    @SerializedName("group_id")
    @Expose
    private String groupId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("variations")
    @Expose
    private ArrayList<Variation> variations = null;

    protected VariantGroup(Parcel in) {
        groupId = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(groupId);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VariantGroup> CREATOR = new Creator<VariantGroup>() {
        @Override
        public VariantGroup createFromParcel(Parcel in) {
            return new VariantGroup(in);
        }

        @Override
        public VariantGroup[] newArray(int size) {
            return new VariantGroup[size];
        }
    };

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Variation> getVariations() {
        return variations;
    }

    public void setVariations(ArrayList<Variation> variations) {
        this.variations = variations;
    }

}
