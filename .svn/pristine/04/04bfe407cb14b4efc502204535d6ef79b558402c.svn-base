<template>
    <div>
        <light-timeline :items='items'>
            <template slot='tag' slot-scope='{ item }'>
                {{item.date}}
            </template>
            <template slot='content' slot-scope='{ item }'>
                {{item.msg}}
            </template>
        </light-timeline>
    </div>

    <!--<div>-->
        <!--<time-line>-->
            <!--<time-label :time="timeLabel" remark="remark"></time-label>-->
            <!--<time-item icon="icon" has-body="hasBody"  v-for="item in posts" :key="index" :time="item.time">-->
                <!--<time-item-header>你好</time-item-header>-->
                <!--<time-item-body>项目启动</time-item-body>-->
                <!--<time-item-footer>发表于{{new Date()}}</time-item-footer>-->
            <!--</time-item>-->
        <!--</time-line>-->
        <!--<light-timeline :item="items"></light-timeline>-->
    <!--</div>-->
</template>


<script>
    import TimeLine from "../../common/components/form/timeline/TimeLine";
    import TimeItem from "../../common/components/form/timeline/TimeItem";
    import TimeItemHeader from "../../common/components/form/timeline/TimeItemHeader";
    import TimeItemBody from "../../common/components/form/timeline/TimeItemBody";
    import TimeItemFooter from "../../common/components/form/timeline/TimeItemFooter";
    import TimeLabel from '../../common/components/form/timeline/TimeLabel'
    export default {
        components: {
            TimeItemFooter,
            TimeItemBody,
            TimeItemHeader,
            TimeItem,
            TimeLine,
            TimeLabel
            },
        data(){
            return {
                posts:  [
                    { id: 1, title: 'My journey with Vue', content: "caicaikan", time: "2017-04-09"},
                    { id: 2, title: 'Blogging with Vue', content: "caicaikan", time: "2018-04-09"},
                    { id: 3, title: 'Why Vue is so fun', content: "caicaikan", time: "2019-04-09" }
                ],
                items: [
                    {
                        tag: '2019-02-12',
                        content: '测试内容',
                        data: '2019-02-12',
                        msg: '测试内容'
                    },
                    {
                        tag: '2019-02-13',
                        type: 'circle',
                        content: '练习内容',
                        data: '2019-02-13',
                        msg: '练习内容'
                    }
                ]
            }
        },
        methods:{
            onEnlargeText: function (enlargeAmount) {
                this.postFontSize += enlargeAmount
            }
        }
    }
</script>
