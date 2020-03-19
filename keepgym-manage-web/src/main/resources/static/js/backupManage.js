new Vue({
    el: '#app',
    data: function () {
        return {}
    },
    created() {

    },
    computed: {},
    methods: {
        importExcel(path) {
            let that = this;
            this.$confirm('你确定要导出数据到Excel吗？', '导出提醒', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                $.ajax({
                    url:"/to"+path,
                    type:"get",
                    data:{},
                    success:function (data) {
                        if(data === 'success'){
                            that.$alert('储存位置为E:/excel', '导出成功', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        }
                        if(data === 'fail'){
                            that.$alert('请稍后再试', '导出失败', {
                                confirmButtonText: '确定',
                                callback: action => {
                                }
                            });
                        }
                    }
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消删除'
                });

            });
        },
    }
});