package hu.bme.aut.mobsoftlab.seriestrackerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SeriesDetails implements Parcelable {

    public SeriesDetails() {
    }

    // region Parcelable implementation

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static final Parcelable.Creator<SeriesDetails> CREATOR = new Parcelable.Creator<SeriesDetails>() {
        public SeriesDetails createFromParcel(Parcel in) {
            return new SeriesDetails(in);
        }

        public SeriesDetails[] newArray(int size) {
            return new SeriesDetails[size];
        }
    };

    private SeriesDetails(Parcel in) {
    }

    // endregion
}
