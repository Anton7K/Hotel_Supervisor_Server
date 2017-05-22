<%--
  Created by IntelliJ IDEA.
  User: Anton
  Date: 14.05.2017
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script src="js/jquery-1.11.3.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $.get("/sessionCheck", function (data) {
                alert(data);
            });
        });
    </script>
  </head>
  <body>
  $END$
  </body>
</html>
