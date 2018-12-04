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

    /**
     * 分发onCreate
     *
     * @param view                mvpView
     * @param arguments           bundle
     * @param originSavedState    保存状态
     * @param presenterSavedState presenter保存状态
     */
    public void dispatchCreate(Object view, @Nullable Bundle arguments, @Nullable Bundle originSavedState, @Nullable Bundle presenterSavedState) {
        if (mPresenterFactory == null) {
            return;
        }
        Bundle presenterBundle = null;
        if (presenterSavedState != null) {
            presenterBundle = ParcelFn.unmarshall(ParcelFn.marshall(presenterSavedState));
        }
        createPresenter(presenterBundle);
        if (mPresenters != null && !mPresenters.isEmpty()) {
            mPresenterFactory.bindPresenter(mPresenters);
            for (MvpPresenter presenter : mPresenters) {
                //noinspection unchecked
                presenter.hostCreate(view, arguments, originSavedState);
            }
        }
    }

    /**
     * 创建Presenter
     *
     * @param presenterBundle presenter保存状态
     */
    private void createPresenter(Bundle presenterBundle) {
        if (presenterBundle != null) {
            mPresenters = PresenterStorage.INSTANCE.getPresenter(presenterBundle.getStringArray(PRESENTER_ID_KEYS));
        }

        if (mPresenters == null) {
            mPresenters = mPresenterFactory.createPresenter();
            PresenterStorage.INSTANCE.add(mPresenters);
            for (MvpPresenter presenter : mPresenters) {
                // dispatch onCreate
                presenter.create(null != presenterBundle ? presenterBundle.getBundle(PRESENTER_KEY.concat(presenter.getClass().getSimpleName())) : null);
            }
        }
    }

    /**
     * 分发onCreateView
     */
    public void dispatchCreateView() {
        if (mPresenters != null && !mPresenters.isEmpty()) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.hostCreateView();
            }
        }
    }

    /**
     * 分发onStart
     */
    public void dispatchStart() {
        if (mPresenters != null && !mPresenters.isEmpty()) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.hostStart();
            }
        }
    }

    /**
     * 保存presenter状态
     *
     * @return bundle
     */
    public Bundle getPresenterSaveState() {
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

    /**
     * 分发onResume
     */
    public void dispatchResume() {
        if (mPresenters != null && !mPresenters.isEmpty() && !mPresenterHasView) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.hostResume();
            }
            mPresenterHasView = true;
        }
    }

    /**
     * 分发onPause
     */
    public void dispatchPause() {
        if (mPresenters != null && !mPresenters.isEmpty() && mPresenterHasView) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.hostPause();
            }
            mPresenterHasView = false;
        }
    }

    /**
     * 分发onStop
     */
    public void dispatchStop() {
        if (mPresenters != null && !mPresenters.isEmpty()) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.hostStop();
            }
        }
    }

    /**
     * 分发onDestroyView
     */
    public void dispatchDestroyView() {
        if (mPresenters != null && !mPresenters.isEmpty()) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.hostDestroyView();
            }
        }
    }

    /**
     * 分发onDestroy
     *
     * @param isFinal 是否真正destroy
     */
    public void dispatchDestroy(boolean isFinal) {
        if (mPresenters != null && !mPresenters.isEmpty()) {
            for (MvpPresenter presenter : mPresenters) {
                presenter.hostDestroy();
                if (isFinal) {
                    presenter.destroy();
                }
            }
            if (isFinal) {
                mPresenters.clear();
                mPresenters = null;
                mPresenterFactory = null;
            }
        }
    }
}
