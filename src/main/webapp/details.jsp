<%--
  Created by IntelliJ IDEA.
  User: TikiMikiAnki
  Date: 16.04.2018
  Time: 19:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    body{
        background-color:#92a8d1;
    }
    .descrip{
        font-size: 20px;
        font-family: Verdana;
        color: darkred;
    }
    .title{
        font-size: 30px;
        font-family: Cambria;
        text-align: center;
        font-weight: bold;
    }

</style>
<head>
    <title>Details</title>
</head>
<body>
<div class="title">
    <p>
        Job Title:
        <br>
        <%=request.getParameter("title")%>

</div>
<br>
<br>
<div class="descrip">
    <p>
    Job Description:
        <br>
        <br>
    <%=request.getParameter("description")%>
</div>



</body>
</html>
