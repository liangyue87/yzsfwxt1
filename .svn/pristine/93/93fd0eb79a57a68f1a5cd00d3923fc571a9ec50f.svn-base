<template>
	<div id="app">
		<transition name="fade"
		            mode="out-in">
			<router-view v-if="isRouterAlive"></router-view>
		</transition>
	</div>
</template>

<script>
	// import FileUpload from './common/components/form/file-upload/FileUpload.vue'
export default {
	name: 'app',
	components: {
        // FileUpload
	},
	provide(){
		return {
			reload : this.reload
		}
	},
	data(){
		return {
			isRouterAlive : true
		}
	},
	methods:{
		reload(){
			this.isRouterAlive = false;
			this.$nextTick(function (){
				this.isRouterAlive = true;
			})
		}
	},
	created () {
		// 当this.$router.options.routes的长度为1，且本地缓存存在菜单列表的时候才重新配置路由
		if (sessionStorage.getItem('menuArr') != undefined && sessionStorage.getItem('menuArr') != "") {
			let obj = JSON.parse(sessionStorage.getItem('menuArr'));
			let routes = obj.arr;
			//this.$router.addRoutes(routes)
			// this.$router不是响应式的，所以手动将路由元注入路由对象
			this.$router.options.routes = [];
			for(let o=0;o<routes.length;o++){
				this.$router.options.routes.push(routes[o]);
			}
			//this.$router.options.routes.push(routes[0])
			//let path = routes[0].children[0].url;
			//this.$router.push({ path: path });
		}
	}
}

</script>

<style lang="scss">
body {
	display: block;
	margin: 0px;
	padding: 0px;
	/*background: url(assets/bg1.jpg) center !important;
		background-size: cover;*/
	// background: #1F2D3D;
	font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, SimSun, sans-serif;
	font-size: 14px;
	-webkit-font-smoothing: antialiased;
}
#app {
	display: block;
	position: absolute;
	top: 0px;
	bottom: 0px;
	width: 100%;
}
#pswp{
	z-index: 9999;
}
.el-submenu [class^=fa] {
	vertical-align: baseline;
	margin-right: 10px;
}

.el-menu-item [class^=fa] {
	vertical-align: baseline;
	margin-right: 10px;
}

.toolbar {
	background: #ffffff;
	padding: 0px;
	//border:1px solid #dfe6ec;
	margin: 10px 0px;
	.el-form-item {
		margin-bottom: 10px;
	}
}

.fade-enter-active,
.fade-leave-active {
	transition: all .2s ease;
}

.fade-enter,
.fade-leave-active {
	opacity: 0;
}

/*解决表格错位*/
body .el-table th.gutter{
	display: table-cell!important;
}

</style>
