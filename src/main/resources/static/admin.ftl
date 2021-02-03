<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>LinkA预约查询及核销系统</title>
    <style>
        #find, #verif{
            border:1px solid red;
            background: orange;
            width: 80%;
            height: 100px;
            border-radius: 20px;
            color:white;
            outline: none;
            margin-top: 20%;
            font-size: 45px;
            margin-left: 10%;
            margin-right: 10%;
        }
        h1{
            margin-top: 20%;
            font-size: 60px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div>
        <h1>「故宫里的神兽世界」<br>预约查询及核销系统</h1>
        <button id="find" type="button" onclick="gotoOrderList()">查询</button>
        <button id="verif" type="button" onclick="gotoOrderVerifi()">核销</button>
    </div>

    <script type="text/javascript" src="js/jquery.min.js"></script>

    <script type="text/javascript">


        function gotoOrderList() {
            window.location.href = "../orderDateQuery.html";
        }

        function gotoOrderVerifi() {
            window.location.href = "../orderVerifi.html";
        }

    </script>
</body>
</html>