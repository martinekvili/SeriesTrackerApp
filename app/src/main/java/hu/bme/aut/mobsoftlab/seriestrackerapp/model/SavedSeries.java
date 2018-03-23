package hu.bme.aut.mobsoftlab.seriestrackerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SavedSeries implements Parcelable {

    public SavedSeries() {
    }

    // region Parcelable implementation

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static final Parcelable.Creator<SavedSeries> CREATOR = new Parcelable.Creator<SavedSeries>() {
        public SavedSeries createFromParcel(Parcel in) {
            return new SavedSeries(in);
        }

        public SavedSeries[] newArray(int size) {
            return new SavedSeries[size];
        }
    };

    private SavedSeries(Parcel in) {
    }

    // endregion
}
