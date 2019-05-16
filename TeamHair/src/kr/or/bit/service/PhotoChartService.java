package kr.or.bit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionForward;
import kr.or.bit.dao.ChartDao;
import kr.or.bit.dao.QnADao;
import kr.or.bit.dto.ChartPhotoDto;

public class PhotoChartService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
        JSONObject jsonObject = new JSONObject();

        JSONArray photolist = new JSONArray();
		try {
  		  	ChartDao dao = new ChartDao();
  		  	List<ChartPhotoDto> list = dao.photolist();
  		  	
  		  	for(int i = 0; i < list.size(); i++) {
  		  		ChartPhotoDto dto = list.get(i);
  		        //deptno의 한명 정보가 들어갈 JSONObject 선언
  		        JSONObject photoinfo = new JSONObject();
  		        photoinfo.put("photoid", dto.getPhotoid());
  		        photoinfo.put("likecount", dto.getLikecount());
  		        
  		        photolist.add(photoinfo);
  			}
  			jsonObject.put("photolist", photolist);

    		  	
    		request.setAttribute("photolist", jsonObject);
		  		  
  		  	forward = new ActionForward();
  			forward.setRedirect(false);
  			forward.setPath("/WEB-INF/chart/likephoto_list.jsp");

		  	}catch(Exception e){
		  		System.out.println(e.getMessage());
		  	}
		return forward;
	}

}
