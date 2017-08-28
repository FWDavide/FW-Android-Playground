package com.futureworkshops.android.architecture.presentation.common;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.futureworkshops.android.architecture.R;

import butterknife.BindView;

/**
 * Base activity that can handle {@link Toolbar} set up.
 */

public class BaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void setupToolbar(boolean showHomeAsUp) {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
    }

}
