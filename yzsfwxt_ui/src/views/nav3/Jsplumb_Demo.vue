<template>
    <el-row style="text-align: center">
        <el-col :span="24" style="margin-top: 0px">
            <div v-if="type=='1'">
                <XQSL :xmid="this.xmid"></XQSL>
            </div>
            <div v-if="type=='2'">
                <XKYLX :xmid="this.xmid"></XKYLX>
            </div>
            <div v-if="type=='3'">
                <SJST :xmid="this.xmid"></SJST>
            </div>
            <div v-if="type=='4'">
                <WTYS :xmid="this.xmid" ></WTYS>
            </div>
            <div v-if="type=='5'">
                <HTQD :xmid="this.xmid"></HTQD>
            </div>
            <div v-if="type=='6'">
                <SFKP :xmid="this.xmid"></SFKP>
            </div>
            <div v-if="type=='7'">
                <SGJG :xmid="this.xmid"></SGJG>
            </div>
            <div v-if="type=='8'">
                <ZHYS :xmid="this.xmid"></ZHYS>
            </div>
            <div v-if="type=='9'">
                <YJ :xmid="this.xmid"></YJ>
            </div>
            <div v-if="type=='A'">
                <GCPD :xmid="this.xmid"></GCPD>
            </div>
            <div v-if="type=='B'">
                <ZLGD :xmid="this.xmid"></ZLGD>
            </div>
            <div v-if="type=='C'">
                <JS :xmid="this.xmid"></JS>
            </div>
        </el-col>
    </el-row>


</template>


<script>

    import XQSL from '@/views/work/template/1_xqsl.vue';
    import XKYLX from '@/views/work/template/2_xkylx.vue';
    import SJST from '@/views/work/template/3_sjst.vue';
    import WTYS from '@/views/work/template/4_wtys.vue';
    import HTQD from '@/views/work/template/5_htqd.vue';
    import SFKP from '@/views/work/template/6_sfkp.vue';
    import SGJG from '@/views/work/template/7_sgjg.vue';
    import ZHYS from '@/views/work/template/8_zhys.vue';
    import YJ from '@/views/work/template/9_yj.vue';
    import GCPD from '@/views/work/template/10_gcpd.vue';
    import ZLGD from '@/views/work/template/11_zlgd.vue';
    import JS from '@/views/work/template/12_js.vue';

    export default {
        components: {
            'XQSL' : XQSL,
            'XKYLX': XKYLX,
            'SJST' : SJST,
            'WTYS' : WTYS,
            'HTQD' : HTQD,
            'SFKP' : SFKP,
            'SGJG' : SGJG,
            'ZHYS' : ZHYS,
            'YJ' : YJ,
            'GCPD' : GCPD,
            'ZLGD' : ZLGD,
            'JS' : JS,
        },
        data() {
            return {
                xmid: this.$route.query.xmid,
                type: this.$route.query.type,
            }
        },
        methods: {


        },
        mounted() {
            let obj ={ head :
                            {
                                authcode: '6cbc3132909f4ce285c03153f11b1f6a',
                                ywsysid: 'HF_005',
                                mstype:  '002',
                                dsflag : '1'
                            },
                       body:
                            {datas:
                                [{
                                    mobileno: 'listphone',
                                    content: 'content'
                                }]
                            }
                    }
        }
    }
</script>

<style>

</style>
