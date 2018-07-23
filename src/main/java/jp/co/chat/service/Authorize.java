/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package jp.co.chat.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import jp.co.chat.common.GoogleOauth2;
import jp.co.chat.model.Info;

public class Authorize extends HttpServlet {

	/**
	 * 初回認証呼び出し
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		// 未ログイン(SSO)の場合
		// Googleログインを行っていてもSSOでログインを行わないと情報が正しく取得できない
		UserService userService = UserServiceFactory.getUserService();
		if (!userService.isUserLoggedIn()) {
			response.sendRedirect(userService.createLoginURL(request.getRequestURI()));;
			return;
		}

		GoogleOauth2 goauth2 = null;
		try {
			goauth2 = new GoogleOauth2();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}

		//Datastoreにheader認証、email、spreadsheetidを登録
		Info info = new Info();
		info.setKey(Datastore.createKey(Info.class, "info"));
		if (Datastore.getOrNull(info.getKey()) == null) {
			info.setHeader("sample");
			info.setAddress("sample");
			info.setSheetId("sample");
			Datastore.put(info);
		}
		response.sendRedirect(goauth2.authorize());


	}
}
