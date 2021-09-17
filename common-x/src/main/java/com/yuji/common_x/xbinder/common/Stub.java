package com.yuji.common_x.xbinder.common;

import android.app.Person;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.yuji.xlt.ability.utils.Logger;

import java.util.List;

/**
 * <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/9/15]
 * @since V1.0.0
 */
public abstract class Stub extends Binder implements IPersonManager {
    private static final String DESCRIPTOR = "com.yuji.common_x.xbinder.bean.Person.IPersonManager";

    static final int INTERFACE_addPerson = IBinder.FIRST_CALL_TRANSACTION;
    static final int INTERFACE_getPersonList = IBinder.FIRST_CALL_TRANSACTION + 1;

    public Stub() {
        this.attachInterface(this, DESCRIPTOR);
    }

    public static IPersonManager asInterface(IBinder binder) {
        if ((binder == null)) {
            return null;
        }
        IInterface iin = binder.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof IPersonManager))) {
            return ((IPersonManager) iin);
        }
        return new Proxy(binder);
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION:
                reply.writeString(DESCRIPTOR);
                return true;
            case INTERFACE_addPerson:
                Logger.w("Stub", "onTransact INTERFACE_addPerson" + Thread.currentThread());
                data.enforceInterface(DESCRIPTOR);
                Person arg0 = null;
                if (0 != data.readInt()) {
                    arg0 = Person.CREATOR.createFromParcel(data);
                }
                this.addPerson(arg0);
                reply.writeNoException();
                return true;
            case INTERFACE_getPersonList:
                data.enforceInterface(DESCRIPTOR);
                List<Person> result = this.getPersonList();
                reply.writeNoException();
                reply.writeTypedList(result);
                break;
        }
        return super.onTransact(code, data, reply, flags);
    }

}
