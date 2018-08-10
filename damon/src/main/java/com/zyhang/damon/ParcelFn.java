package com.zyhang.damon;

import android.os.Parcel;

/**
 * ProjectName:Damon
 * Description:
 * Created by zyhang on 2017/4/28.22:34
 * Modify by:
 * Modify time:
 * Modify remark:
 */

final class ParcelFn {
    private static final ClassLoader CLASS_LOADER = ParcelFn.class.getClassLoader();

    static <T> T unmarshall(byte[] array) {
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(array, 0, array.length);
        parcel.setDataPosition(0);
        Object value = parcel.readValue(CLASS_LOADER);
        parcel.recycle();
        //noinspection unchecked
        return (T) value;
    }

    static byte[] marshall(Object o) {
        Parcel parcel = Parcel.obtain();
        parcel.writeValue(o);
        byte[] result = parcel.marshall();
        parcel.recycle();
        return result;
    }
}
