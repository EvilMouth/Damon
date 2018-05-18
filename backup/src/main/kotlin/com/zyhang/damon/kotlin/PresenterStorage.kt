package com.zyhang.damon.kotlin

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午5:54
 * Modify by:
 * Modify time:
 * Modify remark:
 */

internal enum class PresenterStorage {
    INSTANCE;

    val mIdToPresenter: HashMap<String, MvpPresenter<MvpView>> = HashMap()
    val mPresenterToId: HashMap<MvpPresenter<MvpView>, String> = HashMap()

    fun add(presenter: MvpPresenter<MvpView>) {
        val id: String = presenter::class.simpleName + "/" + System.nanoTime() + "/" + Math.random() * Int.MAX_VALUE
        mIdToPresenter.put(id, presenter)
        mPresenterToId.put(presenter, id)
        presenter.addOnDestroyListener(object : OnDestroyListener {
            override fun onDestroy() {
                mIdToPresenter.remove(mPresenterToId.remove(presenter))
            }
        })
    }

    @Suppress("UNCHECKED_CAST")
    fun <P> getPresenter(id: String): P = mIdToPresenter[id] as P

    fun getId(presenter: MvpPresenter<MvpView>): String = mPresenterToId[presenter]!!
}