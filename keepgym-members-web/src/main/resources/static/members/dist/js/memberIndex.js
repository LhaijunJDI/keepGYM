new Vue({
    el: '#app',
    data: function () {
        return {
            form: [{}],
            tableHead: [
                {lable: '序号', prop: 'id'},
                {lable: '打卡时间', prop: 'strClockInTime'},
                {lable: '离开时间', prop: 'strClockOutTime'},
                {lable: '健身时长(min)   ', prop: 'sportTime'},
            ],
            memberInfo: {
                memberName: '',
                endTime: '',
            },
            memberNotice: [{}],
            messageNum: '',
            value: new Date(),
        }
    },
    mounted() {
        this.$nextTick(() => {
            this.toSearchAllNotice();
        });

    },
    methods: {
        findAllClocks() {
            this.findMemberInfo();
            let that = this;
            var memberId = document.getElementById("memberId").value;
            $.ajax({
                url: "/toSearchAllClock",
                type: "get",
                data: {"memberId": memberId},
                contentType: "application/json",
                success: function (data) {
                    if (data != null) {
                        that.form = data;
                    }
                    if (data == null) {
                        alert("请求超时，请刷新重试！");
                    }
                }
            })
        },
        findMemberInfo() {
            var memberId = document.getElementById("memberId").value;
            let that = this;
            $.ajax({
                url: "/toSearchMember",
                type: "get",
                data: {
                    "memberId": memberId,
                },
                contentType: 'application/json',
                success: function (data) {
                    if (data != null) {
                        that.memberInfo.memberName = data.username;
                    }
                    if (data == null) {
                        alert("请求超时，请刷新重试！");
                    }
                },
            });
        },

        loginout() {
            this.$message({
                message: '您已成功退出！',
                type: 'warning'
            });
        },
        //显示打卡天数
        handleSelected(day) {
            let flag = 0; //默认显示为0
            this.form.forEach(item => { //this.foem是后台传递过来的数据
                if (item.strClockInTime1 == day) {  //判断显示数据
                    flag = 1;
                }
            })
            return flag;
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
                        console.log(data.length);
                        that.messageNum = data.length;
                    }
                    if (data == null) {
                        alert("请求超时，请刷新重试！");
                    }
                },
            });
        },
    },
})
