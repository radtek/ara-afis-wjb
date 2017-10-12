<%@ page language="java"  pageEncoding="UTF-8"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE HTML>
<html>
  <head></head>

<body >
	<div class="hideDiv">
        <input type="hidden" id="action" value="toAddOption" />
        <input type="hidden" id="prifex" value="managerInfo" />
		<input type="hidden" id="page" value="Manager"/>
		<input type="hidden" id="operate"  />
		<input type="hidden" id="detail_key" value="<spring:message code='system_manager_roleDescription' />"/>
		<input type="hidden" id="detail_data_key" value="describe"/>
	</div>
	<section class="wrapper_top">
		<div class="row">
			<div class="col-lg-12">
				<!--breadcrumbs start -->
				<ul class="breadcrumb">
					<li><a href="javascript:void(0)" onclick="loadPage('main-content','common/loadContent','')"><i class="icon-home"></i><spring:message code="home" /></a></li>
					<li><a href="avascript:void(0)"><i class="icon-gear"></i><spring:message code="setting" /></a></li>
					<li class="active"><i class="icon-group"></i><spring:message code="system_parameter_setParameter" /></li>
				</ul>
				<!--breadcrumbs end -->
			</div>
		</div>
	</section>
	
	<section class="wrapper">
		<form id="dataForm" name="dataForm">
			<input type="hidden" id="anyOption" name="anyOption" /> 
			<input type="hidden" id="anyId" name="anyId" />
		</form>
		<div class="row">
			<div class="col-lg-12">
				 <!-- page start-->
              <section class="panel">
                  <header class="panel-heading panel-bg-orgin"><i class="icon-book" ></i><spring:message code="system_manager_manager" /></header>
                  <div class="panel-body">
                        <div class="adv-table">
                            <table cellpadding="0" cellspacing="0" border="0" class="display table table-bordered  table-hover" id="dataTable" width="100%">
                                <thead>
	                                <tr>
	                                    <th style="width:26px;"></th>
	                                    <th><spring:message code="system_manager_mangerAcount" /></th>
	                                    <th><spring:message code="system_manager_role" /></th>
	                                    <th><spring:message code="system_code_stat" /></th>
	                                    <th><spring:message code="system_manager_lastLoginTime" /></th>
	                                    <th><spring:message code="system_code_crateDateTime" /></th>
	                                    <th style="width:150px;"><spring:message code="system_code_action" /></th>
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
		var oTable = $('#dataTable').dataTable({
			"data": ${managerListJson},
			columns: [
				{
					"class": 'details-control',
					"data": null,
					"defaultContent": ''
				},
				{ "data": "account" },
				{ "data": "roleName"},
				{ "data": "statu" },
				{ "data": "lastLogin" },
				{ "data": "createOn"},
				{
					"data": null,
					"defaultContent": '<button class="btn btn-primary btn-xs hor-btn-space button_modify" ><i class="icon-pencil"></i></button><button class="btn btn-danger btn-xs button_del"><i class="icon-trash "></i></button>'
				}
			],
			"deferRender": true,
            "processing": true,
			"aoColumnDefs": [
				{ "bSortable": false, "aTargets": [ 0 ] }
			],
			"aaSorting": [[5, 'desc']],
            "language": {
                "url":"json/${pageContext.request.locale.language}_datatable.json"
            }
		});
	</script>
	<script type="text/javascript" src="js/common/pageEvent.js"></script>
</body>
</html>
