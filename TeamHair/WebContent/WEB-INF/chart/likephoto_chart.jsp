<%@page import="kr.or.bit.dto.ChartUserDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="/WEB-INF/common/header.jsp"></jsp:include>
<meta charset="UTF-8">
<title>Insert title here</title>


 <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<style type="text/css">
	#charcontainer {	
		margin: 3% 10%;
	}
</style>
<div id="charcontainer">
	<div id="container" style="min-width: 300px; height: 400px; margin: 0 auto">
	</div>
</div>

<script type="text/javascript">
	$(function(){
		var data1 = new Array();
		$.getJSON("LikePhotoAsyncChart.cht", function (data) {
			console.log(data);
			var list = data.photolist;
			console.log(list);
			$.each(list, function (index, value) {
				data1[index] = ['<a href="Instapopup.insta?photoid=' + parseInt(value.photoid) + '">' + parseInt(value.photoid) + '</a>', parseInt(value.likecount)];
				console.log(data1);
			});
	
	
	Highcharts.chart('container', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: 'TOP10 인기사진'
	    },
	    xAxis: {
	        type: 'category',
	        labels: {
	            rotation: 0,
	            style: {
	                fontSize: '13px',
	                fontFamily: 'Verdana, sans-serif'
	            }
	        }
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: '좋아요 갯수'
	        }
	    },
	    legend: {
	        enabled: false
	    },
	    tooltip: {
	        pointFormat: 'Population in 2017: <b>{point.y:.1f} millions</b>'
	    },
	    series: [{
	        name: 'Population',
	        data: data1,
	        dataLabels: {
	            enabled: true,
	            rotation: 0,
	            color: '#000000',
	            align: 'center',
	            y: 10, // 10 pixels down from the top
	            style: {
	                fontSize: '15px',
	                fontFamily: 'Verdana, sans-serif'
	            }
	        }
	    }]
	});
		});
	});
	</script>
</head>
<body>

</body>
<jsp:include page="/WEB-INF/common/footer.jsp"></jsp:include>
</html>