<template>
    <img :src="src" id="123456">
</template>

<script>
  import axios from 'axios'
    export default{
        data(){
            return {
                //src: require('../../assets/Photo.jpg'),
                img: null

            }
        },
        mounted() {
          this.getGoodsList()
        },
        methods: {
          getGoodsList () {
            // console.log("1111111111")
            // console.log(document.getElementById("123456").innerHTML);
            // this.img = document.getElementById("123456").innerHTML;
            let img = new Image;
            //img.src=require('../../assets/Photo.jpg')
            this.getBase64Image(img);
          },
          getBase64Image(img) {
            var canvas = document.createElement("canvas");
            canvas.width = img.width;
            canvas.height = img.height;
            var ctx = canvas.getContext("2d");
            ctx.drawImage(img, 0, 0, img.width, img.height);
            var dataURL = canvas.toDataURL("image/png");
            console.log(dataURL);
             return dataURL;
            //return dataURL.replace("data:image/png;base64,", ""); 
        }
        }
    }
      
</script>