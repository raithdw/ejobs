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
        background-image: url("http://www.wallpapers4u.org/wp-content/uploads/stains_light_background_texture_50519_1920x1080.jpg");
        margin-top: inherit;
    }
    .descrip{
        font-size: 30px;
        font-family: sans-serif;
        color: #3D0C02;
        text-align: left;
    }
    .title{
        font-size: 50px;
        font-family: Cambria;
        text-align: center;
        font-weight: bold;
        text-shadow: 2px 2px 4px #000000;
        color: mediumpurple;
    }
    .titledesc{
        font-size: 35px;
        font-family: Cambria;
        text-align: center;
        font-weight: bold;
        color: #3D0C02;

    }
    h1{
        font-size: 40px;
        font-family: Cambria;
        text-shadow: 2px 2px 4px #000000;
        color: mediumpurple;
        font-weight: bold;
    }

</style>
<head>
    <title>Details</title>
</head>
<body>
<div class="title">
    Job Title:
    <br>
</div>
<div class="titledesc">
    <%=request.getParameter("title")%>
</div>
<br>
    <h1>
        Job Description:
    </h1>

<div class="descrip">
   <%=request.getParameter("description")%>
</div>


</body>
</html>
