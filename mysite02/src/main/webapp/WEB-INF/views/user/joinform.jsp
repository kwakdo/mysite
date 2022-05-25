<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>MySite</h1>
			<ul>
				<li><a href="">ë¡ê·¸ì¸</a><li>
				<li><a href="">íìê°ì</a><li>
				<li><a href="">íìì ë³´ìì </a><li>
				<li><a href="">ë¡ê·¸ìì</a><li>
				<li>ë ìëíì¸ì ^^;</li>
			</ul>
		</div>
		<div id="content">
			<div id="user">

				<form id="join-form" name="joinForm" method="" action="">
					<label class="block-label" for="name">ì´ë¦</label>
					<input id="name" name="name" type="text" value="">

					<label class="block-label" for="email">ì´ë©ì¼</label>
					<input id="email" name="email" type="text" value="">
					<input type="button" value="id ì¤ë³µì²´í¬">
					
					<label class="block-label">í¨ì¤ìë</label>
					<input name="password" type="password" value="">
					
					<fieldset>
						<legend>ì±ë³</legend>
						<label>ì¬</label> <input type="radio" name="gender" value="female" checked="checked">
						<label>ë¨</label> <input type="radio" name="gender" value="male">
					</fieldset>
					
					<fieldset>
						<legend>ì½ê´ëì</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>ìë¹ì¤ ì½ê´ì ëìí©ëë¤.</label>
					</fieldset>
					
					<input type="submit" value="ê°ìíê¸°">
					
				</form>
			</div>
		</div>
		<div id="navigation">
			<ul>
				<li><a href="">ìëí</a></li>
				<li><a href="">ë°©ëªë¡</a></li>
				<li><a href="">ê²ìí</a></li>
			</ul>
		</div>
		<div id="footer">
			<p>(c)opyright 2015, 2016, 2017, 2018</p>
		</div>
	</div>
</body>
</html>