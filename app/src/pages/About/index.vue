<template>
    <div class="list">
      <div class="title">
        <span>关于系统</span>
        <h4>基于知识图谱的用户画像平台</h4>
        <div class="hr-wrapper">
          <div class="hr"></div>
        </div>
      </div>
    </div>
</template>

<script>

export default {
  name: "my-home", 
};
</script>

<style>
.title {
  width: 100%;
  text-align: center;
  background-color: #fff;
  height: 80px;
  line-height: 30px;
  color: #121013;
  font-weight: 600;
}
.hr-wrapper{
  display: flex;
  justify-content: center;
  margin-top: 10px;
}
.hr{
  width: 40px;
  height: 2px;
  background: #cccccc;
}
.person-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-content: flex-start;
  cursor: pointer;
}
.person-item {
  width: calc(33.3% - 40px);
  height: 250px;
  margin-right: 20px;
}
</style>