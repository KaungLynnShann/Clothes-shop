<template>
    <div class="my-5 container">
      <!-- Poster, pants Info -->
      <v-row class="ma-0">
        <!-- Poster -->
        <v-col cols="3" class="ma-0 pa-0">
          <v-img class="ma-0" :src="localDomain + pants.pantsPhoto" contain></v-img>
        </v-col>
  
        <!--Pants Info -->
        <v-col cols="7">
          <div class="text-h4">Name: {{ pants.pantsType }}</div>
          <div class="text-body-6 ml-2 my-2">Type: {{ pants.pantsCategory ?
           pants.pantsCategory.categoryName : 'N/A' }}</div>
          <div class="text-body-1 ml-2 my-2">Price: {{ pants.pantsPrice }} Kyat</div>
          <div class="text-body-1 ml-2 my-2">Size: {{ pants.pantsSize }} </div>
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
    name: "PantsDetails",
  
    data() {
      return {
        localDomain: utils.constant.localDomain,
        pantsId: this.$route.params.pantsId,
        pants: {},
      };
    },
  
    async created() {
      await this.fetchPants();
    },
  
    methods: {
      async fetchPants() {
        const resp = await utils.http.get("/pants/get/" + this.pantsId);
        if (resp && resp.status === 200) {
          const data = await resp.json();
          if (data) {
            this.pants = data;
          }
        }
      },
  
      goBack() {
        this.$router.push('/shop'); // Navigate to the shop page
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
  