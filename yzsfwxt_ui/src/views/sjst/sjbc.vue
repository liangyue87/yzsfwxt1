<!--项目基本信息模板界面-->
<template>
	<section>
		<el-row style="margin-top: 15px;">
			<el-col :span="24" >
					<el-row>
                        <el-col :span="4">
                            <el-tree :data="data" :props="defaultProps" :default-expand-all="true" @node-click="handleNodeClick"></el-tree>
                        </el-col>
                        <el-col  :span="20" v-if="xmjbxxFlag">
                            <xmjbxx :xmid="xmid"></xmjbxx>
                        </el-col>
                        <el-col :span="20" v-if="xmfwpzFlag">
                            <el-card  class="box-card"  style="margin-top: 15px;">
                                <div slot="header" class="clearfix">
                                    <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">项目配置</div>
                                </div>
                                <div class="text item">
                                    <el-tabs v-model="activeName" type="card">
                                        <el-tab-pane label="项目范围配置" name="1"><xmfwpz v-if="activeName=='1'" :isxmtc="isxmtc"></xmfwpz></el-tab-pane>
                                        <el-tab-pane label="物料清单配置" name="2"><wlqdpz :xmid="xmid" v-if="activeName=='2'" :isxmtc="isxmtc"></wlqdpz></el-tab-pane>
                                        <el-tab-pane label="工商表配置" name="3"><dbpz :xmid="xmid" v-if="activeName=='3'" :isxmtc="isxmtc"></dbpz></el-tab-pane>
                                        <el-tab-pane label="五级计量表配置" name="4"><wjjlbpz :xmid="xmid" v-if="activeName=='4'" :isxmtc="isxmtc"></wjjlbpz></el-tab-pane>
                                        <el-tab-pane label="居民表配置" name="5"><hbpz :xmid="xmid" v-if="activeName=='5'" :isxmtc="isxmtc"></hbpz></el-tab-pane>
                                    </el-tabs>
                                </div>
                            </el-card>
                        </el-col>
                        <el-col :span="20" v-if="yusuanFlag">
                            <YSbc :sfxs="'xmglys'"></YSbc>
                        </el-col>
					</el-row>
			</el-col>
		</el-row>
	</section>
</template>

<script>
    import xmjbxx from '@/views/sjst/editXmjbxx.vue';
    import xmfwpz from '@/views/template/xmfwpz.vue';
    import wlqdpz from '@/views/template/wlqdpz.vue';
    import dbpz from '@/views/template/dbpz.vue';
    import hbpz from '@/views/template/hbpz.vue';
    import wjjlbpz from '@/views/template/wjjlbpz.vue';
    import YSbc from '@/views/sjst/YSbc.vue';
	export default {
        props:['qb'],
			data() {
			return {
				data: [{
					label: '项目分类',
					children: [{
                        id:"XMJBXX",
                        label: '项目基本信息'
                    },{
						id:"XMFWPZ",
						label: '项目范围配置'
					},{
                        id:"XMYS",
                        label: '预算'
                    }]
				}],
                activeName:'1',
				LDData:[],
                xmfwpzFlag:false,
                yusuanFlag:false,
                xmjbxxFlag:false,
				defaultProps: {
					children: 'children',
					label: 'label'
				},
                formLabelWidth:"100px",
				xmid:this.$route.query.xmid,
                isxmtc:this.$route.query.isxmtc,
				//xmid:"1"
			}
		},

		methods: {
			handleNodeClick(data) {
				if(data.id!=null){
					this.xmfwpzFlag=false;
                    this.xmjbxxFlag=false;
                    this.yusuanFlag=false;
					switch (data.id) {
                        case "XMJBXX":
                            this.xmjbxxFlag=true;
                            break;
						case "XMFWPZ":
                            this.xmfwpzFlag=true;
                            break;
                        case "XMYS":
                            this.yusuanFlag=true;
                            break;
                        default:
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
			this.xmjbxxFlag=true;
		},
        components:{
            "xmjbxx":xmjbxx,
            "xmfwpz":xmfwpz,
            'wlqdpz': wlqdpz,
            'dbpz': dbpz,
            'hbpz': hbpz,
            'wjjlbpz': wjjlbpz,
            'YSbc':YSbc
        }
	}

</script>

