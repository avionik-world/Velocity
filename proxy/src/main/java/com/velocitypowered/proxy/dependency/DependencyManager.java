/*
 * Copyright (C) 2024 Velocity Contributors
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

package com.velocitypowered.proxy.dependency;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DependencyManager {

    private final File directory = new File("dependencies");

    public DependencyManager() {
        checkFolderExists();
    }

    public void checkFolderExists() {
        if (!this.directory.exists()) {
            this.directory.mkdirs();
        }
    }

    public List<URL> getDependencyUrls() {
        return getDependencyFiles().stream().map(file -> {
            try {
                return file.toURI().toURL();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    public List<File> getDependencyFiles() {
        return Arrays.stream(Objects.requireNonNull(this.directory.listFiles()))
                .filter(File::isFile)
                .filter(file -> file.getName().endsWith(".jar"))
                .toList();
    }

}
