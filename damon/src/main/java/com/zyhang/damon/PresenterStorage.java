package com.zyhang.damon;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;

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

    private ArrayMap<String, MvpPresenter> idToPresenter = new ArrayMap<>();
    private ArrayMap<MvpPresenter, String> presenterToId = new ArrayMap<>();

    void add(List<? extends MvpPresenter> presenters) {
        for (MvpPresenter presenter : presenters) {
            String id = presenter.getClass().getSimpleName() + "/" + System.nanoTime() + "/" + (int) (Math.random() * Integer.MAX_VALUE);
            idToPresenter.put(id, presenter);
            presenterToId.put(presenter, id);
            presenter.addOnDestroyListener(() -> idToPresenter.remove(presenterToId.remove(presenter)));
        }
    }

    @Nullable
    List<? extends MvpPresenter> getPresenter(@Nullable String[] ids) {
        if (null == ids) {
            return null;
        }
        List<MvpPresenter> presenters = null;
        for (String id : ids) {
            //noinspection unchecked
            MvpPresenter presenter = idToPresenter.get(id);
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
    String getId(MvpPresenter presenter) {
        return presenterToId.get(presenter);
    }
}
