<template>
    <div class="my-5 container">
      <!-- Poster, Shirt Info -->
      <v-row class="ma-0">
        <!-- Poster -->
        <v-col cols="3" class="ma-0 pa-0">
          <v-img class="ma-0" :src="localDomain + shirt.shirtPhoto" contain></v-img>
        </v-col>
  
        <!-- Shirt Info -->
        <v-col cols="7">
          <div class="text-h4">Name: {{ shirt.shirtType }}</div>
          <div class="text-body-6 ml-2 my-2">Type: {{ shirt.shirtCategory ? shirt.shirtCategory.categoryName : 'N/A' }}</div>
          <div class="text-body-1 ml-2 my-2">Price: {{ shirt.shirtPrice }} Kyat</div>
          <div class="text-body-1 ml-2 my-2">Size: {{ shirt.shirtSize }} </div>
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
    name: "ShirtDetails",
  
    data() {
      return {
        localDomain: utils.constant.localDomain,
        shirtId: this.$route.params.shirtId,
        shirt: {},
      };
    },
  
    async created() {
      await this.fetchShirt();
    },
  
    methods: {
      async fetchShirt() {
        const resp = await utils.http.get("/shirt/get/" + this.shirtId);
        if (resp && resp.status === 200) {
          const data = await resp.json();
          if (data) {
            this.shirt = data;
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
  