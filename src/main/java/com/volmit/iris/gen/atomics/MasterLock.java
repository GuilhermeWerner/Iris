package com.volmit.iris.gen.atomics;

import com.volmit.iris.util.IrisLock;
import com.volmit.iris.util.KMap;

public class MasterLock
{
	private KMap<String, IrisLock> locks;
	private IrisLock lock;

	public MasterLock()
	{
		locks = new KMap<>();
		lock = new IrisLock("MasterLock");
	}

	public void clear()
	{
		locks.clear();
	}

	public void lock(String key)
	{
		lock.lock();
		if(!locks.containsKey(key))
		{
			locks.put(key, new IrisLock("Locker"));
		}

		IrisLock l = locks.get(key);
		lock.unlock();
		l.lock();
	}

	public void unlock(String key)
	{
		lock.lock();
		if(!locks.containsKey(key))
		{
			locks.put(key, new IrisLock("Unlocker"));
		}

		IrisLock l = locks.get(key);
		lock.unlock();
		l.unlock();
	}
}