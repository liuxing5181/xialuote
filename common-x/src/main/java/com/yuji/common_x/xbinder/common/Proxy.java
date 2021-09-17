package com.yuji.common_x.xbinder.common;

import android.app.Person;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.yuji.xlt.ability.utils.Logger;

import java.util.List;

/**
 * <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/9/15]
 * @since V1.0.0
 */
public class Proxy implements IPersonManager {
    private static final String DESCRIPTOR = "com.yuji.common_x.xbinder.bean.Person.IPersonManager";

    private IBinder mRemote;

    Proxy(android.os.IBinder remote) {
        mRemote = remote;
    }

    @Override
    public void addPerson(Person person) throws RemoteException {
        Parcel data = Parcel.obtain(); //客户端准备发给服务端的数据就在这个包下。
        Parcel reply = Parcel.obtain();//服务端返回的数据
        try {
            data.writeInterfaceToken(DESCRIPTOR);
            if (person != null) {
                data.writeInt(1);
                person.writeToParcel(data, 0);
            } else {
                data.writeInt(0);
            }
            Logger.w("Proxy", "addPerson th = " + Thread.currentThread());
            mRemote.transact(Stub.INTERFACE_addPerson, data, reply, 0);
            reply.readException();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reply.recycle();
            data.recycle();
        }
    }

    @Override
    public List<Person> getPersonList() throws RemoteException {
        return null;
    }

    @Override
    public IBinder asBinder() {
        return null;
    }
}
