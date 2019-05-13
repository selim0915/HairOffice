package kr.or.bit.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.QnADao;
import kr.or.bit.dto.QnADto;

public class QnAupdateservice implements Action{

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
		
			int boardID = Integer.parseInt(multi.getParameter("boardid"));
			String boardSubject = multi.getParameter("boardsubject"); 
			String boardContent = multi.getParameter("boardcontent");
			String fileName = multi.getParameter("filename");		
			String userID = multi.getParameter("userid");
	
			QnADto qna = new QnADto();
			
			qna.setUserID(userID);
			qna.setBoardSubject(boardSubject);
			qna.setBoardContent(boardContent);
			qna.setFileName(fileName);
			
			
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

		return forward;
	}

}
