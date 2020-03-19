new Vue({
    el: '#app',
    data: function () {
        return {
            totalMembers: '',
            clockMembers:'',
            totalIncome:'',
            newMembers:'',
        };


    },
    created() {
        this.toSearchTotalMembers();
        this.toSearchClockMembers();
        this.toSearchTotalIncome();
        this.toSearchNewMembers();
    },
    mounted(){
    },
    computed: {},
    methods: {
        toSearchTotalMembers() {
            let that = this;
            $.ajax({
                url: "/toSearchAllMembers",
                type: "get",
                data: {},
                success: function (data) {
                    if (data != null) {
                        that.totalMembers = data.length;
                    }
                    if(data == null){
                        that.totalMembers = "会员人数显示失败"
                    }
                }
            });

        },
        toSearchClockMembers(){
            let that = this;
            $.ajax({
                url: "/toSearchClockMembers",
                type: "get",
                data: {},
                success: function (data) {
                    if (data != null) {
                        that.clockMembers = data.length;
                    }
                    if(data == null){
                        that.clockMembers = "打卡人数显示失败"
                    }
                }
            });
        },

        toSearchTotalIncome(){
            let that = this;
            $.ajax({
                url: "/toSearchTotalIncome",
                type: "get",
                data: {},
                success: function (data) {
                    if (data != null) {
                        that.totalIncome = data;
                    }
                    if(data == null){
                        that.totalIncome = "";
                    }
                }
            });
        },

        toSearchNewMembers(){
            let that = this;
            $.ajax({
                url: "/toSearchNewMembers",
                type: "get",
                data: {},
                success: function (data) {
                    if (data != null) {
                        that.newMembers = data;
                    }
                    if(data == null){
                        that.newMembers = "新增会员人数显示失败";
                    }
                }
            });
        }

    }
});