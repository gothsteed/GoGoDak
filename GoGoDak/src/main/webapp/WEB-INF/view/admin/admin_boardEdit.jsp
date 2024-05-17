<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
    //    /GoGoDak
%>
   
  <style type="text/css">
  
.flex-row {
	display: flex;
	flex-direction: row;
}  
  
  
 .board_wrap {
  width: 1000px;
  margin: 100px auto;
  background-color: #f8f9fa;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.board_title {
  margin-bottom: 20px;
  text-align: center;
}

.board_title strong {
  font-size: 2.2rem;
  color: #333;
}

.board_title p {
  margin-top: 5px;
  font-size: 1.4rem;
  color: #666;
}

.bt_wrap {
  margin-top: 20px;
  text-align: center;
}

.bt_wrap a {
  display: inline-block;
  min-width: 100px;
  margin-left: 10px;
  padding: 8px 16px;
  border: 1px solid #007bff;
  border-radius: 4px;
  font-size: 1.4rem;
  color: #007bff;
  text-decoration: none;
  transition: background-color 0.3s, color 0.3s;
}

.bt_wrap a:first-child {
  margin-left: 0;
}

.bt_wrap a.on {
  background: #007bff;
  color: #fff;
}

.board_list {
  width: 100%;
  border-top: 1px solid #ccc;
}

.board_list > div {
  border-bottom: 1px solid #ccc;
  font-size: 1.4rem;
}

.board_list > div.top {
  border-bottom: 2px solid #007bff;
}

.board_list > div:last-child {
  border-bottom: none;
}

.board_list > div > div {
  display: inline-block;
  padding: 10px 0;
  text-align: center;
  font-size: 1.4rem;
  color: #333;
}

.board_list > div.top > div {
  font-weight: 600;
}

.board_list .num {
  width: 10%;
  color: #fbc02d;
}

.board_list .title {
  width: 70%; /* Increase width for title */
  text-align: left;
}

.board_list .top .title {
  text-align: center;
}

.board_list .writer,
.board_list .date,
.board_list .count {
  width: 10%;
}

.board_page {
  margin-top: 20px;
  text-align: center;
}

.board_page a {
  display: inline-block;
  width: 32px;
  height: 32px;
  line-height: 32px;
  border: 1px solid #ddd;
  border-left: 0;
  text-align: center;
  font-size: 1.2rem;
  color: #333;
  text-decoration: none;
}

.board_page a.bt {
  padding-top: 8px;
}

.board_page a.num {
  padding-top: 7px;
  font-size: 1.4rem;
}

.board_page a.num.on {
  border-color: #fbc02d;
  background: #fbc02d;
  color: #fff;
 
}

.board_page a:first-child {
  border-left: 1px solid #ddd;
}

.board_view {
  width: 100%;
  border-top: 1px solid #ccc;
  font-size: 1.4rem;
}

.board_view .title {
  padding: 20px 0;
  border-bottom: 1px dashed #ddd;
  font-size: 2rem;
}

.board_view .info {
  padding: 15px 0;
  border-bottom: 1px solid #ddd;
  font-size: 1.4rem;
}

.board_view .info dl {
  display: inline-block;
  margin: 0 20px;
}

.board_view .info dl::before {
  content: "|";
  margin-right: 20px;
  color: #ccc;
}

.board_view .info dl:first-child::before {
  display: none;
}

.board_view .info dl dt,
.board_view .info dl dd {
  display: inline-block;
}

.board_view .info dl dd {
  margin-left: 5px;
  color: #777;
}

.board_view .cont {
  padding: 15px 0;
  line-height: 160%;
}

.board_write {
  border-top: 1px solid #ccc;
}

.board_write .title,
.board_write .info {
  padding: 15px 0;
}

.board_write .info {
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}

.board_write .cont {
  padding: 15px 0;
}

.board_write .cont textarea {
  width: 100%;
  height: 250px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
}

.board_write .title input[type="text"],
.board_write .info input[type="text"],
.board_write .info input[type="password"] {
  width: calc(50% - 10px); /* Set width to 50% of container width */
  padding: 8px; /* Adjust padding */
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-right: 10px; /* Add margin-right for spacing */
  display: inline-block; /* Display inline */
  vertical-align: middle; /* Align vertically */
  background-color: #fff; /* White background */
}

/* Adjust width for smaller screens */
@media (max-width: 768px) {
  .board_write .title input[type="text"],
  .board_write .info input[type="text"],
  .board_write .info input[type="password"] {
    width: calc(100% - 10px); /* Full width on smaller screens */
    margin-right: 0; /* Remove right margin */
    margin-bottom: 10px; /* Add bottom margin for spacing */
  }
}
      	
</style>
      

<jsp:include page="../header.jsp" />


     <div class="board_wrap">
       <div class="board_title">
         <strong>공지사항</strong>
         <p>공지사항을 안내해드립니다.</p>
       </div>
       <div class="board_write_wrap">
         <div class="board_write">
           <div class="title">
             <dl>
               <dt>제목</dt>
               <dd><input type="text" placeholder="제목 입력" /></dd>
             </dl>
           </div>
         
           <div class="cont">
            <dt>내용</dt> 
            <textarea placeholder="내용 입력"></textarea>
           </div>
         </div>
         
		<%-- 추가사항시작 --%>
		<div class="flex-row">
				<div>
					<input type="file" id="imageInput" name="selectFile" accept="image/*" multiple="multiple" class="form-control">
					<%-- <img src="images/image.jpg" class="img-fluid" />  --%>
				</div>
			
				<div id="imagePreview"></div>
			</div>
		<%-- 추가사항끝 --%>	
		
		
         <div class="bt_wrap">
           <a href="write.html" class="on">수정</a>
           <a href="#">취소</a>
         </div>
       </div>
     </div>
 
<jsp:include page="../footer.jsp" />  




<script type="text/javascript">


const imageInput = document.getElementById('imageInput');
const imagePreview = document.getElementById('imagePreview');

imageInput.addEventListener('change', function(event) {
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function() {
            const imageUrl = reader.result;
            const imageElement = document.createElement('img');
            imageElement.src = imageUrl;
            imageElement.style.maxWidth = '500px'; // 이미지의 최대 너비 지정
            imageElement.style.maxHeight = '500px'; // 이미지의 최대 높이 지정
            imagePreview.innerHTML = ''; // 이미지를 교체하므로 이전 이미지를 삭제
            imagePreview.appendChild(imageElement);
        };
        reader.readAsDataURL(file);
    }
});
</script>