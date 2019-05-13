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
import kr.or.bit.service.CommentsAllSeslectService;
import kr.or.bit.service.CommentsInsertService;
import kr.or.bit.service.InstaLikeListService;
import kr.or.bit.service.InstaLikeService;
import kr.or.bit.service.LoginOkService;
import kr.or.bit.service.ModifyLikeService;
import kr.or.bit.service.PhotoAllSelectService;

@WebServlet("*.insta")
public class InstaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InstaController() {
        super();
    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String urlCommand = requestURI.substring(contextPath.length());
	
    	Action action = null;
    	ActionForward forward = new ActionForward(); 
    	
    	if(urlCommand.equals("/Instalike.insta")) { // insert + select
    		try {
    			System.out.println("접속 완료");
    			action = new InstaLikeService();
    			forward = action.execute(request, response);
			}catch(Exception e) {
					e.printStackTrace();
			}
    	} else if(urlCommand.equals("/InstalikeYn.insta")) { // 좋아요 update
    		try {
    			action = new ModifyLikeService();
    			forward = action.execute(request, response);
			}catch(Exception e) {
					e.printStackTrace();
			}
    	} else if(urlCommand.equals("/Instalikes.insta")) { // 좋아요 select
    		try {
    			action = new InstaLikeListService();
    			forward = action.execute(request, response);
			}catch(Exception e) {
					e.printStackTrace();
			}
    	} else if(urlCommand.equals("/Insta.insta")) { // 로그인 OK
    		try {
    			action = new PhotoAllSelectService();
    			forward = action.execute(request, response);
			}catch(Exception e) {
					e.printStackTrace();
			}
    	} else if(urlCommand.equals("/Instacomment.insta")) { // 로그인 OK
    		try {
    			action = new CommentsInsertService();
    			forward = action.execute(request, response);
			}catch(Exception e) {
					e.printStackTrace();
			}
    	} else if(urlCommand.equals("/Instapopup.insta")) { // 로그인 OK
    		try {
    			action = new CommentsAllSeslectService();
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
