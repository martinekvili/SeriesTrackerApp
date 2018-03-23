package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.details;

import android.os.Parcel;
import android.os.Parcelable;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesDetails;

public class DetailsPresenterState implements Parcelable {

    private final SavedSeries series;
    private SeriesDetails details;

    public DetailsPresenterState(SavedSeries series) {
        this.series = series;
    }

    public SavedSeries getSeries() {
        return series;
    }

    public SeriesDetails getDetails() {
        return details;
    }

    public void setDetails(SeriesDetails details) {
        this.details = details;
    }

    // region Parcelable implementation

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DetailsPresenterState> CREATOR = new Creator<DetailsPresenterState>() {
        @Override
        public DetailsPresenterState createFromParcel(Parcel in) {
            return new DetailsPresenterState(in);
        }

        @Override
        public DetailsPresenterState[] newArray(int size) {
            return new DetailsPresenterState[size];
        }
    };

    private DetailsPresenterState(Parcel in) {
        series = in.readParcelable(SavedSeries.class.getClassLoader());
        details = in.readParcelable(SeriesDetails.class.getClassLoader());
    }

    // endregion
}
