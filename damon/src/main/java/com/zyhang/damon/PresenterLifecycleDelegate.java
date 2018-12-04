package com.zyhang.damon;

import android.os.Bundle;

import com.zyhang.damon.factory.PresenterFactory;
import com.zyhang.damon.factory.PresenterGetter;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.23:02
 * Modify by:
 * Modify time:
 * Modify remark:
 */

public class PresenterLifecycleDelegate<P extends MvpPresenter> implements PresenterGetter<P> {

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

    @Nullable
    @Override
    public List<? extends MvpPresenter> getPresenters() {
        return mPresenters;
    }

    @Nullable
    @Override
    public P getPresenter() {
        if (mPresenters == null || mPresenters.isEmpty()) {
            return null;
        }
        // 导致ClassCastException的原因
        // 1.@RequiresPresenter注入了多个Presenter并且Activity泛型P不是@RequiresPresenter的第一个
        // 2.@RequiresPresenter注入的Presenter与Activity泛型P不匹配
        return (P) mPresenters.get(0);
    }

    public void dispatchCreate(Object view, @Nullable Bundle arguments, @Nullable Bundle savedState) {
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
            mPresenters = mPresenterFactory.createPresenter();
            PresenterStorage.INSTANCE.add(mPresenters);
        }
    }

    public void dispatchCreateView() {
        if (mPresenters != null && !mPresenters.isEmpty()) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.createView();
            }
        }
    }

    public void dispatchStart() {
        if (mPresenters != null && !mPresenters.isEmpty()) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.start();
            }
        }
    }

    public Bundle dispatchSaveInstanceState() {
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

    public void dispatchResume() {
        if (mPresenters != null && !mPresenters.isEmpty() && !mPresenterHasView) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.resume();
            }
            mPresenterHasView = true;
        }
    }

    public void dispatchPause() {
        if (mPresenters != null && !mPresenters.isEmpty() && mPresenterHasView) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.pause();
            }
            mPresenterHasView = false;
        }
    }

    public void dispatchStop() {
        if (mPresenters != null && !mPresenters.isEmpty()) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.stop();
            }
        }
    }

    public void dispatchDestroyView() {
        if (mPresenters != null && !mPresenters.isEmpty()) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.destroyView();
            }
        }
    }

    public void dispatchDestroy(boolean isFinal) {
        if (isFinal && mPresenters != null && !mPresenters.isEmpty()) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.destroy();
            }
            PresenterStorage.INSTANCE.remove(mPresenters);
            mPresenters.clear();
            mPresenters = null;
            mPresenterFactory = null;
        }
    }
}
