new Vue({
    el: '#app',
    data: function () {
        return {
            form: {
                courseId: '',
                name: '',
                type: '',
                num: '',
                startTime: '',
                endTime: '',
                coach: '',
                content: '',
                picSrc: '',
            },

            orderInfo: {
                memberId: '',
                courseId: '',
            },
            messageNum: '',
        }
    },
    mounted() {
        this.$nextTick(() => {
            this.toSearchAllNotice();
        });
    },
    created() {
        this.getAllCourse();
    },
    methods: {
        getAllCourse() {
            let that = this;
            var memberId = document.getElementById("memberId").value;
            $.ajax({
                url: "/toSearchAllCourse",
                type: "get",
                contentType: "application/json",
                success: function (data) {
                    console.log(data);
                    if (data != null) {
                        that.form = data;
                    } else {
                        alert("请求超时，请刷新重试！");
                    }
                }
            });
        },
        confirmOnSubmit(id){
            let that = this;
            this.$confirm('你是否要需要该课程?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                that.onSubmit(id);
            }).catch(() => {

            });
        },
        onSubmit(courseId) {
            let that = this;
            var mid = document.getElementById("memberId").value;
            this.orderInfo.memberId = mid;
            this.orderInfo.courseId = courseId;
            var dates = JSON.stringify(this.orderInfo);
            console.log(this.orderInfo);
            $.ajax({
                url: "/saveOrderCourse",
                type: "post",
                data: dates,
                contentType: "application/json",
                success: function (data) {
                    if (data == 'success') {
                        that.$alert('', '您已成功预约该课程', {
                            confirmButtonText: '确定',
                            callback: action => {
                                window.location.href = location.href;
                            }
                        });

                    }
                    if (data == 'fail') {
                        this.$message({
                            message: '预约失败，请重新预约！',
                            type: 'warning'
                        });
                        window.location.href = location.href;
                    }
                    if (data == 'already') {
                        that.$notify({
                            title: '警告',
                            message: '您已预约过此课程，请勿重复预约！',
                            type: 'warning'
                        });
                    }
                    if (data == 'full') {
                        that.$notify({
                            title: '警告',
                            message: '预约人数已满，请预约其它课程！',
                            type: 'warning'
                        });

                    }
                }
            });
        },
        toSearchAllNotice() {
            let that = this;
            var memberId = document.getElementById("memberId").value;
            $.ajax({
                url: "/toSearchAllNotice",
                type: "get",
                data: {
                    "memberId": memberId,
                },
                contentType: 'application/json',
                success: function (data) {
                    if (data != null) {
                        that.memberNotice = data;
                        that.messageNum = data.length;
                    }
                    if (data == null) {
                        alert("请求超时，请刷新重试！");
                    }
                },
            });
        },
        //退出登录
        loginout(){
            $.ajax({
                url:"/loginOut",
                type:"get",
                data:{},
                success:function () {

                }
            })
        },


    }
})
