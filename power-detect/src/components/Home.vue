<template>
    <div style="margin-top: -4%;margin-left: -1%; background: #edefef;">
        <h1 style="font-size: 50px;">歡迎使用本系統</h1>
        <el-descriptions :column="2" min-width="50%" border>
            <el-descriptions-item>
                <template #label>帳號</template>{{ user.userName }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template #label>email</template>{{ user.userEmail }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template #label>暱稱</template>{{ user.nickName }}
            </el-descriptions-item>
            <el-descriptions-item>
                <template #label>創建時間</template>{{ user.createTime }}
            </el-descriptions-item>
        </el-descriptions>
        <div style="text-align: center; font-size: 30px; margin-top: 2%;">
            <DateUtils></DateUtils>
        </div>
    </div>
</template>
<script lang="ts">
import DateUtils from './DateUtils.vue'

export default {
    name: 'Home',
    components:{
        DateUtils,
    },
    data(){
        return{
            user: JSON.parse(sessionStorage.getItem('CurUser') || "{}") 
        }
    },
    methods:{
        init(){
            
        }
    }
}
</script>

<style scoped>
.el-descriptions {
  margin-top: 40px;
  font-size: 140px; 
}
.el-descriptions-item{
    font-size: 500px;
}
</style>