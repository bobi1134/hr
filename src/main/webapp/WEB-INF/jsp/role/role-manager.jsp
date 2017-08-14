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
    <title>role-manager.jsp</title>
    <%@include file="../jspf/head.jspf" %>
    <style type="text/css">
        *,body{
            padding: 0;
            margin: 0;
        }
    </style>
</head>
<body>

    <%--dataGrid--%>
    <table id="dataGrid" data-options="fit:true,border:false">
    </table>

    <%--对话框--%>
    <div id="grant-role"></div>

    <%--授权隐藏表单--%>
    <form id="grantForm" action="${ctx}/role/grant" method="post">
        <input type="hidden" name="roleId" id="roleId"/>
        <input type="hidden" name="resourceIds" id="resourceIds"/>
        <input type="submit" value="提交==="/>
    </form>

    <script type="text/javascript">
        /**
         * 加载角色表格
         */
        $('#dataGrid').datagrid({
            url:'${ctx}/role/list',
            columns:[[
                {field:'id',title:'ID',width:30,align:'center'},
                {field:'name',title:'名称',width:100,align:'center'},
                {field:'description',title:'描述',width:100,align:'center'},
                {field:'operation',title:'操作',width:200,align:'center',formatter:function(value, row){
                    return "<a href='#' onclick='grantRoleFun("+row.id+")'>授权</a>&nbsp;&nbsp;<a href='#'>编辑</a>&nbsp;&nbsp;<a href='#'>删除</a>";;
                }},
            ]],
            pagination:true,
            pageSize:10,
            striped : true,
            rownumbers : true,
            singleSelect : true,
        });

        /**
         * 按角色进行授权
         * @param id
         */
        var roleId;
        function grantRoleFun(id) {
            $("#grant-role").dialog({
                title: '授权',
                width: 500,
                height: 500,
                closed: false,
                cache: false,
                href: '${ctx}/role/grant',
                modal: true,
                queryParams: { roleId:id},
                buttons:[{
                    text : '确定',
                    handler:function () {
                        //获取所有已选择的节点
                        var nodes = $('#role-tree').tree('getChecked');
                        var resourceIds = [];
                        for(var i = 0; i < nodes.length; i++){
                            resourceIds.push(nodes[i].id)
                        }
                        //赋值给隐藏表单
                        $("#resourceIds").val(resourceIds);
                        $("#roleId").val(id);

                        //修改
                        $.post("${ctx}/role/grant", {resourceIds:$("#resourceIds").val(), roleId:$("#roleId").val()}, function (res) {
                            if(res.success){
                                $.messager.alert('修改成功！', res.msg);
                            }else{
                                $.messager.alert('修改失败！', res.msg);
                            }
                            $("#grant-role").dialog("close");
                        });
                    }
                }]
            });
        }
    </script>
</body>
</html>
