<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@  taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<script>
	var autoTextarea = function(elem, extra, maxHeight) {
		extra = extra || 0;
		var isFirefox = !!document.getBoxObjectFor
				|| 'mozInnerScreenX' in window, isOpera = !!window.opera
				&& !!window.opera.toString().indexOf('Opera'), addEvent = function(
				type, callback) {
			elem.addEventListener ? elem
					.addEventListener(type, callback, false) : elem
					.attachEvent('on' + type, callback);
		}, getStyle = elem.currentStyle ? function(name) {
			var val = elem.currentStyle[name];

			if (name === 'height' && val.search(/px/i) !== 1) {
				var rect = elem.getBoundingClientRect();
				return rect.bottom - rect.top
						- parseFloat(getStyle('paddingTop'))
						- parseFloat(getStyle('paddingBottom')) + 'px';
			}
			;

			return val;
		} : function(name) {
			return getComputedStyle(elem, null)[name];
		}, minHeight = parseFloat(getStyle('height'));

		elem.style.resize = 'none';

		var change = function() {
			var scrollTop, height, padding = 0, style = elem.style;

			if (elem._length === elem.value.length)
				return;
			elem._length = elem.value.length;

			if (!isFirefox && !isOpera) {
				padding = parseInt(getStyle('paddingTop'))
						+ parseInt(getStyle('paddingBottom'));
			}
			;
			scrollTop = document.body.scrollTop
					|| document.documentElement.scrollTop;

			elem.style.height = minHeight + 'px';
			if (elem.scrollHeight > minHeight) {
				if (maxHeight && elem.scrollHeight > maxHeight) {
					height = maxHeight - padding;
					style.overflowY = 'auto';
				} else {
					height = elem.scrollHeight - padding;
					style.overflowY = 'hidden';
				}
				;
				style.height = height + extra + 'px';
				scrollTop += parseInt(style.height) - elem.currHeight;
				document.body.scrollTop = scrollTop;
				document.documentElement.scrollTop = scrollTop;
				elem.currHeight = parseInt(style.height);
			}
			;
		};

		addEvent('propertychange', change);
		addEvent('input', change);
		addEvent('focus', change);
		change();
	};
</script>

</head>

<body style="text-align: center; font-family: 微软雅黑;">
	<img src="<s:property value="#request.recipe.getPic()"/>"
		style="width: 400px; height: 350px; margin: 0 auto 0">
	<br>
	<span style="font-size: 60px;"><s:property
			value="#request.recipe.getRname()" /></span>
	<br>
	<span style="font-size: 40px;">作者:<s:property
			value="#request.user.getUsername()" /></span>
	<br>
	<br>
	<br>
	<br>
	<textarea id="textarea1" readonly
		style="border: 1px solid #FFF; width: 90%; height: 200px; overflow-y: visible; font-size: 50px;"><s:property
			value="#request.recipe.getInfo()" /></textarea>
	<br>
	<span
		style="font-size: 85px; display: block; width: 400px; height: 130px; background-color: #97CBFF; margin: 0 auto 0;">具体步骤</span>
	<s:iterator id="listSteps" value="#request.listSteps">
		<span style="font-size: 60px;">步骤<s:property
				value="#listSteps.num" /></span>
		<br>
		<img src="<s:property value="#listSteps.pic"/>"
			style="width: 80%; height: 450px;">
		<br>
		<textarea id="textarea2" readonly
			style="border: 1px solid #FFF; width: 80%; height: 400px;font-size: 60px;"><s:property
				value="#listSteps.content" /></textarea>
		<br>
	</s:iterator>
	<span
		style="font-size: 70px; display: block; width: 400px; height: 130px; background-color: #97CBFF; margin: 0 auto 0;">评论详情</span>
	<br>
	<s:iterator id="commentlist" value="#request.commentlist">
		<img src="<s:property value="#commentlist.head"/>"
			style="width: 150px; height: 150px; float: left; border-radius: 500px; margin: 10px 100px;">
		<textarea id="textarea3" readonly
			style="border: 1px solid #FFF; line-height: 150px; width: 500px; font-size: 50px;"><s:property
				value="#commentlist.content" /></textarea>
		<br>
	</s:iterator>
	<script> 
        var text1 = document.getElementById("textarea1");
        autoTextarea(text1);// 调用
		var text2 = document.getElementById("textarea2");
		autoTextarea(text2);// 调用
		var text3 = document.getElementById("textarea3");
		autoTextarea(text3);// 调用
    </script>
</body>
</html>
