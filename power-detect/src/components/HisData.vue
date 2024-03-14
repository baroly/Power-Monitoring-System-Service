<template>
    <div>
        <el-link type="primary" @click="backServer()">伺服裝置列表</el-link> >>
        <el-link type="primary" @click="backDev()">IoT裝置列表</el-link> >>
        <el-link type="primary" @click="currentUse()">當月使用數據</el-link> >>
        <el-link type="success" >年度歷史紀錄</el-link>
    </div>

    <div style="margin-top: 20px;">
        <el-text class="mx-1" size="large">選擇年度</el-text>
        <el-date-picker
          style="margin-left: 5px;"
          v-model="selectYear"
          type="year"
          placeholder="Pick a year"
          @change="getHisData(1)" 
           />
    </div>
    
    <div class="card" style="width: 1200px; height: 800px;">
        <Chart type="bar" :data="chartData" :options="chartOptions" />
    </div>
</template>

<script lang="ts">
import Chart from 'primevue/chart'

export default{
    components:{
        Chart
    },
    setup(){
        
    },
    data(){
        return{
            dataInfo: JSON.parse(sessionStorage.getItem('dataInfo') || "{}"),
            selectYear: new Date(),
            chartData: {
                labels:['Jen', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep' , 'Oct', 'Nov', 'Dec'],
                datasets: [{
                    label: 'kW h',
                    data: [100, 325, 702, 620, 300, 604, 789, 222, 823, 293, 890, 283],
                    backgroundColor: ['rgba(255, 159, 64, 0.2)', 'rgba(75, 192, 192, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(153, 102, 255, 0.2)'],
                    borderColor: ['rgb(255, 159, 64)', 'rgb(75, 192, 192)', 'rgb(54, 162, 235)', 'rgb(153, 102, 255)'],
                    borderWidth: 1
                }]
            },
            chartOptions: {}
        }
    },
    methods:{
        setChartOptions () {
            const documentStyle = getComputedStyle(document.documentElement);
            const textColor = documentStyle.getPropertyValue('--text-color');
            const textColorSecondary = documentStyle.getPropertyValue('--text-color-secondary');
            const surfaceBorder = documentStyle.getPropertyValue('--surface-border');

            return {
                plugins: {
                    legend: {
                        labels: {
                            color: textColor
                        }
                    }
                },
                scales: {
                    x: {
                        ticks: {
                            color: textColorSecondary
                        },
                        grid: {
                            color: surfaceBorder
                        }
                    },
                    y: {
                        beginAtZero: true,
                        ticks: {
                            color: textColorSecondary
                        },
                        grid: {
                            color: surfaceBorder
                        }
                    }
                }
            };
        },
        getHisData(type){
            var selectYear = 2000
            if(type === 0){
                selectYear = new Date().getFullYear()
            }else{
                selectYear = new Date(this.selectYear).getFullYear()
            }
            
            console.log(selectYear)
            console.log(selectYear)
            this.$axios.get(this.$url + '/ampereHisData/getHis?year=' + selectYear + '&id=' + this.dataInfo.id + '&port=' + this.dataInfo.port)
                .then(resp => resp.data)
                .then(resp => {
                    if(resp.code == 200){
                        console.log(resp)
                        this.chartData.datasets[0].data = resp.data
                        //this.DevData = resp.data
                        //this.tableData = resp.total
                    }else{
                        alert('查詢錯誤')
                    }
                }).catch(error => {
                    alert('伺服器異常請稍後再試')
                    console.log(error)
                })
            
            

            
            //const year = new Date(this.selectYear)
            //console.log(year)
            //console.log(year.getFullYear)
            //console.log('yeeeeee')
        },
        backServer(){
            this.$router.replace('/server')
        },
        backDev(){
            this.$router.replace('/dev')
        },
        currentUse(){
            this.$router.replace('/data')
        }
    },
    beforeMount() {
        this.getHisData(0)
        //this.chartData.datasets[0].data[0] = 666
        //console.log(this.dataInfo)
        this.setChartOptions()
    }


}
</script>