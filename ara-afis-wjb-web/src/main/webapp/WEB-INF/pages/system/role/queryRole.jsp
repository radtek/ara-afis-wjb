<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE HTML>
<html>
<head></head>

<body>
<div class="hideDiv">
    <input type="hidden" id="action" value="toAddOption"/>
    <input type="hidden" id="prifex" value="role"/>
    <input type="hidden" id="page" value="Role"/>
    <input type="hidden" id="operate"/>
</div>
<section class="wrapper_top">
    <div class="row">
        <div class="col-lg-12">
            <!--breadcrumbs start -->
            <ul class="breadcrumb">
                <li><a href="javascript:void(0)" onclick="loadPage('main-content','common/loadContent','')"><i
                        class="icon-home"></i><spring:message code="home" /></a></li>
                <li><a href="javascript:void(0)"><i class="icon-gear"></i><spring:message code="setting" /></a></li>
                <li class="active"><i class="icon-shield"></i><spring:message code="system_role_managerRole" /></li>
            </ul>
            <!--breadcrumbs end -->
        </div>
    </div>
</section>

<section class="wrapper">
    <form id="dataForm" name="dataForm">
        <input type="hidden" id="anyOption" name="anyOption"/>
        <input type="hidden" id="anyId" name="anyId"/>
    </form>
    <div class="row">
        <div class="col-lg-12">
            <div id="result" class="alert alert-block fade in hideDiv">
                <button data-dismiss="alert" class="close close-sm" type="button">
                    <i class="icon-remove"></i>
                </button>
                <h4><i id="result_sign"></i><span id="result_title"></span></h4>
                <p id="result_msg"></p>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <!-- page start-->
            <section class="panel">
                <header class="panel-heading panel-bg-orgin"><i class="icon-book"></i><spring:message code="system_manager_role" /></header>
                <div class="panel-body">
                    <div class="adv-table">
                        <table cellpadding="0" cellspacing="0" border="0"
                               class="display table table-bordered  table-hover" id="dataTable" width="100%">
                            <thead>
                                <tr>
                                    <th><spring:message code="system_role_roleName" /></th>
                                    <th><spring:message code="system_manager_roleDescription" /></th>
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
        "data": ${roleListJson},
        columns: [
            { "data": "name" },
            { "data": "describe"},
            {
                "data": null,
                "defaultContent": '<button class="btn btn-primary btn-xs hor-btn-space button_modify" ><i class="icon-pencil"></i></button><button class="btn btn-danger btn-xs button_del"><i class="icon-trash "></i></button>'
            }
        ],
        "processing": true,
        "language": {
            "url":"json/${pageContext.request.locale.language}_datatable.json"
        }
    });
</script>
<script type="text/javascript" src="js/common/pageEvent.js"></script>
</body>
</html>
