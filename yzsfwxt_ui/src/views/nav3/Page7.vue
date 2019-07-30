<template>
    <div>
        <button @click="ImageView2">查看</button>
        <input type="file" value="C:\Users\ZQ\Desktop\11111.jpg" id="file" ></input>
        <button @click="upload()"></button>
        <!---->
        <!--<img class="preview-img" v-for="(item, index) in imglist" :src="item.src" height="100" @click="ImageView1(index, imglist)">-->
        <!--<img class="preview-img" :src="img.src" height="100" @click="ImageView1(img)">-->
    </div>

</template>


<script>
   // import ImageView from '../../common/components/form/img-preview/ImageView.vue'

    export default {
       /* components: {
            ImageView
        },*/
        data() {
            return {
                slide: [],
                type: 'A',
                loginType: 'username',
                parentMessage: 'Parent',
                items: [
                    {message: 'Foo'},
                    {message: 'Bar'}
                ],
                object: {
                    firstName: 'John',
                    lastName: 'Doe',
                    age: 30
                },
                src: "http://pic.58pic.com/58pic/15/68/59/71X58PICNjx_1024.jpg",
                imglist: [],
                img: {
                    src: 'https://farm4.staticflickr.com/3894/15008518202_c265dfa55f_h.jpg',
                    w: 600,
                    h: 400
                }
            };
        },
        methods: {
            upload(){
                let target = document.getElementById("file");
            },
            showSelectFile() {
                document.getElementById('file').click()
            },
            handleClose() {
            },
            uploadSuccess() {
                this.$notify.success('部署成功')
                // this.queryForPage()
            },
            showImages() {
                this.slide = [
                    {
                        msrc: "http://pic.58pic.com/58pic/15/68/59/71X58PICNjx_1024.jpg",
                        src: "http://pic.58pic.com/58pic/15/68/59/71X58PICNjx_1024.jpg",
                        h: 400,
                        w: 600
                    }
                ]
            },
            ImageView1(index, imglist) {
                this.$preview.openList(index, imglist);
                console.log(index)
            },
            ImageView2() {
                this.imglist = [{
                    src: 'https://farm4.staticflickr.com/3894/15008518202_c265dfa55f_h.jpg',
                    w: 600,
                    h: 400
                }, {
                    src: 'https://farm6.staticflickr.com/5591/15008867125_b61960af01_h.jpg',
                    w: 1200,
                    h: 900
                }]
                this.$preview.openList(0, this.imglist);
            },
        },
        computed: {}
    }
</script>
