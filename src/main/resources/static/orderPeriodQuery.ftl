<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>时间段</title>
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
        input{
            border: none;
            font-size: 45px;
            font-weight: normal;
            width: 80%;
            height: 100px;
            text-align: center;
        }
        p{
            font-size: 35px;
        }
        tr{
            display: block;
            margin-top: 20px;
            /*border:1px solid #ccc;*/
            /*border: 1px solid darkred;*/
        }
        td{
            width: 500px;
            /*border: 1px solid darkred;*/

        }
        table{
            width: 80%;
            height: 40px;
            font-size: 35px;
            margin-top: 15px;
            margin-left: 10%;
            text-align: left;
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
        <#--<img src="image/headImage.png" style="border-radius: 30px; width:100%; height:100%; object-fit: cover; position: relative;">-->
        <h2>「故宫里的神兽世界」预约查询</h2>
        <HR align=center width=80% color=darkred SIZE=1>
        <input readonly id="uDateId" value="${uDate}"/>
        <HR align=center width=80% color=darkred SIZE=1>
        <table id="tab">
            <tr>
                <td style="margin-left: 10%; padding-left: 5px">时间段</td>
                <td style="text-align: right">预约人数</td>
                <td style="text-align: right">核销人数</td>
            </tr>
          <#list dataList as order>
           <tr class="jump">
               <input type="hidden"value="${order.uDate!'0'}">
               <td>${order.uDate!'0'}</td>
               <td style="text-align: right">${order.uChildNum!'0'}</td>
               <td style="text-align: right">${order.remark!'0'}</td>
           </tr>
          </#list>
        </table>
    </div>

</div>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">

    // $(".jump").click(function () {
    //     var uDate = document.getElementById("uDateId").value;
    //     var uPeriod = $(this).children("input").attr("value");
    //     window.location.href = "../orderMsgQuery?uDate=" + uDate + "&uPeriod=" + uPeriod;
    // })

</script>
</body>
</html>