/*
 * ShinyAppsConnectAccount.java
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
package org.rstudio.studio.client.shiny.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;

public class ShinyAppsConnectAccount extends Composite
{

   private static ShinyAppsConnectAccountUiBinder uiBinder = GWT
         .create(ShinyAppsConnectAccountUiBinder.class);

   interface ShinyAppsConnectAccountUiBinder extends
         UiBinder<Widget, ShinyAppsConnectAccount>
   {
   }

   public ShinyAppsConnectAccount()
   {
      initWidget(uiBinder.createAndBindUi(this));
      accountInfo.addKeyUpHandler(new KeyUpHandler()
      {
         @Override
         public void onKeyUp(KeyUpEvent event)
         {
            if (onAccountInfoChanged_ != null)
            {
               onAccountInfoChanged_.execute();
            }
         }
      });
   }
   
   public void setOnAccountInfoChanged(Command cmd)
   {
      onAccountInfoChanged_ = cmd;
   }
   
   public String getAccountInfo()
   {
      return accountInfo.getText();
   }

   @UiField
   TextArea accountInfo;
   
   private Command onAccountInfoChanged_;
}