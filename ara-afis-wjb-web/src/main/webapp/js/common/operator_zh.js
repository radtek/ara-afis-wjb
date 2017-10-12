$(function(){
	//修改按钮绑定
	$(document).on("click",".button_modify",function(){
		var action = $("#action").val();
		var prifex = $("#prifex").val();
		var page = $("#page").val();
		$("#anyOption").val("modify"+page);
		if(page != "Engine"){
			$("#anyId").val($(this).nextAll("input").first().val());
			var nTr = $(this).closest('tr');
			var trData = oTable.fnGetData(nTr);
			$("#anyId").val(getTrElementValue(trData,"id"));
		}
		loadPage("main-content",prifex+"/"+action,"dataForm");
	});

	//删除按钮绑定
	$(document).on("click",".button_del",function(){
		if(confirm("您确定要删除此项目吗？")){
			var prifex = $("#prifex").val();
			var page = $("#page").val();
			$("#operate").val("del");
			if(page != "Engine"){
				var nTr = $(this).closest('tr');
				var trData = oTable.fnGetData(nTr);
				$("#anyId").val(getTrElementValue(trData,"id"));
				$(this).parent().parent("tr").addClass("delFlag");;
			}
			updateInfo(prifex+"/delOption","dataForm","result");
		}
	});

	//添加按钮绑定
	$(document).on("click","#button_add",function(){
		var action = $("#action").val();
		var prifex = $("#prifex").val();
		var page = $("#page").val();
		var operate = $("#operate").val();
		if("modify" != operate && "changePwd" != operate){
			$("#operate").val("add");
			operate = "add";
		}

		if(checkForm(page)){
			$("#anyOption").val(operate+page);
			if("" != $("#fileAddress").val() && typeof($("#fileAddress").val()) != "undefined"){
				$('#anyFile').uploadifyUpload();
			}else{
				updateInfo(prifex+"/"+action,"dataForm","result");
			}
		}
	});


	//密码修改按钮绑定
	$(document).on("click","#button_change",function(){
		var action = $("#action").val();
		var prifex = $("#prifex").val();
		var page = $("#page").val();
		var operate = $("#operate").val();
		if(checkForm(page)){
			$("#anyOption").val(operate+page);
			updateInfo(prifex+"/"+"changeOption","dataForm","result");
		}
	});

	//详细按钮绑定
	$(document).on("click","#button_detail",function(){
		var action = $("#action").val();
		var prifex = $("#prifex").val();
		var page = $("#page").val();
		$("#anyOption").val("detail"+page);
		$("#anyId").val($(this).siblings("input").first().val());
		loadPage('main-content','toGetDetail_'+action+'.action','dataForm');
	});

	//初始化修改管理员密码页面
	$(document).on("click","#button_changePwd",function(){
		var prifex = $("#prifex").val();
		var anyId = $("#anyId").val();
		loadPage('main-content',prifex+"/"+'toChangeOption?anyId='+anyId,'');
	});

	//表单重置
	$(document).on("click","#button_reset",function(){
		reset("dataForm");
	});

	//文件选择按钮绑定
	$(document).on("change","#anyFile",function(){
		$("input.file").val($("#anyFile").val());
	});

	/*消息提示框的消失 */
	$(".message > .dismiss > a").off().on("click", function (event) {
		var value = $(this).attr("href");
		var id = value.substring(value.indexOf('#') + 1);
		$("#" + id).fadeOut('slow', function () { });
		return false;
	});

	//禁止退格键 作用于Firefox、Opera
	//document.onkeypress = banBackSpace;
	//禁止退格键 作用于IE、Chrome
	//document.onkeydown = banBackSpace;
});