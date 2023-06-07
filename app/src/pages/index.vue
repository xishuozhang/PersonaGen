<template>
  <div class="wrapper">
    <div class="content">
      <div class="header">
        <ul>
          <li id="/DataProcessing" @click="toPath">Data Processing</li>
          <li id="/home" @click="toPath">Home</li>
          <li id="/about" @click="toPath">About PersonaGen</li>
          <li id="/contact" @click="toPath">Contact Us</li>
          <li id="/team" @click="toPath">Team</li>
          <!-- <li id="/video" @click="toPath">Demo Video</li> -->
        </ul>
      </div>
      <router-view />
    </div>
    <!-- <footer class="footer">
      <div>Contact U</div>
      <div>Copy@2023区</div>
    </footer> -->
  </div>
</template>

<script>
export default {
  name: "app-index",

  mounted() {
    this.redirectPath()
  },

  methods: {
    redirectPath() {
      const route = this.$router.currentRoute.fullPath;
      document.getElementById(route).classList.add("active");
      this.navigator(route);
    },
    toPath(e) {
      const liList = document.getElementsByTagName("li");
      for (let i = 0; i < liList.length; i++) {
        liList[i]?.classList.remove("active");
      }
      document.getElementById(e.target.id).classList.add("active");
      this.navigator(e.target.id);
    },
    navigator(id) {
      const route = this.$router.currentRoute.fullPath;
      if(id === route) {
        return;
      }
      this.$router.push(id)
    }
  }
};
</script>

<style scoped>
.wrapper {
  width: 100%;
  height: 100%;
  position: relative;
}
.hr {
  width: 100px;
  height: 10px;
  background: #898989;
}
.content {
  width: 70%;
  height: 90%;
  margin: 0px auto;
  padding: 50px 0;
  background: #fff;
  box-sizing: border-box;
  /* border: 1px solid #eee; */
}
.header {
  box-sizing: border-box;
  width: 100%;
  height: 50px;
  padding: 0 20px;
  background-color: #e5e5e5;
  border-radius: 8px;
  box-shadow: 5px 5px 20px #e3e3e3;
}
.header ul {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.header ul li {
  line-height: 50px;
  text-align: center;
  cursor: pointer;
  padding: 0 20px;
}
.list {
  overflow-y: auto;
  padding: 50px 0;
}
.active {
  background: #e35c58;
  color: #fff;
  overflow: hidden;
  border-radius: 8px;
}

.footer {
  position: fixed;
  box-sizing: border-box;
  width: 100%;
  height: 100px;
  padding: 50px;
  color: #fff;
  background: #5c5c5c;
  font-size: 12px;
  bottom: 0px;
  /* position:fixed */
}
 
</style>