<template>
  <div class="div">
    <label for="file-input" class="file-label">
      <span class="file-icon"><i class="fas fa-cloud-upload-alt"></i></span>
      <span class="file-text">{{ fileText }}</span>
    </label>
    <input id="file-input" type="file" @change="uploadFile" class="file-input">
    <div class="progress-bar">
      <div class="progress" :style="{ width: progress + '%' }"></div>
      <div class="progress-text">{{ progress }}%</div>
    </div>
    <!-- <button v-if="showDownloadButton" @click="downloadFile" class="download-button">下载文件</button> -->
    <br/>
    <!-- <button @click="downloadFile" class="download-button">导入数据</button>
    <button @click="downloadFile" class="download-button">关系关联</button> -->
  </div>
</template>

<script>
import { importExcelDeptStaff } from '@/api/index'
export default {
  data() {
    return {
      progress: 0,
      showDownloadButton: false,
      fileText: 'Select File'
    };
  },
  methods: {
    async uploadFile(event) {
      // 执行文件上传操作

      // 模拟上传进度的示例
      let currentProgress = 0;
      const progressInterval = setInterval(() => {
        if (currentProgress < 90) {
          currentProgress += 10;
          this.progress = currentProgress;
        } else {
          this.progress = 99
        }
        // else {
        //   clearInterval(progressInterval);
        //   this.showDownloadButton = true;
        // }
        
      }, 500);

      // 获取选择的文件名并显示
      const file = event.target.files[0];
      console.log(file, 444);

     

      const formData = new FormData();
      formData.append('file', file);



      const res = await importExcelDeptStaff(formData);

      clearInterval(progressInterval);
      this.progress = 100;
      this.showDownloadButton = true;

       // 将数据存放到 Blob 对象中
      const b = new Blob([res.data],{
        type: 'application/vnd.ms-excel'
      })

      //创建一个空的 a 标签
      let a = document.createElement('a');
      // 将 blob 文件转化为 url 格式并赋值给a标签的 href 属性
      a.href = URL.createObjectURL(b)
      // 给a标签设置下载链接并命名
      a.setAttribute('download', 'excel.xls');
      // 点击事件触发 href
      a.click();
      // 清除 a 标签
      a = null
      
      
      if (file) {
        this.fileText = file.name;
      } else {
        this.fileText = '选择文件';
      }
    },
    downloadFile() {
      // 执行文件下载操作
      console.log('下载文件');
    }

  
}
};
</script>

<style>
.div{
  margin-top: 150px;
}
.file-label {
  display: inline-block;
  background-color: #e35c58;
  color: #fff;
  padding: 10px 20px;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.file-label:hover {
  background-color: #ff8c00;
}

.file-icon {
  margin-right: 8px;
}

.file-text {
  vertical-align: middle;
}

.file-input {
  display: none;
}

.progress-bar {
  width: 100%;
  height: 10px;
  border: 1px solid #ccc;
  position: relative;
  margin-top: 10px;
  overflow: hidden;
  border-radius: 8px;
}

.progress {
  height: 100%;
  background-color: #42b983;
  transition: width 0.3s ease;
}

.progress-text {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: #fff;
}

.download-button {
  background-color: #ffaa00;
  color: #fff;
  padding: 10px 20px;
  border: none;
  cursor: pointer;
  border-radius: 4px;
  font-size: 16px;
  transition: background-color 0.3s ease;
}

.download-button:hover {
  background-color: #ff8c00;
}
</style>
