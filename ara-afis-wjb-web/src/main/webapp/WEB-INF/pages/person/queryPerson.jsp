<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML>
<html>
  <head></head>
<body>
	<div class="hideDiv">
		<input type="hidden" id="action" value="toAddOption"/>
        <input type="hidden" id="prifex" value="person" />
		<input type="hidden" id="page" value="Person"/>
		<input type="hidden" id="operate"  />
		<input type="hidden" id="detail_key" value="<spring:message code='person_detailKey' />"/>
		<input type="hidden" id="detail_data_key" value="middleName,nationName,cnName,nationIdNum,dataSourceFlag,collectPlace,collectPerson"/>
	</div>
	<section class="wrapper_top">
		<div class="row">
			<div class="col-lg-12">
				<!--breadcrumbs start -->
				<ul class="breadcrumb">
					<li><a href="javascript:void(0)" onclick="loadPage('main-content','common/loadContent','')"><i class="icon-home"></i><spring:message code="home" /></a></li>
					<li><a href="avascript:void(0)"><i class="icon-user"></i><spring:message code="user" /></a></li>
					<li class="active"><i class="icon-user"></i><spring:message code="person_managerVisoUser" /></li>
				</ul>
				<!--breadcrumbs end -->
			</div>
		</div>
	</section>
	
	<section class="wrapper" id="app-content">
		<form id="dataForm" name="dataForm">
			<input type="hidden" id="anyOption" name="anyOption" /> 
			<input type="hidden" id="anyId" name="anyId" />
		</form>
		<div class="row">
			<div class="col-lg-12">
				 <!-- page start-->
              <section class="panel">
                  <header class="panel-heading panel-bg-orgin"><i class="icon-book" ></i><spring:message code="person_visoUser" /></header>
                  <div class="panel-body">
                        <div class="adv-table">
                            <div class="clearfix">
                                <div class="input-group pull-right" style="width: 15%">
                                    <div class="input-group-btn">
                                        <button type="button" class="btn green dropdown-toggle" data-toggle="dropdown"><spring:message code="person_filterItem" /> <span class="caret"></span></button>
                                        <ul class="dropdown-menu">
                                            <li><a href="javascript:void(0)" @click="filterSelect('eid','<spring:message code="person_certificateID" />')"><spring:message code="person_certificateID" /></a></li>
                                            <li><a href="javascript:void(0)" @click="filterSelect('fileId','<spring:message code="person_fingerScanNum" />')"><spring:message code="person_fingerScanNum" /></a></li>
                                            <li><a href="javascript:void(0)" @click="filterSelect('birthday','<spring:message code="person_birthday" />')"><spring:message code="person_birthday" /></a></li>
                                            <li><a href="javascript:void(0)" @click="filterSelect('nationCode','<spring:message code="person_nationCode" />')"><spring:message code="person_nationCode" /></a></li>
                                            <li><a href="javascript:void(0)" @click="filterSelect('createOn','<spring:message code="person_inLibaryTime" />')"><spring:message code="person_inLibaryTime" /></a></li>
                                            <li class="divider"></li>
                                            <li><a href="javascript:void(0)" @click="filterSelect('','')"><spring:message code="person_all" /></a></li>
                                        </ul>
                                    </div>
                                    <input id="filter_item" name="filter_item"  type="text" class="form-control" :value="itemMsg"  readonly>
                                </div>
                            </div>
                            <table cellpadding="0" cellspacing="0" border="0" class="display table table-bordered  table-hover" id="dataTable" width="100%">
                                <thead>
	                                <tr>
	                                    <th style="width:26px;"></th>
	                                    <th ><spring:message code="person_userID" /></th>
	                                    <th><spring:message code="person_certificateID" /></th>
	                                    <th><spring:message code="person_fingerScanNum" /></th>
	                                    <th><spring:message code="person_familyName" /></th>
	                                    <th><spring:message code="person_userName" /></th>
	                                    <th><spring:message code="person_sex" /></th>
	                                    <th><spring:message code="person_birthday" /></th>
	                                    <th><spring:message code="person_nationCode" /></th>
	                                    <th><spring:message code="person_inLibaryTime" /></th>
	                                </tr>
	                             </thead>
                            </table>
                        </div>
                  </div>
              </section>
              <!-- page end-->
			</div>
		</div>
	</section>
	<script type="text/javascript">
        var appContent = new Vue({
            el: '#app-content',
            data: {
                itemType: '',
                itemMsg: ''
            },
            methods: {
                filterSelect: function (itemType,itemMsg) {
                    this.itemMsg = itemMsg;
                    this.itemType = itemType;
                    $('#dataTable').DataTable().ajax.reload();
                }
            }
        });
		var oTable = $('#dataTable').dataTable({
			serverSide: true,
			fnServerData: function retrieveData( source,data, callback){
				$.ajax({
					url : 'person/queryPerson',//这个就是请求地址对应sAjaxSource
					data : {"aoData":JSON.stringify(data),"filterItem":appContent.itemType},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
					type : 'POST',
					dataType : 'json',
                    success : function(data) {
                        if(0 == data.anyStatus){
                            getResultDiv("result",data.anyStatus,data.msg);
                        }else{
                            callback(data);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
                        }
                    }
				});
			}, // 获取数据的处理函数
			"pagingType":   "full_numbers",
			columns: [
				{
					"class": 'details-control',
					"data": null,
					"defaultContent": ''
				},
				{ "data": "id" },
				{ "data": "eid"},
				{ "data": "fileId" },
				{ "data": "familyName" },
				{ "data": "firstName"},
				{ "data": "sex"},
				{ "data": "birthday"},
				{ "data": "nationCode"},
				{ "data": "createOn"}
			],
            "processing": true,
			"deferRender": true,
			"aaSorting": [[9, 'desc']],
            "language": {
                "url":"json/${pageContext.request.locale.language}_datatable.json"
            }
		});
	</script>
	<script type="text/javascript" src="js/common/pageEvent.js"></script>
</body>
</html>
