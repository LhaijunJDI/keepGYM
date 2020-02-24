new Vue({
    el: '#app',
    data: function () {
        return {
            value: new Date(),
        }
    },

    methods: {
        loginout(){
            this.$message({
                message: '您已成功退出！',
                type: 'warning'
              });
        },

    },

})
