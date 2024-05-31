<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String ctxPath = request.getContextPath();
%>     
<jsp:include page="header.jsp" />

<style type="text/css">
    .company{
        position: relative;
    }
    .gogodak_img{
        position: absolute;
		top: 28%;
        right: 3%;
        z-index: 999;
    }
</style>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=3e40c6a4e83259bd26e2771ad2db4e63"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

$(document).ready(function() {
    // Initial map setup
    var mapContainer = document.getElementById('map');
    var mapOptions = {
        center: new kakao.maps.LatLng(37.556513150417395, 126.91951995383943), // Initial map position (Seoul)
        level: 4	 // Map zoom level
    };
  
    var map = new kakao.maps.Map(mapContainer, mapOptions);

    // 지도 타입 컨트롤을 생성하여 지도에 추가
    var mapTypeControl = new kakao.maps.MapTypeControl();
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
    
    // 줌 컨트롤을 생성하여 지도에 추가
    var zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
    
    // 마커이미지를 기본이미지를 사용하지 않고 다른 이미지로 사용할 경우의 이미지 주소 
    var imageSrc = 'http://localhost:9090/GoGoDak/images/index/gogodak.png';
    
 // 마커이미지의 크기 
    var imageSize = new kakao.maps.Size(34, 39);
      
    // 마커이미지의 옵션. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정한다. 
    var imageOption = {offset: new kakao.maps.Point(15, 39)};

    // 마커의 이미지정보를 가지고 있는 마커이미지를 생성한다. 
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

    // == 마커 생성하기 == //
      var marker = new kakao.maps.Marker({ 
       map: mapobj, 
        position: locPosition, // locPosition 좌표에 마커를 생성 
        image: markerImage     // 마커이미지 설정
      }); 
    
      marker.setMap(mapobj); // 지도에 마커를 표시한다.
    
    // AJAX 요청을 통해 매장 정보를 가져옴
    $.ajax({
        url: '<%= ctxPath %>/storeLocationJSON.dk', // Ensure this is the correct URL
        async: false, // 지도는 비동기 통신이 아닌 동기 통신으로 해야 함
        dataType: 'json',
        success: function(json) {
            if (json.length > 0) {
                // 매장 정보가 있을 경우
                $.each(json, function(index, item) {
                    var position = {};
                    
                    // 인포윈도우에 표시될 내용 설정
                    position.content = "<div class='mycontent'>" +
                                       "  <div class='title'>" +
                                       "    <a href='" + item.manufacturer_url + "' target='_blank'><strong>" + item.manufacturer_name + "</strong></a>" +
                                       "  </div>" +
                                       "  <div class='desc'>" +
                                       "    <span class='address'>" + item.manufacturer_tel + "</span>" +
                                       "  </div>" +
                                       "</div>";
                    
                    position.latlng = new kakao.maps.LatLng(item.lat, item.lng);
                    position.zIndex = item.zIndex;
                    
                    // 마커 생성
                    var marker = new kakao.maps.Marker({ 
                        map: map, 
                        position: position.latlng   
                    }); 

                    // 인포윈도우 생성
                    var infowindow = new kakao.maps.InfoWindow({
                        content: position.content,
                        removable: true,
                        zIndex: position.zIndex
                    });

                    // 인포윈도우를 지도에 표시
                    infowindow.open(map, marker);
                });
            } else {
                console.error('No coordinates found for the location.');
            }
        },
        error: function(xhr, status, error) {
            console.error('AJAX request failed:', error);
        }
    });
});

</script>

<div class="container mt-5 mb-5 company">
    <h2 class="text-center" style="font-family: 'Noto Sans KR', sans-serif;">
        <i class="fa-solid fa-quote-left"></i>&nbsp;&nbsp;고고닭 소개&nbsp;&nbsp;<i class="fa-solid fa-quote-right"></i>
    </h2>
    <table class="table table-striped table-bordered mt-5"> 
        <tbody>
            <tr>
                <th>상점명</th>
                <td>고고닭</td>
            </tr>
            <tr>
                <th>대표이사</th>
                <td>서영학</td>
            </tr>
            <tr>
                <th>대표전화</th>
                <td>02-336-8546~8</td>
            </tr>
            <tr>
                <th>주소</th>
                <td>서울특별시 마포구 월드컵북로 21 풍성빌딩 2,3,4층</td>
            </tr>
            <tr>
                <th>사업자등록번호</th>
                <td>301-87-00296</td>
            </tr>
            <tr>
                <th>통신판매업신고</th>
                <td>2017-서울강남-02608</td>
            </tr>
            <tr>
                <th>호스팅제공</th>
                <td>카페24(주)</td>
            </tr>
        </tbody>
    </table>
    
    <div class="gogodak_img">
        <img src="<%= ctxPath %>/images/index/gogodak.png" width="200" alt="..." />
    </div>

    <div class="container mt-5">
        <ul class="nav nav-tabs" id="productTab" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="details-tab" data-toggle="tab" href="#details" role="tab" aria-controls="details" aria-selected="true" data-location="">지도</a>
            </li>
           
        </ul>
        <div class="tab-content" id="productTabContent">
            <div class="tab-pane fade show active" id="details" role="tabpanel" aria-labelledby="details-tab">
            </div>
        </div>
        <!-- 지도를 표시할 영역 -->
        <div id="map" style="width: 100%; height: 500px;"></div>
    </div>
    
</div>

<jsp:include page="footer.jsp" />
