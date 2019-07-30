<template>
	<section>
		<el-table :data="Ysjezh" style="width: 100%">
			<el-table-column property="mc" label="预算分类" width="180"></el-table-column>
			<el-table-column property="ysje" label="金额" width="180"></el-table-column>
			<el-table-column property="bz" label="备注" width="180"></el-table-column>
		</el-table>
		<div>
			<table>
				<tr>
					<td style="width: 80px">
						<span><b>总计 ：</b></span>
					</td>
					<td>
						<!--<el-input v-model="this.ysrybz" readonly="true" style="width: 600px;"></el-input>-->
						<span>{{this.zje}}</span>
					</td>
				</tr>
				<tr>
					<td style="width: 80px">
						<span><b>大写 ：</b></span>
					</td>
					<td>
						<span>{{this.zje | toChies}}</span>
					</td>
				</tr>
			</table>
		</div>
	</section>
</template>

<script>
	import axios from 'axios'
	import {getyYsjezh} from '../../api/Yswt';
	export default {
		filters:{//局部过滤器

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
			}

		},
		data(){
			return{
				Ysjezh:[],
				zje: 0,//总金额
			};


		},
		methods:{
			getyYsjezh(){
				let params = {
					id:this.$route.query.tow1.zbid
				}
				getyYsjezh(params).then((res) => {
					this.Ysjezh = res.data.ysjezh;
					this.getZje();
				});

			},
			//得到总金额
			getZje() {
				let data = this.Ysjezh;
				this.zje = 0;
				data.forEach((a, index) => {
					if (a.ysje != '') {
						let num = parseFloat(a.ysje);
						if(  isNaN(num)){
							num = 0;
						}
						if (this.zje == 0) {
							this.zje = num;
						} else {
							this.zje = num + parseFloat(this.zje);
						}
					}
					this.zje = parseFloat(this.zje).toFixed(2);
				});

			},
		},
		mounted() {
			this.getyYsjezh();
		}
	}
</script>

<style>
</style>
