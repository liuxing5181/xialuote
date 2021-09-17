package com.yuji.common_x.xbinder.server;

import android.app.Person;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.yuji.common_x.xbinder.common.Stub;
import com.yuji.xlt.ability.utils.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * <BR>
 *
 * @author dengshishuang
 * @version [V1.0.0, 2021/9/15]
 * @since V1.0.0
 */
public class RemoteService extends Service {
    private ArrayList<Person> peoples = new ArrayList<>();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logger.w("RemoteService", "onBind ..");
        return iBinder;
    }

    private IBinder iBinder = new Stub() {
        @Override
        public void addPerson(Person person) throws RemoteException {
            peoples.add(person);
        }

        @Override
        public List<Person> getPersonList() throws RemoteException {
            return peoples;
        }
    };
}
