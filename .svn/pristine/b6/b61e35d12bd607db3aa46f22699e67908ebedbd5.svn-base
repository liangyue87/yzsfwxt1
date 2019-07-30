var QQV = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('输入QQ号'))
    } else if (/^[1-9][0-9]{4,10}$/.test(value)) {
        callback()
    } else {
        callback(new Error('输入正确的QQ号'))
    }
}

// 类似金钱,首位不为0,最多2位小数
export function checkNumPot2(rule, value, callback) {
    const reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
    if (!value) {
        return callback(new Error('请填写数字'))
    } else if (!reg.test(value)) {
        return callback(new Error('请填写数字,最多2位小数'))
    } else {
        callback()
    }
}

// 身份证
export function checkIdNum(rule, value, callback) {
    const reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if (!value) {
        return callback(new Error('证件号码不能为空'))
    } else if (!reg.test(value)) {
        return callback(new Error('证件号码不正确'))
    } else {
        callback()
    }
}

// 整数
export function checkInterNum(rule, value, callback) {
    const reg = /^[0-9]*[1-9][0-9]*$/;
    if (!value) {
        return callback(new Error('请填写整数'))
    } else if (!reg.test(value)) {
        return callback(new Error('请输入整数'))
    } else {
        callback()
    }
}

//密码校验
export function mima(rule, value, callback) {
    const reg = /^(?![0-9]+$)(?![a-zA-Z]+$)(?![_]+$)[0-9A-Za-z_]{8,16}$/;
    if (!value) {
        return callback(new Error('请输入密码'))
    } else if (!reg.test(value)) {
        return callback(new Error('密码需同时包含字母和数字，长度8-16位'))
    } else {
        callback()
    }
}

// 不可为空
export function checkNotnull(rule, value, callback) {
    if (!value) {
        return callback(new Error('不可为空'))
    }else{
        callback()
    }
}

//验证不为空时必须是整数
export function NullOrInteger(rule, value, callback) {
    const reg = /^(([1-9]\d+)|[0-9])(\.\d{1,2})?$/;
    if (value == '' || value == undefined || value == null) {
        callback()
    }else{
        if (!reg.test(value)) {
            return callback(new Error('请按正确格式输入，例如：88.88'))
        } else {
            callback()
        }
    }
}

export function NullOrFloat(rule, value, callback) {
    const reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
    if (value == '' || value == undefined || value == null) {
        callback()
    }else{
        if (!reg.test(value)) {
            return callback(new Error('请填写数字,最多2位小数'))
        } else {
            callback()
        }
    }
}

// 验证GPS格式
export function checkGPS(rule, value, callback) {
    if(value == '' || value == null){
        callback(new Error('不可为空'))
    }else{
        let val = value.replace('，', ',');
        let gps = val.split(',');
        if (gps.length != 3)
        {
            callback(new Error('请确认GPS数据格式：35XXXXX.XXX,5XXXXX.XXX,XX.XXX'));
        }
        else
        {
            if (gps[0].substring(0, 2) != "35" || gps[1].substring(0, 1) != "5")
            {
                callback(new Error('请确认GPS数据格式：35XXXXX.XXX,5XXXXX.XXX,XX.XXX'));
            }else{
                callback();
            }
        }
    }
}
/*|15[012356789]|17[678]|18[0-9]|14[57]|19[0-9]|16[0-9]*/
export default{
    QQ: [{ required: true, validator: QQV, trigger: 'blur' }],
    phone: [{ required: true, pattern: /^(0|86|17951)?(1[0-9]{2})[0-9]{8}$/, message: '目前只支持中国大陆的手机号码', trigger: 'blur' }],
    zjphone: [{ required: true, pattern: /^((0\d{2,3})-)?(\d{7,8})(-(\d{3,4}))?$/, message: '请输入正确的座机号码!', trigger: 'blur' }],
    yxyz: [{ required: true, pattern: /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/, message: '请输入有效的邮箱!', trigger: 'blur' }],
    numPot2: [{ required: true, validator: checkNumPot2, trigger: 'blur' }],
    InterNum: [{ required: true, validator: checkInterNum, trigger: 'blur' }],
    notNull: [{ required: true, validator: checkNotnull, trigger: 'blur' }],
    mimayz: [{ required: true, validator: mima, trigger: 'blur' }],
    gps: [{ required: true, validator: checkGPS, trigger: 'blur' }],
    NullOrInteger:[{required: true, validator: NullOrInteger, trigger: 'blur'}],
    NullOrFloat:[{required: true, validator: NullOrFloat, trigger: 'blur'}],
    //金额小写转大写
    toChies:function(money){
        //汉字的数字
        var cnNums = new Array('零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖');
        //基本单位
        var cnIntRadice = new Array('', '拾', '佰', '仟');
        //对应整数部分扩展单位
        var cnIntUnits = new Array('', '万', '亿', '兆');
        //对应小数部分单位
        var cnDecUnits = new Array('角', '分', '毫', '厘');
        //整数金额时后面跟的字符
        var cnInteger = '整';
        //整型完以后的单位
        var cnIntLast = '元';
        //最大处理的数字
        var maxNum = 999999999999999.9999;
        //金额整数部分
        var integerNum;
        //金额小数部分
        var decimalNum;
        //输出的中文金额字符串
        var chineseStr = '';
        //分离金额后用的数组，预定义
        var parts;
        if (money == '') { return ''; }
        money = parseFloat(money);
        if (money >= maxNum) {
            //超出最大处理数字
            return '';
        }
        if (money == 0) {
            chineseStr = cnNums[0] + cnIntLast + cnInteger;
            return chineseStr;
        }
        //转换为字符串
        money = money.toString();
        if (money.indexOf('.') == -1) {
            integerNum = money;
            decimalNum = '';
        } else {
            parts = money.split('.');
            integerNum = parts[0];
            decimalNum = parts[1].substr(0, 4);
        }
        //获取整型部分转换
        if (parseInt(integerNum, 10) > 0) {
            var zeroCount = 0;
            var IntLen = integerNum.length;
            for (var i = 0; i < IntLen; i++) {
                var n = integerNum.substr(i, 1);
                var p = IntLen - i - 1;
                var q = p / 4;
                var m = p % 4;
                if (n == '0') {
                    zeroCount++;
                } else {
                    if (zeroCount > 0) {
                        chineseStr += cnNums[0];
                    }
                    //归零
                    zeroCount = 0;
                    chineseStr += cnNums[parseInt(n)] + cnIntRadice[m];
                }
                if (m == 0 && zeroCount < 4) {
                    chineseStr += cnIntUnits[q];
                }
            }
            chineseStr += cnIntLast;
        }
        //小数部分
        if (decimalNum != '') {
            var decLen = decimalNum.length;
            for (var i = 0; i < decLen; i++) {
                var n = decimalNum.substr(i, 1);
                if (n != '0') {
                    chineseStr += cnNums[Number(n)] + cnDecUnits[i];
                }
            }
        }
        if (chineseStr == '') {
            chineseStr += cnNums[0] + cnIntLast + cnInteger;
        } else if (decimalNum == '') {
            chineseStr += cnInteger;
        }
        return chineseStr;
    },
    //获取当前日期 yyyy-mm-dd
    getNowDay(){
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        return currentdate;
    },
    // 获取给定日期的 n个月后的日期  给定日期格式如：val = 2019-02-26; n = 多少个月
    computeYmd(val, n) {
        let str = val.split('-');
        let d = new Date(str[0], str[1], str[2]);
        // 因为getMonth()获取的月份的值只能在0~11之间所以我们在进行setMonth()之前先给其减一
        d.setMonth((d.getMonth()-1) + n);
        let yy1 = d.getFullYear();
        let mm1 = d.getMonth()+1;
        let dd1 = d.getDate()-1;
        if(dd1 == '00'){
            mm1 = parseInt(mm1)-1;
            let new_date = new Date(yy1,mm1,1);
            dd1 = (new Date(new_date.getTime()-1000*60*60*24)).getDate()
        }
        if (mm1 < 10 ) {
            mm1 = '0' + mm1;
        }
        if (dd1 < 10) {
            dd1 = '0' + dd1;
        }
        return yy1 + '-' + mm1 + '-' + dd1;
    },
    //列表内时间格式转换
    formatChange: function (sj) {
        if(sj == null){
            return "";
        }else{
            let date = new Date(sj);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
            let Y = date.getFullYear() + '-';
            let M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
            let D = date.getDate() + ' ';
            //let h = date.getHours() + ':';
            let h = date.getHours()>=0 && date.getHours()<10 ? '0'+date.getHours()+ ':' : date.getHours()+ ':';
            let m = date.getMinutes()>=0 && date.getMinutes()<10 ? '0'+date.getMinutes()+ ':' : date.getMinutes()+ ':';
            let s = date.getSeconds()>=0 && date.getSeconds()<10 ? '0'+date.getSeconds() : date.getSeconds();
            return Y+M+D+h+m+s
        }
    },
    formatChange_date: function (sj) {
        if(sj == null){
            return "";
        }else{
            let date = new Date(sj);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
            let Y = date.getFullYear() + '-';
            let M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
            let D = date.getDate()>=0 && date.getDate()<10 ? '0'+date.getDate()+ '  ' : date.getDate()+ '  ';

            return Y+M+D
        }
    },
    //根据当前时间和随机数生成流水号
    randomNumber() {
        const now = new Date();
        let month = now.getMonth() + 1;
        let day = now.getDate();
        let hour = now.getHours();
        let minutes = now.getMinutes();
        let seconds = now.getSeconds();
        return now.getFullYear().toString() + month.toString() + day + hour + minutes + seconds + (Math.round(Math.random() * 89 + 100)).toString()
    },
    CstToGst: function(time, format){
        var t = new Date(time);
        var tf = function(i){return (i < 10 ? '0' : '') + i};
        return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
            switch(a){
                case 'yyyy':
                    return tf(t.getFullYear());
                    break;
                case 'MM':
                    return tf(t.getMonth() + 1);
                    break;
                case 'mm':
                    return tf(t.getMinutes());
                    break;
                case 'dd':
                    return tf(t.getDate());
                    break;
                case 'HH':
                    return tf(t.getHours());
                    break;
                case 'ss':
                    return tf(t.getSeconds());
                    break;
            }
        })
    }
}