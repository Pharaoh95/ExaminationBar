package com.bees.teachers.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.bees.projectConstant;
import com.bees.teachers.service.TeacherService;

import cn.itcast.servlet.BaseServlet;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class pushServlet extends BaseServlet {
	// private Logger LOG = LoggerFactory.getLogger(messagePushServlet.class);
	private JPushClient jpushClient = new JPushClient(projectConstant.masterSecret, projectConstant.appKey);
	private TeacherService teacherService = new TeacherService();

	public void pushForOne(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		// 请求参数解析
		String teacherId = request.getParameter("teacherId");
		String teacherName = request.getParameter("teacherName");
		if (teacherId == null || teacherName == null) {
			writer.print("params Error!");
			return;
		}
		// 极光推送API
		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.all())
				.setAudience(Audience.alias(teacherId))
				.setNotification(Notification.alert(teacherName + "老师您好，考务信息已到达，请注意查阅")).build();
		try {
			PushResult result = jpushClient.sendPush(payload);
			System.out.println("Got result: " + result);
			writer.print("向 " + teacherName + "(" + teacherId + ") 发送消息成功");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			writer.print("push client Error!");
		}
	}

	public void pushBatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int status = Integer.parseInt(request.getParameter("status"));
		PrintWriter writer = response.getWriter();
		if (status == 2) {
			PushPayload.alertAll(projectConstant.ALERT);
			writer.print("已向所有用户发送APP消息！");
			return;
		}
		writer.print("TODO");
	}

	public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter writer = response.getWriter();
		String emailAdr = (String) request.getSession().getAttribute("email");
		String teacherName = (String) request.getSession().getAttribute("teacherName");
		String content = request.getParameter("content");
		if ("".equals(content.trim())) {
			writer.print("内容不能为空");
			return;
		}
		System.out.println(content);
		try {
			SimpleEmail email = teacherService.getEmailObj();
			email.addTo(emailAdr);
			email.setMsg(content);
			email.send();
		} catch (EmailException e) {
			System.out.println(e.getMessage());
			writer.print("Email Server Error!");
		}
		writer.print("邮件发送成功！");
	}
}
