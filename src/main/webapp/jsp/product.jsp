<%--
  Created by IntelliJ IDEA.
  User: Moataz Mujahid
  Date: 3/15/2024
  Time: 3:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>product</title>
</head>
<body>
<img src="data:${requestScope.PRODUCT.mimeType};base64,${requestScope.PRODUCT.productPic}" alt="Product Image">

</body>
</html>
