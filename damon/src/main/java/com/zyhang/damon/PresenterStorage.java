package com.zyhang.damon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.22:56
 * Modify by:
 * Modify time:
 * Modify remark:
 */

enum PresenterStorage {
    INSTANCE;

    private HashMap<String, MvpPresenter> mIdToPresenter = new HashMap<>();
    private HashMap<MvpPresenter, String> mPresenterToId = new HashMap<>();

    public void add(List<? extends MvpPresenter> presenters) {
        for (final MvpPresenter presenter : presenters) {
            String id = presenter.getClass().getSimpleName() + "/" + System.nanoTime() + "/" + (int) (Math.random() * Integer.MAX_VALUE);
            mIdToPresenter.put(id, presenter);
            mPresenterToId.put(presenter, id);
            presenter.addOnDestroyListener(new OnDestroyListener() {
                @Override
                public void onDestroy() {
                    mIdToPresenter.remove(mPresenterToId.remove(presenter));
                }
            });
        }
    }

    @Nullable
    public List<? extends MvpPresenter> getPresenter(@Nullable String[] ids) {
        if (null == ids) {
            return null;
        }
        List<MvpPresenter> presenters = null;
        for (String id : ids) {
            //noinspection unchecked
            MvpPresenter presenter = mIdToPresenter.get(id);
            if (presenter != null) {
                if (null == presenters) {
                    presenters = new ArrayList<>();
                }
                presenters.add(presenter);
            }
        }
        return presenters;
    }

    @Nullable
    public String getId(MvpPresenter presenter) {
        return mPresenterToId.get(presenter);
    }
}
