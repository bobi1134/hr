<%--
  Created by IntelliJ IDEA.
  User: xialiangbo
  Date: 2017/8/6
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>role-grant.jsp</title>
    <%@include file="../jspf/head.jspf" %>
    <style type="text/css">
        *,body{
            padding: 0;
            margin: 0;
        }
    </style>
</head>
<body>

    <%--授权菜单树--%>
    <ul id="role-tree"></ul>

    <script type="text/javascript">

        /** 获取上个页面传递过来的参数 */
        var obj = $('#grant-role').dialog('options');
        var queryParams = obj["queryParams"];

        /** 系统设计最多只有三层目录 */
        var allMeun = [];
        $("#role-tree").tree({
            url:'${ctx}/resource/grantTree/'+queryParams.roleId,
            checkbox:true,
            animate:true,
            lines:true,
            loadFilter:function(data){
                _data = data.data.allResources;//所有Resource
                var hasResourceIds = data.data.hasResourceIds;//该角色拥有Resource
                /** 遍历出第一层菜单*/
                for (var i = 0; i < _data.length; i++) {
                    var p_id = _data[i].id;  var p_text = _data[i].name;  var p_pid = _data[i].pid;
                    if(p_pid == null){
                        var parentMenu = {id:p_id, text:p_text, state:open};
                        /** 遍历出第二层菜单*/
                        var subMenu = [];
                        for(var j = 0; j < _data.length; j++){
                            var sub_id = _data[j].id;  var sub_text = _data[j].name;  var sub_pid = _data[j].pid;
                            if(sub_pid != null && sub_pid == p_id){
                                /** 遍历出第三层菜单*/
                                var grandsonMenu = [];
                                for(var k = 0; k < _data.length; k++){
                                    var grandson_id = _data[k].id;  var grandson_text = _data[k].name; var grandson_type = _data[k].type; var grandson_pid = _data[k].pid;
                                    if(grandson_type == 1 && grandson_pid == sub_id){
                                        var grandsonJson = {id:grandson_id, text:grandson_text};
                                        //将第三层菜单已有的资源加上checked
                                        for(var m = 0; m <  hasResourceIds.length; m++){
                                            if(hasResourceIds[m] == grandson_id){
                                                grandsonJson = {id:grandson_id, text:grandson_text, checked:true};
                                            }
                                        }
                                        grandsonMenu.push(grandsonJson);
                                    }
                                }
                                var subJson = {id:sub_id, text:sub_text, children:grandsonMenu};
                                subMenu.push(subJson);
                            }
                        }
                        parentMenu.children = subMenu;
                        allMeun.push(parentMenu);
                    }
                }
                return allMeun;
            }
        });
    </script>
</body>
</html>
