// window.onload=function(){
//     alert(2);
// }
$(function () {

    //计算二月的天数
    var year = 2000;
    var month = 0;
    if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
        month = 29;
    } else {
        month = 28;
    }

    console.log(month)
    // alert(month);

    //test
    // var m = 1;
    // if(m<=1){
    //     alert(m);
    // } else if(m>1&&m<=10){
    //     alert("m>1&&m<=10");
    // }


    /** 星期的判断 **/


//     var now = new Date();
//     var day = now.getDay();
// var year =    now.getYear();
//
//     console.log("now+"+now);
//     console.log("year+"+year);
//     console.log("day+"+day);
//
//     var week;
//
//     switch (day) {
//         case 1:
//             week = "星期1";
//             break;
//         case 2:
//             week = "星期2"
//             break;
//         case 3:
//             week = "星期3"
//             break;
//         case 4:
//             week = "星期4"
//             break;
//         default:
//             week = "星期天";
//             break;
//     }
//
//
//     var weekday=new Array(7)
//     weekday[0]="Sunday"
//     weekday[1]="Monday"
//     weekday[2]="Tuesday"
//     weekday[3]="Wednesday"
//     weekday[4]="Thursday"
//     weekday[5]="Friday"
//     weekday[6]="Saturday"
//
//     document.write("Today it is " + weekday[day])
//
//     document.write("今天是" + week);//输出中文日期


    var hour = new Date().getHours();

    if (hour <= 12) {
        // alert("中午好! ")
    }


    var n = 10;

    // while(n>=5){
    //     // alert(n);
    //     n--;
    // }

    do {
        // alert(n)
        n++;

    } while (n < 15);


    /**
     * 计算100以内奇数
     */

    // var sum = 0;
    // for (var i = 1; i < 100; i += 2) {
    //     sum = sum + i;
    // }
    // alert("100以内所有奇数的和为:" + sum);
    // })

})
/**
 * 验证表单中的用户名和密码
 */


function check() {
    var uname = form1.username.value;
    var psw = form1.password.value;
    if ((uname == "") || (uname == null)) {
        alert("请输入用户名!")
        form1.username.focus();//用户名获取焦点
        return;
    } else if ((psw == "") || (psw == null)) {
        alert("请输入密码!")
        form1.password.focus();//用户名获取焦点
        return;
    } else {
        form1.submit();
    }
}

function f(num) {
    if (num <= 1) {
        return 1
    }
    else {
        return f(num - 1) * num
    }
}

function maskingKey() {
    // alert("jinlai")
    if (event.keyCode == 8) {

        event.keyCode = 0;
        event.returnValue = false;
        alert("当前设置不能用退格");
    }
    if (event.keyCode == 2) {
        event.returnValue = false;
        alert("禁止右键!");
    }
}
function array_max() {
    var i, max = this[i];

    for (i = 1; i < this.length; i++) {
        if (max < this[i]) {
            max = this[i];
        }
        return max;
    }
    Array.prototype.max = array_max();
    var x = new Array(1, 2, 3, 4, 5);
    var y = x.max();

    console.log("y的值为:" + y);


}

function gosite() {
//            alert("mouse")
    if (event.button == 2) {
        alert("鼠标右键点击")
    }
}


function select(element) {
    var lastSelection = null;
    var e, r, c

    if (element == null) {
        e = event.srcElement;
    } else {

        e = element;
    }

    if (e.tagName == "TD") {
        c = findcell(e);

        if (c != null) {
            if (lastSelection != null) {
                deselectworcell(window.lastSelection)
            }

            selecroworcell(c);
            lastSelection = c;
        }
    }
    //取消冒泡语句
    window.event.cancelBubble = true;

}


function pp() {
    alert("不可复制")
    return false;
}


function writeCookie() {
    document.cookie = encodeURI("username=" + document.fm2.username.value);
    document.cookie = encodeURI("psssword=" + document.fm2.psssword.value);
    document.fm2.submit();
}


function readCookie() {

    var cookieStr = decodeURI(document.cookie);

    var cookieArray = cookieStr.split(";");

    for (var i = 0; i < cookieArray.length; i++) {
        var cookieNum = cookieArray[i].split("=");
        var cookieName = cookieNum[0];
        var CookieValue = cookieNum[1];

        alert("cookie的名称为:" + cookieName + "cookie的值为:" + CookieValue)

    }

}


function redird() {
    var  cookiedate  = new Date();

    cookiedate.setDate(cookiedate.getDate()+1);

    alert(cookiedate);

    if(document.cookie.length!=0){
        alert("你已经来过了")
    }else {

        document.cookie = "test=test;expires="+cookiedate.toUTCString();
        Window.href="https://www.baidu.com/";

    }


}

