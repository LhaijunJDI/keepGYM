new Vue({
    el: '#app',
    data: function () {
        return {
            form: {
                memberId: '',
                memberName: '',
                phone: '',
                resource1: '',
                resource2: '',
                resource3: '',
                resource4: '',
                resource5: '',
            },
        }
    },
    created() {
        this.getMemberInfo();
    },
    methods: {
        getMemberInfo() {
            let that = this;
            var memberId = document.getElementById("memberId").value;
            $.ajax({
                url: "/toSearchMember",
                type: "get",
                data: {
                    "memberId": memberId,
                },
                contentType: 'application/json',
                success: function (data) {
                    if (data != null) {
                        that.form.memberName = data.username;
                        that.form.phone = data.phone;
                    } else {
                        alert("操作失败，请刷新重试！");
                    }
                }
            });
        },

        onSubmit() {
            let that = this;
            var mid = document.getElementById("memberId").value;
            this.form.memberId = mid;
            var dates = JSON.stringify(this.form);
            console.log(this.form);
            $.ajax({
                url: "/saveFeedback",
                type: "post",
                data: dates,
                contentType: "application/json",
                success: function (data) {
                    if (data == 'success') {
                        that.$message('提交成功！');
                        window.location.href = location.href;
                    }
                    if (data == 'fail') {
                        that.$message({
                            message: '提交反馈失败，请重新提交！',
                            type: 'warning'
                        });
                        window.location.href = location.href;
                    }
                }
            });
    },
    reset() {
        this.form.resource1 = '';
        this.form.resource2 = '';
        this.form.resource3 = '';
        this.form.resource4 = '';
        this.form.resource5 = '';
    },

}
})
