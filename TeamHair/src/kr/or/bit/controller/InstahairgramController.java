package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.service.LoginOkService;
import kr.or.bit.service.PhotoAllSelectService;
import kr.or.bit.service.PhotoInsertService;

@WebServlet("*.insta")
public class InstahairgramController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InstahairgramController() {
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String urlCommand = requestURI.substring(contextPath.length());
	
    	Action action = null;
    	ActionForward forward = new ActionForward(); 
    	
    	if(urlCommand.equals("/InstaGallery.insta")) { // 인스타 전체 보기
    		try {
    			forward.setRedirect(false);
    			forward.setPath("/WEB-INF/branch/hairgallery.jsp");
			}catch(Exception e) {
					e.printStackTrace();
			}
    	} else if(urlCommand.equals("/InstaBlog.insta")) { // 인스타 개인 블로그 전체 보기
    		try {
    			action = new PhotoAllSelectService();
    			forward = action.execute(request, response);
			}catch(Exception e) {
					e.printStackTrace();
			}
    	} else if(urlCommand.equals("/instraWrite.insta")) { // 인스타 글쓰기 폼 이동
    		try {
    			forward.setRedirect(false);
    			forward.setPath("/WEB-INF/instahairgram/instragramWrite.jsp");
			}catch(Exception e) {
					e.printStackTrace();
			}
    	} else if(urlCommand.equals("/instaWriteOk.insta")) { // 인스타 글쓰기 폼 작성
    		try {
    			action = new PhotoInsertService();
    			forward = action.execute(request, response);
			}catch(Exception e) {
					e.printStackTrace();
			}
    	} 
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { //true
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
