/*
 * WordCounterMap.java
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

import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * Mapper.
 */
public class WordCounterMap {

    private static final Long ONE = 1L;

    public static void map(final String source, final BiConsumer<String, Long> consumer) {

        Stream.of(source.split(" ")).forEach(word -> consumer.accept(word, ONE));
    }
}
