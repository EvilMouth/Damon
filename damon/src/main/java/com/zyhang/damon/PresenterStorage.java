package com.zyhang.damon;

import java.util.HashMap;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午10:56
 * Modify by:
 * Modify time:
 * Modify remark:
 */

enum PresenterStorage {
    INSTANCE;

    private HashMap<String, MvpPresenter> mIdToPresenter = new HashMap<>();
    private HashMap<MvpPresenter, String> mPresenterToId = new HashMap<>();

    public void add(final MvpPresenter presenter) {
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

    public <P> P getPresenter(String id) {
        //noinspection unchecked
        return (P) mIdToPresenter.get(id);
    }

    public String getId(MvpPresenter presenter) {
        return mPresenterToId.get(presenter);
    }
}
