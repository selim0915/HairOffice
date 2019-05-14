package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.BranchDao;
import kr.or.bit.dto.BranchDto;

public class BranchnoSearchService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
        JSONObject jsonObject = new JSONObject();
        
        JSONArray branchjsonarrlist = new JSONArray();
		int branchid = Integer.parseInt(request.getParameter("branchID"));
		System.out.println(branchid);	
		try {
			
  			BranchDao branchdao = new BranchDao();
  		  	List<BranchDto> branchlist = branchdao.getBranchList();

  		    for(int i = 0; i < branchlist.size(); i++) {
				BranchDto b = branchlist.get(i);
		        //deptno의 한명 정보가 들어갈 JSONObject 선언
		        JSONObject branchinfo = new JSONObject();
		        
		        branchinfo.put("MAP_X", b.getMap_X());		        
		        branchinfo.put("MAP_Y", b.getMap_Y());
		        
		        
		        
		        System.out.println("데이터 : " + branchinfo);
			}
  		    
			/*
			 * jsonObject.put("branchlist", branchjsonarrlist);
			 * branchjsonarrlist.add(jsonObject);
			 */
			
  		  	
  		  	 request.setAttribute("branchlist", jsonObject);
		  		  
  		  	 forward = new ActionForward();
  			 forward.setRedirect(false); //forward 방식
  			 forward.setPath("/BranchnoSearch.jsp");

		  	}catch(Exception e){
		  		System.out.println(e.getMessage());
		  	}
		return forward;
	}

}
