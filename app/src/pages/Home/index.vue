<template>
    <div class="list">
      <div class="title">
        <span>SAMPLE</span>
        <br />
        <span>USER PROFILING</span>
        <div class="hr-wrapper">
          <div class="hr"></div>
        </div>
      </div>
      <div>
      </div>
      <div class="person-list">
        <div v-for="(item,i) in personList" :key="i" class="person-item" @click="() => getDetail(item)">
          <Card :name="item.name" :des="item.position" :img="item.img" imgStyle="width: 150px; height: 150px" />
        </div>
      </div>
    </div>
</template>

<script>
import Card from '@/components/Card.vue'
import { getAll } from '@/api/index'
export default {
  name: "my-home",
  components: {
    Card
  },
   data() {
    return {
      personList: [
        {
          name: 'name',
          position: 'Front-end Engineel',
          img: 'srcAddress'
        },
        {
          name: '-Jack-',
          position: 'Front-end Engineel',
          img: 'srcAddress'
        },
        {
          name: '-Jack-',
          position: 'Front-end Engineel',
          img: 'srcAddress'
        },
        {
          name: '-Jack-',
          postition: 'Front-end Engineel',
          img: 'srcAddress'
        },
        {
          name: '-Jack-',
          position: 'Front-end Engineel',
          img: 'https://img-blog.csdnimg.cn/0d070b971ef942d78a9c17c5792f94e8.jpeg'
        },
        {
          name: '-Jack-',
          position: 'Front-end Engineel',
          img: 'https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png'
        },
      ]
    }
  },
  mounted () {
    this.getData()
  },
  methods: {
    getDetail(item) {
      console.log(item, 333);
      
      this.$router.push({
        name: 'detail',
        params: {
          info: item
        }
      })
    },
    async getData () {
      const res = await getAll()
      console.log(res)
      this.personList = res.data
    }
  }
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