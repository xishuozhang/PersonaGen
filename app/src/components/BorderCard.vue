<template>
  <div class="imag-wrapper" :style="wrapperStyle">
    <img :style="imgStyle" slot="cover" alt="example" :src="img" />
    <div class="font-wrapper">
      <div class="imag-item">-{{name}}-</div>
      <div class="imag-item">{{position}}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "border-card",
  props: ["name", "position", "img", "imgStyle", "wrapperStyle"],
};
</script>

<style scoped>
.imag-wrapper {
  width: 150px;
  display: flex;
  flex-wrap: wrap;
  flex-direction: column;
  align-items: center;
  margin: 0 auto;
  margin-right: 20px;
}
.font-wrapper {
  font-size: 12px;
  padding: 10px;
}
.imag-item {
  text-align: center;
}
</style>