/*
 * WordCounterReduce.java
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

import java.util.List;
import java.util.function.BiConsumer;

/**
 * Reducer.
 */
public class WordCounterReduce {

    public static void reduce(final String key, final List<Long> values,
                              final BiConsumer<String, Long> consumer) {

        consumer.accept(key, values.stream().mapToLong(Long::valueOf).sum());
    }
}
