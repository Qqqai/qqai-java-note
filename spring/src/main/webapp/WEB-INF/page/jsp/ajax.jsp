<%--
  Created by IntelliJ IDEA.
  User: 2b
  Date: 2020/12/16
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<button id="btn">发送</button>
</body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<script>
    $(function () {
        let info = {
            name: "qqai",
            age: 21,
            sex: "男",
            birthday: new Date()
        }
        console.log(JSON.stringify(info))
        $("#btn").click(function () {
            $.ajax({
                url: "/ajax/test",
                data: JSON.stringify(info),
                type: "post",
                dataType: "json",
                contentType: "application/json;charset=UTF-8",
                success: function (data) {
                    console.log(data)
                    alert("阿巴阿巴阿巴 name:" + data.name + " age:" + data.age);
                }
            })
        })
    })
</script>
</html>
