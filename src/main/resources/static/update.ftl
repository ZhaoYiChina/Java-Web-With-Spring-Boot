<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>预约修改</title>
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
        input{
            font-size: 40px;
            font-weight: normal;
            padding: 10px;
        }
        select{
            font-size: 40px;
            font-weight: normal;
            padding: 5px;
        }
        /*div{*/
            /*border:1px solid red;*/
        /*}*/
        p{
            font-size: 35px;
        }
        button{
            background: #017fcb;
            width: 200px;
            height: 100px;
            border-radius: 50px;
            font-size: 40px;
            color:white;
            outline: none;
            margin-top: 10%;
        }
    </style>
</head>
<body>
    <div style="margin-top: 5%; margin-left: 5%; margin-right: 5%; margin-bottom: 150px">
        <div>
            <#--<img src="image/headImage.png" style="border-radius: 30px; width:100%; height:100%; object-fit: cover; position: relative;">-->
        </div>
        <div style="text-align: center">
            <h1>「故宫里的神兽世界」<br/>防控疫情在线预约</h1>
        </div>
        <div style="border: 1px solid darkred; padding:10px; margin-top: 20px;">
            <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为配合疫情防控期间对客流量的控制，请登记您预计到访的时间。</p>
            <p>【展览时间】</br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2021年2月5日 - 3月31日</p>
            <p>【开放时间】<br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;周二-周日 10:00-21:30（20:30停止入场，每周一例行闭馆，法定节假日正常开放。）</p>
            <p>【展览地点】<br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;七宝万科广场一楼，近乐聚广场（商场2号门）</p>
            <p>【展览地址】</br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;上海市闵行区漕宝路3366号</p>
            <p>【交通信息】<br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;公交：地铁9号线七宝站2号口出<br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;自驾：建议从民主路青年路驶入商场地下车库</p>
            <p>【温馨提示】<br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;进入商场时请配合出示健康码、测温等防疫检测流程。<br/>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;参观展览请全程佩戴口罩。</p>
        </div>
        <#--<div style="border: 1px solid #7c7c7c; margin-top: 20px; text-align: center; color: orange;" id="order"; onclick="seeOrder()">-->
            <#--<p>我的预约（Booking）</p>-->
        <#--</div>-->
        <div style="margin-right: 5%; margin-top: 5%; text-align: left;">
            <input id="orderId" value="${orderId}" type="hidden">
            <h2>日期</h2>
            <div class="mycontainer">
                <input type="date" id="uDate" style="border: 1px solid darkred; border-radius: 10px;" max="2021-03-31" min=""/>
            </div>

            <h2>时间段</h2>
            <div class="mycontainer">
                <select style="	width:68%; border: 1px solid darkred; border-radius: 10px; padding: 10px;" id="uPeriod">
                    <option value="1">10:00-12:00</option>
                    <option value="2">12:00-14:00</option>
                    <option value="3">14:00-16:00</option>
                    <option value="4">16:00-18:00</option>
                    <option value="5">18:00-20:30</option>
                </select>
            </div>

            <h2>真实姓名</h2>
            <!--<p>(如多人用"，"分隔)</p>-->
            <div class="mycontainer">
                <input style="border: 1px solid darkred; border-radius: 10px;" type="text" placeholder="输入姓名" id="uName">
            </div>

            <!--<h2>预约人手机号</h2>-->
            <!--<div class="mycontainer">-->
                <!--<input style="border: 1px solid darkred; border-radius: 10px;" type="number" placeholder="输入手机号" id="uPhone">-->
            <!--</div>-->

            <h2>本人身份证号码</h2>
            <div class="mycontainer">
                <input style="border: 1px solid darkred; border-radius: 10px;" type="text" placeholder="输入身份证号" id="uIdNumber">
            </div>

            <h2>儿童人数</h2>
            <div class="mycontainer">
                <select style="	width:68%; border: 1px solid darkred; border-radius: 10px; padding: 10px;" id="uChildNum">
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">其他</option>
                </select>
            </div>

            <div style="text-align: center">
                <button id="submitId" type="button" onclick="submitOrder()">提交</button>
            </div>

        </div>
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                var time = new Date();
                var day = ("0" + time.getDate()).slice(-2);
                var month = ("0" + (time.getMonth() + 1)).slice(-2);
                var today = time.getFullYear() + "-" + (month) + "-" + (day);
                $('#uDate').val(today);

                var myDate = new Date;
                var year = myDate.getFullYear(); //获取当前年
                var mon = myDate.getMonth()+1 < 10 ? "0"+(myDate.getMonth()+1) : (myDate.getMonth()+1);//
                var dat = myDate.getDate() < 10 ? "0"+myDate.getDate() : myDate.getDate();
                $("#uDate").attr("min",year+"-"+mon+"-"+dat);
            })



            function seeOrder() {
                window.location.href = "../order.html";
            }

            function dateFormat(dateData) {
                var date = new Date(dateData)
                var y = date.getFullYear()
                var m = date.getMonth() + 1
                m = m < 10 ? ('0' + m) : m
                var d = date.getDate()
                d = d < 10 ? ('0' + d) : d
                const time = y + '-' + m + '-' + d
                return time
            }

            function submitOrder() {

                $("#uDate").on("change",function(){
                    pID = $("option:selected",this).val();//需求主键
                        queryDemandByPid(pID);
                    });

                var chineseReg = /^[\u4E00-\u9FA5]{2,20}$/;
                if(!chineseReg.test(document.getElementById("uName").value)) {
                    alert('【姓名】 请填写2个以上中文!');
                    document.getElementById("uName").focus();
                    return false;
                }

                // var phoneNumReg = /(^[0-9]{3,4}\-[0-9]{7}$)|(^[0-9]{7}$)|(^[0-9]{3,4}[0-9]{7}$)|(^0{0,1}13[0-9]{9}$)/
                // if(!phoneNumReg.test(document.getElementById("uPhone").value)) {
                //     alert('请填写正确电话号码!!');
                //     document.getElementById("uPhone").focus();
                //     return false;
                // }

                var date = document.getElementById("uDate").valueAsDate;
                var uDate = dateFormat(date)
                var dayOfWeek = document.getElementById("uDate").valueAsDate.getDay();
                var uPeriod = document.getElementById("uPeriod").value;
                var utime = new Date();
                var uday = ("0" + utime.getDate()).slice(-2);
                var umonth = ("0" + (utime.getMonth() + 1)).slice(-2);
                var utoday = utime.getFullYear() + "-" + (umonth) + "-" + (uday);

                if(uDate > "2021-03-31" || uDate <= utoday || uDate < "2021-02-05"){
                    alert("尊敬的用户您好！您预约的时间不在展览时间范围内，请重新选择。")
                }else {
                    if(uDate == "2021-02-08"  || uDate == "2021-02-15"  || uDate == "2021-02-22"
                            || uDate == "2021-03-01" || uDate == "2021-03-08" || uDate == "2021-03-15"
                            || uDate == "2021-03-22" || uDate == "2021-03-29"){
                        alert("尊敬的用户您好！展览期间每逢周一闭馆休整，请重新选择。")
                    }else {
                        // if((dayOfWeek == 2 || dayOfWeek == 3 || dayOfWeek == 4) && uPeriod == 5){
                        //     alert("尊敬的用户您好！展会周二至周四期间不能预约18:00-20:00时间段的场次，请重新选择。")
                        // }else {
                        var uIdNumber = document.getElementById("uIdNumber").value;
                        if (checkIdcard2(uIdNumber)) {
                            // 提交数据
                            var uName = document.getElementById("uName").value;
                            var uChildNum = document.getElementById("uChildNum").value;
                            var orderId = document.getElementById("orderId").value;
                            $.ajax({
                                type: "post",
                                url: "/submitOrder",
                                data: {
                                    "uDate": uDate,
                                    "uPeriod": uPeriod,
                                    "uName": uName,
                                    "uIdNumber": uIdNumber,
                                    "uChildNum": uChildNum,
                                    "orderId": orderId
                                },
                                dataType: "json",
                                success: function (result) {
                                    if (result.code == "0") {
                                        alert(result.msg);
                                    } else if (result.code == "1") {
                                        alert(result.msg);
                                        var btn = document.getElementById("submitId");
                                        btn.style.background = "green";
                                        btn.innerHTML = "修改成功";
                                        window.location.href = "../order.html";
                                        // window.location.reload();
                                    } else if (result.code == "2") {
                                        alert(result.msg);
                                        var btn = document.getElementById("submitId");
                                        btn.style.background = "yellow";
                                        btn.innerHTML = "修改失败";
                                    } else {
                                        alert(result.msg);
                                        var btn = document.getElementById("submitId");
                                        btn.style.background = "yellow";
                                        btn.innerHTML = "修改失败";
                                    }
                                },
                                error: function (data) {
                                    alert("失败");
                                }
                            })
                        }
                        // }
                    }
                }
            }


            function checkIdcard2(idcard){
                var Errors=new Array(
                    "验证通过!",
                    "【身份证号码】 位数不对!",
                    "【身份证号码】 出生日期超出范围或含有非法字符!",
                    "【身份证号码】 校验错误!",
                    "【身份证号码】 地区非法!"
                );
                var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
                var idcard,Y,JYM;
                var S,M;
                var idcard_array = new Array();
                idcard_array = idcard.split("");
//地区检验
                if(area[parseInt(idcard.substr(0,2))]==null)
                {
                    alert(Errors[4]);
                    return false ;
                }
//身份号码位数及格式检验
                switch(idcard.length){
                    case 15:
                        if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
                            ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
                        } else {
                            ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
                        }
                        if(ereg.test(idcard)) return true;
                        else
                        {
                            alert(Errors[2]);
                            return false;
                        }
                        break;
                    case 18:
//18位身份号码检测
//出生日期的合法性检查
//闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
//平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
                        if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){
                            ereg=/^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
                        } else {
                            ereg=/^[1-9][0-9]{5}(19|20)[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
                        }
                        if(ereg.test(idcard)){//测试出生日期的合法性
//计算校验位
                            S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
                                + (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
                                + (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
                                + (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
                                + (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
                                + (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
                                + (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
                                + parseInt(idcard_array[7]) * 1
                                + parseInt(idcard_array[8]) * 6
                                + parseInt(idcard_array[9]) * 3 ;
                            Y = S % 11;
                            M = "F";
                            JYM = "10X98765432";
                            M = JYM.substr(Y,1);//判断校验位
                            if(M == idcard_array[17]) return true; //检测ID的校验位
                            else
                            { alert(Errors[3]);
                                return false;
                            }
                        }
                        else
                        {
                            alert(Errors[2]);
                            return false;
                        }
                        break;
                    default:
                        alert(Errors[1]);
                        return false ;
                        break;
                }
            }
        </script>
    </div>
</body>
</html>