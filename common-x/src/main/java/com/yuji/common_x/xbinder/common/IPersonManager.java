package com.yuji.common_x.xbinder.common;

import android.app.Person;
import android.os.IInterface;
import android.os.RemoteException;

import java.util.List;

/**
 * <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/9/15]
 * @since V1.0.0
 */
public interface IPersonManager extends IInterface {
    void addPerson(Person person) throws RemoteException;

    List<Person> getPersonList() throws RemoteException;
}
