<%--
  Created by IntelliJ IDEA.
  User: xialiangbo
  Date: 2017/8/3
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>HR人事管理系统</title>
    <%@include file="jspf/head.jspf" %>
</head>
<body class="easyui-layout">
    <%--顶部--%>
    <div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px">
        顶部
    </div>

    <%--左部树形菜单--%>
    <div data-options="region:'west',split:true,title:'菜单'" style="width:150px;">
        <ul id="menu-tree">
        </ul>
    </div>

    <%--中间部分--%>
    <div data-options="region:'center',title:''">
        <%--tab菜单--%>
        <div id="menu-tab" class="easyui-tabs" style="height:100%">
            <div title="首页" style="padding:20px;display:none;">
                首页
                <%--底部信息--%>
                <div class="easyui-layout" data-options="fit:true">
                    <div data-options="region:'south',border:false" style="height:15px;background:#A9FACD;text-align: center;">
                        Copyright © 2017 hr.mrx1234.com All Rights Reserved
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%--js--%>
    <script type="text/javascript">
         var allMeun = [];
        /**
         * 异步加载树形菜单
         */
        $("#menu-tree").tree({
            url:'${ctx}/resource/menuTree',
            animate:true,
            lines:true,
            loadFilter:function(data){
                /** 遍历第一遍，将所有父菜单查询出来 */
                for (var i = 0; i < data.length; i++) {
                    var p_id = data[i].id;
                    var p_text = data[i].name;
                    var p_pid = data[i].pid;
                    /** 遍历条件 */
                    if(p_pid == null){
                        var parentMenu;
                        var subMenu = [];
                        /** 遍历第二遍，将父菜单包含的子菜单遍历出来 */
                        for(var j = 0; j < data.length; j++){
                            var sub_text = data[j].name;
                            var sub_url = data[j].url;
                            var sub_pid = data[j].pid;
                            /** 遍历条件 */
                            if(sub_pid != null && sub_pid == p_id){
                                subMenu.push({text:sub_text, url:sub_url});
                            }
                        }
                        if(subMenu.length > 0){
                            parentMenu = {id:p_id, text:p_text, state:open};
                            parentMenu.children = subMenu;
                            allMeun.push(parentMenu);
                        }
                    }
                }
                return allMeun;
            },
            onClick:function(node){
                if($("#menu-tree").tree("isLeaf", node.target)){
                    addTab(node.text, node.url);
                }
            }
        });

        /**
         * 添加选项卡
         * @param title：标题
         * @param url：跳转路径
         */
        function addTab(title, url){
            if ($('#menu-tab').tabs('exists', title)){
                $('#menu-tab').tabs('select', title);
            } else {
                var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
                $('#menu-tab').tabs('add',{
                    title:title,
                    content:content,
                    closable:true
                });
            }
        }
    </script>
</body>
</html>