<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>核销</title>
    <style>
        #mainDiv{
            margin-top: 10%;
            margin-left: 10%;
            margin-right: 10%;
            width:80%;
            height: 1200px;
            text-align: center;
            border:1px solid darkorange;
        }
        form{
            margin-top: 20%;
            width: 100%;
            height: 200px;
        }
        input{
            border:1px solid black;
            width: 80%;
            height: 40%;
            font-size: 60px;
        }
        h1{
            font-size: 60px;
            margin-top: 3%;
        }
        h2{
            font-size: 45px;
            font-weight: normal;
            margin-top: 10%;
        }
        table{
            width: 100%;
            height: 40px;
            font-size: 35px;
            /*border: 1px solid darkred;*/
        }
        tr{
            display: block;
            margin-top: 10px;
        }
        td{
            width: 500px;
            /*border: 1px solid darkred;*/
        }
    </style>
</head>
<body>

<!--拍照按钮-->
<div id="mainDiv">
    <div id="verifiResultDiv">
        <h1 style="margin-top: 20%">核销结果</h1>
        <h2 id="verifiResult" style="margin-top: 10%"></h2>
    </div>
    <div id="orderMsg">
        <HR align=center width=80% color=darkred SIZE=1 style="margin-top: 20%">
        <h2 id="orderDate" style="margin-top: 10%"></h2>
        <table id="orderTable">
            <tr>
                <td style="text-align: left; padding-left: 10%;">时间段</td>
                <td style="text-align: right; padding-right: 10%;">预约人数</td>
            </tr>
            <tr>
                <td style="text-align: left; padding-left: 10%;"></td>
                <td style="text-align: right; padding-right: 10%;"></td>
            </tr>
            <tr style="margin-top: 20px">
                <td style="text-align: left; padding-left: 10%;">姓名</td>
                <td style="text-align: right; padding-right: 10%;">手机号</td>
            </tr>
            <tr>
                <td style="text-align: left; padding-left: 10%;"></td>
                <td style="text-align: right; padding-right: 10%;"></td>
            </tr>
        </table>
        <HR align=center width=80% color=darkred SIZE=1 style="margin-top: 10%">
    </div>

    <form name="form" method="post" action="javascript:">
        <input type="file" name="picpath" id="picFile"style="display: none;"
               onchange="readFile(this)" multiple="multiple" accept="image/*" />
        <input type="button" value="拍照核销预约二维码" onclick="document.form.picpath.click()">
    </form>
</div >

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/layer.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        document.getElementById("orderMsg").style.display = "none";
        document.getElementById("verifiResultDiv").style.display = "none";
    })


    function readFile(obj){
        var file = obj.files[0];
        //判断类型是不是图片  不难发现这个检测是基于正则表达式的，因此可以进行各种复杂的匹配，非常有用。
        if(!/image\/\w+/.test(file.type)){
            alert("请确保文件为图像类型");
            return false;
        }
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function(e){
            getResultbyOCR(this.result);
        }
    }
    function getResultbyOCR(imagesdata) {
        var requestdata = imagesdata;
        $.ajax({
            type: "POST",
            url: "/getResultbyOCR",
            data: JSON.stringify(requestdata),
            // 将对象序列化成JSON字符串
            dataType: "json",
            // beforeSend: function () {
            //     this.layerIndex = layer.load(0, { shade: [0.5, '#393D49'],time: 60*1000});
            // },
            contentType: 'application/json;charset=utf-8',
            // 设置请求头信息
            success: function (result) {
                if(result.code == "0"){
                    alert(result.msg);
                    document.getElementById("orderMsg").style.display = "none";
                    document.getElementById("verifiResult").innerText = "核销失败";
                    document.getElementById("verifiResultDiv").style.display = "block";
                }else if(result.code == "1") {
                    alert(result.msg);
                    var userOrder = result.order;
                    document.getElementById("orderDate").innerText = userOrder.uDate;
                    document.getElementById("orderMsg").style.display = "block";
                    document.getElementById("verifiResult").innerText = "核销成功";
                    document.getElementById("verifiResultDiv").style.display = "block";
                    var tr = $("#orderTable tr");
                    var tr1 = $(tr[1]).find("td");
                    $(tr1[0]).html(userOrder.remark);
                    $(tr1[1]).html(userOrder.uChildNum + 1);
                    var tr3 = $(tr[3]).find("td");
                    $(tr3[0]).html(userOrder.uName);
                    $(tr3[1]).html(userOrder.uPhone);

                    //window.location.href = "../home.html";

                // }else if(result.code == "2") {
                //     alert(result.msg);
                //     window.location.href = "../admin.html";
                }else {
                    alert("核销失败");
                    document.getElementById("orderMsg").style.display = "none";
                    document.getElementById("verifiResult").innerText = "核销失败";
                    document.getElementById("verifiResultDiv").style.display = "block";
                }
            },
            error: function (data) {
                alert("核销失败");
                document.getElementById("orderMsg").style.display = "none";
                document.getElementById("verifiResult").innerText = "核销失败";
                document.getElementById("verifiResultDiv").style.display = "block";
            }
        });
    }

</script>
</body>
</html>