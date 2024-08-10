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

    <title>국내페스티벌 등록</title>

</head>

<body id="page-top">

			<!-- header 시작 -->
			<%@ include file="../ad_common/ad_header.jsp" %>
	     	<!-- header 끝 -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">국내페스티벌 등록</h1>
                    </div>
                    
                 <!-- DataTales Example -->   
			 	 <div class="card shadow mb-4"> <!--  테이블 css수정 0618-->
		             <div class="card-header py-3 d-flex justify-content-between align-items-center">
   							<h6 class="m-0 font-weight-bold text-primary">국내페스티벌 등록</h6>
					 </div>
					 
					 <div class="card-body">
		                  <div class="table_div">
		                     <form name="ad_festivalAdd" action="ad_showAddAction.adshow?showCategory=페스티벌&${_csrf.parameterName}=${_csrf.token}" method="post" enctype="multipart/form-data">
		                        
		                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
		                      	  
		                       <tr>
	                              <th> * 공연 카테고리 </th>
	                              <td>                                 
	                                 <select class="input" name="showCategory" id="showCategory" required>
	                                    <option value="페스티벌">페스티벌</option>
	                                 </select>
	                              </td>
	                           </tr>     
	                           <tr>
	                              <th> * 페스티벌명 </th>
	                              <td>
	                                 <input type="text" class="input" name="showName" id="showName" size="40" placeholder="페스티벌명 작성" required autofocus>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 페스티벌 이미지 </th>
	                              <td>
	                                 <input type="file" class="input" name="showImage" id="showImage" accept="image/*">
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 날짜 </th>
	                              <td>
	                                 <input type="text" class="input" name="showDay" id="showDay" size="40" placeholder="날짜 작성(YYYY-MM-DD, YYYY-MM-DD, YYYY-MM-DD)" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 공연시간 </th>
	                              <td>
	                                 <input type="number" class="input" name="showTime" id="showTime" size="5" placeholder="공연시간 작성" required>분
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 장소 </th>
	                              <td>
	                                 <input type="text" class="input" name="showPlace" id="showPlace" size="40" placeholder="장소 작성" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 관람연령 </th>
	                              <td>
	                                 <input type="text" class="input" name="showAge" id="showAge" size="20" placeholder="관람연령 작성" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 1매당 가격 </th>
	                              <td>
	                                 <input type="number" class="input" name="showPrice" id="showPrice" size="40" placeholder="1매당 가격 작성" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 혜택 </th>
	                              <td>
	                                 <input type="text" class="input" name="showBene" id="showBene" size="40" placeholder="혜택 작성" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 현수용인원 </th>
	                              <td>
	                                 <input type="number" class="input" name="curCapacity" id="curCapacity" size="20" placeholder="현수용인원 작성" required>
	                              </td>
	                           </tr>
	                           <tr>
	                              <th> * 최대수용인원 </th>
	                              <td>
	                                 <input type="number" class="input" name="maxCapacity" id="maxCapacity" size="20" placeholder="최대수용인원 작성" required>
	                              </td>
	                           </tr>
	                           
		                           <tr>
		                              <td colspan="2">
		                                 <br>
		                                 <div align="right">
		                                    <input class="btn btn-primary inputButton" type="submit" value="페스티벌등록">
		                                    <input class="btn btn-danger inputButton" type="reset" value="초기화">
		                                    <input class="btn btn-secondary inputButton" type="button" value="페스티벌목록" onclick="window.location='ad_showEdit.adshow?showCategory=페스티벌'">
		                                 </div>
		                              </td>
		                           </tr>
		                        </table>
		                     </form>
		                  </div>
		                </div> 
		            </div>
           		</div> 
                <!-- /.container-fluid -->


            <!-- footer 시작 -->
			<%@ include file="../ad_common/ad_footer.jsp" %>
			<!-- footer 끝 -->


</body>

</html>