package hu.bme.aut.mobsoftlab.seriestrackerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class EpisodeDetails implements Parcelable {

    private final String plot;
    private final String imdbRating;
    private boolean isLastEpisode;

    public EpisodeDetails(String plot, String imdbRating) {
        this.plot = plot;
        this.imdbRating = imdbRating;
    }

    public String getPlot() {
        return plot;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public boolean isLastEpisode() {
        return isLastEpisode;
    }

    public void setLastEpisode(boolean lastEpisode) {
        isLastEpisode = lastEpisode;
    }

    // region Parcelable implementation

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(plot);
        dest.writeString(imdbRating);
        dest.writeInt(isLastEpisode ? 1 : 0);
    }

    public static final Parcelable.Creator<EpisodeDetails> CREATOR = new Parcelable.Creator<EpisodeDetails>() {
        public EpisodeDetails createFromParcel(Parcel in) {
            return new EpisodeDetails(in);
        }

        public EpisodeDetails[] newArray(int size) {
            return new EpisodeDetails[size];
        }
    };

    private EpisodeDetails(Parcel in) {
        plot = in.readString();
        imdbRating = in.readString();
        isLastEpisode = in.readInt() != 0;
    }

    // endregion
}
