<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
<c:set var="photolist" value="${requestScope.photolist}" />
<c:set var="commentslist" value="${requestScope.commentslist}" />
<c:set var="likecount" value="${requestScope.likecount }" />
<c:set var="userlist" value="${requestScope.userid }" />
<c:set var="profilelist" value="${requestScope.profilelist }" />

<fmt:formatDate var="photodate" value="${photolist.updateDate}" pattern="yyyy년MM월dd일 HH:mm"/>

<div class="popup animationClose">
	<div class="col-lg-8 col-md-7 col-xs-7 col-sm-7 imgsession">
		<img src="./upload/${photolist.fileName }" alt="프로필 업로드 사진">
	</div>

	<div class="col-lg-4 col-md-5 col-xs-5 col-sm-5 contentsession">
		<div class="insta-right">
			<div class="instagram-card">
				<div class="">
					<a class="btnclose" href="#" onclick="btnclose();return false"><i
						class="fas fa-times"></i></a>
				</div>
				<div class="instagram-card-header">
					<img class="instagram-card-user-image rounded-circle"
						src="./upload/${profilelist.photoName}"> <a class="instagram-card-user-name"
						href="http://localhost:8090/TeamHair/Instauserid.insta?userid=${userlist.userId }" target="_blank">${userlist.userId }</a>
					<div class="btn-follow">
						<a class="reservepopup1" href="GangnamBranchInfo.brh"><span>ㆍ예약하기</span></a>
					</div>
					
					<!-- 
					<div class="btn-delete">
						<a href="#" onclick="deletephoto(${photolist.photoId });return false;"><i class="fas fa-trash"></i></a>
					</div>
					 -->
				</div>
				

				<div class="instagram-card-content">

					<p>
						<a class="user-comments"
							href="http://localhost:8090/TeamHair/Instauserid.insta?userid=${userlist.userId }">${userlist.userId }
						</a>${photolist.description}
					</p>
					<p class="comments">${photodate }</p>
					<div class="comment-append" id="comment-append">
						<c:forEach var="i" begin="0" end="${commentslist.size()}">
						<fmt:formatDate var="commentsdate" value="${commentslist[i].updateDate}" pattern="yyyy년MM월dd일 HH:mm"/>
							<p>
								<a class="user-comments" href="http://localhost:8090/TeamHair/Instauserid.insta?userid=${commentslist[i].userId}">${commentslist[i].userId}</a>
								${commentslist[i].comments}
							</p>
							<p class="comments">${commentsdate }</p>
						</c:forEach>
					</div>
				</div>
				<a class="instagram-card-icon" href="#"
					onclick="like(${photolist.photoId });return false;"><i
					id="test" class="far fa-heart"></i></a> <a class="instagram-card-icon"
					href="#" onclick="commentfocus();return false;"><i
					class="far fa-comment"></i></a>


				<p id="likecount">
					<a class="user-comments" href="#"> 좋아요 ${likecount }개</a>
				</p>
				<hr />

				<div class="instagram-card-footer">
					<input class="instagram-card-input" type="text"
						placeholder="댓글 달기..."> <a class="instagram-card-icon"
						href="#" onclick="comments(${photolist.photoId});return false;"><i
						class="fas fa-feather"></i></a>
				</div>
			</div>
		</div>

	</div>
</div>
