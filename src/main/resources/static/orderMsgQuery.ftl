<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>查询</title>
    <link rel="stylesheet" href="css/home.css">
    <style>
        h1{
            font-size: 60px;
            margin-top: 3%;
        }
        h2{
            font-size: 45px;
            font-weight: normal;
            margin-top: 10%;
        }
        div{
            /*border:1px solid darkorange;*/
        }
        /*button{*/
        /*background: #017fcb;*/
        /*width: 130px;*/
        /*height: 70px;*/
        /*border-radius: 15px;*/
        /*color:white;*/
        /*outline: none;*/
        /*margin-left: 10px;*/
        /*font-size: 35px;*/
        /*}*/
        p{
            font-size: 35px;
        }
        tr{
            /*border:1px solid #ccc;*/
            /*border: 1px solid darkred;*/
        }
        td{
            /*border:1px solid #ccc;*/
            /*border: 1px solid darkred;*/
        }
        table{
            width: 100%;
            height: 40px;
            font-size: 35px;
            /*border: 1px solid darkred;*/
        }

        #orderDiv{
            margin-top: 5%;
            margin-left: 5%;
            margin-right: 5%;
            margin-bottom: 150px;
            border: 1px solid darkred;
            border-radius: 30px;
            background: white;
        }
        body{
            background: #F0F0F0;
        }
    </style>
</head>
<body>
<div id="orderDiv" style="margin-top: 5%; margin-left: 5%; margin-right: 5%; margin-bottom: 150px; border: 1px solid darkred; ">
    <div style="text-align: center">
        <img src="image/headImage.png" style="border-radius: 30px; width:100%; height:100%; object-fit: cover; position: relative;">
        <h2>「故宫里的神兽世界」预约查询</h2>
        <HR align=center width=80% color=darkred SIZE=1>
        <table>
            <tr>
                <td style="text-align: left; padding-left: 10%;">日期</td>
                <td style="text-align: right; padding-right: 10%;">预约人数</td>
                <td style="text-align: right; padding-right: 10%;">核销人数</td>
            </tr>
            <tr>
                <td style="text-align: left; padding-left: 10%;">${userOrder.remark}</td>
                <td style="text-align: right; padding-right: 10%;">${userOrder.uPeroid}</td>
                <td style="text-align: right; padding-right: 10%;">${userOrder.uChildNum + 1}</td>
            </tr>
        </table>
    </div>
</div>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">


</script>
</body>
</html>