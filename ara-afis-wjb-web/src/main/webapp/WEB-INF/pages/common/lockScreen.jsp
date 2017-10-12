<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>锁屏界面</title>
    <!--[if IE]>
    <meta http-equiv='X-UA-Compatible' content="IE=edge,IE=9,IE=8,chrome=1"/><![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="img/faviconLS.ico">

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-reset.css" rel="stylesheet">
    <!--external css-->
    <link href="assets/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/style-responsive.css" rel="stylesheet"/>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="js/other/html5shiv.js"></script>
    <script src="js/other/respond.min.js"></script>
    <![endif]-->
</head>

<body class="lock-screen" onload="startTime()" onkeydown="BindEnter(event)">
<div class="hideDiv">
    <input type="hidden" id="pw_empty" value="<spring:message code="pw_empty" />" />
</div>
<div class="lock-wrapper">
    <div id="time"></div>
    <div class="lock-box text-center">
        <img src="img/avatar-big.jpg"/>
        <h1>${account}</h1>
        <span class="locked" id="errorMsg"><spring:message code="common_systemLocked"/> </span>
        <form role="form" id="dataForm" class="form-inline" action="login" method="POST" onsubmit="return checkLock();">
            <div class="form-group col-lg-12">
                <input type="hidden" id="account" name="account" value="${account}">
                <input type="password" id="password" name="password"  placeholder="<spring:message code="pass_word" />" class="form-control lock-input" autofocus>
                <button class="btn btn-no-space btn-lock" type="submit" id="btn">
                    <i class="icon-arrow-right"></i>
                </button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="js/jquery/jquery.js"></script>
<script type="text/javascript" src="js/common/login.js"></script>
<script type="text/javascript">
    function startTime() {
        var today = new Date();
        var h = today.getHours();
        var m = today.getMinutes();
        var s = today.getSeconds();
        // add a zero in front of numbers<10
        m = checkTime(m);
        s = checkTime(s);
        document.getElementById('time').innerHTML = h + ":" + m + ":" + s;
        t = setTimeout(function () {
            startTime()
        }, 500);
    }

    function checkTime(i) {
        if (i < 10) {
            i = "0" + i;
        }
        return i;
    }


    
    var forbidBackup = {
        init:{
            initData: function () {
                forbidBackup.init.initEvent();
            },
            initEvent: function () {
                //禁止浏览器后退事件
                forbidBackup.forbidFunction.forbidBackup();

                //键盘输入监听
                document.onkeypress = forbidBackup.forbidFunction.banBackSpace();
                // 禁止退格键 作用于IE、Chrome
                document.onkeydown = forbidBackup.forbidFunction.banBackSpace();


            }
        },
        forbidFunction:{
            //禁止退格键方法，不含输入文本时的操作
            banBackSpace:function (e){
                var ev = e || window.event;//获取event对象
                var obj = ev.target || ev.srcElement;//获取事件源
                var t = obj.type || obj.getAttribute('type');//获取事件源类型
                //获取作为判断条件的事件类型
                var vReadOnly = obj.readOnly;
                var vDisabled = obj.disabled;
                //处理undefined值情况
                vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
                vDisabled = (vDisabled == undefined) ? true : vDisabled;
                //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
                //并且readOnly属性为true或disabled属性为true的，则退格键失效
                var flag1= ev.keyCode == 8 && (t=="password" || t=="text" || t=="textarea")&& (vReadOnly==true || vDisabled==true);
                //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
                var flag2= ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea" ;
                //判断
                if(flag2 || flag1)return false;
            },
            //监听到的浏览器后退事件
            forbidBackup:function () {
                if (typeof history.pushState === "function") {
                    history.pushState("jibberish", null, null);
                    window.onpopstate = function () {
                        history.pushState('newjibberish', null, null);

                        //do your code
                    };
                }
                else {
                    var ignoreHashChange = true;
                    window.onhashchange = function () {
                        if (!ignoreHashChange) {
                            ignoreHashChange = true;
                            window.location.hash = Math.random();
                        }
                        else {
                            ignoreHashChange = false;
                        }
                    };
                }
            }
        }

    }

    forbidBackup.init.initData();





</script>

</body>
</html>
