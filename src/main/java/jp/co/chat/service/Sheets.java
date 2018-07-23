package jp.co.chat.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slim3.datastore.Datastore;
import org.slim3.repackaged.org.json.JSONException;
import org.slim3.repackaged.org.json.JSONObject;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.appengine.api.datastore.Key;

import jp.co.chat.common.GoogleOauth2;
import jp.co.chat.model.Info;
import jp.co.chat.model.RefreshToken;









public class Sheets extends HttpServlet{

	private Logger log = Logger.getLogger(Sheets.class.getName());

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");

		//必要な情報の取得
		Key info = Datastore.createKey(Info.class, "info");
		Info infoData = Datastore.get(Info.class, info);
		String header = infoData.getHeader();
		String address = infoData.getAddress();
		String sheetId = infoData.getSheetId();

		//応答情報
		JSONObject responseJson = new JSONObject();
		String answer = "分かりません。";

		//Oauth認証
		GoogleOauth2 goauth2 = null;
		try {
			goauth2 = new GoogleOauth2();
		} catch (GeneralSecurityException e) {
			log.warning(e.toString());
			e.printStackTrace();
		}

		String email = address;

		Key key = Datastore.createKey(RefreshToken.class, email);
		RefreshToken rt = Datastore.get(RefreshToken.class, key);

		Credential credential = goauth2.getCredetionl(rt.getRefreshToken());

		com.google.api.services.sheets.v4.Sheets service = goauth2.getSheetsService(credential);

		try {
			//ヘッダー認証
			if (!request.getHeader("Authorization").equals(header)) {

				answer = "ヘッダー認証情報が一致しません。";
				return;

			} else {

				//スプレッドシート処理
				//json取り扱えるようにする処理
				BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"UTF-8"));
				StringBuilder sb = new StringBuilder();
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}

				String spreadsheetId = sheetId;
				String intents = "";

				JSONObject json = null;
				json = new JSONObject(sb.toString());
				intents = json.getJSONObject("result")//API ver1
						//json.getJSONObject("result")//API ver2
						.getString("action");

				String range = intents + "!A1:Z1000";
				log.info(range);
				ValueRange res = service.spreadsheets().values()
						.get(spreadsheetId, range)
						.execute();

				List<List<Object>> ss = res.getValues();
				if (ss == null || ss.size() == 0) {
					//返答内容："答えが分かりません"
				} else {
					json = new JSONObject(sb.toString());
					//ssからパラメータを取得
					List<String> param = new ArrayList<String>();
					for (int i = 0; i < ss.get(0).size(); i++) {
						List<Object> element = ss.get(0);

						//dialogflowから渡ってきたパラメータを配列に格納
						param.add(
								json.getJSONObject("result")//API ver1
								//json.getJSONObject("queryResult")//API ver2
								.getJSONObject("parameters")
								.getString(element.get(i).toString())
								);
					}

					//回答を見つける
					for (List row : ss) {
						boolean flg = false;

						for (int i=0; i < param.size(); i++) {
							if (row.get(i).toString().equals(param.get(i))) {
								log.info(row.get(i).toString());
								if (i == param.size()-1) {
									flg = true;
									break;
								}
							} else {
								break;
							}
						}

						//パラメータと合致した場合
						if ( flg ) {
							answer = row.get(param.size()).toString();
							break;
						}
					}
				}

			}

		} catch (Exception e) {

			// TODO 自動生成された catch ブロック
			log.warning(e.toString());
			e.printStackTrace();
			answer = "スプレッドシート情報が正しく取得できません。";

		} finally {

			//返答
			try {

				responseJson.put("displayText", answer);//API ver1
				responseJson.put("speech", answer);//API ver1
				//responseJson.put("fulfillmentText", answer);//API ver2
				PrintWriter out = response.getWriter();
				out.println(responseJson);
				log.info(responseJson.toString());

			} catch (JSONException e) {

				// TODO 自動生成された catch ブロック
				log.warning(e.toString());
				e.printStackTrace();

			}

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		doGet(request, response);

	}
}
