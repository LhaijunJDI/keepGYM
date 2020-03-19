new Vue({
    el: '#app',
    data: function () {
        return {
            alreadyCheckMessageList:[{}],
            noCheckMessageList:[{}],
            messageNum: '',
            currentMemberId:'',
        }
    },
    mounted() {
        this.$nextTick(() => {
            this.currentMemberId = document.getElementById("memberId").value;
            this.toSearchNoCheckNotice();
            this.toSearchCheckNotice();

        });
    },
    created() {

    },
    methods: {
        toSearchNoCheckNotice() {
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
                        that.noCheckMessageList = data;
                        that.messageNum = data.length;
                    }
                    if (data == null) {
                        alert("请求超时，请刷新重试！");
                    }
                },
            });
        },
        toSearchCheckNotice(){
            let that = this;
            var memberId = document.getElementById("memberId").value;
            $.ajax({
                url: "/toSearchAllCheckNotice",
                type: "get",
                data: {
                    "memberId": memberId,
                },
                contentType: 'application/json',
                success: function (data) {
                    if (data != null) {
                        that.alreadyCheckMessageList = data;
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
        setAllNoticeCheck(){
            let that = this ;
            $.ajax({
                url: "/setAllNoticeCheck",
                type: "post",
                data: {
                    "memberId": this.currentMemberId,
                },
                success: function (data) {
                    if (data == 'success') {
                        that.toSearchNoCheckNotice();
                        that.toSearchCheckNotice();
                    }
                    if (data == 'fail') {
                        that.$alert('请刷新后再试', '标记失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    }
                },
            });
        },
        setNoticeCheck(id){
            let that = this;
            $.ajax({
                url: "/setNoticeCheck",
                type: "post",
                data: {
                    "id": id,
                },
                success: function (data) {
                    if (data == 'success') {
                                that.toSearchNoCheckNotice();
                                that.toSearchCheckNotice();
                    }
                    if (data == 'fail') {
                        that.$alert('请刷新后再试', '标记失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    }
                },
            });
        },
        deleteNotice(id){
            let that = this;
            $.ajax({
                url: "/deleteNotice",
                type: "delete",
                data: {
                    "id": id,
                },
                success: function (data) {
                    if (data == 'success') {

                                that.toSearchNoCheckNotice();
                                that.toSearchCheckNotice();

                    }
                    if (data == 'fail') {
                        that.$alert('请刷新后再试', '删除失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    }
                },
            });
        },
        confirmDelete() {
            let that = this;
            this.$confirm('你确定要删除全部通知吗？', '删除提醒', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                that.deleteAllNotice();
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '取消删除'
                });
            });
        },
        deleteAllNotice(){
            let  that = this ;
            $.ajax({
                url: "/deleteAllNotice",
                type: "delete",
                data: {
                    "memberId": this.currentMemberId,
                },
                success: function (data) {
                    if (data == 'success') {
                        that.toSearchNoCheckNotice();
                        that.toSearchCheckNotice();
                    }
                    if (data == 'fail') {
                        that.$alert('请刷新后再试', '删除失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    }
                },
            });
        }

    }
})
