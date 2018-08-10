package com.zyhang.damon;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.23:02
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class PresenterLifecycleDelegate {

    private static final String PRESENTER_KEY = "presenter - ";
    private static final String PRESENTER_ID_KEYS = "presenter_ids";

    @Nullable
    private PresenterFactory mPresenterFactory;
    @Nullable
    private List<? extends MvpPresenter> mPresenters;

    private boolean mPresenterHasView;

    public PresenterLifecycleDelegate(@Nullable PresenterFactory presenterFactory) {
        this.mPresenterFactory = presenterFactory;
    }

    public void onCreate(MvpView view, @Nullable Bundle arguments, @Nullable Bundle savedState) {
        if (mPresenterFactory == null) {
            return;
        }
        Bundle presenterBundle = null;
        if (savedState != null) {
            presenterBundle = ParcelFn.unmarshall(ParcelFn.marshall(savedState));
        }
        createPresenter(presenterBundle);
        if (mPresenters != null && !mPresenters.isEmpty()) {
            mPresenterFactory.bindPresenter(mPresenters);
            for (MvpPresenter presenter : mPresenters) {
                //noinspection unchecked
                presenter.create(view, arguments, null != presenterBundle ? presenterBundle.getBundle(PRESENTER_KEY.concat(presenter.getClass().getSimpleName())) : null);
            }
        }
    }

    private void createPresenter(Bundle presenterBundle) {
        if (presenterBundle != null) {
            mPresenters = PresenterStorage.INSTANCE.getPresenter(presenterBundle.getStringArray(PRESENTER_ID_KEYS));
        }

        if (mPresenters == null) {
            //noinspection ConstantConditions
            mPresenters = mPresenterFactory.createPresenter();
            PresenterStorage.INSTANCE.add(mPresenters);
        }
    }

    public void onStart() {
        if (mPresenters != null && !mPresenters.isEmpty()) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.start();
            }
        }
    }

    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        if (mPresenters != null && !mPresenters.isEmpty()) {
            String[] ids = new String[mPresenters.size()];
            for (MvpPresenter presenter : mPresenters) {
                Bundle presenterBundle = new Bundle();
                presenter.save(presenterBundle);
                bundle.putBundle(PRESENTER_KEY.concat(presenter.getClass().getSimpleName()), presenterBundle);

                ids[mPresenters.indexOf(presenter)] = PresenterStorage.INSTANCE.getId(presenter);
            }
            bundle.putStringArray(PRESENTER_ID_KEYS, ids);
        }
        return bundle;
    }

    public void onResume() {
        if (mPresenters != null && !mPresenters.isEmpty() && !mPresenterHasView) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.resume();
            }
            mPresenterHasView = true;
        }
    }

    public void onPause() {
        if (mPresenters != null && !mPresenters.isEmpty() && mPresenterHasView) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.pause();
            }
            mPresenterHasView = false;
        }
    }

    public void onStop() {
        if (mPresenters != null && !mPresenters.isEmpty()) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.stop();
            }
        }
    }

    public void onDestroy(boolean isFinal) {
        if (isFinal && mPresenters != null && !mPresenters.isEmpty()) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.destroy();
            }
            mPresenters.clear();
            mPresenters = null;
        }
    }
}
