<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
	<section>
		<el-row :gutter="10">
		<!--工具条-->
		<el-col :span="12" class="toolbar" style="margin: 0px;">
				<el-col :span="24" class="toolbar" >
					<el-col :span="24" class="toolbar" >
						<el-button type="primary" size="mini" v-on:click="openAddDay"><i class="iconfont icon-xinzeng"></i>新增</el-button>
					</el-col>
					<el-table :data="daysList" highlight-current-row v-loading="daysLoading" style="width: 100%;" :height="height">
						<el-table-column prop="DAY" label="日期" min-width="180" align="center">
						</el-table-column>
						<el-table-column prop="WEEK" label="星期" width="150" align="center">
						</el-table-column>
						<el-table-column prop="WORK" label="类型" width="150" align="center">
							<template slot-scope="scope">
								<span class="table_text" v-if="scope.row.WORK == 0">休息</span>
								<span class="table_text" v-if="scope.row.WORK == 1">工作</span>
							</template>
						</el-table-column>
						<el-table-column label="操作" min-width="120" align="center">
							<template slot-scope="scope">
								<label title="编辑" @click="updateDays(scope.$index, scope.row)"><!--(scope.$index, scope.row)-->
									<i class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
								<label title="删除" @click="deleteDays(scope.row.ID)">
									<i class="iconfont delete" style="cursor:pointer;padding: 2px;color: #f78989"></i></label>
								<!--<el-button size="small" type="warning" @click="openEditForm(scope.$index, scope.row)">编辑</el-button>
								<el-button size="small" type="danger" @click="deleteFk(scope.$index, scope.row)">删除</el-button>-->
							</template>
						</el-table-column>
					</el-table>
				</el-col>
			</el-col>

			<el-col :span="12" class="toolbar" style="margin: 0px;">
				<el-col :span="24" class="toolbar" >
					<el-col :span="24" class="toolbar" >
						<el-button type="primary" size="mini" v-on:click="openAddTime"><i class="iconfont icon-xinzeng"></i>新增</el-button>
					</el-col>
					<el-table :data="workTimeList" highlight-current-row v-loading="timeLoading" style="width: 100%;" :height="height">
						<el-table-column prop="ams" label="上午工作时间" width="140" align="center">
							<template slot-scope="scope">
								<span class="table_text">{{scope.row.ams}}</span>——
								<span class="table_text">{{scope.row.amf}}</span>
							</template>
						</el-table-column>
						<el-table-column prop="pms" label="下午工作时间" width="140" align="center">
							<template slot-scope="scope">
								<span class="table_text">{{scope.row.pms}}</span>——
								<span class="table_text">{{scope.row.pmf}}</span>
							</template>
						</el-table-column>
						<el-table-column prop="kssj" label="执行时间" width="200" align="center">
							<template slot-scope="scope">
								<span class="table_text">{{scope.row.kssj}}</span>——
								<span class="table_text">{{scope.row.jssj}}</span>
							</template>
						</el-table-column>
						<el-table-column label="操作" min-width="120" align="center">
							<template slot-scope="scope">
								<label title="编辑" @click="updateTime(scope.$index, scope.row)"><!--(scope.$index, scope.row)-->
									<i class="iconfont icon-ai-edit" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
								<label title="删除" @click="deleteTime(scope.row.id)">
									<i class="iconfont delete" style="cursor:pointer;padding: 2px;color: #f78989"></i></label>
								<!--<el-button size="small" type="warning" @click="openEditForm(scope.$index, scope.row)">编辑</el-button>
								<el-button size="small" type="danger" @click="deleteFk(scope.$index, scope.row)">删除</el-button>-->
							</template>
						</el-table-column>
					</el-table>
				</el-col>
			</el-col>
		</el-row>



		<!--新增编辑特殊工作日-->
		<el-dialog title="新增" :visible.sync ="daysFormVisible"  width="30%" :close-on-click-modal="false">
			<el-form :model="daysForm" label-width="120px" ref="daysForm" style="margin-top: 25px;">
				<el-form-item label="日期" prop="name">
					<div class="block">
						<el-date-picker
								v-model="daysForm.day"
								type="date" @change="getWeek"
								placeholder="选择日期" value-format="yyyy-MM-dd" format="yyyy-MM-dd">
						</el-date-picker>
					</div>
				</el-form-item>

				<el-form-item label="工作休息情况">
					<el-radio-group v-model="daysForm.work">
						<el-radio class="radio" :label="0" disabled>休息</el-radio>
						<el-radio class="radio" :label="1" disabled>工作</el-radio>
					</el-radio-group>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer" style="text-align: center;">
				<el-button size="mini" type="info" @click.native="daysFormVisible = false">
					<i class="iconfont icon-quxiao"></i>取消</el-button>
				<el-button size="mini" type="primary" @click.native="saveDays" :loading="daysButtonLoading">
					<i class="iconfont icon-queren"></i>保存</el-button>
			</div>
		</el-dialog>

		<!--新增上下班时间界面-->
		<el-dialog title="新增" :visible.sync ="timeFormVisible" width="45%" :close-on-click-modal="false">
			<el-form :model="timeForm" label-width="120px" ref="timeForm">
				<el-form-item label="上午上班时间" prop="name">
					<el-col :span="10">
						<div class="inline-block">
							<el-time-picker placeholder="上班时间"
									v-model="timeForm.ams" value-format="HH:mm" format="HH:mm">
							</el-time-picker>
						</div>
					</el-col>
					<el-col :span="10">
						<div class="inline-block">
							<el-time-picker placeholder="下班时间"
									v-model="timeForm.amf" value-format="HH:mm" format="HH:mm">
							</el-time-picker>
						</div>
					</el-col>
				</el-form-item>
				<el-form-item label="下午上班时间" prop="name">
					<el-col :span="10">
						<div class="inline-block">
							<el-time-picker placeholder="上班时间"
									v-model="timeForm.pms" value-format="HH:mm" format="HH:mm">
							</el-time-picker>
						</div>
					</el-col>
					<el-col :span="10">
						<div class="inline-block">
							<el-time-picker placeholder="下班时间"
									v-model="timeForm.pmf" value-format="HH:mm" format="HH:mm">
							</el-time-picker>
						</div>
					</el-col>
				</el-form-item>
				<el-form-item label="规则执行日期">
					<el-date-picker
							v-model="timeForm.kssj"
							type="date"
							placeholder="选择日期" value-format="yyyy-MM-dd" format="yyyy-MM-dd">
					</el-date-picker>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer" style="text-align: center;">
				<el-button size="mini" type="info" @click.native="timeFormVisible = false">
					<i class="iconfont icon-quxiao"></i>取消</el-button>

				<el-button size="mini" type="primary" @click="saveTime" :loading="timeButtonLoading">
					<i class="iconfont icon-queren"></i>提交</el-button>
			</div>
		</el-dialog>
	</section>
</template>

<script>
	import { getDaysList,getWorkTime,deleteDays,saveDays,deleteTime,saveTime } from '../../api/workday';

	export default {
		data() {
			return {
				height: 450,
				daysList:[],//特殊日期上班休息情况
				workTimeList:[],//上下班时间
				daysLoading: false,
				timeLoading: false,

				daysFormVisible: false,
				daysButtonLoading: false,
				daysForm :{
					id:'',
					day:'',
					work:-1,
					week:''
				},

				timeFormVisible: false,
				timeButtonLoading: false,
				timeForm :{
					id:'',
					ams:'',
					amf:'',
					pms:'',
					pmf:'',
					kssj:'',
					jssj:''
				}
			}
		},
		methods: {
			getDaysList() {
				this.daysLoading = true;
				getDaysList().then((res) => {
					this.daysList = res.data.list;
					this.daysLoading = false;
				});
			},
			getWorkTime() {
				this.timeLoading = true;
				getWorkTime().then((res) => {
					this.workTimeList = res.data.list;
					this.timeLoading = false;
				});
			},
			getWeek() {
				var show_day=new Array('星期日','星期一','星期二','星期三','星期四','星期五','星期六');
				let date = new Date(this.daysForm.day).getDay();
				this.daysForm.week = show_day[date];
				if(date == 0 || date == 6){
					this.daysForm.work = 1;
				}else {
					this.daysForm.work = 0;
				}

			},
			openAddDay(){
				this.daysForm = {
					id:'',
					day:'',
					work:-1,
					week:''
				},
				this.daysFormVisible = true;
			},
			updateDays(index,row){
				this.daysForm = {
					id:row.ID,
					day:row.DAY,
					work:row.WORK,
					week:row.WEEK
				},
				this.daysFormVisible = true;
			},
			saveDays(){
				if(this.daysForm.day == null || this.daysForm.day == ''){
					this.$message({
						message: '请选择时间',
						type: 'warning'
					});
					return;
				}
				let params = this.daysForm;
				saveDays(params).then((res) => {
					if(res.result == "success"){
						this.$message({
							message: '保存成功',
							type: 'success'
						});
					}else{
						this.$message({
							message: '保存失败',
							type: 'error'
						});
					}
					this.getDaysList();
					this.daysFormVisible = false;
				});
			},
			deleteDays(id){
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					let params = {id : id};
					deleteDays(params).then((res) => {
						if(res.result == "success"){
							this.$message({
								message: '保存成功',
								type: 'success'
							});
						}else{
							this.$message({
								message: '保存失败',
								type: 'error'
							});
						}
						this.getDaysList();
					});
				});
			},
			openAddTime(){
				this.timeForm = {
							id:'',
							ams:'08:00',
							amf:'12:00',
							pms:'14:00',
							pmf:'17:30',
							kssj:'',
							jssj:''
					},
				this.timeFormVisible = true;
			},
			updateTime(index,row){
				this.timeForm = row;
				this.timeFormVisible = true;
			},
			saveTime(){
				let flag = false;
				if(this.timeForm.id == ''){
					let obj = this.workTimeList;
					for(let i = 0;i<obj.length;i++){
						if(this.timeForm.kssj == obj[i].kssj){
							flag = true;
							break;
						}
					}
				}
				if(flag){
					this.$message({
						message: '选择的生效时间段已设置规则，请删除后重新添加',
						type: 'warning'
					});
					return;
				} else if(this.timeForm.ams == null || this.timeForm.ams == ''){
					this.$message({
						message: '请选择时间',
						type: 'warning'
					});
					return;
				}
				let params = this.timeForm;
				saveTime(params).then((res) => {
					if(res.result == "success"){
						this.$message({
							message: '保存成功',
							type: 'success'
						});
					}else{
						this.$message({
							message: '保存失败',
							type: 'error'
						});
					}
					this.getWorkTime();
					this.timeFormVisible = false;
				});
			},
			deleteTime(id){
				this.$confirm('确认删除该记录吗?', '提示', {
					type: 'warning'
				}).then(() => {
					let params = {id : id};
					deleteTime(params).then((res) => {
						if(res.result == "success"){
							this.$message({
								message: '保存成功',
								type: 'success'
							});
						}else{
							this.$message({
								message: '保存失败',
								type: 'error'
							});
						}
						this.getWorkTime();
					});
				});
			}
		},
		mounted() {
			this.getDaysList();
			this.getWorkTime();
		}
	}

</script>

<style scoped>

</style>
