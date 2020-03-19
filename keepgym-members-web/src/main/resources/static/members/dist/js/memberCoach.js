new Vue({
    el: '#app',
    data: function () {
        return {
            form: {
                id: '',
                name: '',
                phone: '',
                gender: '',
                type: '',
                enterTime:'',
                content: '',
                picSrc:'',
                teachTime:'',
            },

            orderInfo:{
                memberId:'',
                coachId: '',
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
        this.getAllCoach();
    },
    methods: {
        //获取全部教练的信息并传递给form
        getAllCoach() {
            let that = this;
            var memberId = document.getElementById("memberId").value;
            $.ajax({
                url: "/toSearchAllCoach",
                type: "get",
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

        onSubmit(id) {
            let that = this;
            var mid = document.getElementById("memberId").value;
            this.orderInfo.memberId = mid;
            this.orderInfo.coachId = id;
            var dates = JSON.stringify(this.orderInfo);
            $.ajax({
                url: "/saveOrderCoach",
                type: "put",
                data: dates,
                contentType: "application/json",
                success: function (data) {
                    if (data == 'success') {
                        that.$alert('请等待通知试教时间','预约成功', {
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
                    if(data == 'already'){
                        that.$notify({
                            title: '警告',
                            message: '您已预约过该教练，请勿重复预约！',
                            type: 'warning'
                        });
                    }
                }
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

    }
})
