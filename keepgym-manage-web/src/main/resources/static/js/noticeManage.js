new Vue({
    el: '#app',
    data: function () {
        return {
            //查询会员预约信息
            course: '',
            coach: '',
            memberInfo: [{}],
            orderCourseInfo: [{}],
            orderCoachInfo: [{}],
            alreadyCheckNotice: [{}],
            confirmMemberDialog: false,
            alreadyCheckNoticeDialog: false,
            confirmInfo: {
                wh: '',
                receiveId: '',
                sendId: '',
                type: '',
                content: '',
            },
            currentMemberId: '',
        }

    },
    created() {
        this.toSearchAllMembers();
        this.toSearchAllOrderCourse();
        this.toSearchAllOrderCoach();
    },
    computed: {
        // 模糊搜索
        orderCourses() {
            const search = this.course
            if (search) {
                // filter() 方法创建一个新的数组，新数组中的元素是通过检查指定数组中符合条件的所有元素。
                // 注意： filter() 不会对空数组进行检测。
                // 注意： filter() 不会改变原始数组。
                return this.orderCourseInfo.filter(data => {
                    // some() 方法用于检测数组中的元素是否满足指定条件;
                    // some() 方法会依次执行数组的每个元素：
                    // 如果有一个元素满足条件，则表达式返回true , 剩余的元素不会再执行检测;
                    // 如果没有满足条件的元素，则返回false。
                    // 注意： some() 不会对空数组进行检测。
                    // 注意： some() 不会改变原始数组。
                    return Object.keys(data).some(key => {
                        // indexOf() 返回某个指定的字符在某个字符串中首次出现的位置，如果没有找到就返回-1；
                        // 该方法对大小写敏感！所以之前需要toLowerCase()方法将所有查询到内容变为小写。
                        return String(data[key]).toLowerCase().indexOf(search) > -1
                    })
                })
            }
            return this.orderCourseInfo
        },
        orderCoaches() {
            const search = this.coach
            if (search) {
                // filter() 方法创建一个新的数组，新数组中的元素是通过检查指定数组中符合条件的所有元素。
                // 注意： filter() 不会对空数组进行检测。
                // 注意： filter() 不会改变原始数组。
                return this.orderCoachInfo.filter(data => {
                    // some() 方法用于检测数组中的元素是否满足指定条件;
                    // some() 方法会依次执行数组的每个元素：
                    // 如果有一个元素满足条件，则表达式返回true , 剩余的元素不会再执行检测;
                    // 如果没有满足条件的元素，则返回false。
                    // 注意： some() 不会对空数组进行检测。
                    // 注意： some() 不会改变原始数组。
                    return Object.keys(data).some(key => {
                        // indexOf() 返回某个指定的字符在某个字符串中首次出现的位置，如果没有找到就返回-1；
                        // 该方法对大小写敏感！所以之前需要toLowerCase()方法将所有查询到内容变为小写。
                        return String(data[key]).toLowerCase().indexOf(search) > -1
                    })
                })
            }
            return this.orderCoachInfo
        },
    },
    methods: {


        //查找全部会员信息
        toSearchAllOrderCourse() {
            let that = this;
            $.ajax({
                url: "/toSearchAllOrderCourse",
                type: "get",
                data: {},
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        that.orderCourseInfo = data;
                    }

                }
            })
        },
        toSearchAllOrderCoach() {
            let that = this;
            $.ajax({
                url: "/toSearchAllOrderCoach",
                type: "get",
                data: {},
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        that.orderCoachInfo = data;
                    }
                }
            })
        },
        toSearchAllMembers() {
            let that = this;
            $.ajax({
                url: "/toSearchAllEndTimeMembers",
                type: "get",
                data: {},
                dataType: "json",
                success: function (data) {
                    if (data != null) {
                        that.memberInfo = data;
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
                    if (data == 'success') {
                        that.$alert('', '通知成功', {
                            confirmButtonText: '确定',
                            callback: action => {
                                that.toSearchAllMembers();
                                that.toSearchAllOrderCourse();
                                that.toSearchAllOrderCoach();
                                that.confirmMemberDialog = false;
                            }
                        });
                    }
                    if (data == 'fail') {
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
        },
        setOrderCoachNum(coachId, memberId) {
            let that = this ;
            this.$confirm('该会员已上一节私教?', '授课提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
               $.ajax({
                   url:'/toUpdateOrderCoachNum',
                   type:'post',
                   data:{"coachId":coachId,"memberId":memberId},
                   success:function (data) {
                       if(data === 'success'){
                           that.$alert('', '打卡成功', {
                               confirmButtonText: '确定',
                               callback: action => {
                                   that.toSearchAllOrderCoach();
                               }
                           });
                       }
                       if(data === 'fail'){
                           that.$alert('请稍后再试', '打卡失败', {
                               confirmButtonText: '确定',
                               callback: action => {
                               }
                           });
                       }
                       if(data === 'zero'){
                           that.$alert('该会员剩余私教课为零', '打卡失败', {
                               confirmButtonText: '确定',
                               callback: action => {
                               }
                           });
                       }
                   }
               });
            }).catch(() => {

            });
        },
    }
});