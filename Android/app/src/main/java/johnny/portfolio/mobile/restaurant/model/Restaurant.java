package johnny.portfolio.mobile.restaurant.model;

import johnny.portfolio.mobile.restaurant.R;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Johnny on 10/28/2015.
 */
public class Restaurant implements Parcelable {
    public enum Category {Restaurant, Dessert, CoffeeTea, Bakeries, IceCream}

    int id;
    String name;
    Category category;
    String location;
    String image;
    float rating = 4.0f;
    int reviews = 0;
    String image1;
    String image2;
    String image3;

    public Restaurant(int id, String name, Category category, String location, float rating, int reviews, String image, String image1, String image2, String image3) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.location = location;
        this.rating = rating;
        this.reviews = reviews;
        this.image = image;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getType() {
        return category;
    }

    public void setType(Category category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public String getReviewText() {
        return reviews + " Reviews";
    }

    public String getLongReviewText() {
        return "Total " + reviews + " reviews from customers.";
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image1 = image3;
    }


    public String toString() {
        return name;
    }


    public static int getIconResource(Category category) {
        switch (category) {
            case Restaurant:
                return R.drawable.restaurant;
            case Dessert:
                return R.drawable.dessert;
            case CoffeeTea:
                return R.drawable.coffeetea;
            case Bakeries:
                return R.drawable.bakeries;
            case IceCream:
                return R.drawable.icecream;
        }
        return -1;
    }
    public static Category getCategoryByNumber(int category) {
        switch (category) {
            case 0:
                return Category.Restaurant;
            case 1:
                return Category.Dessert;
            case 2:
                return Category.CoffeeTea;
            case 3:
                return Category.Bakeries;
            case 4:
                return Category.IceCream;
        }
        return Category.Restaurant;
    }

    // implement Parcelable
    private Restaurant(Parcel in) {
        id = in.readInt();
        name = in.readString();
        category = Category.values()[in.readInt()];
        location = in.readString();
        rating = in.readFloat();
        reviews = in.readInt();
        image = in.readString();
        image1 = in.readString();
        image2 = in.readString();
        image3 = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(id);
        out.writeString(name);
        out.writeInt(category.ordinal());
        out.writeString(location);
        out.writeFloat(rating);
        out.writeInt(reviews);
        out.writeString(image);
        out.writeString(image1);
        out.writeString(image2);
        out.writeString(image3);
    }

    public static final Parcelable.Creator<Restaurant> CREATOR
            = new Parcelable.Creator<Restaurant>() {
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };
}