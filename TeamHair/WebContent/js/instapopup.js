$(document).ready(
		function() {
			$('.openBtn').click(
					function(e) {
						setTimeout(function() {
							$('.popup').removeClass('animationClose').addClass(
									'animationOpen');
						}, 100);
						
						$('.obscure').fadeIn(50);
						e.preventDefault();
						
						
						var sessionid = "${sessionScope.usersdto.userId}"; //var sessionid = ''
						if($('#test').hasClass('far fa-heart') == true){
							$.ajax({
								url : "Instalike.insta",
								data : {
									photoid : "1",
									userid : sessionid,
									likeyn : "n",
									wasuser : "y"
								},
								type : "GET",
								dataType : "json",
								success : function(data) {
									if(data.likeyn == "y"){
										$('#test').removeClass('far fa-heart');
										$('#test').addClass('fas fa-heart');
									}
									
								}
							});
						}else if($('#test').hasClass('fas fa-heart') == true){
						}
						
					});
			

			$('.btnclose').click(
					function(e) {
						e.preventDefault();
						setTimeout(function() {
							$('.obscure').fadeOut(350);
						}, 50);
						$('.popup').removeClass('animationOpen').addClass(
								'animationClose');
					});
		});