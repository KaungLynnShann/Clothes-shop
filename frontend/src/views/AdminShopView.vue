<template>
    <div>
        <v-container style="margin-top:-10%;">
        <ShopView/>
        <v-spacer></v-spacer>
        <div>
            <v-btn color="blue" to="/admin/category/">
                <v-icon >mdi-chevron-left</v-icon>
                <span>back</span>
            </v-btn>
        </div>
    </v-container>
    </div>
  </template>
  
  <script>
  import ShopView from './ShopView.vue';
  export default {

    components:{
        ShopView,
    },
  
   data: () => ({}),
  };
  </script>
  