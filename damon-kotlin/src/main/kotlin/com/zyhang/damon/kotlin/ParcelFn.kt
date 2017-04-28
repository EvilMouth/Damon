package com.zyhang.damon.kotlin

import android.os.Parcel

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.下午5:43
 * Modify by:
 * Modify time:
 * Modify remark:
 */

internal object ParcelFn {
    val CLASS_LOADER: ClassLoader = ParcelFn::class.java.classLoader

    fun <T> unmarshall(array: ByteArray): T {
        val parcel = Parcel.obtain()
        parcel.unmarshall(array, 0, array.size)
        parcel.setDataPosition(0)
        val value = parcel.readValue(CLASS_LOADER)
        parcel.recycle()
        @Suppress("UNCHECKED_CAST")
        return value as T
    }

    fun marshall(o: Any): ByteArray {
        val parcel = Parcel.obtain()
        parcel.writeValue(o)
        val result = parcel.marshall()
        parcel.recycle()
        return result
    }
}