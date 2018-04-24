package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.newseries;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.bme.aut.mobsoftlab.seriestrackerapp.R;
import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;
import hu.bme.aut.mobsoftlab.seriestrackerapp.model.SeriesSearchResult;

public class NewSeriesDialog extends DialogFragment implements NewSeriesScreen {

    private static final String ARG_ALREADY_ADDED_SERIES = "ALREADY_ADDED_SERIES";

    private DialogDismissedListener dismissedListener;

    @Inject
    NewSeriesPresenter presenter;

    @BindView(R.id.seriesSearch)
    DelayAutoCompleteTextView seriesSearch;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.seasonPicker)
    NumberPicker seasonPicker;
    @BindView(R.id.episodePicker)
    NumberPicker episodePicker;
    @BindView(R.id.networkErrorText)
    TextView networkErrorText;

    public NewSeriesDialog() {
        setRetainInstance(true);
        SeriesTrackerApplication.injector.inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the DialogDismissedListener so we can send events to the host
            dismissedListener = (DialogDismissedListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString() + " must implement DialogDismissedListener");
        }

        presenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        presenter.detachScreen();
        super.onDetach();
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (!presenter.isInitialized() && !tryReadArguments(getArguments()))
            throw new IllegalStateException("Did not find the necessary arguments for the dialog.");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_new_series, null))
                .setTitle(R.string.dialog_new_series_title)
                // Add action buttons
                .setPositiveButton(R.string.dialog_positive, null)
                .setNegativeButton(R.string.dialog_new_series_negative, (dialog, which) -> dialog.dismiss());
        return builder.create();
    }

    private boolean tryReadArguments(Bundle arguments) {
        if (arguments == null)
            return false;

        String[] alreadyAddedSeries = arguments.getStringArray(ARG_ALREADY_ADDED_SERIES);
        if (alreadyAddedSeries == null)
            return false;

        presenter.initialize(new HashSet<>(Arrays.asList(alreadyAddedSeries)));
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();

        AlertDialog dialog = (AlertDialog) getDialog();
        if (dialog == null)
            return;

        dialog.setOnDismissListener(d -> dismissedListener.onDialogDismissed());

        ButterKnife.bind(this, dialog);

        Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(view -> presenter.addNewSeries());

        seriesSearch.setAdapter(new SeriesSearchResultAdapter(getContext(), presenter));
        seriesSearch.setLoadingIndicator(progressBar);
        seriesSearch.setOnItemClickListener(
                (parent, view, position, id) -> presenter.chooseSeries((SeriesSearchResult) parent.getItemAtPosition(position)));
    }

    /**
     * Fixes a known android support library bug - the dialog dismisses on orientation change.
     * See this <a href="https://stackoverflow.com/a/15444485">SO answer</a>.
     */
    @Override
    public void onDestroyView() {
        Dialog dialog = getDialog();
        // handles https://code.google.com/p/android/issues/detail?id=17423
        if (dialog != null && getRetainInstance()) {
            dialog.setDismissMessage(null);
        }
        super.onDestroyView();
    }

    @Override
    public void setSeasonCount(int seasonCount) {
        setupPicker(seasonPicker, seasonCount, (picker, oldVal, newVal) -> presenter.chooseSeason(newVal));
    }

    @Override
    public void setEpisodeCount(int episodeCount) {
        setupPicker(episodePicker, episodeCount, (picker, oldVal, newVal) -> presenter.chooseEpisode(newVal));

        networkErrorText.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);
    }

    private static void setupPicker(NumberPicker picker, int maxValue, NumberPicker.OnValueChangeListener changeListener) {
        picker.setMinValue(1);
        picker.setMaxValue(maxValue);
        picker.setWrapSelectorWheel(true);
        picker.setOnValueChangedListener(changeListener);
    }

    @Override
    public void showNetworkErrorMessage(String errorMessage) {
        networkErrorText.setText(getString(R.string.network_error_message, errorMessage));

        linearLayout.setVisibility(View.GONE);
        networkErrorText.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissDialog() {
        dismiss();
    }

    public static NewSeriesDialog newInstance(Set<String> alreadyAddedSeries) {
        NewSeriesDialog fragment = new NewSeriesDialog();

        Bundle args = new Bundle();
        args.putStringArray(ARG_ALREADY_ADDED_SERIES, alreadyAddedSeries.toArray(new String[]{}));
        fragment.setArguments(args);

        return fragment;
    }

    public interface DialogDismissedListener {
        void onDialogDismissed();
    }
}
