var imageView = null;
imageView = new Vue({
    el: "#imageView",
    data: {
        imageUrl : ''
    },
    methods: {

        initPage: function(srcImageUrl) {
            this.imageUrl = srcImageUrl;

            this.$forceUpdate();
        },
    },

    mixins: [CommonUtil]
});
