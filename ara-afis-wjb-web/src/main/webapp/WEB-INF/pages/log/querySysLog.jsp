<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
  <head></head>
<body >
	<div class="hideDiv">
		<input type="hidden" id="action" value="toAddOption"/>
		<input type="hidden" id="prifex" value="logInfo" />
		<input type="hidden" id="page" value="SysLog"/>
		<input type="hidden" id="operate"  />
		<input type="hidden" id="detail_key" value="<spring:message code="log_executor" />"/>
		<input type="hidden" id="detail_data_key" value="createBy"/>
	</div>
	<section class="wrapper_top">
		<div class="row">
			<div class="col-lg-12">
				<!--breadcrumbs start -->
				<ul class="breadcrumb">
					<li><a href="javascript:void(0)" onclick="loadPage('main-content','common/loadContent','')"><i class="icon-home"></i><spring:message code="home" /></a></li>
					<li><a href="avascript:void(0)"><i class="icon-list-alt"></i><spring:message code="log" /></a></li>
					<li class="active"><i class="icon-list-alt"></i><spring:message code="log_querySysLog" /></li>
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
                  <header class="panel-heading panel-bg-orgin"><i class="icon-list-alt" ></i><spring:message code="log_sysLog" /></header>
                  <div class="panel-body">
                        <div class="adv-table">
                            <div class="clearfix">
                                <div class="input-group pull-right" style="width: 15%">
                                    <div class="input-group-btn">
                                        <button type="button" class="btn green dropdown-toggle" data-toggle="dropdown"><spring:message code="person_filterItem" /> <span class="caret"></span></button>
                                        <ul class="dropdown-menu">
                                            <li><a href="javascript:void(0)" @click="filterSelect('type','<spring:message code="person_filterItem" />')"><spring:message code="log_logType" /></a></li>
                                            <li><a href="javascript:void(0)" @click="filterSelect('content','<spring:message code="log_logContent" />')"><spring:message code="log_logContent" /></a></li>
                                            <li><a href="javascript:void(0)" @click="filterSelect('createOn','<spring:message code="log_actionTime" />')"><spring:message code="log_actionTime" /></a></li>
                                            <li class="divider"></li>
                                            <li><a href="javascript:void(0)" @click="filterSelect('','')"><spring:message code="person_all" /></a></li>
                                        </ul>
                                    </div>
                                    <input id="filter_item" name="filter_item"  type="text" class="form-control" :value="itemMsg" readonly>
                                </div>
                            </div>
                            <table cellpadding="0" cellspacing="0" border="0" class="display table table-bordered  table-hover" id="dataTable" width="100%">
                                <thead>
	                                <tr>
                                        <th style="width:26px;"></th>
                                        <th><spring:message code="log_logType" /></th>
                                        <th><spring:message code="system_code_action" /></th>
                                        <th><spring:message code="log_logContent" /></th>
                                        <th><spring:message code="log_actionTime" /></th>
                                        <th><spring:message code="log_executor" /></th>
	                                </tr>
	                             </thead>
                                <tbody></tbody>
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
                    url : 'logInfo/querySysLog',//这个就是请求地址对应sAjaxSource
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
                { "data": "type" },
                { "data": "operate"},
                { "data": "content" },
                { "data": "createOn" },
                { "data": "createBy"}
            ],
            "columnDefs": [
                { "bSortable": false, "aTargets": [0] },
                {
                    "targets": [5],
                    "visible": false,
                    "searchable": false
                }
            ],
            "deferRender": true,
            "processing": true,
            "aaSorting": [[1, 'desc']],

            "language": {
                "url":"json/${pageContext.request.locale.language}_datatable.json"
			}
//            "oLanguage": { //国际化配置
//                "sProcessing" : "正在加载数据，请稍后...",
//                "sLengthMenu" : "显示 _MENU_ 条",
//                "sZeroRecords" : "没有您要搜索的内容",
//                "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
//                "sInfoEmpty" : "记录数为0",
//                "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
//                "sInfoPostFix" : "",
//                "sSearch" : "搜索",
//                "sUrl" : "",
//                "oPaginate": {
//                    "sFirst" : "第一页",
//                    "sPrevious" : "上一页",
//                    "sNext" : "下一页",
//                    "sLast" : "最后一页"
//                }
//            },
        });
	</script>
    <script type="text/javascript" src="js/common/pageEvent.js"></script>
</body>
</html>
