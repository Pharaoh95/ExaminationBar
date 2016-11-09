package com.bees.teachers.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bees.teachers.dao.TeacherDao;

import cn.itcast.servlet.BaseServlet;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class messagePushServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected static final Logger LOG = LoggerFactory.getLogger(messagePushServlet.class);
	public static final String ALERT = "您的考务信息！";
	public static final String TITLE = "推送信息！";
	public static final String masterSecret = "3fc7b758e4750ffa6ce47c1e";
	public static final String appKey = "b08e71701ba549afae3b3566";
	public PushPayload payload = null;

	public void PunishAllorOneBytag(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// -----------全部推送

		// 创建JPushClient
		JPushClient jpushClient = new JPushClient(masterSecret, appKey);
		// 构建推送对象
		String teacherid = "";
		try {
			teacherid = new String(request.getParameter("teacherid").getBytes("iso-8859-1"), "utf-8");
		} catch (NullPointerException e) {
			payload = buildPushObject_all_all_alert();
		}
		if (teacherid != "") {
			payload = buildPushObject_all_alias_alert("123122");
		}
		// payload =
		// buildPushObject_android_tag_alertWithTitle(request.getParameter("teacherid"));

		try {
			PushResult result = jpushClient.sendPush(payload);
			LOG.info("Got result - " + result);

		} catch (APIConnectionException e) {
			// Connection error, should retry later
			// update .. set qulified_status=0
			LOG.error("Connection error, should retry later", e);

		} catch (APIRequestException e) {
			// Should review the error, and fix the request
			LOG.error("Should review the error, and fix the request", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
		}

	}

	public static PushPayload buildPushObject_all_all_alert() {
		return PushPayload.alertAll(ALERT);
	}

	public static PushPayload buildPushObject_all_alias_alert(String teacherid) {
		return PushPayload.newBuilder().setPlatform(Platform.all()).setAudience(Audience.alias(teacherid))
				.setNotification(Notification.alert(teacherid + "老师您好，考务信息已到达，请注意查收")).build();
	}

}
