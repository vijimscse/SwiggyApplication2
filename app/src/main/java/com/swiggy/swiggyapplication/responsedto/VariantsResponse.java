
package com.swiggy.swiggyapplication.responsedto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VariantsResponse implements Parcelable {

    @SerializedName("variants")
    @Expose
    private Variants variants;

    protected VariantsResponse(Parcel in) {
        variants = in.readParcelable(Variants.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(variants, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<VariantsResponse> CREATOR = new Creator<VariantsResponse>() {
        @Override
        public VariantsResponse createFromParcel(Parcel in) {
            return new VariantsResponse(in);
        }

        @Override
        public VariantsResponse[] newArray(int size) {
            return new VariantsResponse[size];
        }
    };

    public Variants getVariants() {
        return variants;
    }

    public void setVariants(Variants variants) {
        this.variants = variants;
    }

}
