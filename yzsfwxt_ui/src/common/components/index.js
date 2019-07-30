import Vue from 'vue'


// File Upload
//import FileUpload from './form/file-upload/FileUpload.vue'
// import BatchFileUpload from './form/file-upload/BatchFileUpload.vue'
// import PictureCard from './form/file-upload/picture/PictureCard.vue'
// import FileDownloader from './form/file-download/FileDownloader.vue'

// Image
import ImageView from './form/img-preview/ImageView.vue'

import ButtonCounter from './other/ButtonCounter.vue'
import BlogPost from './other/BlogPost.vue'

import preview from './form/img-preview'

function initGlobalComponents() {

    // file upload
    // Vue.component(FileUpload.name, FileUpload)
    // // Vue.component(BatchFileUpload.name, BatchFileUpload)
    // Vue.component(PictureCard.name, PictureCard)
    // Vue.component(FileDownloader.name, FileDownloader)
    Vue.component(ImageView.name, ImageView)
    Vue.component(ButtonCounter.name, ButtonCounter)
    Vue.component(BlogPost.name, BlogPost)

    Vue.prototype.$preview = preview

}

export default initGlobalComponents
