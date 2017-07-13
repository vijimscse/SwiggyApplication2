
package com.swiggy.swiggyapplication.responsedto;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Variation implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private double price;
    @SerializedName("default")
    @Expose
    private Integer _default;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("inStock")
    @Expose
    private Integer inStock;
    @SerializedName("isVeg")
    @Expose
    private Integer isVeg;

    private boolean isSelectable = true;


    protected Variation(Parcel in) {
        name = in.readString();
        id = in.readString();
        price = in.readDouble();
        inStock = in.readInt();
        isSelectable = in.readInt() == 0 ? false : true;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeDouble(price);
        dest.writeInt(inStock);
        dest.writeInt(isSelectable ? 1 : 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Variation> CREATOR = new Creator<Variation>() {
        @Override
        public Variation createFromParcel(Parcel in) {
            return new Variation(in);
        }

        @Override
        public Variation[] newArray(int size) {
            return new Variation[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getDefault() {
        return _default;
    }

    public void setDefault(Integer _default) {
        this._default = _default;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public Integer getIsVeg() {
        return isVeg;
    }

    public void setIsVeg(Integer isVeg) {
        this.isVeg = isVeg;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setSelectable(boolean selectable) {
        isSelectable = selectable;
    }
}
