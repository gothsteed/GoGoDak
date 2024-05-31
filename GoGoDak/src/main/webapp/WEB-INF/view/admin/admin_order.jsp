<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="../header_admin.jsp" />

<style type="text/css">
    table#memberTbl {
        width: 90%;
        margin: 0 auto;
    }
    table#memberTbl th {
        text-align: center;
        font-size: 14pt;
    }
    table#memberTbl tr.memberInfo:hover {
        cursor: pointer;
    }
    form[name="member_search_frm"] {
        width: 90%;
        margin: 0 auto 2% auto;
    }
    form[name="member_search_frm"] button.btn {
        margin-left: 2%;
    }
    div#pageBar {
        width: 90%;
        margin: 3% auto 0 auto;
        display: flex;
    }
    div#pageBar > nav {
        margin: auto;
    }
    .my.pagination > .active > a, 
    .my.pagination > .active > span, 
    .my.pagination > .active > a:hover, 
    .my.pagination > .active > span:hover, 
    .my.pagination > .active > a:focus, 
    .my.pagination > .active > span:focus {
        background: #fbc02d;
        border-color: #fbc02d;
    }
    .page-link{
        color: #212529;
    }
</style>

<script type="text/javascript">
    $(document).ready(function(){
        if("${requestScope.searchType}" != "" && "${requestScope.searchWord}" != "" ){ 
            $("select[name='searchType']").val("${requestScope.searchType}");    
            $("input:text[name='searchWord']").val("${requestScope.searchWord}");
        }
        
        if("${requestScope.sizePerPage}" != ""){ 
            $("select[name='sizePerPage']").val("${requestScope.sizePerPage}");    
        }
        
        $("input:text[name='searchWord']").bind("keydown", function(e){
            if(e.keyCode == 13){
                goSearch();
            }
        });
        
        $("select[name='sizePerPage']").bind("change", function(){ 
            const frm = document.member_search_frm;
            frm.action = "order.dk";
            frm.method = "get";
            frm.submit();
        });
        
        $("table#memberTbl tr.memberInfo").click( e => {
            const order_seq = $(e.target).parent().children(".order_seq").text(); 
            
            const frm = document.memberOneDetail_frm;
            frm.order_seq.value = order_seq;
            frm.action = "${pageContext.request.contextPath}/member/member_orderdetail.dk";
            frm.method = "post";
            frm.submit();
        });
    });

    function goSearch(){
        const searchType = $("select[name='searchType']").val();
        
        if(searchType == ""){ 
            alert("검색대상을 선택하세요!!");
            return; 
        }
        
        const frm = document.member_search_frm;
        frm.action = "order.dk";
        frm.method = "get";
        frm.submit();
    }
</script>

<div class="container" style="padding: 3% 0;">
    <h2 class="text-center mb-5"><i class="fa-solid fa-quote-left"></i>&nbsp;&nbsp;주문 확인 목록&nbsp;&nbsp;<i class="fa-solid fa-quote-right"></i></h2>
   
    <form name="member_search_frm" class="row justify-content-between" style="align-items: center;">
        <div class="col-6">
            <select name="searchType" style="padding: 3px;">
                <option value="">검색대상</option>
                <option value="name">회원명</option>
                <option value="id">아이디</option>
            </select>
            &nbsp;
            <input type="text" name="searchWord" style="padding: 2px;" /> 
            <input type="text" style="display: none;" />
          
            <button type="button" class="btn btn-secondary" onclick="goSearch()">검색</button>
        </div>
        
        <div class="col-3">
            <span style="font-size: 13pt; font-weight: bold;">페이지당 회원명수&nbsp;&nbsp;</span>
            <select name="sizePerPage" style="padding: 3px;">
                <option value="10">10명</option>
                <option value="5">5명</option>
                <option value="3">3명</option>      
            </select>
        </div>
    </form>

    <table class="table table-bordered table-hover" id="memberTbl">
        <thead style="background: #fbc02d; color: #fff">
            <tr>
                <th>번호</th>
                <th>아이디</th>
                <th>회원명</th>
                <th>주소</th>
                <th>배송상태</th>
            </tr>
        </thead>
        
        <tbody>
            <c:if test="${not empty requestScope.orderList}">
                <c:forEach var="ordervo" items="${requestScope.orderList}" varStatus="status" >
                    <tr class="memberInfo">
                        <td class="order_seq">${ordervo.order_seq}</td>    
                        <td class="id">${ordervo.id}</td>
                        <td>${ordervo.name}</td>
                        <td>${ordervo.address}</td>
                        <td>
                            <c:choose>
                                <c:when test="${ordervo.deliverystatus == 0}">미출고</c:when>
                                <c:when test="${ordervo.deliverystatus == 1}">출고</c:when>
                                <c:when test="${ordervo.deliverystatus == 2}">배송중</c:when>
                                <c:when test="${ordervo.deliverystatus == 3}">배송완료</c:when>
                                <c:otherwise>알 수 없음</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>    
            
            <c:if test="${empty requestScope.orderList}">
                <tr>
                    <td colspan="5">데이터가 존재하지 않습니다.</td>
                </tr>
            </c:if>    
        </tbody>
    </table>     

    <div id="pageBar">
        <nav>
            <ul class="pagination my">${requestScope.pageBar}</ul>
        </nav>
    </div>
</div>

<form name="memberOneDetail_frm">
    <input type="hidden" name="order_seq" />
    <input type="hidden" name="goBackURL" value="${requestScope.currentURL}" />
</form>

