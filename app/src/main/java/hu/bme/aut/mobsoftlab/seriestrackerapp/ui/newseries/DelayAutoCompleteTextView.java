package hu.bme.aut.mobsoftlab.seriestrackerapp.ui.newseries;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

public class DelayAutoCompleteTextView extends AppCompatAutoCompleteTextView {

    private static final int MESSAGE_TEXT_CHANGED = 100;
    private static final int AUTOCOMPLETE_DELAY = 750;

    private ProgressBar mLoadingIndicator;

    private final Handler mHandler;

    public DelayAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mHandler = new Handler(msg -> {
            DelayAutoCompleteTextView.super.performFiltering((CharSequence) msg.obj, msg.arg1);
            return true;
        });
    }

    public void setLoadingIndicator(ProgressBar progressBar) {
        mLoadingIndicator = progressBar;
    }

    @Override
    protected void performFiltering(CharSequence text, int keyCode) {
        if (mLoadingIndicator != null) {
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }
        mHandler.removeMessages(MESSAGE_TEXT_CHANGED);
        mHandler.sendMessageDelayed(mHandler.obtainMessage(MESSAGE_TEXT_CHANGED, text), AUTOCOMPLETE_DELAY);
    }

    @Override
    public void onFilterComplete(int count) {
        if (mLoadingIndicator != null) {
            mLoadingIndicator.setVisibility(View.GONE);
        }
        super.onFilterComplete(count);
    }
}

