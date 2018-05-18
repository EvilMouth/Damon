package com.zyhang.damon.kotlin

import android.os.Bundle
import android.support.annotation.CallSuper
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.RxLifecycleAndroid
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.CopyOnWriteArrayList

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午5:46
 * Modify by:
 * Modify time:
 * Modify remark:
 */

open class MvpPresenter<out V : MvpView> : MvpPresenterHelper, LifecycleProvider<ActivityEvent> {

    private var mView: V? = null
    fun view(): V? = mView

    private val lifecycleSubject: BehaviorSubject<ActivityEvent> = BehaviorSubject.create<ActivityEvent>()

    private val mDisposables = CompositeDisposable()

    fun add(disposable: Disposable) {
        mDisposables.add(disposable)
    }

    fun remove(disposable: Disposable) {
        mDisposables.remove(disposable)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onCreate(arguments: Bundle?, savedState: Bundle?) = Unit

    override fun onStart() = Unit

    override fun onSave(state: Bundle) = Unit

    override fun onResume() = Unit

    override fun onPause() = Unit

    override fun onStop() = Unit

    @CallSuper
    override fun onDestroy() {
        mDisposables.dispose()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    internal fun create(view: MvpView, arguments: Bundle?, savedState: Bundle?) {
        lifecycleSubject.onNext(ActivityEvent.CREATE)
        @Suppress("UNCHECKED_CAST")
        mView = view as V
        onCreate(arguments, savedState)
    }

    internal fun start() {
        lifecycleSubject.onNext(ActivityEvent.START)
        onStart()
    }

    internal fun save(state: Bundle) {
        onSave(state)
    }

    internal fun resume() {
        lifecycleSubject.onNext(ActivityEvent.RESUME)
        onResume()
    }

    internal fun pause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE)
        onPause()
    }

    internal fun stop() {
        lifecycleSubject.onNext(ActivityEvent.STOP)
        onStop()
    }

    internal fun destroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY)
        mOnDestroyListeners.forEach(OnDestroyListener::onDestroy)
        onDestroy()
        mView = null
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private val mOnDestroyListeners: CopyOnWriteArrayList<OnDestroyListener> = CopyOnWriteArrayList()

    fun addOnDestroyListener(listener: OnDestroyListener) {
        mOnDestroyListeners.add(listener)
    }

    fun removeOnDestroyListener(listener: OnDestroyListener) {
        mOnDestroyListeners.remove(listener)
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    override fun lifecycle(): Observable<ActivityEvent>
            = lifecycleSubject.hide()

    override fun <T : Any?> bindUntilEvent(event: ActivityEvent): LifecycleTransformer<T>
            = RxLifecycle.bindUntilEvent(lifecycleSubject, event)

    override fun <T : Any?> bindToLifecycle(): LifecycleTransformer<T>
            = RxLifecycleAndroid.bindActivity(lifecycleSubject)
}