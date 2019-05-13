package kr.or.bit.service;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.QnADao;
import kr.or.bit.dto.QnADto;

public class QnAinsertservice implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		System.out.println("uploadpath  " + uploadpath);
		
		int size = 1024*1024*10;	//10M 네이버 계산기
		int result;
		
		MultipartRequest multi;
		try {
			QnADao dao = new QnADao();
			multi = new MultipartRequest(request,uploadpath, size, "UTF-8", new DefaultFileRenamePolicy());
			Enumeration filenames = multi.getFileNames();
			
			System.out.println("multi 확인 : ");
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa" + multi.getParameter("userid"));
			
			
			String userID = multi.getParameter("userid");
			String boardSubject = multi.getParameter("boardsubject"); 
			String boardContent = multi.getParameter("boardcontent");
			
			QnADto qna = new QnADto();
			
			qna.setUserID(userID);
			qna.setBoardSubject(boardSubject);
			qna.setBoardContent(boardContent);
			
			
			System.out.println(qna.toString());
			
			String file = (String)filenames.nextElement();
			String filename = multi.getFilesystemName(file);
			System.out.println("iiiiiiiiiiiiiiiiiiiiiiii 파일이름 : " + filename);
			qna.setFileName(filename);
						
			result = dao.insertQnA(qna);

			if (result > 0) {
				System.out.println("등록성공");
			} else { // -1 (제약, 컬럼길이 문제)
				System.out.println("등록실패");
			}
			

			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/QnA.do"); //리스트
			
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		
		
		
		
		
//		String userID = request.getParameter("userid");
//		String boardSubject = request.getParameter("boardsubject"); 
//		String boardContent = request.getParameter("boardcontent");
//		String fileName = request.getParameter("filename");
////		
////		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
////		System.out.println("uploadpath  " + uploadpath);
////		
////		int size = 1024*1024*10;	//10M 네이버 계산기
////		MultipartRequest multi;		
////		
////		
////				
////		
//		
//		
//		int result = 0;
//		System.out.println("service 받아온 값을 봅시다.");
//		System.out.println("userid : " + userID);
//		System.out.println("subject : " + boardSubject);
//		System.out.println("content : " + boardContent);
//		System.out.println("filename : " + fileName);
//		
//	
//		try {
//			request.setCharacterEncoding("UTF-8");	
//			
//			QnADto qna = new QnADto();
//			
//			qna.setUserID(userID);
//			qna.setBoardSubject(boardSubject);
//			qna.setBoardContent(boardContent);
//			qna.setFileName(fileName);
//			
//			
//			System.out.println(qna.toString());
//			
//			QnADao dao = new QnADao();
//			result = dao.insertQnA(qna);
//
//			if (result > 0) {
//				System.out.println("등록성공");
//			} else { // -1 (제약, 컬럼길이 문제)
//				System.out.println("등록실패");
//			}
//			
//
//			forward = new ActionForward();
//			forward.setRedirect(false);
//			forward.setPath("/QnA.do"); //리스트
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return forward;
	}

}
