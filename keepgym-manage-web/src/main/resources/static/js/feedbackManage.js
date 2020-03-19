new Vue({
    el: '#app',
    data: function () {
        return {
            feedbackList: [{}],
            confirmInfo: {
                wh: '',
                receiveId: '',
                sendId: '',
                type: '',
                content: '',
            },
            alreadyCheckNotice: [{}],
            currentMemberId: '',
            confirmMemberDialog: false,
            alreadyCheckNoticeDialog: false,
        }

    },
    created() {
        this.toSearchAllFeedback();
    },
    computed: {},
    methods: {


        //查找全部会员信息
        toSearchAllFeedback() {
            let that = this;
            $.ajax({
                url: "/toSearchAllFeedback",
                type: "get",
                data: {},
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        that.feedbackList = data;
                    }

                }
            })
        },

        setCurrentMemberId(wh, id) {
            this.confirmInfo.wh = wh;
            this.currentMemberId = id;
            this.confirmMemberDialog = true;
        },

        addMessage() {
            let that = this;
            this.confirmInfo.sendId = document.getElementById("managerId").value;
            this.confirmInfo.receiveId = this.currentMemberId;
            var datas = JSON.stringify(this.confirmInfo);
            $.ajax({
                url: "/toAddMessage",
                type: "put",
                data: datas,
                contentType: "application/json",
                success: function (data) {
                    if (data === 'success') {
                        that.$alert('', '通知成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.toSearchAllFeedback();
                                that.confirmMemberDialog = false;
                            }
                        });
                    }
                    if (data === 'fail') {
                        that.$alert('请稍后再试', '通知失败', {
                            confirmButtonText: '确定',
                            callback: action => {

                            }
                        });
                    }
                }
            })
        },
        checkAlreadyNotice(id) {
            let that = this;
            $.ajax({
                url: "/toSearchAlreadyNotice",
                type: "get",
                data: {"memberId": id},
                success: function (data) {
                    if (data != null) {
                        that.alreadyCheckNotice = data;
                        that.alreadyCheckNoticeDialog = true;
                    }
                    if (data == null) {
                        that.$alert('请稍后再试', '查询失败', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    }
                }
            })
        }
    }
});