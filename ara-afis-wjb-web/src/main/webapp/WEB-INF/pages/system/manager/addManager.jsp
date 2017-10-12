<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <head>
        <script type="text/javascript">
            $(function () {
                if ("" != "${manager}") {
                    if ("E" == "${manager.statu}") {
                        $("div.switch").bootstrapSwitch("setState", true);
                    } else {
                        $("div.switch").bootstrapSwitch("setState", false);
                    }
                }
            });
        </script>
    </head>

<body>
<div class="hideDiv">
    <input type="hidden" id="action" value="addOption"/>
    <input type="hidden" id="prifex" value="managerInfo"/>
    <input type="hidden" id="page" value="Manager"/>
    <input type="hidden" id="operate" value="<s:if test="${manager != null}">modify</s:if>"/>
    <input type="hidden" id="switchInitStat" value="${manager.statu}">
    <input type="hidden" id="selectInitStat" value="${manager.role.id}">
</div>

<section class="wrapper_top">
    <div class="row">
        <div class="col-lg-12">
            <!--breadcrumbs start -->
            <ul class="breadcrumb">
                <li><a href="javascript:void(0)" onclick="loadPage('main-content','common/loadContent','')"><i
                        class="icon-home"></i><spring:message code="home" /></a></li>
                <li><a href="avascript:void(0)"><i class="icon-gear"></i><spring:message code="setting" /></a></li>
                <li class="active"><i class="icon-group"></i><spring:message code="system_manager_addSystemManager" /></li>
            </ul>
            <!--breadcrumbs end -->
        </div>
    </div>
</section>

<section class="wrapper">

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
            <section class="panel">
                <header class="panel-heading panel-bg-orgin">
                    <i class="icon-book"></i><spring:message code="system_manager_addSystemManager" />
                </header>
                <div class="panel-body">
                    <div class="form">
                        <form class="cmxform form-horizontal tasi-form" id="dataForm" name="dataForm"
                              novalidate="novalidate">
                            <input type="hidden" id="anyOption" name="anyOption"/>
                            <input type="hidden" id="anyId" name="anyId"
                                   value="<s:choose><s:when test="${manager != null}">${manager.id}</s:when><s:otherwise>0</s:otherwise></s:choose>"/>
                            <div class="form-group ">
                                <label for="account" class="control-label col-lg-2" style="text-align: right"><span class="form-req">*</span><spring:message code="system_manager_mangerAcount" />:</label>
                                <div class="col-lg-4">
                                    <input id="account" name="account" value="${manager.account}" class="form-control"
                                           type="text" maxlength="30" required data-rule-onlyNumChar="true"
                                           data-msg-onlyNumChar="<spring:message code='system_manager_mangerAcountFormat' />">
                                </div>
                            </div>
                            <div class="form-group " id="passwd">
                                <s:if test="${manager == null}">
                                    <label for="password" class="control-label col-lg-2" style="text-align: right"><span class="form-req">*</span><spring:message code="system_manager_mangerPassword" />
                                        :</label>
                                    <div class="col-lg-4">
                                        <input id="password" name="password" value="${manager.password}"
                                               class="form-control" minlength="6" maxlength="30" type="password"
                                               required>
                                    </div>
                                </s:if>
                            </div>

                            <div class="form-group ">
                                <label for="roleId" class="control-label col-lg-2" style="text-align: right"><span class="form-req">*</span><spring:message code="system_manager_role" />
                                    :</label>
                                <div class="col-lg-2">
                                    <select id="roleId" name="role.id" class="form-control" required
                                            data-msg-required="<spring:message code='system_code_require' />">
                                        <option value="">-- <spring:message code="system_code_choose" /> --</option>
                                        <s:forEach items="${roleList}" var='role'>
                                            <option value="${role.id}"
                                                    <s:if test="${role.id == manager.role.id}">selected="selected"</s:if>>${role.name}</option>
                                        </s:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group ">
                                <label for="statu" class="control-label col-lg-2 " style="text-align: right"><span class="form-req">*</span><spring:message code="system_code_stat2" />
                                    :</label>
                                <div class="col-lg-10">

                                    <div class="switch " data-on="success" data-off="danger"
                                         data-on-label="<spring:message code='system_code_enable2' />"
                                         data-off-label="<spring:message code='system_code_disable2' />"  >
                                        <input  type="checkbox" id="statu" name="statu" checked/>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-lg-offset-2 col-lg-6">
                                    <s:if test="${manager != null}">
                                        <button class="btn btn-shadow btn-danger button_modify" type="button" id="button_changePwd">
                                            <i class="icon-ok-sign"></i> <spring:message code="system_manager_changePassword" />
                                        </button>
                                    </s:if>
                                    <button class="btn btn-shadow btn-danger button_modify" type="button" id="button_add">
                                        <i class="icon-ok-sign"></i>
                                        <s:choose>
                                            <s:when test="${manager != null}"><spring:message code="system_code_update" /></s:when>
                                            <s:otherwise><spring:message code="system_code_add" /></s:otherwise>
                                        </s:choose>
                                    </button>
                                    <button class="btn btn-shadow btn-default button_modify" type="button" id="button_reset">
                                        <i class="icon-refresh"></i> <spring:message code="system_code_reset" />
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </section>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <section class="panel">
                <table class="table table-striped table-advance table-hover">
                    <thead>
                    <tr>
                        <th class="col-lg-2"><i class="icon-bullhorn"></i> <spring:message code="system_manager_mangerAcount" /></th>
                        <th class="col-lg-1"><i class="icon-bookmark"></i> <spring:message code="system_manager_role" /></th>
                        <th class="col-lg-1"><i class="icon-question-sign"></i> <spring:message code="system_code_stat" /></th>
                        <th class="col-lg-1"><i class="icon-off"></i> <spring:message code="system_manager_lastLoginTime" /></th>
                        <th class="col-lg-2"><i class=" icon-calendar"></i> <spring:message code="system_code_crateDateTime" /></th>
                    </tr>
                    </thead>
                    <tbody id="tabBody">
                    </tbody>
                </table>
            </section>
        </div>
    </div>

</section>
<script type="text/javascript" src="js/common/pageEvent.js"></script>
</body>
</html>
