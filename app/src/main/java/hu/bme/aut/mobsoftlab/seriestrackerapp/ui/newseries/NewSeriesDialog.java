package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.newseries;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.Button;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import hu.bme.aut.mobsoftlab.seriestrackerapp.R;
import hu.bme.aut.mobsoftlab.seriestrackerapp.SeriesTrackerApplication;

public class NewSeriesDialog extends DialogFragment implements NewSeriesScreen {

    private static final String ARG_ALREADY_ADDED_SERIES = "ALREADY_ADDED_SERIES";

    @Inject
    NewSeriesPresenter presenter;

    public NewSeriesDialog() {
        setRetainInstance(true);
        SeriesTrackerApplication.injector.inject(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
                .setPositiveButton(R.string.dialog_new_series_positive, null)
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

        Button positiveButton = dialog.getButton(Dialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(view -> presenter.addNewSeries());
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
        // TODO set UI
    }

    @Override
    public void setEpisodeCount(int episodeCount) {
        // TODO set UI
    }
    
        
    @Override
    public void showNetworkErrorMessage(String errorMessage) {
        // TODO set UI
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
}
