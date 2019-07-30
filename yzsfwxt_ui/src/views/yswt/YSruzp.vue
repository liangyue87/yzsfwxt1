<!--<script  type="text/javascript"src="../js/jquery.min.js"></script>-->
<template>
  <section>
    <el-row>
      <hhldsx :xmid="xmid" :activityid="activityId"></hhldsx>
      <el-col :span="24">
        <el-row style="margin-top: 15px;">
          <el-col :span="24">
            <!--项目基本信息模板-->
            <XMJBXX></XMJBXX>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="24" style="text-align: center;margin-top: 10px;"></el-col>
    </el-row>

    <el-card  class="box-card"  style="margin-top: 15px;">
      <div slot="header" class="clearfix">
        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">项目配置</div>
      </div>
      <div class="text item">
        <el-tabs v-model="activeName" type="card" >
          <el-tab-pane label="项目范围配置" name="1"><xmfwpz :qb="0" :isxmtc="'2'" v-if="activeName=='1'"></xmfwpz></el-tab-pane>
          <el-tab-pane label="物料清单配置" name="2"><wlqdpz :qb="0" :xmid="xmid" v-if="activeName=='2'"></wlqdpz></el-tab-pane>
          <el-tab-pane label="工商表配置" name="3"><dbpz :qb="0" :isxmtc="'2'" :xmid="xmid" v-if="activeName=='3'"></dbpz></el-tab-pane>
          <el-tab-pane label="五级计量表配置" name="4"><wjjlbpz :qb="0" :isxmtc="'2'" :xmid="xmid" v-if="activeName=='4'"></wjjlbpz></el-tab-pane>
          <el-tab-pane label="居民表配置" name="5"><hbpz :qb="0" :isxmtc="'2'" :xmid="xmid" v-if="activeName=='5'"></hbpz></el-tab-pane>
        </el-tabs>
      </div>

    </el-card>
    <el-card class="box-card"  style="margin-top: 15px;">
      <div slot="header" class="clearfix">
        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">审图成果</div>
      </div>
      <div class="text item">
        <el-row>
          <el-table :data="Data"style="width: 100%" :stripe="true" :show-header="false">
            <el-table-column
                    prop="name"
                    label="审图类型"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="psyl"
                    label="拍摄预览"
                    align="center">
              <template slot-scope="scope">
                <label title="预览"><i @click="stPreview_vue(scope.$index, scope.row)" class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                <span>(附件：{{scope.row.pictureNumber}}张)</span>
              </template>
            </el-table-column>
            <el-table-column
                    prop="glxz"
                    label="管理下载"
                    align="center">
              <template slot-scope="scope">
                <label title="管理"><i @click="stManage_vue(scope.$index, scope.row,'stcg')" class="iconfont icon-renyuanguanli" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                <!--<a :href="'#'" @click="stDownLoad_vue(scope.$index, scope.row)">下载</a>-->
                <span>(附件：{{scope.row.fjNumber}}张)</span>
              </template>
            </el-table-column>
          </el-table>
        </el-row>
        <el-row class="row"></el-row>
      </div>
    </el-card>
    <el-card class="box-card"  style="margin-top: 15px;">
      <div slot="header" class="clearfix">
        <div style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">设计成果</div>
      </div>
      <div class="text item">
        <el-row>
          <el-table :data="sjData"style="width: 100%" :stripe="true" >
            <el-table-column
                    prop="name"
                    label="设计成果类别"
                    align="center">
            </el-table-column>
            <el-table-column
                    prop="psyl"
                    label="图纸目录"
                    align="center">
              <template slot-scope="scope">
                <label title="预览"><i @click="stPreview_vue(scope.$index, scope.row)" class="iconfont icon-yulan" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                <span>(附件：{{scope.row.pictureNumber}}张)</span>
              </template>
            </el-table-column>
            <el-table-column
                    prop="glxz"
                    label="给施资料"
                    align="center">
              <template slot-scope="scope">
                <label title="管理"><i @click="stManage_vue(scope.$index, scope.row,'sjcg')" class="iconfont icon-renyuanguanli" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                <span>(附件：{{scope.row.fjNumber}}张)</span>
              </template>
            </el-table-column>
          </el-table>
        </el-row>
        <el-row class="row"></el-row>
      </div>
    </el-card>

    <el-dialog  :title="title" :visible.sync="stcgDialogFormVisible" width="50%">
      <el-table :data="stcgxqData"style="width: 100%" :stripe="true" >
        <el-table-column
                prop="fjmc"
                label="附件名称"
                align="center">
        </el-table-column>
        <el-table-column
                prop="fjhz"
                label="附件后缀"
                align="center">
        </el-table-column>
        <el-table-column
                prop="scr"
                label="上传人"
                align="center">
        </el-table-column>
        <el-table-column
                prop="scsj"
                label="上传时间"
                align="center">
        </el-table-column>
        <el-table-column
                prop="cz"
                label="操作"
                align="center"
                width="200">
          <template slot-scope="scope">
            <label title="下载"><i @click="stDownLoad_vue(scope.$index, scope.row)" class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
          </template>
        </el-table-column>

      </el-table>

    </el-dialog>

    <el-row>
        <el-col :span="24">
          <el-card class="box-card" style="margin-top: 20px">
            <div slot="header" class="clearfix" style="margin:-18px -20px; padding:10px 20px; background: #f5f7fa; height: 18px;">
              <span>指派人员</span>
            </div>
            <el-col :span="24">
            <div class="text item">
              <!--可指派的预算人员-->
              <el-row>
                <el-col :span="6" v-model="Yszy" v-for="item in Yszy" class="col" :key="item.id">
                  <el-radio v-model="radio" :label="item.id">{{item.name}}（在建项目{{item.sl}}个）</el-radio>
                </el-col>

              </el-row>
              <el-row class="row"></el-row>
              <el-row style="margin-top: 20px;">
                <el-col :span="12">&nbsp;</el-col>
                <!--<el-col :span="12" style="text-align: center;">
                    预约勘查时间：
                    <el-date-picker v-model="kcsj" type="datetime" readonly></el-date-picker>
                    <el-button @click="toRwlbsjy">取消</el-button>
                    <el-button type="primary" @click="taskZpSjy">发起指派</el-button>
                </el-col>-->
              </el-row>
              <el-row class="row"></el-row>
            </div>
            </el-col>
          </el-card>
        </el-col>
      <el-col :span="24" style="text-align: center;margin-top: 10px;"></el-col>
    </el-row>

    <el-row>
      <el-col :span="24">
        <el-card class="box-card">
          <el-col :span="7">&nbsp;</el-col>
          <router-link :to="tabUrl">
            <el-button size="mini" type="info" @click="toXckcrwcj()"><i class="iconfont icon-quxiao"></i>取消</el-button>
          </router-link>
          <el-button size="mini" type="primary"  @click="saveXckcTask()" style="width: 100px;margin-left: 10px" :loading="sumbitLoading"><i class="iconfont icon-shenhetongguorenyuan"></i>发起指派</el-button>
          <el-button size="mini" type="info" @click="toFeturn()" :loading="sumbitLoading"><i class="iconfont icon-fanhui"></i>驳回</el-button>
          <el-button size="mini" type="danger"@click="yqdialogOpen()"  v-if="isyqshow" style="margin-left: 10px;"><i class="iconfont icon-gaojing"></i>延期</el-button>
          <el-button size="mini" type="primary" @click="goxmqj()" :loading="sumbitLoading"><i class="iconfont icon-xiangmuquanjing_"></i>项目全景</el-button>
          <!--<el-button size="mini" type="primary" @click="goxmzl()" :loading="submitLoading"><i class="iconfont icon-ziliaoquanjiing_"></i>项目资料</el-button>-->
        </el-card>
      </el-col>
    </el-row>
    <el-dialog title="申请延期" :visible.sync = "sqyqVisible" :close-on-click-modal="false" width="50%" >
      <el-form :model="sqyqForm"  ref="sqyqForm" :rules="sqyqrules">
        <el-row>
          <el-col :span="12">
            <el-form-item label="申请人" prop="sqr" style="margin-bottom: 15px" size="small" label-width="110px">
              <el-input v-model="sqyqForm.sqr"  readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="审批人" prop="spr" style="margin-bottom: 15px" size="small" label-width="110px">
              <el-input v-model="sqyqForm.spr"  readonly></el-input>
              <el-input v-model="sqyqForm.sprid" style="display: none" ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="要求完成时间" prop="yqwcsj" style="margin-bottom: 15px" size="small" label-width="110px">
              <el-input v-model="sqyqForm.yqwcsj"  readonly></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="预计完成时间" prop="jhyqjz_vue" style="margin-bottom: 15px" size="small" label-width="110px">
              <el-date-picker :picker-options="{ selectableRange: '8:00:00 - 20:00:00' }" style="width: 100%" format="yyyy-MM-dd HH:mm"value-format="yyyy-MM-dd HH:mm" size="small" type="datetime" placeholder="选择日期" v-model="sqyqForm.jhyqjz_vue"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-form-item label="延期说明" prop="csyy" style="margin-bottom: 15px" size="small" label-width="110px">
            <el-input
                    type="textarea"
                    :rows="4"
                    placeholder="请输入内容"
                    v-model="sqyqForm.csyy">
            </el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-upload
                  class="upload-demo"
                  ref="upload"
                  action="#"
                  :on-preview="handlePreview"
                  :http-request="submitUpload"
                  :on-remove="handleRemove"
                  :on-change="handleChange"
                  :file-list="fileList"
                  :auto-upload="true"
                  :show-file-list=false>
            <el-button slot="trigger" size="mini" type="success" ><i class="iconfont icon-shangchuan_f"></i>&nbsp;上传</el-button>
            <el-button  size="mini" type="success" @click="delayps"><i class="iconfont icon-paishe"></i>&nbsp;拍摄</el-button>
            <!--<el-button size="mini" type="success" style="margin-left: 10px" @click="download"><i class="iconfont icon-xiazai_f"></i>下载</el-button>-->
            <!--<el-button size="mini" type="primary" style="margin-left: 10px" @click="look"><i class="iconfont icon-yulan"></i>预览</el-button>-->
          </el-upload>

        </el-row>
        <el-table :data="delayfjData" :stripe="true" style="padding-bottom: 10px">
          <el-table-column property="FJMC" label="附件名称" align="center"></el-table-column>
          <el-table-column property="SCSJ" label="上传时间" align="center"></el-table-column>
          <el-table-column property="USERNAME" label="上传人" align="center"></el-table-column>
          <el-table-column property="address" label="操作" align="center">
            <template slot-scope="scope">
              <el-row>
                <el-col :span="24">
                  <div v-if="scope.row.FJHZ!=null&&(scope.row.FJHZ.toLowerCase()=='png'||scope.row.FJHZ.toLowerCase()=='jpg')">
                    <el-col :span="24">
                      <label title="删除"><i class="iconfont delete" @click="deleteDelayFj_vue(scope.$index, scope.row)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                      <label title="预览"><i class="iconfont icon-yulan" @click="downloadDelayFile_vue(scope.$index, scope.row,1)" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                    </el-col>
                  </div>
                  <div v-else>
                    <el-col :span="24">
                      <label title="删除"><i @click="deleteDelayFj_vue(scope.$index, scope.row)" class="iconfont delete" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                      <label title="下载"><i @click="downloadDelayFile_vue(scope.$index, scope.row,2)" class="iconfont icon-xiazai" style="cursor:pointer;padding: 2px;color: #66b1ff"></i></label>
                    </el-col>
                  </div>
                </el-col>
              </el-row>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="mini" type="info" @click="sqyqVisible = false"><i class="iconfont icon-quxiao"></i>取 消</el-button>
        <el-button type="primary" size="mini" @click="sqyq_vue('sqyqForm')"><i class="iconfont icon-queren"></i>确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="拍摄" :visible.sync = "creamVisible_Delay" top="5vh" :close-on-click-modal="false">
      <DocCamera  v-bind:dirName ="dirName" v-bind:fileName ="fileName_Delay" v-on:listenToChildEvent="showMsg_delay"></DocCamera>
    </el-dialog>
  </section>
</template>
<style lang="css">
  .row{
    margin-top: 14px;
  }
  .row .el-col{
    text-align: center;
  }
</style>
<script>
  import DocCamera from '@/views/template/DocCamera.vue';
  import XMJBXX from '@/views/template/xmjbxx.vue';
  import hhldsx from '@/views/template/hhldsx.vue';
  import xmfwpz from '@/views/template/xmfwpz.vue';
  import wlqdpz from '@/views/template/wlqdpz.vue';
  import dbpz from '@/views/template/dbpz.vue';
  import hbpz from '@/views/template/hbpz.vue';
  import wjjlbpz from '@/views/template/wjjlbpz.vue';
  import util from '../../common/js/util';
  import axios from 'axios';
  import { initStcgTable,stManage,stPreview,download_sjst,sqyqxx,sqyq,yqbuttonshow,importFile_delay,toDetial_delay,deleteDelayFj,stcgsc_delay,fsdx} from '../../api/sjst';
  import ElImage from "element-ui/packages/image/src/main";

  import { getsjstrzwid,getYswtList, getTable, startDelegate, getYsZy ,getYszyById,getYszyxmById,ToYsZpzr,ToBackward,updateQszt} from '../../api/Yswt';
  export default {
    data() {
      return {
        //---------------------------
        delayid:"",
        fileName_Delay:'yszpdelay',
        fileList:[],
        slide1:[],
        creamVisible_Delay:false,
        delayfjData:[],

        //---------------------------
        tabUrl:'',
        stcgDialogFormVisible:false,
        stcgxqData:[],
        sjstrzwid:'',//设计审图任务id
        fileName: 'sjcgsc',//给子组件的文件名字
        dirName:"",
        Data:[],
        sjData:[],
        title:"",
        activeName:'1',
        radio : '',//选中的预算专员
        yszyxmxx:[],//预算专员的项目信息
        Yszyid:'', //选中预算专员的id
        yszyxx:[],
        Ts: '', //预算专员的数量 暂时无用
        Yszy: [], //预算专员的数据
        textarea: '', //备注
        xmid:this.$route.query.xmid,//项目id
        activityId:'40002',
        Ysfrom: '', //上个页面传来的项目信息
        kcsj: '', //预约勘查时间
        tableData: [],
        check_value: {
          sjy_value: true,
          sgdw_value: true,
          ysk_value: true,
          sjydc_value: true,
          sgdwdc_value: true,
          yskdc_value: false
        },
        sumbitLoading:false,
        //延期
        sqyqVisible:false,

        sqyqForm:{
          sqr:"",
          spr:"",
          yqwcsj:"",
          jhyqjz_vue:"",
          csyy:"",
          sprid:"",
        },
        isyqshow:false,
        sqyqrules:{
          jhyqjz_vue:[
            { required: true, message: '该字段不可为空', trigger: 'blur' }
          ],

          csyy:[
            { required: true, message: '该字段不可为空', trigger: 'blur' }
          ]},
        //--------------------------

      };
    },
    methods: {
      //跳转至项目全景
      goxmqj(){
        this.$router.push({path:'/Xmlct', query:{ xmid:this.xmid  }})

      },
      //跳转至项目资料
      goxmzl(){
        this.$router.push({path:'/lookZl', query:{ xmid:this.xmid }})
      },
      //延期--------------------
      handlePreview(file) {

      },
      handleChange(file, fileList) {
        this.fileList = fileList;
      },
      handleRemove(file, fileList) {

      },
      submitUpload(){
        if(this.fileList.length>0){
          let file = this.fileList[this.fileList.length-1];
          let formData = new FormData();
          formData.append("multipartFiles",file.raw);
          formData.append("filename",file.name);
          formData.append("delayid",this.delayid);
          formData.append("xmid",this.xmid);
          // formData.append("fjlxEj",this.fjlxEj_delay);
          // formData.append("fjlxYj",this.fjlxYj_delay);
          importFile_delay(formData).then((res)=>{
            let msg = res.data.msg;
            if(msg == '上传成功'){
              this.$message({
                message: '上传成功',
                type: 'success'
              });
              this.fileList=[];
              this.delayid=res.data.delayid;
              this.toDetial_vue();
            }else{
              this.$message.error('上传失败！');
            }
          })
        }
      },
      delayps(){
        this.creamVisible_Delay = true;
      },
      toDetial_vue() {
        let params = {
          delayid: this.delayid
        }
        toDetial_delay(params).then((res) => {
          if (res.data != null && res.data.row != null) {
            this.delayfjData = res.data.row;
          }
        })
      },
      showMsg_delay:function(data){//存储子组件保存的图片名称
        let name = data;
        let path = 'upload/'+this.dirName+'/'+data;
        this.submitUpload_delay(name, path);

        //接口预留位置  data为图片名称 将图片名称保存到数据库
      },
      deleteDelayFj_vue(index,row){
        this.$confirm('确认删除该记录吗?', '提示', {
          type: 'warning'
        }).then(() => {
          let params = {
            id: row.ID,
          };
          deleteDelayFj(params).then((res) => {
            let msg = res.data;
            if(msg){
              this.$message({
                message: '删除成功',
                type: 'success'
              });
              this.toDetial_vue();
            }else{
              this.$message.error('删除失败！');
            }
          });
        })
      },
      downloadDelayFile_vue(index,row,flag){
        let formData = new FormData()
        formData.append("path",row.FJLJ);
        download_sjst(formData).then((res)=>{
          if(flag==1){
            this.slide1=[];
            let reader = new FileReader()
            reader.onload = (e) => {
              let img = new Image();
              img.src =e.target.result;
              let item = {};
              item.msrc = item.src = e.target.result;
              item.h = img.height;
              item.w = img.width;
              this.slide1.push(item);
              this.$preview.openList(0, this.slide1);
            }
            reader.readAsDataURL(res.data)
          }else{
            const content = res.data
            const blob = new Blob([content])
            const fileName = row.FJMC
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
      },
      submitUpload_delay(name, path){
        let params = {
          name: name,
          path: path,
          delayid: this.delayid,
        }
        stcgsc_delay(params).then((res) => {
          let msg = res.data.msg;
          if (msg == '上传成功') {
            this.$message({
              message: '上传成功',
              type: 'success'
            });
            this.delayid=res.data.delayid;
            this.toDetial_vue();
            this.creamVisible_Delay=false;
          } else {
            this.$message.error('上传失败！');
          }
        })
      },
      //------------------------------
      yqbuttonshow_vue(){
        let params={
          activityId:this.activityId,
          xmid:this.xmid
        }
        yqbuttonshow(params).then((res)=>{
          this.isyqshow = res.data;
        })
      },
      //延期申请
      yqdialogOpen(){
        let params={
          xmid:this.xmid,
          activityId:this.activityId
        }
        sqyqxx(params).then((res)=>{
          if(res.data!=null){
            this.sqyqForm.sqr=res.data.sqr;
            this.sqyqForm.spr=res.data.spr;
            this.sqyqForm.sprid=res.data.sprid;
            this.sqyqForm.yqwcsj = res.data.yqwcsj;
            this.sqyqForm.jhyqjz_vue = "";
            this.sqyqForm.csyy = "";
            this.sqyqVisible=true;
          }
        })
      },
      //申请延期
      sqyq_vue(formName){
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let yqwcsjdate = util.formatDate.parse(this.sqyqForm.yqwcsj,'yyyy-MM-dd hh:mm');
            let jhyqjz_vuedate = util.formatDate.parse(this.sqyqForm.jhyqjz_vue,'yyyy-MM-dd hh:mm');
            if(jhyqjz_vuedate<yqwcsjdate){
              this.$message.error('预计完成时间不得小于要求完成时间');
              return;
            }
            let params={
              delayid:this.delayid,
              xmid:this.xmid,
              activityId:this.activityId,
              yqwcsj:this.sqyqForm.yqwcsj,
              jhyqjz_vue:this.sqyqForm.jhyqjz_vue,
              csyy:this.sqyqForm.csyy,
              sprid:this.sqyqForm.sprid
            }
            sqyq(params).then((res)=>{
              if(res.data.flag){
                this.$message.success('保存成功！');
                this.sqyqVisible=false;
                this.isyqshow = false;
                let param={
                  id:res.data.id
                }
                fsdx(param).then((res)=>{

                })
              }else{
                this.$message.error('保存失败！');
              }
            })
          } else {
            return false;
          }
        });

      },

      //初始化参数
      init(){
        let JSONstr = sessionStorage.getItem("user");
        let JSON = eval("("+JSONstr+")");
        this.dirName = JSON.username;//文件夹名字
      },
      stPreview_vue(index, row) {
        this.slide1 = [];
        let path = [];
        this.fjlxYj = row.parentid;
        this.fjlxEj = row.id;
        let params = {
          xmid: this.xmid,
          sjstrzwid: this.sjstrzwid,
          fjlxEj: row.id,
          fjlxYj: row.parentid
        }
        stPreview(params).then((res) => {
          if (res.data != null) {
            let result = res.data.row;
            for (let i = 0; i < result.length; i++) {
              let img = new Image();
              img.src = "data:image/jpeg;base64," + result[i];
              let item = {};
              item.msrc = item.src = "data:image/jpeg;base64," + result[i];
              item.h = img.height;
              item.w = img.width;
              this.slide1.push(item);
            }
            this.$preview.openList(0, this.slide1);
          }
        })
      },
      //下载单个文件
      stDownLoad_vue(index, row) {
        this.downLoad=true;
        let formData = new FormData();
        formData.append("path", row.fjlj);
        formData.append("type", "1");
        download_sjst(formData).then((res)=>{
          const content = res.data
          const blob = new Blob([content])
          const fileName = row.fjmc
          if ('download' in document.createElement('a')) { // 非IE下载
            const elink = document.createElement('a')
            elink.download = fileName
            elink.style.display = 'none'
            elink.href = URL.createObjectURL(blob)
            document.body.appendChild(elink)
            elink.click()
            URL.revokeObjectURL(elink.href) // 释放URL 对象
            document.body.removeChild(elink)
            this.downLoad=false;
          } else { // IE10+下载
            navigator.msSaveBlob(blob, fileName);
            this.downLoad=false;
          }
        })
      },
      //打开详情
      stManage_vue(index, row, type) {

        this.fjlxYj = row.parentid;
        this.fjlxEj = row.id;
        let params = {
          xmid: this.xmid,
          sjstrzwid: this.sjstrzwid,
          fjlxEj: row.id,
          fjlxYj: row.parentid
        }
        stManage(params).then((res) => {
          if (res.data != null) {
            this.stcgxqData = res.data.row;
            if (type == 'stcg') {
              this.title = "审图成果";
            } else {
              this.title = "设计成果";
            }
            this.type = type;

            this.stcgDialogFormVisible = true;
            setTimeout(()=>{
              this.$refs.mychild.childClick();
            },10)
          }
        })
      },
      //初始化审图成果列表
      initStcgTable_vue(type) {
        let params = {
          xmid: this.xmid,
          sjstrzwid: this.sjstrzwid,
          type: type
        }
        initStcgTable(params).then((res) => {
          if (res.data != null) {
            if (type == 'stcg') {
              this.Data = res.data.row;
            }
            if (type == 'sjcg') {
              this.sjData = res.data.row;
            }
          }
        })
      },
      getsjstrzwid(){
        let params = {
          xmid:this.xmid,
          activityId:'30003'
        }
        getsjstrzwid(params).then((res) =>{
          if(res != "error"){
            //this.$router.push({path: '/scsjcg', query: {xmid : row.ID,activityId:'30003',sjstrzwid:row.REFID}})
            if(res.sjstrzwid.length>0){
              this.sjstrzwid = res.sjstrzwid[0].refid;
            }
            //初始化审图成果
            this.initStcgTable_vue('stcg');
            //初始化设计成果
            this.initStcgTable_vue('sjcg');

          }
        })
      },
      updateQszt(){
        let params = {
          xmid : this.xmid,
          activityId:'40002'
        }
        updateQszt(params).then(res =>{

        });
      },
      initList(){
        let params = {
          xmid:this.xmid,
          page: '1',
          size: '99999',
          bs:1,
        }
        getYswtList(params).then(res => {
          this.yszp = res.data.yszp;
          for (let i=0;i<this.yszp.length;i++){
            if (this.xmid == this.yszp[i].id){
              this.Ysfrom = this.yszp[i];
            }
          }
        });
      },
      //选中预算专员
      Yszyclick(row){
        this.Yszyid = row.id;
        let params = {
          id: this.Yszyid,

        }
        //获取专员信息
        getYszyById(params).then(res => {

          this.yszyxx = res.data.yszyxx;
          this.yszyxmxx = res.data.yszyruxp;
        });

      },
      //获取预算专员的项目的信息
// 		Yszyxmxx(){
// 			let params = {
// 			 xmid: this.xmid,
// 			}
// 			 //获取专员项目信息
// 			getYszyxmById(params).then(res => {
//
// 				this.yszyxmxx = res.data.yszyxmxx;
// 				console.log(this.yszyxmxx);
// 			});
// 		},
      //获取项目数据
      getTableData() {
        this.tableData.push({cjr: "-", count: "0", lb: "楼栋", strCjsj: "-",xmid : "0"});
        this.tableData.push({cjr: "-", count: "0", lb: "庭院", strCjsj: "-",xmid : "0"});
        this.tableData.push({cjr: "-", count: "0", lb: "水箱", strCjsj: "-",xmid : "0"});
        this.tableData.push({cjr: "-", count: "0", lb: "地下室", strCjsj: "-",xmid : "0"});
        this.tableData.push({cjr: "-", count: "0", lb: "泵房", strCjsj: "-",xmid : "0"});
        let params = {
          id: this.xmid
        };
        getTable(params).then(res => {
          let num = res.xmfw.length;
          for (let i=0;i<this.tableData.length;i++){
            for (let j=0;j<num;j++){
              if (this.tableData[i].lb == res.xmfw[j].lb){
                this.tableData[i].cjr = res.xmfw[j].cjr;
                this.tableData[i].count = res.xmfw[j].count;
                this.tableData[i].strCjsj = res.xmfw[j].strCjsj;
                this.tableData[i].xmid = res.xmfw[j].xmid;
              }
            }
          }
        });
        getYsZy().then(res => {
          this.Yszy = res.data.yszy;
          this.Ts = res.data.ts;
        });
      },
      //跳转页面Xckcrwcj
      toXckcrwcj() {
        this.tabUrl='/Backlog';
        this.$routerTab.close();
        this.$routerTab.refresh('/Backlog');
        this.$routerTab.refresh('/MyWork');//刷新我的工作
      },
      //element表格渲染方法
      objectSpanMethod({ row, column, rowIndex, columnIndex }) {
        if (columnIndex === 0) {
          if (rowIndex % 2 === 0) {
            return {
              rowspan: 2,
              colspan: 1
            };
          } else {
            return {
              rowspan: 0,
              colspan: 0
            };
          }
        }
      },
      //退回上层40001
      toFeturn(){
        this.$prompt('请输入驳回原因！', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
        }).then(({ value }) => {
          //为空（不填或者填完删除）
          if (value === null || value === ""){
            this.$message({
              type: 'warning',
              message: '填写驳回原因后才可以驳回!'
            });
          }else if (value != null || value != "") {//不为空

            this.$confirm('确认退回预算?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {

              let params ={
                xmid : this.xmid,//项目id
                htyy : value,//回退原因
              };
              this.sumbitLoading=true;
              ToBackward(params).then(res =>{
                this.sumbitLoading=false;
                this.$message({
                  type: 'success',
                  message: '退回成功!',
                });
              });
              this.$routerTab.close();
              this.$router.push({ path: '/Backlog', query: {} });
              this.$routerTab.refresh('/Backlog');
              this.$routerTab.refresh('/MyWork');//刷新我的工作

            }).catch(() => {
              this.$message({
                type: 'info',
                message: '取消退回'
              });
            });

          }

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '取消输入'
          });
        });
      },
      //发起指派ToYsZpzr
      saveXckcTask() {
        if(this.radio==''||this.radio==null){
          this.$message.error('请选择指派人员！');
          return;
        }
        this.$confirm('您正在递交工作结果，一旦递交确认后将不能打回或再次修改，你确定要递交吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          let params = {
            xmid: this.xmid,
            procinstid : this.Ysfrom.procinstid,
            ysr: this.radio,
            zprbz:this.textarea,
          };
          this.sumbitLoading=true;
          ToYsZpzr(params).then(res => {
            this.sumbitLoading=false;
            this.$message({
              type: 'success',
              message: '指派成功!'
            });
          });
          this.$router.push({path: '/Backlog', query: {}})
          this.$routerTab.close();
          this.$routerTab.refresh('/Backlog');
          this.$routerTab.refresh('/MyWork');//刷新我的工作

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消递交'
          });
        });
      }
    },

    //页面启动调用的方法
    mounted() {
      this.yqbuttonshow_vue();
      this.init();
      this.initList();
      this.getTableData();
      this.getsjstrzwid();
      this.updateQszt();
    },
    components: {
      //将别名demo 变成 组件 Demo
      'DocCamera':DocCamera,
      XMJBXX: XMJBXX,
      'hhldsx' : hhldsx,
      'xmfwpz': xmfwpz,
      'wlqdpz': wlqdpz,
      'dbpz': dbpz,
      'hbpz': hbpz,
      'wjjlbpz': wjjlbpz,
    }
  };
</script>

<style scoped></style>
