
package com.swiggy.swiggyapplication.responsedto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variants implements Parcelable {

    @SerializedName("variant_groups")
    @Expose
    private List<VariantGroup> variantGroups = null;
    @SerializedName("exclude_list")
    @Expose
    private List<List<ExcludeItem>> excludeList = null;

    protected Variants(Parcel in) {
        variantGroups = in.createTypedArrayList(VariantGroup.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(variantGroups);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Variants> CREATOR = new Creator<Variants>() {
        @Override
        public Variants createFromParcel(Parcel in) {
            return new Variants(in);
        }

        @Override
        public Variants[] newArray(int size) {
            return new Variants[size];
        }
    };

    public List<VariantGroup> getVariantGroups() {
        return variantGroups;
    }

    public void setVariantGroups(List<VariantGroup> variantGroups) {
        this.variantGroups = variantGroups;
    }

    public List<List<ExcludeItem>> getExcludeList() {
        return excludeList;
    }

    public void setExcludeList(List<List<ExcludeItem>> excludeList) {
        this.excludeList = excludeList;
    }

}
