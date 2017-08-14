<%--
  Created by IntelliJ IDEA.
  User: xialiangbo
  Date: 2017/8/3
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>HR人事管理系统登录</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui-1.5.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui-1.5.2/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/jquery-easyui-1.5.2/demo/demo.css">
    <script type="text/javascript" src="${ctx}/static/jquery-easyui-1.5.2/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
</head>
<body>
    <div style="max-width:400px; margin: 130px auto;">
        <div class="easyui-panel" title="HR人事管理系统登录" style="width:100%;max-width:400px;padding:30px 60px;">
            <form id="loginForm" action="${ctx}/login" method="post">
                <div style="margin-bottom:10px">
                    <input class="easyui-textbox" style="width:100%;height:40px;padding:12px" data-options="prompt:'用户名',iconCls:'icon-man',iconWidth:38" name="account"/>
                </div>
                <div style="margin-bottom:20px">
                    <input class="easyui-textbox" type="password" style="width:100%;height:40px;padding:12px" data-options="prompt:'********',iconCls:'icon-lock',iconWidth:38" name="pwd"/>
                </div>
                <div style="margin-bottom:20px">
                    <input type="checkbox" checked="checked">
                    <span>Remember me</span>
                </div>
                <div>
                    <input type="submit" value="登 录" class="easyui-linkbutton" style="padding:5px 0px;width:100%;outline: none"/>
                </div>
            </form>
        </div>
    </div>

    <%--js--%>
    <script type="text/javascript">
        $('#loginForm').form({
            onSubmit: function(){
            },
            success:function(data) {
                var _data = eval('(' + data + ')');
                if(_data.success){
                    location.href = "${ctx}/index";
                }else{
                    $.messager.alert('警告', _data.msg);
                }
            }
        });
    </script>
</body>
</html>
