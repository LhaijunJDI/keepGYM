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
                courseId: '',
            }
        }
    },
    created() {
        this.getAllCoach();
    },
    methods: {
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
                        that.$alert('您已成功预约该课程','', {
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
                            message: '您已预约过此课程，请勿重复预约！',
                            type: 'warning'
                        });
                    }
                    if(data == 'full'){
                        that.$notify({
                            title: '警告',
                            message: '预约人数已满，请预约其它课程！',
                            type: 'warning'
                        });

                    }
                }
            });
        },


    }
})
