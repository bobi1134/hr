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
    <title>user-manager.jsp</title>
    <%@include file="../jspf/head.jspf" %>
    <style type="text/css">
        *,body{
            padding: 0;
            margin: 0;
        }
    </style>
</head>
<body>
    <table id="dataGrid" data-options="fit:true,border:false">

    </table>

    <script type="text/javascript">
        $('#dataGrid').datagrid({
            url:'${ctx}/user/list',
            columns:[[
                {field:'id',title:'ID',width:30,align:'center'},
                {field:'account',title:'账号',width:100,align:'center'},
                {field:'address',title:'地址',width:100,align:'center'},
                {field:'salt',title:'盐',width:100,align:'center'},
                {field:'sex',title:'性别',width:30,align:'center',formatter:function (val) {
                   return val==0 ? "女" : "男";
                }},
                {field:'phone',title:'电话',width:150,align:'center'},
                {field:'status',title:'状态',width:100,align:'center', formatter:function () {
                    return status==0 ? "正常" : "停用";
                }},
                {field:'create_time',title:'创建时间',width:100,align:'center'},
                {field:'dept_id',title:'部门ID',width:100,align:'center'},
                {field:'operation',title:'操作',width:100,align:'center',formatter:function(){
                    return "<a href='#'>编辑</a>&nbsp;&nbsp;<a href='#'>删除</a>";;
                }},
            ]],
            pagination:true,
            pageSize:10
        });
    </script>
</body>
</html>
