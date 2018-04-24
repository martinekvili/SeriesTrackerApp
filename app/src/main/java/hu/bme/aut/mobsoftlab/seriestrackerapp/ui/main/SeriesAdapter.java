package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.bme.aut.mobsoftlab.seriestrackerapp.GlideApp;
import hu.bme.aut.mobsoftlab.seriestrackerapp.R;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SavedSeries;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(SavedSeries selectedSeries);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.seriesPoster)
        ImageView poster;
        @BindView(R.id.seriesTitle)
        TextView title;
        @BindView(R.id.seriesSeason)
        TextView season;
        @BindView(R.id.seriesEpisode)
        TextView episode;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION)
                itemClickListener.onItemClick(series.get(position));
        }
    }

    private final OnItemClickListener itemClickListener;
    private List<SavedSeries> series;

    public SeriesAdapter(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        this.series = new ArrayList<>();
    }

    public void setSeries(List<SavedSeries> series) {
        this.series = series;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_item_series, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the data model based on position
        SavedSeries currentSeries = series.get(position);

        // Set item views based on the data model
        Context context = holder.itemView.getContext();
        GlideApp.with(context)
                .load(currentSeries.getPosterUrl())
                .placeholder(R.drawable.placeholder)
                .fitCenter()
                .into(holder.poster);
        holder.title.setText(currentSeries.getTitle());
        holder.season.setText(context.getString(R.string.series_season, currentSeries.getSeason()));
        holder.episode.setText(context.getString(R.string.series_episode, currentSeries.getEpisode()));
    }

    @Override
    public int getItemCount() {
        return series.size();
    }
}
