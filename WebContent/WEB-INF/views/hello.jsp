<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>Getting Started: Serving Web Content</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
    <p>${name} !你xx好! </p>
    
    <spring:url value="string" var="url" />
    <form action="${url}" method="post">
    	<input name="name" id="name" />
    	<input type="submit" value="submit"/>
    </form>
</body>
</html>