<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册/登录</title>
    <style>
        h1{
            font-size: 50px;
            font-weight: normal;
        }
        #uPhone,#uMsg{
            border-top: 0px;
            border-left: 0px;
            border-right: 0px;
            font-size: 35px;
            margin-top: 30px;
            padding-bottom: 15px;
            padding-left: 15px;
            height: 50px;
        }
        #getMsg{
            border-top: 0px;
            border-left: 0px;
            border-right: 0px;
            font-size: 50px;
            margin-top: 35px;
            padding-bottom: 15px;
            height: 50px;
            padding-left: 25px;
        }
        #submit{
            border:1px solid red;
            background: orange;
            width: 100%;
            height: 100px;
            border-radius: 20px;
            color:white;
            outline: none;
            margin-top: 20%;
            font-size: 45px;
        }
        div{
            /*border:1px solid red;*/
        }
    </style>
</head>
<body>
    <div style="margin-top: 25%; margin-left: 10%; margin-right: 10%; margin-bottom: 120px;">
        <div style="text-align: center">
            <img src="image/logo.png" style="border-radius: 30px; width:60%; height:60%; object-fit: cover; position: relative;">
        </div>
        <div style="margin-top: 20%">
            <h1>验证手机号</h1>
            <input type="number" id="uPhone" placeholder="请输入手机号" id="uPhone" style="width: 100%">
            <input type="number" id="uMsg" placeholder="请输入验证码" id="uMsg" style="width: 350px;"/>
            <input type="text" placeholder="获取验证码" id="getMsg" style="width: 260px; text-align: center; margin-left: 70px" readonly="readonly" onclick="getCode()"/>
        </div>
        <div style="text-align: center">
            <button id="submit" type="submit" onclick="submitCode()">提交</button>
        </div>
    </div>
    <script type="text/javascript" src="js/jquery.min.js"></script>

    <script type="text/javascript">

        var wait = 60;

        function time() {
            if (wait == 0) {
                document.getElementById("getMsg").removeAttribute("disabled");
                document.getElementById("getMsg").value = "获取验证码";
                wait = 60;
            } else {
                document.getElementById("getMsg").setAttribute("disabled", true);
                document.getElementById("getMsg").value = wait + " s";
                wait--;
                setTimeout(function() {
                    time()
                }, 1000)
            }
        }

        function getCode() {
            // window.open( "http://192.168.0.133:8080/order.html");
            // alert(uPhone);
            var phoneNumReg = /^((13[0-9])|(14[5-9])|(15([0-3]|[5-9]))|(16[6-7])|(17[1-8])|(18[0-9])|(19[1|3])|(19[5|6])|(19[8|9]))\d{8}$/;
            if(!phoneNumReg.test(document.getElementById("uPhone").value)) {
                alert('请填写正确电话号码!!');
                document.getElementById("uPhone").focus();
                return false;
            }
            time();
            document.getElementById("getMsg").onclick=function(){time(this);}


            var uPhone = document.getElementById("uPhone").value;
            // alert("正在发送验证码");
            $.ajax({
                type: "post",
                url: "/getCode?uPhone=" + uPhone,
                success: function (result) {
                    if(result.code == "0"){
                        alert(result.msg);
                    }else if(result.code == "1") {
                        alert(result.msg);
                    }else {
                        alert("Else");
                    }
                },
                error: function (data) {
                    alert("失败");
                }
            })
        }

        function submitCode() {

            var uPhone = document.getElementById("uPhone").value;
            var uMsg = document.getElementById("uMsg").value;

            $.ajax({
                type: "post",
                url: "/loginSubmit?uPhone=" + uPhone + "&uMsg=" + uMsg,
                success: function (result) {
                    if(result.code == "0"){
                        alert(result.msg);
                    }else if(result.code == "1") {
                        alert(result.msg);
                        window.location.href = "../home.html";
                        // window.location.reload();
                    }else if(result.code == "2") {
                        alert(result.msg);
                        window.location.href = "../admin.html";
                    }else {
                        alert("Else");
                    }
                },
                error: function (data) {
                    alert("失败");
                }
            })
        }
    </script>
</body>
</html>