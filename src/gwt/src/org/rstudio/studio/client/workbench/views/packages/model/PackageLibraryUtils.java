/*
 * PackageLibraryUtils.java
 *
 * Copyright (C) 2009-14 by RStudio, Inc.
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */
package org.rstudio.studio.client.workbench.views.packages.model;

import org.rstudio.core.client.files.FileSystemItem;
import org.rstudio.studio.client.workbench.model.Session;

public class PackageLibraryUtils
{
   public enum PackageLibraryType
   {
      Project,
      User,
      System
   }

   public static PackageLibraryType typeOfLibrary(Session session, 
                                                  String library)
   {
      FileSystemItem projectDir = 
            session.getSessionInfo().getActiveProjectDir();

      // if there's an active project and this package is in its library or
      // the package has no recorded library (i.e. it's not installed), it
      // belongs in the project library
      if ((projectDir != null && library.startsWith(projectDir.getPath())) ||
          library.length() == 0)
      {
         return PackageLibraryType.Project;
      }
      else if (library.startsWith(FileSystemItem.HOME_PATH))
      {
         return PackageLibraryType.User;
      } 
      else
      {
         return PackageLibraryType.System;
      }
   }
         
   public static String nameOfLibraryType(PackageLibraryType type)
   {
      if (type == PackageLibraryType.Project)
         return "Packrat Library";
      else if (type == PackageLibraryType.User)
         return "User Library";
      else if (type == PackageLibraryType.System)
         return "System Library";
      return "Library";
   }
   
   public static String getLibraryDescription (Session session, String library)
   {
      return nameOfLibraryType(typeOfLibrary(session, library));
   }
}