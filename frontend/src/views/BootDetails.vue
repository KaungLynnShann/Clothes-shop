<template>
    <div class="my-5 container">
      <!-- Poster,boot Info -->
      <v-row class="ma-0">
        <!-- Poster -->
        <v-col cols="3" class="ma-0 pa-0">
          <v-img class="ma-0" :src="localDomain + boot.bootPhoto" contain></v-img>
        </v-col>
  
        <!-- boot Info -->
        <v-col cols="7">
          <div class="text-h4">Name: {{ boot.bootType }}</div>
          <div class="text-body-6 ml-2 my-2">Type: {{ boot.bootCategory ? 
          boot.bootCategory.categoryName : 'N/A' }}</div>
          <div class="text-body-1 ml-2 my-2">Price: {{ boot.bootPrice }} Kyat</div>
          <div class="text-body-1 ml-2 my-2">Size: {{ boot.bootSize }} </div>
        </v-col>
      </v-row>
      
      <!-- Back Button with Icon and Text -->
      <div class="back-button" @click="goBack">
        <v-icon>mdi-chevron-left</v-icon>
        <span>Back</span>
      </div>
    </div>
  </template>
  
  <script>
  import utils from "@/utils/utils";
  
  export default {
    name:"BootDetails",
  
    data() {
      return {
        localDomain: utils.constant.localDomain,
        bootId: this.$route.params.bootId,
        boot: {},
      };
    },
  
    async created() {
      await this.fetchBoot();
    },
  
    methods: {
      async fetchBoot() {
        const resp = await utils.http.get("/boot/get/" + this.bootId);
        if (resp && resp.status === 200) {
          const data = await resp.json();
          if (data) {
            this.boot = data;
          }
        }
      },
  
      goBack() {
        this.$router.push('/shop'); 
      }
    },
  };
  </script>
  
  <style scoped>
  .container {
    margin-left: 10vw !important;
    margin-right: 10vw !important;
  }
  
  .back-button {
    cursor: pointer;
    display: inline-flex;
    align-items: center;
    margin-top: 20px;
  }
  
  .back-button span {
    margin-left: 8px;
  }
  </style>
  