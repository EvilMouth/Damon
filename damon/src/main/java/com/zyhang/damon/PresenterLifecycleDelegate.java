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

public class PresenterLifecycleDelegate<Presenter extends MvpPresenter> {

    private static final String PRESENTER_KEY = "presenter";
    private static final String PRESENTER_ID_KEY = "presenter_id";

    @Nullable
    private PresenterFactory<Presenter> mPresenterFactory;
    @Nullable
    private List<Presenter> mPresenters;

    private boolean mPresenterHasView;

    public PresenterLifecycleDelegate(@Nullable PresenterFactory<Presenter> presenterFactory) {
        this.mPresenterFactory = presenterFactory;
    }

    @Nullable
    public Presenter getPresenter() {
        return mPresenter;
    }

    public void onCreate(MvpView view, @Nullable Bundle arguments, @Nullable Bundle savedState) {
        if (mPresenterFactory == null) return;
        Bundle presenterBundle = null;
        if (savedState != null) {
            presenterBundle = ParcelFn.unmarshall(ParcelFn.marshall(savedState));
        }
        createPresenter(presenterBundle);
        if (mPresenter != null) {
            //noinspection unchecked
            mPresenter.create(view, arguments, null != presenterBundle ? presenterBundle.getBundle(PRESENTER_KEY) : null);
        }
    }

    private void createPresenter(Bundle presenterBundle) {
        if (presenterBundle != null) {
            mPresenter = PresenterStorage.INSTANCE.getPresenter(presenterBundle.getString(PRESENTER_ID_KEY));
        }

        if (mPresenters == null) {
            //noinspection ConstantConditions
            mPresenters = mPresenterFactory.createPresenter();
            PresenterStorage.INSTANCE.add(mPresenters);
        }
    }

    public void onStart() {
        if (mPresenter != null) {
            mPresenter.start();
        }
    }

    public Bundle onSaveInstanceState() {
        Bundle bundle = new Bundle();
        if (mPresenter != null) {
            Bundle presenterBundle = new Bundle();
            mPresenter.save(presenterBundle);
            bundle.putBundle(PRESENTER_KEY, presenterBundle);
            bundle.putString(PRESENTER_ID_KEY, PresenterStorage.INSTANCE.getId(mPresenter));
        }
        return bundle;
    }

    public void onResume() {
        if (mPresenter != null && !mPresenterHasView) {
            mPresenter.resume();
            mPresenterHasView = true;
        }
    }

    public void onPause() {
        if (mPresenter != null && mPresenterHasView) {
            mPresenter.pause();
            mPresenterHasView = false;
        }
    }

    public void onStop() {
        if (mPresenter != null) {
            mPresenter.stop();
        }
    }

    public void onDestroy(boolean isFinal) {
        if (mPresenter != null && isFinal) {
            mPresenter.destroy();
            mPresenter = null;
        }
    }
}
