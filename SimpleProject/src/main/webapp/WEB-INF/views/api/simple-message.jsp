<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문자에요</title>
<style>
	#wrap{
		width : 1000px;
		min-height : 400px;
		margin : auto;
		height : auto;
		border : 1px solid rgba(0, 0, 0, 0.6);
		border-radius : 16px;
		background-color : rgba(16, 16, 16, 0.8);
		padding : 20px;
	}
	#wrap > div{
		width : 100%;
	}
	#content{
		width : 90%;
		margin : auto;
		height : auto;
		min-height : 300px;
	}
	button{
		width : 100%;
	}
</style>
</head>
<body>
	<jsp:include page="../include/header.jsp" />
		
	<div id="wrap">
		
		<div id="content">
		
		</div>
		<div>
			<button class="btn btn-outline-secondary" onclick="getmessage();">더보기 ▽</button>
		</div>
	</div>
	
	<script>
		$(function(){
			getMessage();
		});
		let pageNo = 1;
		function getMessage(){
			$.ajax({
				url : `message?pageNo=\${pageNo}`,
				type : 'get',
				success : result => {
					// console.log(result);
					const messages = result.body;
					
					const outputStr = messages.map(e => 
												   `
												   <div class="message"
												    <h3 class="category">\${e.DST_SE_NM}</h3>]
												   	<p class="content">
														\${e.MSG_CN}
													</p>
													<h6 class="region">\${e.RCPTN_RGN_NM}</h6>
												   </div>
												   `
												   ).join('');
					$('#content').append(outputStr);
					pageNo++;
				}
				
			});
		}
	</script>
	
	
	<jsp:include page="../include/footer.jsp" />
</body>
</html>