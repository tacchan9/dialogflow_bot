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
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import jp.co.chat.common.GoogleOauth2;
import jp.co.chat.model.RefreshToken;

public class Callback extends HttpServlet {

	private Logger logger = Logger.getLogger(Callback.class.getName());

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		GoogleOauth2 goauth2 = null;

		try {
			goauth2 = new GoogleOauth2();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}

		// リフレッシュトークンの取得
		String refreshTokenCd = goauth2.oauth2CallBack(request.getParameter("code"));
		logger.info("refreshTokenCd : " + refreshTokenCd);

		// メールアドレスの取得
		UserService service = UserServiceFactory.getUserService();
		User usr = service.getCurrentUser();
		String email = usr.getEmail();


		// DataStoreへの書き込み
		RefreshToken refreshToken = new RefreshToken();

		Key key = Datastore.createKey(RefreshToken.class, email);
		refreshToken.setKey(key);
		refreshToken.setRefreshToken(refreshTokenCd);

		Datastore.put(refreshToken);

		response.setContentType("text/plain");
		response.getWriter().println(refreshTokenCd);

	}

}
