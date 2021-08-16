package com.fabbi.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fabbi.entity.Member;
import com.fabbi.service.MemberServiceImpl;
import com.fabbi.util.Helper;


@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Helper helper;
	private MemberServiceImpl memberService;

	public void init(){
		helper = Helper.getInstance();
		memberService = MemberServiceImpl.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action == null) {
			action = "";
		}
		
		switch(action) {
		case "new":
			response.sendRedirect("create.jsp");
			break;
		case "edit":
			try {
				goUpdate(request, response);
			} catch (ServletException | IOException | ParseException e) {
				e.printStackTrace();
			}
			break;
		case "delete":
			deleteMember(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		default:
			showMember(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action == null) {
			action = "";
		}
		
		switch(action) {
		case "new":
			try {
				createMember(request, response);
			} catch (ServletException | IOException | ParseException e) {
				e.printStackTrace();
			}
			break;
		case "edit":
			try {
				updateMember(request, response);
			} catch (ServletException | IOException | ParseException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	private void showMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Member> memberList = memberService.getAll();
		request.setAttribute("memberList", memberList);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
	
	private void deleteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Member member = memberService.getById(id);
		boolean result = memberService.delete(member);
		if (result) {
			response.sendRedirect("/MemberManagement/home");
		}
	}
	
	private void createMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String username = request.getParameter("username_create");
		String passwordRaw = request.getParameter("password_create");
		String password = helper.encrypt(passwordRaw);
		String name = request.getParameter("name_create");
		String dobRaw = request.getParameter("dob_create");
		Date date = format.parse(dobRaw);
		String email = request.getParameter("email_create");
		String phone = request.getParameter("phone_create");
		String address = request.getParameter("address_create");
		
		Member member = new Member(username, password, name, date, email, phone, address);
		boolean result = memberService.add(member);
		if (result) {
			showMember(request, response);
		}
	}
	
	private void goUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		int id = Integer.parseInt(request.getParameter("id"));
		Member member = memberService.getById(id);
		
		request.setAttribute("memberUpdate", member);
		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}
	
	private void updateMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
		int id = Integer.parseInt(request.getParameter("id_update"));
		Member member = memberService.getById(id);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String name = request.getParameter("name_create");
		String dobRaw = request.getParameter("dob_create");
		Date date = format.parse(dobRaw);
		String email = request.getParameter("email_create");
		String phone = request.getParameter("phone_create");
		String address = request.getParameter("address_create");
		
		member.setName(name);
		member.setDob(date);
		member.setEmail(email);
		member.setPhone(phone);
		member.setAddress(address);
		
		boolean result = memberService.update(member);
		if (result) {
			showMember(request, response);
		}
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect("login.jsp");
	}
}
