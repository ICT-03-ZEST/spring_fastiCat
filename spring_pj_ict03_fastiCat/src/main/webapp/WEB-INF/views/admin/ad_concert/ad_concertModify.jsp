<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../ad_common/ad_setting.jsp" %> 

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>국내공연 수정</title>

<script> /* 이미지 미리보기 */
        function previewImage(event) {
            var reader = new FileReader();
            reader.onload = function() {
                var output = document.getElementById('imgPreview');
                output.src = reader.result;
            }
            reader.readAsDataURL(event.target.files[0]);
        }
</script>

</head>

<body id="page-top">

			<!-- header 시작 -->
			<%@ include file="../ad_common/ad_header.jsp" %>
	     	<!-- header 끝 -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">국내공연 수정</h1>
                    </div>
                    
				 <!-- DataTales Example -->   
			 	 <div class="card shadow mb-4"> 
		             <div class="card-header py-3 d-flex justify-content-between align-items-center">
   							<h6 class="m-0 font-weight-bold text-primary">국내공연 수정</h6>
					 </div>
					 
					 <div class="card-body">
	                  <div class="table_div">
	                     <form name="ad_concertModify" action="ad_showModifyAction.adshow?showCategory=공연" method="post" enctype="multipart/form-data">
		                       <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		                       
		                       <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
		                       <!-- hidden : 직접 input 태그에서 입력받지 못한 값들을 전달할 때 사용 -->
	                           <input type="hidden" name="hiddenPageNum" value="${pageNum}">
	                           <input type="hidden" name="hiddenShowNum" value="${dto.showNum}">
	                           <input type="hidden" name="hiddenShowImage" value="${dto.showImage}">  <!-- 기존 상품이미지 -->
	                      	 <tr>
								<th> * 공연 번호 </th>
								<td> ${dto.showNum}</td>
							 </tr>
	                      	 <tr>
	                              <th> * 공연 카테고리 </th>
	                              <td>                                 
	                                 <select class="input" name="showCategory" id="showCategory" required>
	                                    <option <c:if test="${dto.showCategory == '케이팝'}">selected</c:if> value="케이팝">케이팝</option>
	                                    <option <c:if test="${dto.showCategory == '트로트'}">selected</c:if> value="트로트">트로트</option>
	                                    <option <c:if test="${dto.showCategory == '인디음악'}">selected</c:if> value="인디음악">인디음악</option>
	                                 </select>
	                              </td>
	                           </tr>
	                            <tr>
	                              <th> * 공연명 </th>
	                              <td>
	                                 <input type="text" class="input" name="showName" id="showName" value="${dto.showName}" size="40" placeholder="페스티벌명 작성" required autofocus>
	                              </td>
	                           </tr>
	                           <tr>
								    <th> * 이미지 등록  </th>
								    <td>
								        <div style="display: flex; align-items: center;">
								            <!-- 기존 이미지 미리보기 -->
								            <div style="margin-right: 20px;">
								             	   기존이미지: <br>
								                <img src="${dto.showImage}" alt="현재 이미지" style="max-width: 200px; max-height: 200px;"/>
								            </div>
								            <!-- 파일 입력 및 변경할 이미지 미리보기 -->
								            <div>
								                <input type="file" class="input" name="showImage" id="showImage" accept="image/*" onchange="previewImage(event)">
								            </div>
								            <div>
								                	변경 이미지 : <br>
								                <img src="#" style="max-width: 200px; max-height: 200px;" id="imgPreview" class="img-preview" alt="이미지 미리보기">
								            </div>
								        </div>
								    </td>
							   </tr>

	                           <tr>
	                              <th> * 날짜 </th>
	                              <td>
	                                 <input type="text" class="input" name="showDay" id="showDay" value="${dto.showDay}" size="40" placeholder="날짜 작성(YYYY-MM-DD)" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 공연시간 </th>
	                              <td>
	                                 <input type="number" class="input" name="showTime" id="showTime" value="${dto.showTime}" size="5" placeholder="공연시간 작성" required>분
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 장소 </th>
	                              <td>
	                                 <input type="text" class="input" name="showPlace" id="showPlace" value="${dto.showPlace}" size="40" placeholder="장소 작성" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 관람연령 </th>
	                              <td>
	                                 <input type="text" class="input" name="showAge" id="showAge" value="${dto.showAge}" size="20" placeholder="관람연령 작성" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 1매당 가격 </th>
	                              <td>
	                                 <input type="number" class="input" name="showPrice" id="showPrice" value="${dto.showPrice}" size="40" placeholder="1매당 가격 작성" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 혜택 </th>
	                              <td>
	                                 <input type="text" class="input" name="showBene" id="showBene" value="${dto.showBene}" size="40" placeholder="혜택 작성" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 현수용인원 </th>
	                              <td>
	                                 <input type="number" class="input" name="curCapacity" id="curCapacity" value="${dto.curCapacity}" size="20" placeholder="현수용인원 작성" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 최대수용인원 </th>
	                              <td>
	                                 <input type="number" class="input" name="maxCapacity" id="maxCapacity" value="${dto.maxCapacity}" size="20" placeholder="최대수용인원 작성" required>
	                              </td>
	                           </tr>
		                           
		                           <tr>
		                              <td colspan="2">
		                                 <br>
		                                 <div align="right">
		                                    <input class="btn btn-primary inputButton" type="submit" value="페스티벌수정">
		                                    <input class="btn btn-danger inputButton" type="reset" value="초기화">
		                                    <input class="btn btn-secondary inputButton" type="button" value="페스티벌목록" onclick="window.location='ad_showEdit.adshow?showCategory=공연'">
		                                 </div>
		                              </td>
		                           </tr>
	                        </table>
                   		 </form>
                  	  </div>
                   </div> 
                </div>
                 
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- footer 시작 -->
			<%@ include file="../ad_common/ad_footer.jsp" %>
			<!-- footer 끝 -->


</body>

</html>