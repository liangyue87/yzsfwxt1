<template>
	<el-table :data="tableData" style="width: 100%">
			<el-table-column property="fjmc" label="资料名" width="280"></el-table-column>
			<el-table-column property="fjhz" label="资料类型" width="180"></el-table-column>
			<el-table-column property="scsj" label="上传时间" width="200"></el-table-column>
			<el-table-column property="username" label="上传人" width="200"></el-table-column>
			<el-table-column label="操作" width="200">
				<template slot-scope="scope">
					<a href="#" @click="downloadFile_vue(scope.$index, scope.tableData,2)" >
						<label title="下载">
							<i class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i>
						</label>
					</a>
					<!--<el-button size="small" type="primary" @click="downloadFile_vue(scope.$index, scope.tableData,2)">下载</el-button>-->
				</template>
			</el-table-column>
	</el-table>
</template>

<script>
import axios from 'axios';
import {getYszls,downloads} from '../../api/Yswt';

export default {
	data() {
		return {
			tableData: [],
		};
	},
	
	methods: {
		//查询基本数据
		getYszl() {

			let params = {
				zbid:this.$route.query.tow1.zbid,
				xmid:this.$route.query.xmid
			}
			getYszls(params).then((res) => {
				if (typeof(res.data.YsZlxx[0].fjlj) != 'undefined'){
					this.tableData = res.data.YsZlxx;
				}

			});
		},
		downloadFile_vue(index,tableData,flag){
			tableData=this.tableData[index];

			let formData = new FormData();
			formData.append("path",tableData.fjlj);

			downloads(formData).then((res) => { // 处理返回的文件流
				if(flag==1){
					this.slide1=[];
					let reader = new FileReader()
					reader.onload = (e) => {
						let item = {};
						let img = new Image();
						img.src =e.target.result;
						item.msrc = item.src = e.target.result;
						// this.Imgsrc = e.target.result;
						item.h = img.height;
						item.w = img.width;
						this.slide1.push(item);
						this.$preview.openList(0, this.slide1);
						//this.msgFlag = true;
						//this.prop.src = this.Imgsrc
					}
					reader.readAsDataURL(res)
				}else{
					const content = res.data;
					const blob = new Blob([content]);
					const fileName = tableData.fjmc;

					if ('download' in document.createElement('a')) { // 非IE下载
						const elink = document.createElement('a')
						elink.download = fileName
						elink.style.display = 'none'
						elink.href = URL.createObjectURL(blob)
						document.body.appendChild(elink)
						elink.click()
						URL.revokeObjectURL(elink.href) // 释放URL 对象
						document.body.removeChild(elink)
					} else { // IE10+下载
						navigator.msSaveBlob(blob, fileName)
					}
				}
			})
		}
	},
	mounted() {
		this.getYszl();
	}
};
</script>

<style></style>
