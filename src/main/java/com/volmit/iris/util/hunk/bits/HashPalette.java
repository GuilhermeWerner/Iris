/*
 * Iris is a World Generator for Minecraft Bukkit Servers
 * Copyright (c) 2021 Arcane Arts (Volmit Software)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.volmit.iris.util.hunk.bits;

import com.volmit.iris.util.collection.KMap;
import com.volmit.iris.util.function.Consumer2;

import java.util.concurrent.atomic.AtomicInteger;

public class HashPalette<T> implements Palette<T> {
    private final KMap<T, Integer> palette;
    private final KMap<Integer, T> lookup;
    private final AtomicInteger size;

    public HashPalette()
    {
        this.size = new AtomicInteger(0);
        this.palette = new KMap<>();
        this.lookup = new KMap<>();
    }

    @Override
    public T get(int id) {
        if(id < 0 || id >= size.get())
        {
            return null;
        }

        return lookup.get(id);
    }

    @Override
    public int add(T t) {
        int index = size.getAndIncrement();
        palette.put(t, index);
        lookup.put(index, t);
        return index;
    }

    @Override
    public int id(T t) {
        Integer v = palette.get(t);
        return v != null ? v : -1;
    }

    @Override
    public int size() {
        return size.get();
    }

    @Override
    public void iterate(Consumer2<T, Integer> c) {
        for(T i : palette.keySet())
        {
            c.accept(i, id(i));
        }
    }
}
