<template>
  <div>
    <div class="card-info">
      <img
        class="img"
        style="width: 150px; height: 200px"
        slot="cover"
        alt="example"
        :src="info.img"
      />
      <div class="content">
        <ul>
          <li>Name: {{ info.name }}</li>
          <li>Position: {{info.position}}</li>
          <li>Age: {{info.age}}</li>
          <li>Gender: {{info.gender}}</li>
          <li>Education: {{info.educationalExperience}}</li>
          <!-- <li>Email: 124569883@126.com</li> -->
        </ul>
      </div>
    </div>

    <!-- 问题描述 -->
    <div class="ques">
      <div class="ques-type">
        <div class="button">Feedback</div>
        <div class="ques-content">{{decInfo.feedBack}}</div>
      </div>
      <div class="ques-info">
        <div class="button">Requirements For Application</div>
        <div class="ques-content">{{decInfo.requirementForApplication}}</div>
      </div>
    </div>
    <!-- 问题描述2 -->
    <div class="ques">
      <div class="ques-type">
        <div class="button">Motivation</div>
        <div class="ques-content">{{decInfo.motivation}}</div>
      </div>
      <div class="ques-info">
        <div class="button">Demographic Data Visualisation</div>
        <div class="ques-content">
          <div id="main" style="width: 100%;height: 220px"></div>
        </div>
      </div>
    </div>

    <!--  贡献列表 -->
    <div class="list">
      <div class="list-title">
        <span class="list-title-content">Recommentation Lists</span>
      </div>
      <div class="list-content">
        <div v-for="(item, index) in personList" :key="index" @click="() => getDetail(item)">
          <BorderCard
            :name="item.name"
            :position="item.position"
            :img="item.img"
            imgStyle="width: 150px;"
            wrapperStyle="border: 1px solid #eee;box-shadow: 5px 5px 5px #eee;"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BorderCard from "@/components/BorderCard";
import { getPersonById, getProblemById, getOtherPerson } from "@/api/index";
import * as echarts from "echarts";
export default {
  name: "person-detail",
  components: {
    BorderCard
  },
  data() {
    return {
      form: {
        name: "",
        position: "",
        email: "",
        img: ""
      },
      personList: [
        // {
        //   name: "-Jack-",
        //   des: "Front-end Engineel",
        //   img: "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
        // },
        // {
        //   name: "-Jack-",
        //   des: "Front-end Engineel",
        //   img: "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
        // },
        // {
        //   name: "-Jack-",
        //   des: "Front-end Engineel",
        //   img: "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
        // },
        // {
        //   name: "-Jack-",
        //   des: "Front-end Engineel",
        //   img: "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
        // },
        // {
        //   name: "-Jack-",
        //   des: "Front-end Engineel",
        //   img: "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
        // },
        // {
        //   name: "-Jack-",
        //   des: "Front-end Engineel",
        //   img: "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png"
        // }
      ],
      info: {},
      decInfo: {},
      chartData: [
        {
          name: 'bug',
          data: [200 ,100 ,260,100]
        },
        {
          name: 'task',
          data: [200 ,100 ,260,500]
        },
        {
          name: 'story',
          data: [200 ,100 ,260,300]
        },
      ]
    };
  },
  mounted() {
    if (this.$route.params.info) {
      sessionStorage.setItem("info", JSON.stringify(this.$route.params.info));
      this.info = this.$route.params.info;
    } else {
      this.info = JSON.parse(sessionStorage.getItem("info"));
    }
    console.log(this.info,11111)
    this.getPersonInof();
    this.getDesData();
    this.queryOtherPerson();
    this.initEcahrt();
  },
  methods: {
    getDetail(item) {
      console.log(item, 333);
      
      this.$router.push({
        name: 'detail',
        params: {
          info: item
        }
      }).catch()
      this.info = item
      this.getDesData();
      this.queryOtherPerson();
    },
  
    // 获取用户信息
    async getPersonInof() {
      const params = { id: 4 };
      //console.log(params);
      getPersonById(params).then(res => {
        //console.log(res.data)
        this.form = res.data[0];
        //console.log(this.form);
      });
    },
    // 查询描述信息
    async getDesData() {
      const res = await getProblemById({ id: this.info.id });
      this.decInfo = res?.data[0] ?? {};
    },
    async queryOtherPerson() {
      const params = {
        personId: this.info.id,
        positionId: this.info.positionId
      };
      const res = await getOtherPerson(params);
      this.personList = res?.data ?? [];
    },
    initEcahrt() {
      // var myChart = echarts.init(document.getElementById("main"));
      // var barWidth = 15;
      // var colors = [
      //   {
      //     type: "linear",
      //     x: 0,
      //     x2: 1,
      //     y: 0,
      //     y2: 0,
      //     colorStops: [
      //       {
      //         offset: 0,
      //         color: "#218bd1"
      //       },
      //       {
      //         offset: 0.5,
      //         color: "#7EC3F0"
      //       },
      //       {
      //         offset: 0.5,
      //         color: "#1985cd"
      //       },
      //       {
      //         offset: 1,
      //         color: "#1985cd"
      //       }
      //     ]
      //   },
      //   {
      //     type: "linear",
      //     x: 0,
      //     x2: 1,
      //     y: 0,
      //     y2: 0,
      //     colorStops: [
      //       {
      //         offset: 0,
      //         color: "#6DD4E5"
      //       },
      //       {
      //         offset: 0.5,
      //         color: "#6DD4E5"
      //       },
      //       {
      //         offset: 0.5,
      //         color: "#0EADC9"
      //       },
      //       {
      //         offset: 1,
      //         color: "#218bd1"
      //       }
      //     ]
      //   },
      //   {
      //     type: "linear",
      //     x: 0,
      //     x2: 1,
      //     y: 0,
      //     y2: 0,
      //     colorStops: [
      //       {
      //         offset: 0,
      //         color: "#acace6"
      //       },
      //       {
      //         offset: 0.5,
      //         color: "#acace6"
      //       },
      //       {
      //         offset: 0.5,
      //         color: "#6161bd"
      //       },
      //       {
      //         offset: 1,
      //         color: "#6161bd"
      //       }
      //     ]
      //   }
      // ];

      // const option = {
      //   title: {
      //     text: "Awesome Chart"
      //   },
      //   xAxis: {
      //     data: ["前端", "后端", "UI", "测试", "运维", "DB"]
      //   },
      //   legend: {
      //     data: ["bug", "task", "story"],
      //     textStyle: {
      //       color: "#333" //坐标的字体颜色
      //     }
      //   },
      //   yAxis: {},
      //   series: [
      //     {
      //       z: 1,
      //       name: "bug",
      //       type: "bar",
      //       barWidth: barWidth,
      //       data: [320, 200, 260],
      //       itemStyle: {
      //         normal: {
      //           color: colors[0]
      //         }
      //       }
      //     },
      //     {
      //       z: 3,
      //       name: "左上部1",
      //       type: "pictorialBar",
      //       symbolPosition: "end",
      //       data: [320, 200, 260],
      //       symbol: "diamond",
      //       symbolOffset: ["-165%", "-50%"],
      //       symbolSize: [barWidth - 4, (10 * (barWidth - 4)) / barWidth],
      //       itemStyle: {
      //         normal: {
      //           borderColor: "#218bd1",
      //           borderWidth: 2,
      //           color: "#218bd1"
      //         }
      //       }
      //     },
      //     {
      //       name: "左上部2",
      //       type: "bar",
      //       label: {
      //         normal: {
      //           show: true,
      //           position: "top",

      //           fontSize: 16,
      //           color: "#218bd1",
      //           offset: [-30, -5]
      //         }
      //       },
      //       itemStyle: {
      //         color: "transparent"
      //       },
      //       tooltip: {},
      //       data: [320, 200, 260]
      //     },

      //     {
      //       z: 1,
      //       name: "task",
      //       type: "bar",
      //       barWidth: barWidth,
      //       data: [170, 182, 191],
      //       itemStyle: {
      //         normal: {
      //           color: colors[1]
      //         }
      //       }
      //     },
      //     {
      //       z: 3,
      //       name: "中上部1",
      //       type: "pictorialBar",
      //       symbolPosition: "end",
      //       data: [170, 182, 191],
      //       symbol: "diamond",
      //       symbolOffset: ["-15%", "-50%"],
      //       symbolSize: [barWidth - 4, (10 * (barWidth - 4)) / barWidth],
      //       itemStyle: {
      //         normal: {
      //           borderColor: "#2edaf8",
      //           borderWidth: 2,
      //           color: "#2edaf8"
      //         }
      //       }
      //     },

      //     {
      //       name: "中上部2",
      //       type: "bar",
      //       label: {
      //         normal: {
      //           show: true,
      //           position: "top",

      //           fontSize: 16,
      //           color: "#2edaf8",
      //           offset: [-30, -5]
      //         }
      //       },
      //       itemStyle: {
      //         color: "transparent"
      //       },
      //       tooltip: {},
      //       data: [170, 182, 191]
      //     },

      //     {
      //       z: 1,
      //       type: "bar",
      //       name: "story",
      //       barWidth: barWidth,
      //       data: [220, 182, 191],
      //       itemStyle: {
      //         normal: {
      //           color: colors[2]
      //         }
      //       }
      //     },
      //     {
      //       z: 3,
      //       name: "右上部1",
      //       type: "pictorialBar",
      //       symbolPosition: "end",
      //       data: [220, 182, 191],
      //       symbol: "diamond",
      //       symbolOffset: ["130%", "-50%"],
      //       symbolSize: [barWidth - 4, (10 * (barWidth - 4)) / barWidth],
      //       itemStyle: {
      //         normal: {
      //           borderColor: "#7b7be1",
      //           borderWidth: 2,
      //           color: "#7b7be1"
      //         }
      //       }
      //     },

      //     {
      //       name: "中上部2",
      //       type: "bar",
      //       label: {
      //         normal: {
      //           show: true,
      //           position: "top",

      //           fontSize: 16,
      //           color: "#7b7be1",
      //           offset: [-30, -5]
      //         }
      //       },
      //       itemStyle: {
      //         color: "transparent"
      //       },
      //       tooltip: {},
      //       data: [220, 182, 191]
      //     }
      //   ]
      // };

      //myChart.setOption(option);
    var dom = document.getElementById('main');
    var myChart = echarts.init(dom, null, {
      renderer: 'canvas',
      useDirtyRect: false
    }); 
    var option;
    option = {
      legend: {},
      tooltip: {},
      dataset: {
        source: [
          ['product', 'Bug', 'Story', 'Task'],
          ['Advanced Development', 2, 2, 2],
          ['Front-end Development', 2, 0, 2],
          ['Intermediate Development', 2, 0, 1],
          ['Operation and maintenance', 3, 0, 2],
          ['Primary Development', 3, 1, 1],
          ['Test', 1, 0, 2]
        ]
       },
        xAxis: { 
          axisLabel: {
            color: '#333',
            //interval: 0,
            show: false
          },
          type: 'category' 
        },
        yAxis: { 
          //max:this.threeChartValue[0]>0?this.threeChartValue[0]:0
        },
        series: [{ type: 'bar' }, { type: 'bar' }, { type: 'bar' }]
    };
     myChart.setOption(option);
    }
  }
};
</script>

<style scoped>
.card-info {
  display: flex;
  justify-content: space-between;
  margin-top: 50px;
}
.img {
  flex: 1;
  margin-right: 100px;
  box-shadow: 5px 5px 20px #e3e3e3;
}
.content {
  flex: 4;
  background: #f68080;
  border-radius: 8px;
  box-shadow: 5px 5px 20px #e3e3e3;
  padding: 20px;
}
.content li {
  line-height: 27px;
}
.ques {
  display: flex;
  width: 100%;
  margin-top: 50px;
}
.ques-type {
  width: calc((100% - 20px) / 2);
  background: #faf0f1;
  border-radius: 8px;
  margin-right: 20px;
  height: 300px;
  box-shadow: 5px 5px 20px #e3e3e3;
  overflow-y: auto;
}
.ques-info {
  width: calc((100% - 20px) / 2);
  background: #faf0f1;
  border-radius: 8px;
  height: 300px;
  box-shadow: 5px 5px 20px #e3e3e3;
  overflow-y: auto;
}
.button {
  width: 250px;
  height: 50px;
  border-radius: 8px;
  color: #fff;
  background: #e85a59;
  line-height: 50px;
  text-align: center;
}
.ques-content {
  /* width: 100%; */
  color: #3e3637;
  padding: 50px 10px 10px 50px;
  line-height: 30px;
}
.list-content {
  display: flex;
}
.list {
  border: 1px solid #eee;
  border-radius: 8px;
  box-shadow: 5px 5px 5px #eee;
  padding: 20px;
  margin: 50px 0 80px 0;
}
.list-title {
  width: 100%;
  margin-bottom: 20px;
  font-weight: 600;
}
.list-title-content {
  border-bottom: 2px solid #b4b4b4;
}
</style>