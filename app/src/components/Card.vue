<template>
    <div class="imag-wrapper" :style="wrapperStyle"> 
      <img
        :style="imgStyle"
        slot="cover"
        alt="example"
        :src="img"
      />
      <div class="imag-item">-{{name}}-</div>
      <div class="imag-item">{{des}}</div>
    </div>
</template>

<script>

export default {
  name: "my-card",
  props: ['name','des','img', 'imgStyle', 'wrapperStyle']
};
</script>

<style scoped>
.imag-wrapper{
  width: 200px;
  height: 200px;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 0 auto;
}
.imag-item{
  text-align: center;
}
</style>