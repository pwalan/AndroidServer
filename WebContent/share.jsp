<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@  taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>分享</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/table1.css">
</head>

<body style="text-align:center;">
    <img src="<s:property value="#request.recipe.getPic()"/>" style="width:400x;height:400px;margin:0 auto 0"><br>
    <span style="font-size:60px;"><s:property value="#request.recipe.getRname()"/></span><br>
    <span style="font-size:40px;">作者:<s:property value="#request.user.getUsername()"/></span><br><br><br><br>
    <span style="font-size:40px;">具体步骤</span>
    <s:iterator id="listSteps" value="#request.listSteps">
        <img src="<s:property value="#listSteps.pic"/>" style="width:95%;height:400px;"><br>
        <span style="font-size:40px;">步骤<s:property value="#listSteps.num"/></span>
        <textarea style="width:95%;height:300px;"><s:property value="#listSteps.content"/></textarea><br>
    </s:iterator>
    <span style="font-size:40px;">评论详情</span><br>
    <s:iterator id="commentlist" value="#request.commentlist">
        <img src="<s:property value="#commentlist.head"/>" style="width:28%;height:300px;float:left">
        <textarea style="width:70%;height:300px;"><s:property value="#commentlist.content"/></textarea><br>
    </s:iterator>
</body>
</html>
