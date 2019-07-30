<!--项目基本信息模板界面-->
<template>
	<section>
		<el-row style="margin-top: 15px;">
			<el-col :span="24" >
					<el-row>
                        <el-col :span="4">
                            <el-tree :data="data" :props="defaultProps" :default-expand-all="true" @node-click="handleNodeClick"></el-tree>
                        </el-col>
                        <el-col :span="20" v-if="tyFlag==true">
                            <tyfwpz :qb="this.qb" :xmid="xmid" :isxmtc="isxmtc"></tyfwpz>
                        </el-col>
                        <el-col :span="20" v-if="ldFlag==true">
                            <ldfwpz :qb="this.qb" :xmid="xmid" :isxmtc="isxmtc"></ldfwpz>
                        </el-col>
                        <el-col :span="20" v-if="dxsFlag==true">
                            <dxsfwpz :qb="this.qb" :xmid="xmid" :isxmtc="isxmtc"></dxsfwpz>
                        </el-col>
                        <el-col :span="20" v-if="bfFlag==true">
                            <bffwpz :qb="this.qb" :xmid="xmid" :isxmtc="isxmtc"></bffwpz>
                        </el-col>
						<el-col :span="20" v-if="sxFlag==true">
                            <sxfwpz :qb="this.qb" :xmid="xmid" :isxmtc="isxmtc"></sxfwpz>
						</el-col>
                        <el-col :span="20" v-if="gwFlag==true">
                            <gwfwpz :qb="this.qb" :xmid="xmid" :isxmtc="isxmtc"></gwfwpz>
                        </el-col>
					</el-row>
			</el-col>
		</el-row>
	</section>
</template>

<script>
    import tyfwpz from '@/views/template/tyfwpz.vue';
    import gwfwpz from '@/views/template/gwfwpz.vue';
    import ldfwpz from '@/views/template/ldfwpz.vue';
    import dxsfwpz from '@/views/template/dxsfwpz.vue';
    import bffwpz from '@/views/template/bffwpz.vue';
    import sxfwpz from '@/views/template/sxfwpz.vue';
	export default {
        props:['qb','isxmtc'],
			data() {
			return {
				data: [{
					label: '项目范围配置',
					children: [{
						id:"TY",
						label: '庭院管理'
					},{
						id:"LD",
						label: '楼栋范围管理'
					},{
						id:"DXS",
						label: '地下室管理'
					},{
						id:"BF",
						label: '泵房管理'
					},{
						id:"SX",
						label: '水箱管理'
					},{
                        id:"GW",
                        label: '管网管理'
                    }]
				}],
				LDData:[],
                tyFlag:false,
				ldFlag:false,
				dxsFlag:false,
				bfFlag:false,
				sxFlag:false,
                gwFlag:false,
				defaultProps: {
					children: 'children',
					label: 'label'
				},
                formLabelWidth:"100px",
				xmid:this.$route.query.xmid
				//xmid:"1"
			}
		},

		methods: {
			handleNodeClick(data) {
				if(data.id!=null){
					this.tyFlag=false;
					this.ldFlag=false;
					this.dxsFlag=false;
					this.bfFlag=false;
					this.sxFlag=false;
                    this.gwFlag=false;
					switch (data.id) {
						case "TY":
							this.tyFlag=true;
							break;
						case "LD":
							this.ldFlag=true;
							break;
						case "DXS":
							this.dxsFlag=true;
							break;
						case "BF":
							this.bfFlag=true;
							break;
						case "SX":
							this.sxFlag=true;
							break;
                        case "GW":
                            this.gwFlag=true;
                            break;
					}
				}
			},


			LDHandleEdit_vue(index,row){

			},
			LDHandleDelete_vue(index,row){

			},
			LDHandleFq_vue(index,row){

			},



		},
		mounted() {
			this.tyFlag=true;
		},
        components:{
            "tyfwpz":tyfwpz,
            "ldfwpz":ldfwpz,
            "dxsfwpz":dxsfwpz,
            "bffwpz":bffwpz,
            "sxfwpz":sxfwpz,
            "gwfwpz":gwfwpz
        }
	}

</script>

