/*
 * Main.java
 *
 * Copyright (C) 2018 Pavel Prokhorov (pavelvpster@gmail.com)
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.interactiverobotics.map_reduce_demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Main application class.
 */
public class Main {

    private static Map<String, List<Long>> data = new HashMap<>();

    public static void main(final String[] args) throws InterruptedException {

        final List<String> documents = Arrays.asList("abc abc xyz", "abc def xyz");

        final CountDownLatch countDownLatch = new CountDownLatch(documents.size());
        documents.forEach(document ->
                new Thread(() -> {
                    System.out.println("Process document '" + document + "'."
                            + " Thread Id: " + Thread.currentThread().getId());

                    WordCounterMap.map(document, Main::put);

                    countDownLatch.countDown();
                }).start());
        countDownLatch.await();

        data.forEach((key, values) ->
                new Thread(() ->

                        WordCounterReduce.reduce(key, values, Main::print)

                ).start());
    }

    private static void put(final String key, final Long value) {
        synchronized (data) {
            final List<Long> values = data.getOrDefault(key, new ArrayList<>());
            values.add(value);
            data.put(key, values);
        }
    }

    private static void print(final String key, final Long value) {
        System.out.println("Word '" + key + "' occurred " + value + " time(s)."
                + " Thread Id: " + Thread.currentThread().getId());
    }
}
