package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.newseries;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesSearchResult;

public class SeriesSearchResultAdapter extends ArrayAdapter<SeriesSearchResult> {

    private final NewSeriesPresenter presenter;

    public SeriesSearchResultAdapter(@NonNull Context context, @NonNull NewSeriesPresenter presenter) {
        super(context, android.R.layout.simple_dropdown_item_1line);
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = super.getView(position, null, parent);

        TextView textView = (TextView) convertView;
        SeriesSearchResult item = getItem(position);
        textView.setText(item == null ? null : item.getTitle());

        return textView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return filter;
    }

    private final Filter filter = new Filter() {

        @Override
        public String convertResultToString(Object resultValue) {
            return ((SeriesSearchResult) resultValue).getTitle();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint == null)
                return null;

            SeriesSearchResult[] searchResults = presenter.getSearchResults(constraint.toString()).toArray(new SeriesSearchResult[] {});

            FilterResults results = new FilterResults();
            results.values = searchResults;
            results.count = searchResults.length;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();

            if (results != null && results.values != null)
                addAll((SeriesSearchResult[]) results.values);

            notifyDataSetChanged();
        }

    };
}