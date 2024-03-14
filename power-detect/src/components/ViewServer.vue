<template>
    <div>
        <div>
            <el-link type="primary" @click="backServer()">伺服裝置列表</el-link> >>
            <el-link type="success" >IoT裝置列表</el-link>
        </div>

        <div style="margin: 5px; margin-top: 20px;">
            <el-button style="" type="primary" @click="addServer">綁定IoT裝置</el-button>
        </div>

        <div style="flex: 1; text-align: left; font-size: 36px;">
            <span style="font-size: 40px;">IoT裝置列表</span>
        </div>
        
        <el-table :data="ServerData"
        :header-cell-style="{ background:'#545c64', color:'#ffffff', textAlign:'center'}"
        :cell-style="{'font-size':'26px'}"
        border >
            <el-table-column prop="id" label="ID" width="" />
            <el-table-column prop="alias" label="別稱" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="primary" @click="viewData(scope.row)">檢視</el-button>
                    <el-button type="success" @click="mod(scope.row)">編輯</el-button>
                    <!-- <el-popconfirm title="確定要刪除?" @confirm="del(scope.row.id)">
                        <template #reference>
                            <el-button type="danger">刪除</el-button>
                        </template>
                    </el-popconfirm> -->
                </template>
            </el-table-column>
        </el-table>
        
        <el-pagination
        v-model:current-page="pageNum"
        v-model:page-size="pageSize"
        :page-sizes="[5, 10, 20, 40]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalData"
        :before-close="resetForm"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"/>

        <el-dialog
            v-model="addIoTDevDialog"
            :title="titleMsg"
            width="20%"
            align-center>
                <el-form :model="addIoTDevForm" :rules="rules" label-width="30%" ref="addIoTDevForm">
                    <el-form-item label="別名" prop="alias">
                        <el-col :span="20">
                            <el-input v-model="addIoTDevForm.alias" />
                        </el-col>
                    </el-form-item>
                    <el-form-item label="序號" prop="uid">
                      <el-col :span="20">
                        <el-input v-model="addIoTDevForm.uid" :disabled="lockuid" />
                      </el-col>
                    </el-form-item>
                </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="addIoTDevDialog = false">取消</el-button>
                    <el-button type="primary" @click="saveIotDev">送出</el-button>
                </span>
            </template>
        </el-dialog>
        
    </div>
</template>
<script lang="ts">
import { User, Menu as search} from '@element-plus/icons-vue'
import { ElMessage, checkTagEmits } from 'element-plus'
import { el } from 'element-plus/es/locale'
import { inject, ref } from 'vue'

export default{
    name: 'MainContent',
    components:{
        search
    },
    setup(){
        
    },
    data() {
        let checkAge = (rule, value, callback) => {
            if(value > 150){
                callback(new Error('年齡過大'))
            }else{
                callback()
            }
        }
        let checkDupliacte = (rule, value, callback) => {
            
        }
        return {
            server: JSON.parse(sessionStorage.getItem('server') || "{}"),
            tableData: [],
            ServerData: [],
            pageNum: 1,
            pageSize: 5,
            totalData: 0,
            name:'',
            addIoTDevDialog:false,
            titleMsg:'新增伺服器',
            lockuid:false,
            addIoTDevForm:{
                alias:'',
                uid:'',
            },
            rules:{
                alias:[
                    { required: true, message: '請輸入別名', trigger: 'blur' },
                ],
                uid:[
                    { required: true, message: '請輸入產品序號', trigger: 'blur' },
                ],
            }
        }
    },
    methods: {
        saveIotDev(){
            if(this.addIoTDevForm.id){
                this.doUpdate()
            }else{
                console.log('beforeDoAdd')
                this.doAdd()
            }
        },
        doAdd(){
            this.$refs.addIoTDevForm.validate((valid)=>{
                if(valid){
                    this.addIoTDevForm.userServerId = this.server.id
                    //var data = this.doReq('/userServer/creatServer', 'post')
                    console.log('before post')
                    console.log(this.$url + '/userDev/createDev')
                    this.$axios.post(this.$url + '/userDev/createDev', this.addIoTDevForm)
                    .then(resp => resp=resp.data).then(resp => {
                        if(resp.code == 200){
                            ElMessage({
                                message: '新增成功',
                                type: 'success',
                            })
                            this.loadAllListGet()
                            this.addIoTDevDialog = false
                            
                        }else{
                            alert(resp.msg)
                        }
                    }).catch(error => {
                        console.log(error)
                    })
                    //console.log(data)
                }else{
                    console.log('error submit')
                    return false
                }
            })
        },
        doUpdate(){
            this.$refs.addIoTDevForm.validate((valid)=>{
                if(valid){
                    this.$axios.post(this.$url + '/userDev/modDev', this.addIoTDevForm)
                        .then(resp => resp=resp.data).then(resp => {
                        if(resp.code == 200){
                            ElMessage({
                                message: '修改成功',
                                type: 'success',
                            })
                            this.loadAllListGet()
                            this.addIoTDevDialog = false
                        }else{
                            alert('修改失敗')
                        }
                    }).catch(error => {
                        console.log('err')
                    })
                }else{
                    console.log('error submit')
                    return false
                }
            })
            
            
        },
        doReq(action,meth){
            switch(meth){
                case 'get':
                    console.log('print get: ' + action)
                case 'post':
                    this.$axios.post(this.$url + action, this.addUserForm)
                    .then(resp => resp=resp.data).then(resp => {
                        return resp
                        // if(resp.code == 200){
                        //     // ElMessage({
                        //     //     message: 'Congrats, this is a success message.',
                        //     //     type: 'success',
                        //     // })
                        //     // this.loadListPost()
                        //     // this.addUserDialog = false
                            
                        // }else{
                        //     alert('新增失敗')
                        // }
                    }).catch(error => {
                        console.log(error)
                    })
                    // console.log('print post: ' + this.$url + action)
                    // console.log(this.addServerForm)
                    //return {id:1,name:'leo'}
            }
            //console.log('haha')
        },
        loadAllListGet(){
            console.log(this.server)
            this.$axios.get(this.$url + '/userDev/getDev?serverId=' + this.server.id)
                .then(resp => resp.data)
                .then(resp => {
                    if(resp.code == 200){
                        console.log(resp)
                        this.ServerData = resp.data
                        this.tableData = resp.total
                    }else{
                        alert('查詢錯誤')
                    }
                }).catch(error => {
                    alert('伺服器異常請稍後再試')
                    console.log(error)
                })
        },
        loadListPost(){
            console.log('Post')
            this.$axios.post(this.$url + '/user/listPageC1', {
                    pageNum:this.pageNum,
                    pageSize:this.pageSize,
                    param:{
                        name:this.name,
                        gender:this.gender
                    }
                })
                .then(resp => resp=resp.data).then(resp => {
                    if(resp.code == 200){
                        this.totalData = resp.total
                        this.tableData = resp.data
                    }else{
                        alert('查詢錯誤')
                    }
                }).catch(error => {
                    console.log('erraaaa')
                })
        },
        resetForm(){
            this.$refs.addIoTDevForm.resetFields();
        },
        handleSizeChange(val){
            this.pageNum = 1
            this.pageSize = val
            this.loadListPost()
        },
        handleCurrentChange(val){
            this.pageNum = val
            this.loadListPost()
        },
        addServer(){
            this.titleMsg = '綁定IoT裝置'
            this.addIoTDevDialog = true
            this.addIoTDevForm.id = ''
            this.lockuid = false
            //this.resetForm()
            this.$nextTick(()=>{
                this.resetForm()
            })
        },
        mod(row){
            
            console.log(row.id)
            this.addIoTDevDialog = true
            this.$nextTick(()=>{
                this.addIoTDevForm.id = row.id
                this.addIoTDevForm.alias = row.alias
                this.addIoTDevForm.uid = row.uid
                this.lockuid = true
                this.titleMsg = '編輯裝置'
            })
        },
        del(userId){
            this.$axios.post(this.$url + '/user/delete', {id:userId})
                .then(resp => resp=resp.data).then(resp => {
                    if(resp.code == 200){
                        ElMessage({
                            message: 'Congrats, this is a success message.',
                            type: 'success',
                        })
                        this.loadListPost()
                    }else{
                        alert('新增失敗')
                    }
                }).catch(error => {
                    console.log('err')
                })
        },
        resetParam(){
            this.name='',
            this.gender='',
            this.loadListPost()
        },
        viewData(row){
            console.log(row)
            sessionStorage.setItem("dev", JSON.stringify(row))
            this.$router.replace('/data')
        },
        backServer(){
            this.$router.replace('/server')
        }
    },
    beforeMount() {
        this.loadAllListGet();
    }
    
    
}
</script>