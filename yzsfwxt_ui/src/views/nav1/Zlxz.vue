<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section class="body_"  v-loading="loading"
			 element-loading-text="文件下载中，请稍后"
			 element-loading-spinner="el-icon-loading"
			 element-loading-background="rgba(0, 0, 0, 0.1)">
		<el-row>
			<el-col :span="24" class="toolbar">
			<!--列表-->
			<el-table :data="data" highlight-current-row style="width: 50%;">
				<el-table-column type="index" label="序号" width="100" align="center">
				</el-table-column>
				<el-table-column prop="filename" label="文件名称" min-width="250" align="center">
				</el-table-column>
				<el-table-column label="操作" width="120" align="center">
					<template slot-scope="scope">
						<label title="下载" @click="download(scope.row)">
							<i class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
					</template>
				</el-table-column>
			</el-table>
			</el-col>
		</el-row>
	</section>
</template>

<script>
	import { getFileList,download } from '../../api/Zlxz';

	export default {
		data() {
			return {
				data: [],
				loading: false
			}
		},
		methods: {
			getFileList(){
				getFileList().then(res => {
					this.data = res.data.row;
				})
			},
			download(row){
				this.loading = true;
				let formData = new FormData();
				formData.append("path",row.path);
				download(formData).then((res) => { // 处理返回的文件流
					const content = res
					const blob = new Blob([content]);
					const fileName = row.filename;
					if ('download' in document.createElement('a')) { // 非IE下载
						const elink = document.createElement('a');
						elink.download = fileName;
						elink.style.display = 'none';
						elink.href = URL.createObjectURL(blob);
						document.body.appendChild(elink);
						elink.click();
						URL.revokeObjectURL(elink.href) ;// 释放URL 对象
						document.body.removeChild(elink);
					} else { // IE10+下载
						navigator.msSaveBlob(blob, fileName);
					}
					this.loading = false;
				})
			}
		},
		mounted() {
			this.getFileList();
		}
	}

</script>

<style scoped>
	.body_ {
		height: 580px;
	}
</style>
