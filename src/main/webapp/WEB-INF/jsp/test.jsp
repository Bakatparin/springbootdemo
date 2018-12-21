<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/15
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        document.forms[0].submit();


        (function () {
            var b = {};
            var a = {};
            a.PADCHAR = "=";
            a.ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
            a.makeDOMException = function () {
                var f, d;
                try {
                    return new DOMException(DOMException.INVALID_CHARACTER_ERR)
                } catch (d) {
                    var c = new Error("DOM Exception 5");
                    c.code = c.number = 5;
                    c.name = c.description = "INVALID_CHARACTER_ERR";
                    c.toString = function () {
                        return "Error: " + c.name + ": " + c.message
                    };
                    return c
                }
            };
            a.getbyte64 = function (e, d) {
                var c = a.ALPHA.indexOf(e.charAt(d));
                if (c === -1) {
                    throw a.makeDOMException()
                }
                return c
            };
            a.decode = function (f) {
                f = "" + f;
                var j = a.getbyte64;
                var h, e, g;
                var d = f.length;
                if (d === 0) {
                    return f
                }
                if (d % 4 !== 0) {
                    throw a.makeDOMException()
                }
                h = 0;
                if (f.charAt(d - 1) === a.PADCHAR) {
                    h = 1;
                    if (f.charAt(d - 2) === a.PADCHAR) {
                        h = 2
                    }
                    d -= 4
                }
                var c = [];
                for (e = 0; e < d; e += 4) {
                    g = (j(f, e) << 18) | (j(f, e + 1) << 12) | (j(f, e + 2) << 6) | j(f, e + 3);
                    c.push(String.fromCharCode(g >> 16, (g >> 8) & 255, g & 255))
                }
                switch (h) {
                    case 1:
                        g = (j(f, e) << 18) | (j(f, e + 1) << 12) | (j(f, e + 2) << 6);
                        c.push(String.fromCharCode(g >> 16, (g >> 8) & 255));
                        break;
                    case 2:
                        g = (j(f, e) << 18) | (j(f, e + 1) << 12);
                        c.push(String.fromCharCode(g >> 16));
                        break
                }
                return c.join("")
            };
            a.getbyte = function (e, d) {
                var c = e.charCodeAt(d);
                if (c > 255) {
                    throw a.makeDOMException()
                }
                return c
            };
            a.encode = function (f) {
                if (arguments.length !== 1) {
                    throw new SyntaxError("Not enough arguments")
                }
                var g = a.PADCHAR;
                var h = a.ALPHA;
                var k = a.getbyte;
                var e, j;
                var c = [];
                f = "" + f;
                var d = f.length - f.length % 3;
                if (f.length === 0) {
                    return f
                }
                for (e = 0; e < d; e += 3) {
                    j = (k(f, e) << 16) | (k(f, e + 1) << 8) | k(f, e + 2);
                    c.push(h.charAt(j >> 18));
                    c.push(h.charAt((j >> 12) & 63));
                    c.push(h.charAt((j >> 6) & 63));
                    c.push(h.charAt(j & 63))
                }
                switch (f.length - d) {
                    case 1:
                        j = k(f, e) << 16;
                        c.push(h.charAt(j >> 18) + h.charAt((j >> 12) & 63) + g + g);
                        break;
                    case 2:
                        j = (k(f, e) << 16) | (k(f, e + 1) << 8);
                        c.push(h.charAt(j >> 18) + h.charAt((j >> 12) & 63) + h.charAt((j >> 6) & 63) + g);
                        break
                }
                return c.join("")
            };
            b.pay = function (d) {
                var c = encodeURIComponent(a.encode(d));
                location.href = "pay.htm?goto=" + c
            };
            b.decode = function (c) {
                return a.decode(decodeURIComponent(c))
            };
            window._AP = b
        })();

    </script>
</head>
<body>
<form name="punchout_form" method="post"
      action="https://openapi.alipay.com/gateway.do?sign=Qk8%2Fg1y5uZEdPLw7xdGgQTOL9ADWeqUX5r7o0yLETpUes0JTRvmOQOW12nG5LHdUc80z5s1FjTsUzys0J6dGmf4GJ3aB5YjbhHKV2aq1Ate5Yprtz9%2BaRGfe%2Bbbo%2FhzoTFh8BT8CaEEwBcbZ2oKYZWfcGGPvf5%2B%2B5azz4%2FKkZkI5qyQ%2BT63%2FwtU2I5xuWVFeKo6WJIfU5uDOINYKiswr0%2Fy4jzNHzgKOI9lifqXR5EaN%2FpXORjWsammIl5Tnb8jGz9B2GEVDDrGvUxSScRxX6Oxe1%2F3n9onI0q3gcf%2Bm8vw7lP4s2p6wMUQFHJvkn6w3QE05iB%2BcJv4rq34%2FB8%2FOsg%3D%3D&timestamp=2018-11-15+17%3A18%3A11&sign_type=RSA2&notify_url=http%3A%2F%2Fdev.innocity2.com%2Fonlineweb%2Fmobile%2Fpay%2FbackAliMobileResponse&charset=UTF-8&app_id=2017101109242475&method=alipay.trade.wap.pay&return_url=http%3A%2F%2Fdev.innocity2.com%2Fonlineweb%2Fmobile%2Fpay%2FfrontAliMobileResponse&version=1.0&alipay_sdk=alipay-sdk-java-dynamicVersionNo&format=json">
    <input type="hidden" name="biz_content"
           value="{&quot;out_trade_no&quot;:&quot;uQDviokN7HSoEv92ggRajg%3D%3D&quot;,&quot;product_code&quot;:&quot;QUICK_WAP_PAY&quot;,&quot;subject&quot;:&quot;hs1测试专利5&quot;,&quot;timeout_express&quot;:&quot;2m&quot;,&quot;total_amount&quot;:&quot;2000&quot;}">
    <input type="submit" value="立即支付" style="display:none">
</form>
</body>
</html>
