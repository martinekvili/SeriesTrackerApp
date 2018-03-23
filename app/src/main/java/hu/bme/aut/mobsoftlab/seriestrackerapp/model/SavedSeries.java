package hu.bme.aut.mobsoftlab.seriestrackerapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SavedSeries implements Parcelable {

    private final String imdbID;
    private final String title;
    private final String posterUrl;

    private int season;
    private int episode;

    public SavedSeries(String imdbID, String title, String posterUrl) {
        this.imdbID = imdbID;
        this.title = title;
        this.posterUrl = posterUrl;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public SavedSeries copy() {
        SavedSeries copy = new SavedSeries(imdbID, title, posterUrl);
        copy.season = season;
        copy.episode = episode;

        return copy;
    }

    // region Parcelable implementation

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imdbID);
        dest.writeString(title);
        dest.writeString(posterUrl);

        dest.writeInt(season);
        dest.writeInt(episode);
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
        imdbID = in.readString();
        title = in.readString();
        posterUrl = in.readString();

        season = in.readInt();
        episode = in.readInt();
    }

    // endregion
}
