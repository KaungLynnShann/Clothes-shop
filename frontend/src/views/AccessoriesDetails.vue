<template>
    <div class="my-5 container">
      <!-- Poster, accessories Info -->
      <v-row class="ma-0">
        <!-- Poster -->
        <v-col cols="3" class="ma-0 pa-0">
          <v-img class="ma-0" :src="localDomain + hat.hatPhoto" contain></v-img>
        </v-col>
  
        <!-- hat Info -->
        <v-col cols="7">
          <div class="text-h4">Name: {{ hat.hatType }}</div>
          <div class="text-body-6 ml-2 my-2">Type: {{ hat.hatCategory ? hat.hatCategory.categoryName : 'N/A' }}</div>
          <div class="text-body-1 ml-2 my-2">Price: {{ hat.hatPrice }} Kyat</div>
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
    name: "AccessoriesDetails",
  
    data() {
      return {
        localDomain: utils.constant.localDomain,
        hatId: this.$route.params.hatId,
        hat: {},
      };
    },
  
    async created() {
      await this.fetchHat();
    },
  
    methods: {
      async fetchHat() {
        const resp = await utils.http.get("/hat/get/" + this.hatId);
        if (resp && resp.status === 200) {
          const data = await resp.json();
          if (data) {
            this.hat = data;
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
  